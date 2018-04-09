package com.andgdx.command;

import java.util.LinkedList;

import com.andgdx.entity.IEntity;

public class CommandContainer implements ICommandContainer {

	private LinkedList<ICommand> commands = new LinkedList<ICommand>();
	private IEntity entity;
	
	public CommandContainer()
	{}
	
	public void executeAllCommands() {
		for (ICommand command : commands)
		{
			command.execute(entity);
		}
		
	}
	public void addCommand(ICommand command) {
		commands.add(command);
		
	}
	public void setEntity(IEntity entity) {
		this.entity = entity;
	}

	public LinkedList<ICommand> getCommands() {
		return commands;
	}
	
	
	
}
