package com.andgdx.command;

import com.andgdx.entity.IEntity;

public class SetPositionCommand extends Command implements ICommand {

	private int x,y;
	private IEntity entity;
	
	
	public SetPositionCommand()
	{}
	
	
	public SetPositionCommand(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public void execute(IEntity entity) {
		this.entity = entity;
		entity.setX(x);
		entity.setY(y);
		
	}


	public void reset() {
		x = 0;
		y = 0;
		
	}

	public boolean equals(ICommand command) {
		boolean result = false;
		
		if (command instanceof SetPositionCommand)
		{
			SetPositionCommand cmd = (SetPositionCommand) command;
			result = (cmd.x == this.x && cmd.y == this.y );
			
		}
		return result;
		
	}


	public void correctionExecute(IEntity entity) {
		IEntity ent = (entity == null) ? this.entity : entity;
		execute(entity);
	}
	
	
 

}
