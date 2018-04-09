package com.andgdx.entity.modifier.listener;

import com.andgdx.entity.modifier.PathModifier;

public interface  IPathModifierListener {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	public void onPathStarted(final PathModifier pPathModifier );
	public void onPathWaypointStarted(final PathModifier pPathModifier,   final int pWaypointIndex);
	public void onPathWaypointFinished(final PathModifier pPathModifier,   final int pWaypointIndex);
	public void onPathFinished(final PathModifier pPathModifier);
}
