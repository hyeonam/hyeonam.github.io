import processing.core.*; import java.applet.*; import java.awt.*; import java.awt.image.*; import java.awt.event.*; import java.io.*; import java.net.*; import java.text.*; import java.util.*; import java.util.zip.*; import javax.sound.midi.*; import javax.sound.midi.spi.*; import javax.sound.sampled.*; import javax.sound.sampled.spi.*; import java.util.regex.*; import javax.xml.parsers.*; import javax.xml.transform.*; import javax.xml.transform.dom.*; import javax.xml.transform.sax.*; import javax.xml.transform.stream.*; import org.xml.sax.*; import org.xml.sax.ext.*; import org.xml.sax.helpers.*; public class chr extends PApplet {/*
Catch gifts and aviod bombs.
And keep Santa alive to have beautiful Christmas...

If you have lots of gifts, you can see different messages below the score.
If you have a bomb once, Santa is died.
Then no more Santa this Christmas..
Move you mouse to move the santa left and right.
Please do not kill him.
*/

PImage backImg;
PImage santaImg;
PImage santa2Img;

PFont f1, f2, f3;

int count=0;
int num = 100;
int score = 0;
Santa santa;
ArrayList gifts;
ArrayList bombs;
final int numGifts = 5;
final int numBombs = 2;

public void setup() {

  size(400, 400);
  frameRate(20);

  backImg = loadImage("back.jpg");
  santaImg = loadImage("santa1.png");
  santa2Img = loadImage("DeadSanta.png");
  
  f1=loadFont("Arial-Black-48.vlw");
  f2=loadFont("ACaslonPro-Italic-48.vlw");
  f3=loadFont("FootlightMTLight-48.vlw");

  santa = new Santa();

  gifts = new ArrayList();
  for (int i = 0; i<numGifts; i++) {
    gifts.add(new Gift());
  }
   
  bombs = new ArrayList();
  for (int i = 0; i<numBombs; i++) {
    bombs.add(new Bomb());
  }
}

public void draw() {
 
//First Stage
  if(count==0){
  background(255, 0, 0);
  fill(0,169,76);
  rect(160,180,80,40);
  fill(0);
  textAlign(CENTER);
  textFont(f1, 20);
  text("START",200,205);
  textFont(f2, 25);
  text("Catch Gifts and Avoid Bombs!",200,250);
  smooth();
}

//Main Game
  if(count==1){
  image(backImg, 0, 0);
  fill(0);
  textFont(f1, 20);
  text("Score : "+score, 90, 50);

  if (num > 0) {
    if (score < 0) {
      count=2;              
       }
   if (score <= 10 && score >=0) {
      textAlign(CENTER);
      fill(0);
      textFont(f2, 20);
      text("Too slow, Santa", 90, 70);
       }
   if (score <= 20 && score > 10) {
      textAlign(CENTER);
      fill(0);
      textFont(f2, 20);
      text("good good", 90,70);
       }
   if (score <= 30 && score > 20) {
      textAlign(CENTER);
      fill(0);
      textFont(f2, 20);
      text("more more", 90,70);
       }
    if (score <= 50 && score > 30) {
      textAlign(CENTER);
      fill(0);
      textFont(f2, 20);
      text("Yahoo~~~", 90, 70);
       }
     }

  for(int i = 0; i < gifts.size(); i++) {
  if( num > 0) {
    Gift g = (Gift)gifts.get(i);
    g.draw();
    }
  }  
  for(int j = 0; j < bombs.size(); j++) {
  if( num > 0) {
    Bomb b = (Bomb)bombs.get(j);
    b.draw();
    }
  } 
  santa.draw();
 }

//Game Over Stag  
  if(count==2){
  background(255, 0, 0);
  fill(0,169,76);
  rect(120,180,160,40);
  fill(0);
  textAlign(CENTER);
  textFont(f1, 20);
  text("GAMEOVER",200,205);
  textFont(f2, 25);
  text("Sorry, No Santa this Christmas...",200,250);
  image(santa2Img, 50, 270);  
 }
}

public void mousePressed(){
  if(mouseX>=160&&mouseX<=240&&mouseY>=180&&mouseY<=220)
  count=1;
  else
  count=1;
}

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

  public void draw() {
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
    scale(0.5f);

    fill(0);
    ellipse(0,0,40,40);
    ellipse(15,15,7,7);
    fill(255);
    textAlign(CENTER);
    textFont(f1, 25);
    text("B",8,8);
    popMatrix();
  }

  public boolean captureBomb() {
    if ((xPos >= mouseX - 50 && xPos <= mouseX + 50) &&(yPos >= 310 && yPos <= 400))
    return true;
    else
    return false;
  }
}

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

  public void draw() {
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
    scale(0.5f);

    fill(255,0,0);
    rect(0,0,40,40);
    fill(255,255,255, 80);
    rect(0,15,40,10);
    rect(15,0,10,40);
    popMatrix();
  }

  public boolean captureGift() {
    if ((xPos >= mouseX - 45 && xPos <= mouseX + 45) &&(yPos >= 310 && yPos <= 400))
    return true;
    else
    return false;
  }
}

class Santa {

  public void draw() {
  image(santaImg, mouseX, 310);  
  }
}

  static public void main(String args[]) {     PApplet.main(new String[] { "chr" });  }}