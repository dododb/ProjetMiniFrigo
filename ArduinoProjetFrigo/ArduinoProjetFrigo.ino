#include "PID_v1.h"

<<<<<<< HEAD
int a = 0;

String returnOrder;
String returnOrderType;

const int NUMBER_OF_TRAME = 2;
const int TRAME_INDEX[] = { 6, 0 };

double Setpoint, Input, Output;
PID myPID(&Input, &Output, &Setpoint, 2, 5, 1, DIRECT);

void setup() {
  Serial.begin(9600);
  
  //Setpoint = 18;
  //myPID.SetMode(AUTOMATIC);
}

void loop() {
int receiveTrames[2];
  if (Serial.available()) {
    a = Serial.read();
  }
  getOrder(receiveTrames, a);
  //Serial.println(a);
  delay(1000);

  if(receiveTrames[0]==0){
    returnOrderType = "OFF";
    Send(returnOrderType);    
  }
    else {
      returnOrderType = "ON";
      Send(returnOrderType, receiveTrames[1],receiveTrames);
    }
  delay(1000);
}
//fonction d'envoie
//fonction d'envoie ON
void Send(String data1, int data2, int data3) {
  Serial.print(data1);
  switch(data2){
    case 1: 
      Serial.print(":Veille:");
      StandBy();
      break;
      
    case 2: 
      Serial.print(":Consigne:");
      Order(data3);
      break;
      
    case 3: 
      Serial.print(":Autre:");
      break;
      
    default:
      Serial.print(":Chiffre:");
      Serial.println(data2);
      break;
  }

}
//fonction d'envoie OFF
void Send(String data1){
  Serial.println(data1);
}

//Analyse de la trame ressus
bool getOrder(int* trames, int trame)
{
    int ordreType = trame >> TRAME_INDEX[0];
    trame -= ordreType << TRAME_INDEX[0];
  
    int ordre = trame >> TRAME_INDEX[2];
  
    trames[0] = ordreType;
    trames[1] = ordre;
    return true;
}


//mode
//mode Ordre
void Order(int data3){
  Serial.print(data3);
  return;
}

//mode veille
void StandBy(){
  return;
}


// Fonction qui calcule le PID
=======
int pin = 13;
int ledPin = 6;
int a = 0;

const int NUMBER_OF_TRAME = 2;
const int TRAME_INDEX[] = { 6, 0 };

double Setpoint, Input, Output;
PID myPID(&Input, &Output, &Setpoint, 2, 5, 1, DIRECT);

void setup() {
  Serial.begin(9600);
  pinMode(pin, INPUT);
  //Setpoint = 18;
  //myPID.SetMode(AUTOMATIC);
  delay(10000);
}

void loop() {
int receiveTrames[2];
  if (Serial.available()) {
    a = Serial.read();
  }
  getOrder(receiveTrames, a);
  //Serial.println(a);
  delay(1000);

  Send(receiveTrames[0], receiveTrames[1], a);

  delay(1000);
}

void Send(int data1, int data2, int data3) {
  Serial.print(data1);
  Serial.print(": caca");
  Serial.print(data2);
  Serial.print(":");
  Serial.println(data3);
}

bool getOrder(int* trames, int trame)
{
    int ordreType = trame >> TRAME_INDEX[0];
    trame -= ordreType << TRAME_INDEX[0];
  
    int ordre = trame >> TRAME_INDEX[2];
  
    trames[0] = ordreType;
    trames[1] = ordre;
    return true;
}

>>>>>>> branch 'master' of https://github.com/dododb/ProjetMiniFrigo
void funcPID()
{
  Input = analogRead(1);
  Input = (5*Input*100/1024);
  myPID.Compute();
  analogWrite(3,Output);
  Serial.print(Input); Serial.println(Output);
}

