package com.andgdx.scene;


import com.andgdx.entity.IEntity;


interface ISceneLayerManager {
	
	public void addToLayer(int layer, IEntity entity);
	/**
	 * Removes from normal layers and HUD. Slower than direct removel from either.
	 * @param entity
	 */
	public void remove(IEntity entity);

	public void moveToLayer(int layer, IEntity entity);
	public int getAmountOfEntities(int layer);
	public int getAmountOfEntities();
	public void doTheZOrdering();
	public void addToHud(int hudLayer, IEntity entity);

}
