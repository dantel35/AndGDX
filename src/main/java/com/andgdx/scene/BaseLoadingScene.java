package com.andgdx.scene;

import com.andgdx.assets.AssetPack;
import com.andgdx.engine.Engine;
import com.andgdx.text.Text;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class BaseLoadingScene extends Scene {

	private BitmapFont font;
	public AssetManager assetManager;
	private AssetPack pack;
	protected Text loadingFont;
	float minimalShowDuration;
	float elapsedSeconds;
	protected boolean showLoadingText;
	Table table = new Table();

	public BaseLoadingScene() {
		this(0,null);
	}
	
	public BaseLoadingScene(float minimalShowDurationInSeconds) {
		this(minimalShowDurationInSeconds,null);
	}

	public BaseLoadingScene(float minimalShowDurationInSeconds,BitmapFont font) {

		loadingFont = new Text(0, 0, font,"Loading: ", Color.WHITE);

		this.minimalShowDuration = minimalShowDurationInSeconds;

		setBackgroundColor(Color.BLACK);
		this.assetManager = Engine.getAssetManager();
		showLoadingText = true;

	}

	/**
	 * Override this
	 */
	public void onPopulateScene() {
		
		table.add(loadingFont).row();
		table.setFillParent(true);
		this.addActor(table);

	}

	private void loadAssets() {
		if(pack != null)
		pack.assetsLoading(assetManager);
	}

	/**
	 * Set a new AssetPack for the loading Scene.
	 * 
	 * @param pack
	 */
	public void setAssetPack(AssetPack pack) {
		this.pack = pack;
		loadAssets();
	}

	/**
	 * Do not call yourself.
	 */
	public void draw() {
		boolean ready = false;
		countElapsedSeconds();
		if (assetManager == null) {
			continueWithNextScene();
			return;
		}
		if (assetManager.update()) {

			ready = true;
		}

		if (showLoadingText) {
			loadingFont.setText("Loading: " + assetManager.getProgress() * 100
					+ " %");
		}
		super.draw();

		if (ready) {
			continueWithNextScene();
		}
	}

	protected void countElapsedSeconds() {
		elapsedSeconds = elapsedSeconds + Gdx.graphics.getDeltaTime();
	}

	protected void continueWithNextScene() {
		if (isMinimalWaitingTimeMet()) {
			elapsedSeconds = 0;
			SceneManager.loadScheduledScene();
		}
	}

	private boolean isMinimalWaitingTimeMet() {
		boolean result = false;
		if (elapsedSeconds >= minimalShowDuration) {
			result = true;
		}

		return result;
	}

	public void reset() {
		elapsedSeconds = 0;
		if (loadingFont != null)
			loadingFont.dispose();

		this.detachAllChildren();
	}

}
