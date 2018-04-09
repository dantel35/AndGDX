package com.andgdx.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class CommandCache implements ICommandCache {

	HashMap<Long, ICommand> cache = new HashMap<Long, ICommand>();
	
	
	
	public void addCommand(ICommand command) {
		cache.put(command.getID(), command);
		
	}

	public void compareCommands(ICommandContainer container) {
		LinkedList <ICommand> compareCommands = container.getCommands();
		ICommand cachedCommand;
		for(ICommand command : compareCommands)
		{
			cachedCommand = cache.get(command.getID());
			System.out.println(cachedCommand.getID() + " " + command.getID() + " "+ command.equals(cachedCommand));
			
			if (command.equals(cachedCommand))
			{
//				CommandFactory.free(GoToCommand.class, command);
			}
			else
			{
				correct(cache, command.getID());
			}
			cache.remove(command.getID());
			cachedCommand.setFree();
		}
	}
	
	private void correct(HashMap<Long,ICommand> cache, long fromIDupwards)
	{
		List<Long> sortedKeys=new ArrayList<Long>(cache.keySet());
		Collections.sort(sortedKeys);
		
		for (Long key : sortedKeys)
		{
			ICommand command = cache.get(key);
			command.correctionExecute(null);
		}
	}

}
