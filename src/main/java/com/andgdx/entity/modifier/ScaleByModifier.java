package com.andgdx.entity.modifier;

import com.andgdx.entity.IEntity;
import com.andgdx.entity.modifier.listener.IEntityModifierListener;
import com.badlogic.gdx.scenes.scene2d.actions.RelativeTemporalAction;

/**
 * Scales an actor's scale to a relative size.
 * 
 * @author Nathan Sweet
 */
public class ScaleByModifier extends RelativeTemporalAction {
	private float amountX, amountY;
	protected IEntityModifierListener modifierListener;
	float oldOriginX, oldOriginY;

	protected void updateRelative(float percentDelta) {
		oldOriginX = actor.getOriginX();
		oldOriginY= actor.getOriginY();

//		actor.setOrigin(actor.getWidth()/2, actor.getHeight()/2);
		actor.scaleBy(amountX * percentDelta, amountY * percentDelta);
//		actor.setOrigin(oldOriginX, oldOriginY);
		checkListener(percentDelta);
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

	public void setAmount(float x, float y) {
		amountX = x;
		amountY = y;
	}

	public void setAmount(float scale) {
		amountX = scale;
		amountY = scale;
	}

	public float getAmountX() {
		return amountX;
	}

	public void setAmountX(float x) {
		this.amountX = x;
	}

	public float getAmountY() {
		return amountY;
	}

	public void setAmountY(float y) {
		this.amountY = y;
	}

}
