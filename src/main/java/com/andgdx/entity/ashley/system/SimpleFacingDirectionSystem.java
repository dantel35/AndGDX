package com.andgdx.entity.ashley.system;

import com.andgdx.entity.ashley.component.AndGDXEntityComponent;
import com.andgdx.entity.ashley.component.FacingDirectionComponent;
import com.andgdx.entity.ashley.component.VelocityComponent;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

public class SimpleFacingDirectionSystem extends IteratingSystem {

	private ComponentMapper<FacingDirectionComponent> facingDirectionMapper = ComponentMapper.getFor(FacingDirectionComponent.class);
	private ComponentMapper<AndGDXEntityComponent> entityMapper = ComponentMapper.getFor(AndGDXEntityComponent.class);

	public SimpleFacingDirectionSystem() {
		super(Family.all(FacingDirectionComponent.class,AndGDXEntityComponent.class).get());
	}

	public void processEntity(Entity entity, float deltaTime) {
		FacingDirectionComponent velocity = facingDirectionMapper.get(entity);
		AndGDXEntityComponent andGDXEntity = entityMapper.get(entity);
			
//		position.x += velocity.x * deltaTime;
//		position.y += velocity.y * deltaTime;
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