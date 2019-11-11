package com.TD.main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

 public class Projectile {
   private int x;
   private int y;
   private int speed;
   private int angle;
   private float yVelocity;
   private float xVelocity;
   private int width;
   
   public Projectile(int x, int y, int speed, int typeOfProjectile, 
			int angle, Enemy target, boolean freeze) {
      this.yVelocity = 0.0F; this.xVelocity = 0.0F;
     
      this.arrowNotDead = true;
      this.freeze = false;
     
      this.loader = new BufferImageLoader("spritesheet.png");
   
      this.x = x;
      this.y = y;
      this.speed = speed;
      this.angle = angle;
      this.target = target;
      this.freeze = freeze;
 
     
      if (typeOfProjectile == 1) {
       
        this.width = 8;
        this.height = 14;
        this.projectileImg = this.loader.grabSpriteAtASpecificCords(7, 4, this.width, this.height, 0, 0);
     }
      else if (typeOfProjectile == 2) {
       
        this.width = 14;
        this.height = 14;
        this.projectileImg = this.loader.grabSpriteAtASpecificCords(7, 5, this.width, this.height, 0, 0);
      } else if (typeOfProjectile == 3) {
       
        this.width = 5;
        this.height = 20;
        this.projectileImg = this.loader.grabSpriteAtASpecificCords(7, 4, this.width, this.height, 15, 0);
      } else if (typeOfProjectile == 4) {
       
        this.width = 12;
        this.height = 20;
        this.projectileImg = this.loader.grabSpriteAtASpecificCords(7, 4, this.width, this.height, 20, 0);
     } 
 
     
      this.rectangle = new Rectangle(x, y, this.width, this.height);
      calculateDirection();
   }
   private int height; private Rectangle rectangle; private boolean arrowNotDead; private boolean freeze; private BufferedImage projectileImg; private BufferImageLoader loader; private Enemy target;
   private void calculateDirection() {
      float xDistanceFromTarget = Math.abs(this.target.getX() + 16 - this.x);
      float yDistanceFromTarget = Math.abs(this.target.getY() + 16 - this.y);
      float totalDistanceFromTaget = xDistanceFromTarget + yDistanceFromTarget;
      float xPercentOfMovement = xDistanceFromTarget / totalDistanceFromTaget;
      this.xVelocity = xPercentOfMovement;
      this.yVelocity = 1.0F - xPercentOfMovement;
     
      if (this.target.getX() < this.x)
        this.xVelocity *= -1.0F; 
      if (this.target.getY() < this.y) {
        this.yVelocity *= -1.0F;
     }
   }
 
 
   
   public void move() {
      increaseX(this.xVelocity * this.speed);
      increaseY(this.yVelocity * this.speed);
   }
 
   
   public void draw(Graphics g) {
      AffineTransform at = AffineTransform.getTranslateInstance(this.rectangle.getX(), this.rectangle.getY());
      at.rotate(Math.toRadians(this.angle));
      Graphics2D g2d = (Graphics2D)g;
      g2d.setColor(Color.BLACK);
     
      g2d.drawImage(this.projectileImg, at, null);
   }
 
 
 
   
    private void increaseX(float xSpeed) { this.rectangle.x = (int)(this.rectangle.x + xSpeed); }
 
 
   
    private void increaseY(float ySpeed) { this.rectangle.y = (int)(this.rectangle.y + ySpeed); }
 
 
 
   
    public int getX() { return this.x; }
 
 
   
    public void setX(int x) { this.x = x; }
 
 
   
    public int getY() { return this.y; }
 
 
   
    public void setY(int y) { this.y = y; }
 
 
   
    public int getWidth() { return this.width; }
    public void setWidth(int width) { this.width = width; }
    public int getHeight() { return this.height; }
    public void setHeight(int height) { this.height = height; }   
    public Rectangle getRectangle() { return this.rectangle; }
  
    public void setRectangle(Rectangle rectangle) { this.rectangle = rectangle; }
 
 
   
    public void setArrowDead() { this.arrowNotDead = false; }
 
   
    public boolean getArrowNotDeadState() { return this.arrowNotDead; }
 }
