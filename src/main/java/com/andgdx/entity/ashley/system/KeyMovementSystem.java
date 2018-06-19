package com.andgdx.entity.ashley.system;

import com.andgdx.animation.Animatable;
import com.andgdx.animation.IAnimationMachine;
import com.andgdx.command.CommandFactory;
import com.andgdx.command.GoToCommand;
import com.andgdx.command.UserInputFactory;
import com.andgdx.command.UserKeyInput;
import com.andgdx.entity.AndGDXEntityUtil;
import com.andgdx.entity.IEntity;
import com.andgdx.entity.ashley.component.AndGDXEntityComponent;
import com.andgdx.entity.ashley.component.ComponentContainer;
import com.andgdx.entity.ashley.component.KeyMovementComponent;
import com.andgdx.entity.ashley.component.VelocityComponent;
import com.andgdx.entity.modifier.listener.EntityModifierAdapter;
import com.andgdx.entity.modifier.listener.IEntityModifierListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class KeyMovementSystem extends AndGDXIteratingSystem implements IEntitySystem{

	
	private CompMapper<VelocityComponent> velocityMapper = CompMapper.getFor(VelocityComponent.class);
	private CompMapper<AndGDXEntityComponent> entityMapper = CompMapper.getFor(AndGDXEntityComponent.class);
	private boolean wasMovingLastTick = false;
	

	
	
	public KeyMovementSystem() {
		super(VelocityComponent.class,AndGDXEntityComponent.class, KeyMovementComponent.class);
	}

	public void processEntity(ComponentContainer entity, float deltaTime) {
		VelocityComponent velocity = velocityMapper.get(entity);
		AndGDXEntityComponent andGDXComponent = entityMapper.get(entity);
		float startX = andGDXComponent.andGDXEntity.getX();
		float startY = andGDXComponent.andGDXEntity.getY();
		
		GoToCommand command;

		boolean somethingPressed = false;
		
		try
		{
		 if (Gdx.input.isKeyPressed(KeyMovementComponent.MOVE_WEST)) {
			 wasMovingLastTick = true;
			 UserKeyInput input = UserInputFactory.pool(UserKeyInput.class);
				  command = CommandFactory.pool(GoToCommand.class);
				input.setID(command.getID());
				
				float westAddX = andGDXComponent.andGDXEntity.getX() - 1*velocity.x;
				float westAddY = andGDXComponent.andGDXEntity.getY();
				AndGDXEntityUtil.updateFacingAndMovementDirection(andGDXComponent.andGDXEntity, startX, startY, westAddX, westAddY);
				listener.onStarted(andGDXComponent.andGDXEntity);

				andGDXComponent.andGDXEntity.setX(westAddX);
				andGDXComponent.andGDXEntity.setY(westAddY);

				input.setKey(UserKeyInput.MOVE_WEST);
			 input.setKeyPressed();
			 somethingPressed= true;
//			 client.broadcast(input);
			 UserInputFactory.free(UserKeyInput.class, input);
			 
	        
		 }
	        if (Gdx.input.isKeyPressed(KeyMovementComponent.MOVE_EAST)) {
				 wasMovingLastTick = true;

	        	UserKeyInput input = UserInputFactory.pool(UserKeyInput.class);
				  command = CommandFactory.pool(GoToCommand.class);
				input.setID(command.getID());
				
				float eastAddX = andGDXComponent.andGDXEntity.getX() + 1*velocity.x;
				float eastAddY = andGDXComponent.andGDXEntity.getY();
				AndGDXEntityUtil.updateFacingAndMovementDirection(andGDXComponent.andGDXEntity, startX, startY, eastAddX, eastAddY);
				listener.onStarted(andGDXComponent.andGDXEntity);

				andGDXComponent.andGDXEntity.setX(eastAddX);
				andGDXComponent.andGDXEntity.setY(eastAddY);
				
	        	input.setKey(UserKeyInput.MOVE_EAST);
				 input.setKeyPressed();
				 somethingPressed= true;
//				 client.broadcast(input);
				 UserInputFactory.free(UserKeyInput.class, input);
	        }
	        if (Gdx.input.isKeyPressed(KeyMovementComponent.MOVE_SOUTH)) {
				 wasMovingLastTick = true;

	        	UserKeyInput input = UserInputFactory.pool(UserKeyInput.class);
				  command = CommandFactory.pool(GoToCommand.class);
				input.setID(command.getID());

				float southAddX = andGDXComponent.andGDXEntity.getX();
				float southAddY = andGDXComponent.andGDXEntity.getY() - 1*velocity.y;
				
				AndGDXEntityUtil.updateFacingAndMovementDirection(andGDXComponent.andGDXEntity, startX, startY, southAddX, southAddY);
				listener.onStarted(andGDXComponent.andGDXEntity);

				andGDXComponent.andGDXEntity.setX(andGDXComponent.andGDXEntity.getX());
				andGDXComponent.andGDXEntity.setY(andGDXComponent.andGDXEntity.getY() - 1*velocity.y);
				input.setKey(UserKeyInput.MOVE_SOUTH);
				 input.setKeyPressed();
				 somethingPressed= true;
//				 client.broadcast(input);
				 UserInputFactory.free(UserKeyInput.class, input);
	        }
	        if (Gdx.input.isKeyPressed(KeyMovementComponent.MOVE_NORTH)) {
				 wasMovingLastTick = true;

	        	UserKeyInput input = UserInputFactory.pool(UserKeyInput.class);
				  command = CommandFactory.pool(GoToCommand.class);
				input.setID(command.getID());
				float northAddX = andGDXComponent.andGDXEntity.getX();
				float northAddY = andGDXComponent.andGDXEntity.getY() + 1*velocity.y;
				
				AndGDXEntityUtil.updateFacingAndMovementDirection(andGDXComponent.andGDXEntity, startX, startY, northAddX, northAddY);
				
				listener.onStarted(andGDXComponent.andGDXEntity);
				andGDXComponent.andGDXEntity.setX(andGDXComponent.andGDXEntity.getX());
				andGDXComponent.andGDXEntity.setY(andGDXComponent.andGDXEntity.getY() + 1*velocity.y);

				input.setKey(UserKeyInput.MOVE_NORTH);
				 input.setKeyPressed();
				 somethingPressed= true;
//				 client.broadcast(input);
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
//	        	input.setKeyReleased();
				 wasMovingLastTick = false;

				listener.onFinished(andGDXComponent.andGDXEntity);

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
					machine.play("move");					
				}
				

			}
			
		}
		};


}