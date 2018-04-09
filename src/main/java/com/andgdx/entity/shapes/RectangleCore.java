package com.andgdx.entity.shapes;

import com.andgdx.entity.IEntity;
import com.andgdx.entity.EntityCore;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class RectangleCore extends EntityCore implements IEntity{
	
	Texture texture;
	Sprite sprite;
	Color color = Color.WHITE;
	public RectangleCore()
	{
		this(0,0,1,1);
	}
	
	public RectangleCore(int x, int y,int width, int height)
	{
		
		Pixmap pixmap = new Pixmap( width, height, Format.RGBA8888 );
		pixmap.setColor(Color.WHITE);
		pixmap.fillRectangle(0, 0, width, height);
		
		
		texture = new Texture(pixmap);
		this.setBounds(x, y, width, height);
		 
		
		pixmap.dispose();
		
	}
 
	
	@Override
	public void draw(Batch batch, float alpha) {
		
		if (texture != null) {
			batch.setColor(getColor().r,getColor().g, getColor().b, getColor().a * alpha);
			batch.draw(texture, getX(), getY());
			batch.setColor(Color.WHITE);
		}

		super.draw(batch, alpha);

	}

	public int getCenterX() {
		return (int) (getX()+getWidth()/2);
	}

	public int getCenterY() {
		return (int) (getY()+getHeight()/2);
	}
	
	public void setAlpha(float alpha) {
		this.getColor().a = alpha;
	}


	
	 

}
