package com.andgdx.camera;

import com.andgdx.entity.IEntity;
import com.andgdx.scene.HUD;
import com.andgdx.update.IUpdateHandler;
import com.andgdx.update.UpdateHandlerList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * derived from 2010 Nicolas Gramlich (c) 2011 Zynga Inc.
 *Adapted by Daniel Knittel
 * @author Nicolas Gramlich
 * @since 10:24:18 - 25.03.2010
 */
public class SimpleCamera implements IUpdateHandler {
	// ===========================================================
	// Constants
	// ===========================================================

	static final float[] VERTICES_TMP = new float[2];
	public static final int VERTEX_INDEX_X = 0;
	public static final int VERTEX_INDEX_Y = 1;

	private static final int UPDATEHANDLERS_CAPACITY_DEFAULT = 4;

	// ===========================================================
	// Fields
	// ===========================================================

	protected float mXMin;
	protected float mXMax;
	protected float mYMin;
	protected float mYMax;

	private float mZNear = -1.0f;
	private float mZFar = 1.0f;

	private HUD mHUD;

	private IEntity mChaseEntity;

	protected float mRotation = 0;
	protected float mCameraSceneRotation = 0;

	protected int mSurfaceX;
	protected int mSurfaceY;
	protected int mSurfaceWidth;
	protected int mSurfaceHeight;

	protected boolean mResizeOnSurfaceSizeChanged;
	protected UpdateHandlerList mUpdateHandlers;
	float oldX;
	float oldY;
	
	//test
	int oldXt;
	int oldYt;
	
	Vector3 pos = new Vector3(0, 0, 0);

	Viewport viewport;
	private boolean mShaking = false;
	private float mDuration;
	private float mIntensity;
	private float mCurrentDuration;
	private boolean antiPixelJitter = false;

	// ===========================================================
	// Constructors
	// ===========================================================

	public SimpleCamera(final float pX, final float pY, final float pWidth,
			final float pHeight) {
		this.set(pX, pY, pX + pWidth, pY + pHeight);
		 
	}

	public SimpleCamera(final float pX, final float pY, final float pWidth,
			final float pHeight, Viewport viewport) {
		this.set(pX, pY, pX + pWidth, pY + pHeight);
		setViewport(viewport);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public void setViewport(Viewport viewport) {
		this.viewport = viewport;
		mSurfaceWidth = viewport.getScreenWidth();
		mSurfaceHeight = viewport.getScreenHeight();
		synchPosition();
	}
	
	public Viewport getViewport()
	{
		return viewport;
	}

	private void synchPosition() {
		 viewport.getCamera().position.set(getXMin()+getXMax()*0.5f,
		 getYMin()+getYMax()*0.5f, 0);
//		setPosition((int)Math.round(getXMin() + getXMax() * 0.5f), (int)Math.round(getYMin() + getYMax() * 0.5f));
	}

	public void setPosition(float x, float y) {

		position(x, y);
	}

//	private void position(float x, float y) {
//		 
//		if (viewport != null && (x != oldX || y != oldY)) {
//
//			viewport.getCamera().position.set(x, y, 0);
//			oldX = x;
//			oldY = y;
//		}
//	}

	private void position(float x, float y) {
		 
		if (viewport != null ) //&& (x != oldX || y != oldY)) 
			{

			float roundX = Math.round(x);
			float roundY = Math.round(y);
			int sWidth =viewport.getScreenWidth();
			int sHeight =viewport.getScreenHeight();
			int cWidth = (int) Gdx.graphics.getBackBufferWidth();
			int cHeight = (int) Gdx.graphics.getBackBufferHeight();

			
			
			float widthRatio = (sWidth > 0) ? cWidth/sWidth : 1;
			float heightRatio = (sHeight >0 ) ? cHeight/sHeight :1;
			
			viewport.getCamera().position.set(roundX*widthRatio, roundY*heightRatio, 0);
			oldX = x;
			oldY = y;
		}
	}
	
	
	

	public void shake(float duration, float itensity) {
		mShaking = true;
		mDuration = duration;
		mIntensity = itensity;
		mCurrentDuration = 0;
	}

	public float getXMin() {
		return this.mXMin;
	}

	public void setXMin(final float pXMin) {
		this.mXMin = pXMin;
	}

	public float getXMax() {
		return this.mXMax;
	}

	public void setXMax(final float pXMax) {
		this.mXMax = pXMax;
	}

	public float getYMin() {
		return this.mYMin;
	}

	public void setYMin(final float pYMin) {
		this.mYMin = pYMin;
	}

	public float getYMax() {
		return this.mYMax;
	}

	public void setYMax(final float pYMax) {
		this.mYMax = pYMax;
	}

	public void set(final float pXMin, final float pYMin, final float pXMax,
			final float pYMax) {
		this.mXMin = pXMin;
		this.mXMax = pXMax;
		this.mYMin = pYMin;
		this.mYMax = pYMax;
	}

//	public float getZNear() {
//		return this.mZNear;
//	}
//
//	public float getZFar() {
//		return this.mZFar;
//	}
//
//	public void setZNear(final float pZNear) {
//		this.mZNear = pZNear;
//	}
//
//	public void setZFar(final float pZFar) {
//		this.mZFar = pZFar;
//	}
//
//	public void setZClippingPlanes(final float pNearZClippingPlane,
//			final float pFarZClippingPlane) {
//		this.mZNear = pNearZClippingPlane;
//		this.mZFar = pFarZClippingPlane;
//	}

	public float getWidth() {
		return this.mXMax - this.mXMin;
	}

	public float getHeight() {
		return this.mYMax - this.mYMin;
	}

	public final float getCameraSceneWidth() {
		return this.mXMax - this.mXMin;
	}

	public final float getCameraSceneHeight() {
		return this.mYMax - this.mYMin;
	}

	public float getCenterX() {
		return (this.mXMin + this.mXMax) * 0.5f;
	}

	public float getCenterY() {
		return (this.mYMin + this.mYMax) * 0.5f;
	}

	public void setCenter(final float pCenterX, final float pCenterY) {
		final float dX = pCenterX - this.getCenterX();
		final float dY = pCenterY - this.getCenterY();

		this.mXMin += dX;
		this.mXMax += dX;
		this.mYMin += dY;
		this.mYMax += dY;
	}

	public void offsetCenter(final float pX, final float pY) {
		this.setCenter(this.getCenterX() + pX, this.getCenterY() + pY);
	}

	public HUD getHUD() {
		return this.mHUD;
	}

//	public void setHUD(final HUD pHUD) {
//		this.mHUD = pHUD;
//		if (pHUD != null) {
//			pHUD.setCamera(this);
//		}
//	}

	public boolean hasHUD() {
		return this.mHUD != null;
	}

	public void setChaseEntity(final IEntity pChaseEntity) {
		this.mChaseEntity = pChaseEntity;
	}

//	public boolean isRotated() {
//		return this.mRotation != 0;
//	}
//
//	public float getRotation() {
//		return this.mRotation;
//	}
//
//	public void setRotation(final float pRotation) {
//		this.mRotation = pRotation;
//	}

	 public int getSurfaceX() {
	 return viewport.getScreenX();
	 }
	//
	 public int getSurfaceY() {
	 return viewport.getScreenY();
	 }
	//
	 public int getSurfaceWidth() {
	 
		 return viewport.getScreenWidth();
	 }
	//
	 public int getSurfaceHeight() {
	 return viewport.getScreenHeight();
	 }

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	public void onUpdate(final float pSecondsElapsed) {
		if (this.mUpdateHandlers != null) {
			this.mUpdateHandlers.onUpdate(pSecondsElapsed);
		}

//		if (this.mHUD != null) {
//			this.mHUD.onUpdate(pSecondsElapsed);
//		}

		this.updateChaseEntity();
		this.checkShake(pSecondsElapsed);
	}

	private void checkShake(final float pSecondsElapsed) {
		if (mShaking) {
			float centerX = viewport.getCamera().position.x;
			float centerY = viewport.getCamera().position.y;
			mCurrentDuration = (mCurrentDuration + pSecondsElapsed);
			if (mCurrentDuration > mDuration) {
				mShaking = false;
				mCurrentDuration = 0;
				this.setCenter(centerX, centerY);
				this.position(centerX, centerY);
			} else {
				int sentitX = 1;
				int sentitY = 1;
				if (Math.random() < 0.5)
					sentitX = -1;
				if (Math.random() < 0.5)
					sentitY = -1;
				
				
				
				float shakeX = (float) (centerX + Math.random()
						* mIntensity * sentitX);
				float shakeY = (float) (centerY + Math.random()
						* mIntensity * sentitY);
				 
				this.position(shakeX, shakeY);
			}
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================

	public void onDrawHUD() {
		if (this.mHUD != null) {

		}
	}

	public void updateChaseEntity() {
		if (this.mChaseEntity != null) {

//			int x = (int) (MathUtils.round(10f * mChaseEntity.getCenterX()) / 10f);
//			int y = (int) (MathUtils.round(10f * mChaseEntity.getCenterY()) / 10f);
//			
//			int x = (int)  mChaseEntity.getCenterX();
//			int y = (int)  mChaseEntity.getCenterY();
			
			float x = mChaseEntity.getX() + mChaseEntity.getWidth()*0.5f;
			float y = mChaseEntity.getY() - mChaseEntity.getHeight()*0.5f;

			
//			if (antiPixelJitter || true)
//			{
//			int deltaX = Math.abs(x-oldXt);
//			int deltaY = Math.abs(y-oldYt);
//
//			if ( (deltaX & 1) != 0 ) { x=x-(deltaX & 1); } 
//			if ( (deltaY & 1) != 0 ) { y=y-(deltaY & 1); } 
//			}
			
			this.setCenter(x,y);
			this.setPosition(x,y);
//			oldXt =x;
//			oldYt = y;
		}
	}

	public void reset() {
		// TODO Auto-generated method stub

	}

	public void enableAntiPixelJitter(boolean enable)
	{
		antiPixelJitter = enable;
	}
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
