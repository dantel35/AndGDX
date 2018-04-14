package com.andgdx.entity.modifier;

import com.andgdx.entity.modifier.listener.IEntityModifierListener;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;

/**
 * Sets the alpha for an actor's color (or a specified color), from the current
 * alpha to the new alpha. Note this action transitions from the alpha at the
 * time the action starts to the specified alpha.
 * 
 * @author Nathan Sweet
 */
public class AlphaModifier extends AndGDXTemporalAction {
	private float start, end;
	private Color color;
	protected IEntityModifierListener modifierListener;


	
	protected void begin() {
		if (color == null)
			color = actor.getColor();
		start = color.a;
	}

	protected void update(float percent) {
		color.a = start + (end - start) * percent;
		actor.setColor(color);
		super.update(percent);
	}

//	private void checkListener(float percent) {
//		if(modifierListener != null)
//		if (percent >= 1 && notFinishedYet ) {
//			modifierListener.onFinished();
//			
//			
//			notFinishedYet = false;
//		} else if (firstTimeExec  ) {
//			modifierListener.onStarted();
//			firstTimeExec = false;
//		}
//
//	}

//	public void setModifierListener(IEntityModifierListener listener) {
//		this.modifierListener = listener;
//	}

	public void reset() {
		super.reset();
		color = null;
//		modifierListener = null;
//		firstTimeExec = true;
//		notFinishedYet = true;
	}
	
//	public void restart () {
//		super.restart();
//	
//	}

	public Color getColor() {
		return color;
	}

	/**
	 * Sets the color to modify. If null (the default), the {@link #getActor()
	 * actor's} {@link Actor#getColor() color} will be used.
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	public float getAlpha() {
		return end;
	}

	public void setAlpha(float alpha) {
		this.end = alpha;
	}
}
