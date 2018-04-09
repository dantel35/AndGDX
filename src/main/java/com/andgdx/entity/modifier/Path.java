package com.andgdx.entity.modifier;
/**
 * (c) 2010 Nicolas Gramlich
 * (c) 2011 Zynga Inc.
 *
 * @author Nicolas Gramlich
 * @since 16:50:02 - 16.06.2010
 */
public class Path {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private final float[] mXs;
	private final float[] mYs;

	private int mIndex;
	private boolean mLengthChanged;
	private float mLength;

	// ===========================================================
	// Constructors
	// ===========================================================

	public Path(final int pLength) {
		this.mXs = new float[pLength];
		this.mYs = new float[pLength];

		this.mIndex = 0;
		this.mLengthChanged = false;
	}

	public Path(final float[] pCoordinatesX, final float[] pCoordinatesY) throws IllegalArgumentException {
		if (pCoordinatesX.length != pCoordinatesY.length) {
			throw new IllegalArgumentException("Coordinate-Arrays must have the same length.");
		}

		this.mXs = pCoordinatesX;
		this.mYs = pCoordinatesY;

		this.mIndex = pCoordinatesX.length;
		this.mLengthChanged = true;
	}

	public Path(final Path pPath) {
		final int size = pPath.getSize();
		this.mXs = new float[size];
		this.mYs = new float[size];

		System.arraycopy(pPath.mXs, 0, this.mXs, 0, size);
		System.arraycopy(pPath.mYs, 0, this.mYs, 0, size);

		this.mIndex = pPath.mIndex;
		this.mLengthChanged = pPath.mLengthChanged;
		this.mLength = pPath.mLength;
	}

	public Path deepCopy() {
		return new Path(this);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public Path to(final float pX, final float pY) {
		this.mXs[this.mIndex] = pX;
		this.mYs[this.mIndex] = pY;

		this.mIndex++;

		this.mLengthChanged = true;

		return this;
	}

	public Path to(final int pIndex) {
		return this.to(this.getX(pIndex), this.getY(pIndex));
	}

	private float getX(final int pIndex) {
		return this.mXs[pIndex];
	}

	private float getY(final int pIndex) {
		return this.mYs[pIndex];
	}

	public float[] getCoordinatesX() {
		return this.mXs;
	}

	public float[] getCoordinatesY() {
		return this.mYs;
	}

	public int getSize() {
		return this.mXs.length;
	}

	public float getLength() {
		if (this.mLengthChanged) {
			this.updateLength();
		}
		return this.mLength;
	}

	public float getSegmentLength(final int pSegmentIndex) {
		final float[] coordinatesX = this.mXs;
		final float[] coordinatesY = this.mYs;

		final int nextSegmentIndex = pSegmentIndex + 1;

		final float dx = coordinatesX[pSegmentIndex] - coordinatesX[nextSegmentIndex];
		final float dy = coordinatesY[pSegmentIndex] - coordinatesY[nextSegmentIndex];

		return (float) Math.sqrt(dx * dx + dy * dy);
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	private void updateLength() {
		float length = 0.0f;

		for (int i = this.mIndex - 2; i >= 0; i--) {
			length += this.getSegmentLength(i);
		}
		this.mLength = length;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}