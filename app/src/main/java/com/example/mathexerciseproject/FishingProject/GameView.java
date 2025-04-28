package com.example.mathexerciseproject.FishingProject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class GameView extends View {

    private Fish fish;
    private Paint fishPaint;
//    private Paint barPaint;
    private float barY;
    private float barLeft;
    private float barRight;

    private Handler handler = new Handler();
    private GameUpdateRunnable gameLoop = new GameUpdateRunnable();
    private boolean isLooping = true; // Start looping by default

    private class GameUpdateRunnable implements Runnable {
        @Override
        public void run() {
            fish.updatePosition();
            invalidate(); // Request a redraw of the view
            if (isLooping) {
                handler.postDelayed(this, 30); // Run again if the loop is active
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
        fishPaint = new Paint();
        fishPaint.setColor(Color.BLUE);

//        barPaint = new Paint();
//        barPaint.setColor(Color.GREEN);

        barLeft = 100;
        barRight = 800;
        barY = 500;
        float fishStartX = (barLeft + barRight) / 2;
        float fishSpeed = 15;
        fish = new Fish(fishStartX, fishSpeed, barLeft, barRight);

        // Start the game loop when the view is initialized
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
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        canvas.drawRect(barLeft, barY - 50, barRight, barY + 50, barPaint);
        float fishX = fish.getXPosition();
        canvas.drawCircle(fishX, barY, 30, fishPaint);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        handler.removeCallbacks(gameLoop);
        isLooping = false;
    }
}