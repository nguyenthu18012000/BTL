package com.TD.main;

        import java.awt.image.BufferedImage;
        import java.io.IOException;
        import java.net.URL;
        import javax.imageio.ImageIO;
        
        
        public class BufferImageLoader
        {
          private int spriteSize = 32;
          private BufferedImage img = null;
 
          public BufferImageLoader(String spriteName) {
            URL url = getClass().getResource(spriteName);
            
            try {
              this.img = ImageIO.read(url);
            } catch (IOException e) {
              e.printStackTrace();
              System.out.println("Det faila här uppe!");
            } 
          }
 
          public BufferedImage grabSprite(int xLocation, int yLocation) {
            int x = xLocation * this.spriteSize - this.spriteSize;
            int y = yLocation * this.spriteSize - this.spriteSize;
   
            BufferedImage sprite = this.img.getSubimage(x, y, this.spriteSize, this.spriteSize);
            return sprite;
          }
 
          public BufferedImage grabBigSprite(int xLocation, int yLocation, int width, int height) {
            int x = xLocation * this.spriteSize - this.spriteSize;
            int y = yLocation * this.spriteSize - this.spriteSize;
    
            BufferedImage sprite = this.img.getSubimage(x, y, this.spriteSize * width, this.spriteSize * height);
            
            return sprite;
          }

          public BufferedImage grabSpriteAtASpecificCords(int xLocation, int yLocation, int width, int height, int xOffset, int yOffset) {
            int x = xLocation * this.spriteSize - this.spriteSize;
            int y = yLocation * this.spriteSize - this.spriteSize;

            BufferedImage sprite = this.img.getSubimage(x + xOffset, y + yOffset, width, height);
            return sprite;
          }
 
          public BufferedImage getImg() { return this.img; }
        
        
        
          
          public void setImg(BufferedImage img) { this.img = img; }
        }

