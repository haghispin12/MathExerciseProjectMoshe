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

public class GameView extends View {
    private Bar bar;

    private fish1 fish1;
    private float gameHeight; // Keep track of the fish's vertical position
    private Handler handler = new Handler();
    private GameUpdateRunnable gameLoop = new GameUpdateRunnable();
    private boolean isLooping = false;

    public fish1 getFish1() {
        return fish1;
    }

    private class GameUpdateRunnable implements Runnable {
        @Override
        public void run() {
            fish1.updatePosition();
            bar.updatePosition();
            invalidate();
            if (isLooping) {
                handler.postDelayed(this, 30);
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
        float fishStartX = 5;
        float fishSpeed = 10f; // Adjusted to match baseSpeed in Fish
        fish1 = new fish1(fishStartX, fishSpeed, 130f, getWidth()-100f); //the number 130 is keeping in mind the radius of the circle 30f
        bar = new Bar(400f, 10f, Gravity, getWidth()-200f, 5);

        fish1.getPaint().setColor(Color.BLUE);
        bar.getPaint().setColor(Color.GREEN);

        gameHeight = getHeight() / 2f; // Initialize fish Y position to the center




        startGameLoop();
    }

    public void startGameLoop() {
        Log.d("GameView", "startGameLoop() called");
        if (!isLooping) {
            isLooping = true;
            handler.postDelayed(gameLoop, 30);
            Log.d("GameView", "Game loop started");
        } else {
            Log.d("GameView", "Game loop already running");
        }
    }

    public void stopGameLoop() {
        isLooping = false;
        handler.removeCallbacks(gameLoop);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        gameHeight = h / 2f; // Ensure fish Y position is based on the actual view height
        fish1.leftBound = 130;
        fish1.rightBound = w -130;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // We'll implement touch handling here later
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (canvas != null) {
            canvas.drawColor(Color.CYAN);

            float fishX = fish1.getXPosition();

            canvas.drawRect(bar.getBarX(), gameHeight - 50, bar.getBarX() + bar.getBarLength(), gameHeight + 50, bar.getPaint());
            canvas.drawCircle(fishX, gameHeight, fish1.getRadius(), fish1.getPaint());

//            canvas.drawCircle(400, gameHeight+100, fishRadius, fishPaint);

        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        handler.removeCallbacks(gameLoop);
        isLooping = false;
    }
}