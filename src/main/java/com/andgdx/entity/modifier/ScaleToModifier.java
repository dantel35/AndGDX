package com.andgdx.entity.modifier;

import com.andgdx.entity.IEntity;
import com.andgdx.entity.modifier.listener.IEntityModifierListener;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;

/**
 * Sets the entities scale from its current value to a specific value.
 * 
 * @author Nathan Sweet
 */
public class ScaleToModifier extends TemporalAction {
	private float startX, startY;
	private float endX, endY;
	protected IEntityModifierListener modifierListener;

	protected void begin() {
		startX = actor.getScaleX();
		startY = actor.getScaleY();
	}

	protected void update(float percent) {
		actor.setScale(startX + (endX - startX) * percent, startY
				+ (endY - startY) * percent);
		checkListener(percent);
	}

	private void checkListener(float percent) {
		if (modifierListener != null)
			if (percent >= 1) {
				modifierListener.onFinished((IEntity) actor);
			} else if (percent <= 0.1f) {
				modifierListener.onStarted((IEntity) actor);
			}

	}

	
	public void reset () {
		super.reset();
		modifierListener = null;
	}
	
	public void setModifierListener(IEntityModifierListener listener) {
		this.modifierListener = listener;
	}

	public void setScale(float x, float y) {
		endX = x;
		endY = y;
	}

	public void setScale(float scale) {
		endX = scale;
		endY = scale;
	}

	public float getX() {
		return endX;
	}

	public void setX(float x) {
		this.endX = x;
	}

	public float getY() {
		return endY;
	}

	public void setY(float y) {
		this.endY = y;
	}
}
