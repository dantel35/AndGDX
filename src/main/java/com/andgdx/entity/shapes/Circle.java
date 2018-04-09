package com.andgdx.entity.shapes;

import com.andgdx.entity.Entity;
import com.badlogic.gdx.graphics.Color;

public class Circle extends Entity {
	
	CircleCore core;
 
	
	public Circle()
	{}
//	
	public Circle(int x, int y,int radius)
	{
		core = new CircleCore(x,y,radius);	
		int width = radius/2;
		int height = width;
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
