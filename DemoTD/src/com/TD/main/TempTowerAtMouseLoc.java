 package com.TD.main;
 
 import java.awt.Graphics;
 import java.awt.image.BufferedImage;
 
 
 public class TempTowerAtMouseLoc
 {
    private int x = 800; private int y = 800;
 
 
 
 
   
    private BufferImageLoader loader = new BufferImageLoader("spritesheet.png");
   
    private BufferedImage bowTower = this.loader.grabSprite(2, 2);
    private BufferedImage iceTower = this.loader.grabSprite(4, 2);
    private BufferedImage missileTower = this.loader.grabSprite(7, 3);
    private BufferedImage hammerTower = this.loader.grabSprite(7, 2);
 
   
   private int typeOfTower;
 
 
   
   public void draw(Graphics g) {
      this.x /= 32;
      this.y /= 32;
     
      this.x *= 32;
      this.y *= 32;
 
     
      if (this.y >= 480) {
        this.y = 448;
     }
      if (this.typeOfTower == 1) {
        g.drawImage(this.bowTower, this.x, this.y, null);
      } else if (this.typeOfTower == 2) {
        g.drawImage(this.iceTower, this.x, this.y, null);
      } else if (this.typeOfTower == 3) {
        g.drawImage(this.missileTower, this.x, this.y, null);
      } else if (this.typeOfTower == 4) {
        g.drawImage(this.hammerTower, this.x, this.y, null);
     } 
   }
 
 
 
 
 
   
    public int getX() { return this.x; }
 
 
   
    public void setX(int x) { this.x = x; }
 
 
   
    public int getY() { return this.y; }
 
 
   
    public void setY(int y) { this.y = y; }
 
 
   
    public void setTypeOfTower(int typeOfTower) { this.typeOfTower = typeOfTower; }
 }
