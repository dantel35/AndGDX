package com.andgdx.entity.shapes;

import com.andgdx.entity.IEntity;
import com.andgdx.entity.EntityCore;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class CircleCore extends EntityCore implements IEntity{
	
	Texture texture;
	Sprite sprite;
	Color color = Color.WHITE;
	public CircleCore()
	{
		this(0,0,10);
	}
	
	public CircleCore(int x, int y, int radius)
	{
		int width = (radius+1)*2;
		int height = width;
		Pixmap pixmap = new Pixmap( width, height, Format.RGBA8888 );
		pixmap.setColor(Color.WHITE);
		pixmap.fillCircle(width/2, height/2, radius);
		
		
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
