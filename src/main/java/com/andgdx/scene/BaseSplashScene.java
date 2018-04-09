package com.andgdx.scene;

import com.andgdx.assets.AssetPack;
import com.andgdx.engine.Engine;
import com.andgdx.entity.IEntity;
import com.andgdx.sprite.AnimatedSprite;
import com.andgdx.sprite.Sprite;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
/**
 * This scene will be shown as the very first scene when the application starts.
 * It shows a logo that you specified via the constructor. It also acts as a loading scene but for the very first scene only.
 * Put all the assets in this scene that you want to be loaded on the startup of your application.
 * @author Daniel Knittel
 * @author Janina Knittel
 *
 */
public class BaseSplashScene extends BaseLoadingScene{
	
	public AssetManager assetManager;
	private   AssetPack pack;

	AnimatedSprite animatedLogo;
	Sprite staticLogo;
	
	float minimalShowDuration;
	IEntity logo;
	 
	public BaseSplashScene(AnimatedSprite animatedLogo)
	{
		this(animatedLogo, 0,null);
	}
	
	public BaseSplashScene(AnimatedSprite animatedLogo,BitmapFont font)
	{
		this(animatedLogo, 0, font);
	}
	public BaseSplashScene(AnimatedSprite animatedLogo, float minimalShowDuration )
	{
		this(animatedLogo, minimalShowDuration, null);
	}
	
	public BaseSplashScene(AnimatedSprite animatedLogo, float minimalShowDuration, BitmapFont font)
	{
		 super(minimalShowDuration,font);
		 this.animatedLogo =   animatedLogo;
		 logo= animatedLogo;
	     init(pack);
	}
	
	public BaseSplashScene(Sprite staticLogo)
	{
		this(staticLogo,0,null);
	}
	
	public BaseSplashScene(Sprite staticLogo, BitmapFont font)
	{
		this(staticLogo,0, font);
	}
	
	public BaseSplashScene(Sprite staticLogo,  float minimalShowDuration)
	{
		this(staticLogo,minimalShowDuration, null);
	}
	
	public BaseSplashScene(Sprite staticLogo, float minimalShowDuration, BitmapFont font)
	{
		 super(minimalShowDuration,font);
		 this.staticLogo = staticLogo;
		 logo= staticLogo;
	     init(pack);
	}
	

	private void init(AssetPack pack) {
		setBackgroundColor(Color.BLACK);
	     this.assetManager = Engine.getAssetManager();
	     this.pack = pack;
//	 	int y = (int) this.getCamera().viewportHeight / 2;
//		int x =   (int) ((this.getCamera().viewportWidth / 2)-logo.getWidth()/2) ;
	     
	 	int y = (int) Gdx.graphics.getHeight() / 2;
		int x =   (int) ((Gdx.graphics.getWidth() / 2)-logo.getWidth()/2) ;
		x = 0;
		logo.setX(x);
		logo.setY(y);
	     
		 
	     
	}
	
	public void setLogoPosition(float x, float y)
	{
		logo.setX(x);
		logo.setY(y);
	}
	
	/**
	 * Override this
	 */
	public void onPopulateScene() {
		
		if(animatedLogo != null)
		{
//			attachChild(animatedLogo);		
			table.add(animatedLogo).row();
		}
		if(staticLogo != null)
		{
//			attachChild(staticLogo);	
			table.add(staticLogo).row();
		}
		
		if(loadingFont != null)
		{
			attachChild(loadingFont);
			int y = (int) ( 15+ loadingFont.getHeight());
			int x =   (int) ((Gdx.graphics.getWidth() / 2)) ;
			loadingFont.setY(y);
			loadingFont.setX(x);
			table.add(loadingFont).bottom();
		}
		
		table.setFillParent(true);
		this.addActor(table);
	}
	
	private void loadAssets()
	{
		pack.assetsLoading(assetManager);
		
	}
	
	
	/**
	 * Set a new AssetPack for the loading Scene.
	 * @param pack
	 */
	public void setAssetPack(AssetPack pack)
	{
		this.pack = pack;
		loadAssets();
	}
	
	/**
	 * If set to true, the loading text known from the BaseLoadingScene
	 * will be displayed at the bottom of the screen.
	 * @param show
	 */
	public void showLoadingText(boolean show)
	{
		showLoadingText = show;
		if(loadingFont != null)
		{
			loadingFont.setVisible(show);
			
		}
		
	}
	
 

}
