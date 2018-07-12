package com.andgdx.entity.shapes;

import com.badlogic.gdx.graphics.Color;

public class Line extends Rectangle{
	RectangleCore core;
	
	public Line(int fromX, int fromY, int toX, int toY, Color color, int lineWidth)
	{
		
		float a = Math.abs(toX-fromX);
		float b = Math.abs(toY-fromY);
		float lineLength = (float) Math.sqrt((a*a + b*b));
		core = new RectangleCore(fromX,fromY, (int)lineLength,lineWidth);	
		core.setBounds(0, 0, (int)lineLength,lineWidth);
		core.setColor(color);
		this.setBounds(fromX, fromY-lineWidth/2,(int)lineLength,lineWidth);
		this.setOrigin(0, lineWidth/2);
		addActor(core);
		
		float degrees = (float) Math.toDegrees(Math.asin(b/lineLength));
		this.setRotation(degrees);
		
	}
	
	public Line(float fromX, float fromY, float toX, float toY, Color color, float lineWidth)
	{
		this((int) fromX, (int) fromY, (int) toX, (int) toY, color, (int) lineWidth);
	}
	
	public Line(float fromX, float fromY, float toX, float toY,float lineWidth)
	{
		this((int) fromX, (int) fromY, (int) toX, (int) toY, Color.BLACK, (int) lineWidth);
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
