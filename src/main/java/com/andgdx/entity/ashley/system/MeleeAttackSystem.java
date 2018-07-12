package com.andgdx.entity.ashley.system;

import com.andgdx.animation.Animatable;
import com.andgdx.animation.IAnimationMachine;
import com.andgdx.command.CommandFactory;
import com.andgdx.command.GoToCommand;
import com.andgdx.command.UserInputFactory;
import com.andgdx.command.UserKeyInput;
import com.andgdx.entity.IEntity;
import com.andgdx.entity.ashley.component.AndGDXEntityComponent;
import com.andgdx.entity.ashley.component.CanDoMeleeDamageComponent;
import com.andgdx.entity.ashley.component.ComponentContainer;
import com.andgdx.entity.ashley.component.KeyMovementComponent;
import com.andgdx.entity.ashley.component.VelocityComponent;
import com.andgdx.entity.modifier.listener.EntityModifierAdapter;
import com.andgdx.entity.modifier.listener.IEntityModifierListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class MeleeAttackSystem extends AndGDXIteratingSystem implements IEntitySystem{

	
	private CompMapper<AndGDXEntityComponent> entityMapper = CompMapper.getFor(AndGDXEntityComponent.class);
	private CompMapper<VelocityComponent> velocityMapper = CompMapper.getFor(VelocityComponent.class);

	private boolean wasMovingLastTick = false;
	
private boolean toggle;
	
	
	public MeleeAttackSystem() {
		super(CanDoMeleeDamageComponent.class,AndGDXEntityComponent.class);
	}

	public void processEntity(ComponentContainer entity, float deltaTime) {
		
		AndGDXEntityComponent andGDXComponent = null;
		GoToCommand command;
		
		
		boolean somethingPressed = false;
		
		try
		{
		 if (Gdx.input.isKeyJustPressed(CanDoMeleeDamageComponent.ATTACK_1_KEY)) {
			 
			 VelocityComponent velocity = velocityMapper.get(entity);
				  andGDXComponent = entityMapper.get(entity);
			 
			 wasMovingLastTick = true;
			 UserKeyInput input = UserInputFactory.pool(UserKeyInput.class);
				  command = CommandFactory.pool(GoToCommand.class);
				input.setID(command.getID());
				
				IEntity lookAtEntity = andGDXComponent.andGDXEntity.getLookAt();
				if (lookAtEntity != null)
				{
					command.x = lookAtEntity.getCenterX();
					command.y = lookAtEntity.getCenterY();
					command.listener = this.listener;
					command.duration = 5/velocity.x;
//					command.execute(andGDXComponent.andGDXEntity);
				}
				listener.onStarted(andGDXComponent.andGDXEntity);

				input.setKey(CanDoMeleeDamageComponent.ATTACK_1_KEY);
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
					if(machine.isThisPlaying("attack"))
					{
						
						alterState(machine);
						machine.chain("attack");	
						
					}
					else
					{
						machine.play("attack");
					}
				}
				

			}
			
		}
		};
		
		private void alterState(IAnimationMachine machine)
		{
			toggle = !toggle;
			if(toggle)
			{
				machine.addToState("attack2");				
			}
			else
			{
				machine.removeFromState("attack2");
			}
		}


}