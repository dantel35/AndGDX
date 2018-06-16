package com.andgdx.command;

import com.andgdx.entity.IEntity;
import com.andgdx.entity.modifier.AndGDXActions;
import com.andgdx.entity.modifier.listener.IEntityModifierListener;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

public class GoToCommand extends Command implements ICommand {
	
	public float x, y, duration;
	private IEntity entity;
	public IEntityModifierListener listener;
	
	public GoToCommand()
	{}
	
	public GoToCommand(float x, float y, float duration) {
	this(x,y,duration, null);
	}
	
	public GoToCommand(float x, float y, float duration, IEntityModifierListener listener) {
		super();
		this.x = x;
		this.y = y;
		this.duration = duration;
		this.listener = listener;
	}
	
	public void setFree()
	{
		Pool<GoToCommand> pool = Pools.get(GoToCommand.class);
		pool.free(this);
	}

	public void execute(IEntity entity) {
		this.entity = entity;
		entity.registerEntityModifier(AndGDXActions.moveTo(x, y, duration, listener));
		System.out.println("go to " + x + " " + y + " name: " + entity.getName());
	}

	public void reset() {
		x = 0;
		y = 0;
		duration = 0;
	}

	public boolean equals(ICommand command) {
		boolean result = false;
		if (command instanceof GoToCommand)
		{
			GoToCommand cmd = (GoToCommand) command;
			
			System.out.println(" x " + cmd.x + "  " + this.x  + " y " + cmd.y + " " + this.y 
					+ " duration " + cmd.duration + " " + this.duration );
			
			result = (cmd.x == this.x && cmd.y == this.y && cmd.duration == this.duration );
			
		}
		return result;
	}

	public void correctionExecute(IEntity entity) {
		duration = 0.00001f;
		IEntity ent = (entity == null) ? this.entity : entity;
		execute(ent);
		
	}

	
	
	
	 

}
