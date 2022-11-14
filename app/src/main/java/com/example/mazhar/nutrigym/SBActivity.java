package com.example.mazhar.nutrigym;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;


public class SBActivity extends AppCompatActivity {

    ImageView imageButton;
    Button button6;
    Random r;
    int angle;
    boolean restart=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sb);

        r = new Random();
        imageButton = (ImageView) findViewById(R.id.imageButton);
        button6 = (Button) findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(restart){
                    int temp=angle%360;
                    angle = r.nextInt() + 360;
                    RotateAnimation rotate= new RotateAnimation(temp,360,RotateAnimation.RELATIVE_TO_SELF, 0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
                    rotate.setFillAfter(true);
                    rotate.setDuration(1000);
                    rotate.setInterpolator(new AccelerateDecelerateInterpolator());
                    imageButton.startAnimation(rotate);
                    restart=false;
                }
                else{
                    int temp=angle%360;
                    angle = r.nextInt() + 360;
                    RotateAnimation rotate= new RotateAnimation(0,angle,RotateAnimation.RELATIVE_TO_SELF, 0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
                    rotate.setFillAfter(true);
                    rotate.setDuration(3600);
                    rotate.setInterpolator(new AccelerateDecelerateInterpolator());
                    imageButton.startAnimation(rotate);
                    restart=true;
                }
            }
        });
    }
}
