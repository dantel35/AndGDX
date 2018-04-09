package com.andgdx.entity.collision;

import com.andgdx.entity.IEntity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

/**
 * Very efficient mask (collideArea)  -  in form of a rectangle.
 * You can use it together with the CircularMask for maximum efficiency.
 * 
 * Be warned that the efficiency is lost if you collide it with a rectangle. In this case
 * this mask will use its polygon representation to check for collision.
 * Only use RectangularPolygonMasks if you really need to.
 * ATTENTION: THIS MASK WILL NOT WORK WITH ROTATION NOR WITH SKEWING! ONLY SCALE WILL WORK PROPERLY.
 * @author Daniel Knittel
 *
 */
public class RectangularMask extends Rectangle implements ICollideArea {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7413022278713018896L;
	private int originOffsetX,  originOffsetY,  offsetWidth,  offsetHeight;
	private float originX, originY, scaleX, scaleY;
	
	private float debugX, debugY;
	private com.andgdx.entity.shapes.Rectangle debugRectangle;
	private float rotationAngle;

	
	/**
	 * The following values are the original values delivered to
	 *  updateCollideArea and are meant for updating the polygon representation
	 *  of this RectangluarMask. The polygon representation is needed for cases
	 *  where one wants to check collision of a rectangle and a polygon.
	 */
	private RectangularPolygonMask polygonRepresentation;
	private float polyWidth;
	private float polyHeight;
	private float polyOriginX;
	private float polyOriginY;
	private float polyX, polyY;
	
	/**
	 * Collision mask in form of a rectangle. This will create the mask according to 
	 * the entities x,y, width and height.
	 * ATTENTION: THIS MASK WILL NOT WORK WITH ROTATION NOR WITH SKEWING! ONLY SCALE WILL WORK PROPERLY.
	 */
	public RectangularMask()
	{
		this(0,0,0,0);
	}
	
	/**
	 * Collision mask in form of a rectangle. This will create and update the mask according to 
	 * the following rules:
	 *  maskX = x+origionOffsetX
	 *  maskY = y+origionOffsetY
	 *  maskWidth = width + offsetWidth
	 *  maskHeight = height + offsetHeight
	 *  
	 *  This way you can set the collision mask smaller or bigger than the actual entity, covering
	 *  some special part of it.
	 *  
	 *  ATTENTION: THIS MASK WILL NOT WORK WITH ROTATION NOR WITH SKEWING! ONLY SCALE WILL WORK PROPERLY.
	 *  
	 * @param originOffsetX
	 * @param originOffsetY
	 * @param offsetWidth
	 * @param offsetHeight
	 */
	public RectangularMask(int originOffsetX, int originOffsetY, int offsetWidth, int offsetHeight)
	{
		this.originOffsetX = originOffsetX;
		this.originOffsetY = originOffsetY;
		this.offsetHeight = offsetHeight;
		this.offsetWidth = offsetWidth;
	}
	 

	public boolean collides(IEntity entity1, IEntity entity2) {
		ICollideArea first = entity1.getCollideArea();
		ICollideArea second = entity2.getCollideArea();
		boolean returnValue = false;
		if (first instanceof Rectangle && second instanceof Rectangle) {
			Rectangle firstRectangle = (Rectangle) first;
			Rectangle secondRectangle = (Rectangle) second;
 
			returnValue = collides(firstRectangle, secondRectangle); 
		}
		else if((first instanceof Rectangle && second instanceof Circle))
		{
			Rectangle rectangle = (Rectangle)first;
			 
			Circle circle = (Circle)second;
			returnValue = collides(rectangle, circle); 
		}
		else if((first instanceof Circle && second instanceof Rectangle))
		{
			Rectangle rectangle = (Rectangle)second;
			 
			Circle circle = (Circle)first;
			returnValue = collides(rectangle, circle); 
			System.out.println(" circle = " + circle);
		}
		else if(first instanceof Rectangle && second instanceof RectangularPolygonMask)
		{
			Rectangle rectangle = (Rectangle) first;
			RectangularPolygonMask polygon = (RectangularPolygonMask) second;
			
			returnValue = collides(rectangle,polygon);	
		
		}
		else if(first instanceof RectangularPolygonMask && second instanceof Rectangle)
		{
			RectangularPolygonMask polygon = (RectangularPolygonMask) first;
			Rectangle rectangle = (Rectangle) second;
			
			returnValue = collides(rectangle,polygon);

		}
		return returnValue;
	}
	
	private void updatePolygonRepresentation()
	{
		
		if(polygonRepresentation == null)
		{
			polygonRepresentation = new RectangularPolygonMask(originOffsetX, originOffsetY, offsetWidth, offsetHeight);
		}
		polygonRepresentation.updateCollideArea(polyX, polyY, polyWidth, polyHeight, polyOriginX, polyOriginY, rotationAngle, scaleX, scaleY);
	}

	 

	public boolean collides(Rectangle rectangle, Circle circle) {
		return Intersector.overlaps(circle, rectangle);
	}
	
	public boolean collides(Rectangle rectangle, RectangularPolygonMask polygon) {
		updatePolygonRepresentation();
		return Intersector.overlapConvexPolygons(polygon,polygonRepresentation);

	}


	public boolean collides(Rectangle rectangle, Rectangle rectangle2) {
		return Intersector.overlaps(rectangle, rectangle2);
	}

	public void updateCollideArea(float x, float y, float width, float height,
			float originX, float originY, float rotationAngle, float scaleX,
			float scaleY) {
		this.x =  (originOffsetX)*scaleX+x;
		this.y = (originOffsetY)*scaleY+y;
		
		this.polyX = x;
		this.polyY = y;
		this.polyWidth = width;
		this.polyHeight = height;
		this.polyOriginX = originX;
		this.polyOriginY = originY;
		
		this.debugX = (x+originOffsetX);
		this.debugY = (y+originOffsetY);
		this.width = (width+offsetWidth)*scaleX;
		this.height = (height+offsetHeight)*scaleY;
		this.originX = originX-originOffsetX;
		this.originY = originY-originOffsetY;

		this.scaleX = scaleX;
		this.scaleY = scaleY;
		this.rotationAngle = rotationAngle;
		
		
	}
	
	
	public void debugDraw(Batch batch)
	{
		 if(debugRectangle == null)
		 {
			 debugRectangle = new com.andgdx.entity.shapes.Rectangle((int)x,(int)y,(int)width,(int)height);
		 }
		 debugRectangle.setColor(Color.GREEN);
		 debugRectangle.setOrigin(originX, originY);
		 debugRectangle.setScaleX(scaleX);
		 debugRectangle.setScaleY(scaleY);
//		 debugRectangle.setRotation(rotationAngle);
		 debugRectangle.setX(debugX);
		 debugRectangle.setY(debugY);
		  
		 debugRectangle.draw(batch, 0.3f);
		 
	}

	 

}
