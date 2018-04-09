package com.andgdx.entity.collision;

import com.andgdx.entity.IEntity;
import com.andgdx.entity.collision.util.CirclePolygonCollision;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * 
 * @author Daniel Knittel
 * @author Janina Knittel
 *
 */
public class CircularMask extends Circle implements ICollideArea {

	/**
	 * Very efficient Mask (collideArea) in form of a circle, but only if no rotation is used!
	 * Only use rotation on the entities this Mask is attached to if you really need to. 
	 */
	private static final long serialVersionUID = -6731925927691181841L;

	int radiusOffset;
	boolean autoRadius;
	float scaleX = 1;
	float scaleY = 1;
	private int originOffsetX, originOffsetY;
	private float debugOriginX, debugOriginY;
	private int fixedRadius;

	private float debugX, debugY;
	private com.andgdx.entity.shapes.Circle debugCircle;

	Vector2 vectorForRotation = new Vector2(0,0);
	
	private float rotationAngle;

	private float oldOrigin;

	private float angleToOrigin;

	private float oldOriginX;

	private float oldOriginY;

	/**
	 * Creates a circular mask which middle point will be the middle point of
	 * the sprite rectangle. The radius will be width/2. Notice that the mask
	 * will only scale along with the sprite on the x axis. If you scale on y it
	 * will not affect the radius of the circular mask.
	 */
	public CircularMask() {
		this(0, 0, 0, true);
	}

	public CircularMask(int radius) {
		this(radius, 0, 0, false);

	}

	public CircularMask(int originOffsetX, int originOffsetY) {
		this(0, originOffsetX, originOffsetY, true);
	}

	public CircularMask(int radius, int originOffsetX, int originOffsetY) {
		this(radius, originOffsetX, originOffsetY, false);
	}

	public CircularMask(int radius, int originOffsetX, int originOffsetY,
			boolean autoRadius) {
		this.originOffsetX = originOffsetX;
		this.originOffsetY = originOffsetY;
		this.autoRadius = autoRadius;
		this.radius = radius;
		this.fixedRadius = radius;
	}

	public void recalculateRadius(IEntity entity) {
		if (autoRadius) {
			radius = (int) (Math.min(entity.getWidth() * scaleX,
					entity.getHeight() * scaleY) / 2)
					+ radiusOffset;
		}

	}

	public boolean collides(IEntity entity1, IEntity entity2) {
		ICollideArea first = entity1.getCollideArea();
		ICollideArea second = entity2.getCollideArea();
		boolean returnValue = false;
		if (first instanceof Circle && second instanceof Circle) {
			Circle firstRectangle = (Circle) first;
			Circle secondRectangle = (Circle) second;

			returnValue = collides(firstRectangle, secondRectangle);
		} else if ((first instanceof Rectangle && second instanceof Circle)) {
			Rectangle rectangle = (Rectangle) first;

			Circle circle = (Circle) second;
			returnValue = collides(rectangle, circle);
		} else if ((first instanceof Circle && second instanceof Rectangle)) {
			Rectangle rectangle = (Rectangle) second;

			Circle circle = (Circle) first;
			returnValue = collides(rectangle, circle);
		} else if ((first instanceof Circle && second instanceof Polygon)) {
			Polygon rectangle = (Polygon) second;

			Circle circle = (Circle) first;
			returnValue = collides(rectangle, circle);
		} else if ((first instanceof Polygon && second instanceof Circle)) {
			Polygon rectangle = (Polygon) first;

			Circle circle = (Circle) second;
			returnValue = collides(rectangle, circle);
		}
		return returnValue;
	}

	public boolean collides(Rectangle rectangle, Circle circle) {
		return Intersector.overlaps(circle, rectangle);
	}

	public boolean collides(Polygon rectangle, Circle circle) {
		return CirclePolygonCollision.overlaps(rectangle, circle);
	}

	public boolean collides(Circle circle, Circle circle2) {
		return Intersector.overlaps(circle, circle2);
	}

	public void updateCollideArea(float x, float y, float width, float height,
			float originX, float originY, float rotationAngle, float scaleX,
			float scaleY) {
		if (autoRadius) {
			radius = (int) (width / 2) * scaleX;
		} else {
			radius = fixedRadius * scaleX;
		}

		this.x = (radius + originOffsetX * scaleX) + x;
		this.y = (radius + originOffsetY * scaleY) + y;

		//debugX etc. are coordinates for the debug Shape that is drawn in debugDraw. Do not alter them.
		this.debugX = (x + originOffsetX);
		this.debugY = (y + originOffsetY);
		this.debugOriginX = originX - originOffsetX;
		this.debugOriginY = originY - originOffsetY;

		float scaledOffsettedOriginX = originX - ( originOffsetX * scaleX);
		float scaledOffsettedOriginY = originY - (originOffsetY * scaleY);

		
		this.scaleX = scaleX;
		this.scaleY = scaleY;

		this.rotationAngle = rotationAngle;

		if (this.rotationAngle != 0) {

			 vectorForRotation.set(this.x
					- (scaledOffsettedOriginX + (this.x - radius)), this.y
					- (scaledOffsettedOriginY + (this.y - radius)));
			 vectorForRotation.setAngle(this.rotationAngle + getAngle(originX, originY,vectorForRotation));

			 
			this.x = (this.x - radius) + (scaledOffsettedOriginX) + vectorForRotation.x;
			this.y = (this.y - radius) + (scaledOffsettedOriginY) + vectorForRotation.y;
		}
		this.oldOriginX = originX;
		this.oldOriginY = originY;

	}
	
	//recalculates the angle only if it is necessary.
	private float getAngle(float originX, float originY, Vector2 vector)
	{
		if (this.oldOriginX != originX || this.oldOriginY != originY || this.angleToOrigin==0)
		{
			this.angleToOrigin = vector.angle();
		}
			
		
		return this.angleToOrigin;

	}
	

	public void debugDraw(Batch batch) {
		if (debugCircle == null) {
			debugCircle = new com.andgdx.entity.shapes.Circle((int) x, (int) y,
					(int) radius);
		}
		debugCircle.setColor(Color.GREEN);
		debugCircle.setOrigin(debugOriginX, debugOriginY);
		debugCircle.setScaleX(scaleX);
		debugCircle.setScaleY(scaleY);
		debugCircle.setX(debugX);
		debugCircle.setY(debugY);
		debugCircle.setRotation(rotationAngle);

		debugCircle.draw(batch, 0.3f);

	}
	
	
	
	
	
	
//	korrekt mit scale
//	public void updateCollideArea(float x, float y, float width, float height,
//			float originX, float originY, float rotationAngle, float scaleX,
//			float scaleY) {
//		if (autoRadius) {
//			radius = (int) (width / 2) * scaleX;
//		} else {
//			radius = fixedRadius * scaleX;
//		}
//
//		this.x = (radius + originOffsetX * scaleX) + x;
//		this.y = (radius + originOffsetY * scaleY) + y;
//
//		this.debugX = (x + originOffsetX);
//		this.debugY = (y + originOffsetY);
//
//		this.originX = originX - originOffsetX;
//		this.originY = originY - originOffsetY;
//
//		float scaledOffsettedOriginX = originX - ( originOffsetX * scaleX);
//		float scaledOffsettedOriginY = originY - (originOffsetY * scaleY);
//
//		
//		this.scaleX = scaleX;
//		this.scaleY = scaleY;
//
//		this.rotationAngle = rotationAngle;
//
//		System.out.println("x: " + x + " y: " + y + "; this.x " + this.x
//				+ " this.y: " + this.y + " radius: " + radius + " originX: "
//				+ originX + " originY: " + originY + " this.originX:"
//				+ this.originX + " this.originY: " + this.originY);
//
//		// FIXME NICHT STAENDIG NEUE OBJEKTE ERZEUGEN
//		if (this.rotationAngle != 0) {
//			Vector2 vector = new Vector2(this.x
//					- (scaledOffsettedOriginX + (this.x - radius)), this.y
//					- (scaledOffsettedOriginY + (this.y - radius)));
//
//			Vector2 unitVector = MathUtils.toUnitVector(vector);
//
//			System.out.println("Vektor vorher x: " + vector.x + " y: "
//					+ vector.y);
//			unitVector.setAngle(this.rotationAngle + 45f);
//
//			vector = new Vector2(unitVector.x * vector.len(), unitVector.y
//					* vector.len());
//
//			System.out.println("Vektor nachher x: " + vector.x + " y: "
//					+ vector.y + " scaleX: " + scaleX + " scaleY: " + scaleY
//					+ " rotation: " + this.rotationAngle + 45f);
//
//			this.x = (this.x - radius) + (scaledOffsettedOriginX) + vector.x;
//			this.y = (this.y - radius) + (scaledOffsettedOriginY) + vector.y;
//
//			System.out.println("new x y--- x:" + this.x + "  y: " + this.y);
//		}
//
//	}
	
	
	

	// public void updateCollideArea(float x, float y, float width, float
	// height,
	// float originX, float originY, float rotationAngle, float scaleX,
	// float scaleY) {
	// if(autoRadius)
	// {
	// radius = (int) (width/2)*scaleX;
	// }
	// else
	// {
	// radius = fixedRadius*scaleX;
	// }
	//
	//
	// this.x = (radius + originOffsetX*scaleX)+x;
	// this.y = (radius + originOffsetY*scaleY)+y;
	//
	// this.debugX = (x+originOffsetX);
	// this.debugY = (y+originOffsetY);
	//
	// this.originX = originX-originOffsetX;
	// this.originY = originY-originOffsetY;
	//
	// this.scaleX = scaleX;
	// this.scaleY = scaleY;
	//
	// this.rotationAngle = rotationAngle;
	//
	// System.out.println("x: " + x + " y: " +y + "; this.x "
	// + this.x + " this.y: " + this.y + " radius: " + radius
	// + " originX: " + originX + " originY: " + originY
	// + " this.originX:" + this.originX + " this.originY: " + this.originY);
	//
	// //FIXME NICHT STAENDIG NEUE OBJEKTE ERZEUGEN
	// if(this.rotationAngle != 0)
	// {
	// Vector2 vector = new Vector2(this.x-(this.originX+(this.x-radius)),
	// this.y-(this.originY+(this.y-radius)));
	//
	// Vector2 unitVector = MathUtils.toUnitVector(vector);
	//
	// System.out.println("Vektor vorher x: " + vector.x + " y: " + vector.y);
	// unitVector.setAngle(this.rotationAngle+45f);
	//
	// vector = new Vector2(unitVector.x * vector.len(), unitVector.y *
	// vector.len());
	//
	// System.out.println("Vektor nachher x: " + vector.x + " y: " + vector.y
	// + " scaleX: " + scaleX + " scaleY: " + scaleY
	// + " rotation: " + this.rotationAngle+45f);
	//
	// this.x = (this.x-radius)+(this.originX)+vector.x;
	// this.y= (this.y-radius)+(this.originY)+vector.y;
	//
	// System.out.println("new x y--- x:" + this.x + "  y: " + this.y);
	// }
	//
	//
	// }
}
