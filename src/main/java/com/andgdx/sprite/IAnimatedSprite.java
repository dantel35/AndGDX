package com.andgdx.sprite;

import com.andgdx.sprite.AnimatedSpriteCore.IAnimationListener;

public interface IAnimatedSprite {

	public abstract void setCurrentTileIndex(int pTileIndex);

	public abstract int getCurrentTileIndex();

	public abstract void stopAnimation();

	public abstract void stopAnimation(int pTileIndex);

	public abstract void animate(long pFrameDurationEach);

	public abstract void animate(long pFrameDurationEach,
			IAnimationListener pAnimationListener);

	public abstract void animate(long pFrameDurationEach, boolean pLoop);

	public abstract void animate(long pFrameDurationEach, boolean pLoop,
			IAnimationListener pAnimationListener);

	public abstract void animate(long pFrameDurationEach, int pLoopCount);

	public abstract void animate(long pFrameDurationEach, int pLoopCount,
			IAnimationListener pAnimationListener);

	public abstract void animate(long[] pFrameDurations);

	public abstract void animate(long[] pFrameDurations,
			IAnimationListener pAnimationListener);

	public abstract void animate(long[] pFrameDurations, boolean pLoop);

	public abstract void animate(long[] pFrameDurations, boolean pLoop,
			IAnimationListener pAnimationListener);

	public abstract void animate(long[] pFrameDurations, int pLoopCount);

	public abstract void animate(long[] pFrameDurations, int pLoopCount,
			IAnimationListener pAnimationListener);

	public abstract void animate(long[] pFrameDurations, int pFirstTileIndex,
			int pLastTileIndex, boolean pLoop);

	public abstract void animate(long[] pFrameDurations, int pFirstTileIndex,
			int pLastTileIndex, boolean pLoop,
			IAnimationListener pAnimationListener);

	public abstract void animate(long[] pFrameDurations, int pFirstTileIndex,
			int pLastTileIndex, int pLoopCount);

	public abstract void animate(long[] pFrameDurations, int pFirstTileIndex,
			int pLastTileIndex, int pLoopCount,
			IAnimationListener pAnimationListener);

	/**
	 * Animate specifics frames.
	 *
	 * @param pFrameDurations
	 *            must have the same length as pFrames.
	 * @param pFrames
	 *            indices of the frames to animate.
	 */
	public abstract void animate(long[] pFrameDurations, int[] pFrames);

	/**
	 * Animate specifics frames.
	 *
	 * @param pFrameDurations
	 *            must have the same length as pFrames.
	 * @param pFrames
	 *            indices of the frames to animate.
	 * @param pAnimationListener
	 */
	public abstract void animate(long[] pFrameDurations, int[] pFrames,
			IAnimationListener pAnimationListener);

	/**
	 * Animate specifics frames.
	 *
	 * @param pFrameDurations
	 *            must have the same length as pFrames.
	 * @param pFrames
	 *            indices of the frames to animate.
	 * @param pLoop
	 */
	public abstract void animate(long[] pFrameDurations, int[] pFrames,
			boolean pLoop);

	/**
	 * Animate specifics frames.
	 *
	 * @param pFrameDurations
	 *            must have the same length as pFrames.
	 * @param pFrames
	 *            indices of the frames to animate.
	 * @param pLoop
	 * @param pAnimationListener
	 */
	public abstract void animate(long[] pFrameDurations, int[] pFrames,
			boolean pLoop, IAnimationListener pAnimationListener);

	/**
	 * Animate specifics frames.
	 *
	 * @param pFrameDurations
	 *            must have the same length as pFrames.
	 * @param pFrames
	 *            indices of the frames to animate.
	 * @param pLoopCount
	 */
	public abstract void animate(long[] pFrameDurations, int[] pFrames,
			int pLoopCount);

	/**
	 * Animate specifics frames.
	 *
	 * @param pFrameDurations
	 *            must have the same length as pFrames.
	 * @param pFrames
	 *            indices of the frames to animate.
	 * @param pLoopCount
	 * @param pAnimationListener
	 */
	public abstract void animate(long[] pFrameDurations, int[] pFrames,
			int pLoopCount, IAnimationListener pAnimationListener);

	public abstract void animate(IAnimationData pAnimationData);

	public abstract float getWidth();

	public abstract float getHeight();

}