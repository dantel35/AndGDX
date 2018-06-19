package com.andgdx.entity.ashley.system;

import com.andgdx.animation.Animatable;
import com.andgdx.animation.IAnimationMachine;
import com.andgdx.command.CommandFactory;
import com.andgdx.command.GoToCommand;
import com.andgdx.command.JumpToCommand;
import com.andgdx.command.UserInputFactory;
import com.andgdx.command.UserKeyInput;
import com.andgdx.entity.AndGDXEntityUtil;
import com.andgdx.entity.IEntity;
import com.andgdx.entity.ashley.component.AndGDXEntityComponent;
import com.andgdx.entity.ashley.component.CanDashComponent;
import com.andgdx.entity.ashley.component.CanJumpOnComponent;
import com.andgdx.entity.ashley.component.ComponentContainer;
import com.andgdx.entity.ashley.component.VelocityComponent;
import com.andgdx.entity.modifier.listener.EntityModifierAdapter;
import com.andgdx.entity.modifier.listener.IEntityModifierListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class JumpOnSystem extends AndGDXIteratingSystem implements IEntitySystem{

	
	private CompMapper<VelocityComponent> velocityMapper = CompMapper.getFor(VelocityComponent.class);
	private CompMapper<AndGDXEntityComponent> entityMapper = CompMapper.getFor(AndGDXEntityComponent.class);

	private boolean wasMovingLastTick = false;
	

	
	public JumpOnSystem() {
		super(VelocityComponent.class,AndGDXEntityComponent.class, CanJumpOnComponent.class);
		
	}

	public void processEntity(ComponentContainer entity, float deltaTime) {
		
		AndGDXEntityComponent andGDXComponent = null;
		JumpToCommand command;
		
		
		boolean somethingPressed = false;
		
		try
		{
		 if (Gdx.input.isKeyPressed(CanJumpOnComponent.JUMP_ON_KEY)) {
			 
			 VelocityComponent velocity = velocityMapper.get(entity);
				  andGDXComponent = entityMapper.get(entity);
			 
			 wasMovingLastTick = true;
			 UserKeyInput input = UserInputFactory.pool(UserKeyInput.class);
				  command = CommandFactory.pool(JumpToCommand.class);
				input.setID(command.getID());
				
				IEntity lookAtEntity = andGDXComponent.andGDXEntity.getLookAt();
				if (lookAtEntity != null)
				{
					command.x = lookAtEntity.getCenterX();
					command.y = lookAtEntity.getCenterY();
					command.listener = this.listener;
					command.duration = 5/velocity.x;
					command.height = CanJumpOnComponent.JUMP_HEIGHT;
					command.execute(andGDXComponent.andGDXEntity);
				}

				input.setKey(CanDashComponent.DASH_KEY);
			 input.setKeyPressed();
			 somethingPressed= true;
//			 client.broadcast(input);
			 UserInputFactory.free(UserKeyInput.class, input);
			 
	        
		 }
	        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
	        	UserKeyInput input = UserInputFactory.pool(UserKeyInput.class);
	        	input.setKey(UserKeyInput.ESCAPE);
				 input.setKeyPressed();
				 somethingPressed= true;
//				 client.broadcast(input);
				 

	        }
	        
	        if (somethingPressed == false && wasMovingLastTick == true)
	        {
				 wasMovingLastTick = false;
				 if (andGDXComponent == null)
				 {
					 andGDXComponent = entityMapper.get(entity);
				 }

	        }
	        
	        
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private IEntityModifierListener listener = new EntityModifierAdapter()
	{
		IAnimationMachine machine;

		@Override
		public void onFinished(IEntity entity) {
			if (entity instanceof Animatable)
			{
				machine = ((Animatable) entity).getAnimationMachine();
				if(machine != null)
				{
					machine.stop();
					
				}
			}
			
		}

		@Override
		public void onStarted(IEntity entity) {
			if (entity instanceof Animatable)
			{
				machine = ((Animatable) entity).getAnimationMachine();
				if(machine != null)
				{
					machine.play("dash");					
				}
				

			}
			
		}
		};


}