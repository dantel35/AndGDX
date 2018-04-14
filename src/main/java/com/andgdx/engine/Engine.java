package com.andgdx.engine;

import com.andgdx.camera.SimpleCamera;
import com.andgdx.engine.options.EngineOptions;
import com.andgdx.scene.Scene;
import com.andgdx.scene.SceneManager;
import com.andgdx.update.EmptyLimiter;
import com.andgdx.update.IUpdateHandler;
import com.andgdx.update.IUpdateLimiter;
import com.andgdx.update.UpdateHandlerList;
import com.andgdx.util.TimeConstants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Engine  {

	Scene mScene;
	public static Viewport currentViewport;
	protected IUpdateLimiter limiter;
	protected EngineOptions options;
	private static final int UPDATEHANDLERS_CAPACITY_DEFAULT = 8;
	private final UpdateHandlerList mUpdateHandlers = new UpdateHandlerList(
			UPDATEHANDLERS_CAPACITY_DEFAULT);
	protected long currentTime;
	protected long deltaTime = TimeUtils.nanoTime();
	protected long previousTime;
	private boolean paused = false;
	public static AssetManager assetManager;
	
	public static float currentAndGDXDelta;
	
	private com.badlogic.ashley.core.Engine ashleyEngine;

	
	
	public Engine(EngineOptions options) {
		
		limiter = new EmptyLimiter();
		this.options = options;
		currentViewport = options.getViewport();
		assetManager = new AssetManager();
		SceneManager.engine = this;
		ashleyEngine = options.getAshleyEngine();
	}
	
	public static Engine getInstance()
	{
		return SceneManager.engine;
	}
	
	public static SimpleCamera getCamera()
	{
		return EngineOptions.getCamera();
	}

	public static AssetManager getAssetManager()
	{
		return assetManager;
	}
	
	public void setAssetManager(AssetManager manager)
	{
		assetManager = manager;
	}
	
	/**
	 * Calling this method will renew the AssetManager. This leads to unloading every resource
	 * you loaded with the current instance of it!
	 */
	public static void resetAssetManager()
	{
		assetManager.dispose();
		assetManager = new AssetManager();
	}


	private void synchUpdate() {
		if (limiter != null) {
			limiter.delay();
		}
	}
	
	/**
	 * Pauses the engine. All rendering/updating will pause.
	 */
	public void pause()
	{
		paused  = true;
	}
	
	/**
	 * Resumes engine. If engine was stopped, rendering/updating will be resumed again.
	 */
	public void resume()
	{
		paused = false;
	}

	
	
	/**
	 * Registers an UpdateHandler, that will be called on every update of the
	 * engine.
	 * 
	 * @param handler
	 */
	public void registerUpdateHandler(final IUpdateHandler handler) {
		this.mUpdateHandlers.add(handler);
	}

	/**
	 * Removes the specified UpdateHanlder (or TimerHandler), so that it will
	 * not be executed anymore.
	 * 
	 * @param handler
	 */
	public void unregisterUpdateHandler(final IUpdateHandler handler) {
		this.mUpdateHandlers.remove(handler);
	}

	/**
	 * Removes all UpdateHandlers (including TimerHandler!), so that they are
	 * not executed anymore.
	 */
	public void clearUpdateHandlers() {
		mUpdateHandlers.clear();
	}

	/**
	 * Set the scene that you want to be drawn.
	 * 
	 * @param scene
	 */
	public void setScene(Scene scene) {
		if(scene != null)
		{
		mScene = scene;
		mScene.onInitialiseScene();
		scene.setViewport(currentViewport, (int)options.getCamera().getWidth(), (int)options.getCamera().getHeight());
		}
	}

	/**
	 * Returns the scene that is currently being drawn.
	 * 
	 * @return Scene
	 */
	public Scene getCurrentScene() {
		return mScene;
	}
	
	
	public void onUpdate()
	{
		onUpdate(Gdx.graphics.getDeltaTime());
	}
	

	/**
	 * Do not call this yourself. This method ticks all updates.
	 */
	public  void onUpdate(float deltaTime) {
 
		if(!paused)
		{
			ashleyEngine.update(deltaTime);
		synchUpdate();
		currentAndGDXDelta = deltaTime;
		onUpdateScene(deltaTime);
		EngineOptions.getCamera().onUpdate(deltaTime);
//		System.out.println("x : " + currentViewport.getCamera().position.x + " y : " +  + currentViewport.getCamera().position.y );
		onUpdateUpdateHandlers(deltaTime);
		}
	}

	protected void onUpdateScene(float deltaTime) {
		if (mScene != null) {
			mScene.onUpdate(deltaTime);
		}
	}

	protected long getElapsedNanoseconds() {
		float time =   Gdx.graphics.getDeltaTime();
		long delta = (long) (time *  TimeConstants.NANOSECONDS_PER_SECOND);
		
		return delta;

	}

	protected void onUpdateUpdateHandlers(final float pSecondsElapsed) {
		this.mUpdateHandlers.onUpdate(pSecondsElapsed);
	}

	public void onResize(int width, int height) {
		if(mScene != null)
		{
			mScene.updateViewport();
		}
		
	}

}
