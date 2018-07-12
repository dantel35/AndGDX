
package com.andengine.util.handy;

import com.andgdx.camera.SimpleCamera;
import com.andgdx.entity.Entity;
import com.andgdx.entity.shapes.Line;
import com.andgdx.entity.shapes.Rectangle;
import com.andgdx.scene.HUD;
import com.andgdx.util.ColorUtil;
import com.badlogic.gdx.graphics.Color;

/**
 * @author Jong - Yonatan
 * @author Daniel Knittel
 * @author Janina Knittel
 *
 */
public class ProgressBar extends Rectangle{
    // ===========================================================
    // Constants
    // ===========================================================
    private static final float FRAME_LINE_WIDTH = 2f;
    // ===========================================================
    // Fields
    // ===========================================================
    private final Line[] mFrameLines = new Line[4];
    private final Rectangle mBackgroundRectangle;
    private final Rectangle mProgressRectangle;
    private final Rectangle mProgressRectangleHighlight1;
    private final Rectangle mProgressRectangleHighlight2;

    private final Rectangle mProgressRectangleShadow1;
    private final Rectangle mProgressRectangleShadow2;

    
    private final Rectangle mProgressRectangleShadow;
    private float height;
    private  float mPixelsPerPercentRatio;
    // ===========================================================
    // Constructors
    // ===========================================================
    public ProgressBar(final float pX, final float pY, final float pWidth, final float pHeight) {
        super(pX, pY, pWidth, pHeight);
        height = pHeight;
        this.mBackgroundRectangle = new Rectangle(pX, pY, pWidth, pHeight);

//        this.mFrameLines[0] = new Line(pX , pY + pHeight, pX+pWidth, pY + pHeight, FRAME_LINE_WIDTH); //Top line.
//        this.mFrameLines[1] = new Line(pX + pWidth, pY, pX + pWidth, pY + pHeight, FRAME_LINE_WIDTH); //Right line.
//        this.mFrameLines[2] = new Line(pX, pY, pX+pWidth, pY, FRAME_LINE_WIDTH); //Bottom line.
//        this.mFrameLines[3] = new Line(pX, pY , pX, pY+pHeight, FRAME_LINE_WIDTH); //Left line.

        this.mFrameLines[0] = new Line(0 , 0 + pHeight, 0+pWidth, 0 + pHeight, FRAME_LINE_WIDTH); //Top line.
        this.mFrameLines[1] = new Line(0 + pWidth, 0, 0 + pWidth, 0 + pHeight, FRAME_LINE_WIDTH); //Right line.
        this.mFrameLines[2] = new Line(0, 0, 0+pWidth, 0, FRAME_LINE_WIDTH); //Bottom line.
        this.mFrameLines[3] = new Line(0, 0 , 0, 0+pHeight, FRAME_LINE_WIDTH); //Left line.
        
//        this.mProgressRectangle = new Rectangle(pX, pY, pWidth, pHeight);
        this.mProgressRectangle = new Rectangle(0, 0, pWidth, pHeight);

        float highlightHeight = pHeight/4;
        this.mProgressRectangleShadow = new Rectangle(0,  pHeight-highlightHeight, pWidth, highlightHeight);
        
        mProgressRectangleShadow.setColor(new Color(0,0,0,0.2f));
        
        this.mProgressRectangleHighlight1 = new Rectangle(0,  pHeight/2-highlightHeight, pWidth, highlightHeight);
        mProgressRectangleHighlight1.setColor(new Color(1,1,1,0.2f));
        float highlight2Height = Math.max(1,highlightHeight/3);
		this.mProgressRectangleHighlight2 = new Rectangle(0,  (highlightHeight/2)-highlight2Height, pWidth, highlight2Height);
        mProgressRectangleHighlight2.setColor(new Color(1,1,1,0.2f));
        
        
        float shadowHeight = pHeight / 5;
        mProgressRectangleShadow1 = new Rectangle(0,0, pWidth,shadowHeight);
        mProgressRectangleShadow2 = new Rectangle(0,pHeight - (shadowHeight), pWidth,shadowHeight);
        mProgressRectangleShadow1.setColor(new Color(0,0,0,0.4f));
        mProgressRectangleShadow2.setColor(new Color(0,0,0,0.4f));
        
        
        
//        super.attachChild(this.mBackgroundRectangle); //This one is drawn first.
        super.attachChild(this.mProgressRectangle); //The progress is drawn afterwards.
        for(int i = 0; i < this.mFrameLines.length; i++)
            super.attachChild(this.mFrameLines[i]); //Lines are drawn last, so they'll override everything.

        mProgressRectangle.attachChild(mProgressRectangleHighlight1);
        mProgressRectangle.attachChild(mProgressRectangleShadow);
        mProgressRectangle.attachChild(mProgressRectangleShadow1);
//        mProgressRectangle.attachChild(mProgressRectangleShadow2);

        
        mProgressRectangleHighlight1.attachChild(mProgressRectangleHighlight2);
        
        this.mPixelsPerPercentRatio = pWidth / 100;
    }
    // ===========================================================
    // Getter & Setter
    // ===========================================================
    public void setBackColor(final float pRed, final float pGreen, final float pBlue, final float pAlpha) {
        this.mBackgroundRectangle.setColor(pRed, pGreen, pBlue, pAlpha);
       
    }
    public void setFrameColor(final float pRed, final float pGreen, final float pBlue, final float pAlpha) {
        for(int i = 0; i < this.mFrameLines.length; i++)
            this.mFrameLines[i].setColor(pRed, pGreen, pBlue, pAlpha);
    }
    public void setProgressColor(final float pRed, final float pGreen, final float pBlue, final float pAlpha) {
        this.mProgressRectangle.setColor(pRed, pGreen, pBlue, pAlpha);
        
        Color inputColor = new Color(pRed,pGreen,pBlue,pAlpha);
//        inputColor.mix(inputColor,0.6f, Color.WHITE,0.4f);
        ColorUtil.mix(inputColor,0.6f, Color.WHITE,0.4f, inputColor);

        mProgressRectangleHighlight1.setColor(inputColor);
    }
    
    public void setFrameColor(final Color color) {
    	 for(int i = 0; i < this.mFrameLines.length; i++)
    	this.mFrameLines[i].setColor(color);
    }
    
    public void setProgressColor(final Color color) {
        this.mProgressRectangle.setColor(color);
        Color inputColor = new Color(color.r,color.g,color.b,color.a);
       
//        inputColor.mix(inputColor,0.6f, Color.WHITE,0.4f);
        ColorUtil.mix(inputColor,0.6f, Color.WHITE,0.4f, inputColor);

        mProgressRectangleHighlight1.setColor(inputColor);
        
    }
    public void setBackColor(final Color color) {
        this.mBackgroundRectangle.setColor(color);
    }
    /**
     * Set the current progress of this progress bar.
     * @param pProgress is <b> BETWEEN </b> 0 - 100.
     */
    public void setProgress(final float pProgress) {
        if(pProgress < 0)
        {
        	this.mProgressRectangle.setWidth(0); //This is an internal check for my specific game, you can remove it.
        	this.mProgressRectangleHighlight1.setWidth(0);
//        	this.mProgressRectangleHighlight2.setWidth(0);
//        	this.mProgressRectangleShadow.setWidth(0);
        }
        this.mProgressRectangle.setWidth(this.mPixelsPerPercentRatio * pProgress);

                this.mProgressRectangleHighlight1.setWidth(this.mPixelsPerPercentRatio * pProgress);
//        this.mProgressRectangleHighlight2.setWidth(this.mPixelsPerPercentRatio * pProgress);
//        this.mProgressRectangleShadow.setWidth(this.mPixelsPerPercentRatio * pProgress);
//        this.mProgressRectangleShadow1.setWidth(this.mPixelsPerPercentRatio * pProgress);
//        this.mProgressRectangleShadow2.setWidth(this.mPixelsPerPercentRatio * pProgress);

    }
    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================
    public void setProgressByValue(float currentValue, float maxValue)
    {
    	float value = (100f/maxValue)*currentValue;
    	setProgress(value);
    }
    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}

