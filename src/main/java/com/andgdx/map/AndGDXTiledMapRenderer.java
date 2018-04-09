package com.andgdx.map;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;

public class AndGDXTiledMapRenderer extends OrthogonalTiledMapRenderer {
	private HashMap<Integer, AndGDXTiledMapLayer> layerContainer = new HashMap<Integer, AndGDXTiledMapLayer>();

	public AndGDXTiledMapRenderer(TiledMap map, float unitScale, Batch batch) {
		super(map, unitScale, batch);

	}

	/**
	 * Convenience method. Provides a initialised AndGDXTiledMapLayer layer
	 * which can be added to the scene. Returns null if layer does not exist.
	 * 
	 * @param index
	 * @return an AndGDXTiledMapLayer representing a TiledMap layer or null if
	 *         the layer does not exist.
	 */
	public AndGDXTiledMapLayer getLayer(int index) {
		AndGDXTiledMapLayer resultingLayer = null;
		if (index > -1 && index < map.getLayers().getCount()) {

			if (layerContainer.containsKey(index)) {
				resultingLayer = layerContainer.get(index);
			}

			if (resultingLayer == null) {
				resultingLayer = new AndGDXTiledMapLayer(map, this, index);
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

	/** Called before the rendering of all layers starts. */
	protected void beginRender() {
		AnimatedTiledMapTile.updateAnimationBaseTime();
	}

	/** Called after the rendering of all layers ended. */
	protected void endRender() {
	}

}
