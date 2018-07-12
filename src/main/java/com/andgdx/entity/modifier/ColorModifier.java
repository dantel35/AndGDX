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
 * @author Nathan Sweet - original author, this is a tweaked class
 */
public class ColorModifier  extends AndGDXTemporalAction implements IEntityModifier{
	private float startR, startG, startB, startA;
	private Color color;
	private final Color end = new Color();
	protected IEntityModifierListener modifierListener;

	protected void begin() {
		if (color == null)
			color =  actor.getColor();
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
		super.update(percent);
	}

 
 

	public void reset() {
		super.reset();
		color = null;
 
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