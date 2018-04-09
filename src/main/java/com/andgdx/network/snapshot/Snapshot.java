package com.andgdx.network.snapshot;

import java.util.HashMap;
import com.andgdx.command.ICommandContainer;

public class Snapshot {

	private int clientID;
	
	HashMap<String, ICommandContainer> entityCommands;
	
	public HashMap<String, ICommandContainer> getEntityCommands() {
		return entityCommands;
	}
	
	public ICommandContainer getCommandContainerFor(String id)
	{
		return entityCommands.get(id);
	}

	public void setEntityCommands(HashMap<String, ICommandContainer> entityCommands) {
		this.entityCommands = entityCommands;
	}

	public int getClientID()
	{
		return clientID;
	}
	
	
	
}
