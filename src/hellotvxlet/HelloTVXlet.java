package hellotvxlet;


import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Timer;
import javax.tv.xlet.*;
import org.havi.ui.event.*;
import org.dvb.event.*;
import java.awt.event.*;
import java.util.Random;
import org.havi.ui.*;



public class HelloTVXlet implements Xlet,UserEventListener, ObserverInterface, HActionListener{
    
    static HScene scene = null; //dit hoort bij de klasse niet het object
    static Subject publisher = null;
    Balk balk = null;
    Bal balleke = null;
    Balk eindbalk = null;
    Rectangle[][] blokjesbounds = new Rectangle[5][5];
    Balk[][] blokjes = new Balk[5][5];
    Random rand = new Random();
    private HTextButton reset;
    Timer timer;

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
        timer = new Timer();
        timer.scheduleAtFixedRate(publisher,0,10); //elke 10ms
        
        balk = new Balk(180,450,200,20, Color.BLUE);
        scene.add(balk);
        int breedte = 130;
        int hoogte = 30;
               for(int i=0;i<5;i++){
                   for(int j=0;j<5;j++){
                       
                        int r = rand.nextInt(255);
                        int g = rand.nextInt(255);
                        int b = rand.nextInt(255);

                        Color kleur = new Color(r,g,b);
                      Balk blokje = new Balk(30+132*i,40+31*j,breedte,hoogte, kleur);
                      scene.add(blokje);
                      blokjesbounds[i][j] = blokje.getRect();
                      blokjes[i][j] = blokje;
                      
                   }
               }
        balleke =  new Bal(140,400,40,40);
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
        if(ballekebounds.y > 720)
        {
            timer.cancel();
            endGame();
        }
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
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
                                if(balleke.getXDir() == 1){
                                    balleke.setXDir(-1);
                                }
                                else{
                                    balleke.setXDir(1);
                                }
                                      
                            } else if (blokjes[i][j].contains(pointBottom)) {
                                balleke.setYDir(-1);
                                if(balleke.getXDir() == 1){
                                    balleke.setXDir(-1);
                                }
                                else{
                                    balleke.setXDir(1);
                                }
                            }
                        }
                        blokjes[i][j].setDestroyed(true);
                        scene.remove(blokjes[i][j]);
                        scene.repaint();
                    }
            }
        }
    }
    
    public void reset(){
        
    }
    public void endGame(){
        eindbalk = new Balk(110,200,500,200,Color.RED);
        reset = new HTextButton("RESET");
        reset.setLocation(150,300);
        reset.setSize(100,50);
        reset.setBackground(Color.GREEN);
        reset.setBackgroundMode(HVisible.BACKGROUND_FILL);
        reset.setActionCommand("reset");
        reset.addHActionListener(this);
        scene.add(reset);
        scene.add(eindbalk);
        reset.requestFocus();
        scene.validate();
        scene.setVisible(true);
        scene.repaint();
    }
    
    public void update(int tijd) {
       CollideBalk();
    }

    public void actionPerformed(ActionEvent arg0) {
        String action = arg0.getActionCommand();
        if(action.equals("reset")){
            reset();
        } 
    }
 
   
}
