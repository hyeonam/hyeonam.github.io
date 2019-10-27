class Gift {

  float rotation = 0;
  float xPos, yPos;
  float velocityX, velocityY;
  long lastDrawMillis;
  boolean large;
  int giftScore;

  Gift() {
    this(true, random(0, 400), random(-40, 0), 0, 1);
  }

  Gift(boolean isLarge, float initialX, float initialY, long previousDrawTime, int valgift) {
    xPos = initialX;
    yPos = initialY;
    giftScore = valgift;
    rotation = random(0, PI);
    if (isLarge) {
      velocityX = -sin(rotation)*10;
      velocityY = cos(rotation)*10;
    }
    else {
      velocityX = -sin(rotation)*20;
      velocityY = cos(rotation)*25;
    }
    large = isLarge;
    lastDrawMillis = previousDrawTime;
  }

  void draw() {
    long currentMillis = millis();
    float timeSinceLastDraw = ((float)currentMillis - (float)lastDrawMillis)/1000;
    lastDrawMillis = currentMillis;
    noStroke();

    xPos = (xPos - 2) + velocityX * timeSinceLastDraw;
    yPos = (yPos + 9) + velocityY * timeSinceLastDraw;
    
      if (captureGift()){
      score += giftScore;
      println (score);
      yPos -= 310;
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

    fill(255,0,0);
    rect(0,0,40,40);
    fill(255,255,255, 80);
    rect(0,15,40,10);
    rect(15,0,10,40);
    popMatrix();
  }

  boolean captureGift() {
    if ((xPos >= mouseX - 45 && xPos <= mouseX + 45) &&(yPos >= 310 && yPos <= 400))
    return true;
    else
    return false;
  }
}
