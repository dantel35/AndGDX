package com.andgdx.entity.modifier.listener;

import com.andgdx.entity.modifier.PathModifier;

public abstract class PathModifierAdapter implements IPathModifierListener{

	public abstract void onPathStarted(PathModifier pPathModifier);

	public abstract void onPathWaypointStarted(PathModifier pPathModifier,
			int pWaypointIndex);

	public abstract void onPathWaypointFinished(PathModifier pPathModifier,
			int pWaypointIndex);

	public abstract void onPathFinished(PathModifier pPathModifier);

 

}
