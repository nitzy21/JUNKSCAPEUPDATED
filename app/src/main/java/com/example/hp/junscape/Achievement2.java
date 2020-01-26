package com.example.hp.junscape;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Achievement2 extends AppCompatActivity {

    ImageView banana1, can2, cigar3, chips4, egg5, fish6, tire7, paper8, milk9, leaf10, plastic11, teabag12, wire13,
            tissure14, styro15, btnClose, Desc, backkk;

    int score;
    boolean clickable = false;
    RelativeLayout layoutDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievement2);


        backkk = (ImageView) findViewById(R.id.backkk);
        banana1 = (ImageView) findViewById(R.id.banana1);
        can2 = (ImageView) findViewById(R.id.can2);
        cigar3 = (ImageView) findViewById(R.id.cigar3);
        chips4 = (ImageView) findViewById(R.id.chips4);
        egg5 = (ImageView) findViewById(R.id.egg5);
        fish6 = (ImageView) findViewById(R.id.fish6);
        tire7 = (ImageView) findViewById(R.id.tire7);
        paper8 = (ImageView) findViewById(R.id.paper8);
        milk9 = (ImageView) findViewById(R.id.milk9);
        leaf10 = (ImageView) findViewById(R.id.leaf10);
        plastic11 = (ImageView) findViewById(R.id.plastic11);
        teabag12 = (ImageView) findViewById(R.id.teabag12);
        wire13 = (ImageView) findViewById(R.id.wire13);
        tissure14 = (ImageView) findViewById(R.id.tissue14);
        styro15 = (ImageView) findViewById(R.id.styro15);
        btnClose = (ImageView) findViewById(R.id.btnClose);
        Desc = (ImageView) findViewById(R.id.Desc);
        layoutDesc = (RelativeLayout) findViewById(R.id.layoutDesc);

        SharedPreferences myScore = getSharedPreferences("myScore", Context.MODE_PRIVATE);
        score = myScore.getInt("score",0);
//        Toast.makeText(this, "score = " + score, Toast.LENGTH_SHORT).show();

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutDesc.setVisibility(View.INVISIBLE);
            }
        });

        backkk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        banana1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(score>=10){
                    banana1.setImageResource(R.drawable.bbb);
                    layoutDesc.setVisibility(View.VISIBLE);
                    Desc.setImageResource(R.drawable.infobanana);
                } else {
                    Toast.makeText(Achievement2.this, "Earn 10 Points to Unlock!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        can2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(score>=25){
                        can2.setImageResource(R.drawable.can);
                        layoutDesc.setVisibility(View.VISIBLE);
                        Desc.setImageResource(R.drawable.infocan);
                    } else {
                        Toast.makeText(Achievement2.this, "Earn 25 Points to Unlock!", Toast.LENGTH_SHORT).show();
//                        FloatingAction
                    }
                }
            });


        cigar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(score>=40){
                    cigar3.setImageResource(R.drawable.cigar2);
                    layoutDesc.setVisibility(View.VISIBLE);
                    Desc.setImageResource(R.drawable.infocigar);
                } else {
                    Toast.makeText(Achievement2.this, "Earn 40 Points to Unlock!", Toast.LENGTH_SHORT).show();
//                        FloatingAction
                }
            }
        });


        chips4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(score>=65){
                    chips4.setImageResource(R.drawable.chips2);
                    layoutDesc.setVisibility(View.VISIBLE);
                    Desc.setImageResource(R.drawable.infochips);
                } else {
                    Toast.makeText(Achievement2.this, "Earn 65 Points to Unlock!", Toast.LENGTH_SHORT).show();
//                        FloatingAction
                }
            }
        });

        egg5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(score>=80){
                    egg5.setImageResource(R.drawable.egg);
                    layoutDesc.setVisibility(View.VISIBLE);
                    Desc.setImageResource(R.drawable.infoegg);
                } else {
                    Toast.makeText(Achievement2.this, "Earn 80 Points to Unlock!", Toast.LENGTH_SHORT).show();
//                        FloatingAction
                }
            }
        });

        fish6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(score>=100){
                    fish6.setImageResource(R.drawable.fish);
                    layoutDesc.setVisibility(View.VISIBLE);
                    Desc.setImageResource(R.drawable.infofish);
                } else {
                    Toast.makeText(Achievement2.this, "100 Points to Unlock!", Toast.LENGTH_SHORT).show();
//                        FloatingAction
                }
            }
        });

        tire7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(score>=125){
                    tire7.setImageResource(R.drawable.tire);
                    layoutDesc.setVisibility(View.VISIBLE);
                    Desc.setImageResource(R.drawable.infotire);
                } else {
                    Toast.makeText(Achievement2.this, "Earn 125 Points to Unlock!", Toast.LENGTH_SHORT).show();
//                        FloatingAction
                }
            }
        });

        paper8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(score>=150){
                    paper8.setImageResource(R.drawable.paper);
                    layoutDesc.setVisibility(View.VISIBLE);
                    Desc.setImageResource(R.drawable.infopaper);
                } else {
                    Toast.makeText(Achievement2.this, "Earn 150 Points to Unlock!", Toast.LENGTH_SHORT).show();
//                        FloatingAction
                }
            }
        });

        milk9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(score>=200){
                    milk9.setImageResource(R.drawable.milkkk);
                    layoutDesc.setVisibility(View.VISIBLE);
                    Desc.setImageResource(R.drawable.infomilk);
                } else {
                    Toast.makeText(Achievement2.this, "Earn 200 Points to Unlock!", Toast.LENGTH_SHORT).show();
//                        FloatingAction
                }
            }
        });


        leaf10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(score>=250){
                    leaf10.setImageResource(R.drawable.leaf2);
                    layoutDesc.setVisibility(View.VISIBLE);
                    Desc.setImageResource(R.drawable.infoleaf);
                } else {
                    Toast.makeText(Achievement2.this, "Earn 250 Points to Unlock!", Toast.LENGTH_SHORT).show();
//                        FloatingAction
                }
            }
        });

        plastic11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(score>=300){
                    plastic11.setImageResource(R.drawable.plastic);
                    layoutDesc.setVisibility(View.VISIBLE);
                    Desc.setImageResource(R.drawable.infoplastic);
                } else {
                    Toast.makeText(Achievement2.this, "Earn 300 Points to Unlock!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        teabag12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(score>=350){
                    teabag12.setImageResource(R.drawable.tea2);
                    layoutDesc.setVisibility(View.VISIBLE);
                    Desc.setImageResource(R.drawable.infotea);
                } else {
                    Toast.makeText(Achievement2.this, "Earn 350 Points to Unlock!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        wire13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(score>=400){
                    wire13.setImageResource(R.drawable.wire);
                    layoutDesc.setVisibility(View.VISIBLE);
                    Desc.setImageResource(R.drawable.infowire);
                } else {
                    Toast.makeText(Achievement2.this, "Earn 400 Points to Unlock!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tissure14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(score>=450){
                    tissure14.setImageResource(R.drawable.tissue);
                    layoutDesc.setVisibility(View.VISIBLE);
                    Desc.setImageResource(R.drawable.infotissue);
                } else {
                    Toast.makeText(Achievement2.this, "Earn 450 Points to Unlock!", Toast.LENGTH_SHORT).show();
//                        FloatingAction
                }
            }
        });

        styro15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(score>=500){
                    styro15.setImageResource(R.drawable.styro);
                    layoutDesc.setVisibility(View.VISIBLE);
                    Desc.setImageResource(R.drawable.infostyro);
                } else {
                    Toast.makeText(Achievement2.this, "Earn 500 Points to Unlock!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        if (score >= 10){
            banana1.setImageResource(R.drawable.bbb);
        }
        if (score >=25){
            can2.setImageResource(R.drawable.can);
        }
        if(score >= 40){
            cigar3.setImageResource(R.drawable.cigar2);
        }
        if(score >= 65){
            chips4.setImageResource(R.drawable.chips2);
        }
        if(score >= 80){
            egg5.setImageResource(R.drawable.egg);
        }
        if(score >= 100){
            fish6.setImageResource(R.drawable.fish);
        }
        if(score >= 125){
            tire7.setImageResource(R.drawable.tire2);
        }
        if(score >= 150){
            paper8.setImageResource(R.drawable.paper);
        }
        if(score >= 200){
            milk9.setImageResource(R.drawable.milkkk);
        }
        if(score >= 250){
            leaf10.setImageResource(R.drawable.leaf2);
        }
        if(score >= 300){
            plastic11.setImageResource(R.drawable.plastic);
        }
        if(score >= 350){
            teabag12.setImageResource(R.drawable.tea2);
        }
        if(score >= 400){
            wire13.setImageResource(R.drawable.wire);
        }
        if(score >= 450){
            tissure14.setImageResource(R.drawable.tissue);
        }
        if(score >= 500){
            styro15.setImageResource(R.drawable.styro);
        }

    }

}
