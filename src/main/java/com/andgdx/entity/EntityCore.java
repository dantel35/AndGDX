package com.andgdx.entity;



import com.andgdx.entity.collision.ICollideArea;
import com.andgdx.entity.collision.RectangularMask;
import com.andgdx.scene.Scene;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;

public class EntityCore extends Actor implements IEntity {

	Texture texture;

	ICollideArea collideArea;
	Object userData;
	Object entityType;
	Scene parentScene;
	int facingDirectionInDegrees;
	private float lastX,lastY, lastWidth, lastHeight;

	
	public boolean flippedX;
	public boolean flippedY;
	
	
	public void setFlippedX(boolean flippedX)
	{
		this.flippedX = flippedX;
		this.setScale(-this.getScaleX(), this.getScaleY());
	}
	
	public void setFlippedY(boolean flippedY)
	{
		this.flippedY = flippedY;
		this.setScale(this.getScaleX(), -this.getScaleY());
	}
	
	
	
	public boolean isFlippedX() {
		return flippedX;
	}

	public boolean isFlippedY() {
		return flippedY;
	}

	public EntityCore() {
	}

	public ICollideArea getCollideArea() {
		return collideArea;
	}

	public boolean collidesWith(IEntity entity) {
		boolean collisionHappened = false;
		if (collideArea == null) {
			createDefaultMask();
		}
		if (entity.getCollideArea() == null) {
			entity.createDefaultMask();
		}
		updateCollideArea();
		entity.updateCollideArea();
		collisionHappened = collideArea.collides(this, entity);

		return collisionHappened;
	}

	public void createDefaultMask() {
		collideArea = new RectangularMask();
	}

	/**
	 * Sets the center for rotation. Also affects scaling center!
	 * Relative to lower left corner of entity.
	 * 
	 * @param x
	 * @param y
	 */
	public void setRotationCenter(float x, float y) {
		setOrigin(x, y);
	}

	/**
	 * Sets the center for scaling. Also affects rotation center!
	 * Relative to lower left corner of entity.
	 * 
	 * @param x
	 * @param y
	 */
	public void setScalingCenter(float x, float y) {
		setRotationCenter(x, y);

	}
	
	/**
	 * Sets the origin relative to lower left corner of the entity.
	 * 
	 * 
	 * @param x
	 * @param y
	 */
	public void setOrigin(float x, float y) {
		super.setOrigin(x, y);
	}


	public int getCenterX() {
		return (int) (getX() + (getWidth() * 0.5f));
	}

	public int getCenterY() {
		return (int) (getY() + (getHeight() * 0.5f));
	}

	public void dispose() {
		// TODO Auto-generated method stub

	}

	/**
	 * Get the userData you put into this EntityCore.
	 */
	public Object getUserData() {
		return userData;
	}

	/**
	 * Sets userData to this EntityCore. UserData can be anything you like.
	 * 
	 */
	public void setUserData(Object object) {
		userData = object;

	}
	/**
	 * Empty method.
	 */
	@Override
	public void draw(Batch batch, float parentAlpha) {
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.andgdx.entity.IEntity#setType(Object type)
	 */
	public void setType(Object type) {
		this.entityType = type;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.andgdx.entity.IEntity#getType()
	 */
	public Object getType() {
		return entityType;
	}

	public void updateCollideArea() {
		if (collideArea != null) {
			collideArea.updateCollideArea(getX(), getY(), getWidth(),
					getHeight(), getOriginX(), getOriginY(), getRotation(),
					getScaleX(), getScaleY());
		}
	}
	
	public void setParentScene(Scene scene)
	{
		this.parentScene=scene;
	}
	
	
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.andgdx.entity.IEntity#registerEntityModifier(Action action)
	 */
	public void registerEntityModifier(Action action) {
		this.addAction(action);
	}
	
	public void unregisterEntityModifier(Action action) {
		this.removeAction(action);
		
	}
	public void clearEntityModifiers() {
	this.clearActions();
		
	}
	////////
//	public void setX (float x) {
//		this.lastX = super.getX();
//		super.setX(x);
//	}
//
//	public void setY (float y) {
//		this.lastY = super.getY();
//		super.setX(y);
//	}
//	
//	/** Sets the position of the actor's bottom left corner. */
//	public void setPosition (float x, float y) {
//		this.setX(x);
//		this.setY(y);
//		super.setPosition(x, y);
//	}
//
//	/** Sets the position using the specified {@link Align alignment}. Note this may set the position to non-integer
//	 * coordinates. */
//	public void setPosition (float x, float y, int alignment) {
//		this.setX(x);
//		this.setY(y);
//		super.setPosition(x, y, alignment);
//	}
//
//	/** Add x and y to current position */
//	public void moveBy (float x, float y) {
//		this.lastX = super.getX();
//		this.lastY = super.getY();
//		super.moveBy(x, y);
//	}
//	
//	/** Set bounds the x, y, width, and height. */
//	public void setBounds (float x, float y, float width, float height) {
//		this.lastX = super.getX();
//		this.lastY = super.getY();
//		this.lastWidth = super.getWidth();
//		this.lastHeight = super.getHeight();
//		super.setBounds(x, y, width, height);
//	}
//	
//	
//	/** Sets the width and height. */
//	public void setSize (float width, float height) {
//		this.lastWidth = super.getWidth();
//		this.lastHeight = super.getHeight();
//		super.setSize(width, height);
//	}
//
//	/** Adds the specified size to the current size. */
//	public void sizeBy (float size) {
//		this.lastWidth = super.getWidth();
//		this.lastHeight = super.getHeight();
//		super.sizeBy(size);
//	}
//
//	/** Adds the specified size to the current size. */
//	public void sizeBy (float width, float height) {
//		this.lastWidth = super.getWidth();
//		this.lastHeight = super.getHeight();
//		super.sizeBy(width, height);
//	}
//	
//	public void setWidth (float width) {
//		this.lastWidth = super.getWidth();
//		super.setWidth(width);
//	}
//
//
//	public void setHeight (float height) {
//		this.lastHeight = super.getHeight();
//		super.setHeight(height);
//	}
//
//	
//	
//	///////////
//
//	
//
//	public float getLastX() {
//		// TODO Auto-generated method stub
//		return this.lastX;
//	}
//
//	public float getLastY() {
//		// TODO Auto-generated method stub
//		return this.lastY;
//	}
//
//	public float getLastWidth() {
//		// TODO Auto-generated method stub
//		return this.lastWidth;
//	}
//
//	public float getLastHeight() {
//		// TODO Auto-generated method stub
//		return this.lastHeight;
//	}

	public int getFacingDirection() {
		// TODO Auto-generated method stub
		return facingDirectionInDegrees;
	}

	public void setFacingDirection(int facingDirectionInDegrees) {
		// TODO Auto-generated method stub
		this.facingDirectionInDegrees = facingDirectionInDegrees;
	}

}
