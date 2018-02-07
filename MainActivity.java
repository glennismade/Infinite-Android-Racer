package uk.ac.reading.sis05kol.mooc;

import android.app.Activity;
import android.content.Intent;
import android.content.Context;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Date;

public class MainActivity extends Activity {


    private static final int MENU_RESUME = 1;
    private static final int MENU_START = 2;
    private static final int MENU_STOP = 3;
    public static final int WIDTH = 200;
    public static final int HEIGHT = 300;


    private GameThread mGameThread;
    private GameView mGameView;

    /** Called when the activity is first created. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.activity_main);

        mGameView = (GameView) findViewById(R.id.gamearea);
        mGameView.setStatusView((TextView) findViewById(R.id.text));
        mGameView.setScoreView((TextView) findViewById(R.id.score));

        this.startGame(mGameView, null, savedInstanceState);

        Button HomeButton = (Button) findViewById(R.id.btnHome);

        HomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
               startActivity(new Intent(MainActivity.this, mainmenu.class));
            }
        });


//        Intent menuIntent = new Intent(getApplicationContext(), mainmenu.class);
//        startActivityForResult(menuIntent, 100);
//
//        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//            super.onActivityResult(requestCode, resultCode, data);
//            if(resultCode == 100){
//                startActivity(menuIntent);
//            }
//            finish();
//
//        }

//        ImageView img_animation = (ImageView) findViewById(R.id.imgTrack);
//
//        TranslateAnimation animation = new TranslateAnimation(0.0f, 0.0f,
//                100.0f, 100.0f);
//        animation.setDuration(5000);
//        animation.setRepeatCount(5);
//        animation.setRepeatMode(2);
//
//        img_animation.startAnimation(animation);

}

    private void startGame(GameView gView, GameThread gThread, Bundle savedInstanceState) {

        //Set up a new game, we don't care about previous states
        mGameThread = new TheGame(mGameView);
        mGameView.setThread(mGameThread);
        mGameThread.setState(GameThread.STATE_READY);
        mGameView.startSensor((SensorManager)getSystemService(Context.SENSOR_SERVICE));

    }

 // @Override
    public void onRunning(View v){
     //super.onRunning();


        if(mGameThread.getMode() == GameThread.STATE_RUNNING) { //pause the game if its running
           mGameThread.pause();
        }

        else if(mGameThread.getMode() == GameThread.STATE_PAUSE) { //unpause the game if its running
            mGameThread.unpause();

        }

}

	/*
	 * Activity state functions
	 */

    @Override
    protected void onPause() {
        super.onPause();

        if(mGameThread.getMode() == GameThread.STATE_RUNNING) {
            mGameThread.setState(GameThread.STATE_PAUSE);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        mGameView.cleanup();
        mGameView.removeSensor((SensorManager)getSystemService(Context.SENSOR_SERVICE));
        mGameThread = null;
        mGameView = null;
    }
    
    /*
     * UI Functions
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        menu.add(0, MENU_START, 0, R.string.menu_start);
        menu.add(0, MENU_STOP, 0, R.string.menu_stop);
        menu.add(0, MENU_RESUME, 0, R.string.menu_resume);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_START:
                mGameThread.doStart();
                return true;
            case MENU_STOP:
                mGameThread.setState(GameThread.STATE_LOSE,  getText(R.string.message_stopped));
                return true;
            case MENU_RESUME:
                mGameThread.unpause();
                return true;
        }

        return false;
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // Do nothing if nothing is selected
    }



}
