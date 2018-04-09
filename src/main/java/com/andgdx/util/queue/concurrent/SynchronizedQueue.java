package com.andgdx.util.queue.concurrent;

import com.andengine.util.queue.IQueue;
import com.andgdx.util.list.ListUtils;

/**
 * (c) 2012 Zynga Inc.
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 14:23:50 - 01.02.2012
 */
public class SynchronizedQueue<T> implements IQueue<T> {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private final IQueue<T> mQueue;

	// ===========================================================
	// Constructors
	// ===========================================================

	public SynchronizedQueue(final IQueue<T> pQueue) {
		this.mQueue = pQueue;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	
	public synchronized boolean isEmpty() {
		return this.mQueue.isEmpty();
	}

	
	public synchronized T get(final int pIndex) throws IndexOutOfBoundsException {
		return this.mQueue.get(pIndex);
	}

	
	public synchronized void set(final int pIndex, final T pItem) throws IndexOutOfBoundsException {
		this.mQueue.set(pIndex, pItem);
	}

	
	public synchronized int indexOf(final T pItem) {
		return this.mQueue.indexOf(pItem);
	}

	
	public synchronized void add(final T pItem) {
		this.mQueue.add(pItem);
	}

	
	public synchronized void add(final int pIndex, final T pItem) throws IndexOutOfBoundsException {
		this.mQueue.add(pIndex, pItem);
	}

	
	public synchronized T peek() {
		return this.mQueue.peek();
	}

	
	public synchronized T poll() {
		return this.mQueue.poll();
	}

	
	public synchronized void enter(final T pItem) {
		this.mQueue.enter(pItem);
	}

	
	public synchronized void enter(final int pIndex, final T pItem) throws IndexOutOfBoundsException {
		this.mQueue.enter(pIndex, pItem);
	}

	
	public synchronized T removeFirst() {
		return this.mQueue.removeFirst();
	}

	
	public synchronized T removeLast() {
		return this.mQueue.removeLast();
	}

	
	public synchronized boolean remove(final T pItem) {
		return this.mQueue.remove(pItem);
	}

	
	public synchronized T remove(final int pIndex) throws IndexOutOfBoundsException {
		return this.mQueue.remove(pIndex);
	}

	
	public synchronized int size() {
		return this.mQueue.size();
	}

	
	public synchronized void clear() {
		this.mQueue.clear();
	}

	
	public synchronized String toString() {
		return ListUtils.toString(this);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
