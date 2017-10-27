#include "PID_v1.h"

//variable DHC22
//int power = 0;
//int peltier_level = map(power, 0, 99, 0, 255); 
const byte BROCHE_CAPTEUR = 5; 
const byte DHT_SUCCESS = 0;        // Pas d'erreur
const byte DHT_TIMEOUT_ERROR = 1;  // Temps d'attente d�pass�
const byte DHT_CHECKSUM_ERROR = 2; // Donn�es re�ues erron�es
byte readDHT11(byte pin, float* temperature, float* humidity);
byte readDHT22(byte pin, float* temperature, float* humidity);
byte readDHTxx(byte pin, byte* data, unsigned long start_time, unsigned long timeout);
float humidity=0;
float temperature=0;

//Liste des PINs
int pinTempCannette= A1;
int pinTempPeltier= A0;
int pinCourantPeltier= 3;
int pinDHC22= BROCHE_CAPTEUR;
int led = 13;

//liste value pin
float pinTempCannetteValue=0;
float pinTempPeltierValue= 0;
int pinDHC22Value= 0;

//variable trame ressus
const int NUMBER_OF_TRAMES = 2;
const int NUMBER_OF_BITS = 7;
const int TRAME_LENGTH[] = { 5, 2 };
String returnOrder;
String returnOrderType;
int a = 0;
int receiveTrames[] = { 0, 0};

const double PLAGE_TENSION = 5.0;
const double TENSION = 5.0;
const double Rref = 10000.0;
double A_1 = 3.354016E-3;
double B_1 = 2.569850E-4;
double C_1 = 2.620131E-6;
double D_1 = 6.383091E-8;

//Variable PID
//setPoint = consigne
//Input = temp mesurer
//output = puissance retourné
double Setpoint, Input, Output = 1;
PID myPID(&Input, &Output, &Setpoint, 275, 5, 1, DIRECT);

void setup() {
  Serial.begin(9600);  
  pinMode(pinTempCannette,INPUT);
  pinMode(pinTempPeltier,INPUT);
  pinMode(pinCourantPeltier,OUTPUT);
  pinMode(pinDHC22,INPUT);
  pinMode(led,OUTPUT);
  
  myPID.SetMode(AUTOMATIC);
}

void loop() {

  DHC22(&humidity,&temperature);

  //peltier_level = map(power, 0, 99, 0, 255);

  //return;
   
  if (Serial.available()) {
      a = Serial.read();
    }
  
  getOrder(receiveTrames, a);
  
  delay(1000);
    
  switch(receiveTrames[1]){
    case 0:
      digitalWrite(led,LOW);
      analogWrite(pinCourantPeltier,0);
      returnOrderType = "OFF";
      //Send(returnOrderType);
      Serial.println("OFF:-1:-1:-1:-1:-1");
      
      break;
    case 1:
      digitalWrite(led,HIGH);
      //analogWrite(pinCourantPeltier,peltier_level);
      returnOrderType = "ON";      
      Send(returnOrderType, receiveTrames[0]);
      //power=99;
      break;
    case 2:
      digitalWrite(led,LOW);
      delay(500);
      digitalWrite(led,HIGH);
      returnOrderType = "StandBy";      
      Send(returnOrderType);      
      break;
    default:
      returnOrderType = "ErrorOrder:";
      Send(returnOrderType);
      break;
    }  
   
   SendInfo(humidity,temperature);
   Temp();
}

//fonction d'envoie
//fonction d'envoie ON
void Send(String data1, int data2) {
  Serial.print(data1);
  Serial.print(":");  
  Order(data2);
  
  //peltier_level = map(power, 0, 99, 0, 255);
}

//fonction d'envoie OFF/StandBy/ErrorOrder
void Send(String data1){
  if (data1 == "StandBy")StandBy();
    else Serial.print(data1);  
}

//Envoie des information relatif au frigo
void SendInfo(float humidity, float temperature){
  
  Serial.print(":");
  Serial.print(humidity,2);
  Serial.print(":");
  Serial.print(temperature,2); 
  
}

//mode
//mode Ordre/PID
void Order(int data2){
  Input = pinTempCannetteValue;
  Setpoint = data2;
  myPID.Compute();
  
  
  Serial.print(Output);Serial.print("");
  analogWrite(pinCourantPeltier,(int)Output);  
  //Serial.print(data2);  
}

//mode veille
void StandBy(){
  analogWrite(3, 33.0 / 100.0 * 255); 
  Serial.print("StandBy:-1");
  
}

//Temperature  
 void Temp(){
  
   pinTempCannetteValue = GetTemperature(pinTempCannette);
   pinTempPeltierValue = GetTemperature(pinTempPeltier);   
   
   Serial.print(":");
   Serial.print(pinTempCannetteValue,2);
   Serial.print(":");
   Serial.println(pinTempPeltierValue,2);   
   
}

double GetTemperature(int port)
{
  float reception;
  float tension;
  float resistance;
  reception = analogRead(port);
  tension = reception * PLAGE_TENSION / 1024;
  resistance = (Rref * tension) / (TENSION-tension);
  return SteinhartHart(resistance);
}

double SteinhartHart(double R)
{
  //Division de l'équation en 4 parties. La premiere est 
  //uniquement A1
  double equationB1 = B_1 * log(R/Rref);
  double equationC1 = C_1 * pow(log(R/Rref), 2);
  double equationD1 = D_1 * pow(log(R/Rref), 3);
  double equation = A_1 + equationB1 + equationC1 + equationD1;
  return pow(equation, -1) - 273.15;
}
 
//Analyse de la trame ressus
bool getOrder(int* trames, int trame)
{  
  int bitsRestant = NUMBER_OF_BITS;
  for(int i = 0; i< NUMBER_OF_TRAMES; i++)
  {
    bitsRestant -= TRAME_LENGTH[i];
    trames[i] = trame >> bitsRestant;
    trame -= trames[i] << bitsRestant;
  }
  return true;
}

//DHC Init
void DHC22(float* humidity, float* temperature){
  char option; 
 
  readDHT22(BROCHE_CAPTEUR, temperature, humidity);
  
  
  if(Serial.available() > 0)
  {
    //option = Serial.read();
    //if(option == 'a') power += 5;
      //else if(option == 'z') power -= 5;
    //if(power > 99) power = 99;
    //if(power < 0) power = 0;
    
    //peltier_level = map(power, 0, 99, 0, 255);
  }
  //analogWrite(peltier, peltier_level); //Write this new value out to the port
}
//DHC Setup
byte readDHT11(byte pin, float* temperature, float* humidity) {
  /* Lit le capteur */
  byte data[5];
  byte ret = readDHTxx(pin, data, 18, 1000);
  /* D�tecte et retourne les erreurs de communication */
  if (ret != DHT_SUCCESS)
    return ret;
  /* Calcul la vraie valeur de la temp�rature et de l'humidit� */
  *humidity = data[0];
  *temperature = data[2];
  /* Ok */
  return DHT_SUCCESS;
}

byte readDHT22(byte pin, float* temperature, float* humidity) {
  /* Lit le capteur */
  byte data[5];
  byte ret = readDHTxx(pin, data, 1, 1000);
  /* D�tecte et retourne les erreurs de communication */
  if (ret != DHT_SUCCESS)
    return ret;
  /* Calcul la vraie valeur de la temp�rature et de l'humidit� */
  float fh = data[0];
  fh *= 256;
  fh += data[1];
  fh *= 0.1;
  *humidity = fh;

  float ft = data[2] & 0x7f;
  ft *= 256;
  ft += data[3];
  ft *= 0.1;
  if (data[2] & 0x80) {
    ft *= -1;
  }
  *temperature = ft;
  /* Ok */
  return DHT_SUCCESS;
}

byte readDHTxx(byte pin, byte* data, unsigned long start_time, unsigned long timeout) {
  data[0] = data[1] = data[2] = data[3] = data[4] = 0;
  // start_time est en millisecondes
  // timeout est en microsecondes
  /* Conversion du num�ro de broche Arduino en ports / masque binaire "bas niveau" */
  uint8_t bit = digitalPinToBitMask(pin);
  uint8_t port = digitalPinToPort(pin);
  volatile uint8_t *ddr = portModeRegister(port);   // Registre MODE (INPUT / OUTPUT)
  volatile uint8_t *out = portOutputRegister(port); // Registre OUT (�criture)
  volatile uint8_t *in = portInputRegister(port);   // Registre IN (lecture)
  /* Conversion du temps de timeout en nombre de cycles processeur */
  unsigned long max_cycles = microsecondsToClockCycles(timeout);
  /* Evite les probl�mes de pull-up */
  *out |= bit;  // PULLUP
  *ddr &= ~bit; // INPUT
  delay(100);
  /* R�veil du capteur */
  *ddr |= bit;  // OUTPUT
  *out &= ~bit; // LOW
  delay(start_time); // Temps d'attente � LOW causant le r�veil du capteur
  // N.B. Il est impossible d'utilise delayMicroseconds() ici car un d�lai
  // de plus de 16 millisecondes ne donne pas un timing assez pr�cis.

  /* Portion de code critique - pas d'interruptions possibles */
  noInterrupts();

  /* Passage en �coute */
  *out |= bit;  // PULLUP
  delayMicroseconds(40);
  *ddr &= ~bit; // INPUT

          /* Attente de la r�ponse du capteur */
  timeout = 0;
  while (!(*in & bit)) { /* Attente d'un �tat LOW */
    if (++timeout == max_cycles) {
      interrupts();
      return DHT_TIMEOUT_ERROR;
    }
  }

  timeout = 0;
  while (*in & bit) { /* Attente d'un �tat HIGH */
    if (++timeout == max_cycles) {
      interrupts();
      return DHT_TIMEOUT_ERROR;
    }
  }

  /* Lecture des donn�es du capteur (40 bits) */
  for (byte i = 0; i < 40; ++i) {

    /* Attente d'un �tat LOW */
    unsigned long cycles_low = 0;
    while (!(*in & bit)) {
      if (++cycles_low == max_cycles) {
        interrupts();
        return DHT_TIMEOUT_ERROR;
      }
    }

    /* Attente d'un �tat HIGH */
    unsigned long cycles_high = 0;
    while (*in & bit) {
      if (++cycles_high == max_cycles) {
        interrupts();
        return DHT_TIMEOUT_ERROR;
      }
    }

    /* Si le temps haut est sup�rieur au temps bas c'est un "1", sinon c'est un "0" */
    data[i / 8] <<= 1;
    if (cycles_high > cycles_low) {
      data[i / 8] |= 1;
    }
  }

  /* Fin de la portion de code critique */
  interrupts();

  /*
  * Format des donn�es :
  * [1, 0] = humidit� en %
  * [3, 2] = temp�rature en degr�s Celsius
  * [4] = checksum (humidit� + temp�rature)
  */

  /* V�rifie la checksum */
  byte checksum = (data[0] + data[1] + data[2] + data[3]) & 0xff;
  if (data[4] != checksum)
    return DHT_CHECKSUM_ERROR; /* Erreur de checksum */
  else
    return DHT_SUCCESS; /* Pas d'erreur */
}
