 package com.TD.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class HUD { private int x; private int y; private int width; private int height; 
				private int life; private int sizeOfWave; private int currentWave; 
				private int totalNrOfWaves; private int amountOfGold; private int towerUpgradeCost; 
				private int towerSellAmount; private double countDownDouble; 
				private double countDownDoubleStartValue; private int START_LIFE = 20; 
				
				private boolean gameOver; private boolean towerClicked; private boolean isTowerMaxLevel; 
				private BufferImageLoader loader; private BufferedImage bowTower; 
				private BufferedImage iceTower; private BufferedImage missileTower;
   private BufferedImage hammerTower;
   private BufferedImage soundOn;
   private BufferedImage soundOff;
   private BufferedImage soundMinus;
   private BufferedImage soundPlus;
   private Color pausedColor;
   private Color nextWaveColor;
   
   public HUD(int x, int y, int width, int height, Game game) {
      this.currentWave = 0; this.amountOfGold = 350000; this.towerSellAmount = 666;
      this.countDownDoubleStartValue = 15.1D;
     
      this.START_LIFE = 20;
      this.gameOver = false; this.towerClicked = false; this.isTowerMaxLevel = false;
 
     
      this.pausedColor = Color.BLACK;
      this.nextWaveColor = Color.BLACK;
      this.t1Color = Color.BLACK;
      this.t2Color = Color.BLACK;
      this.t3Color = Color.BLACK;
      this.t4Color = Color.BLACK;
      this.tUpgrade = Color.BLACK;
      this.tSell = Color.BLACK;
      this.isPaused = false; this.musicPaused = false; this.countDownStarted = false;
     
      this.towerInfo1 = " "; this.towerInfo2 = " "; this.towerInfo3 = " ";
     
      this.towerClickedOne = "NAME_HERE"; this.towerClickedTwo = "CURR_DMG_HERE"; this.towerClickedThree = "CURR_SPEE_HERE"; this.towerLevel = "CURR_LVL_HERE";
    
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
      this.life = 20;
      this.game = game;
      this.countDownDouble = this.countDownDoubleStartValue;
      this.countDownString = "Next Wave In: " + (int)this.countDownDouble;
     
      this.loader = new BufferImageLoader("spritesheet.png");
      this.bowTower = this.loader.grabSprite(2, 2);
      this.iceTower = this.loader.grabSprite(4, 2);
      this.missileTower = this.loader.grabSprite(7, 3);
      this.hammerTower = this.loader.grabSprite(7, 2);
      this.soundOn = this.loader.grabSpriteAtASpecificCords(3, 7, 17, 19, 0, 0);
      this.soundOff = this.loader.grabSpriteAtASpecificCords(4, 7, 25, 19, 0, 0);
      this.soundPlus = this.loader.grabSpriteAtASpecificCords(5, 7, 16, 16, 0, 0);
      this.soundMinus = this.loader.grabSpriteAtASpecificCords(5, 7, 16, 2, 16, 0);
     
      setRectBounds();
   }
   private Color t1Color; 
			private Color t2Color; 
			private Color t3Color; 
			private Color t4Color; 
			private Color tUpgrade; 
			private Color tSell; 
			private boolean isPaused; 
			private boolean musicPaused; 
			private boolean countDownStarted; 
			private Rectangle pause; 
			private Rectangle nextWave; 
			private Rectangle tower1; 
			private Rectangle tower2; 
			private Rectangle tower3; 
			private Rectangle tower4; 
			private Rectangle towerUpgrade; 
			private Rectangle towerSell; 
			private Rectangle soundOnOffBounds; 
			private Rectangle soundPlusBounds; 
			private Rectangle soundMinusBounds; 
			private String towerInfo1; 
			private String towerInfo2; 
			private String towerInfo3; 
			private String countDownString; 
			private String towerClickedOne; 
			private String towerClickedTwo; 
			private String towerClickedThree; 
			private String towerLevel; 
			private Game game;
   
			
   public void setRectBounds() {
      this.pause = new Rectangle(this.x + 520, this.y + 50, 100, 30);
      this.nextWave = new Rectangle(this.x + 520, this.y + 10, 100, 30);
      this.tower1 = new Rectangle(this.x + 130, this.y + 30, 36, 36);
      this.tower2 = new Rectangle(this.x + 170, this.y + 30, 36, 36);
      this.tower3 = new Rectangle(this.x + 210, this.y + 30, 36, 36);
      this.tower4 = new Rectangle(this.x + 250, this.y + 30, 36, 36);
      this.towerUpgrade = new Rectangle(this.x + 420, this.y + 10, 75, 25);
      this.towerSell = new Rectangle(this.x + 420, this.y + 50, 75, 25);
 
     
      this.soundOnOffBounds = new Rectangle(5, 2, 17, 19);
      this.soundPlusBounds = new Rectangle(35, 2, 16, 16);
      this.soundMinusBounds = new Rectangle(55, 2, 16, 16);
   }
 
 
 
 
 
   
   public void draw(Graphics g) {
      g.setColor(Color.ORANGE);
      g.fillRect(this.x, this.y, this.width, this.height);
      g.setColor(Color.BLUE);
      g.drawRect(this.x, this.y, this.width - 1, this.height - 1);
      g.setColor(Color.BLACK);
      g.setFont(new Font("Impact", 1, 16));
      g.drawString("Life: " + this.life, this.x + 10, this.y + 20);
      g.drawString("Wave: " + this.currentWave + " / " + this.totalNrOfWaves, this.x + 10, this.y + 50);
      g.drawString("Size: " + this.sizeOfWave, this.x + 10, this.y + 80);
 
 
 
     
      g.setColor(Color.BLUE);
      g.drawLine(this.x + 120, this.y, this.x + 120, this.y + 100);
      g.drawLine(this.x + 122, this.y, this.x + 122, this.y + 100);
      g.setColor(Color.WHITE);
      g.drawLine(this.x + 121, this.y, this.x + 121, this.y + 100);
     
      g.setColor(Color.BLUE);
      g.drawLine(this.x + 295, this.y, this.x + 295, this.y + 100);
      g.drawLine(this.x + 297, this.y, this.x + 297, this.y + 100);
      g.setColor(Color.WHITE);
      g.drawLine(this.x + 296, this.y, this.x + 296, this.y + 100);
 
 
 
 
     
      if (this.towerClicked) {
        g.setFont(new Font("Impact", 1, 13));
        g.setColor(this.tSell);
        g.fillRect(this.towerSell.x, this.towerSell.y, this.towerSell.width, this.towerSell.height);
        g.setColor(Color.GREEN);
        g.drawRect(this.towerSell.x, this.towerSell.y, this.towerSell.width, this.towerSell.height);
        g.setColor(Color.BLACK);
        g.drawString(this.towerClickedOne, this.x + 300, this.y + 20);
        g.drawString(this.towerClickedTwo, this.x + 300, this.y + 38);
        g.drawString(this.towerClickedThree, this.x + 300, this.y + 56);
        g.drawString(this.towerLevel, this.x + 300, this.y + 74);
       
        g.setColor(Color.WHITE);
        g.setFont(new Font("Impact", 1, 11));
        g.drawString("Sell " + this.towerSellAmount, this.towerSell.x + 5, this.towerSell.y + 18);
        if (!this.isTowerMaxLevel) {
 
         
          g.setColor(this.tUpgrade);
          g.fillRect(this.towerUpgrade.x, this.towerUpgrade.y, this.towerUpgrade.width, this.towerUpgrade.height);
          g.setColor(Color.GREEN);
          g.drawRect(this.towerUpgrade.x, this.towerUpgrade.y, this.towerUpgrade.width, this.towerUpgrade.height);
          g.setColor(Color.WHITE);
          g.setFont(new Font("Impact", 0, 13));
          g.drawString("Upgrade " + this.towerUpgradeCost, this.towerUpgrade.x + 5, this.towerUpgrade.y + 18);
       } 
     } 
 
 
 
     
      g.setFont(new Font("Impact", 1, 16));
      g.setColor(Color.BLUE);
      g.drawLine(this.x + 500, this.y, this.x + 500, this.y + 100);
      g.drawLine(this.x + 502, this.y, this.x + 502, this.y + 100);
      g.setColor(Color.WHITE);
      g.drawLine(this.x + 501, this.y, this.x + 501, this.y + 100);
 
 
 
 
 
 
     
      g.setColor(Color.BLACK);
     
      g.drawString("Gold: " + this.amountOfGold, this.x + 130, this.y + 20);
     
      g.setFont(new Font("Arial", 1, 12));
      g.drawString(this.towerInfo1, this.x + 135, this.y + 78);
      g.drawString(this.towerInfo2, this.x + 135, this.y + 90);
      g.drawString(this.towerInfo3, this.x + 210, this.y + 90);
     
      g.setFont(new Font("Impact", 1, 16));
 
     
      g.setColor(this.t1Color);
      g.fillRect(this.tower1.x, this.tower1.y, this.tower1.width, this.tower1.height);
      g.drawImage(this.bowTower, this.tower1.x + 2, this.tower1.y + 2, null);
     
      g.setColor(this.t2Color);
      g.fillRect(this.tower2.x, this.tower2.y, this.tower2.width, this.tower2.height);
      g.drawImage(this.iceTower, this.tower2.x + 2, this.tower2.y + 2, null);
     
      g.setColor(this.t3Color);
      g.fillRect(this.tower3.x, this.tower3.y, this.tower3.width, this.tower3.height);
      g.drawImage(this.missileTower, this.tower3.x + 2, this.tower3.y + 2, null);
     
      g.setColor(this.t4Color);
      g.fillRect(this.tower4.x, this.tower4.y, this.tower4.width, this.tower4.height);
      g.drawImage(this.hammerTower, this.tower4.x + 2, this.tower4.y + 2, null);
 
 
 
 
     
      g.setColor(this.nextWaveColor);
      g.fillRect(this.nextWave.x, this.nextWave.y, this.nextWave.width, this.nextWave.height);
 
     
      g.setColor(this.pausedColor);
      g.fillRect(this.pause.x, this.pause.y, this.pause.width, this.pause.height);
      g.setColor(Color.GREEN);
      g.drawRect(this.x + 520, this.y + 10, 100, 30);
      g.drawRect(this.x + 521, this.y + 11, 98, 28);
     
      g.drawRect(this.x + 520, this.y + 50, 100, 30);
      g.drawRect(this.x + 521, this.y + 51, 98, 30);
     
      g.setColor(Color.WHITE);
      if (this.game.getWaveManager().getWaveNr() == 0) {
        g.drawString("Send Wave", this.x + 532, this.y + 31);
     } else {
       
        g.drawString("Next Wave", this.x + 532, this.y + 31);
     } 
      if (!this.isPaused) {
        g.drawString("    Pause", this.x + 532, this.y + 71);
     } else {
        g.drawString("  Unpause", this.x + 532, this.y + 71);
     } 
 
 
     
      if (this.musicPaused) {
        g.drawImage(this.soundOff, this.soundOnOffBounds.x, this.soundOnOffBounds.y, null);
     } else {
        g.drawImage(this.soundOn, this.soundOnOffBounds.x, this.soundOnOffBounds.y, null);
     } 
      g.drawImage(this.soundPlus, this.soundPlusBounds.x, this.soundPlusBounds.y, null);
      g.drawImage(this.soundMinus, this.soundMinusBounds.x, this.soundMinusBounds.y + 7, null);
     
      if (this.countDownStarted) {
        g.setFont(new Font("Arial", 1, 25));
        g.setColor(Color.BLACK);
        g.drawString(this.countDownString, 430, 470);
     } 
   }
 
   
   public void changePaused() {
      if (!this.isPaused) {
        this.isPaused = true;
     } else {
       
        this.isPaused = false;
     } 
   }
 
 
   
    public void reduceLife() { this.life--; }
 
 
 
 
   
    public int getSizeOfWave() { return this.sizeOfWave; }
   
   public void checkTimer() {
      if (this.countDownStarted) {
        this.countDownDouble -= 0.1D;
        updateCountdownTimerString();
        if (this.countDownDouble <= 0.0D) {
          sendWave();
          this.countDownStarted = false;
          this.game.setWaveCountDown(false);
       } 
     } 
   }
 
 
   
    public void sendWave() { this.game.getWaveManager().send(); }
 
 
 
   
    public void setSizeOfWave(int sizeOfWave) { this.sizeOfWave = sizeOfWave; }
 
 
   
   public void reduceSizeOfWaveByOne() {
      this.sizeOfWave--;
     
      if (this.sizeOfWave == 0 && this.game.getWaveManager().getWavesSize() >= this.game.getWaveManager().getWaveNr()) {
        this.countDownStarted = true;
        this.game.setWaveCountDown(true);
        this.countDownDouble = this.countDownDoubleStartValue;
 
 
     
     }
      else if (this.sizeOfWave == 0 && this.game.getWaveManager().getWavesSize() == this.game.getWaveManager().getWaveNr()) {
        this.game.gameIsWon();
     } 
   }
 
 
 
   
    public void increaseWaveNrByOne() { this.currentWave++; }
 
 
 
 
   
    public void setTotalNrOfWaves(int total) { this.totalNrOfWaves = total; }
 
 
   
    public void addGold(int amount) { this.amountOfGold += amount; }
 
 
 
   
    public void reduceGold(int amount) { this.amountOfGold -= amount; }
 
 
 
   
    public void updateCountdownTimerString() { this.countDownString = "Next Wave In: " + (int)this.countDownDouble; }
   
    public void setPauseColorBlack() { this.pausedColor = Color.BLACK; }
 
 
   
    public void setPauseColorGray() { this.pausedColor = Color.GRAY; }
 
 
   
    public void setNextWaveColorBlack() { this.nextWaveColor = Color.BLACK; }
 
   
    public void setNextWaveColorGray() { this.nextWaveColor = Color.GRAY; }
 
   
    public void setT1ColorBlack() { this.t1Color = Color.BLACK; }
 
   
    public void setT2ColorBlack() { this.t2Color = Color.BLACK; }
 
   
    public void setT3ColorBlack() { this.t3Color = Color.BLACK; }
 
   
    public void setT4ColorBlack() { this.t4Color = Color.BLACK; }
 
   
    public void setT1ColorGray() { this.t1Color = Color.GRAY; }
 
   
    public void setT2ColorGray() { this.t2Color = Color.GRAY; }
 
   
    public void setT3ColorGray() { this.t3Color = Color.GRAY; }
 
   
    public void setT4ColorGray() { this.t4Color = Color.GRAY; }
 
 
 
 
 
 
   
    public Rectangle getPause() { return this.pause; }
 
 
   
    public void setPause(Rectangle pause) { this.pause = pause; }
 
 
   
    public Rectangle getNextWave() { return this.nextWave; }
 
 
   
    public void setNextWave(Rectangle nextWave) { this.nextWave = nextWave; }
 
 
   
    public Rectangle getTower1() { return this.tower1; }
 
 
   
    public void setTower1(Rectangle tower1) { this.tower1 = tower1; }
 
 
   
    public Rectangle getTower2() { return this.tower2; }
 
 
   
    public void setTower2(Rectangle tower2) { this.tower2 = tower2; }
 
 
   
    public Rectangle getTower3() { return this.tower3; }
 
 
   
    public void setTower3(Rectangle tower3) { this.tower3 = tower3; }
 
 
   
    public Rectangle getTower4() { return this.tower4; }
 
 
   
    public void setTower4(Rectangle tower4) { this.tower4 = tower4; }
   
   public void setTowerInfoString(String info1, String info2, String info3) {
      this.towerInfo1 = info1;
      this.towerInfo2 = info2;
      this.towerInfo3 = info3;
   }
   
   public int getCurrentWave() {
      if (this.currentWave == 0) {
        return this.currentWave;
     }
      return this.currentWave - 1;
   }
   
    public int getAmountOfGold() { return this.amountOfGold; }
 
 
   
    public void setTowerClicked(boolean state) { this.towerClicked = state; }
 
 
   
    public String getTowerClickedOne() { return this.towerClickedOne; }
 
 
   
    public void setTowerClickedOne(String towerClickedOne) { this.towerClickedOne = towerClickedOne; }
 
 
   
    public String getTowerClickedTwo() { return this.towerClickedTwo; }
 
 
   
    public void setTowerClickedTwo(String towerClickedTwo) { this.towerClickedTwo = towerClickedTwo; }
 
 
   
    public String getTowerClickedThree() { return this.towerClickedThree; }
 
 
   
    public void setTowerClickedThree(String towerClickedThree) { 
			this.towerClickedThree = towerClickedThree; }
 
   
    public String getTowerLevel() { return this.towerLevel; }
 
   
    public void setTowerLevel(String towerLevel) { this.towerLevel = towerLevel; }
 
   
    public Rectangle getTowerSell() { return this.towerSell; }
 
   
    public Rectangle getTowerUpgrade() { return this.towerUpgrade; }
 
   
    public boolean getTowerClicked() { return this.towerClicked; }
 
   
    public void setTowerSellColorBlack() { this.tSell = Color.BLACK; }
 
 
   
    public void setTowerSellColorGray() { this.tSell = Color.GRAY; }
 
   
    public void setTowerUpgradeColorBlack() { this.tUpgrade = Color.BLACK; }
 
   
    public void setTowerUpgradeColorGray() { this.tUpgrade = Color.GRAY; }
 
   
    public void setTowerUpgradeCost(int towerUpgradeCost) { 
			this.towerUpgradeCost = towerUpgradeCost; }
 
   
    public void setTowerSellAmount(int towerSellAmount) { this.towerSellAmount = towerSellAmount; }
 
   
    public void setIsTowerMaxLevel(boolean state) { this.isTowerMaxLevel = state; }
 
 
   
    public Rectangle getSoundOnOffBounds() { return this.soundOnOffBounds; }
 
 
   
    public void setSoundOnOffBounds(Rectangle soundOnOffBounds) { 
			this.soundOnOffBounds = soundOnOffBounds; }
 
 
   
    public Rectangle getSoundPlusBounds() { return this.soundPlusBounds; }
 
 
   
    public void setSoundPlusBounds(Rectangle soundPlusBounds) { 
			this.soundPlusBounds = soundPlusBounds; }
 
 
   
    public Rectangle getSoundMinusBounds() { return this.soundMinusBounds; }
 
 
   
    public void setSoundMinusBounds(Rectangle soundMinusBounds) { 
			this.soundMinusBounds = soundMinusBounds; }
   
   public void changeMusicOnOff() {
      if (this.musicPaused) {
        this.musicPaused = false;
     } else {
        this.musicPaused = true;
     } 
   }
   
    public boolean getMusicPaused() { return this.musicPaused; }
 
 
   
    public int getLife() { return this.life; }
 
   
    public void setLife(int life) { this.life = life; }
 
 
   
    public int getStartLife() { return 20; }
 
 
   
    public void setCurrentWave(int currentWave) { this.currentWave = currentWave; }
 
 
   
    public void setCountDownStarted(boolean countDownStarted) { 
	this.countDownStarted = countDownStarted; } }
