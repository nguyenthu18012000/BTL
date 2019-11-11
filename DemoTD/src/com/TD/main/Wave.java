 package com.TD.main;
 import java.awt.image.BufferedImage;
import java.util.ArrayList;
 import java.util.List;

import com.TD.main.graphics.MainRender;
 
 public class Wave {
   private Mover mover;
   private MainRender render;
   private int amountGround;
   private int hpGround;
   private int speedGround;
   private int amountAir;
   private int hpAir;
   private int speedAir;
   private int amountBoss;
   
   public Wave(int amountGround, int hpGround, int speedGround, int amountAir, int hpAir, int speedAir, int amountBoss, int hpBoss, int speedBoss, Mover mover, MainRender render, List<Tower> towers, HUD hud) {
      this.enemies = new ArrayList<>();
     
      this.loader = new BufferImageLoader("spritesheet.png");
     
      this.waveDead = false;
      this.sizeOfWave = 0;
 
 
     
      this.amountGround = amountGround;
      this.hpGround = hpGround;
      this.speedGround = speedGround;
     
      this.amountAir = amountAir;
      this.hpAir = hpAir;
      this.speedAir = speedAir;
     
      this.amountBoss = amountBoss;
      this.hpBoss = hpBoss;
      this.speedBoss = speedBoss;
     
      this.mover = mover;
      this.render = render;
      this.towers = towers;
     
      this.ground = this.loader.grabSprite(1, 2);
      this.air = this.loader.grabSprite(3, 2);
      this.boss = this.loader.grabSprite(4, 1);
 
     
      for (int i = 0; i < this.amountGround; i++) {
        this.enemies.add(new Enemy(128, 0, this.ground, this.speedGround, 4, this.hpGround, hud, 1));
        this.sizeOfWave++;
     } 
      for (int i = 0; i < this.amountAir; i++) {
        this.enemies.add(new Enemy(0, 96, this.air, this.speedAir, 1, this.hpAir, hud, 2));
        this.sizeOfWave++;
     } 
      for (int i = 0; i < this.amountBoss; i++) {
        this.enemies.add(new Enemy(128, 0, this.boss, this.speedBoss, 4, this.hpBoss, hud, 3));
        this.sizeOfWave++;
     } 
   }
   private int hpBoss;
   private int speedBoss;
   
   public void sendWave() {
      this.mover.receiveEnemies(this.enemies, this.sizeOfWave);
      this.render.receiveEnemies(this.enemies);
      for (Tower t : this.towers)
        t.receiveEnemyList(this.enemies); 
   }
   private List<Enemy> enemies; private List<Tower> towers; private BufferImageLoader loader; private BufferedImage ground; private BufferedImage air;
   private BufferedImage boss;
   private boolean waveDead;
   private int sizeOfWave;
   
    public boolean getWaveDead() { return this.waveDead; }
  
    public void setWaveDead(boolean deadOrNot) { this.waveDead = deadOrNot; }
 
    public int getSizeOfWave() { return this.sizeOfWave; }
  
    public List<Enemy> getEnemyList() { return this.enemies; }
 
    public List<Enemy> getEnemies() { return this.enemies; }
  
    public void setEnemies(List<Enemy> enemies) { this.enemies = enemies; }
 
    public List<Tower> getTowers() { return this.towers; }
 
    public void setTowers(List<Tower> towers) { this.towers = towers; }
 }
