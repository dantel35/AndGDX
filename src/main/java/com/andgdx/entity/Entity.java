package com.andgdx.entity;

import com.andgdx.animation.IAnimationMachine;
import com.andgdx.camera.SimpleCamera;
import com.andgdx.entity.ashley.component.AndGDXEntityComponent;
import com.andgdx.entity.ashley.component.IAndGDXComponent;
import com.andgdx.entity.collision.ICollideArea;
import com.andgdx.entity.collision.RectangularMask;
import com.andgdx.scene.Scene;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

public class Entity extends Group implements IEntity {

	ICollideArea collideArea;
	int layer;
	private Object userData;
	private Object entityType;
	private Scene parentScene;
	private IEntity lookAtEntity;
//	private float lastX,lastY, lastWidth, lastHeight;
	int facingDirectionInDegrees;
	int movementDirectionInDegrees;
	IAnimationMachine animationMachine;
	com.badlogic.ashley.core.Entity ashleyEntity = new com.badlogic.ashley.core.Entity();
	AndGDXEntityComponent andGdxComp = new AndGDXEntityComponent();
	public Entity() {
		andGdxComp.andGDXEntity = this;
		ashleyEntity.add(andGdxComp);
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
//	
//	////////
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

	public int getFacingDirection() {
		return facingDirectionInDegrees;
	}

	public void setFacingDirection(int facingDirectionInDegrees) {
		this.facingDirectionInDegrees = facingDirectionInDegrees;
	}
	
	
	public int getMovementDirection() {
		return movementDirectionInDegrees;
	}

	public void setMovementDirection(int movementDirectionInDegrees) {
		this.movementDirectionInDegrees = movementDirectionInDegrees;
	}
	

	@Override
	public void setLookAt(IEntity entity) {
		this.lookAtEntity = entity;
	}

	@Override
	public IEntity getLookAt() {
		return lookAtEntity;
	}

	@Override
	public void addComponent(IAndGDXComponent component) {
		ashleyEntity.add(component);
	}

	@Override
	public void removeComponent(Class<? extends IAndGDXComponent> component) {
		ashleyEntity.remove(component);
		
	}

	@Override
	public void removeAllComponents() {
		ashleyEntity.removeAll();
		
	}

	@Override
	public boolean isScheduledForRemoval() {
		return ashleyEntity.isScheduledForRemoval();
	}

	@Override
	public com.badlogic.ashley.core.Entity getAshleyEntity() {
		return ashleyEntity;
	}

	@Override
	public int getLayer() {
		return this.layer;
	}

	@Override
	public void setLayer(int layer) {
		this.layer = layer;
		
	}


}
