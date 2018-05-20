package com.andgdx.animation;

import com.andgdx.entity.IEntity;

public abstract class AnimationConfig {
	
	public boolean isPlaying = false;
	public boolean isStopped = false;
	public boolean isPaused = false;
	public String name;

	
	public void play(IEntity entity)
	{
		isPlaying = true;
		isStopped = false;
		onPlay(entity);
	};
	
	public void stop(IEntity entity)
	{
		onStop(entity);
		isStopped = true;
		isPlaying = false;
	}
	
	public void pause(IEntity entity)
	{
		isPaused = true;
		isPlaying = false;
		onPause(entity);
	}
	
	public void resume(IEntity entity)
	{
		isPaused = false;
		isPlaying = true;
		onPause(entity);
	}
	
	public abstract void onPlay(IEntity entity);
	public abstract void onStop(IEntity entity);
	public abstract void onPause(IEntity entity);
	public abstract void onResume(IEntity entity);

}
