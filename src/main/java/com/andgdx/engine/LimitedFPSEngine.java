package com.andgdx.engine;

import com.andgdx.engine.options.EngineOptions;
import com.andgdx.update.FPSLimiter;

public class LimitedFPSEngine extends Engine{
	
	/**
	 * This engine limits the maximum FPS to what you specified with fpsLimit.
	 * E.g. if you set fpsLimit to 60, your game will run with a maximum of 60FPS
	 * even on devices that would be able to run at higher FPS.
	 * @param options
	 * @param fpsLimit
	 */
	public LimitedFPSEngine(EngineOptions options, float fpsLimit)
	{
		super(options);
		limiter =new FPSLimiter(fpsLimit);
	}

}
