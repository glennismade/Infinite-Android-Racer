package uk.ac.reading.sis05kol.mooc;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import javax.xml.transform.Source;

/**
 * Created by glennhealy on 09/04/2016.
 */
public class SettingsActivity extends Activity {


    private GameThread myGameThread;
    private GameView myGameView;
    public final boolean audio = true;

    MediaPlayer Music;


   // public final boolean Checked = false;
   // public int ON, OFF;


    //this method doesnt work currenly. need to figure out how to enable sound files.
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);


        final CheckBox Sound = (CheckBox) findViewById(R.id.chbxSound);

        Sound.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (Sound.isChecked()) {
                    // mediaplayer is already muted, so needs be to unmuted
                    Music.setVolume(1, 1);
                } else {
                    // mute media player
                    Music.setVolume(0, 0);
                }
            }
        });

    }


}
