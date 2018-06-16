package com.andgdx.entity.ashley.system;


import com.andgdx.entity.ashley.component.CompFilter;
import com.andgdx.entity.ashley.component.ComponentContainer;
import com.andgdx.entity.ashley.component.ComponentContainerFactory;
import com.andgdx.entity.ashley.component.IAndGDXComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.systems.IteratingSystem;

public class AndGDXIteratingSystem extends IteratingSystem {

	

	@SafeVarargs
	public AndGDXIteratingSystem (Class<? extends IAndGDXComponent>... componentTypes)
	{
		super(CompFilter.all(componentTypes).get());
	}
	
	public AndGDXIteratingSystem(CompFilter filter) {
		super(CompFilter.get());
	}

	 
	protected void processEntity(ComponentContainer entity, float deltaTime) {
		
		processEntity((Entity) entity, deltaTime);
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		//there will only ever be as many wrappers as there are threads. low overhead.
		ComponentContainer compCont = ComponentContainerFactory.pool(ComponentContainer.class);
		compCont.setAshleyEntity(entity);
		processEntity(compCont,  deltaTime);
		ComponentContainerFactory.free(ComponentContainer.class, compCont);
		
	}

}
