package com.isabelle.androidgame2d;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import com.isabelle.androidgame2d.GameSurface;


public class GameUpdate extends Thread{
    private boolean running;
    private GameSurface gameSurface;
    private SurfaceHolder surfaceHolder;

    public GameUpdate(GameSurface gameSurface, SurfaceHolder surfaceHolder){
        this.gameSurface = gameSurface;
        this.surfaceHolder = surfaceHolder;
    }

    @Override
    public void run(){
        long startTime = System.nanoTime();

        while(running){
            Canvas canvas = null;
            try{
                //get canvas from holder and lock it
                canvas = this.surfaceHolder.lockCanvas();

                //Synchronized
                synchronized (canvas){
                    this.gameSurface.update();
                    this.gameSurface.draw(canvas);
                }
            }catch(Exception e) {
                //Do nothing
            }finally{
                if(canvas!=null){
                    //unlock canvas
                    this.surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
            long now = System.nanoTime();
            //interval to redraw game
            //nano--> milli
            long waitTime = (now - startTime)/1000000;
            if (waitTime <100){
                waitTime =100;
            }

            try{
                //Sleep
                this.sleep(waitTime);
            }catch(InterruptedException e){
                //do nothing
            }
            startTime = System.nanoTime();
        }
    }

    public void setRunning(boolean running){
        this.running = running;
    }
}
