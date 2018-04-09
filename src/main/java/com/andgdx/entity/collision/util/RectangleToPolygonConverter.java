package com.andgdx.entity.collision.util;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;

public class RectangleToPolygonConverter {
	
	/*
	 * Get counter clockwise vertices of rectangle
	 */
	public static float[] getVertices(Rectangle rectangle)
	{
		float x = rectangle.x;
		float y = rectangle.y;
		float w = rectangle.width;
		float h = rectangle.height;
		
		float x1 = x;
		float y1 = y;
		
		float x2 = x;
		float y2 = y+h;
		
		float x3 = x+w;
		float y3 = y+h;
		
		float x4 = x+w;
		float y4 = y;
		
		return new float[]{x1,y1,  x2,y2,  x3,y3,  x4,y4};
	}
	
	public static Polygon rectangleToPolygon(Rectangle rectangle)
	{
		float [] vertices = getVertices(rectangle);
		return new Polygon(vertices);
	}

}
