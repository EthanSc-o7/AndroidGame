package com.example.onemoretime;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Results extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        configureNextButton();

        TextView scoreLabel = (TextView) findViewById(R.id.scoreLabel);
        TextView highScoreLabel = (TextView) findViewById(R.id.highScoreLabel);
        TextView nameScore1 = (TextView) findViewById(R.id.nameScore);
        TextView nameScore2 = (TextView) findViewById(R.id.nameScore2);
        TextView nameScore3 = (TextView) findViewById(R.id.nameScore3);


        int score = getIntent().getIntExtra("SCORE", 0);
        String name = getIntent().getStringExtra("NAME");
        scoreLabel.setText( ""+ score);


        SharedPreferences settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        int highScore = settings.getInt("HIGH_SCORE", 0);


        String secondPlace = settings.getString("SECOND_NAME", "Mario");
        String thirdPlace = settings.getString("THIRD_NAME", "Peach");

        int luigiScore = highScore;
        int score1 = settings.getInt("SECOND_SCORE", 0);
        int score2 = settings.getInt("THIRD_SCORE", 0);
        nameScore1.setText("#1 LUIGI : " + luigiScore);

        if(score > highScore){

            luigiScore = score + 1;
            highScoreLabel.setText("High Score : " + luigiScore );



            nameScore1.setText("#1 LUIGI : " + luigiScore);
            nameScore2.setText("#2 "+ name + " : " + score);
            String oldName = secondPlace;
            nameScore3.setText("#3 "+ oldName + " : " + score1);



            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("SECOND_SCORE", score);
            editor.putString("SECOND_NAME", name);
            editor.putInt("THIRD_SCORE", score1);
            editor.putInt("HIGH_SCORE" , score);
            editor.commit();
        }
        else if(score >= score1){
            nameScore2.setText("#2 "+ name + " : " + score);
            String oldName = secondPlace;
            nameScore3.setText("#3 "+ oldName + " : " + score1);

            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("SECOND_SCORE", score);
            editor.putString("SECOND_NAME", name);
            editor.commit();

        }
        else if(score >= score2 && score < score1){
            nameScore2.setText("#2 " + secondPlace + " : " + score1);
            nameScore3.setText("#3 " + name + " : " + score);
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("THIRD_SCORE", score);
            editor.putString("THIRD_NAME", name);
            editor.commit();
        }


        else {
            highScoreLabel.setText("High Score : " + luigiScore);
            nameScore2.setText("#2 "+ secondPlace + " : " + score1);
            nameScore3.setText("#3 " + thirdPlace + " : " + score2);
        }
    }
    public void configureNextButton() {
        ImageButton nextButton = (ImageButton) findViewById(R.id.imageButton9);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Results.this, MainActivity.class));
            }
        });
    }
}