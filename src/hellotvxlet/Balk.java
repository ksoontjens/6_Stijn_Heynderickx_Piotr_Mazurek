/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hellotvxlet;

import java.awt.Color;
import java.awt.Image;

import java.awt.MediaTracker;
import org.havi.ui.HVisible;


public class Balk extends Sprite{

Image mijnImg;

int mx;
int my;
int msizeY;
int msizeX;
    public Balk(int x, int y, int sizeX, int sizeY){
        //moet verplicht op eerste regel de super aanroepen
        super(x,y,sizeX,  sizeY);
    mx= x;
    my = y;
    msizeX = sizeX;
    msizeY = sizeY;
     mijnImg=   this.getToolkit().getImage("spaceship.png");
   MediaTracker mt = new MediaTracker(this);
     mt.addImage(mijnImg, HVisible.NORMAL_STATE);
     try{
         mt.waitForAll();
     }
     catch(Exception ex){
     ex.printStackTrace();
     }
   //  this.setGraphicContent(mijnImg, HVisible.NORMAL_STATE);
    // this.setSize(mijnImg.getWidth(this),mijnImg.getHeight(this));
    
    this.setBackground(Color.BLUE);
    this.setBackgroundMode(HVisible.BACKGROUND_FILL);
     
     }
    
     public void update(int tijd) {
      
      
    }
     
     public void MoveLeft(){
     mx -= 10;
    
     if(mx <= 0){
     mx =0;
     }
      this.setBounds(mx,my,msizeX,msizeY);
     }
     
     
     public void MoveRight(){
     
     mx += 10;
     if(mx >= 520){
     mx =520;
     }
      this.setBounds(mx,my,msizeX,msizeY);
     }
    }
    
    
