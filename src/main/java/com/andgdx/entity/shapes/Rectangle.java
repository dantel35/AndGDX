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
