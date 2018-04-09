package com.andgdx.grid;

import com.andgdx.util.FloatPoint;
import com.badlogic.gdx.math.MathUtils;

public class Grid<T> implements IGrid<T> {

	Object[][] gridArray;
	float width, height;
	int amountOfRows, amountOfColumns;

	float tileWidth, tileHeight;

	public Grid(float width, float height, int amountOfColumns, int amountOfRows) {
		this.width = width;
		this.height = height;
		this.amountOfColumns = amountOfColumns;
		this.amountOfRows = amountOfRows;
		this.tileWidth = (float) width / amountOfColumns;
		this.tileHeight = (float) height / amountOfRows;

		gridArray = new Object[amountOfRows][amountOfColumns];

	}

	/* (non-Javadoc)
	 * @see com.andgdx.grid.IGrid#getAmountOfRows()
	 */
	public int getAmountOfRows() {
		return amountOfRows;
	}

	/* (non-Javadoc)
	 * @see com.andgdx.grid.IGrid#getAmountOfColumns()
	 */
	public int getAmountOfColumns() {
		return amountOfColumns;
	}

	/* (non-Javadoc)
	 * @see com.andgdx.grid.IGrid#getTileWidth()
	 */
	public float getTileWidth() {
		return tileWidth;
	}

	/* (non-Javadoc)
	 * @see com.andgdx.grid.IGrid#getTileHeight()
	 */
	public float getTileHeight() {
		return tileHeight;
	}

	/*
	 * Returns the X coordinate of the columns center
	 */
	/* (non-Javadoc)
	 * @see com.andgdx.grid.IGrid#getX(int)
	 */
	public float getX(int column) {
		float x = (tileWidth / 2f) + column * tileWidth;
		return x;
	}

	/* (non-Javadoc)
	 * @see com.andgdx.grid.IGrid#getElement(int, int)
	 */
	public T getElement(int row, int column) {
		@SuppressWarnings("unchecked")
		T element = (T) gridArray[row][column];
		return element;
	}

	/* (non-Javadoc)
	 * @see com.andgdx.grid.IGrid#getElement(float, float)
	 */
	public T getElement(float x, float y) {
		int row = getRow(y);
		int column = getColumn(x);
		@SuppressWarnings("unchecked")
		T element = (T) gridArray[row][column];
		return element;
	}

	/* (non-Javadoc)
	 * @see com.andgdx.grid.IGrid#addElement(float, float, T)
	 */
	public void addElement(float x, float y, T element) {
		int row = getRow(y);
		int column = getColumn(x);
		gridArray[row][column] = element;
	}

	/* (non-Javadoc)
	 * @see com.andgdx.grid.IGrid#addElement(int, int, T)
	 */
	public void addElement(int row, int column, T element) {
		gridArray[row][column] = element;
	}

	/* (non-Javadoc)
	 * @see com.andgdx.grid.IGrid#getRow(float)
	 */
	public int getRow(float y) {
		int row;

		row = (int) ((y - (tileHeight / 2f)) / tileHeight);

		return row;
	}

	/* (non-Javadoc)
	 * @see com.andgdx.grid.IGrid#getColumn(float)
	 */
	public int getColumn(float x) {
		int row;

		row = (int) ((x - (tileWidth / 2f)) / tileWidth);

		return row;
	}

	/*
	 * Returns the Y coordinate of the rows' center
	 */
	/* (non-Javadoc)
	 * @see com.andgdx.grid.IGrid#getY(int)
	 */
	public float getY(int row) {
		float y = (tileHeight / 2f) + row * tileHeight;

		return y;
	}

	/* (non-Javadoc)
	 * @see com.andgdx.grid.IGrid#getCenterCoordinatesOf(int, int)
	 */
	public FloatPoint getCenterCoordinatesOf(int row, int column) {

		float x = (tileWidth / 2f) + column * tileWidth;
		float y = (tileHeight / 2f) + row * tileHeight;
		// TODO create pool for floatpoints
		FloatPoint point = new FloatPoint(x, y);
		return point;
	}

	private boolean liebeIchSchatzi() {
		return true;
	}

}
