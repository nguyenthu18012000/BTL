 package com.TD.main;
 
 import java.awt.Canvas;
 import java.awt.Dimension;
 import java.awt.Graphics;
 import java.awt.event.KeyEvent;
 import java.awt.event.MouseEvent;
 import java.awt.image.BufferStrategy;
 import java.awt.image.BufferedImage;
 import java.util.ArrayList;
 import java.util.List;
 import javax.swing.JFrame;

import com.TD.main.graphics.MainRender;
 
 public class Game
   extends Canvas
   implements Runnable
 {
   BufferImageLoader loader = new BufferImageLoader("spritesheet.png");
   List<Enemy> enemies = new ArrayList<>();
   
   private int counter = 0;
   
   private boolean running = false;
   
   private boolean started = false;
   
   private boolean gameWon = false;
 
   
   private boolean waveCountDown = false;
 
   
   private boolean gameOver = false;
 
   
   private JFrame f = new JFrame(); private static final long serialVersionUID = 1L; public static final int WIDTH = 640; public static final int HEIGHT = 576; public static final String TITLE = "TD Game"; public static final double TARGET_UPS = 60.0D; private Thread thread; private MainRender render; private HUD hud; public Game() {
     Dimension dimension = new Dimension(640, 576);
     setPreferredSize(dimension);
     setMaximumSize(dimension);
     setMinimumSize(dimension);
     
     this.hud = new HUD(0, 480, 640, 96, this);
     this.mover = new Mover(this, this.hud);
     this.tempTowerAtMouseLoc = new TempTowerAtMouseLoc();
     this.render = new MainRender(this.hud, this.tempTowerAtMouseLoc);
     this.waveManager = new WaveManager(this.mover, this.render, this.hud);
     this.enemySprite = this.loader.grabSprite(1, 2);
     this.mover.receiveMap(this.render.getMap());
     this.mover.getRenderObj(this.render);
     this.towerHandler = new TowerHandler(this.mover, this.render, this);
     this.buttonsClicked = new ButtonsClicked(this.mover, this.render, this.waveManager, this.hud, this.towerHandler, this);
     this.buttonsHoveredOver = new ButtonsHoveredOver();
     
     this.mouseMotion = new MouseMotion(this);
     this.mouseInputs = new MouseInputs(this);
     this.buttonsHoveredOver.getHud(this.render.getHud());
     this.buttonsClicked.getHud(this.render.getHud());
   }
   private Mover mover; private WaveManager waveManager; private TowerHandler towerHandler; private MouseMotion mouseMotion; private MouseInputs mouseInputs; private ButtonsClicked buttonsClicked; private ButtonsHoveredOver buttonsHoveredOver; private TempTowerAtMouseLoc tempTowerAtMouseLoc; BufferedImage enemySprite;
   
   private synchronized void start() {
     if (this.running) {
       return;
     }
     this.running = true;
     this.thread = new Thread(this);
     this.thread.start();
   }
 
   
   private synchronized void stop() {
     if (!this.running) {
       return;
     }
     this.running = false;
     try {
       this.thread.join();
     } catch (InterruptedException e) {
       e.printStackTrace();
     } 
     System.exit(1);
   }
 
   
   public void run() {
     initialize();
     
     long last = System.nanoTime();
     double ns = 1.6666666666666666E7D;
     double delta = 0.0D;
     int updates = 0;
     int frames = 0;
     long timer = System.currentTimeMillis();
     
     while (this.running) {
       long now = System.nanoTime();
       delta += (now - last) / ns;
       last = now;
       
       if (delta >= 1.0D) {
         update();
         updates++;
         delta--;
       } 
       render();
       frames++;
       
       if (System.currentTimeMillis() - timer > 1000L) {
         timer += 1000L;
         this.f.setTitle("Tower Defense | UPS: " + updates + "  FPS: " + frames);
         updates = 0;
         frames = 0;
       } 
     } 
     
     stop();
   }
 
 
   
   public void update() {
     if (!this.gameOver) {
       this.mover.move();
     }
     if (this.waveCountDown && !this.gameOver) {
       this.counter++;
       if (this.counter >= 6) {
         this.hud.checkTimer();
         this.counter = 0;
       } 
     } 
   }
 
 
 
   
   public void render() {
     BufferStrategy bs = getBufferStrategy();
     if (bs == null) {
       createBufferStrategy(3);
       return;
     } 
     Graphics g = bs.getDrawGraphics();
 
 
     
     this.render.render(g);
 
     
     g.dispose();
     bs.show();
   }
 
 
   
   public void initialize() {
     addKeyListener(new KeyInputs(this));
     addMouseMotionListener(this.mouseMotion);
     addMouseListener(this.mouseInputs);
   }
   
   public void gameOver() {
     this.gameOver = true;
     this.render.setGameOver(true);
   }
 
 
 
 
   
   public static void main(String[] args) {
     Game game = new Game();
     
     game.f.setResizable(false);
     game.f.setTitle("Tower Defense");
     game.f.setVisible(true);
     game.f.add(game);
     game.f.pack();
     game.f.setDefaultCloseOperation(3);
     game.f.setLocationRelativeTo(null);
     
     game.start();
   }
 
 
 
   
   public void keyPressed(KeyEvent e) {
     if ((this.gameOver || !this.started || this.gameWon) && 
       e.getKeyCode() == 10) {
       if (this.gameOver || this.gameWon) {
         this.hud.setCountDownStarted(false);
         this.waveCountDown = false;
         
         if (this.gameWon) {
           this.render.setGameWon(false);
           this.gameWon = false;
           this.mover.setGameWon(false);
         } 
         
         this.towerHandler.setTowerNumber(1);
         this.waveManager.setWaveNr(0);
         this.hud.addGold(-this.hud.getAmountOfGold() + 350);
         this.hud.setLife(this.hud.getStartLife());
         this.hud.setSizeOfWave(0);
         
         for (int i = this.towerHandler.getTowers().size() - 1; i > -1; i--) {
            Tower t = this.towerHandler.getTowers().get(i);
            this.towerHandler.removeTowerHere(t.getX(), t.getY());
            this.towerHandler.getTowers().remove(i);
            this.mover.getTowerList().remove(i);
            this.render.getTowerList().remove(i);
         } 
         
          for (int i = this.render.getEnemies().size() - 1; i > -1; i--) {
           
            this.render.getEnemies().remove(i);
            this.mover.getEnemyList().remove(i);
         } 
         
          this.waveManager.resetWavesAndTowers();
 
         
          for (int i = this.mover.getEnemiesTemp().size() - 1; i > -1; i--) {
            this.mover.getEnemiesTemp().remove(i);
         }
         
          for (int i = this.render.getEnemiesTemp().size() - 1; i > -1; i--) {
            this.render.getEnemiesTemp().remove(i);
         }
         
          this.render.setGameOver(false);
          this.mover.setGameOver(false);
          this.mover.resetJagVetInte();
          this.hud.setCurrentWave(0);
         
          this.gameOver = false;
        } else if (!this.started) {
          this.mover.setStartMenu(false);
          this.render.setStartMenu(false);
          this.buttonsClicked.getSoundClass().play();
          this.buttonsClicked.setGameStartedToTrue();
       } 
     }
   }
 
 
 
 
   
   public void keyReleased(KeyEvent e) {}
 
 
 
 
   
   public void mouseMoved(MouseEvent e) {
      int xx = e.getX();
      int yy = e.getY();
      if (this.render.getTowerClicked()) {
        this.tempTowerAtMouseLoc.setX(xx);
        this.tempTowerAtMouseLoc.setY(yy);
     } 
     
      this.buttonsHoveredOver.testIfMouseIsOver(xx, yy);
   }
   
   public void mouseDragged(MouseEvent e) {
      int x = e.getX();
      int y = e.getY();
     
      this.buttonsHoveredOver.testIfMouseIsOver(x, y);
   }
 
 
   
   public void mouseClicked(MouseEvent e) {}
 
 
   
   public void mouseReleased(MouseEvent e) {
      if (!this.gameOver && !this.gameWon) {
        this.buttonsClicked.testIfAnyButtonIsPressed(e.getX(), e.getY());
     }
     
      if (this.render.getTowerClicked() && e.getButton() == 1 && e.getY() < 480) {
        int type = this.render.getTypeOfTower();
        if (type == 1 && this.hud.getAmountOfGold() >= 150) {
          if (!this.towerHandler.checkIfTowerCanBePlacedHere(this.tempTowerAtMouseLoc.getX(), this.tempTowerAtMouseLoc.getY())) {
            this.towerHandler.tower(this.tempTowerAtMouseLoc.getX(), this.tempTowerAtMouseLoc.getY(), type);
            this.hud.reduceGold(150);
         } else {
            System.out.println("Tower already exists at: " + (this.tempTowerAtMouseLoc.getX() / 32) + " , " + (
                this.tempTowerAtMouseLoc.getY() / 32) + ", or its not a grassTile!");
         } 
        } else if (type == 2 && this.hud.getAmountOfGold() >= 270) {
          if (!this.towerHandler.checkIfTowerCanBePlacedHere(this.tempTowerAtMouseLoc.getX(), this.tempTowerAtMouseLoc.getY())) {
            this.towerHandler.tower(this.tempTowerAtMouseLoc.getX(), this.tempTowerAtMouseLoc.getY(), type);
            this.hud.reduceGold(270);
         } else {
            System.out.println("Tower already exists at: " + (this.tempTowerAtMouseLoc.getX() / 32) + " , " + (
                this.tempTowerAtMouseLoc.getY() / 32) + ", or its not a grassTile!");
         } 
        } else if (type == 3 && this.hud.getAmountOfGold() >= 310) {
          if (!this.towerHandler.checkIfTowerCanBePlacedHere(this.tempTowerAtMouseLoc.getX(), this.tempTowerAtMouseLoc.getY())) {
            this.towerHandler.tower(this.tempTowerAtMouseLoc.getX(), this.tempTowerAtMouseLoc.getY(), type);
            this.hud.reduceGold(310);
         } else {
            System.out.println("Tower already exists at: " + (this.tempTowerAtMouseLoc.getX() / 32) + " , " + (
                this.tempTowerAtMouseLoc.getY() / 32) + ", or its not a grassTile!");
         } 
        } else if (type == 4 && this.hud.getAmountOfGold() >= 400) {
          if (!this.towerHandler.checkIfTowerCanBePlacedHere(this.tempTowerAtMouseLoc.getX(), this.tempTowerAtMouseLoc.getY())) {
            this.towerHandler.tower(this.tempTowerAtMouseLoc.getX(), this.tempTowerAtMouseLoc.getY(), type);
            this.hud.reduceGold(400);
         } else {
            System.out.println("Tower already exists at: " + (this.tempTowerAtMouseLoc.getX() / 32) + " , " + (
                this.tempTowerAtMouseLoc.getY() / 32) + ", or its not a grassTile!");
         } 
       } else {
          System.out.println("not enough gold for tower type: " + type);
       } 
      } else if (this.render.getTowerClicked() && e.getButton() != 1) {
 
       
        this.render.changeTowerClicked(2);
       
        this.tempTowerAtMouseLoc.setX(800);
        this.tempTowerAtMouseLoc.setY(800);
     } 
   }
   
   public void mousePressed(MouseEvent e) {
      this.buttonsClicked.setPressedX(e.getX());
      this.buttonsClicked.setPressedY(e.getY());
   }
 
   
    public WaveManager getWaveManager() { return this.waveManager; }
 
   
   public void gameIsWon() {
      this.render.setGameWon(true);
      this.gameWon = true;
      this.mover.setGameWon(true);
   }
 
   
    public ButtonsHoveredOver getButtonsHoveredOver() { return this.buttonsHoveredOver; }
 
   
    public void setWaveCountDown(boolean waveCountDown) { this.waveCountDown = waveCountDown; }
 }
