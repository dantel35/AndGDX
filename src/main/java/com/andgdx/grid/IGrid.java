package com.andgdx.grid;

import com.andgdx.util.FloatPoint;

public interface IGrid<T> {

	public int getAmountOfRows();

	public int getAmountOfColumns();

	public float getTileWidth();

	public float getTileHeight();

	/*
	 * Returns the X coordinate of the columns center
	 */
	public float getX(int column);

	public T getElement(int row, int column);

	public T getElement(float x, float y);

	public void addElement(float x, float y, T element);

	public void addElement(int row, int column, T element);

	public int getRow(float y);

	public int getColumn(float x);

	/*
	 * Returns the Y coordinate of the rows' center
	 */
	public float getY(int row);

	public FloatPoint getCenterCoordinatesOf(int row, int column);

}