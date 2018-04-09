package com.andgdx.entity.modifier.listener;

import com.andgdx.entity.IEntity;
import com.andgdx.entity.modifier.PathModifier;

public interface  IPathActionListener {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	public void onPathStarted(final PathModifier pPathModifier, final IEntity pEntity);
	public void onPathWaypointStarted(final PathModifier pPathModifier, final IEntity pEntity, final int pWaypointIndex);
	public void onPathWaypointFinished(final PathModifier pPathModifier, final IEntity pEntity, final int pWaypointIndex);
	public void onPathFinished(final PathModifier pPathModifier, final IEntity pEntity);
}