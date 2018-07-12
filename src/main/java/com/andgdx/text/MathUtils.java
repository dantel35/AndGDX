package com.andgdx.text;

import com.badlogic.gdx.utils.FloatArray;

/** MathUtils libGDX backend
 *  @author dermetfan
 *  @since 0.5.0
 *  @see TypeWriterMathUtils.dermetfan.utils.math.MathUtils */
public class MathUtils extends com.andgdx.text.util.TypeWriterMathUtils {

	/** @see TypeWriterMathUtils.dermetfan.utils.math.MathUtils#clamp(float[], int, int, float, float) */
	public static FloatArray clamp(FloatArray values, float min, float max) {
		clamp(values.items, 0, values.size, min, max);
		return values;
	}

	/** @see TypeWriterMathUtils.dermetfan.utils.math.MathUtils#abs(float[], int, int) */
	public static FloatArray abs(FloatArray values) {
		abs(values.items, 0, values.size);
		return values;
	}

	/** @see TypeWriterMathUtils.dermetfan.utils.math.MathUtils#add(float[], int, int, float) */
	public static FloatArray add(FloatArray values, float value) {
		add(values.items, 0, values.size, value);
		return values;
	}

	/** @see TypeWriterMathUtils.dermetfan.utils.math.MathUtils#sub(float[], int, int, float) */
	public static FloatArray sub(FloatArray values, float value) {
		sub(values.items, 0, values.size, value);
		return values;
	}

	/** @see TypeWriterMathUtils.dermetfan.utils.math.MathUtils#mul(float[], int, int, float) */
	public static FloatArray mul(FloatArray values, float factor) {
		mul(values.items, 0, values.size, factor);
		return values;
	}

	/** @see TypeWriterMathUtils.dermetfan.utils.math.MathUtils#div(float[], int, int, float) */
	public static FloatArray div(FloatArray values, float divisor) {
		div(values.items, 0, values.size, divisor);
		return values;
	}

	/** @see TypeWriterMathUtils.dermetfan.utils.math.MathUtils#sum(float[], int, int) */
	public static float sum(FloatArray values) {
		return sum(values.items, 0, values.size);
	}

	/** @see TypeWriterMathUtils.dermetfan.utils.math.MathUtils#amplitude2(float[], int, int) */
	public static float amplitude2(FloatArray f) {
		return amplitude2(f.items, 0, f.size);
	}

	/** @see TypeWriterMathUtils.dermetfan.utils.math.MathUtils#max(float[], int, int) */
	public static float max(FloatArray floats) {
		return max(floats.items, 0, floats.size);
	}

	/** @see TypeWriterMathUtils.dermetfan.utils.math.MathUtils#min(float[], int, int) */
	public static float min(FloatArray floats) {
		return min(floats.items, 0, floats.size);
	}

	/** @see TypeWriterMathUtils.dermetfan.utils.math.MathUtils#nearest(float, float, float[], int, int) */
	public static float nearest(float value, float range, FloatArray values) {
		return nearest(value, range, values.items, 0, values.size);
	}

	/** @see TypeWriterMathUtils.dermetfan.utils.math.MathUtils#nearest(float, float[], int, int) */
	public static float nearest(float value, FloatArray values) {
		return nearest(value, values.items, 0, values.size);
	}

	/** @see TypeWriterMathUtils.dermetfan.utils.math.MathUtils#scale(float[], int, int, float, float) */
	public static FloatArray scale(FloatArray values, float min, float max) {
		scale(values.items, 0, values.size, min, max);
		return values;
	}

}
