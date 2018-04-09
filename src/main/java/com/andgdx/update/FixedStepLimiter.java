package com.andgdx.update;

import com.andgdx.util.TimeConstants;

public class FixedStepLimiter implements IUpdateLimiter {

	private final long mStepLength;
	private long mSecondsElapsedAccumulator;
	
	public FixedStepLimiter(final int pStepsPerSecond)
	{
		this.mStepLength = TimeConstants.NANOSECONDS_PER_SECOND / pStepsPerSecond;
	}
	
	 
	public void delay() {
		// TODO Auto-generated method stub
		
	}

 

}
