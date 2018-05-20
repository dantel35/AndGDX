package com.andgdx.entity.modifier;

import com.andgdx.entity.IEntity;
import com.andgdx.entity.modifier.listener.IEntityModifierListener;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;

/** Sets the actor's rotation from its current value to a specific value.
 * @author Nathan Sweet */
public class RotateToModifier extends TemporalAction {
	private float start, end;
	protected IEntityModifierListener modifierListener;
	protected void begin () {
		start = actor.getRotation();
	}

	protected void update (float percent) {
		actor.setRotation(start + (end - start) * percent);
		checkListener(percent);
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

	public float getRotation () {
		return end;
	}

	public void setRotation (float rotation) {
		this.end = rotation;
	}
	
}
