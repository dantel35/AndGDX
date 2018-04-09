package com.andgdx.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class BackgroundSprite extends SpriteDrawable {

	public BackgroundSprite(Texture texture) {
		super(new Sprite(texture));
	}
	
	public BackgroundSprite(TextureRegion region)
	{
		super(new Sprite(region));
	}
	
	public BackgroundSprite()
	{}
	
	 
}
