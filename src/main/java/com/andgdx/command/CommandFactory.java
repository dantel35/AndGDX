package com.andgdx.command;



import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

public class CommandFactory {

	private static long id =0;
	
 
	
	static public <T extends ICommand> T pool(Class<T> type) {
		Pool<T> pool = Pools.get(type);
		T command = pool.obtain();
		command.setPool(pool);
		command.setID(getNextID());
		return command;
	}
	static public <T extends ICommand> void free(Class<T> type, T commandObject) {
		Pool<T> pool = Pools.get(type);
		pool.free(commandObject);
	}
	 
	
	
	private static long getNextID()
	{
		id++;
		return id;
	}
	
	public static ICommand getCommand(Class<Command> type) {
		return pool(type);
	}


 

}
