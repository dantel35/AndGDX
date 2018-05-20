package com.andgdx.map;

import com.andgdx.engine.Engine;
import com.andgdx.entity.Entity;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;

/**
 * A MapLayer that can be added to the scene as an entity.
 * @author Daniel Knittel
 *
 */

public class AndGDXMapLayer extends Entity{
	
	int index;
	TiledMap map;
	
	AndGDXTiledMapRenderer renderer;
	boolean render = true;
	private OrthographicCamera camera;
//	TiledMapTileLayer layer;
	MapLayer layer;
	
	public AndGDXMapLayer(TiledMap map, AndGDXTiledMapRenderer renderer, int layerIndex)
	{
		this.map = map;
		this. renderer = renderer;
		this.index = layerIndex;
		layer = map.getLayers().get(layerIndex);
	}
	
	
	// ===========================================================
		// Getter & Setter
		// ===========================================================

		public String getName() {
			return layer.getName();
		}

		/**
		 * Returns width of the layer.
		 */
		public float getWidth() {
			return 0;
		}

		/**
		 * Returns height of the layer.
		 */
		public float getHeight() {
			return 0;
		}
		
		public MapLayer getTiledMapTileLayer()
		{
			return layer;
		}
//		/**
//		 * Returns the amount of columns in this layer.
//		 * @return
//		 */
//		public int getTileColumns() {
//			return (int) (layer.getWidth()/layer.getTileWidth());
//		}
//
//		/**
//		 * Returns the amount of rows in this layer.
//		 * @return
//		 */
//		public int getTileRows() {
//			return (int) (layer.getHeight()/layer.getTileHeight());
//		}
//
//
//
//		public Cell getTile(final int pTileColumn, final int pTileRow)  {
//			return getTileAt((int)(pTileColumn*layer.getTileWidth()), (int)(pTileRow*layer.getTileHeight()));
//		}
//
//		/**
//		 * @param pX in SceneCoordinates.
//		 * @param pY in SceneCoordinates.
//		 * @return the {@link TMXTile} located at <code>pX/pY</code>.
//		 */
//		public Cell getTileAt(final int x, final int y) {
//			
//
//			return layer.getCell(x, y);
//		}
	
	
	@Override
	public void draw(Batch batch, float parentAlpha) { 
		if (render)
		{
		camera = (OrthographicCamera) Engine.currentViewport.getCamera();
		 
		renderer.setView(camera);
		
		renderer.render(index);
		}
		super.draw(batch, parentAlpha);
	}
		


	public MapObjects getObjects() {
		return layer.getObjects();
	}

}
