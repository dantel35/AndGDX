package com.andgdx.sprite;

import com.andgdx.sprite.AnimatedSpriteCore.IAnimationListener;
import com.andgdx.texture.TextureOptions;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimatedSprite extends Sprite implements IAnimatedSprite {

	AnimatedSpriteCore core;

	// ===========================================================
	// Constructors texturePath
	// ===========================================================

	private IAnimationListener mAnimationListener;

	public AnimatedSprite(String texturePath, int columns, int rows) {
		this(new Texture(texturePath), columns, rows, TextureOptions.NEAREST);
	}

	public AnimatedSprite(String texturePath, int columns, int rows,
			IAnimationListener listener) {
		this(new Texture(texturePath), columns, rows, TextureOptions.NEAREST);
		mAnimationListener = listener;
	}

	public AnimatedSprite(String texturePath, int columns, int rows,
			TextureOptions options) {
		this(new Texture(texturePath), columns, rows, options);
	}

	public AnimatedSprite(String texturePath, int columns, int rows,
			TextureOptions options, IAnimationListener listener) {
		this(new Texture(texturePath), columns, rows, options);
		mAnimationListener = listener;
	}

	// ===========================================================
	// Constructors texture
	// ===========================================================

	public AnimatedSprite(Texture texture, int columns, int rows) {
		this(texture, columns, rows, TextureOptions.NEAREST);
	}

	public AnimatedSprite(Texture texture, int columns, int rows,
			IAnimationListener listener) {
		this(texture, columns, rows, TextureOptions.NEAREST);
		mAnimationListener = listener;
	}

	public AnimatedSprite(Texture texture, int columns, int rows,
			TextureOptions options, IAnimationListener listener) {
		this(texture, columns, rows, TextureOptions.NEAREST);
		mAnimationListener = listener;
	}

	public AnimatedSprite(Texture texture, int columns, int rows,
			TextureOptions options) {
		this(new TextureRegion(texture), columns, rows, options, null);
	}

	// ===========================================================
	// Constructors textureRegion
	// ===========================================================

	public AnimatedSprite(TextureRegion textureRegion, int columns, int rows) {
		this(textureRegion, columns, rows, TextureOptions.NEAREST);
	}

	public AnimatedSprite(TextureRegion textureRegion, int columns, int rows,
			IAnimationListener listener) {
		this(textureRegion, columns, rows, TextureOptions.NEAREST);
		mAnimationListener = listener;
	}

	public AnimatedSprite(TextureRegion textureRegion, int columns, int rows,
			TextureOptions options, IAnimationListener listener) {
		this(textureRegion, columns, rows, TextureOptions.NEAREST);
		mAnimationListener = listener;
	}

	public AnimatedSprite(TextureRegion textureRegion, int columns, int rows,
			TextureOptions options) {
		core = new AnimatedSpriteCore(textureRegion, columns, rows, options,
				mAnimationListener);
		addActor(core);
	}

	/* (non-Javadoc)
	 * @see com.andgdx.sprite.IAnimatedSprite#setCurrentTileIndex(int)
	 */
	public void setCurrentTileIndex(int pTileIndex) {
		core.setCurrentTileIndex(pTileIndex);

	}

	/* (non-Javadoc)
	 * @see com.andgdx.sprite.IAnimatedSprite#getCurrentTileIndex()
	 */
	public int getCurrentTileIndex() {
		return core.getCurrentTileIndex();
	}

	/* (non-Javadoc)
	 * @see com.andgdx.sprite.IAnimatedSprite#stopAnimation()
	 */
	public void stopAnimation() {
		core.stopAnimation();
	}

	/* (non-Javadoc)
	 * @see com.andgdx.sprite.IAnimatedSprite#stopAnimation(int)
	 */
	public void stopAnimation(final int pTileIndex) {
		core.stopAnimation(pTileIndex);
	}

	/* (non-Javadoc)
	 * @see com.andgdx.sprite.IAnimatedSprite#animate(long)
	 */
	public void animate(final long pFrameDurationEach) {
		this.animate(pFrameDurationEach, null);
	}

	/* (non-Javadoc)
	 * @see com.andgdx.sprite.IAnimatedSprite#animate(long, com.andgdx.sprite.AnimatedSpriteCore.IAnimationListener)
	 */
	public void animate(final long pFrameDurationEach,
			final IAnimationListener pAnimationListener) {
		core.animate(pFrameDurationEach, pAnimationListener);
	}

	/* (non-Javadoc)
	 * @see com.andgdx.sprite.IAnimatedSprite#animate(long, boolean)
	 */
	public void animate(final long pFrameDurationEach, final boolean pLoop) {
		this.animate(pFrameDurationEach, pLoop, null);
	}

	/* (non-Javadoc)
	 * @see com.andgdx.sprite.IAnimatedSprite#animate(long, boolean, com.andgdx.sprite.AnimatedSpriteCore.IAnimationListener)
	 */
	public void animate(final long pFrameDurationEach, final boolean pLoop,
			final IAnimationListener pAnimationListener) {
		core.animate(pFrameDurationEach, pLoop, pAnimationListener);
	}

	/* (non-Javadoc)
	 * @see com.andgdx.sprite.IAnimatedSprite#animate(long, int)
	 */
	public void animate(final long pFrameDurationEach, final int pLoopCount) {
		this.animate(pFrameDurationEach, pLoopCount, null);
	}

	/* (non-Javadoc)
	 * @see com.andgdx.sprite.IAnimatedSprite#animate(long, int, com.andgdx.sprite.AnimatedSpriteCore.IAnimationListener)
	 */
	public void animate(final long pFrameDurationEach, final int pLoopCount,
			final IAnimationListener pAnimationListener) {
		core.animate(pFrameDurationEach, pLoopCount, pAnimationListener);
	}

	/* (non-Javadoc)
	 * @see com.andgdx.sprite.IAnimatedSprite#animate(long[])
	 */
	public void animate(final long[] pFrameDurations) {
		this.animate(pFrameDurations, (IAnimationListener) null);
	}

	/* (non-Javadoc)
	 * @see com.andgdx.sprite.IAnimatedSprite#animate(long[], com.andgdx.sprite.AnimatedSpriteCore.IAnimationListener)
	 */
	public void animate(final long[] pFrameDurations,
			final IAnimationListener pAnimationListener) {
		core.animate(pFrameDurations, pAnimationListener);
	}

	/* (non-Javadoc)
	 * @see com.andgdx.sprite.IAnimatedSprite#animate(long[], boolean)
	 */
	public void animate(final long[] pFrameDurations, final boolean pLoop) {
		this.animate(pFrameDurations, pLoop, null);
	}

	/* (non-Javadoc)
	 * @see com.andgdx.sprite.IAnimatedSprite#animate(long[], boolean, com.andgdx.sprite.AnimatedSpriteCore.IAnimationListener)
	 */
	public void animate(final long[] pFrameDurations, final boolean pLoop,
			final IAnimationListener pAnimationListener) {
		core.animate(pFrameDurations, pLoop, pAnimationListener);
	}

	/* (non-Javadoc)
	 * @see com.andgdx.sprite.IAnimatedSprite#animate(long[], int)
	 */
	public void animate(final long[] pFrameDurations, final int pLoopCount) {
		this.animate(pFrameDurations, pLoopCount, null);
	}

	/* (non-Javadoc)
	 * @see com.andgdx.sprite.IAnimatedSprite#animate(long[], int, com.andgdx.sprite.AnimatedSpriteCore.IAnimationListener)
	 */
	public void animate(final long[] pFrameDurations, final int pLoopCount,
			final IAnimationListener pAnimationListener) {
		core.animate(pFrameDurations, pLoopCount, pAnimationListener);
	}

	/* (non-Javadoc)
	 * @see com.andgdx.sprite.IAnimatedSprite#animate(long[], int, int, boolean)
	 */
	public void animate(final long[] pFrameDurations,
			final int pFirstTileIndex, final int pLastTileIndex,
			final boolean pLoop) {
		this.animate(pFrameDurations, pFirstTileIndex, pLastTileIndex, pLoop,
				null);
	}

	/* (non-Javadoc)
	 * @see com.andgdx.sprite.IAnimatedSprite#animate(long[], int, int, boolean, com.andgdx.sprite.AnimatedSpriteCore.IAnimationListener)
	 */
	public void animate(final long[] pFrameDurations,
			final int pFirstTileIndex, final int pLastTileIndex,
			final boolean pLoop, final IAnimationListener pAnimationListener) {

		core.animate(pFrameDurations, pFirstTileIndex, pLastTileIndex, pLoop,
				pAnimationListener);
	}

	/* (non-Javadoc)
	 * @see com.andgdx.sprite.IAnimatedSprite#animate(long[], int, int, int)
	 */
	public void animate(final long[] pFrameDurations,
			final int pFirstTileIndex, final int pLastTileIndex,
			final int pLoopCount) {
		this.animate(pFrameDurations, pFirstTileIndex, pLastTileIndex,
				pLoopCount, null);
	}

	/* (non-Javadoc)
	 * @see com.andgdx.sprite.IAnimatedSprite#animate(long[], int, int, int, com.andgdx.sprite.AnimatedSpriteCore.IAnimationListener)
	 */
	public void animate(final long[] pFrameDurations,
			final int pFirstTileIndex, final int pLastTileIndex,
			final int pLoopCount, final IAnimationListener pAnimationListener) {
		core.animate(pFrameDurations, pFirstTileIndex, pLastTileIndex,
				pLoopCount, pAnimationListener);
	}

	/* (non-Javadoc)
	 * @see com.andgdx.sprite.IAnimatedSprite#animate(long[], int[])
	 */
	public void animate(final long[] pFrameDurations, final int[] pFrames) {
		this.animate(pFrameDurations, pFrames, null);
	}

	/* (non-Javadoc)
	 * @see com.andgdx.sprite.IAnimatedSprite#animate(long[], int[], com.andgdx.sprite.AnimatedSpriteCore.IAnimationListener)
	 */
	public void animate(final long[] pFrameDurations, final int[] pFrames,
			final IAnimationListener pAnimationListener) {
		core.animate(pFrameDurations, pFrames, pAnimationListener);
	}

	/* (non-Javadoc)
	 * @see com.andgdx.sprite.IAnimatedSprite#animate(long[], int[], boolean)
	 */
	public void animate(final long[] pFrameDurations, final int[] pFrames,
			final boolean pLoop) {
		this.animate(pFrameDurations, pFrames, pLoop, null);
	}

	/* (non-Javadoc)
	 * @see com.andgdx.sprite.IAnimatedSprite#animate(long[], int[], boolean, com.andgdx.sprite.AnimatedSpriteCore.IAnimationListener)
	 */
	public void animate(final long[] pFrameDurations, final int[] pFrames,
			final boolean pLoop, final IAnimationListener pAnimationListener) {
		core.animate(pFrameDurations, pFrames, pLoop, pAnimationListener);
	}

	/* (non-Javadoc)
	 * @see com.andgdx.sprite.IAnimatedSprite#animate(long[], int[], int)
	 */
	public void animate(final long[] pFrameDurations, final int[] pFrames,
			final int pLoopCount) {
		core.animate(pFrameDurations, pFrames, pLoopCount);
	}

	/* (non-Javadoc)
	 * @see com.andgdx.sprite.IAnimatedSprite#animate(long[], int[], int, com.andgdx.sprite.AnimatedSpriteCore.IAnimationListener)
	 */
	public void animate(final long[] pFrameDurations, final int[] pFrames,
			final int pLoopCount, final IAnimationListener pAnimationListener) {
		core.animate(pFrameDurations, pLoopCount, pAnimationListener);
	}

	/* (non-Javadoc)
	 * @see com.andgdx.sprite.IAnimatedSprite#animate(com.andgdx.sprite.IAnimationData)
	 */
	public void animate(final IAnimationData pAnimationData) {
		core.animate(pAnimationData);
	}
	
	/* (non-Javadoc)
	 * @see com.andgdx.sprite.IAnimatedSprite#getWidth()
	 */
	public float getWidth()
	{
		return core.getWidth();
	}
	
	/* (non-Javadoc)
	 * @see com.andgdx.sprite.IAnimatedSprite#getHeight()
	 */
	public float getHeight()
	{
		return core.getHeight();
	}
	
	
	public void setColor(Color color)
	{
		if(core!=null)
		core.setColor(color);
	}
	
	public void setAlpha(float alpha) {
		if(core!=null)
		core.setAlpha(alpha);
	}
	
	

}
