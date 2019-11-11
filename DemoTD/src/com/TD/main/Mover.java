 package com.TD.main;


import java.util.ArrayList;
import java.util.List;

import com.TD.main.graphics.MainRender;

public class Mover { 
			private List<Enemy> enemies; 
			private List<Enemy> enemiesTemp;
   private List<Tower> towers;
   private List<Projectile> projectiles;
   private int[][] map;
   private MainRender render;
   private boolean allSpawned;
   private long timer;
   
   public Mover(Game game, HUD hud) {
      this.enemies = new ArrayList<>();
      this.enemiesTemp = new ArrayList<>();
      this.towers = new ArrayList<>();
      this.projectiles = new ArrayList<>();
      this.map = new int[15][20];
     
      this.allSpawned = false;
     
      this.JagVetInte = 0;
     
      this.paused = false; this.startMenu = true; this.gameOver = false; this.gameWon = false;
      this.delay = 0L;

      this.game = game;
      this.hud = hud;
   }
   private int JagVetInte; private Game game; private boolean paused; private boolean startMenu; private boolean gameOver; private boolean gameWon; private long delay; private HUD hud;
   
   public void move() {
      if (!this.paused && !this.startMenu && !this.gameWon) {
        moveIt();
     }
   }
 
 
 
   
   public void moveIt() {
      for (int i = 0; i < this.towers.size(); i++) {
        ((Tower)this.towers.get(i)).update();
     }
    
      if (this.enemiesTemp.size() > 0 && !this.allSpawned) {
       
        if (System.currentTimeMillis() - this.timer > 1000L) {
          this.timer += 1000L;
          spawn();
       } 
       
        if (this.enemies.size() == this.enemiesTemp.size()) {
          this.allSpawned = true;
          System.out.println("all spawned (moverClass)");
          this.timer = System.currentTimeMillis();
       } 
     } else {
        this.timer = System.currentTimeMillis();
     }    
      for (Enemy e : this.enemies) {
       
        if (e.getAlive()) {
         
          int x = e.getX();
          int y = e.getY();
          int curDir = e.getCurDir();
          int thisTile = thisTile(x, y);
          int nextTile = nextTile(x, y, curDir);
 
 
 
         
          if (curDir == -1 || curDir == 5) {
            if (curDir == -1) {
             
              e.setAliveToFalse();
              this.hud.reduceLife();
              if (this.hud.getLife() <= 0) {
                this.gameOver = true;
                this.game.gameOver();
             }
           
           }
            else if (curDir == 5) {
              System.out.println("Enemy were Removed. I were spawned but found no way to go.");
              e.setAliveToFalse();
           } 
         }
 
 
         
          if (curDir == 0) {
            int newDir = findStartDirection(x, y);
            e.setCurDir(newDir);
            curDir = newDir;
         }
          else if (this.enemies.size() > 0 && thisTile != nextTile) {
           
            int newDir = findNewDir(curDir, x, y);
            e.setCurDir(newDir);
            curDir = newDir;
         } 
          if (!e.isFrozen()) {
            if (curDir == 1) {
              e.increaseX(); continue;
            }  if (curDir == 2) {
              e.decreaseX(); continue;
            }  if (curDir == 3) {
              e.decreaseY(); continue;
            }  if (curDir == 4)
              e.increaseY(); 
           continue;
         } 
          e.checkFrozenTimer();
       } 
     } 
   }
 
 
 
 
   
   private int thisTile(int x, int y) {
      x /= 32;
      y /= 32;
      int tile = this.map[y][x];
     
      return tile;
   }
 
 
   
    public void receiveTower(Tower tower) { this.towers.add(tower); }
 
 
   
    public void receiveArrow(Projectile projectile) { this.projectiles.add(projectile); }
 
   
   private void spawn() {
      this.enemies.add(this.enemiesTemp.get(this.JagVetInte));
      this.render.addEnemy(this.JagVetInte);
      this.JagVetInte++;
   }
 
 
   
   private int nextTile(int x, int y, int curDir) {
      int tile = -1;
     
      if (curDir == 1 && x < 640) {
       
        x /= 32;
        y /= 32;
        tile = this.map[y][x + 1];
      } else if (curDir == 2 && x > 0) {
        x--;
        x /= 32;
        y /= 32;
        tile = this.map[y][x];
      } else if (curDir == 3 && y > 0) {
        y--;
        x /= 32;
        y /= 32;
        tile = this.map[y][x];
      } else if (curDir == 4 && y < 480) {
       
        y /= 32;
        x /= 32;
        tile = this.map[y + 1][x];
     } 
 
     
      return tile;
   }
 
   
   public void receiveEnemies(List<Enemy> testar, int sizeOfWave) {
      for (int i = 0; i < testar.size(); i++) {
        this.enemiesTemp.add(testar.get(i));
     }
      this.allSpawned = false;
     
      this.hud.setSizeOfWave(sizeOfWave);
   }
 
 
   
    public void receiveMap(int[][] map) { this.map = map; }
 
 
 
 
   
    public void getRenderObj(MainRender render) { this.render = render; }
 
   
   private int findStartDirection(int x, int y) {
      int newDir = 5;
      x /= 32;
      y /= 32;
      int curMapType = this.map[y][x];
     
      if (curMapType == this.map[y][x + 1]) {
        newDir = 1;
      } else if (curMapType == this.map[y][x - 1]) {
        newDir = 2;
      } else if (curMapType == this.map[y - 1][x]) {
        newDir = 3;
      } else if (curMapType == this.map[y + 1][x]) {
        newDir = 4;
     } else {
        System.out.println("Cant find a way, i just spawned and no paths at all could be found.");
     } 
      return newDir;
   }
 
 
   
   private int findNewDir(int curDir, int x, int y) {
      int newDir = -1;
      x /= 32;
      y /= 32;
      int curMapType = this.map[y][x];
 
     
      if (curDir == 1) {
 
       
        if (y > 0 && curMapType == this.map[y + 1][x]) {
          newDir = 4;
        } else if (curMapType == this.map[y - 1][x]) {
          newDir = 3;
       }
     
     }
      else if (curDir == 2) {
 
       
        if (curMapType == this.map[y - 1][x]) {
          newDir = 3;
        } else if (curMapType == this.map[y + 1][x]) {
          newDir = 4;
       }
     
     }
      else if (curDir == 3) {
 
       
        if (curMapType == this.map[y][x + 1]) {
          newDir = 1;
        } else if (curMapType == this.map[y][x - 1]) {
          newDir = 2;
       }
     
     }
      else if (curDir == 4) {
 
       
        if (curMapType == this.map[y][x + 1]) {
          newDir = 1;
        } else if (curMapType == this.map[y][x - 1]) {
          newDir = 2;
       } 
     } 
 
     
      return newDir;
   }
   
   public void changePaused() {
      if (!this.paused) {
        this.paused = true;
        this.delay = System.currentTimeMillis() - this.timer;
        System.out.println("pause");
     } else {
        this.paused = false;
        this.timer = System.currentTimeMillis() - this.delay;
        System.out.println("unpause");
     } 
   }
 
 
   
    public List<Tower> getTowerList() { return this.towers; }
 
 
   
    public void setStartMenu(boolean startMenu) { this.startMenu = startMenu; }
 
 
   
    public void setGameOver(boolean gameOver) { this.gameOver = gameOver; }
 
 
   
    public List<Enemy> getEnemyList() { return this.enemies; }
 
   
    public List<Enemy> getEnemiesTemp() { return this.enemiesTemp; }
 
   
    public void resetJagVetInte() { this.JagVetInte = 0; }
   
   public void gameWon() {
      this.game.gameIsWon();
      this.gameWon = true;
   }
 
 
   
    public void setGameWon(boolean b) { this.gameWon = b; } }
