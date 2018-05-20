package com.andgdx.entity.modifier.listener;

import com.andgdx.entity.IEntity;

public abstract class EntityModifierAdapter implements IEntityModifierListener{
	
	 
	public abstract void onFinished(IEntity entity);
	
	 
	public  abstract void onStarted(IEntity entity);

}
