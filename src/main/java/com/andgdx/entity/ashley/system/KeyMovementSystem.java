package com.andgdx.entity.ashley.system;

import com.andgdx.command.CommandFactory;
import com.andgdx.command.GoToCommand;
import com.andgdx.command.UserInputFactory;
import com.andgdx.command.UserKeyInput;
import com.andgdx.entity.ashley.component.AndGDXEntityComponent;
import com.andgdx.entity.ashley.component.FacingDirectionComponent;
import com.andgdx.entity.ashley.component.VelocityComponent;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class KeyMovementSystem extends IteratingSystem {

	private ComponentMapper<VelocityComponent> velocityMapper = ComponentMapper.getFor(VelocityComponent.class);
	private ComponentMapper<AndGDXEntityComponent> entityMapper = ComponentMapper.getFor(AndGDXEntityComponent.class);
	private float oldX, oldY;
	
	
	public KeyMovementSystem() {
		super(Family.all(FacingDirectionComponent.class,AndGDXEntityComponent.class).get());
	}

	public void processEntity(Entity entity, float deltaTime) {
		VelocityComponent velocity = velocityMapper.get(entity);
		AndGDXEntityComponent andGDXEntity = entityMapper.get(entity);
			
		andGDXEntity.andGDXEntity.setX(velocity.x * deltaTime);
		andGDXEntity.andGDXEntity.setY(velocity.y * deltaTime);

		boolean somethingPressed = false;
		
		try
		{
		 if (Gdx.input.isKeyPressed(UserKeyInput.MOVE_WEST)) {
			 UserKeyInput input = UserInputFactory.pool(UserKeyInput.class);
				GoToCommand command = CommandFactory.pool(GoToCommand.class);
				input.setID(command.getID());
//				playerPosition.setLocation(playerPosition.x -2,playerPosition.y);
				command.x = andGDXEntity.andGDXEntity.getCenterX() - 1*velocity.x;
				command.y =  andGDXEntity.andGDXEntity.getCenterY();
//				command.duration = 1;

				input.setKey(UserKeyInput.MOVE_WEST);
			 input.setKeyPressed();
			 somethingPressed= true;
//			 commandCache.addCommand(command);
			 command.execute(andGDXEntity.andGDXEntity);
//			 client.broadcast(input);
			 UserInputFactory.free(UserKeyInput.class, input);
//			 command.setFree();
			 
	        
		 }
	        if (Gdx.input.isKeyPressed(UserKeyInput.MOVE_EAST)) {
	        	UserKeyInput input = UserInputFactory.pool(UserKeyInput.class);
				GoToCommand command = CommandFactory.pool(GoToCommand.class);
				input.setID(command.getID());
				
//				playerPosition.setLocation(playerPosition.x +2,playerPosition.y);
				command.x = andGDXEntity.andGDXEntity.getCenterX() + 1*velocity.x;
				command.y =  andGDXEntity.andGDXEntity.getCenterY();
//				command.duration = 1;

				
	        	input.setKey(UserKeyInput.MOVE_EAST);
				 input.setKeyPressed();
				 somethingPressed= true;
//				 commandCache.addCommand(command);
				 command.execute(andGDXEntity.andGDXEntity);
//				 client.broadcast(input);
				 UserInputFactory.free(UserKeyInput.class, input);
//				 CommandFactory.free(GoToCommand.class, command);
//				 command.setFree();
	        }
	        if (Gdx.input.isKeyPressed(UserKeyInput.MOVE_SOUTH)) {
	        	UserKeyInput input = UserInputFactory.pool(UserKeyInput.class);
				GoToCommand command = CommandFactory.pool(GoToCommand.class);
				input.setID(command.getID());
//				playerPosition.setLocation(playerPosition.x ,playerPosition.y - 2);
				command.x = andGDXEntity.andGDXEntity.getCenterX();
				command.y =  andGDXEntity.andGDXEntity.getCenterY() - 1*velocity.y;
//				command.duration = 1;

				input.setKey(UserKeyInput.MOVE_SOUTH);
				 input.setKeyPressed();
				 somethingPressed= true;
//				 commandCache.addCommand(command);
				 command.execute(andGDXEntity.andGDXEntity);
//				 client.broadcast(input);
				 UserInputFactory.free(UserKeyInput.class, input);
//				 CommandFactory.free(GoToCommand.class, command);
//				 command.setFree();
	        }
	        if (Gdx.input.isKeyPressed(UserKeyInput.MOVE_NORTH)) {
	        	UserKeyInput input = UserInputFactory.pool(UserKeyInput.class);
				GoToCommand command = CommandFactory.pool(GoToCommand.class);
				input.setID(command.getID());
//				playerPosition.setLocation(playerPosition.x,playerPosition.y + 2);
				command.x = andGDXEntity.andGDXEntity.getCenterX();
				command.y =  andGDXEntity.andGDXEntity.getCenterY() + 1*velocity.y;
//				command.duration = 1;

				input.setKey(UserKeyInput.MOVE_NORTH);
				 input.setKeyPressed();
				 somethingPressed= true;
//				 commandCache.addCommand(command);
				 command.execute(andGDXEntity.andGDXEntity);
//				 client.broadcast(input);
				 UserInputFactory.free(UserKeyInput.class, input);
//				 CommandFactory.free(GoToCommand.class, command);

//				 command.setFree();
	        }
	        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
	        	UserKeyInput input = UserInputFactory.pool(UserKeyInput.class);
	        	input.setKey(UserKeyInput.ESCAPE);
				 input.setKeyPressed();
				 somethingPressed= true;
//				 client.broadcast(input);
				 

	        }
	        
	        if (somethingPressed == false)
	        {
//	        	input.setKeyReleased();
	        }
	        
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	private float calculateRotationAngle(float fromX, float fromY, float toX, float toY) {
//		float playerX = playerSprite.getX();
//		float playerY = playerSprite.getY();
//
//		float enemyX = enemy.getX();
//		float enemyY = enemy.getY();

		float yKathete = fromY - toY;
		float xKathete = fromX - toX;
		//double tangens = xKathete / yKathete;
		double tangens = 0;
		float alpha=0;
		

		System.out.println("Rotation: xKath: " + xKathete + " yKath: " +yKathete );
		if(yKathete > 0) //Enemy ist ueber Held
		{
			if(xKathete == 0)
			{
				alpha = 0; //0Grad
			}
			else if(xKathete > 0) //Gegner links vom Held
			{
				tangens = Math.abs(yKathete / xKathete);
				alpha = (float) Math.toDegrees(Math.atan(tangens))+270;
			}
			else if(xKathete < 0) //Gegner rechts vom Held
			{
				tangens = Math.abs(xKathete / yKathete);
				alpha = (float) Math.toDegrees(Math.atan(tangens));
			}
		}
		else if(yKathete == 0) // gegner auf derselben hoehe
		{
			if(xKathete  > 0) //gegeer ist links vom held
			{
				alpha = 270;
			}
			else if(xKathete < 0) //gegner ist rechts vom held
			{
				alpha = 90;
			}
		}
		else if(yKathete <0) //gegner ist unter Held
		{
			if(xKathete > 0) // gegner ist links vom Held
			{
				tangens = Math.abs(xKathete / yKathete);
				alpha = (float) Math.toDegrees(Math.atan(tangens))+180;
			}
			else if(xKathete == 0) // gegner ist genau unter held
			{
				alpha = 180;
				
			}
			else if(xKathete < 0) // gegner ist rechts vom held
			{
				//alpha = alpha +180;
				tangens = Math.abs(yKathete / xKathete);
				alpha = (float) Math.toDegrees(Math.atan(tangens))+90;
			}
		}
		//alpha = (float) Math.atan(tangens)*100;
	//alpha = Math.abs(alpha);

		return alpha;
	}

}