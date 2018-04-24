package com.example.landongerrits.a308mockup;

import android.graphics.Point;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class main extends AppCompatActivity {

    private TextView scoreLabel;
    private TextView startLabel;
    private ImageView crowd;
    private ImageView musicNote;

    private ImageView upArrow;
    private ImageView downArrow;
    private ImageView leftArrow;
    private ImageView rightArrow;

    // Score
    private int score = 0;

    // Position
    private int crowdX;

    private int upArrowPos;
    private int downArrowPos;
    private int leftArrowPos;
    private int rightArrowPos;


    // Size Constraints
    private int frameHeight;
    private int frameWidth; // not used yet

    private int screenWidth;
    private int screenHeight;

    private int crowdSize;

    // Initialize handler
    private Handler handler = new Handler();
    private Timer timer = new Timer();

    //Status Check
    private boolean action_flag = false;
    private boolean start_flag = false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreLabel = (TextView) findViewById(R.id.scoreLabel);
        startLabel = (TextView) findViewById(R.id.startLabel);
        crowd = (ImageView) findViewById(R.id.crowd);

        upArrow = (ImageView) findViewById(R.id.upArrow);
        downArrow = (ImageView) findViewById(R.id.downArrow);
        leftArrow = (ImageView) findViewById(R.id.leftArrow);
        rightArrow = (ImageView) findViewById(R.id.rightArrow);

        // get screen size
        WindowManager wm = getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);

        screenWidth = size.x;
        screenHeight = size.y;

        // set arrows off screen
        upArrow.setY(-80);
        downArrow.setY(-80);
        leftArrow.setY(-80);
        rightArrow.setY(-80);




    }

    public void hitCheck(){

        int upArrowCenterX = upArrowPos + upArrow.getWidth() / 2;
        int upArrowCenterY = upArrowPos + upArrow.getHeight() / 2;


        //incomplete finish this method
//        if(0 <= upArrowCenterX && upArrowCenterX <= crowdSize && crowdX <= upArrowCenterY && upArrowCenterY <= crowdX + crowdSize) {
//            upArrowPos = screenHeight + 10;
//            score += 100;
//        }

    }

    public void changePos() {

        hitCheck();

        // Spawn music note
//        musicNoteX -= 16;
//        if(musicNoteX < 0) {
//            musicNoteX = screenWidth + 20;
//            musicNoteY = (int) Math.floor(Math.random() * (frameHeight - musicNote.getHeight()));
//        }
//        musicNote.setX(musicNoteX);
//        musicNote.setY(musicNoteY);

        //spawn arrows

        //up arrow spawn
        upArrowPos += 14;
        if(upArrowPos > frameHeight){
            upArrowPos = -50;
        }
        upArrow.setY(upArrowPos);

        //down arrow spawn
        downArrowPos += 10;
        if(downArrowPos > frameHeight){
            downArrowPos = -50;
        }
        downArrow.setY(downArrowPos);

        //left arrow spawn
        leftArrowPos += 11;
        if(leftArrowPos > frameHeight){
            leftArrowPos = -50;
        }
        leftArrow.setY(leftArrowPos);

        //right arrow spawn
        rightArrowPos += 15;
        if(rightArrowPos > frameHeight){
            rightArrowPos = -50;
        }
        rightArrow.setY(rightArrowPos);



//        // Move crowd
//        if(action_flag) {
//            // touching
//            crowdX += 20;
//        } else {
//            // releasing
//            crowdX -= 20;
//        }
//
//
//        if(crowdX < 0) crowdX = 0;
////        if(crowdX >= frameWidth - crowdSize) crowdX = frameWidth - crowdSize;
//
//        crowd.setX(crowdX);


        scoreLabel.setText("Score : " + score);
    }

    public boolean onTouchEvent(MotionEvent me) {

        if (start_flag == false) {

            start_flag = true;

            FrameLayout frame = (FrameLayout) findViewById(R.id.frame);
            frameHeight = frame.getHeight();

            crowdX = (int)crowd.getX();

            // square icon so height = width
            crowdSize = crowd.getHeight();


            startLabel.setVisibility(View.GONE);

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            changePos();
                        }
                    });
                }
            }, 0,20);


        } else {
            if (me.getAction() == MotionEvent.ACTION_DOWN){
                action_flag = true;

            } else if(me.getAction() == MotionEvent.ACTION_UP) {
                action_flag = false;
            }
          }




        return true;
    }
}
