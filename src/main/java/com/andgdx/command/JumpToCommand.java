package com.andgdx.command;

import com.andgdx.entity.IEntity;
import com.andgdx.entity.modifier.AndGDXActions;
import com.andgdx.entity.modifier.listener.IEntityModifierListener;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

public class JumpToCommand extends Command implements ICommand {
	
	public float x, y, height,duration;
	private IEntity entity;
	public IEntityModifierListener listener;
	
	public JumpToCommand()
	{}
	
	public JumpToCommand(float x, float y, float height,float duration) {
	this(x,y,height,duration, null);
	}
	
	public JumpToCommand(float x, float y,float height, float duration, IEntityModifierListener listener) {
		super();
		this.x = x;
		this.y = y;
		this.height = height;
		this.duration = duration;
		this.listener = listener;
	}
	
	public void setFree()
	{
		Pool<JumpToCommand> pool = Pools.get(JumpToCommand.class);
		pool.free(this);
	}

	public void execute(IEntity entity) {
		this.entity = entity;
		entity.registerEntityModifier(AndGDXActions.jumpTo(x, y, height,duration, listener));
		System.out.println("jump to x: " + x + " y: " + y + " name: " + entity.getName());
	}

	public void reset() {
		x = 0;
		y = 0;
		height = 0;
		duration = 0;
	}

	public boolean equals(ICommand command) {
		boolean result = false;
		if (command instanceof JumpToCommand)
		{
			JumpToCommand cmd = (JumpToCommand) command;
			
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
