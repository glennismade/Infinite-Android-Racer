package uk.ac.reading.sis05kol.mooc;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by glennhealy on 18/04/2016.
 */
public class myTimer extends Object {

   Timer timer = new Timer();

   int secondsPassed =0;
   TimerTask mytask = new TimerTask() {
      @Override
      public void run() {
         secondsPassed++;
      }
   };

   public void startTimer(){

      timer.scheduleAtFixedRate(mytask, 1000, 1000);
   }

}
