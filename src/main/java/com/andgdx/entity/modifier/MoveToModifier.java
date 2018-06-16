package com.andgdx.entity.modifier;

import com.andgdx.entity.IEntity;
import com.andgdx.entity.modifier.listener.IEntityModifierListener;
import com.andgdx.entity.modify.IModifier;
import com.andgdx.util.AndGDXMathUtils;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
//import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.Align;
/**
 * Moves an actor from its current position to a specific position.
 * 
 */
public class MoveToModifier  extends TemporalAction implements IEntityModifier {
	private float startX, startY;
	private float endX, endY;
	private int alignment = Align.bottomLeft;
	protected IEntityModifierListener modifierListener;
	private boolean fromTo;
	private boolean firstTimeExec = true;
	private boolean notFinishedYet = true;

	protected void begin() {
		if(fromTo == false)
		{
			startX = actor.getX();
			startY = actor.getY();
			
		}
	}

	protected void update(float percent) {
		float x = MathUtils.round(startX + (endX - startX) * percent);
		float y = MathUtils.round( startY+ (endY - startY) * percent);
		actor.setPosition(x, y, alignment);
		checkListener(percent);
	}

	private void checkListener(float percent) {
		
		if (percent >= 1) {
			if(modifierListener != null)
			modifierListener.onFinished((IEntity) actor);
			
			notFinishedYet = false;
		} else if (firstTimeExec  ) {
			updateFacingDirection();
			if(modifierListener != null)
			{
				modifierListener.onStarted((IEntity) actor);				
			}
			
			firstTimeExec = false;
		}

	}
	
	private void updateFacingDirection()
	{
		IEntity entity;
		IEntity lookAtEntity;
		int direction;
		if (actor instanceof IEntity)
		{
			entity = (IEntity) actor;
			lookAtEntity = entity.getLookAt();
			if (lookAtEntity == null)
			{ 
				direction = (int) AndGDXMathUtils.calculateRotationAngle(startX, startY, endX, endY);
				entity.setFacingDirection(direction);				
				entity.setMovementDirection(direction);				

			}
			else
			{
				direction = (int) AndGDXMathUtils.calculateRotationAngle(startX, startY, lookAtEntity.getCenterX(), lookAtEntity.getCenterY());

				entity.setFacingDirection(direction);				
				direction = (int) AndGDXMathUtils.calculateRotationAngle(startX, startY, endX, endY);
				entity.setMovementDirection(direction);				
				
			}
			System.out.println("face dir " + entity.getFacingDirection());
		}
	 
	}
	
	
	
	
	
	
	public void setModifierListener(IEntityModifierListener listener)
	{
		this.modifierListener = listener;
	}

	public void reset() {
		super.reset();
		alignment = Align.bottomLeft;
		modifierListener = null;
		fromTo = false;
		firstTimeExec = true;
		notFinishedYet = true;
	}
	
	public void restart () {
		super.restart();
		
	}

	public void setPosition(float x, float y) {
		endX = x;
		endY = y;
	}
	
	public void setFromPosition(float fromX, float fromY)
	{
		fromTo = true;
		startX = fromX;
		startY = fromY;
	}

	public void setPosition(float x, float y, int alignment) {
		endX = x;
		endY = y;
		this.alignment = alignment;
	}

	public float getX() {
		return endX;
	}

	public void setX(float x) {
		endX = x;
	}

	public float getY() {
		return endY;
	}

	public void setY(float y) {
		endY = y;
	}

	public int getAlignment() {
		return alignment;
	}

	public void setAlignment(int alignment) {
		this.alignment = alignment;
	}

	
	
	 
}
