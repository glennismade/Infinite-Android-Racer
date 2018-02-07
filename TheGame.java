package uk.ac.reading.sis05kol.mooc;

//Other parts of the android libraries that we use
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import java.io.File;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Random;
import java.util.Timer;

public class TheGame extends GameThread{

    public String ScoreFile = "scores.txt";
   // public FileOutputStream  files = openFileOutput();



    //bitmaps for all the objects on the screen
    private Bitmap mBall;
    private Bitmap mEnemy;
    private Bitmap mSpeedBoost;
    private Bitmap mBomb;
    ///////////////////

    public static int i;
    public boolean collide = false;
    public boolean collected = false;
    public float BackgroundY = 0, BackgroundX = 0;
   // public float globalScore = score;
//    private Background bg;


    private float Ewidth = 150;
    private float Eheight = 300;
    private float Mwidth = 128;
    private float Mheight = 128;
    private float Bwidth = 85;
    private float Bheight = 85;

    //The X and Y position of the ball on the screen (middle of ball)
    private float mBallX = mCanvasWidth /2;
    private float mBallY = mCanvasHeight /2;
    private float mEnemyX = mCanvasWidth /2;
    private float mEnemyY = mCanvasHeight /2;
    private float mSpeedBoostX = mCanvasWidth /2;
    private float mSpeedBoostY = mCanvasHeight /2;
    private float mBombX = mCanvasWidth /2;
    private float mBombY = mCanvasHeight /2;

    //The speed (pixel/second) of the ball in direction X and Y
    private float mBallSpeedX = 0;
    private float mBallSpeedY = 0;
    private float mEnemySpeedX = 0;
    private float mEnemySpeedY = 1;
    private float mBombSpeedX = 0;
    private float mBombSpeedY = 0;

    //This is run before anything else, so we can prepare things here
    public TheGame(GameView gameView) {
        //House keeping
        super(gameView);

        //Prepare the image so we can draw it on the screen (using a canvas)
        mBall = BitmapFactory.decodeResource (gameView.getContext().getResources(), R.drawable.car); //player car
        mEnemy = BitmapFactory.decodeResource (gameView.getContext().getResources(), R.drawable.car2); //enemy car
        mSpeedBoost = BitmapFactory.decodeResource (gameView.getContext().getResources(), R.drawable.speedboost); //speed boost image
        mBomb = BitmapFactory.decodeResource (gameView.getContext().getResources(), R.drawable.sad_ball); // bomb

      //  mBackgroundImage.setVector(-5);
    }

    //This is run before a new game (also after an old game)
    @Override
    public void setupBeginning() {
        //Initialise speeds
        mBallSpeedX = 0;
        mBallSpeedY = 0;
        mEnemySpeedY =-500;
        mEnemySpeedX =1;
        mBombSpeedY = 5;
        mBombSpeedX = 0;

        //Place the ball in the middle of the screen.
        //mBall.Width() and mBall.getHeigh() gives us the height and width of the image of the ball
        mBallX = mCanvasWidth / 2;
        mBallY = mCanvasHeight / 2;
        mEnemyX = mCanvasWidth /7;
        mEnemyY = mCanvasHeight /2;
        mSpeedBoostY =mCanvasHeight /5;
        mSpeedBoostX = mCanvasWidth /2;
        mBombX = mCanvasWidth /2;
        mBombY = mCanvasHeight /5;

    }


    @Override
    protected void doDraw(Canvas canvas) {



        //If there isn't a canvas to draw on do nothing
        //It is ok not understanding what is happening here
        if(canvas == null) return;

        super.doDraw(canvas);
        canvas.drawBitmap(mBackgroundImage, BackgroundX, BackgroundY, null); //background image


        //draw the image of the ball using the X and Y of the ball
        //drawBitmap uses top left corner as reference, we use middle of picture
        //null means that we will use the image without any extra features (called Paint)
        canvas.drawBitmap(mBall, mBallX - mBall.getWidth() / 2, mBallY - mBall.getHeight() / 2, null); // drawing the car
        canvas.drawBitmap(mEnemy, mEnemyX - mEnemy.getWidth() /4, mEnemyY - mEnemy.getHeight() /7, null); //drawing the AI car
        canvas.drawBitmap(mBomb, mBombX - mBomb.getWidth() /6, mBombY - mBomb.getHeight() /randomPos(), null); // drawing the sad Face

       //if(!collide){
           canvas.drawBitmap(mSpeedBoost, mSpeedBoostX - mSpeedBoost.getWidth() /2, mSpeedBoostY - mSpeedBoost.getHeight() /6, null); //drawing the point bonus
       //}


//        if (mCanvasHeight <0) {
          //  canvas.drawBitmap(mBackgroundImage, mCanvasHeight+MainActivity.HEIGHT, mCanvasWidth, null);

//        }

        if(mEnemyY <-5){ //this resets the AI cars Y position to below the screen
            mEnemyY = mCanvasHeight;
            mEnemyX = randomEnemyX();
            canvas.drawBitmap(mEnemy, mEnemyX - mEnemy.getWidth() /randomEnemyX(), mEnemyY -mEnemy.getHeight() /7, null);
        }


        float scrollFactor = 0.5f; //Moves the background 0.5 pixels up per call of the draw method

        this.mCanvasHeight += scrollFactor; // this checks the canvas height and add the scroll factor to it.


    }

    //This is run whenever the phone is touched by the user

	@Override
	protected void actionOnTouch(float x, float y) {
		//Increase/decrease the speed of the ball making the ball move towards the touch

        mBallSpeedX = x - mBallX; // user car left and right controlls
		//mBallSpeedY = y - mBallY; // disables to prevent user car from leavign screen
	}



//	This is run whenever the phone moves around its axises
//	@Override
//	protected void actionWhenPhoneMoved(float xDirection, float yDirection, float zDirection) {
//		/*
//		Increase/decrease the speed of the ball.
//		If the ball moves too fast try and decrease 70f
//		If the ball moves too slow try and increase 70f
//		 */
//
//		mBallSpeedX = mBallSpeedX + 72f * xDirection;
//		mBallSpeedY = mBallSpeedY - 72f * yDirection;
//	}

    public float randomPos(){ // random method to generate random x value for point bonus and sad face
        Random r = new Random();
        return r.nextInt(mCanvasWidth-1)+1;
    }

    public float randomEnemyX(){ // random method to generate random x value for AI car
        Random r = new Random();
        return r.nextInt(mCanvasWidth-2)+2;
    }

    public float randomSpeed(){
        Random Speed = new Random();
        return Speed.nextInt((int) (mEnemySpeedY-1))+1;
    }


    //This is run just before the game "scenario" is printed on the screen
    @Override
    protected void updateGame(float secondsElapsed) {
        //Move the ball's X and Y using the speed (pixel/sec)
        mBallX = mBallX + secondsElapsed * mBallSpeedX; //sets the user car's speed
        mBallY = mBallY + secondsElapsed * mBallSpeedY; // this does the same but for Y


        //mEnemyY = mEnemyY * mEnemySpeedY;
        mEnemyY = mEnemyY + secondsElapsed * mEnemySpeedY; // sets the AI car speed x
        mBombY = mBombY + secondsElapsed * mBombSpeedY; // sets the AI car speed Y


        if (getMode() == GameThread.STATE_RUNNING) { // method to check if the game is currently in a running state (something happening) if so the following happens
            mEnemyY --;//oponent car is moved up the screen towards the top
            mSpeedBoostY += 5; //point bonus (bolt) is moved down the screen at 5 speed
            BackgroundY += 5; // the background image is moved down the screen at +5
            mBombY += 5; // the sad face is moved down the screen at +5

            if (mSpeedBoostY > mCanvasHeight) { // used to check is AI cars Y pos is above screen
                mSpeedBoostY = 0; // if so resets it
            }
            if (mBombY > mCanvasHeight) { // checks sad face's Y pos
                mBombY = 0; // if below screen reset
                mBombX = randomPos(); // set X pos to random value
            }

            for (i = 0; i < secondsElapsed; i++) {
                updateScore(1); // calls the update score method to update the score every second that player is alive.

            }


        }

        if (BackgroundY > mCanvasHeight) { // checks the backgroung images y pos (from top left of image)
            BackgroundY = 0; // if below screen top (top of canvas) reset to 0
        }


        if (isCollide(mSpeedBoostX, mSpeedBoostY // used to check for collision, if the method isCollide == true, set collide to true, and add points
        )) {
            collide = true;

            if (!collected) { // if true, score gets updated + 2000
                updateScore(2000);
                collected = true;
                mSpeedBoostY = 0; //reset point bonus Y pos
                mSpeedBoostX = randomPos();// reset point bonus X pos to random value
                //collide = false;
            }

        }

        if (isCollide(mBombX, mBombY)) {
            setState(GameThread.STATE_LOSE); // ends the game if the player contacts sad face
            setScore(score); //updates the score.

        }


        if (collected) { // if point bonus = colected, reprint it to defailt X&Y pos
            mSpeedBoostY = mCanvasHeight;
            mSpeedBoostX = mCanvasWidth;
        }

        if (isCollide(mEnemyX, mEnemyY)) {
            setState(GameThread.STATE_LOSE); //if player and AI car collide end game
            collide = false;
        }
//
//        if(mBallX > mCanvasWidth) {
//            setState(GameThread.STATE_LOSE);
//        }
//        if(mBallX < mCanvasWidth) {
//            setState(GameThread.STATE_LOSE);
//        }

    }

    public void getSize() {
        mSpeedBoost.getHeight();
        mSpeedBoost.getWidth();

      // float size = mSpeedBoost.getHeight() * mSpeedBoost.getWidth(); //doesnt work.
    }


    public boolean ballCollide(){
        return mBallX >= mSpeedBoostX && mBallX <= mSpeedBoostX + Bwidth && mBallY >= mSpeedBoostY && mBallY <= mSpeedBoostY + Bheight;
    }

    public boolean bombCollide(){
        return mBallX > mBombX && mBallX < mBombX + Mwidth && mBallY > mBombY && mBallY < mBombY + Mheight;
    }

    public boolean isCollide(float x2, float y2) {
        return mBallX +64  >= x2 && mBallX +64 <= x2 + Bwidth + 64 && mBallY +64 >= y2 && mBallY +64 <= y2 + Bheight +64; //generic collision detection boolean for all objects.
    }





    public void setVector(int dx) {
        this.dx=dx;
    }

}
