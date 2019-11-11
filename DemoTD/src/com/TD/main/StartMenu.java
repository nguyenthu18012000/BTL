 package com.TD.main;
 
 import java.awt.Graphics;
 import java.awt.image.BufferedImage;
 
 public class StartMenu
 {
    private BufferImageLoader loader = new BufferImageLoader("startmenu.png");
 
 
   
    private BufferedImage startmenu = this.loader.getImg();
 
 
   
    public void draw(Graphics g) { g.drawImage(this.startmenu, 0, 0, null); }
 }
