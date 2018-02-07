package uk.ac.reading.sis05kol.mooc;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by glennhealy on 03/03/2016.
 */
public class mainmenu extends Activity {

    private GameThread myGameThread;
    private GameView myGameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_menu);

        Button startButton = (Button) findViewById(R.id.btnGameStart); //start game button declaration
        Button exitButton = (Button) findViewById(R.id.btnExit); //exit app button declaration
        Button settingsButton = (Button) findViewById(R.id.btnSettings); //this is the settings button declaration
        Button ScoresButton = (Button) findViewById(R.id.btnScores);


        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(mainmenu.this, MainActivity.class));
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(mainmenu.this, SettingsActivity.class));
            }
        });


        ScoresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mainmenu.this, ScoreActivity.class));
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() { //this chunk of code exits the app completely
            @Override
            public void onClick(View v) {
                finish();
                System.exit(1);
            }
        });



    }


}
