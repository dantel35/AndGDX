package com.andgdx.animation;

import java.util.Iterator;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

public class AnimationMachine implements IAnimationMachine {
	
	ObjectMap<String, AnimationConfigBag> animationConfigMap = new ObjectMap<String, AnimationConfigBag>();
	AnimationMachineState state = new AnimationMachineState();
	Animatable entity;
	boolean strict = false;
	Array<String> chained = new Array<String>();
	IAnimationListener listener = new IAnimationListener() {
	
		public void onStarted(Animatable animatable) {
			playing = true;
			
		}
		
		
		public void onFinished(Animatable animatable) {
			if (chained.size > 0)
			{
				playNextInChain();
			}
			else
			{
				playing = false;
			}
			
		}

		
	};
	private boolean playing = false;
	private String whatIsPlaying;
	
	public AnimationMachine (Animatable entity)
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

	public IAnimationMachine play(String animationType) {
		AnimationConfigBag conf = animationConfigMap.get(animationType);
		conf.play(entity);
		this.playing  = true;
		whatIsPlaying = animationType;
		return this;
	}
	
	public void setEntity(Animatable entity)
	{
		this.entity= entity;
	}
	
	public boolean isThisPlaying(String animationType)
	{
		return whatIsPlaying.equals(animationType);
	}

	public void addToState(String state) {
		this.state.addState(state);
		updateAnimationConfigs(this.state);
		
	}

	public void removeFromState(String state) {
		this.state.removeState(state);
		updateAnimationConfigs(this.state);

	}

	public AnimationMachineState getState() {
		return this.state;
	}

	public IAnimationMachine stop() {
		Iterator<AnimationConfigBag> values = animationConfigMap.values();
		AnimationConfigBag animationConf = null;
		while (values.hasNext())
		{
			animationConf = values.next();
			animationConf.stop(entity);
		}
		return this;
		
	}
	
	public IAnimationMachine chain(String animationType)
	{
		chained.add(animationType);
		if (playing != true)
		{
			playNextInChain();
		}
		return this;
	}

	public void playNextInChain() {
		String found=  null;
		for(String s : chained)
		{
			found = s;
			play(found);
			break;
		}
		if (found != null)
		chained.removeValue(found, false);
	}
	

	public void add(String animationType,AnimationConfig animationConfig, AnimationMachineState neededState) {
		AnimationConfigBag bag = animationConfigMap.get(animationType);
		if (bag == null)
		{
			bag = new AnimationConfigBag(listener); //make this poolable.
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
