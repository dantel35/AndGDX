package com.andgdx.animation;

import java.util.Iterator;

import com.andgdx.entity.IEntity;
import com.badlogic.gdx.utils.ObjectMap;

public class AnimationConfigBag {

	ObjectMap<AnimationMachineState,AnimationConfig> configs = new ObjectMap<AnimationMachineState,AnimationConfig>();
	AnimationConfig currentConfig;
	
	public void add(AnimationMachineState state, AnimationConfig config)
	{
		configs.put(state,config);
	}
	
	public void remove(AnimationMachineState state, AnimationConfig config)
	{
		configs.remove(state);
	}

	
	public AnimationConfig get(AnimationMachineState state)
	{
		return configs.get(state);
	}
	
	
	public void play(IEntity entity)
	{
		if(currentConfig != null)
		{
			System.out.println("play executed " + currentConfig.isPlaying );
			if(currentConfig.isPlaying == false)
			{
				currentConfig.play(entity);
				System.out.println("currentConfig is playing");
			}
		}
		else
		{
			System.out.println("currentConfig was null");
		}
	}
	
	public void stop(IEntity entity)
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
			chosenState = (tmpOverlap > overlap) ? itState : chosenState;
			overlap = (tmpOverlap > overlap) ? tmpOverlap : overlap;

		}
		
		if (chosenState != null)
		{
			currentConfig = configs.get(chosenState);
		}
		 
	}
	
	public AnimationConfig getCurrent()
	{
		return currentConfig;
	}
	
}
