package com.andgdx.map;

import com.andgdx.engine.Engine;
import com.andgdx.entity.EntityCore;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
/**
 * An TiledMapLayer that can be added to the scene as an entity.
 * @author Daniel Knittel
 *
 */

public class AndGDXTiledMapLayer extends EntityCore{
	
	int index;
	TiledMap map;
	
	AndGDXTiledMapRenderer renderer;
	private OrthographicCamera camera;
	TiledMapTileLayer layer;
	
	public AndGDXTiledMapLayer(TiledMap map, AndGDXTiledMapRenderer renderer, int layerIndex)
	{
		this.map = map;
		this. renderer = renderer;
		this.index = layerIndex;
		layer = (TiledMapTileLayer) map.getLayers().get(layerIndex);
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
			return layer.getWidth()*layer.getTileWidth();
		}

		/**
		 * Returns height of the layer.
		 */
		public float getHeight() {
			return layer.getHeight()*layer.getTileHeight();
		}
		
		public TiledMapTileLayer getTiledMapTileLayer()
		{
			return layer;
		}
		/**
		 * Returns the amount of columns in this layer.
		 * @return
		 */
		public int getTileColumns() {
			return (int) (layer.getWidth()/layer.getTileWidth());
		}

		/**
		 * Returns the amount of rows in this layer.
		 * @return
		 */
		public int getTileRows() {
			return (int) (layer.getHeight()/layer.getTileHeight());
		}



		public Cell getTile(final int pTileColumn, final int pTileRow)  {
			return getTileAt((int)(pTileColumn*layer.getTileWidth()), (int)(pTileRow*layer.getTileHeight()));
		}

		/**
		 * @param pX in SceneCoordinates.
		 * @param pY in SceneCoordinates.
		 * @return the {@link TMXTile} located at <code>pX/pY</code>.
		 */
		public Cell getTileAt(final int x, final int y) {
			

			return layer.getCell(x, y);
		}
	
	
	@Override
	public void draw(Batch batch, float parentAlpha) { 
		camera = (OrthographicCamera) Engine.currentViewport.getCamera();
		 
		renderer.setView(camera);
		
		renderer.render(index);
	}

}
