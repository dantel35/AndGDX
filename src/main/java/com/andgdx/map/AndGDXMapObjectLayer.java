package com.andgdx.map;

import com.andgdx.engine.Engine;
import com.andgdx.entity.IEntity;
import com.andgdx.sprite.Sprite;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.Array;

public class AndGDXMapObjectLayer extends AndGDXMapLayer {
	
	Array<IEntity> entityArray;
	
	public AndGDXMapObjectLayer(TiledMap map, AndGDXTiledMapRenderer renderer, int layerIndex) {
		super(map, renderer, layerIndex);
		 convertObjectsToEntities();
		 }
	
	public void convertObjectsToEntities()
	{
			for (MapObject object : getObjects()) {
			
			if (object instanceof TextureMapObject)
			{
				TextureMapObject obj = (TextureMapObject) object;
				System.out.println("zaaaysdkfljasdlkfjasldkfjlk");
				Sprite entity = new Sprite(obj.getTextureRegion());
				entity.setScale(obj.getScaleX() * renderer.getUnitScale(), obj.getScaleY() * renderer.getUnitScale());
				entity.setX(obj.getX() * renderer.getUnitScale());
				entity.setY(obj.getY() * renderer.getUnitScale());
				System.out.println("Entity X = " + obj.getX() + " entity Y = " + obj.getY());
				
				this.attachChild(entity);
			}
			this.render = false;
}
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) { 
	
		super.draw(batch, parentAlpha);
	}


}
