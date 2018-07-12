package com.andgdx.sprite;

import com.andgdx.entity.EntityCore;
import com.andgdx.texture.TextureOptions;
import com.andgdx.util.ColorUtil;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SpriteCore extends EntityCore {
	Texture texture;
	protected TextureRegion textureRegion;
	

	
	protected SpriteCore()
	{
		super();

	}
	
	public SpriteCore(String pathName) {
		this(new Texture(pathName), null);
	}

	public SpriteCore(Texture texture) {
		this(texture, null);
	}

	public SpriteCore(String pathName, TextureOptions options) {
		this(new Texture(pathName), options);

	}

	public SpriteCore(TextureRegion region) {
		this(region, null);
	}

	public SpriteCore(TextureRegion region, TextureOptions options) {
		super();

		textureRegion = region;
		if (options != null) {
			applyFilterAndWrap(region.getTexture(), options);
		}
		
		
		this.setBounds(0, 0, textureRegion.getRegionWidth(), textureRegion.getRegionHeight());
	}

	public SpriteCore(Texture texture, TextureOptions options) {
		
		this(new TextureRegion(texture), options);

	}
	
	/**
	 * Only call this yourself if you did not load it using the AssetManager!
	 */
	public void dispose()
	{
		texture.dispose();
	}
	
	
	

	protected void applyFilterAndWrap(Texture texture, TextureOptions options) {
		texture.setFilter(options.mMinFilter, options.mMagFilter); // causes
																	// flickering
																	// when
																	// moving
		texture.setWrap(options.mWrapT, options.mWrapS);
	}

	public void setAlpha(float alpha) {
		this.getColor().a = alpha;
	}

	@Override
	public void draw(Batch batch, float alpha) {
		if (textureRegion != null) {
			batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a * alpha);
			 
 
			batch.draw(textureRegion, getX(), getY());
			
		}

		super.draw(batch, alpha);

	}
	
 

}
