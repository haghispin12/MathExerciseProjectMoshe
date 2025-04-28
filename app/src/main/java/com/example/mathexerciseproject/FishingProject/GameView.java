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

    private fish1 fish1;
    private Paint fishPaint;
    private Paint barPaint;
    private float barY;
    private float barLeft;
    private float barRight;
    private float barLength = 200;
    private float barPositionX;

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
        fishPaint = new Paint();
        fishPaint.setColor(Color.BLUE);

        barPaint = new Paint();
        barPaint.setColor(Color.GREEN);

        barY = getHeight() / 2f;
        barPositionX = (getWidth() - barLength) / 2f;
        barLeft = barPositionX;
        barRight = barPositionX + barLength;

        float fishStartX = getWidth() / 2f;
        float fishSpeed = 10; // Adjusted to match baseSpeed in Fish
        fish1 = new fish1(fishStartX, fishSpeed, 0, getWidth());

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
        barY = h / 2f;
        barPositionX = (w - barLength) / 2f;
        barLeft = barPositionX;
        barRight = barPositionX + barLength;
        fish1.leftBound = 0;
        fish1.rightBound = w;
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
            canvas.drawCircle(fishX, barY, 30, fishPaint);

            canvas.drawRect(barPositionX, barY - 50, barPositionX + barLength, barY + 50, barPaint);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        handler.removeCallbacks(gameLoop);
        isLooping = false;
    }
}