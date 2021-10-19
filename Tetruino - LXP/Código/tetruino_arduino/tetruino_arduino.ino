// -------------- JOYSTICK ------------------------
int inX = A0; // EJE X - CONECTADO AL PIN ANALOGICO
int inY = A1; // EJE Y - CONECTADO AL PIN ANALOGICO
int xValue = 0; // ALMACENA VALOR DE X
int yValue = 0; // ALMACENA VALOR DE Y

// -------------- OTROS BOTONES -------------------
int vb = 0; // ALMACENA EL RESULTADO DE LOS BOTONES
int buttons[] = {7, 8}; // GUARDA EL NUMERO DEL PIN DIGITAL
int numberB = 0; // ALMACENA EL TAMAÑO DEL ARRAY DE BOTONES
char nButtons[]= {'S','P'}; // ALMACENA EL MENSAJE QUE MANDARÁ AL TETRIS

void setup() {
  Serial.begin(9600);
// -------------- JOYSTICK ------------------------
  pinMode(inX, INPUT); // PONE X EN MODO INPUT
  pinMode(inY, INPUT); // PONE Y EN MODO INPUT
  
// -------------- OTROS BOTONES -------------------
  numberB = sizeof(buttons) / sizeof(int); // LEE EL TAMAÑO DEL ARRAY DE BOTONES
    for(int i=0;i<numberB;i++){
     pinMode(buttons[i], INPUT);  // RECORRE LOS BOTONES Y LOS PONE EN MODO INPUT
    }
}

void loop() {
// -------------- JOYSTICK ------------------------
  xValue = analogRead(inX); // LEE VALOR DE X
  yValue = analogRead(inY); // LEE VALOR DE Y

// VALORES EN POSICIÓN NORMAL X: 489, Y: 506 MÁS O MENOS
// RANGO DE VALORES ENTRE 0 Y 1023
  if(xValue>500){
    Serial.println("R"); // Se mueve a la derecha  
    delay(300);
  }
  if(xValue<480){
    Serial.println("L"); // Se mueve a la izquierda
    delay(300);
  }
  if(yValue<500){
    Serial.println("U"); // Se mueve hacia arriba
    delay(300);
  }
  if(yValue>520){
    Serial.println("D"); // Se mueve hacia abajo
    delay(300);
  }

  
// -------------- OTROS BOTONES -------------------
  for(int i=0;i<numberB;i++){
     vb = digitalRead(buttons[i]); // RECORRE Y LEE LOS BOTONES
     if (vb == HIGH){
        Serial.println(nButtons[i]); // SI ALGUNO DE ELLOS ES PULSADO, MANDA EL MENSAJE CORRESPONDIENTE
        delay(1500);
     }
  } 

  delay(300);
}
