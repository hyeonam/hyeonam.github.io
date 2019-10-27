/*
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

void setup() {

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

void draw() {
 
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

void mousePressed(){
  if(mouseX>=160&&mouseX<=240&&mouseY>=180&&mouseY<=220)
  count=1;
  else
  count=1;
}
