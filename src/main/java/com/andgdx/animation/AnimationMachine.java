package com.andgdx.animation;

import java.util.Iterator;

import com.andgdx.entity.IEntity;
import com.badlogic.gdx.utils.ObjectMap;

public class AnimationMachine implements IAnimationMachine {
	
	ObjectMap<String, AnimationConfigBag> animationConfigMap = new ObjectMap<String, AnimationConfigBag>();
	AnimationMachineState state = new AnimationMachineState();
	IEntity entity;
	boolean strict = false;
	
	public AnimationMachine (IEntity entity)
	{
		this.entity = entity;
	}
	
	/**
	 * Setting the animation machine to strict mode means it will only play an animation
	 * if the needed state is at least a subset of the current state of the machine.
	 * Having the animation machine in default mode will cause it to play an animation in any case, even
	 * if the state is not exactly matched.
	 * @param strict
	 */
	public void setStrictMode(boolean strict)
	{
		this.strict = strict;
	}
	
	
	public AnimationMachine (boolean enableStrictMode)
	{
		this.strict = enableStrictMode;
	}

	
		private void updateAnimationConfigs(AnimationMachineState state)
		{
			Iterator<AnimationConfigBag> values = animationConfigMap.values();

			AnimationConfigBag animationConf = null;
			while (values.hasNext())
			{
				animationConf = values.next();
				animationConf.setCurrent(state);
			}
			
		}

	public void play(String animationType) {
		// TODO Auto-generated method stub
		AnimationConfigBag conf = animationConfigMap.get(animationType);
		conf.play(entity);
	}
	
	public void setEntity(IEntity entity)
	{
		this.entity= entity;
	}

	public void addToState(String state) {
		// TODO Auto-generated method stub
		this.state.addState(state);
		updateAnimationConfigs(this.state);
		
	}

	public void removeFromState(String state) {
		// TODO Auto-generated method stub
		this.state.removeState(state);
		updateAnimationConfigs(this.state);

	}

	public AnimationMachineState getState() {
		// TODO Auto-generated method stub
		return this.state;
	}

	public void stop() {
		Iterator<AnimationConfigBag> values = animationConfigMap.values();
		AnimationConfigBag animationConf = null;
		while (values.hasNext())
		{
			animationConf = values.next();
			animationConf.stop(entity);
		}
		
	}

	public void add(String animationType,AnimationConfig animationConfig, AnimationMachineState neededState) {
		AnimationConfigBag bag = animationConfigMap.get(animationType);
		if (bag == null)
		{
			bag = new AnimationConfigBag(); //make this poolable.
		}
		bag.setStrictMode(strict);
		bag.add(neededState, animationConfig);
		animationConfigMap.put(animationType, bag);
	}

	public void clearState() {
		// TODO Auto-generated method stub
		animationConfigMap.clear();
	}

	public void add(String animationType, AnimationConfig animationConfig, String... states) {
		AnimationMachineState aMachState = new AnimationMachineState();
		for (String state : states)
		{
			aMachState.addState(state);
		}
		add(animationType, animationConfig, aMachState);
		
	}

}
