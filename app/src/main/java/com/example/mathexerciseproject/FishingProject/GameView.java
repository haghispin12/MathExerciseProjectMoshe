package com.example.mathexerciseproject.FishingProject;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class GameView extends View {
    private Bar bar;
    private ProgressBar pBar;
    private fish1 fish1;
    private final float gameHeight  = 992;
    private Handler handler = new Handler();
    private GameUpdateRunnable gameLoop = new GameUpdateRunnable();
    private boolean isLooping = false;
    private int scoreTime;

    private class GameUpdateRunnable implements Runnable {
        @Override
        public void run() {
            fish1.updatePosition();
            bar.updatePosition();
            pBar.updatePosition();
            invalidate();
            if (isLooping) {
                handler.postDelayed(this, 30);
            }
            if(fish1.getXPosition()>bar.getBarX() && fish1.getXPosition()<bar.getBarX()+bar.getBarLength()){
                pBar.setOverLap(true);
            }else{
                pBar.setOverLap(false);
            }
        }
    }

    public GameView(Context context) {
        super(context);
        init();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        boolean Gravity = true;
        float fishStartX = 130;
        float fishSpeed = 10f; // Adjusted to match baseSpeed in Fish
        fish1 = new fish1(fishStartX, fishSpeed, 130f, 1000f); //the number 130 is keeping in mind the radius of the circle 30f
        bar = new Bar(100, fishSpeed+5, Gravity, 800f, 0);
        pBar = new ProgressBar(130, 400, false, 3, gameHeight, bar.getBarRBound());
        fish1.getPaint().setColor(Color.BLUE);
        bar.getPaint().setColor(Color.GREEN);
        pBar.getPaint().setColor(Color.RED);
        scoreTime = pBar.getScoreTime();
        startGameLoop();
    }

    public void startGameLoop() {
        Log.d("GameView", "startGameLoop() called");
        if (!isLooping) {
            isLooping = true;
            handler.postDelayed(gameLoop, 30);
        } else {
        }
    }

    public void stopGameLoop() {
        isLooping = false;
        handler.removeCallbacks(gameLoop);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        fish1.leftBound = 130;
        fish1.rightBound = w -130;
        Log.d("status DDD", "h = "+h+"  w = "+w);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (canvas != null) {
            canvas.drawColor(Color.CYAN);

            float fishX = fish1.getXPosition();
            canvas.drawRect(pBar.getRectLeft(),pBar.getRectTop(),pBar.getRectRight(),pBar.getRectBottom(),pBar.getPaint());
            canvas.drawRect(bar.getBarX(), gameHeight - 50, bar.getBarX() + bar.getBarLength(), gameHeight + 50, bar.getPaint());
            canvas.drawCircle(fishX, gameHeight, fish1.getRadius(), fish1.getPaint());
            canvas.drawRect(100, gameHeight+50, 1000f, gameHeight, fish1.getPaint());
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        handler.removeCallbacks(gameLoop);
        isLooping = false;
    }

    /*
    getters
     */

    public int getScoreTime() {
        return scoreTime;
    }
    /*
    setters
     */
    public void setIsFish(boolean isFish){
        bar.setPressing(isFish);
    }
}