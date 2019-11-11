 package com.TD.main;
 
 import java.awt.image.BufferedImage;
 
 
 public class SpriteSheet
 {
   public BufferedImage spritesheet;
   
    public SpriteSheet(BufferedImage ss) { this.spritesheet = ss; }
 
 
   
   public BufferedImage grabSprite(int x, int y, int width, int height) {
      BufferedImage sprite = this.spritesheet.getSubimage(x, y, width, height);
 
     
      return sprite;
   }
 }
