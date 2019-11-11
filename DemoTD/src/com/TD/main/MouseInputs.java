 package com.TD.main;
 
 import java.awt.event.MouseEvent;
 import java.awt.event.MouseListener;
 
 public class MouseInputs
   implements MouseListener
 {
   Game game;
   
    public MouseInputs(Game game) { this.game = game; }
 
 
 
   
    public void mouseClicked(MouseEvent e) { this.game.mouseClicked(e); }
 
 
 
 
   
    public void mouseReleased(MouseEvent e) { this.game.mouseReleased(e); }
 
 
 
 
   
   public void mouseEntered(MouseEvent arg0) {}
 
 
 
   
   public void mouseExited(MouseEvent arg0) {}
 
 
 
   
    public void mousePressed(MouseEvent e) { this.game.mousePressed(e); }
 }
