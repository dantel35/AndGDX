package com.andgdx.pathfinder;

public class SimpleCostFunction<T> implements ICostFunction<T> {

	public float getCost(IPathFinderMap<T> pPathFinderMap, int pFromX,
			int pFromY, int pToX, int pToY, T pEntity) {
		int xdifference = pToX-pFromX;
		int ydifference = pToY -pFromY;
		
		int returnValue = Math.abs(xdifference)+Math.abs(ydifference);
		return returnValue;
	}

}
