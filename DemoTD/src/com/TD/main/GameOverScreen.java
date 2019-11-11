 package com.TD.main;
 
 import java.awt.Graphics;
 import java.awt.image.BufferedImage;
 
 public class GameOverScreen
 {
    private BufferImageLoader loader = new BufferImageLoader("gameover.png");
 
   
    private BufferedImage img = this.loader.getImg();
 
    public void draw(Graphics g) { g.drawImage(this.img, 0, 0, null); }
 }
