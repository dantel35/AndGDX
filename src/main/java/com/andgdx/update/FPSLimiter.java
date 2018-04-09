package com.andgdx.update;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.TimeUtils;

public class FPSLimiter implements IUpdateLimiter{

    private long previousTime = TimeUtils.nanoTime();
    private long currentTime = TimeUtils.nanoTime();
    private long deltaTime = 0;
    private float fps;

    public FPSLimiter(float fps) {
        this.fps = fps;
    }


    
    public void delay()
    {
    	sleep((int)fps);
    }
    
    
    private long diff, start = System.currentTimeMillis();

    public void sleep(int fps) {
        if(fps>0){
          diff = System.currentTimeMillis() - start;
          long targetDelay = 1000/fps;
          if (diff < targetDelay) {
            try{
                Thread.sleep(targetDelay - diff);
              } catch (InterruptedException e) {}
            }   
          start = System.currentTimeMillis();
        }
//        System.out.print("\nFPS: " + Gdx.graphics.getFramesPerSecond() + "\n");
    }
 
}