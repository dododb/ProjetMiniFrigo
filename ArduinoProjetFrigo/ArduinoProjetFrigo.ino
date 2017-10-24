#include "PID_v1.h"

int pin = 13;
int ledPin = 6;
int a = 0;

const int NUMBER_OF_TRAME = 2;
const int TRAME_INDEX[] = { 6, 0 };
int receiveTrames[2];

double Setpoint, Input, Output;
PID myPID(&Input, &Output, &Setpoint, 2, 5, 1, DIRECT);

void setup() {
  Serial.begin(9600);
  pinMode(pin, INPUT);
  Setpoint = 18;
  myPID.SetMode(AUTOMATIC);
}

void loop() {

  
  getOrder(receiveTrames);

  delay(1000);

  Send(receiveTrames[0], receiveTrames[1], a);

  delay(1000);
}

void Send(int data1, int data2, int data3) {
  Serial.print(data1);
  Serial.print(":");
  Serial.print(data2);
  Serial.print(":");
  Serial.println(data3);
}

bool getOrder(int* trames)
{
   if (Serial.available()) {
    int trame = digitalRead(pin);
    int ordreType = trame >> TRAME_INDEX[0];
    trame -= ordreType << TRAME_INDEX[0];
  
    int ordre = trame >> TRAME_INDEX[2];
  
    trames[0] = ordreType;
    trames[1] = ordre;
    return true;
   }
   return false;
}

void funcPID()
{
  Input = analogRead(1);
  Input = (5*Input*100/1024);
  myPID.Compute();
  analogWrite(3,Output);
  Serial.print(Input); Serial.println(Output);
}

