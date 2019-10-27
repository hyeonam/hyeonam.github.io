class Bomb {

  float rotation = 10;
  float xPos, yPos;
  float velocityX, velocityY;
  long lastDrawMillis;
  boolean large;
  int bombScore;

  Bomb() {
    this(true, random(0, 400), random(-40, 00), 0, 5);
  }

  Bomb(boolean isLarge, float initialX, float initialY, long previousDrawTime, int bombval) {
    xPos = initialX;
    yPos = initialY;
    bombScore = bombval;
    rotation = random(0, TWO_PI);
    if (isLarge) {
      velocityX = sin(rotation)*100;
      velocityY = -cos(rotation)*100;
    }
    else {
      velocityX = sin(rotation)*140;
      velocityY = -cos(rotation)*140;
    }
    large = isLarge;
    lastDrawMillis = previousDrawTime;
  }

  void draw() {
    long currentMillis = millis();
    float timeSinceLastDraw = ((float)currentMillis - (float)lastDrawMillis)/1000;
    lastDrawMillis = currentMillis;
    noStroke();

    xPos = (xPos) + velocityX * timeSinceLastDraw;
    yPos = (yPos + 10) + velocityY * timeSinceLastDraw;
    
      if (captureBomb()){
      score -= bombScore;
      yPos -= 400;
      num -= 1;
    }

    if (xPos > 400) {
      xPos -= 400;
    } else if (xPos < 0) {
      xPos += 400;
    }
    if (yPos > 400) {
      yPos -= 400;
      num -= 1;
    } else if (yPos < 0) {
      yPos += 400;
    }

    pushMatrix();
    translate(xPos, yPos);
    rotate(rotation);
    if (!large)
    scale(0.5);

    fill(0);
    ellipse(0,0,40,40);
    ellipse(15,15,7,7);
    fill(255);
    textAlign(CENTER);
    textFont(f1, 25);
    text("B",8,8);
    popMatrix();
  }

  boolean captureBomb() {
    if ((xPos >= mouseX - 50 && xPos <= mouseX + 50) &&(yPos >= 310 && yPos <= 400))
    return true;
    else
    return false;
  }
}
