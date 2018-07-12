package com.andgdx.scene;

import com.andgdx.engine.Engine;
import com.andgdx.entity.Entity;


public class HUD extends Entity {
	
	
	
 
	public float getX()
	{
		return super.getX() - Engine.getCamera().getXMin();
	}
	
	public float getY()
	{
		return super.getY() - Engine.getCamera().getYMin();
	}
	
	public void setX(float x)
	{
		super.setX(Engine.getCamera().getXMin() + x);
	}
	
	public void setY(float y)
	{
		super.setY(Engine.getCamera().getXMin() + y);
	}
	 
	 
}
