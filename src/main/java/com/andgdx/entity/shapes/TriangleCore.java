package com.andgdx.entity.shapes;

import com.andgdx.entity.IEntity;
import com.andgdx.entity.EntityCore;
import com.andgdx.util.Point;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class TriangleCore extends EntityCore implements IEntity{
	
	Texture texture;
	Sprite sprite;
	Color color = Color.WHITE;
	
	 
	
	public TriangleCore(int x1, int y1, int x2, int y2, int x3, int y3)
	{
		int width = getBiggestDistance(x1, x2, x3);
		int height = getBiggestDistance(y1,y2,y3);
		Pixmap pixmap = new Pixmap( width, height, Format.RGBA8888 );
		pixmap.setColor(Color.WHITE);
		Point lowestPoint = getLowestPoint(x1, y1, x2, y2, x3, y3);
		
		
		pixmap.fillTriangle(x1, y1, x2, y2, x3, y3);
		
		
		texture = new Texture(pixmap);
		this.setBounds(lowestPoint.x, lowestPoint.y, width, height);
		 
		
		pixmap.dispose();
		
	}
	
	private int getBiggestDistance(int coord1, int coord2, int coord3)
	{
		return Math.max(Math.abs(coord1-coord2), Math.abs(coord1-coord3));
	}
	
	private Point getLowestPoint(int x1, int y1, int x2 ,int y2, int x3, int y3)
	{
		Point point = null;
		if(y1 < y2 && y1 < y3)
		{
			point = new Point(x1,y1);
		}
		else if( y2 < y1 && y2 < y3)
		{
			point = new Point(x2,y2);
		}
		else if( y3 < y1 && y3 < y2)
		{
			point = new Point(x3,y3);
		}
		
		return point;
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
