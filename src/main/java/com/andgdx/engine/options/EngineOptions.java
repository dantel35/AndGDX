package com.andgdx.engine.options;

import com.andgdx.camera.SimpleCamera;
import com.badlogic.gdx.utils.viewport.Viewport;

 

public class EngineOptions {
	
	float width, height;
	Viewport viewport;
	public static SimpleCamera camera;
	public EngineOptions(int resolutionPolicy, SimpleCamera camera)
	{
		width = camera.getWidth();
		height = camera.getHeight();
		viewport = ResolutionPolicy.getViewport(resolutionPolicy, width, height, 1920,1080);
		camera.setViewport(viewport);
		this.camera = camera;
		
	}
	public float getWidth() {
		return width;
	}
	public float getHeight() {
		return height;
	}
	public Viewport getViewport() {
		return viewport;
	}
	
	public static SimpleCamera getCamera()
	{
		return camera;
	}
	

	
	

}
