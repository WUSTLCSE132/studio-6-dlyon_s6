const int buttonPin = 2;
int count = 0;

unsigned long lastDebounceTime = 0;
unsigned long debounceDelay = 100;
int buttonState;
int lastButtonState = HIGH;

void buttonPressed() {
  int reading = digitalRead(buttonPin);
  
  if ((millis() - lastDebounceTime) > debounceDelay) {
    if (reading != buttonState) {
      buttonState = reading;
      if (buttonState == LOW) {
        count++;
        //Serial.println("Interrupt");
        Serial.print("button presses recorded: ");
        Serial.println(count);
      }
    }
  }
  
  
}

void setup() {
  pinMode(buttonPin, INPUT_PULLUP);

  // Interrupts can happen on "edges" of signals.  
  // Three edge types are supported: CHANGE, RISING, and FALLING 
  attachInterrupt(digitalPinToInterrupt(buttonPin), buttonPressed, CHANGE);
  Serial.begin(9600);
}

void loop() {
  for(int i=0;i<100;i++) {
    Serial.println(i);
    delay(1000);
  }
}
