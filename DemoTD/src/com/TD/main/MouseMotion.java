 package com.TD.main;
 
 import java.awt.event.MouseEvent;
 import java.awt.event.MouseMotionAdapter;
 
 
 public class MouseMotion
   extends MouseMotionAdapter
 {
   Game game;
   
    public MouseMotion(Game game) { this.game = game; }
 
 
 
 
   
    public void mouseMoved(MouseEvent e) { this.game.mouseMoved(e); }
 
 
 
 
   
    public void mouseReleased(MouseEvent e) { this.game.mouseReleased(e); }
 
 
 
   
    public void mouseDragged(MouseEvent e) { this.game.mouseDragged(e); }
 }
