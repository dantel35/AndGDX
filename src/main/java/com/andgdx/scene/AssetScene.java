package com.andgdx.scene;

import com.andgdx.assets.AssetPack;
import com.badlogic.gdx.assets.AssetManager;


/**
 * 
 * This is a scene which is an asset pack itself. Only suitable for scenes with very few assets needed.
 * It is the better strategy to separate assets from the scene. Use carefully.
 * @author Daniel Knittel
 *
 */
public class AssetScene extends Scene implements AssetPack {

	/**
	 * Override this.
	 */
	public void assetsLoading(AssetManager manager) {
	}

}
