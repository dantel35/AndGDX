package com.andgdx.scene;

import com.andgdx.assets.AssetPack;
import com.badlogic.gdx.assets.AssetManager;

/**
 * A template for an AssetScene.
 * It is a scene which is an asset pack itself. Only suitable for scenes with very few assets needed.
 * It is the better strategy to separate assets from the scene. Use carefully.
 * @author Daniel Knittel
 *
 */
public abstract class AssetSceneTemplate extends AssetScene implements AssetPack {

	public abstract void onPopulateScene();
	public abstract void assetsLoading(AssetManager manager);
}
