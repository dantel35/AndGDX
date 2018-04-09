package com.andgdx.entity.collision;

import com.andgdx.entity.IEntity;
import com.andgdx.entity.collision.util.CirclePolygonCollision;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;

public class RectangularPolygonMask extends Polygon implements ICollideArea{

	
	private int originOffsetX,  originOffsetY,  offsetWidth,  offsetHeight;
	private float originX, originY, scaleX, scaleY;
	
	private float debugX, debugY;
	private com.andgdx.entity.shapes.Rectangle debugRectangle;
	private float rotationAngle;
	float x,y, width, height;
	
	float oldX,oldY;
	boolean verticesSet;
	
	
	public RectangularPolygonMask()
	{
		verticesSet = false;
	}
	
	
	public RectangularPolygonMask(int originOffsetX, int originOffsetY, int offsetWidth, int offsetHeight)
	{
		this.originOffsetX = originOffsetX;
		this.originOffsetY = originOffsetY;
		this.offsetHeight = offsetHeight;
		this.offsetWidth = offsetWidth;
		
		
		setRectangularVertexes(originOffsetX, originOffsetY, offsetWidth,
				offsetHeight);
	}


	private void setRectangularVertexes(float originOffsetX, float originOffsetY,
			float offsetWidth, float offsetHeight) {
		float x = originOffsetX;
		float y = originOffsetY;
		float w =offsetWidth;
		float h = offsetHeight;
		
		float x1 = x;
		float y1 = y;
		
		float x2 = x;
		float y2 = y+h;
		
		float x3 = x+w;
		float y3 = y+h;
		
		float x4 = x+w;
		float y4 = y;
		
		float[] vertices = new float[]{x1,y1,  x2,y2,  x3,y3,  x4,y4};
		
		this.setVertices(vertices);
	}
	
	public boolean collides(IEntity entity1, IEntity entity2) {
		ICollideArea first = entity1.getCollideArea();
		ICollideArea second = entity2.getCollideArea();
		boolean returnValue = false;
		if (!verticesSet) { return false;}
		
		if (first instanceof RectangularPolygonMask && second instanceof RectangularPolygonMask) {
			RectangularPolygonMask firstPolygonRectangle = (RectangularPolygonMask) first;
			RectangularPolygonMask secondPolygonRectangle = (RectangularPolygonMask) second;

			returnValue = Intersector.overlapConvexPolygons(firstPolygonRectangle,secondPolygonRectangle);
			System.out.println("polygons involved involved 2");
		}
		else if (first instanceof RectangularPolygonMask && second instanceof RectangularMask) {
			RectangularPolygonMask polygon = (RectangularPolygonMask) first;
			RectangularMask rectangle = (RectangularMask) second;

			returnValue = rectangle.collides(rectangle, polygon); 
			System.out.println("polygon and rectangle involved involved 2");
		}
		else if (first instanceof RectangularMask && second instanceof RectangularPolygonMask) {
			RectangularPolygonMask polygon = (RectangularPolygonMask) second;
			RectangularMask rectangle = (RectangularMask) first;

			returnValue = rectangle.collides(rectangle, polygon); 
			System.out.println("polygon and rectangle involved involved 2");
		}
		else if((first instanceof RectangularPolygonMask && second instanceof Circle))
		{
			RectangularPolygonMask rectangle = (RectangularPolygonMask)first;
			 
			Circle circle = (Circle)second;
			returnValue = CirclePolygonCollision.overlaps(rectangle,circle); 
			System.out.println("circle involved 2");
		}
		else if((first instanceof Circle && second instanceof RectangularPolygonMask))
		{
			System.out.println("circle involved 1");

			RectangularPolygonMask rectangle = (RectangularPolygonMask)second;
			 
			Circle circle = (Circle)first;
			returnValue = CirclePolygonCollision.overlaps(rectangle,circle); 
		}
		return returnValue;
	}

	public void updateCollideArea(float x, float y, float width, float height,
			float originX, float originY, float rotationAngle, float scaleX,
			float scaleY) {
		this.x =  (originOffsetX)*scaleX+x;
		this.y = (originOffsetY)*scaleY+y;
		
		this.debugX = (x+originOffsetX);
		this.debugY = (y+originOffsetY);
		this.width = (width+offsetWidth)*scaleX;
		this.height = (height+offsetHeight)*scaleY;
		this.originX = originX-originOffsetX;
		this.originY = originY-originOffsetY;

		this.scaleX = scaleX;
		this.scaleY = scaleY;
		this.rotationAngle = rotationAngle;
		
		if (!verticesSet)
		{
			setRectangularVertexes(originX, originY, width,
					height);
			verticesSet = true;
		}
		
		this.setRotation(rotationAngle);
		this.setScale(scaleX, scaleY);
		this.setOrigin(originX, originY);
		this.setPosition(x, y);
		
	}

	public void debugDraw(Batch batch) {
		 if(debugRectangle == null)
		 {
			 debugRectangle = new com.andgdx.entity.shapes.Rectangle((int)x,(int)y,(int)width,(int)height);
		 }
		 debugRectangle.setColor(Color.GREEN);
		 debugRectangle.setOrigin(originX, originY);
		 debugRectangle.setScaleX(scaleX);
		 debugRectangle.setScaleY(scaleY);
		 debugRectangle.setRotation(rotationAngle);
		 debugRectangle.setX(debugX);
		 debugRectangle.setY(debugY);
		  
		 debugRectangle.draw(batch, 0.3f);
		
	}

}
