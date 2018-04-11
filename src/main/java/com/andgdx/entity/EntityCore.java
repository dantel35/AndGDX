package com.andgdx.entity;

import com.andgdx.entity.collision.ICollideArea;
import com.andgdx.entity.collision.RectangularMask;
import com.andgdx.scene.Scene;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class EntityCore extends Actor implements IEntity {

	Texture texture;

	ICollideArea collideArea;
	Object userData;
	Object entityType;
	Scene parentScene;

	
	boolean flippedX;
	boolean flippedY;
	
	
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

}
