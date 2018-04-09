package com.andgdx.command;

import java.util.LinkedList;

import com.andgdx.entity.IEntity;

public interface ICommandContainer {
	
	public void executeAllCommands();
	public void addCommand(ICommand command);
	public void setEntity(IEntity entity);
	public LinkedList<ICommand>  getCommands();

}
