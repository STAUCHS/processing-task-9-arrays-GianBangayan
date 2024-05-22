  import processing.core.PApplet;
  import java.util.*;

  public class Sketch extends PApplet {

    float[] snowX = new float[42];
    float[] snowY = new float[42];  
    boolean[] snowVis = new boolean[42];
    int snowDiam = 10;
    int playerX = 200;
    int playerY = 300;
    int playerLives = 3;
    boolean alive = true;
    boolean mouseClick = false;

    public void settings() {
      size(400, 400);
    }

    public void setup() {
      genRand();
      for(int i = 0; i < snowVis.length; i++){
        snowVis[i] = true;
        System.out.print(snowVis[i]);
      }
    }

    public void draw() {
      if(alive == false){
        background(255);
      }
      else{
        background(0);
        snow();
        player();
        lives();
      }
    }

    public void genRand(){
      for (int i=0; i< snowX.length;i++){
        snowX[i] = random(width);
        snowY[i] = random(height) - 400;
      }
    }
      
    public void snow(){
      fill(255);
      double speed = 1;
      for (int i=0; i< snowX.length;i++){
        if(snowVis[i]){
          ellipse(snowX[i],snowY[i], snowDiam, snowDiam);
          if(keyPressed){
            if(keyCode == UP){
              speed = 0.5;
            }
            else if(keyCode == DOWN){
              speed = 2;
            }
          }
          
          snowY[i] += 1*speed;
          if(snowY[i] > height){
            snowY[i] = -20;
          }
          //check for player/snow collisions
           if(dist(playerX, playerY, snowX[i], snowY[i]) < 15+snowDiam/2){
            playerLives--;
            snowVis[i] = false;
            snowY[i] = -20;
            snowVis[i] = true;
          }
          //check for cursor/snow collisions
          if(dist(mouseX, mouseY, snowX[i], snowY[i]) < 15+snowDiam/2 && mouseClick){
            snowVis[i] = false;
            snowY[i] = -20;
            snowVis[i] = true;
            mouseClick = false;
          }
        }
      }
    }

    public void mouseClicked(){
      mouseClick = true;
    }

    public void player(){
      fill(0,0,255);
      ellipse(playerX, playerY, 30, 30);
      if (keyPressed) {
        if (key == 'w') {
          playerY -= 1;
        }
        else if (key == 's') {
          playerY += 1;
        }
        else if(key == 'a'){
          playerX -= 1;
        }
        else if(key == 'd'){
          playerX += 1;
        }
      }
      if(playerX < 0-15){
        playerX = width;
      }
      if(playerX > width+15){
        playerX = 0;
      }
      if(playerY < 0-15){
        playerY = height;
      }
      if(playerY > height+15){
        playerY = 0;
      }
    }

    public void lives(){
      fill(255,0,0);
      if(playerLives == 3){
        rect(10, 10, 20, 20);
        rect(35, 10, 20, 20);
        rect(60, 10, 20, 20);
      }
      else if(playerLives == 2){
        rect(35, 10, 20, 20);
        rect(60, 10, 20, 20);
      }
      else if(playerLives == 1){
        rect(60, 10, 20, 20);
      }
      else if(playerLives ==0){
        alive = false;
      }
    }
  }