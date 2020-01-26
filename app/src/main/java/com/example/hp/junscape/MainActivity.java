package com.example.hp.junscape;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.opengl.Visibility;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import java.util.Timer;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {

    int count = 0;

    //Image
    private ImageView picStart, picAchievements, picAbout, btnSettings, btnVib, exitCredits, exittt;

    //Vibrator
    Vibrator vibrator;

    private GifImageView arrow;

    private Drawable musicon, musicoff, vibon, viboff;

    //Score
    private TextView highScoreLabel;
    private int score = 0, highScore;
    private SharedPreferences settings;

    //Sound players
    private SoundPlayer sound;
    MediaPlayer mainBGMusic, homeBGMusic;
    public static MediaPlayer mainBGMUSIC, homeBGMUSIC;

    //Button
    private ImageView  btnMute, btnBack, credits;

    private RelativeLayout settingsFrame, creditsLayout;

    private boolean condition;
    boolean isPlaying = false;
    boolean isVib = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            condition = getIntent().getBooleanExtra("MUTE2", false);
            if (condition == false) {
                mainBGMusic.setVolume(0, 0);
                homeBGMusic.setVolume(0, 0);
            } else {
                mainBGMusic.setVolume(1, 1);
                homeBGMusic.setVolume(1, 1);
            }

        } catch (Exception e) {
//            Toast.makeText(getApplicationContext(), condition + " " + e.getMessage(), Toast.LENGTH_LONG).show();
        }


        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        sound = new SoundPlayer(this);
        mainBGMusic = MediaPlayer.create(this, R.raw.maingamesound);
        homeBGMusic = MediaPlayer.create(this, R.raw.homesound);
        mainBGMUSIC = mainBGMusic;
        homeBGMUSIC = homeBGMusic;

        arrow = (GifImageView) findViewById(R.id.arrow);
        picStart = (ImageView) findViewById(R.id.picStart);
        picAchievements = (ImageView) findViewById(R.id.picAchievements);
        picAbout = (ImageView) findViewById(R.id.picAbout);

        musicon = getResources().getDrawable(R.drawable.musicon);
        musicoff = getResources().getDrawable(R.drawable.musicoff);
        vibon = getResources().getDrawable(R.drawable.vibon);
        viboff = getResources().getDrawable(R.drawable.viboff);

        //Button
        btnSettings = (ImageView) findViewById(R.id.btnSettings);
        btnMute = (ImageView) findViewById(R.id.btnMute);
        btnBack = (ImageView) findViewById(R.id.btnBack);
        btnVib = (ImageView) findViewById(R.id.btnVib);
        credits = (ImageView) findViewById(R.id.credits);
        exitCredits = (ImageView) findViewById(R.id.exitCredits);
        exittt = (ImageView) findViewById(R.id.exittt);

        settingsFrame = (RelativeLayout) findViewById(R.id.SettingsLayout);
        creditsLayout = (RelativeLayout) findViewById(R.id.creditsLayout);

        //Score
        highScoreLabel = (TextView) findViewById(R.id.highScoreLabel);

        //High Score
        settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        highScore = settings.getInt("HIGH_SCORE", 0);
        highScoreLabel.setText("BEST: " + highScore);

        //Music
        homeBGMusic.start();
        homeBGMusic.setLooping(true);

        //Load score
        SharedPreferences myScore = this.getSharedPreferences("myScore", Context.MODE_PRIVATE);
        score = myScore.getInt("score", 0);

        btnSettings.setVisibility(View.VISIBLE);


        picAchievements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, Achievement2.class);
                startActivity(in);
            }
        });


        picStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isPlaying) {
                    sound.playClickedSound();
                }
                homeBGMusic.stop();
                homeBGMusic.prepareAsync();
                Intent intent = new Intent(MainActivity.this, GamePage.class);
                intent.putExtra("MUTE", isPlaying);
                startActivity(intent);
            }
        });


        picAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, About.class);
//                startActivity(intent);



            }
        });

        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                settingsFrame.setVisibility(View.VISIBLE);

                picStart.setVisibility(View.GONE);
                picAbout.setVisibility(View.GONE);
                picAchievements.setVisibility(View.GONE);
                btnSettings.setVisibility(View.GONE);

                btnMute.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isPlaying == false) {
                            mainBGMusic.setVolume(0, 0);
                            homeBGMusic.setVolume(0, 0);
                            btnMute.setImageDrawable(musicoff);
                            isPlaying = true;
                        } else {
                            mainBGMusic.setVolume(1, 1);
                            homeBGMusic.setVolume(1, 1);
                            btnMute.setImageDrawable(musicon);
                            isPlaying = false;
                        }
                    }
                });

                btnVib.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isVib) {
                            btnVib.setImageDrawable(vibon);
                            isVib = false;
                        } else {
                            isVib = true;
                            btnVib.setImageDrawable(viboff);
                        }

                    }
                });
            }
        });




        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settingsFrame.setVisibility(View.GONE);
                picStart.setVisibility(View.VISIBLE);
//                picAbout.setVisibility(View.VISIBLE);
                picAchievements.setVisibility(View.VISIBLE);
                btnSettings.setVisibility(View.VISIBLE);
            }
        });

        credits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                creditsLayout.setVisibility(View.VISIBLE);

                exitCredits.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        creditsLayout.setVisibility(View.INVISIBLE);
                    }
                });

            }
        });


        exittt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });

    }



    @Override
    public void onBackPressed() {
        count++;
        Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();
        if (count == 2) {
            finish();
            System.exit(0);
        }
    }

}