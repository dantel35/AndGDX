package com.andgdx.util.list;



/**
 * This implementation is particular useful/efficient for enter/poll operations of elements that need to be sorted by natural order instead of the order they are queue.
 * Its {@link java.util.List} like behavior performs better than a plain {@link java.util.ArrayList}, since it automatically shift the contents of its internal Array only when really necessary.
 * Besides sparse allocations to increase the size of the internal Array, {@link SortedList} is allocation free (unlike the {@link java.util.LinkedList} family).
 *
 * (c) 2012 Zynga Inc.
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @author Greg Haynes
 * @since 15:02:40 - 24.02.2012
 */
public class UniqueList<T extends Comparable<T>> implements ISortedList<T> {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private final IList<T> mList;

	// ===========================================================
	// Constructors
	// ===========================================================

	public UniqueList(final IList<T> pList) {
		this.mList = pList;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	
	public boolean isEmpty() {
		return this.mList.isEmpty();
	}

	
	public T get(final int pIndex) throws IndexOutOfBoundsException {
		return this.mList.get(pIndex);
	}

	
	@Deprecated
	public void set(final int pIndex, final T pItem) throws IndexOutOfBoundsException {
		this.mList.set(pIndex, pItem);
	}

	
	public int indexOf(final T pItem) {
		return this.mList.indexOf(pItem);
	}

	
	@Deprecated
	public void add(final int pIndex, final T pItem) {
		final int index = this.indexOf(pItem);
		if (index < 0) {
			this.mList.add(pItem);
		}
	}

	
	public void add(final T pItem) {
		final int index = this.indexOf(pItem);
		if (index < 0) {
			this.mList.add(ListUtils.encodeInsertionIndex(index), pItem);
		}
	}

	
	public T removeFirst() {
		return this.mList.removeFirst();
	}

	
	public T removeLast() {
		return this.mList.removeLast();
	}

	
	public boolean remove(final T pItem) {
		return this.mList.remove(pItem);
	}

	
	public T remove(final int pIndex) {
		return this.mList.remove(pIndex);
	}

	
	public int size() {
		return this.mList.size();
	}

	
	public void clear() {
		this.mList.clear();
	}

	
	public String toString() {
		return ListUtils.toString(this);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
