package com.andgdx.command;

import com.andgdx.entity.IEntity;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;

public interface ICommand extends Poolable {

	public void execute(IEntity entity);
	public void correctionExecute(IEntity entity);
	public long getID();
	public void setID(long id);
	public void setPool(Pool pool);
	public void setFree();
	public boolean equals(ICommand command);

	
}
