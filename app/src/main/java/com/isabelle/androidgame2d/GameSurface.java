import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Surface;

import com.isabelle.androidgame2d.R;

public class GameSurface extends SurfaceView implements SurfaceHolder.Callback {
    private GameUpdate gameUpdate;
    private ChibiCharacter chibi_nusair;

    public GameSurface(Context context) {
        super(context);
        //GameSurface focusable so it can handle events
        this.setFocusable(true);

        //set callback
        this.getHolder().addCallback(this);
    }

    public void update() {
        this.chibi_nusair.update();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        this.chibi_nusair.draw(canvas);
    }

    //implements method of SurfaceHolder.Callback
    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        Bitmap chibiBitmap1 = BitmapFactory.decodeResource(this.getResources(), R.drawable.chibi_nusair);
        this.chibi_nusair = new ChibiCharacter(this, chibiBitmap1, 100, 50);

        this.gameUpdate = new GameUpdate(this, holder);
        this.gameUpdate.setRunning(true);
        this.gameUpdate.start();
    }

    //implements method of SurfaceHolder Callback
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    //implements method of SurfaceHolder Callback
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                this.gameUpdate.setRunning(false);

                //parent thread must wait until end of gameUpdate
                this.gameUpdate.join();
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
            retry = true;
        }
    }

}