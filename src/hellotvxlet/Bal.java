/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hellotvxlet;


import java.awt.*;

import java.awt.MediaTracker;
import org.havi.ui.HVisible;


public class Bal extends Sprite{

Image mijnImg;

int mx;
int my;
int msizeY;
int msizeX;
int richtingX = -1;
int richtingY = -1;
    public Bal(int x, int y, int sizeX, int sizeY){
        //moet verplicht op eerste regel de super aanroepen
        super(x,y,sizeX,  sizeY);
        this.setBordersEnabled(true);
    mx= x;
    my = y;
    msizeX = sizeX;
    msizeY = sizeY;
     mijnImg=   this.getToolkit().getImage("bal.png");
     
      MediaTracker mt = new MediaTracker(this);
     mt.addImage(mijnImg, 1);
     try{
         mt.waitForAll();
       
     }
     catch(Exception ex){
     ex.printStackTrace();
     
     }
     
     this.setBackground(Color.RED);
     //this.setSize(msizeX,msizeY);
     this.setBackgroundMode(HVisible.BACKGROUND_FILL);
     this.setBounds(x, y, msizeX, msizeY);
     System.out.println(mx+" "+my+" "+msizeX+" "+msizeY);
    
     mt.isErrorAny();
     }
    
     public void update(int tijd) {
    //     System.out.println("update bal");
      my = my + richtingY;
      mx = mx + richtingX;
      if(mx<=0){
            richtingX = 1;
        }
      if(mx >= 720-this.getWidth()){
            richtingX= -1;
        }
      if(my<=0){
            richtingY = 1;
        }
      if(my>= 576-this.getHeight()){
            richtingY = -1;
        }
      this.setLocation(mx, my);
    }
    public void changeY()
    {
        richtingY = -1;
    }
}