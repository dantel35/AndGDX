package com.andgdx.camera;

import com.badlogic.gdx.utils.viewport.Viewport;

public class SmoothCamera extends SimpleCamera {

	
	
	private float mMaxVelocityX;
	private float mMaxVelocityY;
	private float mMaxZoomFactorChange;

	private float mTargetCenterX;
	private float mTargetCenterY;
	
	public SmoothCamera(float pX, float pY, float pWidth, float pHeight,  float pMaxVelocityX, float pMaxVelocityY,
			Viewport viewport) {
		super(pX, pY, pWidth, pHeight, viewport);
	this.mMaxVelocityX = pMaxVelocityX;
	this.mMaxVelocityY = pMaxVelocityY;
	}
	
	public SmoothCamera(float pX, float pY, float pWidth, float pHeight, float pMaxVelocityX, float pMaxVelocityY) {
		super(pX, pY, pWidth, pHeight);
		this.mMaxVelocityX = pMaxVelocityX;
		this.mMaxVelocityY = pMaxVelocityY;
	}

	
	@Override
	public void onUpdate(final float pSecondsElapsed) {
		super.onUpdate(pSecondsElapsed);
		final float currentCenterX = this.getCenterX();
		final float currentCenterY = this.getCenterY();

		final float targetCenterX = this.mTargetCenterX;
		final float targetCenterY = this.mTargetCenterY;

		if (currentCenterX != targetCenterX || currentCenterY != targetCenterY) {
			final float diffX = targetCenterX - currentCenterX;
			final float dX = this.limitToMaxVelocityX(diffX, pSecondsElapsed);

			final float diffY = targetCenterY - currentCenterY;
			final float dY = this.limitToMaxVelocityY(diffY, pSecondsElapsed);

			super.setCenter(currentCenterX + dX, currentCenterY + dY);
		}
	}
	
	
	
	
	private float limitToMaxVelocityX(final float pValue, final float pSecondsElapsed) {
		if (pValue > 0) {
			return Math.min(pValue, this.mMaxVelocityX * pSecondsElapsed);
		} else {
			return Math.max(pValue, -this.mMaxVelocityX * pSecondsElapsed);
		}
	}

	private float limitToMaxVelocityY(final float pValue, final float pSecondsElapsed) {
		if (pValue > 0) {
			return Math.min(pValue, this.mMaxVelocityY * pSecondsElapsed);
		} else {
			return Math.max(pValue, -this.mMaxVelocityY * pSecondsElapsed);
		}
	}

	
}
