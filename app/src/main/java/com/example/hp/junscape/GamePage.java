package com.example.hp.junscape;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import pl.droidsonroids.gif.GifImageView;

public class GamePage extends AppCompatActivity {
    final String TAG = this.getClass().getName();

    private RelativeLayout settingsFrame;

    //Frame
    private FrameLayout gameFrame;
    private int frameHeight, frameWidth, initialFrameWidth;
    private RelativeLayout startLayout, exitConfirmLayout, pauseConfirmationLayout;

    //Image
    private ImageView trashCan, ivFish, ivBanana, ivCan, ivChips, ivTire, ivLeaf, ivMilk, ivPaper, ivTissue, ivPlastic, ivCork, ivStyro,
            bomb, picStart, picAchievements, picRetry, picExit, picAbout, picBio, picNonBio, picPause, heart3, btnExit, over, btnSettings, exittt,
            heart4, heart5, btnYes, btnNo, btnRestart, btnPlay;
    private Drawable leftBio, rightBio, leftNonBio, rightNonBio, life3, life1, life2, life11, life22, DrEgg;
    private GifImageView plusPoints, plusLife, lifeTwoGif, lifeThreeGif, gifKeyboard, gifJunk, gameFrame2, gameFrame1, arrow;
    Toast toast;

    //Trash Can Size
    private int trashCanSize;

    //vibrator
    Vibrator vibrator;

    //Position (x,y)
    private float trashCanX, trashCanY;
    private float fishX, fishY;
    private float bananaX, bananaY;
    private float canX, canY;
    private float plusPointsX, plusPointsY;
    private float plusLifeX, plusLifeY;
    private float bombX, bombY;
    private float chipsX, chipsY;
    private float tireX, tireY;
    private float leafX, leafY;
    private float milkX, milkY;
    private float paperX, paperY;
    private float plasticX, plasticY;
    private float tissueX, tissueY;
    private float corkX, corkY;
    private float styroX, styroY;

    //Score
    private TextView scoreLabel, highScoreLabel, highScoreLabel2, scoreShow;
    private int score = 0, highScore, timeCount, lives = 3;
    private SharedPreferences settings;

    //show unlock
    private boolean shown = true;
    private boolean canshown = true;
    private boolean cigarshown = true;
    private boolean chipsshown = true;
    private boolean eggsshown = true;
    private boolean fishshown = true;
    private boolean tireshown = true;
    private boolean papershown = true;
    private boolean milkshown = true;
    private boolean leafshown = true;
    private boolean plasticshown = true;
    private boolean teabagshown = true;
    private boolean wireshown = true;
    private boolean tissueshown = true;
    private boolean styroshown = true;
    //Timer
    private Timer timer;
    private Handler handler = new Handler();

    //Sound players
    private SoundPlayer sound;
    private MediaPlayer mainBGMusic, homeBGMusic;

    //Status
    private boolean start_flg = false;
    private boolean action_flg = false;
    private boolean points_flg = false;
    private boolean bio = false;
    private boolean pause_flg = false;

    //Instruction and Chooser
    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;
    private RelativeLayout instructionsFrame, chooserLayout;
    private TextView[] mDots;
    private SliderAdapter sliderAdapter;
    private ImageView mNextBtn;
    private int mCurrentPage;
    private Boolean condition;
    MainActivity mainActivity = new MainActivity();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        condition = getIntent().getBooleanExtra("MUTE", false);
        mSlideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        mDotLayout = (LinearLayout) findViewById(R.id.dotsLayout);
        instructionsFrame = (RelativeLayout) findViewById(R.id.instructionsFrame);
        chooserLayout = (RelativeLayout) findViewById(R.id.chooserLayout);
        exitConfirmLayout = (RelativeLayout) findViewById(R.id.exitConfirmLayout);
        pauseConfirmationLayout = (RelativeLayout) findViewById(R.id.pauseConfirmationLayout);
        mNextBtn = (ImageView) findViewById(R.id.nextBtn);
        sliderAdapter = new SliderAdapter(this);

        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        sound = new SoundPlayer(this);
        mainBGMusic = MainActivity.mainBGMUSIC;
        homeBGMusic = MainActivity.homeBGMUSIC;

//        final Animation in = new AlphaAnimation(0.0f, 1.0f);
//        in.setDuration(3000);
//        picStart.setAnimation(in);
//        picStart.setAnimation(in);
//        gifKeyboard.setAnimation(in);
//        gifJunk.setAnimation(in);

        gameFrame = (FrameLayout) findViewById(R.id.gameFrame);
        startLayout = (RelativeLayout) findViewById(R.id.startLayout);
        trashCan = (ImageView) findViewById(R.id.trash);

        btnSettings = (ImageView) findViewById(R.id.btnSettings);
        exittt = (ImageView) findViewById(R.id.exittt);

        plusPoints = (GifImageView) findViewById(R.id.plusPoints);
        bomb = (ImageView) findViewById(R.id.black);
        plusLife = (GifImageView) findViewById(R.id.plusLife);
        heart3 = (ImageView) findViewById(R.id.heart3);
        heart4 = (ImageView) findViewById(R.id.heart4);
        heart5 = (ImageView) findViewById(R.id.heart5);
        picStart = (ImageView) findViewById(R.id.picStart);
        picAchievements = (ImageView) findViewById(R.id.picAchievements);
        picAbout = (ImageView) findViewById(R.id.picAbout);
        picRetry = (ImageView) findViewById(R.id.picRetry);
        picPause = (ImageView) findViewById(R.id.picPause);
        over = (ImageView) findViewById(R.id.over);
//        picChooser = (ImageView) findViewById(R.id.picChoose);
        picBio = (ImageView) findViewById(R.id.picBio);
        picNonBio = (ImageView) findViewById(R.id.picNonBio);
        arrow = (GifImageView) findViewById(R.id.arrow);
        gifKeyboard = (GifImageView) findViewById(R.id.gifKeyboard);
        gifJunk = (GifImageView) findViewById(R.id.gifJunk);
        gameFrame2 = (GifImageView) findViewById(R.id.gameFrame2);
        gameFrame1 = (GifImageView) findViewById(R.id.gameFrame1);
        lifeTwoGif = (GifImageView) findViewById(R.id.lifeTwoGif);
        lifeThreeGif = (GifImageView) findViewById(R.id.lifeThreeGif);
        leftBio = getResources().getDrawable(R.drawable.leftbio);
        rightBio = getResources().getDrawable(R.drawable.rightbio);
        leftNonBio = getResources().getDrawable(R.drawable.leftnonb);
        rightNonBio = getResources().getDrawable(R.drawable.rightnonb);
        picExit = (ImageView) findViewById(R.id.picExit);
        btnNo = (ImageView) findViewById(R.id.btnNo);
        btnYes = (ImageView) findViewById(R.id.btnYes);
        btnRestart = (ImageView) findViewById(R.id.btnRestart);
        btnExit = (ImageView) findViewById(R.id.btnExit);
        btnPlay = (ImageView) findViewById(R.id.btnPlay);
        life3 = getResources().getDrawable(R.drawable.life3);
        life1 = getResources().getDrawable(R.drawable.life1);
        life2 = getResources().getDrawable(R.drawable.life2);
        life11 = getResources().getDrawable(R.drawable.life11);
        life22 = getResources().getDrawable(R.drawable.life22);

        settingsFrame = (RelativeLayout) findViewById(R.id.SettingsLayout);

        //Score
        scoreLabel = (TextView) findViewById(R.id.scoreLabel);
        scoreShow = (TextView) findViewById(R.id.scoreShow);
        highScoreLabel = (TextView) findViewById(R.id.highScoreLabel);
        highScoreLabel2 = (TextView) findViewById(R.id.highScoreLabel2);

        //High Score
        settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        highScore = settings.getInt("HIGH_SCORE", 0);
        highScoreLabel.setText("BEST: " + highScore);
        highScoreLabel2.setText("BEST: " + highScore);

        gameFrame.setBackground(life3);

//        //Music
//        homeBGMusic.start();
//        homeBGMusic.setLooping(true);

        //Load score
        SharedPreferences myScore = this.getSharedPreferences("myScore", Context.MODE_PRIVATE);
        score = myScore.getInt("score", 0);


        //show unlock
        SharedPreferences shown1 = this.getSharedPreferences("shown1", Context.MODE_PRIVATE);
        shown = shown1.getBoolean("shown", true);

        SharedPreferences shown2 = getSharedPreferences("shown2", Context.MODE_PRIVATE);
        canshown = shown2.getBoolean("canshown",true);

        SharedPreferences shown3 = getSharedPreferences("shown3", Context.MODE_PRIVATE);
        cigarshown = shown3.getBoolean("cigarshown",true);

        SharedPreferences shown4 = getSharedPreferences("shown4", Context.MODE_PRIVATE);
        chipsshown = shown4.getBoolean("chipsshown",true);

        SharedPreferences shown5 = getSharedPreferences("shown5", Context.MODE_PRIVATE);
        eggsshown = shown5.getBoolean("eggsshown",true);

        SharedPreferences shown6 = getSharedPreferences("shown6", Context.MODE_PRIVATE);
        fishshown = shown6.getBoolean("fishshown",true);

        SharedPreferences shown7 = getSharedPreferences("shown7", Context.MODE_PRIVATE);
        tireshown = shown7.getBoolean("tireshown",true);

        SharedPreferences shown8 = getSharedPreferences("shown8", Context.MODE_PRIVATE);
        papershown = shown8.getBoolean("papershown",true);

        SharedPreferences shown9 = getSharedPreferences("shown9", Context.MODE_PRIVATE);
        milkshown = shown9.getBoolean("milkshown",true);

        SharedPreferences shown10 = getSharedPreferences("shown10", Context.MODE_PRIVATE);
        leafshown = shown10.getBoolean("leafshown",true);

        SharedPreferences shown11 = getSharedPreferences("shown11", Context.MODE_PRIVATE);
        plasticshown = shown11.getBoolean("plasticshown",true);

        SharedPreferences shown12 = getSharedPreferences("shown12", Context.MODE_PRIVATE);
        teabagshown = shown12.getBoolean("teabagshown",true);

        SharedPreferences shown13 = getSharedPreferences("shown13", Context.MODE_PRIVATE);
        wireshown = shown13.getBoolean("wireshown",true);

        SharedPreferences shown14 = getSharedPreferences("shown14", Context.MODE_PRIVATE);
        tissueshown = shown14.getBoolean("tissueshown",true);

        SharedPreferences shown15 = getSharedPreferences("shown15", Context.MODE_PRIVATE);
        styroshown = shown15.getBoolean("styroshown",true);

        //PLAY AND STOP SOUNDS
        mainBGMusic.start();
        mainBGMusic.setLooping(true);

        if (!condition) {
            mainBGMusic.setVolume(1, 1);
        } else {
            mainBGMusic.setVolume(0, 0);
        }

        startLayout.setVisibility(View.INVISIBLE);

        mSlideViewPager.setAdapter(sliderAdapter);

        arrow.setVisibility(View.VISIBLE);

        addDotsIndicator(0);

        mSlideViewPager.addOnPageChangeListener(viewListener);

        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!condition) {
                    sound.playClickedSound();
                }

                mSlideViewPager.setCurrentItem(mCurrentPage + 1);

                instructionsFrame.setVisibility(View.INVISIBLE);
                startLayout.setVisibility(View.INVISIBLE);
                gameFrame.setBackground(life3);

                //chooser
                chooserLayout.setVisibility(View.VISIBLE);

                picBio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(!condition) {
                            sound.playClickedSound();
                        }

                        bio = true;

                        chooserLayout.setVisibility(View.INVISIBLE);
                        ivFish = (ImageView) findViewById(R.id.ivFish);
                        ivBanana = (ImageView) findViewById(R.id.ivBanana);
                        ivCan = (ImageView) findViewById(R.id.ivCan);
                        ivChips = (ImageView) findViewById(R.id.ivChips);
                        ivTire = (ImageView) findViewById(R.id.ivTire);
                        ivLeaf = (ImageView) findViewById(R.id.ivLeaf);
                        ivMilk = (ImageView) findViewById(R.id.ivMilk);
                        ivPaper = (ImageView) findViewById(R.id.ivPaper);
                        ivTissue = (ImageView) findViewById(R.id.ivTissue);
                        ivPlastic = (ImageView) findViewById(R.id.ivPlastic);
                        ivCork = (ImageView) findViewById(R.id.ivCork);
                        ivStyro = (ImageView) findViewById(R.id.ivStyro);
//                                picPause = (ImageView) findViewById(R.id.picPause);

                        getSet();

                        start_flg = true;

                        timeCount = 0;
                        score = 0;
                        scoreLabel.setText("0");

                        timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                if (start_flg) {
                                    handler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            changePos();
                                        }
                                    });
                                }
                            }
                        }, 0, 20);


                    }
                });


                picNonBio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(!condition) {
                            sound.playClickedSound();
                        }

                        bio = false;

                        // CHANGE TRASH IMAGE
                        chooserLayout.setVisibility(View.INVISIBLE);

                        ivFish = (ImageView) findViewById(R.id.ivChips);
                        ivBanana = (ImageView) findViewById(R.id.ivTire);
                        ivCan = (ImageView) findViewById(R.id.ivFish);
                        ivChips = (ImageView) findViewById(R.id.ivLeaf);
                        ivTire = (ImageView) findViewById(R.id.ivPaper);
                        ivLeaf = (ImageView) findViewById(R.id.ivMilk);
                        ivMilk = (ImageView) findViewById(R.id.ivBanana);
                        ivPaper = (ImageView) findViewById(R.id.ivCan);
                        ivTissue = (ImageView) findViewById(R.id.ivPlastic);
                        ivPlastic = (ImageView) findViewById(R.id.ivTissue);
                        ivCork = (ImageView) findViewById(R.id.ivStyro);
                        ivStyro = (ImageView) findViewById(R.id.ivCork);
//                                DrEgg = getResources().getDrawable(R.drawable.egg);
//                                picPause = (ImageView) findViewById(R.id.picPause);

                        getSet();

                        start_flg = true;

                        timeCount = 0;
                        score = 0;
                        scoreLabel.setText("0");

                        timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                if (start_flg) {
                                    handler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            changePos();
                                        }
                                    });
                                }
                            }
                        }, 0, 20);

                    }
                });

            }
        });

        picRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //PLAY AND STOP SOUNDS
                if(!condition) {
                    sound.playClickedSound();
                }

                homeBGMusic.stop();
                homeBGMusic.prepareAsync();
                mainBGMusic.start();
                mainBGMusic.setLooping(true);

                startLayout.setVisibility(view.INVISIBLE);

                gameFrame.setBackground(life3);
                gameFrame1.setVisibility(View.INVISIBLE);
                gameFrame2.setVisibility(View.INVISIBLE);
                chooserLayout.setVisibility(View.VISIBLE);

                trashCan.setX(0.0f);
                trashCan.setY(1200.0f);
            }
        });


        picExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!condition) {
                    sound.playClickedSound();
                }

                exitConfirmLayout.setVisibility(View.VISIBLE);

                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

//                        if(!condition) {
//                            sound.playClickedSound();
//                        }
//
//                        Intent intent = new Intent(Intent.ACTION_MAIN);
//                        intent.addCategory(Intent.CATEGORY_HOME);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        intent.putExtra("MUTE2", condition);
//                        finish();
//                        System.exit(0);
                        if(!condition) {
                            sound.playClickedSound();
                            sound.playGameOverSound();
                        }
                        mainBGMusic.stop();
                        mainBGMusic.prepareAsync();
                        homeBGMusic.start();
                        homeBGMusic.setLooping(true);
                        highScoreLabel.setVisibility(View.VISIBLE);
                        finish();
                    }
                });

                btnNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

//                        if(!condition) {
//                            sound.playClickedSound();
//                        }
//
//                        exitConfirmLayout.setVisibility(View.GONE);
//
//                        //picPause.setImageResource(R.drawable.pause);
//                        if (start_flg && pause_flg == true) {
//                            pause_flg = false;
//                            picPause.setVisibility(View.VISIBLE);
//                            timer = new Timer();
//                            timer.schedule(new TimerTask() {
//                                @Override
//                                public void run() {
//                                    if (start_flg) {
//                                        handler.post(new Runnable() {
//                                            @Override
//                                            public void run() {
//                                                changePos();
//                                            }
//                                        });
//                                    }
//                                }
//                            }, 0, 20);
//
//                        }

                        if(!condition) {
                            sound.playClickedSound();
                        }
                        exitConfirmLayout.setVisibility(View.GONE);
                        //picPause.setImageResource(R.drawable.pause);
                        if (start_flg && pause_flg == true) {
                            pause_flg = false;
                            picPause.setVisibility(View.VISIBLE);
                            timer = new Timer();
                            timer.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    if (start_flg) {
                                        handler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                changePos();
                                            }
                                        });
                                    }
                                }
                            }, 0, 20);

                        }

                    }
                });


            }
        });


    }


    boolean twice;

    @Override
    public void onBackPressed() {

        if (start_flg && pause_flg == false) {
            pause_flg = true;

            timer.cancel();
            timer = null;
        }

        // if at home page
//        if(!start_flg){
        pauseConfirmationLayout.setVisibility(View.INVISIBLE);
        exitConfirmLayout.setVisibility(View.VISIBLE);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!condition) {
                    sound.playClickedSound();
                    sound.playGameOverSound();
                }
                mainBGMusic.stop();
                mainBGMusic.prepareAsync();
                homeBGMusic.start();
                homeBGMusic.setLooping(true);
                finish();
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!condition) {
                    sound.playClickedSound();
                }
                exitConfirmLayout.setVisibility(View.GONE);
                //picPause.setImageResource(R.drawable.pause);
                if (start_flg && pause_flg == true) {
                    pause_flg = false;
                    picPause.setVisibility(View.VISIBLE);
                    timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            if (start_flg) {
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        changePos();
                                    }
                                });
                            }
                        }
                    }, 0, 20);

                }

            }
        });

    }


    public void pausePushed(View view) {

        if (pause_flg == false) {
            pause_flg = true;

            timer.cancel();
            timer = null;

            picPause.setVisibility(View.INVISIBLE);
            pauseConfirmationLayout.setVisibility(View.VISIBLE);

            btnPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (!condition) {
                        sound.playClickedSound();
                    }

                    picPause.setVisibility(View.VISIBLE);
                    pauseConfirmationLayout.setVisibility(View.INVISIBLE);
                    pause_flg = false;

//                    picPause.setImageResource(R.drawable.pause);
                    timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            if (start_flg) {
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        changePos();
                                    }
                                });
                            }
                        }
                    }, 0, 20);
                }
            });

            btnExit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (!condition) {
                        sound.playClickedSound();
                        sound.playGameOverSound();
                    }
                    mainBGMusic.stop();
                    mainBGMusic.prepareAsync();
                    homeBGMusic.start();
                    homeBGMusic.setLooping(true);
                    finish();

                }
            });

            btnRestart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    pause_flg = false;

                    pauseConfirmationLayout.setVisibility(View.INVISIBLE);

                    if (!condition) {
                        sound.playClickedSound();
                    }
                    mainBGMusic.start();
                    mainBGMusic.setLooping(true);

                    picPause.setVisibility(View.INVISIBLE);
                    startLayout.setVisibility(view.INVISIBLE);
                    scoreLabel.setVisibility(View.INVISIBLE);
                    trashCan.setVisibility(View.INVISIBLE);
                    plusPoints.setVisibility(View.INVISIBLE);
                    plusLife.setVisibility(View.INVISIBLE);
                    bomb.setVisibility(View.INVISIBLE);
                    ivFish.setVisibility(View.INVISIBLE);
                    ivCan.setVisibility(View.INVISIBLE);
                    ivBanana.setVisibility(View.INVISIBLE);
                    ivChips.setVisibility(View.INVISIBLE);
                    ivMilk.setVisibility(View.INVISIBLE);
                    ivTire.setVisibility(View.INVISIBLE);
                    ivLeaf.setVisibility(View.INVISIBLE);
                    ivPaper.setVisibility(View.INVISIBLE);
                    ivTissue.setVisibility(View.INVISIBLE);
                    ivPlastic.setVisibility(View.INVISIBLE);
                    ivCork.setVisibility(View.INVISIBLE);
                    ivStyro.setVisibility(View.INVISIBLE);
                    lifeThreeGif.setVisibility(View.INVISIBLE);
                    lifeTwoGif.setVisibility(View.INVISIBLE);
                    heart3.setVisibility(View.INVISIBLE);
                    heart4.setVisibility(View.INVISIBLE);
                    heart5.setVisibility(View.INVISIBLE);
                    gameFrame1.setVisibility(View.INVISIBLE);
                    gameFrame2.setVisibility(View.INVISIBLE);

                    gameFrame.setBackground(life3);
                    chooserLayout.setVisibility(View.VISIBLE);

                    lives = 3;
                    trashCan.setX(0.0f);
                    trashCan.setY(1200.0f);

                }
            });
        }
    }


    public void getSet() {

        if (frameHeight == 0) {
            frameHeight = gameFrame.getHeight();
            frameWidth = gameFrame.getWidth();

            trashCanSize = trashCan.getHeight();
            trashCanX = trashCan.getX();
            trashCanY = trashCan.getY();
        }

        trashCan.setX(0.0f);
        trashCan.setY(1200.0f);
        ivFish.setY(3000.0f);
        ivCan.setY(3000.0f);
        ivBanana.setY(3000.0f);
        ivChips.setY(3000.0f);
        ivLeaf.setY(3000.0f);
        ivTire.setY(3000.0f);
        ivMilk.setY(3000.0f);
        ivPaper.setY(3000.0f);
        ivPlastic.setY(3000.0f);
        ivTissue.setY(3000.0f);
        ivCork.setY(3000.0f);
        ivStyro.setY(3000.0f);
        plusPoints.setY(3000.0f);
        bomb.setY(3000.0f);
        plusLife.setY(3000.0f);

        bombY = bomb.getY();
        plusPointsY = plusPoints.getY();
        plusLifeY = plusLife.getY();
        fishY = ivFish.getY();
        chipsY = ivChips.getY();
        bananaY = ivBanana.getY();
        canY = ivCan.getY();
        tireY = ivTire.getY();
        leafY = ivLeaf.getY();
        milkY = ivMilk.getY();
        paperY = ivPaper.getY();
        plasticY = ivPlastic.getY();
        tissueY = ivTissue.getY();
        corkY = ivCork.getY();
        styroY = ivStyro.getY();


        // SET VISIBLE
        trashCan.setVisibility(View.VISIBLE);
        ivFish.setVisibility(View.VISIBLE);
        ivBanana.setVisibility(View.VISIBLE);
        ivCan.setVisibility(View.VISIBLE);
        ivChips.setVisibility(View.VISIBLE);
        ivTire.setVisibility(View.VISIBLE);
        ivLeaf.setVisibility(View.VISIBLE);
        ivMilk.setVisibility(View.VISIBLE);
        ivPaper.setVisibility(View.VISIBLE);
        ivPlastic.setVisibility(View.VISIBLE);
        ivTissue.setVisibility(View.VISIBLE);
        ivCork.setVisibility(View.VISIBLE);
        ivStyro.setVisibility(View.VISIBLE);
        plusPoints.setVisibility(View.VISIBLE);
        plusLife.setVisibility(View.VISIBLE);
        bomb.setVisibility(View.VISIBLE);
        scoreLabel.setVisibility(View.VISIBLE);
        picPause.setVisibility(View.VISIBLE);
        heart3.setVisibility(View.VISIBLE);
        heart4.setVisibility(View.VISIBLE);
        heart5.setVisibility(View.VISIBLE);

    }

//
//    private boolean isFirstTime() {
//        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
//        boolean ranBefore = preferences.getBoolean("RanBefore", false);
//        if (!ranBefore) {
//            // first time
//            SharedPreferences.Editor editor = preferences.edit();
//            editor.putBoolean("RanBefore", true);
//            editor.commit();
//        }
//        return !ranBefore;
//    }


    public void changePos() {
        //Add timeCount
        timeCount += 20;

        // Move Trash

        if (bio) {
            if (score <= 30) {
                if (action_flg) {    //Touching
                    //Toast.makeText(this, "Time: " +timeCount, Toast.LENGTH_SHORT).show();
                    trashCanX += 13;
                    trashCan.setImageDrawable(rightBio);
                } else {    //Releasing
                    trashCanX -= 13;
                    trashCan.setImageDrawable(leftBio);
                }
            } else {
                if (action_flg) {
                    trashCanX += 14;
                    trashCan.setImageDrawable(rightBio);
                } else {
                    trashCanX -= 14;
                    trashCan.setImageDrawable(leftBio);
                }
            }

            //Check box position
            if (trashCanX < 0) {
                trashCanX = 0;
                trashCan.setImageDrawable(rightBio);
            }
            if (frameWidth - trashCanSize < trashCanX) {
                trashCanX = frameWidth - trashCanSize;
                trashCan.setImageDrawable(leftBio);
            }

        } else {
            if (score <= 30) {
                if (action_flg) {    //Touching
                    //Toast.makeText(this, "Time: " +timeCount, Toast.LENGTH_SHORT).show();
                    trashCanX += 13;
                    trashCan.setImageDrawable(rightNonBio);
                } else {    //Releasing
                    trashCanX -= 13;
                    trashCan.setImageDrawable(leftNonBio);
                }
            } else if (30 < score && score < 55) {
                if (action_flg) {
                    trashCanX += 14;
                    trashCan.setImageDrawable(rightNonBio);
                } else {
                    trashCanX -= 14;
                    trashCan.setImageDrawable(leftNonBio);
                }
            } else {
                if (action_flg) {
                    trashCanX += 15;
                    trashCan.setImageDrawable(rightNonBio);
                } else {
                    trashCanX -= 15;
                    trashCan.setImageDrawable(leftNonBio);
                }
            }

            //Check box position
            if (trashCanX < 0) {
                trashCanX = 0;
                trashCan.setImageDrawable(rightNonBio);
            }
            if (frameWidth - trashCanSize < trashCanX) {
                trashCanX = frameWidth - trashCanSize;
                trashCan.setImageDrawable(leftNonBio);
            }
        }

        trashCan.setX(trashCanX);


//      BIODEGRADABLE ITEMS

//      Banana
        if (timeCount >= 500) {
            bananaY += 7; // SPEED pagbaba (the lower the slower)
        }

        if (bio == true) {
            if (score % 2 == 1 && bananaY > frameHeight) {
                ivBanana.setImageResource(R.drawable.egg2);
            } else if (score % 2 == 0 && bananaY > frameHeight) {
                ivBanana.setImageResource(R.drawable.bbb);
            }
        } else {
            if (score % 2 == 1 && bananaY > frameHeight) {
                ivBanana.setImageResource(R.drawable.cigar2); // tire
            } else if (score % 2 == 0 && bananaY > frameHeight) {
                ivBanana.setImageResource(R.drawable.tire2);
            }
        }

        float bananaCenterX = bananaX + ivBanana.getWidth() / 2; // (x,y) xPos(is dulo sa left)
        float bananaBottomY = bananaY + ivBanana.getHeight(); // (x,y) YPos(is dulo sa left)

        if (hitCheck(bananaCenterX, bananaBottomY)) {
            bananaY = frameHeight + 100;
            score += 2;
            //Toast.makeText(ActivityMain.this, "BanaY =  " + bananaY + " frameheight " + frameHeight, Toast.LENGTH_SHORT).show();
        }
        if (bananaY == 1300.0) { //1322.0 || bananaY == 1334.0 || bananaY == 1380.0
            loseLife();
        }

//        if(score==11)

        if (bananaY > frameHeight && score <= 98) {
            if (timeCount % 2500 == 0) {
                bananaY = -100; // POSITION INTERVAL
                bananaX = (float) Math.floor(Math.random() * (frameWidth - ivBanana.getWidth()));
            }
        } else if (bananaY > frameHeight && score >= 98) {
            ivBanana.setVisibility(View.GONE);
        }
        ivBanana.setX(bananaX);
        ivBanana.setY(bananaY);


//      Fish
        if (score == 13 || score == 11) {

        } else if (score >= 10 && score != 13 && score != 11) {
            fishY += 7; // SPEED
        }
        float fishCenterX = fishX + ivFish.getWidth() / 2;
        float fishBottomY = fishY + ivFish.getHeight();
        //Toast.makeText(ActivityMain.this, "FishY =  " + fishY + " frameheight " + frameHeight, Toast.LENGTH_SHORT).show();
        if (hitCheck(fishCenterX, fishBottomY)) {
            fishY = frameHeight + 100;
            score += 2;
        }
        if (fishY == 1300.0) {
            loseLife();
        }
        if (fishY > frameHeight) {
            if (score >= 4) {
                if (timeCount % 8850 == 0) {
                    fishY = -100;
                    fishX = (float) Math.floor(Math.random() * (frameWidth - ivFish.getWidth()));
                }
            }
        }
        ivFish.setX(fishX);
        ivFish.setY(fishY);


//      Leaf
        if (score >= 30) {
            leafY += 9; // SPEED
        }


        float leafCenterX = leafX + ivLeaf.getWidth() / 2;
        float leafBottomY = leafY + ivLeaf.getHeight();
        if (hitCheck(leafCenterX, leafBottomY)) {
            leafY = frameHeight + 100;
            score += 2;
        }
        if (leafY == 1415.0) {
            loseLife();
        }
        if (leafY > frameHeight) {
            if (score >= 30) {
                if (timeCount % 9465 == 0) { // 9450
                    leafY = -100;
                    leafX = (float) Math.floor(Math.random() * (frameWidth - ivLeaf.getWidth()));
                }
            }
        }
        ivLeaf.setX(leafX);
        ivLeaf.setY(leafY);


//      Paper
        if (score >= 55) {
            paperY += 11; // SPEED
        }

        float paperCenterX = paperX + ivPaper.getWidth() / 2;
        float paperBottomY = paperY + ivPaper.getHeight();
        if (hitCheck(paperCenterX, paperBottomY)) {
            paperY = frameHeight + 100;
            score += 2;
        }
        if (paperY == 1220.0) {// 1611 // 1110.0 pede kaso maaga
            loseLife();
        }
        if (paperY > frameHeight) {
            if (score >= 45) {
                if (timeCount % 7450 == 0) { // if 9450 then 1220.0 lose life
                    paperY = -100;
                    paperX = (float) Math.floor(Math.random() * (frameWidth - ivPaper.getWidth()));
                }
            }
        }
        ivPaper.setX(paperX);
        ivPaper.setY(paperY);


//      Tissue
        if (score >= 104) { //100 // 102 - 3330
            tissueY += 11;
        }

        float tissueCenterX = tissueX + ivTissue.getWidth() / 2;
        float tissueBottomY = tissueY + ivTissue.getHeight();

        if (hitCheck(tissueCenterX, tissueBottomY)) {
            tissueY = frameHeight + 100;
            score += 2;
        }
        if (tissueY == 1220.0) {
            loseLife();
        }
        if (tissueY > frameHeight) {
            if (score >= 63) {
                if (timeCount % 3330 == 0) { // 3300
                    tissueY = -100;
                    tissueX = (float) Math.floor(Math.random() * (frameWidth - ivTissue.getWidth()));
                }
            }
        }
        ivTissue.setX(tissueX);
        ivTissue.setY(tissueY);


//      Cork = KARTON
        if (score >= 53) {
            corkY += 9;
        }

        float corkCenterX = corkX + ivCork.getWidth() / 2;
        float corkBottomY = corkY + ivCork.getHeight();

        if (hitCheck(corkCenterX, corkBottomY)) {
            corkY = frameHeight + 100;
            score += 2;
        }
        if (leafY == 1415.0) {
            loseLife();
        }
        if (corkY > frameHeight) {
            if (score >= 40) {
                if (timeCount % 43000 == 0) {
                    corkY = -100;
                    corkX = (float) Math.floor(Math.random() * (frameWidth - ivCork.getWidth()));
                }
            }
        }
        ivCork.setX(corkX);
        ivCork.setY(corkY);


//      NON BIODEGRADABLE ITEMS
//      Can
        if (timeCount >= 1500) {
            canY += 7;
        }

        if (bio == true) {
            if (score % 2 == 1 && score >= 30 && canY > frameHeight) {
                ivCan.setImageResource(R.drawable.can);
            } else if (score % 2 == 0 && score >= 30 && canY > frameHeight) {
                ivCan.setImageResource(R.drawable.batteries);
            }
        } else {
            if (score % 2 == 1 && score >= 30 && canY > frameHeight) {
                ivCan.setImageResource(R.drawable.fish);
            } else if (score % 2 == 0 && score >= 30 && canY > frameHeight) {
                ivCan.setImageResource(R.drawable.popsie);
            }
        }

        float canCenterX = canX + ivCan.getWidth() / 2;
        float canBottomY = canY + ivCan.getHeight();
        if (hitCheck(canCenterX, canBottomY)) {
            canY = frameHeight + 100;
            loseLife();
        }
        if (canY > frameHeight) {
            if (timeCount % 6000 == 0) {
                canY = -50;
                canX = (float) Math.floor(Math.random() * (frameWidth - ivCan.getWidth()));
            }
        }
        ivCan.setX(canX);
        ivCan.setY(canY);


//      Chips
        if (score >= 21) {
            chipsY += 7;
        }


        if (bio == true) {
            if (score % 2 == 1 && score > 30 && chipsY > frameHeight) {
                ivChips.setImageResource(R.drawable.glass);
            } else if (score % 2 == 0 && chipsY > frameHeight) {
                ivChips.setImageResource(R.drawable.chips2);
            }
        } else {
            if (score % 2 == 1 && score > 30 && chipsY > frameHeight) {
                ivChips.setImageResource(R.drawable.tea2); // tea
            } else if (score % 2 == 0 && chipsY > frameHeight) {
                ivChips.setImageResource(R.drawable.leaf2);
            }
        }


        float chipsCenterX = chipsX + ivChips.getWidth() / 2;
        float chipsBottomY = chipsY + ivChips.getHeight();
        if (hitCheck(chipsCenterX, chipsBottomY)) {
            chipsY = frameHeight + 100;
            loseLife();
        }
        if (chipsY > frameHeight) {
            if (score >= 21) {
                if (timeCount % 1070 == 0) { // 1170
                    chipsY = -50;
                    chipsX = (float) Math.floor(Math.random() * (frameWidth - ivChips.getWidth()));
                }
            }
        }
        ivChips.setX(chipsX);
        ivChips.setY(chipsY);


//      Tire
        if (score >= 38) {
            tireY += 9;
        }
        float tireCenterX = tireX + ivTire.getWidth() / 2;
        float tireBottomY = tireY + ivTire.getHeight();
        if (hitCheck(tireCenterX, tireBottomY)) {
            tireY = frameHeight + 100;
            loseLife();
        }
        if (tireY > frameHeight) {
            if (score >= 30) {
                if (timeCount % 4610 == 0) {
                    tireY = -100;
                    tireX = (float) Math.floor(Math.random() * (frameWidth - ivTire.getWidth()));
                }
            }
        }
        ivTire.setX(tireX);
        ivTire.setY(tireY);


//        Milk
        if (score >= 18) {
            milkY += 7;
        }
        float milkCenterX = milkX + ivMilk.getWidth() / 2;
        float milkBottomY = milkY + ivMilk.getHeight();
        if (hitCheck(milkCenterX, milkBottomY)) {
            milkY = frameHeight + 100;
            loseLife();
        }
        if (milkY > frameHeight) {
            if (timeCount % 27600 == 0) {
                milkY = -100;
                milkX = (float) Math.floor(Math.random() * (frameWidth - ivMilk.getWidth()));
            }
        }
        ivMilk.setX(milkX);
        ivMilk.setY(milkY);


//        Plastic
        if (score >= 75) {
            plasticY += 11;
        }

        if (bio == true) {
            if (score % 2 == 1 && plasticY > frameHeight) {
                ivPlastic.setImageResource(R.drawable.wire);
            } else if (score % 2 == 0 && plasticY > frameHeight) {
                ivPlastic.setImageResource(R.drawable.plastic);
            }
        } else {
            if (score % 2 == 1 && plasticY > frameHeight) {
                ivPlastic.setImageResource(R.drawable.popsie2);
            } else if (score % 2 == 0 && plasticY > frameHeight) {
                ivPlastic.setImageResource(R.drawable.tissue);
            }
        }

        float plasticCenterX = plasticX + ivPlastic.getWidth() / 2;
        float plasticBottomY = plasticY + ivPlastic.getHeight();
        if (hitCheck(plasticCenterX, plasticBottomY)) {
            plasticY = frameHeight + 100;
            loseLife();
        }
        if (plasticY > frameHeight) {
            if (timeCount % 4280 == 0) {
                plasticY = -100;
                plasticX = (float) Math.floor(Math.random() * (frameWidth - ivPlastic.getWidth()));
            }
        }
        ivPlastic.setX(plasticX);
        ivPlastic.setY(plasticY);


//        Styro
        if (score >= 85) {
            styroY += 11;
        }
        float styroCenterX = styroX + ivStyro.getWidth() / 2;
        float styroBottomY = styroY + ivStyro.getHeight();
        if (hitCheck(styroCenterX, styroBottomY)) {
            styroY = frameHeight + 100;
            loseLife();
        }
        if (styroY > frameHeight) {
            if (timeCount % 25000 == 0) {
                styroY = -100;
                styroX = (float) Math.floor(Math.random() * (frameWidth - ivStyro.getWidth()));
            }
        }
        ivStyro.setX(styroX);
        ivStyro.setY(styroY);


//      POWER UPS
//      PLUS 5 POINTS
        if (!points_flg && timeCount % 25000 == 0) {
            points_flg = true;
            plusPointsY = -20;
            plusPointsX = (float) Math.floor(Math.random() * (frameWidth - plusPoints.getWidth()));
        }
        if (points_flg) {
            plusPointsY += 11;
            float plusPointsCenterX = plusPointsX + plusPoints.getWidth() / 2;
            float plusPointsCenterY = plusPointsY + plusPoints.getHeight();

            if (hitCheck(plusPointsCenterX, plusPointsCenterY)) {
                plusPointsY = frameHeight + 30;
                score += 5;
            }
            if (plusPointsY > frameHeight)
                points_flg = false;
            plusPoints.setX(plusPointsX);
            plusPoints.setY(plusPointsY);
        }

//        PLUS LIFE

        if (score >= 16) {
            plusLifeY += 8;
        }
        float plusLifeCenterX = plusLifeX + plusLife.getWidth() / 2;
        float plusLifeBottomY = plusLifeY + plusLife.getHeight();
        if (hitCheck(plusLifeCenterX, plusLifeBottomY)) {
            plusLifeY = frameHeight + 100;
            addLife();
        }
        if (plusLifeY > frameHeight) {
            if (timeCount % 55000 == 0) {
                plusLifeY = -100;
                plusLifeX = (float) Math.floor(Math.random() * (frameWidth - plusLife.getWidth()));
            }
        }
        plusLife.setX(plusLifeX);
        plusLife.setY(plusLifeY);


//      Bomb
        if (score >= 40) {
            bombY += 8;
        }
        float bombCenterX = bombX + bomb.getWidth() / 2;
        float bombBottomY = bombY + bomb.getHeight();
        if (hitCheck(bombCenterX, bombBottomY)) {
            bombY = frameHeight + 100;
            vibrator.vibrate(300);
            gameOver();
        }
        if (bombY > frameHeight) {
            if (timeCount % 60000 == 0) {
                bombY = -100;
                bombX = (float) Math.floor(Math.random() * (frameWidth - bomb.getWidth()));
            }
        }
        bomb.setX(bombX);
        bomb.setY(bombY);

        scoreLabel.setText("" + score);

//        MEDIUM MODE
        if (score >= 30) {
            mediumMode();
        }

//        HARD MODE
        if (score >= 59) { // 55
            hardMode();
        }

    }


    public void mediumMode() {
        bananaY += 2; //4
        canY += 2;
        fishY += 2;
        chipsY += 2; //3
        milkY += 2;
        plusPointsY += 2;


        //Toast.makeText(ActivityMain.this, "LeafY =  " + leafY + " frameheight " + frameHeight, Toast.LENGTH_SHORT).show();

        if (bananaY == 1315.0) { //1322.0 || bananaY == 1334.0 || bananaY == 1380.0 // 1813 matchy sa trash // 1455 ok dati
            loseLife();
        }

        if (fishY == 1315.0) { // 1342 pr 1333 pede din!!!
            loseLife();
        }

        if (leafY == 1367.0) { // 1342 pr 1333 pede din!!!
            loseLife();
        }

    }


    public void hardMode() {
        bananaY += 2; //4
        canY += 2;
        fishY += 2;
        chipsY += 2; //3
        tireY += 2;
        leafY += 2;
        milkY += 2;
        plusPointsY += 1;
        corkY += 2;


        //Toast.makeText(ActivityMain.this, "BanaY =  " + bananaY + " frameheight " + frameHeight, Toast.LENGTH_SHORT).show();

        if (bananaY == 1268.0) { //1588 , 1488.0 - magulo, 1480, 1468, 1340 - dati, 1257,
            loseLife();
        }

        if (fishY == 1268.0) { // 1330 // 993 , 1440, 1340 , 1260
            loseLife();
        }

        if (leafY == 1233.0) { // or 1266
            loseLife();
        }

        if (corkY == 1233.0) { // or 1266
            loseLife();
        }

    }


    public boolean hitCheck(float x, float y) {
        int a = (int) (trashCanX + trashCanSize);
        if (trashCanX <= x && x <= trashCanX + trashCanSize && trashCanY <= y && y <= frameHeight - 170) { // OR frameheight - faceHeight
            // Toast.makeText(ActivityMain.this, "tcX: " + trashCanX + " X: " + x + "tcY: " + trashCanY + " Y: " + y , Toast.LENGTH_LONG).show();
            if (!condition) {
                sound.playHitSound();
            }
            return true;
        }
        return false;
    }

    public void addLife() {
        lives++;

        final Animation in = new AlphaAnimation(0.0f, 1.0f);
        in.setDuration(2000);

        if (lives == 2) {
            heart4.setVisibility(View.VISIBLE);
//            gameFrame.setBackground(life22);
//            gameFrame2.setImageResource(R.drawable.life22);
            gameFrame2.setVisibility(View.VISIBLE);
            gameFrame1.setVisibility(View.INVISIBLE);
            lifeThreeGif.setVisibility(View.INVISIBLE);
        } else if (lives == 3) {
            heart5.setVisibility(View.VISIBLE);
            lifeTwoGif.setVisibility(View.INVISIBLE);
            gameFrame2.setVisibility(View.INVISIBLE);
            gameFrame.setBackground(life3);
        } else {
            lives = 3;
        }
    }

    public void loseLife() {
        lives--;

        if (!condition) {
            sound.playErrorSound();
        }
        vibrator.vibrate(300);

        final Animation in = new AlphaAnimation(0.0f, 1.0f);
        in.setDuration(3000);


        if (lives == 2) {
            heart5.setVisibility(View.INVISIBLE);
            lifeTwoGif.setVisibility(View.VISIBLE);
            lifeTwoGif.setAnimation(in);
//            gameFrame.setBackground(life2);
            gameFrame2.setVisibility(View.VISIBLE);
            gameFrame1.setVisibility(View.INVISIBLE);
//            gameFrame2.setImageResource(R.drawable.life22);
        } else if (lives == 1) {
            heart4.setVisibility(View.INVISIBLE);
            heart5.setVisibility(View.INVISIBLE);
            lifeThreeGif.setVisibility(View.VISIBLE);
//            gameFrame.setBackground(life1);
//            gameFrame2.setImageResource(R.drawable.life11);
            gameFrame1.setVisibility(View.VISIBLE);
            gameFrame2.setVisibility(View.INVISIBLE);
            lifeThreeGif.setAnimation(in);
        } else if (lives < 1) {
            heart3.setVisibility(View.INVISIBLE);

            gameOver();
            lives = 3;
            //Toast.makeText(ActivityMain.this, "not catchy -3", Toast.LENGTH_LONG).show();
        } else {

        }

    }


    public void gameOver() {
        //play and stop sounds
        if (!condition) {
            sound.playGameOverSound();
        }
        mainBGMusic.stop();
        mainBGMusic.prepareAsync();
        homeBGMusic.start();

        if (!condition) {
            homeBGMusic.setVolume(1, 1);
        } else {
            homeBGMusic.setVolume(0, 0);
        }

        // Stop timer
        timer.cancel();
        timer = null;
        start_flg = false;

        // Sleep 1 sec before showing startLayout
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        exittt.setVisibility(View.INVISIBLE);
        btnSettings.setVisibility(View.INVISIBLE);
        settingsFrame.setVisibility(View.INVISIBLE);
        gameFrame2.setVisibility(View.INVISIBLE);
        over.setVisibility(View.VISIBLE);
        startLayout.setVisibility(View.VISIBLE);
        highScoreLabel2.setVisibility(View.VISIBLE);
        highScoreLabel.setVisibility(View.INVISIBLE);
        gifKeyboard.setVisibility(View.INVISIBLE);
        gifJunk.setVisibility(View.INVISIBLE);
        picAbout.setVisibility(View.INVISIBLE);
        picAchievements.setVisibility(View.INVISIBLE);
        picStart.setVisibility(View.INVISIBLE);
        scoreShow.setVisibility(View.VISIBLE);
        picExit.setVisibility(View.VISIBLE);
        scoreLabel.setVisibility(View.INVISIBLE);
        picPause.setVisibility(View.INVISIBLE);
        picRetry.setVisibility(View.VISIBLE);
        trashCan.setVisibility(View.INVISIBLE);
        bomb.setVisibility(View.INVISIBLE);
        plusPoints.setVisibility(View.INVISIBLE);
        plusLife.setVisibility(View.INVISIBLE);
        ivFish.setVisibility(View.INVISIBLE);
        ivCan.setVisibility(View.INVISIBLE);
        ivBanana.setVisibility(View.INVISIBLE);
        ivChips.setVisibility(View.INVISIBLE);
        ivMilk.setVisibility(View.INVISIBLE);
        ivTire.setVisibility(View.INVISIBLE);
        ivLeaf.setVisibility(View.INVISIBLE);
        ivPaper.setVisibility(View.INVISIBLE);
        ivPlastic.setVisibility(View.INVISIBLE);
        ivTissue.setVisibility(View.INVISIBLE);
        ivStyro.setVisibility(View.INVISIBLE);
        ivCork.setVisibility(View.INVISIBLE);
        lifeThreeGif.setVisibility(View.INVISIBLE);
        lifeTwoGif.setVisibility(View.INVISIBLE);
        heart3.setVisibility(View.INVISIBLE);
        heart4.setVisibility(View.INVISIBLE);
        heart5.setVisibility(View.INVISIBLE);
        scoreShow.setText("Score: " + score);

        startLayout.setBackground(life3);

        // Save score
        SharedPreferences myScore = getSharedPreferences("myScore", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor1 = myScore.edit();
        editor1.putInt("score", score);
        editor1.commit();

        toast = Toast.makeText(this, "Achievement Unlocked!", Toast.LENGTH_SHORT);
//        int i = 0;



        if (score >= 10 && shown) {
            toast.show();
            shown = false;
        }
        if (score >= 25 && canshown) {
            toast.show();
            canshown = false;
        }
        if (score >= 40 && cigarshown) {
            toast.show();
            cigarshown = false;
        }
        if (score >= 65 && chipsshown) {
            toast.show();
            chipsshown = false;
        }
        if (score >= 80 && eggsshown) {
            toast.show();
            eggsshown = false;
        }
        if (score >= 100 && fishshown) {
            toast.show();
            fishshown = false;
        }
        if (score >= 125 && tireshown) {
            toast.show();
            tireshown = false;
        }
        if (score >= 150 && papershown) {
            toast.show();
            papershown = false;
        }
        if (score >= 200 && milkshown) {
            toast.show();
            milkshown = false;
        }
        if (score >= 250 && leafshown) {
            toast.show();
            leafshown = false;
        }
        if (score >= 300 && plasticshown) {
            toast.show();
            plasticshown = false;
        }
        if (score >= 350 && teabagshown) {
            toast.show();
            teabagshown = false;
        }
        if (score >= 400 && wireshown) {
            toast.show();
            wireshown = false;
        }
        if (score >= 450 && tissueshown) {
            toast.show();
            tissueshown = false;
        }
        if (score >= 500 && styroshown) {
            toast.show();
            styroshown = false;
        }

        SharedPreferences shown1 = getSharedPreferences("shown1", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor2 = shown1.edit();
        editor2.putBoolean("shown", shown);
        editor2.commit();

        SharedPreferences shown2 = getSharedPreferences("shown2", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor3 = shown2.edit();
        editor3.putBoolean("canshown", canshown);
        editor3.commit();

        SharedPreferences shown3 = getSharedPreferences("shown3", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor4 = shown3.edit();
        editor4.putBoolean("cigarshown", cigarshown);
        editor4.commit();

        SharedPreferences shown4 = getSharedPreferences("shown4", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor5 = shown4.edit();
        editor5.putBoolean("chipsshown", chipsshown);
        editor5.commit();


        SharedPreferences shown5 = getSharedPreferences("shown5", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor6 = shown5.edit();
        editor6.putBoolean("eggsshown", eggsshown);
        editor6.commit();

        SharedPreferences shown6 = getSharedPreferences("shown6", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor7 = shown6.edit();
        editor7.putBoolean("fishshown", fishshown);
        editor7.commit();

        SharedPreferences shown7 = getSharedPreferences("shown7", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor8 = shown7.edit();
        editor8.putBoolean("tireshown", tireshown);
        editor8.commit();

        SharedPreferences shown8 = getSharedPreferences("shown8", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor9 = shown8.edit();
        editor9.putBoolean("papershown", papershown);
        editor9.commit();

        SharedPreferences shown9 = getSharedPreferences("shown9", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor10 = shown9.edit();
        editor10.putBoolean("milkshown", milkshown);
        editor10.commit();

        SharedPreferences shown10 = getSharedPreferences("shown10", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor11 = shown10.edit();
        editor11.putBoolean("leafshown", leafshown);
        editor11.commit();

        SharedPreferences shown11 = getSharedPreferences("shown11", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor12 = shown11.edit();
        editor12.putBoolean("plasticshown", plasticshown);
        editor12.commit();

        SharedPreferences shown12 = getSharedPreferences("shown12", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor13 = shown12.edit();
        editor13.putBoolean("teabagshown", teabagshown);
        editor13.commit();

        SharedPreferences shown13 = getSharedPreferences("shown13", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor14 = shown13.edit();
        editor14.putBoolean("wireshown", wireshown);
        editor14.commit();

        SharedPreferences shown14 = getSharedPreferences("shown14", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor15 = shown14.edit();
        editor15.putBoolean("tissueshown", tissueshown);
        editor15.commit();

        SharedPreferences shown15 = getSharedPreferences("shown15", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor16 = shown15.edit();
        editor16.putBoolean("styroshown", styroshown);
        editor16.commit();


        //Upadate high score
        if (score > highScore)

        {
            highScore = score;
            highScoreLabel.setText("BEST: " + highScore);
            highScoreLabel2.setText("BEST: " + highScore);

            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("HIGH_SCORE", highScore);
            editor.commit();
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (start_flg) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) { // action_up means screen is pressed
                action_flg = true;
            } else if (event.getAction() == MotionEvent.ACTION_UP) { // pressed gesture is released
                action_flg = false;
            }
        }
        return true;
    }


    public void addDotsIndicator(int position) {
        mDots = new TextView[3];
        mDotLayout.removeAllViews();

        for (int i = 0; i < mDots.length; i++) {
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(50);
            mDots[i].setTextColor(getResources().getColor(R.color.colorBlack));
//            mDots[position].setTextColor(getResources().getColor(R.color.colorTransparentWhite));

            mDotLayout.addView(mDots[i]);
        }

        if (mDots.length > 0) {
            mDots[position].setTextColor(getResources().getColor(R.color.colorDivider));
//            mDots[position].setTextColor(getResources().getColor(R.color.colorTransparentWhite));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int i) {
            addDotsIndicator(i);
            mCurrentPage = i;

            arrow.setVisibility(View.VISIBLE);

//            Toast.makeText(MainActivity.this, "i = " +i, Toast.LENGTH_SHORT).show();
            if (i == 0) {
                mNextBtn.setEnabled(false);
                mNextBtn.setVisibility(View.GONE);
                arrow.setVisibility(View.VISIBLE);

                // last slide
            } else if (i == mDots.length - 1) {
                mNextBtn.setEnabled(true);
                mNextBtn.setVisibility(View.VISIBLE);
                arrow.setVisibility(View.INVISIBLE);
            } else {
                mNextBtn.setEnabled(false);
                mNextBtn.setVisibility(View.GONE);
                arrow.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

}




