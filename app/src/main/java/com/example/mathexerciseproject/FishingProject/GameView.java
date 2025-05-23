package com.example.mathexerciseproject.FishingProject;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.example.mathexerciseproject.R;

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
    private FishingActivity parentActivity;

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
            //"fish1.getXPosition()-fish1.getRadius()/2" is to be more forgivingto the player, this way you only need the majority of the fish to overlap the bar
            if(fish1.getXPosition()-fish1.getRadius()/2>bar.getBarX() && fish1.getXPosition()+fish1.getRadius()/2<bar.getBarX()+bar.getBarLength()){
                pBar.setOverLap(true);
            }else{
                pBar.setOverLap(false);
            }
            if(pBar.getIsFinish()){
                triggerGameConclusion();
            }
        }
    }
    public void triggerGameConclusion(){
        if(pBar.getIsFinish()){
            stopGameLoop();
            parentActivity.onGameConclusion();
        }
    }

    public GameView(Context context) {
        super(context);
        this.parentActivity = (FishingActivity) context;
        init();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.parentActivity = (FishingActivity) context;
        init();
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.parentActivity = (FishingActivity) context;
        init();
    }

    private void init() {
        boolean Gravity = true;
        float fishStartX = 100f;
        float fishSpeed = 10f;
        fish1 = new fish1(fishStartX, fishSpeed, 100f, 1000f); //left and right are to set the boundaries of the fish
        fish1.getPaint().setColor(Color.rgb(0,102,204));
        bar = new Bar(100, fishSpeed+3, Gravity, 800f, 0);
        pBar = new ProgressBar(130, 630, false, 3, gameHeight, bar.getBarRBound());
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
        Log.d("status DDD", "h = "+h+"  w = "+w);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (canvas != null) {
            Bitmap bitmap = getRescaledFishIcon();
            canvas.drawColor(Color.rgb(0,51,102));

            float fishX = fish1.getXPosition();
            canvas.drawRect(100, gameHeight-50, 1000f, gameHeight+50, fish1.getPaint());
            canvas.drawRect(pBar.getRectLeft(),pBar.getRectTop(),pBar.getRectRight(),pBar.getRectBottom(),pBar.getPaint());
            canvas.drawRect(bar.getBarX(), gameHeight - 50, bar.getBarX() + bar.getBarLength(), gameHeight + 50, bar.getPaint());
            canvas.drawBitmap(bitmap, fishX-bitmap.getWidth()/2f, gameHeight-bitmap.getHeight()/2f, fish1.getPaint());
//            canvas.drawCircle(fishX, gameHeight, fish1.getRadius(), fish1.getPaint());

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
    public boolean getIsCaught(){
        return pBar.isCaught();
    }
    /*
    setters
     */
    public void setIsFish(boolean isFish){
        bar.setPressing(isFish);
    }

    public Bitmap getRescaledFishIcon(){
        Resources res = getResources();
        Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.fish_icon1);
        int newWidth = 100;
        int newHeight = 100;
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true);
        return scaledBitmap;
    }
}