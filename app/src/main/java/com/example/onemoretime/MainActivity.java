package com.example.onemoretime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.ToggleButton;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    MediaPlayer music;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        configureNextButton();

        music = MediaPlayer.create(MainActivity.this, R.raw.music);
        music.setLooping(true);
        music.start();
        ToggleButton toggle = (ToggleButton) findViewById(R.id.toggleButton);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int length;
                if (isChecked) {
                    music.pause();
                    music.start();
                    length = music.getCurrentPosition();
                } else {
                    music.pause();
                    length = music.getCurrentPosition();
                }
            }
        });

        toggle.setTextOff("Mute");
        toggle.setTextOn("Unmute");
        toggle.setChecked(true);
    }

    public void configureNextButton() {
        ImageButton nextButton = (ImageButton) findViewById(R.id.playGame);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, matching.class));
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        outState.putInt("possition", music.getCurrentPosition());
        music.pause();
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        int pos = savedInstanceState.getInt("possition");
        music.seekTo(pos);
        super.onRestoreInstanceState(savedInstanceState);
    }




}


