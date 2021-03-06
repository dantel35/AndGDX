/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.andgdx.entity.modifier;




import com.andgdx.entity.IEntity;
import com.andgdx.entity.modifier.listener.IEntityModifierListener;
import com.andgdx.util.AndGDXMathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
//import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.Align;


public class JumpToModifier extends AndGDXTemporalAction implements IEntityModifier{
	private float startX, startY;
	private float endX, endY;
	private float mJumpHeight;
	private float mJumpCount=1;
	private int alignment = Align.bottomLeft;
	private boolean firstTimeExec = true;
//	protected IEntityModifierListener modifierListener;

	protected void begin () {
		startX = actor.getX();
		startY = actor.getY();
	}

	protected void update (float percent) {
		onSetValues2(actor, percent, startX, startY);
		checkListener(percent);
	}
	
	
	private void updateFacingDirection()
	{
		IEntity entity;
		if (actor instanceof IEntity)
		{
			entity = (IEntity) actor;
			entity.setFacingDirection((int) AndGDXMathUtils.calculateRotationAngle(startX, startY, endX, endY));
		}
	 
	}
	
	
	private void checkListener(float percent) {
		if (percent >= 1  ) {
			if(modifierListener != null)
			modifierListener.onFinished((IEntity) actor);
		} else if (firstTimeExec  ) {
			updateFacingDirection();
			if(modifierListener != null)
			modifierListener.onStarted((IEntity) actor);
			
			firstTimeExec = false;
		}

	}
	
	protected void onSetValues(final Actor pEntity, final float pPercentageDone, final float pX, final float pY) {
		final float fraction = (pPercentageDone * this.mJumpCount) % 1.0f;
		final float deltaY = (this.mJumpHeight * 4 * fraction * (1 - fraction));// + (endY-startY) * (fraction);
		
		float x = startX + (endX - startX) * pPercentageDone;
		float y = startY +deltaY;
//		y= startY + (endY - startY) * deltaY;
		actor.setPosition(x,y , alignment);
		System.out.println("fraction:" + fraction + " inverse fraction: " + (1-fraction) + " delta: " + deltaY +" Percentage: " + pPercentageDone + " x: " + x + " y: " + y);
	}
	
	private void onSetValues2(final Actor pEntity, final float pPercentageDone, final float pX, final float pY) {
		final float fraction = (pPercentageDone * this.mJumpCount) % 1.0f;
		final float deltaY = (this.mJumpHeight * 4 * fraction * (1 - fraction));// + (endY-startY) * (1-fraction);
		
		float g = 9.81f;
		float speed = 50f;
		float nenner = (g * endX);
		double wurzelinhalt = Math.abs(Math.pow(speed, 4) - (g* ( (g * (endX * endX)) + (2 * endY * (speed * speed)))));
		double zaehler = speed*speed + Math.sqrt(wurzelinhalt);
		System.out.println("nenner : " + nenner + " zaehler " + zaehler + " wurzelinhalt " + wurzelinhalt);
		double degree =  Math.atan(zaehler/ nenner);
			System.out.println("Degree " + degree);
		float x = startX + (endX - startX) * pPercentageDone;
		float y = (float) (startY + Math.tan(degree) * (endX*pPercentageDone) - (9.81f/(2*speed * (Math.cos(degree) * Math.cos(degree)))) * (endX*pPercentageDone)*(endX*pPercentageDone));

		float p = 3;
		float q = -2;
		float b = endY;
//		float nY = (p + q - 2 * b) * (x*x*x) * pPercentageDone + (3 * b -2 * p -q) * (x*x) * pPercentageDone + p*(x * pPercentageDone);

		
		//		y= startY + (endY - startY) * deltaY;
		actor.setPosition(x,y , alignment);
		System.out.println("fraction:" + fraction + " inverse fraction: " + (1-fraction) + " delta: " + deltaY +"Percentage: " + pPercentageDone + " x: " + x + " y: " + y);
	}
	

	public void reset () {
		super.reset();
		alignment = Align.bottomLeft;
		modifierListener = null;
		firstTimeExec = true;
		
		startX = 0; 
		startY = 0;
		endX = 0;
				endY = 0;
		mJumpHeight = 0;
		mJumpCount=1;
		
	}
	
//	public void restart () {
//		super.restart();
//	
//	}

	public void setPosition (float x, float y) {
		endX = x;
		endY = y;
	}

	public void setPosition (float x, float y, int alignment) {
		endX = x;
		endY = y;
		this.alignment = alignment;
	}

	public float getX () {
		return endX;
	}

	public void setX (float x) {
		endX = x;
	}

	public float getY () {
		return endY;
	}

	public void setY (float y) {
		endY = y;
	}

	public int getAlignment () {
		return alignment;
	}

	public void setAlignment (int alignment) {
		this.alignment = alignment;
	}

	public void setJumpHeight(float height) {
		mJumpHeight = height;
	}
	
	public void setJumpCount(int count)
	{
		mJumpCount = count;
	}
	
//	public void setModifierListener(IEntityModifierListener listener)
//	{
//		this.modifierListener = listener;
//	}
}
