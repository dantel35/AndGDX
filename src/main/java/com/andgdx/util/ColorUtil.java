package com.andgdx.util;

import com.badlogic.gdx.graphics.Color;

public class ColorUtil  {
	



	public static com.badlogic.gdx.graphics.Color mix(final Color pColorA, final float pPercentageA, final Color pColorB, final float pPercentageB, Color resultingColorReference) {
		final float red = (pColorA.r * pPercentageA) + (pColorB.r * pPercentageB);
		final float green = (pColorA.g * pPercentageA) + (pColorB.g * pPercentageB);
		final float blue = (pColorA.b * pPercentageA) + (pColorB.b * pPercentageB);
		final float alpha = (pColorA.a * pPercentageA) + (pColorB.a * pPercentageB);

//		this.set(red, green, blue, alpha);
		return resultingColorReference.set(red,green,blue,alpha);
	}
}
