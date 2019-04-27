import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.isabelle.androidgame2d.R;

public class GameSurface extends SurfaceView implements SurfaceHolder.Callback{
    private GameThread gameThread;
    private ChibiCharacter nusair;

    public GameSurface(Context context){
        super(context);
        //GameSurface focusable so it can handle events
        this.setFocusable(true);

        //set callback
        this.getHolder().addCallback(this);
    }

    public void update(){
        this.nusair.update();
    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        this.nusair.draw(canvas);
    }

    //implements method of SurfaceHolder.Callback
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Bitmap chibiBitmap1 = BitmapFactory.decodeResource(this.getResources(), R.drawable.nusair);
        this.nusair = new ChibiCharacter(this.chibiBitmap1, 100, 50);

        this.gameThread = new GameThread(this, holder);
        this.gameThread.setRunning(true);
        this.gameThread.start();
    }

    //implements method of SurfaceHolder Callback
    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        boolean retry = true;
        while(retry){
            try{
                this.gameThread.setRunning(false);

                //parent thread must wait until end of GameThread
                this.gameThread.join();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            retry = true;
        }
    }

}
