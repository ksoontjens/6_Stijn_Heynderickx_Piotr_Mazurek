package hellotvxlet;


import java.awt.Point;
import java.awt.Rectangle;
import java.util.Timer;
import javax.tv.xlet.*;
import org.havi.ui.event.*;
import org.dvb.event.*;
import java.awt.event.*;
import org.havi.ui.*;



public class HelloTVXlet implements Xlet,UserEventListener, ObserverInterface{
    
    static HScene scene = null; //dit hoort bij de klasse niet het object
    static Subject publisher = null;
    Balk balk = null;
    Bal balleke = null;
    Rectangle[][] blokjesbounds = new Rectangle[5][3];
    Balk[][] blokjes = new Balk[5][3];

   public static HScene getScene(){
    return scene;
    }
    public static Subject getPublisher(){
    return publisher;
    }
    public void destroyXlet(boolean unconditional) throws XletStateChangeException {
        
    }

    public void initXlet(XletContext ctx) throws XletStateChangeException {
        scene  = HSceneFactory.getInstance().getDefaultHScene();
        
         publisher = new Subject();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(publisher,0,10); //elke 10ms
        
        balk = new Balk(180,450,200,20);
        scene.add(balk);
        int breedte = 60;
        int hoogte = 40;
               for(int i=0;i<4;i++){
                   for(int j=0;j<3;j++){
                      Balk blokje = new Balk(110+110*i,50+50*j,breedte,hoogte);
                      scene.add(blokje);
                      blokjesbounds[i][j] = blokje.getRect();
                      blokjes[i][j] = blokje;
                   }
               }
        balleke =  new Bal(25,40,40,40);
        scene.add(balleke);
        scene.validate();
        scene.setVisible(true);
        publisher.register(this);
            }

    public void pauseXlet() {
        
    }

    public void startXlet() throws XletStateChangeException {
        //event manager aanvragen
        EventManager  manager = EventManager.getInstance();
        UserEventRepository repos = new UserEventRepository("Keys");
        repos.addAllArrowKeys();
        repos.addKey(HRcEvent.VK_SPACE);
        manager.addUserEventListener(this, repos);     
    }

    
    public void userEventReceived(UserEvent e) {
      if(e.getType() == KeyEvent.KEY_PRESSED){
      switch(e.getCode()){
          case HRcEvent.VK_LEFT: 
              balk.MoveLeft();
              break;
           case HRcEvent.VK_RIGHT: 
              balk.MoveRight();
              break;   
            case HRcEvent.VK_SPACE:  
               publisher.register(balleke);
              break;
            }
      
      }
    }
    
    public void CollideBalk(){
        Rectangle balkbounds = balk.getRect();
        Rectangle ballekebounds = balleke.getRect();
        if(ballekebounds.intersects(balkbounds))
        {
            balleke.setYDir(-1);
        }
        for(int i=0;i<4;i++){
            for(int j=0;j<3;j++){
                    if(ballekebounds.intersects(blokjesbounds[i][j])){
                        int ballLeft =  ballekebounds.x;
                        int ballHeight =  ballekebounds.height;
                        int ballWidth =  ballekebounds.width;
                        int ballTop =  ballekebounds.y;

                        Point pointRight = new Point(ballLeft + ballWidth + 1, ballTop);
                        Point pointLeft = new Point(ballLeft - 1, ballTop);
                        Point pointTop = new Point(ballLeft, ballTop - 1);
                        Point pointBottom = new Point(ballLeft, ballTop + ballHeight + 1);
                        if(!blokjes[i][j].isDestroyed()){
                            if (blokjesbounds[i][j].contains(pointRight)) {
                            balleke.setXDir(-1);
                            } else if (blokjesbounds[i][j].contains(pointLeft)) {
                            balleke.setXDir(1);
                            }

                            if (blokjesbounds[i][j].contains(pointTop)) {
                            balleke.setYDir(1);
                            } else if (blokjes[i][j].contains(pointBottom)) {
                            balleke.setYDir(-1);
                            }
                        }
                        
                        blokjes[i][j].setDestroyed(true);
                        scene.remove(blokjes[i][j]);
                        scene.repaint();
                    }
            }
        }
    }

    public void update(int tijd) {
       CollideBalk();
    }
 
   
}
