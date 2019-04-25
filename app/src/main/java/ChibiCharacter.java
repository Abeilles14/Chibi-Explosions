/*
 * simulates character in the game
 */

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class ChibiCharacter extends GameObject {
    private static final int ROW_TOP_TO_BOTTOM = 0;         //bitmap subImage rows actions
    private static final int ROW_RIGHT_TO_LEFT = 1;
    private static final int ROW_LEFT_TO_RIGHT = 2;
    private static final int ROW_BOTTOM_TO_TOP = 3;

    //Row index of image being used
    private int rowUsing = ROW_LEFT_TO_RIGHT;
    private int colUsing;

    private Bitmap[] leftToRights;                //arrays for each row and motion of chara
    private Bitmap[] rightToLefts;
    private Bitmap[] topToBottoms;
    private Bitmap[] bottomToTops;

    //velocity of game character (pixel/millisecond)
    public static final float VELOCITY = 0.1f;

    private int movingVectorX = 10;
    private int movingVectorY = 5;

    private long lastDrawNanoTime = -1;

    private GameSurface gameSurface;

    //constructor
    public ChibiCharacter(GameSurface gameSurface, Bitmap image, int x, int y) {
        super(image, 4, 3, x, y);                   //main chara has 4 rows, 3 col, and width/height

        this.gameSurface = gameSurface;

        this.leftToRights = new Bitmap[colCount];       //assigns rows of chibi to moves
        this.rightToLefts = new Bitmap[colCount];
        this.topToBottoms = new Bitmap[colCount];
        this.bottomToTops = new Bitmap[colCount];

        for (int col = 0; col < this.colCount; col++) {
            this.topToBottoms[col] = this.createSubImageAt(ROW_TOP_TO_BOTTOM, col);     //array of each individual chibi movement
            this.rightToLefts[col] = this.createSubImageAt(ROW_RIGHT_TO_LEFT, col);
            this.leftToRights[col] = this.createSubImageAt(ROW_LEFT_TO_RIGHT, col);
            this.bottomToTops[col] = this.createSubImageAt(ROW_BOTTOM_TO_TOP, col);
        }
    }

    public Bitmap[] getMoveBitmaps() {
        switch (rowUsing) {                         //starts with ROW_LEFT_TO_RIGHT
            case ROW_BOTTOM_TO_TOP:
                return this.bottomToTops;
            case ROW_LEFT_TO_RIGHT:
                return this.leftToRights;
            case ROW_RIGHT_TO_LEFT:
                return this.rightToLefts;
            case ROW_TOP_TO_BOTTOM:
                return this.topToBottoms;
            default:
                return null;
        }
    }

    public Bitmap getCurrentMoveBitmap() {          //returns current movement
        Bitmap[] bitmaps = this.getMoveBitmaps();
        return bitmaps[this.colUsing];
    }

    public void update() {                          //loops walking, alternates walk
        this.colUsing++;
        if (colUsing >= this.colCount) {
            this.colUsing = 0;
        }

        //current time in nanoseconds
        long now = System.nanoTime();

        if (lastDrawNanoTime == -1) {
            lastDrawNanoTime = now;
        }

        //change nanoseconds to milliseconds (1 nanosecond = 1000000 milliseconds)
        int deltaTime = (int) ((now - lastDrawNanoTime) / 1000000);

        //distance moved
        float distance = VELOCITY * deltaTime;

        //magnitude of distance vector
        double movingVectorLength = Math.sqrt((movingVectorX * movingVectorX) + (movingVectorY * movingVectorY));

        //calculate the new position of the game character
        this.x = x + (int) (distance * movingVectorX / movingVectorLength);
        this.y = y + (int) (distance * movingVectorY / movingVectorLength);

        //if chara touches horizontal edge of screen, change x direction
        if(this.x <0) {
            this.x = 0;
            this.movingVectorX = -this.movingVectorX;
        } else if (this.x > this.gameSurface.getWidth() - width) {
            this.movingVectorX = -this.movingVectorX;
        }
        //if chara touches vertical edge of screen, change y direction
        if(this.y <0) {
            this.y = 0;
            this.movingVectorY = -this.movingVectorY;
        } else if (this.y > this.gameSurface.getHeight() - height) {
            this.movingVectorY = -this.movingVectorY;
        }

        //chose what row to use if diagonal vector
        if(movingVectorX > 0) {
            if (movingVectorY > 0 && Math.abs(movingVectorX) < Math.abs(movingVectorY)) {
                this.rowUsing = ROW_TOP_TO_BOTTOM;
            } else if(movingVectorY < 0 && Math.abs(movingVectorX) < Math.abs(movingVectorY)){
                this.rowUsing = ROW_BOTTOM_TO_TOP;
            } else {
                this.rowUsing = ROW_LEFT_TO_RIGHT
            }
        } else {
            if (movingVectorY > 0 && Math.abs(movingVectorX) < Math.abs(movingVectorY)) {
                this.rowUsing = ROW_TOP_TO_BOTTOM;
            } else if (movingVectorY < 0 && Math.abs(movingVectorX) < Math.abs(movingVectorY)) {
                this.rowUsing = ROW_BOTTOM_TO_TOP;
            } else {
                this.rowUsing = ROW_RIGHT_TO_LEFT;
            }
        }
    }

    public void draw(Canvas canvas) {
        Bitmap  bitmap = this.getCurrentMoveBitmap();
        canvas.drawBitmap(bitmap,x,y,null);
        //last draw time
        this.lastDrawNanoTime = System.nanoTime();
    }

    public void setMovingVector(int movingVectorX, int movingVectorY) {
        this.movingVectorX = movingVectorX;
        this.movingVectorY = movingVectorY;
    }
}
