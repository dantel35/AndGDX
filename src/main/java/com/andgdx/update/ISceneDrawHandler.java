package com.andgdx.update;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;


/**
 * This handler can be registered on the scene. It will fire before, during and after the main drawing phase. 
 * @author Daniel Knittel
 *
 */
public interface ISceneDrawHandler {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	/**
	 * This method is called during the main drawing phase before all other drawing/rendering calls in this phase.
	 * (i.e. after the background is rendered, but before any entity/actor added to the scene is rendered).
	 * @param batch
	 * @param pCamera
	 */
	public void onDraw(final Batch batch, final Camera pCamera);
	
	/**
	 * This method is called before the main drawing phase (i.e. before any entity/actor added to the scene is drawn, even before background drawing).
	 * @param batch
	 * @param pCamera
	 */
	public void preDraw(final Batch batch, final Camera pCamera);
	
	/**
	 * This method is called right after the main drawing phase (i.e. after all entities/actors added to the scene were drawn).
	 * @param batch
	 * @param pCamera
	 */
	public void postDraw(final Batch batch, final Camera pCamera);
	
	
	
	
	/**
	 * This method is called before the main drawing phase and before batch.start(). 
	 * Notice that you need to call batch.begin() and batch.end() yourself, using the batch
	 * provided via parameter or a different batch.
	 * @param batch
	 * @param pCamera
	 */
	public void preBatchStartDraw(final Batch batch, final Camera pCamera);
	
	
	/**
	 * This method is called after the main drawing phase (after batch.end()). 
	 * Notice that you need to call batch.begin() and batch.end() yourself, using the batch
	 * provided via parameter or a different batch.
	 * @param batch
	 * @param pCamera
	 */
	public void postBatchEndDraw(final Batch batch, final Camera pCamera);
}
