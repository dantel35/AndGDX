package com.andgdx.sprite;

import com.andgdx.entity.Entity;
import com.andgdx.texture.TextureOptions;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Sprite extends Entity{
	
	protected SpriteCore core;
	
	protected Sprite()
	{}
	
	public Sprite(String pathName) {
		this(new Texture(pathName), null);
	}

	public Sprite(Texture texture) {
		this(texture, null);
	}

	public Sprite(String pathName, TextureOptions options) {
		this(new Texture(pathName), options);

	}

	public Sprite(TextureRegion region) {
		this(region, null);
	}


	public Sprite(Texture texture, TextureOptions options) {
		
		this(new TextureRegion(texture), options);

	}
	
	public Sprite(TextureRegion region, TextureOptions options) {
		
		core = new SpriteCore(region,options);
		addActor(core);
		
		this.setBounds(0, 0, region.getRegionWidth(), region.getRegionHeight());
	}
	
	/**
	 * Only call this yourself if you did not load it using the AssetManager!
	 */
	public void dispose()
	{
		core.dispose();
	}
 

	public void setColor(Color color)
	{
		super.setColor(color);
		if(core!=null)
		core.setColor(color);
	}
	
	public void setAlpha(float alpha) {
		if(core!=null)
		core.setAlpha(alpha);
	}	


}
