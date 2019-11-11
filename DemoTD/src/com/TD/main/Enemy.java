 package com.TD.main;
 
 import java.awt.Graphics;
 import java.awt.Rectangle;
 import java.awt.image.BufferedImage;
 
 public class Enemy {
   private int speed;
   private int curDir;
   
   public Enemy(int x, int y, BufferedImage sprite, int speed, int curDir, double healthPoints, HUD hud, int enemyType) {
     this.alive = true; this.isFrozen = false;
     this.enemy = sprite;
     this.speed = speed;
     this.curDir = curDir;
     this.healthPoints = healthPoints;
     this.hud = hud;
     this.enemyType = enemyType;
     this.rectangle = new Rectangle(x, y, 32, 32);
   }
   private int enemyType;       
			private double healthPoints; 
			private BufferedImage enemy; 
			private boolean alive;
   private boolean isFrozen;
   
   public void draw(Graphics g) { 
			g.drawImage(this.enemy, this.rectangle.x, this.rectangle.y, null); 
			}
   private Rectangle rectangle;
   private HUD hud;
   private long frozenTimer;
   
   public void increaseX() { this.rectangle.x += this.speed; }
  
   public void increaseY() { this.rectangle.y += this.speed; }
 
   public void decreaseX() { this.rectangle.x -= this.speed; }
   
   public void decreaseY() { this.rectangle.y -= this.speed; }
  
   public int getX() { return this.rectangle.x; }
 
   public int getY() { return this.rectangle.y; }

   public int getSpeed() { return this.speed; }
  
   public void setSpeed(int speed) { this.speed = speed; }
 
   public int getCurDir() { return this.curDir; }

   public void setCurDir(int curDir) { this.curDir = curDir; }
 
   public void setAliveToFalse() {
     if (this.alive) {
       this.alive = false;
       this.hud.reduceSizeOfWaveByOne();
     } 
   }
 
   public boolean getAlive() { return this.alive; }

   public Rectangle bounds() { return this.rectangle; }
   
   public boolean isFrozen() { return this.isFrozen; }

   public void setFrozen(boolean isFrozen) { this.isFrozen = isFrozen; }
  
   private void checkIfHpLessThanZero() {
     if (this.healthPoints <= 0.0D) {
       setAliveToFalse();
       addGoldForKill();
     } 
   }
   public void hitByArrow(double dmg) {
     this.healthPoints -= dmg;
     checkIfHpLessThanZero();
   }
  
   public void hitByIce() {
     if (!this.isFrozen) {
       this.frozenTimer = System.currentTimeMillis();
       this.isFrozen = true;
     } 
   }
  
   public void checkFrozenTimer() {
     if (this.isFrozen && System.currentTimeMillis() - this.frozenTimer > 3000L)
       this.isFrozen = false; 
   }
   
   public void hitByMissile(double dmg) {
     this.healthPoints -= dmg;
     checkIfHpLessThanZero();
   }
   public void hitByHammer(double dmg) {
     this.healthPoints -= dmg;
     checkIfHpLessThanZero();
   }
   
   private void addGoldForKill() {
     if (this.enemyType == 1) {
       this.hud.addGold(5);
     } else if (this.enemyType == 2) {
       this.hud.addGold(10);
     } else if (this.enemyType == 3) {
       this.hud.addGold(20);
     } 
   }
 
   public int getEnemyType() { return this.enemyType; }
 }
