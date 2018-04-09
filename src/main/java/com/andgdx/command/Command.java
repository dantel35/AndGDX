package com.andgdx.command;

import com.badlogic.gdx.utils.Pool;

public abstract class Command implements ICommand {
	
	
	private long id;
	private Pool pool;
	
	
	 
	
	public void setFree()
	{
		getPool().free(this);
	}
	
	private Pool getPool()
	{
		return pool;
	}
	
	public void setPool(Pool pool)
	{
		this.pool = pool;
	}
	
	public long getID() {
		return id;
	}

	public void setID(long id) {
		this.id = id;
	}

}
