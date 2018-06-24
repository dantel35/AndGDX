package com.andgdx.animation;

import com.andgdx.entity.IEntity;

public interface IAnimationMachine {
	
	/*
	 * Play a type of an anmiation. Like 'walk'. Based on the states
	 * you defined, 'walk' could map to animation 'run_left'.
	 */
	public IAnimationMachine play(String animationType);
	public void addToState(String state);
	public void removeFromState(String state);
	public void clearState();
	public AnimationMachineState getState();
	public IAnimationMachine stop();
	public IAnimationMachine chain(String animationType);
	
	public void setEntity(IEntity entity);
	/**
	 * Example: 
	 * animationType is the general type of animation you want to configure. For example "walk" or even "move".
	 * neededStates describes the states that this AnimationMachine needs to have in order to play the needed animation.
	 * @param animationType
	 * @param animationName
	 * @param animationConfig
	 * @param neededStates
	 */
	public void add(String animationType,AnimationConfig animationConfig, AnimationMachineState neededState);
	public void add(String animationType,AnimationConfig animationConfig, String... states);
	
	/**
	 * Setting the animation machine to strict mode means it will only play an animation
	 * if the needed state is at least a subset of the current state of the machine.
	 * Having the animation machine in default mode will cause it to play an animation in any case, even
	 * if the state is not exactly matched.
	 * @param strict
	 */
	public void setStrictMode(boolean strict);

}
