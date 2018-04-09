package com.andgdx.update;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * Use this class instead of the ISceneDrawHandler interface and override only the methods
 * you need. This way you do not need to have empty methods in your production code.
 * @author Nebukadnezar
 *
 */
public class SceneDrawHandlerAdapter implements ISceneDrawHandler{

	public void onDraw(Batch batch, Camera pCamera) {
		
	}

	public void preDraw(Batch batch, Camera pCamera) {
		
	}

	public void postDraw(Batch batch, Camera pCamera) {
		
	}

	public void preBatchStartDraw(Batch batch, Camera pCamera) {
		
	}

	public void postBatchEndDraw(Batch batch, Camera pCamera) {
		
	}

}
