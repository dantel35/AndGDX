package com.andgdx.sprite;

import com.andgdx.animation.Animatable;
import com.andgdx.animation.IAnimationMachine;
import com.andgdx.engine.Engine;
import com.andgdx.texture.TextureOptions;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class AnimatedSpriteCore extends  SpriteCore implements IAnimatedSprite, Animatable {

	private static final int FRAMEINDEX_INVALID = -1;

	// ===========================================================
	// Fields
	// ===========================================================
	IAnimationMachine animationMachine;
	private com.andgdx.animation.IAnimationListener animationMachineListener;
	private Array<com.andgdx.animation.IAnimationListener> animationListeners = new Array<com.andgdx.animation.IAnimationListener>();
	
	private boolean mAnimationRunning;
	private boolean mAnimationStartedFired;

	private int mCurrentFrameIndex;
	private long mAnimationProgress;
	private int mRemainingLoopCount;

	 
	private final IAnimationData mAnimationData = new AnimationData();
	private IAnimationListener mAnimationListener;
	Texture sourceTexture;
	TextureRegion[] animationFrames;

	private int FRAME_ROWS;
	private static int FRAME_COLS;

	int currentTileIndex;
	
	TextureRegion currentFrame;    
	TextureOptions textureOptions;
 
	// ===========================================================
	// Constructors texturePath
	// ===========================================================
	
	public AnimatedSpriteCore(String texturePath, int columns, int rows)
	{
		this(new Texture(texturePath),columns,rows, TextureOptions.NEAREST);
	}
	
	public AnimatedSpriteCore(String texturePath, int columns, int rows, IAnimationListener listener)
	{
		this(new Texture(texturePath),columns,rows, TextureOptions.NEAREST);
		mAnimationListener= listener;
	}
	
	public AnimatedSpriteCore(String texturePath, int columns, int rows, TextureOptions options)
	{
		this(new Texture(texturePath),columns,rows, options);
	}
	
	public AnimatedSpriteCore(String texturePath, int columns, int rows, TextureOptions options, IAnimationListener listener)
	{
		this(new Texture(texturePath),columns,rows, options);
		mAnimationListener= listener;
	}
	
	
	// ===========================================================
		// Constructors texture
		// ===========================================================
	
	public AnimatedSpriteCore(Texture texture, int columns, int rows)
	{
		this(texture, columns, rows, TextureOptions.NEAREST);
	}
	
	public AnimatedSpriteCore(Texture texture, int columns, int rows, IAnimationListener listener)
	{
		this(texture, columns, rows, TextureOptions.NEAREST);
		mAnimationListener= listener;
	}
	
	public AnimatedSpriteCore(Texture texture, int columns, int rows, TextureOptions options, IAnimationListener listener) {
		this(texture, columns, rows, TextureOptions.NEAREST);
		mAnimationListener= listener;
	}
	
	public AnimatedSpriteCore(Texture texture, int columns, int rows, TextureOptions options) {
//		super(texture);
		super();

		this.textureOptions = options;
		sourceTexture = texture;
		applyFilterAndWrap(texture,options);
		
		FRAME_COLS = columns;
		FRAME_ROWS = rows;

		TextureRegion[][] tmp = TextureRegion.split(sourceTexture,
				sourceTexture.getWidth() / FRAME_COLS, sourceTexture.getHeight()
						/ FRAME_ROWS); // #10
		createFrames(tmp);
	}
	
	
	// ===========================================================
		// Constructors textureRegion
		// ===========================================================
	
	
	public AnimatedSpriteCore(TextureRegion textureRegion, int columns, int rows)
	{
		this(textureRegion, columns, rows, TextureOptions.NEAREST);
	}
	
	public AnimatedSpriteCore(TextureRegion textureRegion, int columns, int rows, IAnimationListener listener)
	{
		this(textureRegion, columns, rows, TextureOptions.NEAREST);
		mAnimationListener= listener;
	}
	
	
	public AnimatedSpriteCore(TextureRegion textureRegion, int columns, int rows, TextureOptions options, IAnimationListener listener) {
		this(textureRegion, columns, rows, TextureOptions.NEAREST);
		mAnimationListener= listener;
	}
	
	public AnimatedSpriteCore(TextureRegion textureRegion, int columns, int rows, TextureOptions options) {
//		super(textureRegion.getTexture());
		this.textureOptions = options;
		this.sourceTexture = textureRegion.getTexture();
		applyFilterAndWrap(textureRegion.getTexture(),options);
		
		FRAME_COLS = columns;
		FRAME_ROWS = rows;
 
		
		TextureRegion[][] tmp = textureRegion.split(textureRegion.getRegionWidth() / FRAME_COLS, textureRegion.getRegionHeight() / FRAME_ROWS);
		
		createFrames(tmp);
	}

	private void createFrames(TextureRegion[][] tmp) {
		animationFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		int index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				animationFrames[index++] = tmp[i][j];
			}
		}
		 
		this.setBounds(0, 0, animationFrames[0].getRegionWidth(), animationFrames[0].getRegionHeight());
	}
	
	private void createAutoMask()
	{
		Color color = Color.WHITE;
		sourceTexture.getTextureData().prepare();
		Pixmap pixmap = sourceTexture.getTextureData().consumePixmap();
		for(int i=0; i < pixmap.getWidth(); i++)
		{
		  for(int j =0; j < pixmap.getHeight(); j++)
		  {
		    if(color.set(pixmap.getPixel(i,j)).a  != 0) //if 
		    {
		    	
		    }
		  }
		}
	}
	
	

	// ///// Methods/////////
	@Override
	public void draw(Batch batch, float alpha) {
		onManagedUpdate(Engine.currentAndGDXDelta);
		this.textureRegion = animationFrames[currentTileIndex];
	
//		currentFrame = animationFrames[currentTileIndex];
//		batch.draw(currentFrame,getX(),getY());
		super.draw(batch, alpha);
	}

	protected void onManagedUpdate(final float pSecondsElapsed) {

		if (this.mAnimationRunning) {
			final int loopCount = this.mAnimationData.getLoopCount();
			final int[] frames = this.mAnimationData.getFrames();
			final long animationDuration = this.mAnimationData
					.getAnimationDuration();

			if (!this.mAnimationStartedFired && (this.mAnimationProgress == 0)) {
				this.mAnimationStartedFired = true;
				if (frames == null) {
					this.setCurrentTileIndex(this.mAnimationData
							.getFirstFrameIndex());
				} else {
					this.setCurrentTileIndex(frames[0]);
				}
				this.mCurrentFrameIndex = 0;
				if (this.mAnimationListener != null) {
					this.mAnimationListener.onAnimationStarted(this, loopCount);
					this.mAnimationListener.onAnimationFrameChanged(this,
							AnimatedSpriteCore.FRAMEINDEX_INVALID, 0);
				}
				if (this.animationMachineListener != null) {
					this.animationMachineListener.onStarted(this);
				}
			}
			final long nanoSecondsElapsed = (long) (pSecondsElapsed * com.andgdx.util.TimeConstants.NANOSECONDS_PER_SECOND);
			this.mAnimationProgress += nanoSecondsElapsed;

			if (loopCount == IAnimationData.LOOP_CONTINUOUS) {
				while (this.mAnimationProgress > animationDuration) {
					this.mAnimationProgress -= animationDuration;
					if (this.mAnimationListener != null) {
						this.mAnimationListener.onAnimationLoopFinished(this,
								this.mRemainingLoopCount, loopCount);
					}
				}
			} else {
				while (this.mAnimationProgress > animationDuration) {
					this.mAnimationProgress -= animationDuration;
					this.mRemainingLoopCount--;
					if (this.mRemainingLoopCount < 0) {
						break;
					} else if (this.mAnimationListener != null) {
						this.mAnimationListener.onAnimationLoopFinished(this,
								this.mRemainingLoopCount, loopCount);
					}
				}
			}

			if ((loopCount == IAnimationData.LOOP_CONTINUOUS)
					|| (this.mRemainingLoopCount >= 0)) {
				final int newFrameIndex = this.mAnimationData
						.calculateCurrentFrameIndex(this.mAnimationProgress);

				if (this.mCurrentFrameIndex != newFrameIndex) {
					if (frames == null) {
						this.setCurrentTileIndex(this.mAnimationData
								.getFirstFrameIndex() + newFrameIndex);
					} else {
						this.setCurrentTileIndex(frames[newFrameIndex]);
					}
					if (this.mAnimationListener != null) {
						this.mAnimationListener.onAnimationFrameChanged(this,
								this.mCurrentFrameIndex, newFrameIndex);
					}
				}
				this.mCurrentFrameIndex = newFrameIndex;
			} else {
				this.mAnimationRunning = false;
				if (this.mAnimationListener != null) {
					this.mAnimationListener.onAnimationFinished(this);
				}
				if (this.animationMachineListener != null) {
					this.animationMachineListener.onFinished(this);
				}
			}
		}
	}

	public void setCurrentTileIndex(int pTileIndex) {
		if(pTileIndex < FRAME_COLS*FRAME_ROWS)
		currentTileIndex= pTileIndex;

	}
	
	public int getCurrentTileIndex()
	{
		return currentTileIndex;
	}

	private int getTileCount() {
		return FRAME_COLS*FRAME_COLS;
	}

	public void stopAnimation() {
		this.mAnimationRunning = false;
	}

	public void stopAnimation(final int pTileIndex) {
		this.mAnimationRunning = false;
		this.setCurrentTileIndex(pTileIndex);
	}

	public void animate(final long pFrameDurationEach) {
		this.animate(pFrameDurationEach, null);
	}

	public void animate(final long pFrameDurationEach,
			final IAnimationListener pAnimationListener) {
		this.mAnimationData.set(pFrameDurationEach, this.getTileCount());

		this.initAnimation(pAnimationListener);
	}

	public void animate(final long pFrameDurationEach, final boolean pLoop) {
		this.animate(pFrameDurationEach, pLoop, null);
	}

	public void animate(final long pFrameDurationEach, final boolean pLoop,
			final IAnimationListener pAnimationListener) {
		this.mAnimationData.set(pFrameDurationEach, this.getTileCount(), pLoop);

		this.initAnimation(pAnimationListener);
	}

	public void animate(final long pFrameDurationEach, final int pLoopCount) {
		this.animate(pFrameDurationEach, pLoopCount, null);
	}

	public void animate(final long pFrameDurationEach, final int pLoopCount,
			final IAnimationListener pAnimationListener) {
		this.mAnimationData.set(pFrameDurationEach, this.getTileCount(),
				pLoopCount);

		this.initAnimation(pAnimationListener);
	}

	public void animate(final long[] pFrameDurations) {
		this.animate(pFrameDurations, (IAnimationListener) null);
	}

	public void animate(final long[] pFrameDurations,
			final IAnimationListener pAnimationListener) {
		this.mAnimationData.set(pFrameDurations);

		this.initAnimation(pAnimationListener);
	}

	public void animate(final long[] pFrameDurations, final boolean pLoop) {
		this.animate(pFrameDurations, pLoop, null);
	}

	public void animate(final long[] pFrameDurations, final boolean pLoop,
			final IAnimationListener pAnimationListener) {
		this.mAnimationData.set(pFrameDurations, pLoop);

		this.initAnimation(pAnimationListener);
	}

	public void animate(final long[] pFrameDurations, final int pLoopCount) {
		this.animate(pFrameDurations, pLoopCount, null);
	}

	public void animate(final long[] pFrameDurations, final int pLoopCount,
			final IAnimationListener pAnimationListener) {
		this.mAnimationData.set(pFrameDurations, pLoopCount);

		this.initAnimation(pAnimationListener);
	}

	public void animate(final long[] pFrameDurations,
			final int pFirstTileIndex, final int pLastTileIndex,
			final boolean pLoop) {
		this.animate(pFrameDurations, pFirstTileIndex, pLastTileIndex, pLoop,
				null);
	}

	public void animate(final long[] pFrameDurations,
			final int pFirstTileIndex, final int pLastTileIndex,
			final boolean pLoop, final IAnimationListener pAnimationListener) {
		this.mAnimationData.set(pFrameDurations, pFirstTileIndex,
				pLastTileIndex, pLoop);

		this.initAnimation(pAnimationListener);
	}

	public void animate(final long[] pFrameDurations,
			final int pFirstTileIndex, final int pLastTileIndex,
			final int pLoopCount) {
		this.animate(pFrameDurations, pFirstTileIndex, pLastTileIndex,
				pLoopCount, null);
	}

	public void animate(final long[] pFrameDurations,
			final int pFirstTileIndex, final int pLastTileIndex,
			final int pLoopCount, final IAnimationListener pAnimationListener) {
		this.mAnimationData.set(pFrameDurations, pFirstTileIndex,
				pLastTileIndex, pLoopCount);

		this.initAnimation(pAnimationListener);
	}

	/**
	 * Animate specifics frames.
	 *
	 * @param pFrameDurations
	 *            must have the same length as pFrames.
	 * @param pFrames
	 *            indices of the frames to animate.
	 */
	public void animate(final long[] pFrameDurations, final int[] pFrames) {
		this.animate(pFrameDurations, pFrames, null);
	}

	/**
	 * Animate specifics frames.
	 *
	 * @param pFrameDurations
	 *            must have the same length as pFrames.
	 * @param pFrames
	 *            indices of the frames to animate.
	 * @param pAnimationListener
	 */
	public void animate(final long[] pFrameDurations, final int[] pFrames,
			final IAnimationListener pAnimationListener) {
		this.mAnimationData.set(pFrameDurations, pFrames);

		this.initAnimation(pAnimationListener);
	}

	/**
	 * Animate specifics frames.
	 *
	 * @param pFrameDurations
	 *            must have the same length as pFrames.
	 * @param pFrames
	 *            indices of the frames to animate.
	 * @param pLoop
	 */
	public void animate(final long[] pFrameDurations, final int[] pFrames,
			final boolean pLoop) {
		this.animate(pFrameDurations, pFrames, pLoop, null);
	}

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
	public void animate(final long[] pFrameDurations, final int[] pFrames,
			final boolean pLoop, final IAnimationListener pAnimationListener) {
		this.mAnimationData.set(pFrameDurations, pFrames, pLoop);

		this.initAnimation(pAnimationListener);
	}

	/**
	 * Animate specifics frames.
	 *
	 * @param pFrameDurations
	 *            must have the same length as pFrames.
	 * @param pFrames
	 *            indices of the frames to animate.
	 * @param pLoopCount
	 */
	public void animate(final long[] pFrameDurations, final int[] pFrames,
			final int pLoopCount) {
		this.animate(pFrameDurations, pFrames, pLoopCount, null);
	}

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
	public void animate(final long[] pFrameDurations, final int[] pFrames,
			final int pLoopCount, final IAnimationListener pAnimationListener) {
		this.mAnimationData.set(pFrameDurations, pFrames, pLoopCount);

		this.initAnimation(pAnimationListener);
	}

	public void animate(final IAnimationData pAnimationData) {
		this.animate(pAnimationData, null);
	}

	private void animate(final IAnimationData pAnimationData,
			final IAnimationListener pAnimationListener) {
		this.mAnimationData.set(pAnimationData);

		this.initAnimation(pAnimationListener);
	}

	private void initAnimation(final IAnimationListener pAnimationListener) {
		this.mAnimationStartedFired = false;
		this.mAnimationListener = pAnimationListener;
		this.mRemainingLoopCount = this.mAnimationData.getLoopCount();

		this.mAnimationProgress = 0;
		this.mAnimationRunning = true;
	}
	
	
	
	protected void applyFilterAndWrap(Texture texture,TextureOptions options)
	{
		texture.setFilter(options.mMinFilter, options.mMagFilter); //TODO check if causes flickering when moving
		texture.setWrap(options.mWrapT, options.mWrapS);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	public static interface IAnimationListener {
		// ===========================================================
		// Constants
		// ===========================================================

		// ===========================================================
		// Fields
		// ===========================================================

		/**
		 * @param pAnimatedSprite
		 * @param pInitialLoopCount
		 *            is {@link AnimatedSpriteCore#LOOP_CONTINUOUS} when
		 *            {@link AnimatedSpriteCore} loops infinitely.
		 */
		public void onAnimationStarted(final AnimatedSpriteCore pAnimatedSprite,
				final int pInitialLoopCount);

		/**
		 * @param pAnimatedSprite
		 * @param pOldFrameIndex
		 *            equals {@link AnimatedSpriteCore#FRAMEINDEX_INVALID}, the
		 *            first time
		 *            {@link IAnimationListener#onAnimationFrameChanged(AnimatedSpriteCore, int, int)}
		 *            is called.
		 * @param pNewFrameIndex
		 *            the new frame index of the currently active animation.
		 */
		public void onAnimationFrameChanged(
				final AnimatedSpriteCore pAnimatedSprite, final int pOldFrameIndex,
				final int pNewFrameIndex);

		/**
		 * @param pAnimatedSprite
		 * @param pRemainingLoopCount
		 *            is {@link AnimatedSpriteCore#LOOP_CONTINUOUS} when
		 *            {@link AnimatedSpriteCore} loops infinitely.
		 * @param pInitialLoopCount
		 *            is {@link AnimatedSpriteCore#LOOP_CONTINUOUS} when
		 *            {@link AnimatedSpriteCore} loops infinitely.
		 */
		public void onAnimationLoopFinished(
				final AnimatedSpriteCore pAnimatedSprite,
				final int pRemainingLoopCount, final int pInitialLoopCount);

		public void onAnimationFinished(final AnimatedSpriteCore pAnimatedSprite);
	}
	
	public static class IAnimationAdapter implements IAnimationListener {
		// ===========================================================
		// Constants
		// ===========================================================

		// ===========================================================
		// Fields
		// ===========================================================

		/**
		 * @param pAnimatedSprite
		 * @param pInitialLoopCount
		 *            is {@link AnimatedSpriteCore#LOOP_CONTINUOUS} when
		 *            {@link AnimatedSpriteCore} loops infinitely.
		 */
		 
		public void onAnimationStarted(final AnimatedSpriteCore pAnimatedSprite,
				final int pInitialLoopCount)
				{}

		/**
		 * @param pAnimatedSprite
		 * @param pOldFrameIndex
		 *            equals {@link AnimatedSpriteCore#FRAMEINDEX_INVALID}, the
		 *            first time
		 *            {@link IAnimationListener#onAnimationFrameChanged(AnimatedSpriteCore, int, int)}
		 *            is called.
		 * @param pNewFrameIndex
		 *            the new frame index of the currently active animation.
		 */
		public void onAnimationFrameChanged(
				final AnimatedSpriteCore pAnimatedSprite, final int pOldFrameIndex,
				final int pNewFrameIndex)
				{}

		/**
		 * @param pAnimatedSprite
		 * @param pRemainingLoopCount
		 *            is {@link AnimatedSpriteCore#LOOP_CONTINUOUS} when
		 *            {@link AnimatedSpriteCore} loops infinitely.
		 * @param pInitialLoopCount
		 *            is {@link AnimatedSpriteCore#LOOP_CONTINUOUS} when
		 *            {@link AnimatedSpriteCore} loops infinitely.
		 */
		public void onAnimationLoopFinished(
				final AnimatedSpriteCore pAnimatedSprite,
				final int pRemainingLoopCount, final int pInitialLoopCount)
				{}

		public void onAnimationFinished(final AnimatedSpriteCore pAnimatedSprite)
		{}
	}

	@Override
	public IAnimationMachine getAnimationMachine() {
		return animationMachine;
	}

	@Override
	public void setAnimationMachine(IAnimationMachine animationMachine) {
		this.animationMachine = animationMachine;
	}

	@Override
	public void addAnimationListener(com.andgdx.animation.IAnimationListener listener) {
		animationListeners.add(listener);
		
	}

	@Override
	public void removeAnimationListener(com.andgdx.animation.IAnimationListener listener) {
		animationListeners.removeValue(listener, false);
		
	}

	@Override
	public void clearAnimationListeners() {
		animationListeners.clear();
		
	}

	@Override
	public void setAnimationMachineListener(com.andgdx.animation.IAnimationListener animationMachineListener) {
		this.animationMachineListener = animationMachineListener;
		
	}

}
