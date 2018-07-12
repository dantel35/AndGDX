package com.andgdx.scene;

import java.util.ArrayList;
import java.util.List;

import com.andgdx.entity.IEntity;
import com.badlogic.gdx.utils.Array;

public class SceneLayerManager implements ISceneLayerManager {
	
	Array<ArrayList<IEntity>> layers = new Array<ArrayList<IEntity>>();
	Array<ArrayList<IEntity>> hudLayers = new Array<ArrayList<IEntity>>();
	private int currentHUDcoeff;
	private int amountOfEntities;
	private boolean amountChanged = true;
	public static int initialLayers = 3;
	
	
	public SceneLayerManager()
	{
		init();
	}

	private void init()
	{
		for(int i = 0; i< initialLayers; i++)
		{
			createNewLayer();
		}
	}
	
	private ArrayList<IEntity> createNewLayer()
	{
		updateHUDmeta();
		return new ArrayList<IEntity>();
	}
	
	private void updateHUDmeta()
	{
		if(currentHUDcoeff <= layers.size+1)
		{
			currentHUDcoeff = layers.size + 100;			
		}
	}

	@Override
	public void addToLayer(int layer, IEntity entity) {
		add(layer, entity, this.layers);
		
	}

	private void add(int layer, IEntity entity, Array<ArrayList<IEntity>> lLayers) {
		ArrayList<IEntity> lLayer = lLayers.get(layer);
		if(lLayer == null)
		{
			lLayer = createNewLayer();
		}
		entity.setLayer(layer);
		lLayer.add(entity);
		lLayers.add(lLayer);
		changeOccured();
	}
	
	private void changeOccured()
	{
		amountChanged = true;	
	}

	@Override
	public void moveToLayer(int layer,IEntity entity) {
//		ArrayList<IEntity> lLayer;
//		IEntity element;
//		for(ArrayList<IEntity> list : layers)
//		{
//			if(list.contains(entity))
//			for (int i = 0; i < list.size(); i++)
//			{
//				element = list.get(i);
//				if(element.equals(entity))
//				{
//					list.remove(i);
//					break;
//				}
//			}
//		}
		remove(entity);
		addToLayer(layer, entity);
		
	}

	@Override
	public void remove( IEntity entity) {
		
		
//		for(ArrayList<IEntity> list : layers)
//		{
//			list.remove(entity);
//		}
		changeOccured();
	}

	@Override
	public int getAmountOfEntities(int layer) {
		int result = 0;

	
		ArrayList<IEntity> lLayer = layers.get(layer);
		if (lLayer != null)
		{
			result = lLayer.size();
		}
		
		return result;
	}

	private int getPrecalcAmount() {
		return amountOfEntities;
	}
	
	private void setPrecalcAmount(int precalcAmount) {
		this.amountOfEntities =precalcAmount;
	}

	@Override
	public int getAmountOfEntities() {
		int result = getPrecalcAmount();
		if(amountChanged)
		{
			
		for(ArrayList<IEntity> list : layers)
		{
			result= result + list.size();
		}
		 setPrecalcAmount(result);
		 amountChanged = false;
		}
		
		return result;
	}

	@Override
	public void doTheZOrdering() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addToHud(int hudLayer, IEntity entity) {
		add(hudLayer, entity, this.hudLayers);
		
	}

}
