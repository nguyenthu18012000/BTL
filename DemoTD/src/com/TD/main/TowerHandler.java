 package com.TD.main;
 
 import java.util.ArrayList;
 import java.util.List;

import com.TD.main.graphics.MainRender;
 
 public class TowerHandler
 {
   private Tower tower;
   private Mover mover;
   private MainRender render;
   private Game game;
   
   public TowerHandler(Mover mover, MainRender render, Game game) {
      this.towerMap = new int[15][20];
      this.towerNumber = 1;
      this.towers = new ArrayList<>();
 
     
      this.mover = mover;
      this.render = render;
      this.game = game;
      this.map = render.getMap();
     
      for (int y = 0; y < this.towerMap.length; y++) {
        for (int x = 0; x < (this.towerMap[y]).length; x++)
          this.towerMap[y][x] = 0; 
     } 
   }
   
   private int[][] map;
   private int[][] towerMap;
   
   public void tower(int x, int y, int typeOfTower) {
      this.tower = new Tower(x, y, typeOfTower, this.game.getWaveManager().getCurrentWave().getEnemyList(), this.towerNumber);
      this.towers.add(this.tower);
      System.out.println("Tower Spawned (TH Class)");
      this.mover.receiveTower(this.tower);
      this.render.receiveTower(this.tower);
      this.game.getWaveManager().receiveTower(this.tower);
      this.towerMap[y / 32][x / 32] = this.towerNumber;
      this.towerNumber++;
   }
   private int towerNumber; private List<Tower> towers;
   
   public boolean checkIfTowerCanBePlacedHere(int x, int y) {
      if (this.towerMap[y / 32][x / 32] == 0 && this.map[y / 32][x / 32] == 0) {
        return false;
     }
      return true;
   }
   
   public int findIfTowerIsHere(int x, int y) {
      x /= 32;
      y /= 32;
     
      return this.towerMap[y][x];
   }
 
   
   public Tower getTowerAtLoc(int x, int y) {
      for (Tower t : this.towers) {
        if (t.getX() / 32 == x / 32 && t.getY() / 32 == y / 32) {
          return t;
       }
     } 
     
      System.out.println("it failed here. TH class");
      return this.tower;
   }
 
   
    public int[][] getMap() { return this.map; }
 
 
   
    public void setMap(int[][] map) { this.map = map; }
 
 
   
    public int[][] getTowerMap() { return this.towerMap; }
 
 
   
    public void setTowerMap(int[][] towerMap) 
    { this.towerMap = towerMap; }
 
 
   
    public int getTowerNumber() 
    { return this.towerNumber; }
 
 
   
    public void setTowerNumber(int towerNumber) 
    { this.towerNumber = towerNumber; }
 
 
   
    public List<Tower> getTowers() 
    { return this.towers; }
 
 
   
    public void setTowers(List<Tower> towers) 
    { this.towers = towers; }
 
   
   public void removeTowerHere(int x, int y) {
      x /= 32;
      y /= 32;
     
      this.towerMap[y][x] = 0;
   }
 }
