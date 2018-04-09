package com.andgdx.entity.collision;

import com.andgdx.entity.IEntity;
import com.badlogic.gdx.graphics.g2d.Batch;

public interface ICollideArea {
	
	public final static int RECTANGLE = 0;
	public final static int CIRCLE = 1;
	public static int DEFAULT = 0;
	
 
	public boolean collides(IEntity entity, IEntity entity2);
	
	public void updateCollideArea(float x, float y, float width, float height, float originX, float originY, float rotationAngle, float scaleX, float scaleY);

	/**
	 * Used to make the mask visible by drawing it. For debug purposes.
	 * @param batch
	 * @param alpha
	 */
	public void debugDraw(Batch batch);
}
