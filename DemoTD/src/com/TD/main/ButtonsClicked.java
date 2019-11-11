 package com.TD.main;
 
 import com.TD.main.graphics.MainRender;

 public class ButtonsClicked
 {
   private Mover mover;
   private MainRender render;
   private HUD hud;
   private int pressedX;
   private int pressedY;
   private WaveManager waveManager;
   
   public ButtonsClicked(Mover mover, MainRender render, WaveManager waveManager, HUD hud, TowerHandler towerHandler, Game game) {
     this.gameStarted = false; this.gamePaused = false;
 
     
     this.mover = mover;
     this.render = render;
     this.waveManager = waveManager;
     this.hud = hud;
     this.towerHandler = towerHandler;
     this.sound = new Sound();
     this.game = game;
   }
   private TowerHandler towerHandler; private Tower towerTemp; private Sound sound; private Game game; private boolean gameStarted;
   private boolean gamePaused;
   
   public void getHud(HUD hud) {}
   
   public void testIfAnyButtonIsPressed(int releasedX, int releasedY) {
     if (releasedX < 640 && releasedX > 0 && releasedY < 576 && releasedY > 0 && this.gameStarted) {
       
       if (this.hud.getSoundOnOffBounds().contains(releasedX, releasedY) && this.hud.getSoundOnOffBounds().contains(this.pressedX, this.pressedY)) {
         this.hud.changeMusicOnOff();
         if (this.hud.getMusicPaused()) {
           this.sound.pause();
         } else if (!this.hud.getMusicPaused()) {
           this.sound.play();
         } 
       } else if (this.hud.getSoundMinusBounds().contains(releasedX, releasedY) && this.hud.getSoundMinusBounds().contains(this.pressedX, this.pressedY)) {
         this.sound.changeVolume(-2.0F);
       }
       else if (this.hud.getSoundPlusBounds().contains(releasedX, releasedY) && this.hud.getSoundPlusBounds().contains(this.pressedX, this.pressedY)) {
         this.sound.changeVolume(2.0F);
       } 
       
       if (this.hud.getPause().contains(releasedX, releasedY) && this.hud.getPause().contains(this.pressedX, this.pressedY)) {
         changePaused();
       } else if (this.hud.getNextWave().contains(releasedX, releasedY) && 
         this.hud.getNextWave().contains(this.pressedX, this.pressedY) && !this.gamePaused) {
         
         if (this.render.getSizeOfWave() == 0 || this.waveManager.getWaveNr() == 0) {
 
 
           
           this.waveManager.send();
           this.hud.setCountDownStarted(false);
           this.game.setWaveCountDown(false);
         } else {
           
           System.out.println("not yet,  (ButtonsClicked) render.getSizeOFwave ==" + this.render.getSizeOfWave());
         }
       
       } else if (this.hud.getTower1().contains(releasedX, releasedY) && this.hud.getTower1().contains(this.pressedX, this.pressedY) && !this.gamePaused) {
         this.render.changeTowerClicked(1);
       }
       else if (this.hud.getTower2().contains(releasedX, releasedY) && this.hud.getTower2().contains(this.pressedX, this.pressedY) && !this.gamePaused) {
         this.render.changeTowerClicked(2);
       }
       else if (this.hud.getTower3().contains(releasedX, releasedY) && this.hud.getTower3().contains(this.pressedX, this.pressedY) && !this.gamePaused) {
         this.render.changeTowerClicked(3);
       }
       else if (this.hud.getTower4().contains(releasedX, releasedY) && this.hud.getTower4().contains(this.pressedX, this.pressedY) && !this.gamePaused) {
         this.render.changeTowerClicked(4);
       }
       else if (releasedY < 480 && this.towerHandler.findIfTowerIsHere(releasedX, releasedY) != 0 && !this.gamePaused) {
         this.towerTemp = this.towerHandler.getTowerAtLoc(releasedX, releasedY);
         if (this.towerTemp.getTowerLevel() == 3) {
           this.hud.setIsTowerMaxLevel(true);
         } else {
           this.hud.setIsTowerMaxLevel(false);
         }  System.out.println("hej1   " + this.towerTemp.getTowerNumber());
         this.hud.setTowerClicked(true);
         this.hud.setTowerClickedTwo(this.towerTemp.getTowerDmgString());
         this.hud.setTowerClickedOne(this.towerTemp.getTowerNameString());
         this.hud.setTowerClickedThree(this.towerTemp.getTowerSpeedString());
         this.hud.setTowerLevel(this.towerTemp.getTowerLevelString());
         this.hud.setTowerUpgradeCost(this.towerTemp.getTowerUpgradeCost());
         this.hud.setTowerSellAmount(this.towerTemp.getSellAmount());
       
       }
       else if ((releasedY >= 576 || releasedY <= 480) && !this.gamePaused) {
         this.hud.setTowerClicked(false);
       } else if (this.hud.getTowerClicked() && !this.gamePaused) {
         
         if (this.hud.getTowerUpgrade().contains(releasedX, releasedY) && this.hud.getTowerUpgrade().contains(this.pressedX, this.pressedY) && this.towerTemp.getTowerLevel() != 3) {
           if (this.hud.getAmountOfGold() >= this.towerTemp.getTowerUpgradeCost()) {
             this.hud.addGold(-this.towerTemp.getTowerUpgradeCost());
             this.towerTemp.upgradeTower();
             if (this.towerTemp.getTowerLevel() == 3)
               this.hud.setIsTowerMaxLevel(true); 
             this.hud.setTowerClickedTwo(this.towerTemp.getTowerDmgString());
             this.hud.setTowerClickedOne(this.towerTemp.getTowerNameString());
             this.hud.setTowerClickedThree(this.towerTemp.getTowerSpeedString());
             this.hud.setTowerLevel(this.towerTemp.getTowerLevelString());
             this.hud.setTowerUpgradeCost(this.towerTemp.getTowerUpgradeCost());
             this.hud.setTowerSellAmount(this.towerTemp.getSellAmount());
           } else {
             System.out.println("Aja bajja, ej cash bror!!! (ButtonsClicked)");
           }
         
         } else if (this.hud.getTowerSell().contains(releasedX, releasedY) && this.hud.getTowerSell().contains(this.pressedX, this.pressedY)) {
 
 
           
           this.hud.addGold(this.towerTemp.getSellAmount());
           this.towerHandler.removeTowerHere(this.towerTemp.getX(), this.towerTemp.getY());
           this.mover.getTowerList().remove(this.towerTemp);
           this.render.getTowerList().remove(this.towerTemp);
           this.towerHandler.getTowers().remove(this.towerTemp);
           this.hud.setTowerClicked(false);
         } 
       } 
     } 
   }
   
   private void changePaused() {
     this.render.changePaused();
     this.mover.changePaused();
     if (this.gamePaused) {
       this.gamePaused = false;
     } else {
       this.gamePaused = true;
     }  this.game.getButtonsHoveredOver().changePaused();
   }
 
 
   
   public int getPressedX() { return this.pressedX; }
 
 
   
   public void setPressedX(int pressedX) { this.pressedX = pressedX; }
 
 
   
   public int getPressedY() { return this.pressedY; }
 
 
   
   public void setPressedY(int pressedY) { this.pressedY = pressedY; } 
   public Sound getSoundClass() { return this.sound; }  
   public void setGameStartedToTrue() { this.gameStarted = true; }
 }
