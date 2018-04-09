package com.andgdx.entity;

import com.andgdx.camera.SimpleCamera;
import com.andgdx.entity.collision.ICollideArea;
import com.andgdx.entity.collision.RectangularMask;
import com.andgdx.scene.Scene;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

public class Entity extends Group implements IEntity {

	ICollideArea collideArea;
	private Object userData;
	private Object entityType;
	private Scene parentScene;

	public Entity() {

	}

	public void setCollideArea(ICollideArea collideArea) {
		this.collideArea = collideArea;
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

	public void detachSelf() {
		if(parentScene != null)
		{
			parentScene.detachChild(this);			
		}
		else
		{
			this.remove();
		}
	}

	public void attachChild(IEntity entity) {
		addActor((Actor) entity);
	}

	public void detachChild(IEntity entity) {
		((Actor) entity).remove();
	}

	/**
	 * Sets the center for rotation relative to lower left corner of the entity.
	 * Also affects scaling center! The same as setOrigin.
	 * Convenience method.
	 * 
	 * @param x
	 * @param y
	 */
	public void setRotationCenter(float x, float y) {
		super.setOrigin(x, y);
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
	

	/**
	 * Sets the center for scaling relative to lower left corner of the entity.
	 * Also affects rotation center!
	 * 
	 * @param x
	 * @param y
	 */
	public void setScalingCenter(float x, float y) {
		setRotationCenter(x, y);

	}

	public int getCenterX() {
		return (int) (getX() + (getWidth() * 0.5f));
	}

	public int getCenterY() {
		return (int) (getY() + (getHeight() * 0.5f));
	}

	public void onDraw(Batch batch, SimpleCamera pCamera) {

	}

	public ICollideArea getCollideArea() {
		return collideArea;
	}

	public void dispose() {

	}

	/**
	 * Get the userData you put into this Entity.
	 */
	public Object getUserData() {
		return userData;
	}

	/**
	 * Sets userData to this Entity. UserData can be anything you like.
	 * 
	 */
	public void setUserData(Object object) {
		userData = object;

	}

	public void setType(Object type) {
		this.entityType = type;

	}

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
