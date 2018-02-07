package uk.ac.reading.sis05kol.mooc;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileReader;

import uk.ac.reading.sis05kol.mooc.TheGame;

/**
 * Created by glennhealy on 18/04/2016.
 */
public class ScoreActivity extends Activity  {

    private GameThread myGameThread;
    private GameView myGameView;

//    BufferedReader fileReader = new BufferedReader(new FileReader("score.txt"));
//
//    StringBuilder strBuilder = new StringBuilder();
//
//    public String line;
//
//    while((line = fileReader.readLine()) != null)
//    {
//        strBuilder.append(line);
//    }
//
//    fileReader.close();
//
//    strBuilder.trimToSize();
//
//    String contentsOfFile = strBuilder.toString();

  @Override
    public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);

      setContentView(R.layout.activity_score);

      TextView Score1 = (TextView) findViewById(R.id.txtScore1);


     // Score1.setText(contentsOfFile);

  }



}