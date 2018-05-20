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
		onSetValues(actor, percent, startX, startY);
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
		final float deltaY = this.mJumpHeight * 4 * fraction * (1 - fraction);
		actor.setPosition(startX + (endX - startX) * pPercentageDone, startY +deltaY, alignment);
	}
	

	public void reset () {
		super.reset();
		alignment = Align.bottomLeft;
//		modifierListener = null;
//		firstTimeExec = true;
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
