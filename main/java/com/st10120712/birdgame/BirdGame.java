package com.st10120712.birdgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class BirdGame extends View {

    private Bitmap background;
    private Bitmap bird;
    private Paint level, score, blueBall, blackBall;
    private int canvasWidth, canvasHeight, birdHeight, playerScore;
    private int birdX = 10, birdY = 10;
    private int blueX = 0, blueY = 0;
    private int blackX = 0, blackY = 0;
    private int birdSpeed = 10;

    public BirdGame(Context context) {
        super(context);

        background = BitmapFactory.decodeResource(getResources(), R.drawable.villagescreensize);
        bird = BitmapFactory.decodeResource(getResources(), R.drawable.wingsup);

        //Set the attributes of the text
        level = new Paint();
        level.setColor(Color.GREEN);
        level.setAntiAlias(true);
        level.setTypeface(Typeface.DEFAULT_BOLD);
        level.setTextAlign(Paint.Align.CENTER);
        level.setTextSize(50);

        score = new Paint();
        score.setColor(Color.GREEN);
        score.setAntiAlias(true);
        score.setTypeface(Typeface.DEFAULT_BOLD);
        score.setTextSize(50);

        //Set the colour for the balls
        blueBall = new Paint();
        blueBall.setColor(Color.BLUE);
        blackBall = new Paint();
        blackBall.setColor(Color.BLACK);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //Set the canvas coordinates based on the screen size
        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();
        birdY += birdSpeed;
        birdHeight = bird.getHeight();

        //Setting the bounds of the bird on the canvas
        if (birdY > canvasHeight - birdHeight)
        {
            birdY = canvasHeight - birdHeight;
        }
        if (birdY < birdHeight)
        {
            birdY = birdHeight;
        }
        birdSpeed += 2;

        //Making the balls fly
        blueX -= 15;
        if (blueX <= 0)
        {
            blueX = canvasWidth + 20;
            blueY = (int)(Math.random() * canvasHeight - birdHeight);
        }
        blackX -= 30;
        if (blackX <= 0)
        {
            blackX = canvasWidth + 20;
            blackY = (int)(Math.random() * canvasHeight - birdHeight);
        }

        //Setting the collisions
        if (collision(blueX, blueY))
        {
            playerScore += 100;
            blueX = 0;
        }
        if (collision(blackX, blackY))
        {
            Toast.makeText(getContext(), "BOOM", Toast.LENGTH_SHORT).show();
            birdX = 0;
        }

        //Adding the background image
        canvas.drawBitmap(background, 0, 0, null);
        //Adding the bird onto the screen
        canvas.drawBitmap(bird, birdX, birdY, null);
        //Adding the balls onto the screen
        canvas.drawCircle(blueX, blueY, 25, blueBall);
        canvas.drawCircle(blackX, blackY, 40, blackBall);
        //Adding the text onto the screen
        canvas.drawText("Level: 1", 120, 120, level);
        canvas.drawText("Score: " + playerScore, canvasWidth-250, 120, score);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            birdSpeed = -20;
        }
        return true;
    }

    public boolean collision(int xCoord, int yCoord)
    {
        int birdLeft, birdRight, birdTop, birdBottom;
        birdLeft = birdX;
        birdRight = birdX + bird.getWidth();
        birdTop = birdY;
        birdBottom = birdY + bird.getHeight();

        if (birdLeft < xCoord && xCoord < birdRight && birdTop < yCoord && yCoord < birdBottom)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
