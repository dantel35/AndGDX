package com.andgdx.util;

public class AndGDXMathUtils {

	/**
	 * Clumsy implementation.
	 * @param fromX
	 * @param fromY
	 * @param toX
	 * @param toY
	 * @return
	 */
	public static float calculateRotationAngle(float fromX, float fromY, float toX, float toY) {


		float yKathete = fromY - toY;
		float xKathete = fromX - toX;
		//double tangens = xKathete / yKathete;
		double tangens = 0;
		float alpha=0;
		

		if(yKathete > 0) //end is above start
		{
			if(xKathete == 0)
			{
				alpha = 0; //0Grad
			}
			else if(xKathete > 0) //end is left of start
			{
				tangens = Math.abs(yKathete / xKathete);
				alpha = (float) Math.toDegrees(Math.atan(tangens))+270;
			}
			else if(xKathete < 0) //end is right of start 
			{
				tangens = Math.abs(xKathete / yKathete);
				alpha = (float) Math.toDegrees(Math.atan(tangens));
			}
		}
		else if(yKathete == 0) // end on same height as start
		{
			if(xKathete  > 0) //end left of start 
			{
				alpha = 270;
			}
			else if(xKathete < 0) //end right of start
			{
				alpha = 90;
			}
		}
		else if(yKathete <0) //end is below start
		{
			if(xKathete > 0) // end is left of start
			{
				tangens = Math.abs(xKathete / yKathete);
				alpha = (float) Math.toDegrees(Math.atan(tangens))+180;
			}
			else if(xKathete == 0) // end is exactly below start - on a straight line
			{
				alpha = 180;
				
			}
			else if(xKathete < 0) // end is right of start.
			{
				
				tangens = Math.abs(yKathete / xKathete);
				alpha = (float) Math.toDegrees(Math.atan(tangens))+90;
			}
		}
	 

		return alpha;
	}
	
}
