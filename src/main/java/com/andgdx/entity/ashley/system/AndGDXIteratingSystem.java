package com.andgdx.entity.ashley.system;


import com.andgdx.command.UserInputFactory;
import com.andgdx.command.UserKeyInput;
import com.andgdx.entity.ashley.component.ComponentContainer;
import com.andgdx.entity.ashley.component.ComponentContainerFactory;
import com.andgdx.entity.ashley.component.IAndGDXComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

public class AndGDXIteratingSystem extends IteratingSystem {

	

	@SafeVarargs
	public AndGDXIteratingSystem (Class<? extends IAndGDXComponent>... componentTypes)
	{
		super(Family.all(componentTypes).get());
	}
	
	public AndGDXIteratingSystem(Family family) {
		super(family);
	}

	 
	protected void processEntity(ComponentContainer entity, float deltaTime) {
		
		processEntity((Entity) entity, deltaTime);
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		// TODO Auto-generated method stub
		ComponentContainer compCont = ComponentContainerFactory.pool(ComponentContainer.class);
		compCont.setAshleyEntity(entity);
		processEntity(compCont,  deltaTime);
		ComponentContainerFactory.free(ComponentContainer.class, compCont);
		
	}

}
