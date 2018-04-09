package com.andgdx.engine;

import com.andgdx.engine.options.EngineOptions;
import com.andgdx.update.FixedStepLimiter;
import com.andgdx.util.TimeConstants;

public class FixedStepEngine extends Engine{
	
	private final long stepLength;
	private long secondsElapsedAccumulator;
	private long nanosecondsElapsed;

	/**
	 *
	 *This engine tries to achieve a specific amount of updates per second. Notice: It affects the updates, it does not affect
	 *the actual FPS the game delivers on a specific device. But every action/modifier/movement and animation is tied to updates of the engine, not FPS.
	 * When the time since the last update is bigger long the wanted steplength, additional updates are executed.
	 * @param pEngineOptions
	 * @param pStepsPerSecond
	 */
	public FixedStepEngine(final EngineOptions pEngineOptions, final int pStepsPerSecond) {
		super(pEngineOptions);
		limiter = new FixedStepLimiter(pStepsPerSecond);
		this.stepLength = TimeConstants.NANOSECONDS_PER_SECOND / pStepsPerSecond;
	}
	
	
	public void onUpdate() {
		nanosecondsElapsed = getElapsedNanoseconds();
		
		this.secondsElapsedAccumulator += nanosecondsElapsed;

		
		final long stepLength = this.stepLength;
		
		
		while (this.secondsElapsedAccumulator >= stepLength) {
			super.onUpdate(stepLength * TimeConstants.SECONDS_PER_NANOSECOND);
			this.secondsElapsedAccumulator -= stepLength;
		}
		
	}

}
