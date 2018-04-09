package com.andgdx.engine.options;

import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class ResolutionPolicy {
	
	/**
	 * This policy works with a virtual screen size, which is always the same size (virtualWidth x virtualHeight)
	 * on any device. This size corresponds to the size of the camera. 
	 * The virtual screen will be stretched to fit the actual screen of the device. 
	 * Aspect ratio is not retained, so it might look different (stretched) on different devices.
	 */
	public static final int STRETCH_POLICY = 0;
	
	
	/**
	 * This policy works with a virtual screen size, which is always the same size (virtualWidth x virtualHeight)
	 * on any device. This size corresponds to the size of the camera. 
	 * The virtual screen will be scaled as much as possible to fit in the screen of the device.
	 * It will retain the apsect ratio, thus will not be stretched. This can lead to black bars at the sides.
	 */
	public static final int FIT_POLICY = 1;
	
	
	/**
	 * This policy works with a virtual screen size, which is always the same size (virtualWidth x virtualHeight)
	 * on any device. This size corresponds to the size of the camera. 
	 * The virtual screen will be scaled as much as possible to fill the whole screen of the device, no black bars appear.
	 * It will retain the apsect ratio. This leads to the fact, that parts of the screen will be cut off on some
	 * devices. 
	 */
	public static final int FILL_POLICY = 2;
	
	/**
	 * This policy does not have a constant virtual screen size, it will extend this virtual screen so that it fits the size/resolution
	 * of the device. It will retain the aspect ratio. This leads to the fact, that you can see more of the world
	 * on devices with bigger screens/higher resolutions.
	 */
	public static final int SCREEN_POLICY = 3;
	
	
	
	public static Viewport getViewport(int id, float width, float height, float maxWidth, float maxHeight)
	{
		//default Viewport will be the FitViewport
		Viewport viewport = new FitViewport(width,height);
		
		if(id == STRETCH_POLICY)
		{
			viewport = new StretchViewport(width,height);
		}
		else if(id == FILL_POLICY)
		{
			viewport = new FillViewport(width,height);
		}
		else if(id == SCREEN_POLICY)
		{
			viewport = new ScreenViewport();
		}
		
		return viewport;
	}

}
