package com.andgdx.util;



public class FloatPoint {
	
	private float x,y;
	private int xint,yint;
	
	
	public FloatPoint(float x, float y)
	{
		this.x = x;
		this.y = y;
		xint = (int)x;
		yint = (int)y;
	}
	
	public float getX()
	{
		return x;
	}

	public float getY()
	{
		return y;
	}
	
	public FloatPoint(int x, int y)
	{
		this.xint = x;
		this.yint = y;
		this.x = (float) x;
		this.y = (float) y;
	}
	
	public int getXint()
	{
		return xint;
	}

	public int getYint()
	{
		return yint;
	}
	
	
	public boolean equals(Object object)
	{
		boolean returnValue = false;
		if(((FloatPoint)object).getXint() == this.getXint() && ((FloatPoint)object).getYint() == this.getYint())
		{
			returnValue = true;
		}
			return returnValue;
	}
	
	public int hashCode()
	{
		int returnValue = yint*10;
		returnValue = returnValue + xint;
		return returnValue;
	}
	
}
