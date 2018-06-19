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
import com.andgdx.util.AndGDXMathUtils;
import com.badlogic.gdx.math.CatmullRomSpline;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
//import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.Align;


public class SplineJumpModifier extends AndGDXTemporalAction implements IEntityModifier{
	private float startX, startY;
	private float endX, endY;
	private float mJumpHeight;
	private float mJumpCount=1;
	private int alignment = Align.bottomLeft;
	private boolean firstTimeExec = true;
	private Vector2 vecStart;
//	protected IEntityModifierListener modifierListener;
	private Vector2 vec2;
	private Vector2 normVec;
	private Vector2 vecMiddle;
	private Vector2 vecEnd;
	private Vector2 controlEnd;
	private Vector2 controlStart;
	private Vector2 jumpVector;
	private Vector2[] controlPoints;
	 CatmullRomSpline<Vector2> path;

	protected void begin () {
		startX = actor.getX();
		startY = actor.getY();
		initializeSplie();

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

	
	private void onSetValues(final Actor pEntity, final float pPercentageDone, final float pX, final float pY) {
		
		path.valueAt(jumpVector, pPercentageDone);
		actor.setPosition(jumpVector.x,jumpVector.y , alignment);
		
	}

	private void initializeSplie() {
		vecStart = new Vector2(startX, startY);
		
		  vec2 = new Vector2(((endX+startX)/2), ((endY+startY)/2));
		
		  normVec = vec2.cpy().rotate90(5).nor();
		normVec.scl((vec2.len()/10)*mJumpHeight);
		  vecMiddle =  vec2.cpy().add(normVec);
		  vecEnd = new Vector2(endX,endY);
		  controlEnd = vecEnd.cpy().add(normVec.cpy().nor().rotate90(-1));
		  controlStart = vecStart.cpy().add(normVec.cpy().nor().rotate90(-1));

		
//		  controlPoints =  {controlStart,vecStart, vecMiddle,vecEnd, controlEnd};
		  controlPoints =  new Vector2[5];
		  controlPoints[0] = controlStart;
		  controlPoints[1] =vecStart;
				  controlPoints[2] = vecMiddle;
						  controlPoints[3] = vecEnd;
								  controlPoints[4] =controlEnd;

		   path = new CatmullRomSpline<Vector2>( controlPoints, false);
		
		  jumpVector = new Vector2();
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
		setPosition(x,y,alignment);
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
