package com.andgdx.animation;

import com.andgdx.entity.IEntity;

public interface IAnimationMachine {
	
	/*
	 * Play a type of an anmiation. Like 'walk'. Based on the states
	 * you defined, 'walk' could map to animation 'run_left'.
	 */
	public void play(String animationType);
	public void addToState(String state);
	public void removeFromState(String state);
	public void clearState();
	public AnimationMachineState getState();
	public void stop();
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

}
