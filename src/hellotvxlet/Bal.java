/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hellotvxlet;


import java.awt.Graphics;
import java.awt.Image;

import java.awt.MediaTracker;
import org.havi.ui.HVisible;


public class Bal extends Sprite{

Image mijnImg;

int mx;
int my;
int msizeY;
int msizeX;
    public Bal(int x, int y, int sizeX, int sizeY){
        //moet verplicht op eerste regel de super aanroepen
        super(x,y,sizeX,  sizeY);
    mx= x;
    my = y;
    msizeX = sizeX;
    msizeY = sizeY;
     mijnImg=   this.getToolkit().getImage("bal.png");
   MediaTracker mt = new MediaTracker(this);
     mt.addImage(mijnImg, HVisible.NORMAL_STATE);
     try{
         mt.waitForAll();
       
     }
     catch(Exception ex){
     ex.printStackTrace();
     
     }
     
     this.setGraphicContent(mijnImg, HVisible.NORMAL_STATE);
  this.setSize(mijnImg.getWidth(null)/16,mijnImg.getHeight(null)/16);
    
  System.out.println("hejkes den bal is back"+mt);
     mt.isErrorAny();
     }
    
     public void update(int tijd) {
      
      
    }
     public void paint(Graphics g){
         g.drawImage(mijnImg,0,0,null);
     }
}
    
    

