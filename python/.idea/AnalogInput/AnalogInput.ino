/*
  Analog Input

  Demonstrates analog input by reading an analog sensor on analog pin 0 and
  turning on and off a light emitting diode(LED) connected to digital pin 13.
  The amount of time the LED will be on and off depends on the value obtained
  by analogRead().

  The circuit:
  - potentiometer
    center pin of the potentiometer to the analog input 0
    one side pin (either one) to ground
    the other side pin to +5V
  - LED
    anode (long leg) attached to digital output 13
    cathode (short leg) attached to ground

  - Note: because most Arduinos have a built-in LED attached to pin 13 on the
    board, the LED is optional.

  created by David Cuartielles
  modified 30 Aug 2011
  By Tom Igoe

  This example code is in the public domain.

  http://www.arduino.cc/en/Tutorial/AnalogInput
*/

int xPin = A0;    // select the input pin for the potentiometer
int yPin = A1;      // select the pin for the LED
int x = 0;
int y = 0;
bool b;
 
void setup() {
  pinMode(xPin, INPUT); 
  pinMode(yPin, INPUT);
  Serial.begin(9600); //115200
   b = true;

}

void loop() {
 
    if(Serial.available() > 0 or b == true)
    {
         Serial.println(Serial.read());
         x = analogRead(xPin);
         y = analogRead(yPin); 
         String str  = String(x) + " " + String(y);
         Serial.println(str);   
         b = false;  
    }

}
