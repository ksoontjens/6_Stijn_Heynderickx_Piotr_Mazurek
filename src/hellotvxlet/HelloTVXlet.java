package hellotvxlet;


import java.util.Timer;
import javax.tv.xlet.*;
import org.havi.ui.event.*;
import org.dvb.event.*;
import java.awt.event.*;
import org.havi.ui.*;



public class HelloTVXlet implements Xlet,UserEventListener{
    
    static HScene scene = null; //dit hoort bij de klasse niet het object
    static Subject publisher = null;
    Balk balk = null;
    Bal balleke = null;

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
               for(int i=1;i<5;i++){
                   for(int j=1;j<4;j++){
                      Balk blokje = new Balk(110*i,50*j,breedte,hoogte);
                      scene.add(blokje);
                   }
               }
        balleke =  new Bal(25,40,200,200);
        scene.add(balleke);
        publisher.register(balleke);
    
          scene.validate();
        scene.setVisible(true);
           
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
              System.out.println("miauw");
              break;
      
      }
      
      }
    }
    
 
   
}
