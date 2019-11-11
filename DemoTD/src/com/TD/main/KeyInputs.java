 package com.TD.main;
 
 import java.awt.event.KeyAdapter;
 import java.awt.event.KeyEvent;
 
 public class KeyInputs
   extends KeyAdapter {
   Game game;
   
    public KeyInputs(Game game) { this.game = game; }
 
 
   
    public void keyPressed(KeyEvent e) { this.game.keyPressed(e); }
 
   
    public void keyReleased(KeyEvent e) { this.game.keyReleased(e); }
 }
