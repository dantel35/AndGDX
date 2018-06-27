package com.andgdx.animation;


public abstract class AnimationConfig {
	
	public boolean isPlaying = false;
	public boolean isStopped = false;
	public boolean isPaused = false;
	public String name;
	private IAnimationListener listener;

	public void setListener(IAnimationListener listener)
	{
		this.listener = listener;
	}
	
	public void play(Animatable entity)
	{
		isPlaying = true;
		isStopped = false;
		entity.setAnimationMachineListener(listener);
		onPlay(entity);
	};
	
	public void stop(Animatable entity)
	{
		entity.setAnimationMachineListener(listener);
		onStop(entity);
		isStopped = true;
		isPlaying = false;
	}
	
	public void pause(Animatable entity)
	{
		isPaused = true;
		isPlaying = false;
		entity.setAnimationMachineListener(listener);
		onPause(entity);
	}
	
	public void resume(Animatable entity)
	{
		isPaused = false;
		isPlaying = true;
		entity.setAnimationMachineListener(listener);
		onPause(entity);
	}
	
	public abstract void onPlay(Animatable entity);
	public abstract void onStop(Animatable entity);
	public abstract void onPause(Animatable entity);
	public abstract void onResume(Animatable entity);

}
