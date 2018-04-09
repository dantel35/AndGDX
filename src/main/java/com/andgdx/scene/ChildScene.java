package com.andgdx.scene;

import com.andgdx.dummies.BackgroundSpriteDummy;
import com.andgdx.dummies.ParallaxBackgroundDummy;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class ChildScene extends Scene{
	
	private Table stageRoot;
	public Scene parentScene;
	private boolean sceneInitialised;
	private InputMultiplexer inputMultiplexer;
	
	protected void init() {
		if(sceneInitialised == false)
		{
		sceneInitialised = true;
		inputMultiplexer = parentScene.getInputMultiplexer();
		inputMultiplexer.addProcessor(this);
	 
		Gdx.input.setInputProcessor(inputMultiplexer);

		stageRoot = new Table();
		stageRoot.setFillParent(true);
		this.addActor(stageRoot);
		
		parallaxBackgroundDefault = new ParallaxBackgroundDummy();
		backgroundEntityDefault = new BackgroundSpriteDummy();
		
		backgroundEntity = backgroundEntityDefault;
		parallaxBackground = parallaxBackgroundDefault;
		drawHandler = defaultdrawHandler;
		}
	}
	
	public void setParent(Scene scene)
	{
		parentScene = scene;
	}

}
