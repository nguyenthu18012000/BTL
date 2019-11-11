 package com.TD.main;
 
 public class ButtonsHoveredOver
 {
   private HUD hud;
   private boolean gamePaused = false;
   
    public void getHud(HUD hud) { this.hud = hud; }
 
 
   
   public void testIfMouseIsOver(int x, int y) {
      if (this.hud.getPause().contains(x, y)) {
        this.hud.setPauseColorGray();
      } else if (this.hud.getNextWave().contains(x, y) && !this.gamePaused) {
        this.hud.setNextWaveColorGray();
      } else if (this.hud.getTower1().contains(x, y) && !this.gamePaused) {
        this.hud.setT1ColorGray();
        this.hud.setTowerInfoString("Bow-Tower, Air/Ground", "Low DMG", "Cost: 150 Gold");
      } else if (this.hud.getTower2().contains(x, y) && !this.gamePaused) {
        this.hud.setT2ColorGray();
        this.hud.setTowerInfoString("Ice-Tower, Air/Ground", "Stuns Target", "Cost: 270 Gold");
      } else if (this.hud.getTower3().contains(x, y) && !this.gamePaused) {
        this.hud.setT3ColorGray();
        this.hud.setTowerInfoString("Missile-Tower, Air", "High DMG", "Cost: 310 Gold");
      } else if (this.hud.getTower4().contains(x, y) && !this.gamePaused) {
        this.hud.setT4ColorGray();
        this.hud.setTowerInfoString("Hammer-Tower, Ground", "High DMG", "Cost: 400 Gold");
      } else if (this.hud.getTowerSell().contains(x, y)) {
       
        this.hud.setTowerSellColorGray();
      } else if (this.hud.getTowerUpgrade().contains(x, y)) {
        this.hud.setTowerUpgradeColorGray();
     } else {
        resetAllColors();
     } 
   }
 
   
   private void resetAllColors() {
      this.hud.setPauseColorBlack();
      this.hud.setNextWaveColorBlack();
      this.hud.setT1ColorBlack();
      this.hud.setT2ColorBlack();
      this.hud.setT3ColorBlack();
      this.hud.setT4ColorBlack();
      this.hud.setTowerSellColorBlack();
      this.hud.setTowerUpgradeColorBlack();
      this.hud.setTowerInfoString(" ", " ", " ");
   }
   
   public void changePaused() {
      if (this.gamePaused) {
        this.gamePaused = false;
     } else {
       
        this.gamePaused = true;
     } 
   }
 }
