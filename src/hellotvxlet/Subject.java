/*
 * gaat de observers aansturen
 */

package hellotvxlet;

import java.util.ArrayList;
import java.util.TimerTask;

/**
 *
 * @author student
 */
public class Subject extends TimerTask implements SubjectInterface {
        ArrayList  oblijst = new ArrayList();
        int tijd = 0;
     public void run() {
       
         tijd++;
         for(int i =0;i<oblijst.size();i++){
               //System.out.println("run:"+);
             ((ObserverInterface)oblijst.get(i)).update(tijd);
         }
       
    }

    public void register(ObserverInterface ob) {
        System.out.println("REGISTER: "+ob.toString());
       oblijst.add(ob);
    }

    public void unregister(ObserverInterface ob) {
       oblijst.remove(ob);
    }

}
