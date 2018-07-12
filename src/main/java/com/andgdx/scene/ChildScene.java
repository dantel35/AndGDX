package com.andgdx.scene;

import com.andgdx.engine.Engine;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.viewport.Viewport;

public class ChildScene extends Scene{
	
	public Scene parentScene;
	private Batch parentBatch;
	
	public ChildScene (Viewport viewport)
	{
		super(viewport);
	}

//	protected void init() {
//		if(sceneInitialised == false)
//		{
//		sceneInitialised = true;
//		inputMultiplexer = parentScene.getInputMultiplexer();
//		inputMultiplexer.addProcessor(this);
//	 
//		Gdx.input.setInputProcessor(inputMultiplexer);
//
//		stageRoot = new Table();
//		stageRoot.setFillParent(true);
//		this.addActor(stageRoot);
//		
//		parallaxBackgroundDefault = new ParallaxBackgroundDummy();
//		backgroundEntityDefault = new BackgroundSpriteDummy();
//		
//		backgroundEntity = backgroundEntityDefault;
//		parallaxBackground = parallaxBackgroundDefault;
//		drawHandler = defaultdrawHandler;
//		}
//	}
	

	
	public void setParent(Scene scene)
	{
		parentScene = scene;
		parentBatch = scene.getMainBatch();
		setViewport(scene.getViewport());
		super.setViewport(scene.getViewport());
	}

}
