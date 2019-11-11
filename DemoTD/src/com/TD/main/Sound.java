package com.TD.main;
 
import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
 import javax.sound.sampled.LineUnavailableException;
 
 
 public class Sound
 {
   private Clip clip;
   private boolean started = false;
   private boolean paused = false;
   private FloatControl volume;
   private float value = 0.0F;
   private long clipTime;
 
 
   
   public void play() {
      URL musicURL = Sound.class.getResource("music.wav");
     
      if (!this.started) {
       try {
          this.clip = AudioSystem.getClip();
          this.clip.open(AudioSystem.getAudioInputStream(musicURL));
        } catch (LineUnavailableException|java.io.IOException|javax.sound.sampled.UnsupportedAudioFileException e) {
          e.printStackTrace();
       } 
        this.clip.loop(-1);
       
        this.volume = (FloatControl)this.clip.getControl(FloatControl.Type.MASTER_GAIN);
        this.started = true;
      } else if (this.paused) {
        this.clip.setMicrosecondPosition(this.clipTime);
        this.clip.loop(-1);
        this.paused = false;
     } 
   }
 
   public void pause() {
      if (this.started) {
        this.clipTime = this.clip.getMicrosecondPosition();
        this.clip.stop();
        this.paused = true;
     } 
   }
   
   public void changeVolume(float value) {
      this.value += value;
      if (this.value > -80.0F && this.value < 7.0F) {
        this.volume.setValue(this.value);
     }
      else if (this.value < -80.0F) {
        this.value = -80.0F;
      } else if (this.value > 6.0F) {
        this.value = 6.0F;
     } 
   }
 }
