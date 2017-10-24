int pin = 13;
int ledPin = 6;
int a = 0;

int NUMBER_OF_TRAME = 2;
int TRAME_INDEX[] = { 15, 0 };

void setup() {
Serial.begin(9600);
pinMode(pin,INPUT);
}

void loop() {
  
  int data = digitalRead(pin);
  int order;
  
  if(Serial.available()){
    order = Serial.read(); 
    a = order;
  }

  analogWrite(ledPin, a);
  delay(1000);
}

void Send(int data1){  
  Serial.println(data1);
}

int[] OrdreToSend(int trame)
{
  int trames[2];
  int ordreType = trame>>TRAME_INDEX[0];
  trame -= ordreType;

  int ordre = trame>>TRAME_INDEX[2];
  
  trames[0] = ordreType;
  trame[1] = ordre;
  return trames;
}
  
int getDecalage(int index)
{
  int decal = 0;
  for(int i = index; i<tramesIndex.length;i++)
  {
    decal += tramesIndex[i];
  }
  return decal;
}
