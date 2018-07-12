package com.andgdx.entity.shapes;

import com.andgdx.entity.Entity;
import com.badlogic.gdx.graphics.Color;

public class Rectangle extends Entity {
	
	RectangleCore core;
 
	
	public Rectangle()
	{}
//	
	public Rectangle(int x, int y,int width, int height)
	{
		core = new RectangleCore(x,y,width,height);	
		core.setBounds(0, 0, width, height);
		this.setBounds(x, y, width, height);
		addActor(core);
	}
	
	public Rectangle(float x, float y,float width, float height)
	{
		this((int)x,(int)y,(int)width,(int)height );
	}
	
	public void setWidth(float width)
	{
		core.setWidth(width);
		super.setWidth(width);
	}
	
	public void setHeight(float height)
	{
		core.setHeight(height);
		super.setHeight(height);
	}
//	
//	public float getHeight()
//	{
//		return core.getHeight();
//	}
//	
//	public float getWidth()
//	{
//		return core.getWidth();
//	}
//	
	public void setX(float x)
	{
		core.setX(x);
		super.setX(x);
	}
//	
//	public float getX()
//	{
//		return core.getX();
//	}
//	
//	public float getY()
//	{
//		return core.getY();
//	}
//	
	public void setY(float y)
	{
		core.setY(y);
		super.setX(y);
	}
	
	
	public void setColor(Color color)
	{
		super.setColor(color);
		if(core!=null)
		core.setColor(color);
	}
	
	public void setAlpha(float alpha) {
		if(core!=null)
		core.setAlpha(alpha);
	}

}
