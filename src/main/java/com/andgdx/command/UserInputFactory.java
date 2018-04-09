package com.andgdx.command;



import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

public class UserInputFactory {

	private static long id =0;
	
 
	
	static public <T extends IUserInput> T pool(Class<T> type) {
		Pool<T> pool = Pools.get(type);
		T command = pool.obtain();
//		command.setPool(pool);
		command.setID(getNextID());
		return command;
	}
	
	static public <T extends IUserInput> void free(Class<T> type, T inputObject) {
		Pool<T> pool = Pools.get(type);
		pool.free(inputObject);
	}
	 
	
	
	private static long getNextID()
	{
		id++;
		return id;
	}
	
	public static UserKeyInput getUserInput(Class<IUserInput> type) {
		return new UserKeyInput();
	}


 

}
