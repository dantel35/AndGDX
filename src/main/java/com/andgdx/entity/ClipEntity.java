package com.andgdx.entity;

import com.andgdx.engine.Engine;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
/**
 * Everything added to this entity will be drawn only inside its bounds.
 * Everything that is outside of the specified bounds will be clipped (cut and the part that is outside the bounds will not be drawn).
 * @author Daniel Knittel
 *
 */
public class ClipEntity extends Entity {

	Rectangle clipBounds = new Rectangle();
	Rectangle scissors = new Rectangle();
	private boolean mClippingEnabled = true;

	
	public ClipEntity(int x, int y, int width, int height) {
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
	}
	
	public void setBounds(int x, int y, int width, int height)
	{
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
	}
	
	/**
	 * Returns whether clipping is enabled or not.
	 * Entity will only clip if clipping is enabled.
	 * Default value is true.
	 * @return
	 */
	
	public boolean isClippingEnabled() {
		return this.mClippingEnabled ;
	}


	/**
	 * Set whether clipping is enabled or not.
	 * Entity will only clip if clipping is enabled.
	 * Default value is true.
	 * @return
	 */
	public void setClippingEnabled(final boolean pClippingEnabled) {
		this.mClippingEnabled = pClippingEnabled;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {

		if(mClippingEnabled)
		{
			clipBounds.setX(getX());
			clipBounds.setY(getY());
			clipBounds.setWidth(getWidth());
			clipBounds.setHeight(getHeight());
			ScissorStack.calculateScissors(Engine.currentViewport.getCamera(),
					batch.getTransformMatrix(), clipBounds, scissors);
			batch.flush();
			ScissorStack.pushScissors(scissors);
			
			super.draw(batch, parentAlpha);
			
			ScissorStack.popScissors();
			
		}
		else
		{
			super.draw(batch,parentAlpha);
		}
	}

}
