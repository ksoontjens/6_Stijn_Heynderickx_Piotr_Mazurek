/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hellotvxlet;

import java.awt.Image;
import java.awt.MediaTracker;
import java.util.Random;
import org.havi.ui.HVisible;

/**
 *
 * @author student
 */
public class EnemeyRocket extends Sprite{
int richting = 1;
    Image mijnimg;
    public EnemeyRocket(int x, int y){
        //moet verplicht op eerste regel de super aanroepen
        super(x,y);
    
     mijnimg=   this.getToolkit().getImage("spaceship.png");
     MediaTracker mt = new MediaTracker(this);
     mt.addImage(mijnimg, 1);
     try{
         mt.waitForAll();
     }
     catch(Exception ex){
     ex.printStackTrace();
     }
     this.setGraphicContent(mijnimg, HVisible.NORMAL_STATE);
     this.setSize(mijnimg.getWidth(this),mijnimg.getHeight(this));
     }
    
     public void update(int tijd) {
      
      int x = this.getX();
      int y = this.getY();
   y++;
  
   if(y>576) //uit scherm
   {
   
   HelloTVXlet.scene.remove(this);
   HelloTVXlet.publisher.unregister(this);
   }
   
   
      this .setLocation(x,y);
        
    }
    }
    
    

