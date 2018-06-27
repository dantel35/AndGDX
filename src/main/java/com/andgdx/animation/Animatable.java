package com.andgdx.animation;

public interface Animatable {
	
//	public void playAnimationConfig(String animationConfigKey);
//	public void stopAnimationConfig(String animationConfigKey);
//	public void pauseAnimationConfig(String animationConfigKey);
//	public void resumeAnimationConfig(String animationConfigKey);
//	
//	
//	public void addAnimationConfig(String animationConfigKey, AnimationConfig animationConfig);
//	public AnimationConfig getAnimationConfig(String animationConfigKey);

	public IAnimationMachine getAnimationMachine();
	public void setAnimationMachine(IAnimationMachine animationMachine);
	
	/**
	 * This listener will be set by the animationMachine. Use addAnimationListener(IAnimationListener listener)
	 * for setting your own listeners.
	 * @param animationMachineListener
	 */
	public void setAnimationMachineListener(IAnimationListener animationMachineListener);
	
	public void addAnimationListener(IAnimationListener listener);
	public void removeAnimationListener(IAnimationListener listener);
	public void clearAnimationListeners();
	
	
}
