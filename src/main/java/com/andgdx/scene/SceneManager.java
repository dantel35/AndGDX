package com.andgdx.scene;

import com.andgdx.assets.AssetPack;
import com.andgdx.engine.Engine;

public class SceneManager {
	
	public static Scene scheduledScene;
	public static BaseLoadingScene loadingScene;
	public static BaseSplashScene startingSplashScene;
	public static Engine engine;
	private static boolean firstTimeStarted = true;
	
	/**
	 * Schedule the next scene along with the AssetPack this scene needs.
	 * This is the scene that you want to get displayed as soon as possible (right after the resources needed for it are loaded).
	 * @param scene
	 */
	public static void scheduleScene(Scene scene, AssetPack pack)
	{
		System.out.println("schedule scene");
		if(engine!=null)
		{
			scheduledScene = scene;
			chooseProperScene(pack);
		}
	}
	
	/**
	 * Convenience method.
	 * Schedule the next scene that itself is an AssetPack. You should prefer separating the AssetPack from your scene.
	 * This is the scene that you want to get displayed as soon as possible (right after the resources needed for it are loaded).
	 * @param scene
	 */
	public static void scheduleScene(AssetScene scene)
	{
		scheduleScene(scene,scene);
	}

	private static void chooseProperScene(AssetPack pack) {
		
		if(firstTimeStarted == false || startingSplashScene == null)
		{
			
			prepareLoadingScene(pack);
			engine.setScene(loadingScene);
		}
		else
		{
			firstTimeStarted=false;
			lifecycleScene(startingSplashScene);
			startingSplashScene.setAssetPack(pack);
			engine.setScene(startingSplashScene);
			
		}
		
	}
	
	private static void prepareLoadingScene(AssetPack pack) {
		System.out.println("prepare loading scene");
		if(loadingScene == null)
		{
			loadingScene = new BaseLoadingScene();
			lifecycleScene(loadingScene);
		}
		 	loadingScene.setAssetPack(pack);
			
		 
	}

	/**
	 * This will set the standard loading scene that is displayed between scene switches
	 * @param scene
	 */
	public static void setLoadingScene(BaseLoadingScene scene)
	{
		loadingScene = scene;
	}
	
	public static void setStartingSplashScene(BaseSplashScene scene)
	{
		startingSplashScene = scene;	
	}
	
	public static void loadScheduledScene()
	{
		if(engine != null && scheduledScene != null)
		{
 
			lifecycleScene(scheduledScene);
			engine.setScene(scheduledScene);
		}
	}
	
	private static void lifecycleScene(Scene scene)
	{
		scene.onInitialiseScene();
		scene.onPopulateScene();
	}
	
	private static void unloadOldScene()
	{
		
	}

}
