package com.andgdx.animation;

public interface Animatable {
	
	public void playAnimationConfig(String animationConfigKey);
	public void stopAnimationConfig(String animationConfigKey);
	public void pauseAnimationConfig(String animationConfigKey);
	public void resumeAnimationConfig(String animationConfigKey);
	
	
	public void addAnimationConfig(String animationConfigKey, AnimationConfig animationConfig);
	public AnimationConfig getAnimationConfig(String animationConfigKey);

}
