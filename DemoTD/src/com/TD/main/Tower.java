package com.TD.main;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
 
 public class Tower {
   private int x;
   private int y;
   private int typeOfTower;
   private BufferedImage towerImg;
   private BufferedImage towerImgTwo;
   private BufferedImage towerImgThree;
   
   public Tower(int x, int y, int typeOfTower, List<Enemy> enemies, int towerNumber) {
      this.loader = new BufferImageLoader("spritesheet.png");
      this.projectiles = new ArrayList<>();
 
 
     
      this.firstCall = true; this.targetHasBeenFound = false;
 
     
      this.towerLevel = 1;
     
      this.tileSize = 32;
 
     
      this.x = x;
      this.y = y;
      this.typeOfTower = typeOfTower;
      this.enemies = enemies;
      this.towerNumber = towerNumber;
 
     
      this.towerRange = new Rectangle(this.x - 64, this.y - 64, 160, 160);
     
      if (typeOfTower == 1) {
        this.towerImg = this.loader.grabSprite(2, 2);
        this.towerImgTwo = this.loader.grabSprite(1, 6);
        this.towerImgThree = this.loader.grabSprite(2, 6);
        this.towerDmg = 2.0D;
        this.towerNameString = "Bow Tower";
        this.towerDmgString = "Current Dmg: " + this.towerDmg;
        this.speed = 1000.0D;
        this.towerSpeedString = "Firing Speed: " + (this.speed / 1000.0D);
        this.upgradeOne = 90;
        this.upgradeTwo = 90;
        this.sellAmount = 75;
 
     
     }
      else if (typeOfTower == 2) {
        this.towerImg = this.loader.grabSprite(4, 2);
        this.towerImgTwo = this.loader.grabSprite(3, 6);
        this.towerImgThree = this.loader.grabSprite(4, 6);
        this.towerDmg = 0.0D;
        this.towerNameString = "Ice Tower";
        this.towerDmgString = "Current Dmg: " + this.towerDmg;
        this.speed = 4000.0D;
        this.towerSpeedString = "Firing Speed: " + (this.speed / 1000.0D);
        this.upgradeOne = 100;
        this.upgradeTwo = 120;
        this.sellAmount = 135;
 
     
     }
      else if (typeOfTower == 3) {
        this.towerImg = this.loader.grabSprite(7, 3);
        this.towerImgTwo = this.loader.grabSprite(1, 7);
        this.towerImgThree = this.loader.grabSprite(2, 7);
        this.towerDmg = 2.0D;
        this.towerNameString = "Missile Tower";
        this.towerDmgString = "Current Dmg: " + this.towerDmg;
        this.speed = 500.0D;
        this.towerSpeedString = "Firing Speed: " + (this.speed / 1000.0D);
        this.upgradeOne = 90;
        this.upgradeTwo = 100;
        this.sellAmount = 155;
 
 
 
 
     
     }
      else if (typeOfTower == 4) {
        this.towerImg = this.loader.grabSprite(7, 2);
        this.towerImgTwo = this.loader.grabSprite(5, 6);
        this.towerImgThree = this.loader.grabSprite(6, 6);
        this.towerDmg = 6.0D;
        this.towerNameString = "Hammer Tower";
        this.towerDmgString = "Current Dmg: " + this.towerDmg;
        this.speed = 1000.0D;
        this.towerSpeedString = "Firing Speed: " + (this.speed / 1000.0D);
       
        this.sellAmount = 200;
        this.upgradeOne = 150;
        this.upgradeTwo = 150;
     } 
   }
   private BufferImageLoader loader; 
   private List<Projectile> projectiles; 
   private List<Enemy> enemies; 
   private Enemy target; 
   private long timer; 
   private boolean firstCall; 
   private boolean targetHasBeenFound; 
   private Rectangle towerRange; 
   private double speed; 
   private double towerDmg; 
   private int towerNumber; 
   private int towerLevel; 
   private String towerNameString; 
   private String towerDmgString; 
   private String towerSpeedString;
   private String towerLevelString;
   private int upgradeOne;
   private int upgradeTwo;
   private int sellAmount;
   private int tileSize;
   
   private float calculateAngle() {
      double angleTemp = Math.atan2((this.target.getY() - this.y), (this.target.getX() - this.x));
      return (float)Math.toDegrees(angleTemp) + 90.0F;
   }
 
   
   public void draw(Graphics g) {
      for (Projectile p : this.projectiles) {
        p.draw(g);
     }
 
 
     
      if (this.towerLevel == 1) {
        g.drawImage(this.towerImg, this.x, this.y, null);
      } else if (this.towerLevel == 2) {
       
        g.drawImage(this.towerImgTwo, this.x, this.y, null);
      } else if (this.towerLevel == 3) {
        g.drawImage(this.towerImgThree, this.x, this.y, null);
     } 
   }
 
   public void update() {
      for (int i = 0; i < this.projectiles.size(); i++) {
        for (int j = 0; j < this.enemies.size(); j++) {
          if (((Enemy)this.enemies.get(j)).getAlive() && (
            (Projectile)this.projectiles.get(i)).getRectangle().intersects(((Enemy)this.enemies.get(j)).bounds())) {
            if (this.typeOfTower == 1) {
              ((Enemy)this.enemies.get(j)).hitByArrow(this.towerDmg);
            } else if (this.typeOfTower == 2) {
              ((Enemy)this.enemies.get(j)).hitByIce();
            } else if (this.typeOfTower == 3) {
              ((Enemy)this.enemies.get(j)).hitByMissile(this.towerDmg);
            } else if (this.typeOfTower == 4) {
              ((Enemy)this.enemies.get(j)).hitByHammer(this.towerDmg);
           } 
            ((Projectile)this.projectiles.get(i)).setArrowDead();
         } 
       } 
     } 
 
 
     
      if (this.firstCall) {
        this.firstCall = false;
        this.timer = System.currentTimeMillis();
     } 
     
      if (!this.targetHasBeenFound)
        findTarget(); 
      if (this.targetHasBeenFound)
     {
        if (this.typeOfTower == 1) {
 
 
         
          fireArrow();
 
       
       }
        else if (this.typeOfTower == 2) {
 
         
          fireIceBlock();
       }
        else if (this.typeOfTower == 3) {
 
         
          fireMissle();
       }
        else if (this.typeOfTower == 4) {
 
         
          fireHammer();
       } 
     }
     
      moveArrow();
      checkAndRemoveArrow();
   }
   
   private void fireHammer() {
      if (checkIfTargetIsStillWithinRange(this.target) && this.target.getEnemyType() != 2) {
       
        if ((System.currentTimeMillis() - this.timer) > this.speed) {
          this.timer = System.currentTimeMillis();
          this.projectiles
            .add(new Projectile(this.x + 16, this.y + 16, 5, 4, (int)calculateAngle(), this.target, false));
       } 
     } else {
       
        findNewTarget();
     } 
   }
   private void fireMissle() {
      if (checkIfTargetIsStillWithinRange(this.target) && this.target.getEnemyType() == 2) {
       
        if ((System.currentTimeMillis() - this.timer) > this.speed) {
          this.timer = System.currentTimeMillis();
          this.projectiles
            .add(new Projectile(this.x + 16, this.y + 16, 10, 3, (int)calculateAngle(), this.target, false));
       } 
     } else {
       
        findNewTarget();
     } 
   }
   private void fireIceBlock() {
      if (checkIfTargetIsStillWithinRange(this.target) && !this.target.isFrozen()) {
       
        if ((System.currentTimeMillis() - this.timer) > this.speed) {
          this.timer = System.currentTimeMillis();
          this.projectiles
            .add(new Projectile(this.x + 16, this.y + 16, 10, 2, (int)calculateAngle(), this.target, true));
       } 
     } else {
       
        findNewTarget();
     } 
   }
 
   
   private void fireArrow() {
      if (checkIfTargetIsStillWithinRange(this.target)) {
        if ((System.currentTimeMillis() - this.timer) > this.speed) {
          this.timer = System.currentTimeMillis();
          this.projectiles
            .add(new Projectile(this.x + 16, this.y + 16, 20, 1, (int)calculateAngle(), this.target, false));
       } 
     } else {
       
        findNewTarget();
     } 
   }
 
   
   private void findNewTarget() {
      this.targetHasBeenFound = false;
      findTarget();
   }
 
   
   private void findTarget() {
      for (Enemy e : this.enemies) {
        if (this.typeOfTower == 2) {
          if (e.getAlive() && e.bounds().intersects(this.towerRange) && !e.isFrozen()) {
           
            this.target = e;
            this.targetHasBeenFound = true; break;
         }  continue;
       } 
        if (e.getAlive() && e.bounds().intersects(this.towerRange)) {
         
          this.target = e;
          this.targetHasBeenFound = true;
         break;
       } 
     } 
   }
 
   
   private boolean checkIfTargetIsStillWithinRange(Enemy target) {
      if (target.bounds().intersects(this.towerRange) && target.getAlive()) {
        return true;
     }
      return false;
   }
   
   private void moveArrow() {
      for (Projectile p : this.projectiles) {
        if (p.getArrowNotDeadState()) {
          p.move();
          if (arrowOutsideMap((p.getRectangle()).x, (p.getRectangle()).y)) {
            p.setArrowDead();
         }
       } 
     } 
   }
 
   
   private void checkAndRemoveArrow() {
      for (int i = 0; i < this.projectiles.size(); i++) {
        if (!((Projectile)this.projectiles.get(i)).getArrowNotDeadState()) {
          this.projectiles.remove(i);
       }
     } 
   }
   
   private boolean arrowOutsideMap(int x, int y) {
      if (x > 0 && x < 640 && y > 0 && y < 480) {
        return false;
     }
      return true;
   }
   
   public void increaseTowerLevel() {
      if (this.towerLevel <= 3) {
        this.towerLevel++;
        this.towerLevelString = " Lvl: " + this.towerLevel;
     } else {
        System.out.println("max lvl already (towerClass)");
     } 
   }
   
    public void receiveEnemyList(List<Enemy> testar) { this.enemies = testar; }
 
 
   
    public int getTowerNumber() { return this.towerNumber; }
 
 
   
    public int getX() { return this.x; }
 
 
   
    public void setX(int x) { this.x = x; }
 
 
   
    public int getY() { return this.y; }
 
 
   
    public void setY(int y) { this.y = y; }
 
 
   
    public int getTypeOfTower() { return this.typeOfTower; }
 
 
   
    public void setTypeOfTower(int typeOfTower) { this.typeOfTower = typeOfTower; }
 
 
   
    public Rectangle getTowerRange() { return this.towerRange; }
 
 
   
    public void setTowerRange(Rectangle towerRange) { this.towerRange = towerRange; }
 
 
   
    public double getTowerDmg() { return this.towerDmg; }
 
 
   
    public void setTowerDmg(double towerDmg) { this.towerDmg = towerDmg; }
 
 
   
    public void setTowerNumber(int towerNumber) { this.towerNumber = towerNumber; }
 
   
    public String getTowerNameString() { return this.towerNameString; }
 
   
    public String getTowerDmgString() { return this.towerDmgString; }
 
 
   
    public void setTowerDmgString(String towerDmgString) { this.towerDmgString = towerDmgString; }
 
 
   
    public String getTowerSpeedString() { return this.towerSpeedString; }
 
 
   
    public void setTowerSpeedString(String towerSpeedString) { this.towerSpeedString = towerSpeedString; }
 
   
    public void setTowerLevel(int lvl) { this.towerLevel = lvl; }
 
 
   
    public int getTowerLevel() { return this.towerLevel; }
 
   
   public String getTowerLevelString() {
      this.towerLevelString = "Lvl: " + this.towerLevel;
      return this.towerLevelString;
   }
   public int getTowerUpgradeCost() {
      if (this.towerLevel == 1)
        return this.upgradeOne; 
      if (this.towerLevel == 2) {
        return this.upgradeTwo;
     }
      return 0;
   }
   
   public void upgradeTower() {
      if (this.towerLevel == 1) {
        upgradeStatsDependingOnTowerType();
        this.towerLevel = 2;
        this.sellAmount += this.upgradeOne / 2;
      } else if (this.towerLevel == 2) {
        upgradeStatsDependingOnTowerType();
        this.towerLevel = 3;
        this.sellAmount += this.upgradeTwo / 2;
     } else {
        System.out.println("nope! (tower class)");
     } 
   }
   
   private void upgradeStatsDependingOnTowerType() {
      if (this.typeOfTower == 1) {
        if (this.towerLevel == 1) {
          this.towerDmg = 3.5D;
          this.towerDmgString = "Current Dmg: " + this.towerDmg;
        } else if (this.towerLevel == 2) {
          this.towerDmg = 5.5D;
          this.towerDmgString = "Current Dmg: " + this.towerDmg;
       }
     
     }
      else if (this.typeOfTower == 2) {
        if (this.towerLevel == 1) {
          this.speed = 3500.0D;
          this.towerSpeedString = "Firing Speed: " + (this.speed / 1000.0D);
        } else if (this.towerLevel == 2) {
          this.speed = 2500.0D;
          this.towerSpeedString = "Firing Speed: " + (this.speed / 1000.0D);
       }
     
      } else if (this.typeOfTower == 3) {
       if (this.towerLevel == 1) {
          this.towerDmg = 5.0D;
          this.towerDmgString = "Current Dmg: " + this.towerDmg;
        } else if (this.towerLevel == 2) {
          this.towerDmg = 7.0D;
          this.towerDmgString = "Current Dmg: " + this.towerDmg;
       }
     
      } else if (this.typeOfTower == 4) {
        if (this.towerLevel == 1) {
          this.towerDmg = 8.0D;
          this.towerDmgString = "Current Dmg: " + this.towerDmg;
        } else if (this.towerLevel == 2) {
          this.towerDmg = 11.0D;
          this.towerDmgString = "Current Dmg: " + this.towerDmg;
       } 
     } 
   }
 
   
    public int getSellAmount() { return this.sellAmount; }
 }
