package com.andgdx.entity.ashley.component;



import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

public class ComponentContainerFactory {

	private static long id =0;
	
 
	
	static public <T extends ComponentContainer> T pool(Class<T> type) {
		Pool<T> pool = Pools.get(type);
		T command = pool.obtain();
//		command.setPool(pool);
		command.setID(getNextID());
		return command;
	}
	
	static public <T extends ComponentContainer> void free(Class<T> type, T inputObject) {
		Pool<T> pool = Pools.get(type);
		pool.free(inputObject);
	}
	 
	
	
	private static long getNextID()
	{
		id++;
		return id;
	}
	
	public static ComponentContainer getComponentContainer(Class<ComponentContainer> type) {
		return new ComponentContainer();
	}


 

}
