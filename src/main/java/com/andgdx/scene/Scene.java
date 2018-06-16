package com.andgdx.scene;

import java.util.ArrayList;
import java.util.List;

import com.andgdx.dummies.BackgroundSpriteDummy;
import com.andgdx.dummies.DummyDrawingHandler;
import com.andgdx.dummies.ParallaxBackgroundDummy;
import com.andgdx.engine.Engine;
import com.andgdx.entity.Entity;
import com.andgdx.entity.EntityCore;
import com.andgdx.entity.IEntity;
import com.andgdx.entity.collision.ICollideArea;
import com.andgdx.scene.background.IParallaxBackground;
import com.andgdx.sprite.BackgroundSprite;
import com.andgdx.update.ISceneDrawHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.SnapshotArray;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * The Scene may only be used with an orthographic camera.
 * 
 * @author Daniel Knittel
 * @author Janina Knittel
 */
public class Scene extends Stage {

	ArrayList<Actor> children = new ArrayList<Actor>();
	ArrayList<ChildScene> childScenes = new ArrayList<ChildScene>();
	Color backgroundColor = Color.BLACK;
	BackgroundSprite backgroundEntity;
	BackgroundSprite backgroundEntityDefault;
	IParallaxBackground parallaxBackground;
	IParallaxBackground parallaxBackgroundDefault;
	int screenWidth, screenHeight;
	private Viewport viewport;
	private InputMultiplexer inputMultiplexer;
	private Table stageRoot;
	ISceneDrawHandler drawHandler;
	ISceneDrawHandler defaultdrawHandler = new DummyDrawingHandler();
	private boolean sceneInitialised;
	private boolean debug = false;
	private boolean paused = false;
	Scene childscene;
	private com.badlogic.ashley.core.Engine ashleyEngine;

	public Scene(Viewport fillViewport) {
		super(fillViewport);
		init();
	}

	public Scene() {
	}

	/**
	 * Attach a child scene to this scene. A child scene will always be on a
	 * higher layer as the parent scene.
	 * 
	 * @param childScene
	 */
	public void attachChildScene(ChildScene childScene) {
		childScenes.add(childScene);
		childScene.setParent(this);
	}

	/**
	 * Detach a child scene from this scene. A child scene is a scene that will always be on a
	 * higher layer as the parent scene.
	 * @param childScene
	 */
	public void detachChildScene(ChildScene childScene) {
		childScenes.remove(childScene);
		childScene.setParent(null);
	}

	public void clearChildScenes() {
		for (ChildScene scene : childScenes) {
			scene.setParent(null);
		}
		childScenes.clear();
	}

	public void updateChildScenes(float deltaTime) {
		if (childScenes.size() > 0)
			for (Scene scene : childScenes) {
				scene.onUpdate(deltaTime);
			}
	}

	/**
	 * Set this to true and the collide areas assigned to the attached entities
	 * will be drawn (in green).
	 * 
	 * @param enable
	 */
	public void setCollideAreaDebugDraw(boolean enable) {
		debug = enable;
	}

	/**
	 * Override this method. You can populate your scene in here, e.g. attaching
	 * all the entities you need to be attached at the startup of the scene.
	 */
	public void onPopulateScene() {

	}

	public void onInitialiseScene() {
		init();
	}

	public InputMultiplexer getInputMultiplexer() {
		return inputMultiplexer;
	}

	protected void init() {
		if (sceneInitialised == false) {
			sceneInitialised = true;
			inputMultiplexer = new InputMultiplexer();
			inputMultiplexer.addProcessor(this);

			Gdx.input.setInputProcessor(inputMultiplexer);

			stageRoot = new Table();
			stageRoot.setFillParent(true);
			this.addActor(stageRoot);

			parallaxBackgroundDefault = new ParallaxBackgroundDummy();
			backgroundEntityDefault = new BackgroundSpriteDummy();

			backgroundEntity = backgroundEntityDefault;
			parallaxBackground = parallaxBackgroundDefault;
			drawHandler = defaultdrawHandler;
		}
	}

	public void onUpdate(float deltaTime) {
		if (paused == false) {
			onDraw();
			act(deltaTime);
		}

		updateChildScenes(deltaTime);
	}

	public void onDraw() {
		draw();
	}

	public void draw() {
		Camera camera = viewport.getCamera();
		camera.update();

		if (!getRoot().isVisible())
			return;

		Gdx.gl.glClearColor(this.getBackgroundColor().r,
				this.getBackgroundColor().g, this.getBackgroundColor().b,
				this.getBackgroundColor().a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		Batch batch = this.getBatch();
		if (batch != null) {
			batch.setProjectionMatrix(camera.combined);
			batch.begin();
			drawHandler.preDraw(batch, camera);
			backgroundEntity.draw(batch, camera.position.x
					- camera.viewportWidth / 2, camera.position.y
					- camera.viewportHeight / 2, camera.viewportWidth,
					camera.viewportHeight);
			parallaxBackground.draw((OrthographicCamera) camera, batch);
			drawHandler.onDraw(batch, camera);
			getRoot().draw(batch, 1);
			drawHandler.postDraw(batch, camera);
			if (debug)
				debugCollideAreaDraw(batch);
			batch.end();

			drawHandler.postBatchEndDraw(batch, camera);
		}
	}

	private void debugCollideAreaDraw(Batch batch) {
		for (Actor entity : children) {
			if (entity instanceof IEntity) {
				ICollideArea area = ((IEntity) entity).getCollideArea();
				if (area != null) {
					((IEntity) entity).updateCollideArea();
					area.debugDraw(batch);
				}
			}
		}

	}

	public void attachChild(Entity entity) {
		uniformAttach(entity);
	}

	public void attachChild(EntityCore entity) {
		uniformAttach(entity);
	}

	private void uniformAttach(Actor entity) {
		stageRoot.addActor(entity);
		children.add(entity);
		setParent(entity, this);
	}

	/**
	 * Use this attach if you are attaching from another thread (not the render
	 * thread)
	 * 
	 * @param entity
	 */
	public void safeAttach(final Entity entity) {
		Gdx.app.postRunnable(new Runnable() {

			public void run() {
				attachChild(entity);
			}
		});
	}

	/**
	 * Sets the background color.
	 * 
	 * @param color
	 */
	public void setBackgroundColor(Color color) {
		this.backgroundColor = color;

	}

	/**
	 * Sets a background sprite. This background will be above a background
	 * color, but behind a parallax background.
	 * 
	 * @param backgroundSprite
	 */
	public void setBackground(BackgroundSprite backgroundSprite) {
		if (backgroundSprite != null) {
			backgroundEntity = backgroundSprite;
		} else {
			backgroundEntity = backgroundEntityDefault;
		}
	}

	/**
	 * Sets the parallaxBackground for this scene. This background will be above
	 * the background you set with "setBackground(...)".
	 * 
	 * @param parallaxBackground
	 */
	public void setParallaxBackground(IParallaxBackground parallaxBackground) {
		if (parallaxBackground != null) {
			this.parallaxBackground = parallaxBackground;

		} else {
			this.parallaxBackground = parallaxBackgroundDefault;
		}
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setViewport(Viewport viewport, int screenWidth, int screenHeight) {
		super.setViewport(viewport);
		this.viewport = viewport;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		updateViewport();
	}

	public void updateViewport() {
		viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),
				false);
		
		float pixelWidth = Gdx.graphics.getBackBufferWidth(); 
		float pixelHeight = Gdx.graphics.getBackBufferHeight(); 
         
		float cameraHeight = Engine.getCamera().getHeight();
		float cameraWidth = Engine.getCamera().getWidth();
		
		float unitWidth = Gdx.graphics.getWidth();
		float unitHeight = Gdx.graphics.getHeight();
		float getappWidth = Gdx.app.getGraphics().getWidth();
		float getappHeight = Gdx.app.getGraphics().getHeight();
		
		float widthRatio = pixelWidth/cameraWidth;
		float heightRatio = pixelHeight/cameraHeight;
		
		widthRatio = (int)(widthRatio * 10)/10.0f;
		heightRatio = (int)(heightRatio * 10)/10.0f;
		
		OrthographicCamera cam = (OrthographicCamera) Engine.getCamera().getViewport().getCamera();
//		cam.zoom = 2-heightRatio;
		
		float screenWidth = viewport.getScreenWidth();
		float screenHeight = viewport.getScreenHeight();
		float worldWidth = viewport.getWorldWidth();
		float worldHeight = viewport.getWorldHeight();

		
		System.out.println("Ratio is width: " + widthRatio + " height: " + heightRatio);
		System.out.println("unitwidth: " + unitWidth + " cameraWidth: " + cameraWidth 
				+ " screenWidth: " + screenWidth + " worldWidth: " + worldWidth
				+ " getappWidth: " + getappWidth);
		
		
		System.out.println("unitHeight: " + unitHeight + " cameraHeight: " + cameraHeight 
				+ " screenHeight: " + screenHeight + " worldHeight: " + worldHeight
				+ " getappHeight: " + getappHeight);
		screenWidth = ((int)(screenWidth/32f))*32;
		screenHeight = ((int)(screenHeight/32f))*32;
//		unitWidth = ((int)(unitWidth/2f))*2;
//		unitHeight = ((int)(unitHeight/2f))*2;
		
//		viewport.update((int)(screenWidth), (int)(screenHeight),
//				false);
		
	}

	public void onResize(int width, int height) {
		Viewport viewport = getViewport();
		if (viewport != null) {
			viewport.update(width, height, false);

		}
	}

	private void updateZIndices() {
		int index = 0;
		for (Actor entity : children) {
			index++;
			((Actor) entity).setZIndex(index);
		}
	}

	public void detachChild(Entity entity) {
		uniformDetach(entity);
	}

	public void detachChild(EntityCore entity) {
		uniformDetach(entity);
	}

	private void uniformDetach(Actor entity) {
		entity.remove();
		children.remove(entity);

		setParent(entity, null);
		if (entity instanceof IEntity)
		{
			IEntity ientity = (IEntity) entity;
			ashleyEngine.removeEntity(ientity.getAshleyEntity());
		}
		
	}

	private void setParent(Actor entity, Scene scene) {
		if (entity instanceof Entity) {
			((Entity) entity).setParentScene(scene);
		} else if (entity instanceof EntityCore) {
			((EntityCore) entity).setParentScene(scene);
		}
	}

	public void safeDetach(final Entity entity) {
		uniformSafeDetach(entity);
	}

	public void safeDetach(final EntityCore entity) {
		uniformSafeDetach(entity);
	}

	private void uniformSafeDetach(final Actor entity) {
		Gdx.app.postRunnable(new Runnable() {

			public void run() {
				uniformDetach(entity);
			}
		});
	}

	public int getChildCount() {
		return children.size();
	}

	/**
	 * Removes all Entities/Actors from the scene. Same as clear().
	 */
	public void detachAllChildren() {
		clear();
	}

	/**
	 * Removes all Entities/Actors from the scene.
	 */
	public void clear() {
		super.clear();
		children.clear();
	}

	public SnapshotArray<Actor> getChildrenAsActors() {
		return stageRoot.getChildren();
	}

	/**
	 * This list will only be up to date if you detached all the entities using
	 * this scenes "detachChild(...)" or "safeDetachChild(...)" methods (for
	 * every detach!).
	 * 
	 * @return
	 */
	public List<Actor> getAllChildren() {
		return children;
	}

	/**
	 * Register a draw handler on the scene. Any new registration will overwrite
	 * a previously added handler.
	 * 
	 * @param handler
	 */
	public void registerSceneDrawHandler(ISceneDrawHandler handler) {
		if (handler == null) {
			drawHandler = defaultdrawHandler;
		} else {
			drawHandler = handler;
		}
	}
	
	/**
	 * Set the scene paused. It it is paused, draw and update/act of this scene will not be fired anymore.
	 * @param paused
	 */
	public void setPaused(boolean paused)
	{
		this.paused = paused;
	}
	
	/**
	 * If the scene is paused, this method will return true.
	 * @return
	 */
	public boolean isPaused()
	{
		return this.paused;
	}

	public void setAshleyEngine(com.badlogic.ashley.core.Engine ashleyEngine) {
		this.ashleyEngine = ashleyEngine;
		
	}
	

}
