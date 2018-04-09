package com.andgdx.assets;

import com.badlogic.gdx.assets.AssetManager;

/**
 * An AssetPack can be used in SceneManager to schedule a scene along with
 * all the assets that are needed in this scene.
 * 
 * Of course all assets can also be loaded with the very first scene.
 * @author Daniel Knittel
 *
 */
public interface AssetPack {
	
	/**
	 * Implement this method and put your assets into it in the form of:
	 * manager.load("data/mytexture.png", Texture.class);
	 * manager.load("data/myfont.fnt", BitmapFont.class);
	 * manager.load("data/mymusic.ogg", Music.class);
	 * ..etc..
	 * @param manager
	 */
	public void assetsLoading(AssetManager manager);

}
