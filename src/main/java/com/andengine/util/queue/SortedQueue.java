package com.andengine.util.queue;

import com.andgdx.util.list.SortedList;

/**
 * (c) 2012 Zynga Inc.
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 17:17:33 PM - 03.022012
 */
public class SortedQueue<T extends Comparable<T>> extends SortedList<T> implements ISortedQueue<T> {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public SortedQueue(final IQueue<T> pQueue) {
		super(pQueue);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	public T peek() {
		if (this.isEmpty()) {
			return null;
		} else {
			return this.get(0);
		}
	}

	public T poll() {
		if (this.isEmpty()) {
			return null;
		} else {
			return this.remove(0);
		}
	}

	public void enter(final T pItem) {
		this.add(pItem);
	}

	@Deprecated
	public void enter(final int pIndex, final T pItem) throws IndexOutOfBoundsException {
		this.add(pIndex, pItem);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
