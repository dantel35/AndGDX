package com.andgdx.dummies;

import com.andgdx.scene.background.ParallaxBackground;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;

public class ParallaxBackgroundDummy extends ParallaxBackground {
	
	/**
	 * Dummy class for ParallaxBackground. For the case no ParallaxBackground is used.
	 * This class eliminates an extra if check in the drawing routine of the scene.
	 */
	public void draw(OrthographicCamera worldCamera, Batch batch){
		 //do nothing
	}

}
