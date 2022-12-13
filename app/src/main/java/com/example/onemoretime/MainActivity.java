package com.example.onemoretime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.ToggleButton;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private Spinner spinner;
    private static final String[] paths = {"Cards 4 (Baby)","Cards 6 (Toodler)", "Cards 8 (Easy)","Cards 10 (Medium)","Cards 12 (Hard)",
            "Cards 14 (Advanced)","Cards 16 (Expert)","Cards 18 (Master)", "Cards 20 (Tony Diaz)"};
    MediaPlayer music;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        configureNextButton(4);

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

        spinner = (Spinner)findViewById(R.id.cards);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:
                configureNextButton(4);
                break;
            case 1:
                configureNextButton(6);
                break;
            case 2:
                configureNextButton(8);
                break;
            case 3:
                configureNextButton(10);
                break;
            case 4:
                configureNextButton(12);
                break;
            case 5:
                configureNextButton(14);
                break;
            case 6:
                configureNextButton(16);
                break;
            case 7:
                configureNextButton(18);
                break;
            case 8:
                configureNextButton(20);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void configureNextButton(int i) {
        ImageButton nextButton = (ImageButton) findViewById(R.id.playGame);
        if (i == 4) {
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this, matching3.class));
                }
            });
        }
        if (i == 6) {
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this, matching6.class));
                }
            });
        }
        if (i == 8) {
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this, matching.class));
                }
            });
        }
        if (i == 10) {
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this, matching10.class));
                }
            });
        }
        if (i == 12) {
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this, matching12.class));
                }
            });
        }
        if (i == 14) {
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this, matching14.class));
                }
            });
        }
        if (i == 16) {
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this, matching16.class));
                }
            });
        }
        if (i == 18) {
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this, matching18.class));
                }
            });
        }
        if (i == 20) {
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this, matching20.class));
                }
            });
        }
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


