package com.andgdx.animation;

import java.util.Iterator;

import com.andgdx.entity.IEntity;
import com.badlogic.gdx.utils.ObjectMap;

public class AnimationConfigBag {

	ObjectMap<AnimationMachineState,AnimationConfig> configs = new ObjectMap<AnimationMachineState,AnimationConfig>();
	AnimationConfig currentConfig;
	boolean strict = false;
	IAnimationListener listener;
	
	public AnimationConfigBag(IAnimationListener listener)
	{
		this.listener = listener;
	}
	
	
	public void add(AnimationMachineState state, AnimationConfig config)
	{
		config.setListener(listener);
		configs.put(state,config);
	}
	
	public void remove(AnimationMachineState state, AnimationConfig config)
	{
		config.setListener(null);
		configs.remove(state);
	}

	
	public AnimationConfig get(AnimationMachineState state)
	{
		return configs.get(state);
	}
	
	
	public void play(Animatable entity)
	{
		if(currentConfig != null)
		{
//			if(currentConfig.isPlaying == false)
//			{
				currentConfig.play(entity);
//			}
		}
	}
	
	public void stop(Animatable entity)
	{
		Iterator<AnimationConfig> values = configs.values();
		AnimationConfig animationConf = null;
		while (values.hasNext())
		{
			animationConf = values.next();
			animationConf.stop(entity);
		}
	}
	
	
	public void setCurrent(AnimationMachineState state)
	{
		
		Iterator<AnimationMachineState> keys = configs.keys();
		int overlap = 0;
		int tmpOverlap;
		AnimationMachineState chosenState = null;
		while (keys.hasNext())
		{
			AnimationMachineState itState = keys.next();
			tmpOverlap =  itState.overlap(state);
			if(passesStrictSetting(itState, state))
			{
				chosenState = (tmpOverlap > overlap) ? itState : chosenState;
				overlap = (tmpOverlap > overlap) ? tmpOverlap : overlap;				
			}

		}
		
		if (chosenState != null)
		{
			currentConfig = configs.get(chosenState);
		}
		 
	}
	
	public void setStrictMode(boolean mode)
	{
		this.strict = mode;
	}
	
	private boolean passesStrictSetting(AnimationMachineState neededState, AnimationMachineState offeredState)
	{
		return !strict || (neededState.isSubset(offeredState));
	}
	
	public AnimationConfig getCurrent()
	{
		return currentConfig;
	}
	
}
