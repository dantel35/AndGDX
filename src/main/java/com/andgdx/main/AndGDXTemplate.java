package com.andgdx.main;

import com.andgdx.camera.SimpleCamera;
import com.andgdx.engine.Engine;
import com.andgdx.engine.options.EngineOptions;
import com.andgdx.scene.Scene;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.utils.viewport.Viewport;

public abstract class AndGDXTemplate extends ApplicationAdapter {
	
	public Scene currentScene;
	public Viewport currentViewport;
	public Engine engine;
	public EngineOptions engineOptions;
	
 
	
	public AndGDXTemplate()
	{
		
	}
	 
	public void setEngine(Engine engine)
	{
		this.engine = engine;
	}
	
	public Engine getEngine()
	{
		return engine;
	}
	
	public SimpleCamera getCamera()
	{
		return EngineOptions.getCamera();
	}
	
	
	@Override
	public void create () {
		  engineOptions = onCreateEngineOptions();
		Engine limitedFPS = onCreateEngine(engineOptions);
		setEngine(limitedFPS);
 
		currentScene = onCreateScene();
		engine.setScene(currentScene); 

	
	}
	/**
	 * Called as first method in the create process. Create your EngineOptions in here.
	 * @return engineOptions - return the EngineOptions you created.
	 */
	public abstract EngineOptions onCreateEngineOptions();
	
	/**
	 * Called as second method in the create process. Create your Engine in here, using
	 * the EngineOptions you prepared in onCreateEngineOptions();.
	 * @param engineOptions
	 * @return engine - do not forget to return the created engine.
	 */
	public abstract Engine onCreateEngine(EngineOptions engineOptions);
	
	/**
	 * Called as last method in the create process. Create and populate your starting scene in here.
	 * This scene will be set automatically as the first scene that the engine will display.
	 * @return scene - do not forget to return
	 */
	public abstract Scene onCreateScene();
	
	

	@Override
	public void render () {
		engine.onUpdate();
	}
	
	@Override
	public void resume () {
		engine.onUpdate();
	}

	
	
	public void resize(int width, int height) {
		engine.onResize(width,height);
	
	}
}
