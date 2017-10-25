#include "PID_v1.h"

// HEAD
int a = 0;

String returnOrder;
String returnOrderType;

const int NUMBER_OF_TRAMES = 2;
const int NUMBER_OF_BITS = 7;
const int TRAME_LENGTH[] = { 5, 2 };

double Setpoint, Input, Output;
PID myPID(&Input, &Output, &Setpoint, 2, 5, 1, DIRECT);

int TFidge[] = { 0, 0 };
void setup() {
  Serial.begin(9600);
  
  //Setpoint = 18;
  //myPID.SetMode(AUTOMATIC);
}

void loop() {
  if (Serial.available()) {
    a = Serial.read();
  }
  getOrder(TFidge, a);
  Serial.print("-1:ON:-1:");
  Serial.print(TFidge[0]);
  Serial.print(":");
  Serial.print(TFidge[1]);
  Serial.println(":-1:-1");
  delay(1000);
/*int receiveTrames[2];
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
      Send(returnOrderType, receiveTrames[1],receiveTrames[0]);
    }
  delay(1000);*/
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
  //Serial.print(trame);
  int bitsRestant = NUMBER_OF_BITS;
  for(int i = 0; i< NUMBER_OF_TRAMES; i++)
  {
    bitsRestant -= TRAME_LENGTH[i];
    trames[i] = trame >> bitsRestant;
    trame -= trames[i] << bitsRestant;
  }
  return true;
}


//mode
//mode Ordre
void Order(int data3){
  Serial.println(data3);
  return;
}

//mode veille
void StandBy(){
  return;
}

void funcPID()
{
  Input = analogRead(1);
  Input = (5*Input*100/1024);
  myPID.Compute();
  analogWrite(3,Output);
  Serial.print(Input); Serial.println(Output);
}

