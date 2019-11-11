 package com.TD.main.graphics;
 import java.awt.Color;
 import java.awt.Graphics;
 import java.awt.image.BufferedImage;
 import java.util.ArrayList;
 import java.util.List;

import com.TD.main.BufferImageLoader;
import com.TD.main.Enemy;
import com.TD.main.GameOverScreen;
import com.TD.main.GameWon;
import com.TD.main.HUD;
import com.TD.main.PausedScreen;
import com.TD.main.Projectile;
import com.TD.main.StartMenu;
import com.TD.main.TempTowerAtMouseLoc;
import com.TD.main.Tower;
 
 public class MainRender {
   private BufferedImage grass;
   private BufferedImage dirt;
   private BufferedImage waterN;
   private BufferedImage waterNE;
   private BufferedImage waterE;
   private BufferedImage waterSE;
   private BufferedImage waterS;
   private BufferedImage waterSW;
   private BufferedImage waterW;
   
   public MainRender(HUD hud, TempTowerAtMouseLoc tempTowerAtMouseLoc) {
      this.enemies = new ArrayList<>();
      this.projectiles = new ArrayList<>();
      this.enemiesTemp = new ArrayList<>();
      this.towers = new ArrayList<>();
      this.pausedScreen = new PausedScreen();
      this.paused = false; 
      this.towerClicked = false; 
      this.startMenuBoolean = true; 
      this.gameOver = false; 
      this.gameWon = false;
      this.typeOfTower = 1;
      this.startMenu = new StartMenu();
      this.gameOverScreen = new GameOverScreen();
      this.gameWonClass = new GameWon();  
      
      
      this.map = new int[][]{
	{10,10,10, 4, 1, 8,10, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	{10,10,10, 4, 1, 8,10, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
	{6 , 6, 6, 5, 1, 8,10, 4, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
	{1 , 1, 1, 1, 1, 7,10, 4, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0}, 
	{2 , 2, 3, 0, 1, 0, 8, 4, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0}, 
	{10,10, 4, 0, 1, 0, 8, 4, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0}, 
	{10,10, 4, 0, 1, 0, 8, 4, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0}, 
	{10, 6, 5, 0, 1, 0, 7, 5, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0}, 
	{ 4, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0}, 
	{ 5, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0,12,13,14, 0, 0, 1, 0, 0}, 
	{ 0, 1, 0, 9, 2, 3, 1, 0, 0, 0, 1, 1,15,16,17, 0, 0, 1, 0, 0},
	{ 0, 1, 0, 7, 6, 5, 1, 0, 9, 3, 1, 0,18,19,20, 0, 0, 1, 0, 0}, 
	{ 0, 1, 0, 0, 0, 0, 1, 0, 8, 4, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0}, 
	{ 0, 1, 1, 1, 1, 1, 1, 0, 8, 4, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
	{ 0, 0, 0, 0, 0, 0, 0, 0, 7, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
	};
   
      this.tempTowerAtMouseLoc = tempTowerAtMouseLoc;
      this.hud = hud;
      BufferImageLoader loader = new BufferImageLoader("spritesheet.png");
      this.grass = loader.grabSprite(1, 1);
      this.dirt = loader.grabSprite(2, 1);
      this.waterN = loader.grabSprite(2, 3);
      this.waterNE = loader.grabSprite(3, 3);
      this.waterE = loader.grabSprite(3, 4);
      this.waterSE = loader.grabSprite(3, 5);
      this.waterS = loader.grabSprite(2, 5);
      this.waterSW = loader.grabSprite(1, 5);
      this.waterW = loader.grabSprite(1, 4);
      this.waterNW = loader.grabSprite(1, 3);
      this.waterMid = loader.grabSprite(2, 4);
      this.waterLeft = loader.grabSprite(6, 1);
 
     
      this.castleNW = loader.grabSprite(4, 3);
      this.castleN = loader.grabSprite(5, 3);
      this.castleNE = loader.grabSprite(6, 3);
     
      this.castleW = loader.grabSprite(4, 4);
      this.castleM = loader.grabSprite(5, 4);
      this.castleE = loader.grabSprite(6, 4);
     
      this.castleSW = loader.grabSprite(4, 5);
      this.castleS = loader.grabSprite(5, 5);
      this.castleSE = loader.grabSprite(6, 5);
   }
   private BufferedImage waterNW; 
   private BufferedImage waterMid; 
   private BufferedImage waterLeft; 
   private BufferedImage castleNW; 
   private BufferedImage castleN; 
   private BufferedImage castleNE; 
   private BufferedImage castleW; 
   private BufferedImage castleM; 
   private BufferedImage castleE; 
   private BufferedImage castleSW; 
   private BufferedImage castleS; 
   private BufferedImage castleSE; 
   private List<Enemy> enemies; 
   private List<Projectile> projectiles; 
   private List<Enemy> enemiesTemp; 
   private List<Tower> towers; 
   private HUD hud; 
   private PausedScreen pausedScreen;
   private boolean paused;
   
   public void render(Graphics g) {
      if (this.startMenuBoolean) {
        this.startMenu.draw(g);
     }
      else if (!this.startMenuBoolean && !this.gameOver && !this.gameWon) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 640, 576);
       
        for (int y = 0; y < this.map.length; y++) {
          for (int x = 0; x < (this.map[y]).length; x++) {
            if (this.map[y][x] == 0) {
              g.drawImage(this.grass, x * 32, y * 32, null);
            } else if (this.map[y][x] == 1) {
              g.drawImage(this.dirt, x * 32, y * 32, null);
            } else if (this.map[y][x] == 2) {
              g.drawImage(this.waterN, x * 32, y * 32, null);
            } else if (this.map[y][x] == 3) {
              g.drawImage(this.waterNE, x * 32, y * 32, null);
            } else if (this.map[y][x] == 4) {
              g.drawImage(this.waterE, x * 32, y * 32, null);
            } else if (this.map[y][x] == 5) {
              g.drawImage(this.waterSE, x * 32, y * 32, null);
            } else if (this.map[y][x] == 6) {
              g.drawImage(this.waterS, x * 32, y * 32, null);
            } else if (this.map[y][x] == 7) {
              g.drawImage(this.waterSW, x * 32, y * 32, null);
            } else if (this.map[y][x] == 8) {
              g.drawImage(this.waterW, x * 32, y * 32, null);
            } else if (this.map[y][x] == 9) {
              g.drawImage(this.waterNW, x * 32, y * 32, null);
            } else if (this.map[y][x] == 10) {
              g.drawImage(this.waterMid, x * 32, y * 32, null);
            } else if (this.map[y][x] == 11) {
              g.drawImage(this.waterLeft, x * 32, y * 32, null);
           }
            else if (this.map[y][x] == 12) {
              g.drawImage(this.castleNW, x * 32, y * 32, null);
            } else if (this.map[y][x] == 13) {
              g.drawImage(this.castleN, x * 32, y * 32, null);
            } else if (this.map[y][x] == 14) {
              g.drawImage(this.castleNE, x * 32, y * 32, null);
           }
            else if (this.map[y][x] == 15) {
              g.drawImage(this.castleW, x * 32, y * 32, null);
            } else if (this.map[y][x] == 16) {
              g.drawImage(this.castleM, x * 32, y * 32, null);
            } else if (this.map[y][x] == 17) {
              g.drawImage(this.castleE, x * 32, y * 32, null);
           }
            else if (this.map[y][x] == 18) {
              g.drawImage(this.castleSW, x * 32, y * 32, null);
            } else if (this.map[y][x] == 19) {
              g.drawImage(this.castleS, x * 32, y * 32, null);
            } else if (this.map[y][x] == 20) {
              g.drawImage(this.castleSE, x * 32, y * 32, null);
           } 
         } 
        }  this.hud.draw(g);
 
 
 
 
       
        for (int i = 0; i < this.towers.size(); i++) {
          ((Tower)this.towers.get(i)).draw(g);
       }
        for (Enemy e : this.enemies) {
          if (e.getAlive()) {
            e.draw(g);
         }
       } 
 
       
        if (this.towerClicked) {
          this.tempTowerAtMouseLoc.setTypeOfTower(this.typeOfTower);
          this.tempTowerAtMouseLoc.draw(g);
       } 
       
        if (this.paused) {
          this.pausedScreen.draw(g);
       }
      } else if (this.gameOver) {
        this.gameOverScreen.draw(g);
     } 
 
     
      if (this.gameWon && !this.gameOver)
        this.gameWonClass.draw(g); 
   }
   private boolean towerClicked; private boolean startMenuBoolean; private boolean gameOver; private boolean gameWon;
   private TempTowerAtMouseLoc tempTowerAtMouseLoc;
   private int typeOfTower;
   private StartMenu startMenu;
   private GameOverScreen gameOverScreen;
   private GameWon gameWonClass;
   private int[][] map;
   
   public void changePaused() {
      this.hud.changePaused();
     
      if (this.paused) {
        this.paused = false;
     } else {
       
        this.paused = true;
     } 
   }
 
   
   public void receiveEnemies(List<Enemy> list) {
      for (int i = 0; i < list.size(); i++) {
        this.enemiesTemp.add(list.get(i));
     }
   }
   
    public void addEnemy(int i) { this.enemies.add(this.enemiesTemp.get(i)); }
 
 
   
    public int[][] getMap() { return this.map; }
 
 
   
    public void receiveTower(Tower tower) { this.towers.add(tower); }
 
 
   
    public HUD getHud() { return this.hud; }
 
 
   
    public int getSizeOfWave() { return this.hud.getSizeOfWave(); }
 
 
   
    public void setTotalOfWaves(int total) { this.hud.setTotalNrOfWaves(total); }
 
 
   
    public void increaseWaveNrByOne() { this.hud.increaseWaveNrByOne(); }
 
 
 
   
    public void receiveArrows(Projectile projectile) { this.projectiles.add(projectile); }
 
   
   public void changeTowerClicked(int typeOfTower) {
      this.typeOfTower = typeOfTower;
      if (!this.towerClicked) {
        this.towerClicked = true;
     } else {
        this.towerClicked = false;
     } 
   }
 
 
   
    public boolean getTowerClicked() { return this.towerClicked; }
 
 
   
    public int getTypeOfTower() { return this.typeOfTower; }
 
 
   
    public List<Tower> getTowerList() { return this.towers; }
 
 
   
    public void setStartMenu(boolean startMenuBoolean) { this.startMenuBoolean = startMenuBoolean; }
 
 
   
    public void setGameOver(boolean gameOver) { this.gameOver = gameOver; }
 
 
   
    public List<Enemy> getEnemies() { return this.enemies; }
 
 
   
    public void setEnemies(List<Enemy> enemies) { this.enemies = enemies; }
 
   
    public List<Enemy> getEnemiesTemp() { return this.enemiesTemp; }
 
 
   
    public void setGameWon(boolean b) { this.gameWon = b; }
 }
