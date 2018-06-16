package com.andgdx.entity;

import com.andgdx.util.AndGDXMathUtils;

public class AndGDXEntityUtil {
	
	
	public static void updateFacingAndMovementDirection(IEntity entity,float startX,float startY, float endX,float endY)
	{
		 	int direction;
			IEntity lookAtEntity = entity.getLookAt();
			if (lookAtEntity == null)
			{ 
				direction = (int) AndGDXMathUtils.calculateRotationAngle(startX, startY, endX, endY);
				entity.setFacingDirection(direction);				
				entity.setMovementDirection(direction);				

			}
			else
			{
				direction = (int) AndGDXMathUtils.calculateRotationAngle(startX, startY, lookAtEntity.getCenterX(), lookAtEntity.getCenterY());

				entity.setFacingDirection(direction);				
				direction = (int) AndGDXMathUtils.calculateRotationAngle(startX, startY, endX, endY);
				entity.setMovementDirection(direction);				
				
			}
		
	}

}
