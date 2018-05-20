package com.andgdx.entity.modifier;

import com.andgdx.entity.IEntity;
import com.andgdx.entity.modifier.listener.IEntityModifierListener;
import com.badlogic.gdx.scenes.scene2d.actions.RelativeTemporalAction;

/** Sets the actor's rotation from its current value to a relative value.
 * @author Nathan Sweet */
public class RotateByModifier extends RelativeTemporalAction {
	private float amount;
	protected IEntityModifierListener modifierListener;

	protected void updateRelative (float percentDelta) {
		actor.rotateBy(amount * percentDelta);
		checkListener(percentDelta);
	}

	private void checkListener(float percent) {
		if(modifierListener != null)
		if (percent >= 1  ) {
			modifierListener.onFinished((IEntity) actor);
		} else if (percent <= 0.1f  ) {
			modifierListener.onStarted((IEntity) actor);
		}

	}
	public void reset () {
		super.reset();
		modifierListener = null;
	}
	
	public void setModifierListener(IEntityModifierListener listener)
	{
		this.modifierListener = listener;
	}

	public float getAmount () {
		return amount;
	}

	public void setAmount (float rotationAmount) {
		amount = rotationAmount;
	}
}
