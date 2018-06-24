package com.andgdx.util;

import com.badlogic.gdx.math.Vector2;

public class AndGDXMathUtils {

	/**
	 * Clumsy implementation.
	 * @param fromX
	 * @param fromY
	 * @param toX
	 * @param toY
	 * @return
	 */
	public static float calculateRotationAngle(float fromX, float fromY, float toX, float toY) {

		float alpha=0;
		Vector2 vector = new Vector2(toX-fromX, toY-fromY);
		alpha  = vector.angle();


		return alpha;
	}
	
}
