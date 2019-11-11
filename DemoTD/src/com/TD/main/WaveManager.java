 package com.TD.main;
 
 import java.util.ArrayList;
 import java.util.List;

import com.TD.main.graphics.MainRender;
 
 public class WaveManager
 {
   private Mover mover;
   private MainRender render;
   private HUD hud;
   
   public WaveManager(Mover mover, MainRender render, HUD hud) {
      this.waves = new ArrayList<>();
      this.towers = new ArrayList<>();
      this.waveNr = 0;
 
 
     
      this.mover = mover;
      this.render = render;
      this.hud = hud;
     
      createListOfWaves();
   }
 
   
   private List<Wave> waves;
   private List<Tower> towers;
   private int waveNr;
   
   private void createListOfWaves() {
      this.waves.add(new Wave(5, 5, 1, 0, 6, 1, 0, 25, 1, this.mover, this.render, this.towers, this.hud));
      this.waves.add(new Wave(5, 5, 1, 2, 6, 1, 0, 25, 1, this.mover, this.render, this.towers, this.hud));
      this.waves.add(new Wave(10, 8, 1, 4, 8, 1, 1, 25, 1, this.mover, this.render, this.towers, this.hud));
      this.waves.add(new Wave(10, 8, 1, 4, 8, 1, 0, 25, 1, this.mover, this.render, this.towers, this.hud));
      this.waves.add(new Wave(15, 8, 1, 8, 8, 1, 4, 25, 1, this.mover, this.render, this.towers, this.hud));
 
     
      this.waves.add(new Wave(5, 11, 1, 10, 13, 1, 0, 25, 1, this.mover, this.render, this.towers, this.hud));
      this.waves.add(new Wave(5, 11, 1, 13, 13, 1, 0, 25, 1, this.mover, this.render, this.towers, this.hud));
      this.waves.add(new Wave(7, 15, 1, 15, 18, 1, 0, 25, 1, this.mover, this.render, this.towers, this.hud));
      this.waves.add(new Wave(10, 15, 1, 20, 18, 1, 2, 40, 1, this.mover, this.render, this.towers, this.hud));
      this.waves.add(new Wave(13, 15, 1, 20, 18, 1, 5, 40, 1, this.mover, this.render, this.towers, this.hud));
 
     
      this.waves.add(new Wave(15, 20, 2, 10, 25, 2, 0, 20, 2, this.mover, this.render, this.towers, this.hud));
      this.waves.add(new Wave(15, 20, 2, 13, 25, 2, 0, 20, 2, this.mover, this.render, this.towers, this.hud));
      this.waves.add(new Wave(17, 20, 2, 15, 25, 2, 0, 20, 2, this.mover, this.render, this.towers, this.hud));
      this.waves.add(new Wave(20, 25, 2, 20, 30, 2, 2, 55, 2, this.mover, this.render, this.towers, this.hud));
      this.waves.add(new Wave(23, 25, 2, 20, 30, 2, 5, 55, 2, this.mover, this.render, this.towers, this.hud));
 
     
      this.waves.add(new Wave(5, 30, 1, 0, 36, 1, 0, 20, 1, this.mover, this.render, this.towers, this.hud));
      this.waves.add(new Wave(5, 30, 4, 3, 36, 1, 2, 80, 1, this.mover, this.render, this.towers, this.hud));
      this.waves.add(new Wave(7, 30, 1, 5, 40, 1, 5, 80, 1, this.mover, this.render, this.towers, this.hud));
 
     
      this.waves.add(new Wave(30, 45, 1, 15, 55, 1, 5, 100, 1, this.mover, this.render, this.towers, this.hud));
      this.waves.add(new Wave(45, 45, 1, 25, 55, 2, 10, 120, 1, this.mover, this.render, this.towers, this.hud));
 
 
 
     
      this.render.setTotalOfWaves(this.waves.size());
   }
 
 
   
   public void send() {
      if (this.waves.size() > this.waveNr) {
        ((Wave)this.waves.get(this.waveNr)).sendWave();
       
        this.waveNr++;
        this.render.increaseWaveNrByOne();
     } else {
        System.out.println("slut på waves");
     } 
   }
 
 
   
   public void resetWavesAndTowers() {
      for (int i = this.waves.size() - 1; i > -1; i--) {
        this.waves.remove(i);
     }
     
      for (int i = this.towers.size() - 1; i > -1; i--) {
        this.towers.remove(i);
     }
      createListOfWaves();
   }
   
   public int getWaveSize() {
      System.out.println("waveNR: " + this.waveNr + "   Wave Size: " + ((Wave)this.waves.get(this.waveNr)).getSizeOfWave());
      return ((Wave)this.waves.get(this.waveNr)).getSizeOfWave();
   }
 
   
    public int getWaveNr() { return this.waveNr; }
 
 
   
    public Wave getCurrentWave() { return this.waves.get(this.render.getHud().getCurrentWave()); }
 
   
    public void receiveTower(Tower tower) { this.towers.add(tower); }
 
 
   
    public List<Tower> getTowers() { return this.towers; }
 
 
   
    public void setTowers(List<Tower> towers) { this.towers = towers; }
 
 
   
    public void setWaveNr(int waveNr) { this.waveNr = waveNr; }
 
 
   
    public int getWavesSize() { return this.waves.size(); }
 }
