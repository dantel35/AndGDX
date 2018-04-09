package com.andgdx.entity.modifier;

import com.andgdx.entity.modifier.listener.IEntityModifierListener;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;

/**
 * Sets the actor's color (or a specified color), from the current to the new
 * color. Note this action transitions from the color at the time the action
 * starts to the specified color.
 * 
 * @author Nathan Sweet
 */
public class ColorModifier extends TemporalAction {
	private float startR, startG, startB, startA;
	private Color color;
	private final Color end = new Color();
	protected IEntityModifierListener modifierListener;

	protected void begin() {
		if (color == null)
			color = actor.getColor();
		startR = color.r;
		startG = color.g;
		startB = color.b;
		startA = color.a;
	}

	protected void update(float percent) {
		float r = startR + (end.r - startR) * percent;
		float g = startG + (end.g - startG) * percent;
		float b = startB + (end.b - startB) * percent;
		float a = startA + (end.a - startA) * percent;
		color.set(r, g, b, a);
		actor.setColor(color);
		checkListener(percent);
	}

	private void checkListener(float percent) {
		if (modifierListener != null)
			if (percent >= 1) {
				modifierListener.onFinished();
			} else if (percent <= 0.1f) {
				modifierListener.onStarted();
			}

	}

	public void setModifierListener(IEntityModifierListener listener) {
		this.modifierListener = listener;
	}

	public void reset() {
		super.reset();
		color = null;
		modifierListener = null;
	}

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

	public Color getEndColor() {
		return end;
	}

	/** Sets the color to transition to. Required. */
	public void setEndColor(Color color) {
		end.set(color);
	}
}