package com.andgdx.entity;

import com.andgdx.entity.ashley.component.IAndGDXComponent;
import com.andgdx.entity.collision.ICollideArea;
import com.badlogic.gdx.scenes.scene2d.Action;


public interface IEntity {
	
	public float getX();
	public float getY();
	
	public int getLayer();
	public void setLayer(int layer);
	
 
	
	public float getWidth();
	public float getHeight();
	
	public String getName();
	


	
	public int getFacingDirection();
	public void setFacingDirection(int facingDirectionInDegrees);
	
	public int getMovementDirection();
	public void setMovementDirection(int movementDirectionInDegrees);
	
	//ashley
	public void addComponent(IAndGDXComponent component);
	public void removeComponent(Class<? extends IAndGDXComponent>  component);
	public void removeAllComponents();
	public boolean isScheduledForRemoval ();
	public com.badlogic.ashley.core.Entity getAshleyEntity();
	
	public void setLookAt(IEntity entity);
	public IEntity getLookAt();
	
	
	public void setX(float x);
	public void setY(float y);
	
	public void setWidth(float width);
	public void setHeight(float height);
	
	/**
	 * Returns getX()+(getWidth()*0.5f)
	 * @return
	 */
	public int getCenterX();
	
	/**
	 * Returns getY()+(getHeight()*0.5f)
	 * @return
	 */
	public int getCenterY();
	
	public ICollideArea getCollideArea();
	
	public void updateCollideArea();
	/**
	 * Creates a RectangularMask for collision detection.
	 */
	public void createDefaultMask();
	
	/**
	 * Get the userData you put into this Entity.
	 */
	public Object getUserData();
	
	/**
	 * Sets userData to this Entity. UserData can be anything you like.
	 * 
	 */
	public void setUserData(Object object);
	
	
	/**
	 * Sets a type for this Entity. The type can be anything you like.
	 * It can be used to identify a specific Entity.
	 */
	public void setType(Object type);

	/**
	 * Gets the type you previously set for this Entity (or null if not set).  
	 * The type can be used to identify a specific Entity.
	 */
	public Object getType();
	
	/**
	 * Registers an entity modifier (an action) for this entity.
	 * Same as addAction(...). Convenience method for AndEngine users.
	 * @param action
	 */
	public void registerEntityModifier(Action action);
	
	/**
	 * Unregisters an entity modifier (an action) for this entity.
	 * Same as removeAction(...). Convenience method for AndEngine users.
	 * @param action
	 */
	public void unregisterEntityModifier(Action action);
	
	/**
	 * Clears all entity modifiers (actions) for this entity.
	 * Same as clearActions(). Convenience method for AndEngine users.
	 * @param action
	 */
	public void clearEntityModifiers();
	
	 

}
