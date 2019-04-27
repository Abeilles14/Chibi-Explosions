import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
<<<<<<< HEAD
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.isabelle.androidgame2d.GameUpdate;
import com.isabelle.androidgame2d.R;

public class GameSurface extends SurfaceView implements SurfaceHolder.Callback {
    private GameUpdate gameUpdate;
    private ChibiCharacter chibi_nusair;

    public GameSurface(Context context) {
=======
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.isabelle.androidgame2d.R;

public class GameSurface extends SurfaceView implements SurfaceHolder.Callback{
    private GameThread gameThread;
    private ChibiCharacter nusair;

    public GameSurface(Context context){
>>>>>>> d6e2505ebfdcf17cece0cc2a90c8f7a7b337ad41
        super(context);
        //GameSurface focusable so it can handle events
        this.setFocusable(true);

        //set callback
        this.getHolder().addCallback(this);
    }

<<<<<<< HEAD
    public void update() {
        this.chibi_nusair.update();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        this.chibi_nusair.draw(canvas);
=======
    public void update(){
        this.nusair.update();
    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        this.nusair.draw(canvas);
>>>>>>> d6e2505ebfdcf17cece0cc2a90c8f7a7b337ad41
    }

    //implements method of SurfaceHolder.Callback
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
<<<<<<< HEAD
        Bitmap chibiBitmap1 = BitmapFactory.decodeResource(this.getResources(), R.drawable.chibi_nusair);
        this.chibi_nusair = new ChibiCharacter(this, chibiBitmap1, 100, 50);

        this.gameUpdate = new GameUpdate(this, holder);
        this.gameUpdate.setRunning(true);
        this.gameUpdate.start();
=======
        Bitmap chibiBitmap1 = BitmapFactory.decodeResource(this.getResources(), R.drawable.nusair);
        this.nusair = new ChibiCharacter(this.chibiBitmap1, 100, 50);

        this.gameThread = new GameThread(this, holder);
        this.gameThread.setRunning(true);
        this.gameThread.start();
>>>>>>> d6e2505ebfdcf17cece0cc2a90c8f7a7b337ad41
    }

    //implements method of SurfaceHolder Callback
    @Override
<<<<<<< HEAD
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    //implements method of SurfaceHolder Callback
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                this.gameUpdate.setRunning(false);

                //parent thread must wait until end of GameThread
                this.gameUpdate.join();
            } catch (InterruptedException e) {
=======
    public void surfaceDestroyed(SurfaceHolder holder){
        boolean retry = true;
        while(retry){
            try{
                this.gameThread.setRunning(false);

                //parent thread must wait until end of GameThread
                this.gameThread.join();
            }catch(InterruptedException e){
>>>>>>> d6e2505ebfdcf17cece0cc2a90c8f7a7b337ad41
                e.printStackTrace();
            }
            retry = true;
        }
    }

}
