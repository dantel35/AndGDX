package com.andgdx.entity.ashley.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.utils.ImmutableArray;
/**
 * This is just a convenience delegator class! Wrapping ashley entity.
 * @author N
 *
 */
public class ComponentContainer extends com.badlogic.ashley.core.Entity {
	
	com.badlogic.ashley.core.Entity entity;
	long id;
	public ComponentContainer(com.badlogic.ashley.core.Entity entity)
	{
		this.entity = entity;
	}
	
	public ComponentContainer()
	{}

	
	
	public long getID()
	{
		return this.id;
	}
	public void setID(long id)
	{
		this.id = id;
	}
	
	public void setAshleyEntity(com.badlogic.ashley.core.Entity entity)
	{
		this.entity = entity;
	}
	
	public com.badlogic.ashley.core.Entity getAshleyEntity()
	{
		return this.entity;
	}
	
	
	/**
	 * Adds a {@link Component} to this Entity. If a {@link Component} of the same type already exists, it'll be replaced.
	 * @return The Entity for easy chaining
	 */
	public Entity add (Component component) {
		
		return entity.add(component);
	}
	
	
	/**
	 * Retrieve a component from this {@link Entity} by class. <em>Note:</em> the preferred way of retrieving {@link Component}s is
	 * using {@link ComponentMapper}s. This method is provided for convenience; using a ComponentMapper provides O(1) access to
	 * components while this method provides only O(logn).
	 * @param componentClass the class of the component to be retrieved.
	 * @return the instance of the specified {@link Component} attached to this {@link Entity}, or null if no such
	 *         {@link Component} exists.
	 */
	public <T extends Component> T getComponent (Class<T> componentClass) {
		return entity.getComponent(componentClass);
	}
	
	/** @return immutable collection with all the Entity {@link Component}s. */
	public ImmutableArray<Component> getComponents () {
		return entity.getComponents();
	}
	
	/** @return true if the entity is scheduled to be removed */
	public boolean isScheduledForRemoval () {
		return entity.isScheduledForRemoval();
	}
	
	/**
	 * Removes the {@link Component} of the specified type. Since there is only ever one component of one type, we don't need an
	 * instance reference.
	 * @return The removed {@link Component}, or null if the Entity did no contain such a component.
	 */
	public Component remove (Class<? extends Component> componentClass) {

		return entity.remove(componentClass);
	}
	
	/** Removes all the {@link Component}'s from the Entity. */
	public void removeAll () {
		entity.removeAll();
	}	

}
