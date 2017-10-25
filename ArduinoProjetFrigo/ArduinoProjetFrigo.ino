#include "PID_v1.h"

int a = 0;

String returnOrder;
String returnOrderType;

const int NUMBER_OF_TRAMES = 2;
const int NUMBER_OF_BITS = 7;
const int TRAME_LENGTH[] = { 5, 2 };

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
    delay(1000);



  switch(receiveTrames[1]){
    case 0:
      returnOrderType = "OFF";
      Send(returnOrderType);
      break;
    case 1:
      returnOrderType = "ON";      
      Send(returnOrderType, receiveTrames[0]);
      break;
    case 2:
      returnOrderType = "StandBy";
      Send(returnOrderType);
      break;
    default:
      returnOrderType = "ErrorOrder";
      Send(returnOrderType);
      break;
  }  
  delay(1000);  
}

//fonction d'envoie
//fonction d'envoie ON
void Send(String data1, int data2) {
  Serial.print(data1);
  Serial.print(":Consigne:");  
  Order(data2);  

}
//fonction d'envoie OFF/StandBy/ErrorOrder
void Send(String data1){
  if (data1 == "StandBy")StandBy();
    else Serial.println(data1);
  
  
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
void Order(int data2){
  Serial.println(data2);  
}

//mode veille
void StandBy(){
  Serial.print("ModeVielle"); 
}


// Fonction qui calcule le PID
void funcPID()
{
  Input = analogRead(1);
  Input = (5*Input*100/1024);
  myPID.Compute();
  analogWrite(3,Output);
  Serial.print(Input); Serial.println(Output);
}

