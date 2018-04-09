package com.andgdx.update;

import java.util.ArrayList;

 

/**
 * (c) 2010 Nicolas Gramlich
 * (c) 2011 Zynga Inc.
 *
 * @author Nicolas Gramlich
 * @since 10:24:39 - 18.06.2010
 */
public class RunnableHandler implements IUpdateHandler {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private final ArrayList<Runnable> mRunnables = new ArrayList<Runnable>();

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	 
	public synchronized void onUpdate(final float pSecondsElapsed) {
		final ArrayList<Runnable> runnables = this.mRunnables;
		final int runnableCount = runnables.size();
		for (int i = runnableCount - 1; i >= 0; i--) {
			runnables.get(i).run();
		}
		runnables.clear();
	}

	 
	public synchronized void reset() {
		this.mRunnables.clear();
	}

	// ===========================================================
	// Methods
	// ===========================================================

	public synchronized void postRunnable(final Runnable pRunnable) {
		this.mRunnables.add(pRunnable);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
