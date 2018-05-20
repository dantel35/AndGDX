package com.andgdx.entity.modifier.listener;

import com.andgdx.entity.IEntity;

public interface IEntityModifierListener {
	
	/**
	 * Called when modifier/action finished
	 */
	public void onFinished(IEntity entity);
 
	
	/**
	 * Called when modifier/action started
	 */
	public  void onStarted(IEntity entity);
	 

}
