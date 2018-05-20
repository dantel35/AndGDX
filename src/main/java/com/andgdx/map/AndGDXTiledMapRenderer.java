package com.andgdx.map;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;

public class AndGDXTiledMapRenderer extends OrthogonalTiledMapRenderer {
	private HashMap<Integer, AndGDXMapLayer> layerContainer = new HashMap<Integer, AndGDXMapLayer>();

	public AndGDXTiledMapRenderer(TiledMap map, float unitScale, Batch batch) {
		super(map, unitScale, batch);

	}

	/**
	 * Convenience method. Provides a initialised AndGDXMapLayer layer
	 * which can be added to the scene. Returns null if layer does not exist.
	 * 
	 * @param index
	 * @return an AndGDXMapLayer representing a TiledMap layer or null if
	 *         the layer does not exist.
	 */
	public AndGDXMapLayer getLayer(int index) {
		AndGDXMapLayer resultingLayer = null;
		
		if (index > -1 && index < map.getLayers().getCount()) {
			MapLayer mapLayer = map.getLayers().get(index);
			if (layerContainer.containsKey(index)) {
				resultingLayer = layerContainer.get(index);
			}

			if (resultingLayer == null) {
				
				if (mapLayer instanceof TiledMapTileLayer)
				{
					resultingLayer = new AndGDXTiledMapLayer(map, this, index);
				}
				else
				{
					resultingLayer = new AndGDXMapObjectLayer(map, this, index);
					
				}
				layerContainer.put(index, resultingLayer);
			}
		}

		return resultingLayer;
	}

	@Override
	public void render() {
		beginRender();
		for (MapLayer layer : map.getLayers()) {
			if (layer.isVisible()) {
				if (layer instanceof TiledMapTileLayer) {
					renderTileLayer((TiledMapTileLayer) layer);
				} else {
					renderObjects(layer);
				}
			}
		}
		endRender();
	}

	@Override
	public void render(int[] layers) {
		beginRender();
		for (int layerIdx : layers) {
			MapLayer layer = map.getLayers().get(layerIdx);
			if (layer.isVisible()) {
				if (layer instanceof TiledMapTileLayer) {
					renderTileLayer((TiledMapTileLayer) layer);
				} else {
					renderObjects(layer);
				}
			}
		}
		endRender();
	}

	public void render(int layerIndex) {
		beginRender();
		MapLayer layer = map.getLayers().get(layerIndex);
		if (layer.isVisible()) {
			if (layer instanceof TiledMapTileLayer) {
				renderTileLayer((TiledMapTileLayer) layer);
			} else {
				renderObjects(layer);
			}
		}
		endRender();
	}
	
	
	@Override
	public void renderObject(MapObject object) {
		 if(object instanceof TextureMapObject) {
	            TextureMapObject textureObj = (TextureMapObject) object;
//	                batch.draw(textureObj.getTextureRegion(), textureObj.getX(), textureObj.getY());
//	                batch.draw(textureObj.getTextureRegion(), textureObj.getX(), textureObj.getY(), textureObj.getTextureRegion().getRegionWidth(), textureObj.getTextureRegion().getRegionHeight(), textureObj.getScaleX(), srcY, srcWidth, srcHeight, flipX, flipY);
	        }
	}
	

	/** Called before the rendering of all layers starts. */
	protected void beginRender() {
		AnimatedTiledMapTile.updateAnimationBaseTime();
	}

	/** Called after the rendering of all layers ended. */
	protected void endRender() {
	}

}
