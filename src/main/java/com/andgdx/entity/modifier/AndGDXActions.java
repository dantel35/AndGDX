package com.andgdx.entity.modifier;

import com.andgdx.entity.modifier.listener.IEntityModifierListener;
import com.andgdx.entity.modifier.listener.ILoopModifierListener;
import com.andgdx.entity.modifier.listener.IPathModifierListener;
import com.andgdx.entity.modifier.listener.ISequenceModifierListener;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

/**
 * This Class provides pooled actions / modifiers.
 * 
 * @author Daniel Knittel
 * @author Janina Knittel
 *
 */
public class AndGDXActions {

	/** Returns a new or pooled action of the specified type. */
	static public <T extends Action> T pool(Class<T> type) {
		Pool<T> pool = Pools.get(type);
		T action = pool.obtain();
		action.setPool(pool);
		return action;
	}

	static public JumpToModifier jumpTo(float x, float y, float height,
			float duration) {

		return jumpTo(x, y, height, duration, null, null);
	}

	static public JumpToModifier jumpTo(float x, float y, float height,
			float duration, IEntityModifierListener listener) {

		return jumpTo(x, y, height, duration, null, listener);
	}

	static public JumpToModifier jumpTo(float x, float y, float height,
			float duration, Interpolation interpolation) {

		return jumpTo(x, y, height, duration, interpolation, null);
	}

	static public JumpToModifier jumpTo(float x, float y, float height,
			float duration, Interpolation interpolation,
			IEntityModifierListener listener) {
		JumpToModifier action = pool(JumpToModifier.class);
		action.setPosition(x, y);
		action.setDuration(duration);
		action.setJumpHeight(height);
		action.setInterpolation(interpolation);
		action.setModifierListener(listener);
		return action;
	}

	static public MoveToModifier moveTo(float x, float y, float duration) {
		return AndGDXActions.moveTo(x, y, duration, null, null);
	}

	static public MoveToModifier moveTo(float x, float y, float duration,
			IEntityModifierListener listener) {
		return moveTo(x, y, duration, null, listener);
	}

	static public MoveToModifier moveTo(float x, float y, float duration,
			Interpolation interpolation) {
		return moveTo(x, y, duration, interpolation, null);
	}

	static public MoveToModifier moveTo(float x, float y, float duration,
			Interpolation interpolation, IEntityModifierListener listener) {
		MoveToModifier action = pool(MoveToModifier.class);
		action.setPosition(x, y);
		action.setDuration(duration);
		action.setInterpolation(interpolation);
		action.setModifierListener(listener);
		return action;
	}

	static public MoveToModifier moveFromTo(float fromX, float fromY,
			float toX, float toY, float duration) {
		return AndGDXActions.moveFromTo(fromX, fromY, toX, toY, duration, null,
				null);
	}

	static public MoveToModifier moveFromTo(float fromX, float fromY,
			float toX, float toY, float duration,
			IEntityModifierListener listener) {
		return moveFromTo(fromX, fromY, toX, toY, duration, null, listener);
	}

	static public MoveToModifier moveFromTo(float fromX, float fromY,
			float toX, float toY, float duration, Interpolation interpolation) {
		return moveFromTo(fromX, fromY, toX, toY, duration, interpolation, null);
	}

	static public MoveToModifier moveFromTo(float fromX, float fromY,
			float toX, float toY, float duration, Interpolation interpolation,
			IEntityModifierListener listener) {
		MoveToModifier action = pool(MoveToModifier.class);
		action.setPosition(toX, toY);
		action.setFromPosition(fromX, fromY);
		action.setDuration(duration);
		action.setInterpolation(interpolation);
		action.setModifierListener(listener);
		return action;
	}

	static public SequenceModifier movePath(final float pDuration,
			final Path pPath) {
		return movePath(pDuration, pPath, null, null);
	}

	static public SequenceModifier movePath(final float pDuration,
			final Path pPath, final IPathModifierListener pPathModifierListener) {
		return movePath(pDuration, pPath, null, pPathModifierListener);
	}

	static public SequenceModifier movePath(final float pDuration,
			final Path pPath, final Interpolation pEaseFunction) {
		return movePath(pDuration, pPath, pEaseFunction, null);
	}

	static public SequenceModifier movePath(final float pDuration,
			final Path pPath, final Interpolation pEaseFunction,
			final IPathModifierListener pPathModifierListener) {
		PathModifier action = pool(PathModifier.class);
		SequenceModifier mod = action.getPathSequence(pDuration, pPath,
				pPathModifierListener, pEaseFunction);
		return mod;
	}

	static public SequenceModifier sequence(Action... actions) {
		return sequence(null, actions);
	}

	static public SequenceModifier sequence(ISequenceModifierListener listener,
			Action... actions) {
		SequenceModifier action = pool(SequenceModifier.class);
		for (int i = 0, n = actions.length; i < n; i++) {
			action.addAction(actions[i]);
		}

		action.setListener(listener);
		return action;
	}

	static public RotateToModifier rotateTo(float rotation, float duration) {
		return rotateTo(rotation, duration, null, null);
	}

	static public RotateToModifier rotateTo(float rotation, float duration,
			IEntityModifierListener listener) {
		return rotateTo(rotation, duration, null, listener);
	}

	static public RotateToModifier rotateTo(float rotation, float duration,
			Interpolation interpolation) {
		return rotateTo(rotation, duration, interpolation, null);
	}

	static public RotateToModifier rotateTo(float rotation, float duration,
			Interpolation interpolation, IEntityModifierListener listener) {
		RotateToModifier action = pool(RotateToModifier.class);
		action.setRotation(rotation);
		action.setDuration(duration);
		action.setInterpolation(interpolation);
		action.setModifierListener(listener);
		return action;
	}

	/** Rotates the actor instantly. */
	static public RotateByModifier rotateBy(float rotationAmount) {
		return rotateBy(rotationAmount, 0, null, null);
	}

	static public RotateByModifier rotateBy(float rotationAmount, float duration) {
		return rotateBy(rotationAmount, duration, null, null);
	}

	static public RotateByModifier rotateBy(float rotationAmount,
			float duration, IEntityModifierListener listener) {
		return rotateBy(rotationAmount, duration, null, listener);
	}

	static public RotateByModifier rotateBy(float rotationAmount,
			float duration, Interpolation interpolation) {
		return rotateBy(rotationAmount, duration, interpolation, null);
	}

	static public RotateByModifier rotateBy(float rotationAmount,
			float duration, Interpolation interpolation,
			IEntityModifierListener listener) {
		RotateByModifier action = pool(RotateByModifier.class);
		action.setAmount(rotationAmount);
		action.setDuration(duration);
		action.setInterpolation(interpolation);
		return action;
	}

	/**
	 * Transitions from the alpha at the time this action starts to an alpha of
	 * 0.
	 */
	static public AlphaModifier fadeOut(float duration) {
		return changeAlphaTo(0, duration, null, null);
	}

	/**
	 * Transitions from the alpha at the time this action starts to an alpha of
	 * 1.
	 */
	static public AlphaModifier fadeIn(float duration) {
		return changeAlphaTo(1, duration, null, null);
	}

	/**
	 * Transitions from the alpha at the time this action starts to the
	 * specified alpha.
	 */
	static public AlphaModifier changeAlphaTo(float alpha, float duration) {
		return changeAlphaTo(alpha, duration, null, null);
	}

	/**
	 * Transitions from the alpha at the time this action starts to the
	 * specified alpha.
	 */
	static public AlphaModifier changeAlphaTo(float alpha, float duration,
			IEntityModifierListener listener) {
		return changeAlphaTo(alpha, duration, null, listener);
	}

	/**
	 * Transitions from the alpha at the time this action starts to the
	 * specified alpha.
	 */
	static public AlphaModifier changeAlphaTo(float alpha, float duration,
			Interpolation interpolation) {
		return changeAlphaTo(alpha, duration, interpolation, null);
	}

	/**
	 * Transitions from the alpha at the time this action starts to the
	 * specified alpha.
	 */
	static public AlphaModifier changeAlphaTo(float alpha, float duration,
			Interpolation interpolation, IEntityModifierListener listener) {
		AlphaModifier action = pool(AlphaModifier.class);
		action.setAlpha(alpha);
		action.setDuration(duration);
		action.setInterpolation(interpolation);
		action.setModifierListener(listener);
		return action;
	}

	static public ScaleToModifier scaleTo(float x, float y, float duration) {
		return scaleTo(x, y, duration, null, null);
	}

	static public ScaleToModifier scaleTo(float x, float y, float duration,
			IEntityModifierListener listener) {
		return scaleTo(x, y, duration, null, listener);
	}

	static public ScaleToModifier scaleTo(float x, float y, float duration,
			Interpolation interpolation) {
		return scaleTo(x, y, duration, interpolation, null);
	}

	static public ScaleToModifier scaleTo(float x, float y, float duration,
			Interpolation interpolation, IEntityModifierListener listener) {
		ScaleToModifier action = pool(ScaleToModifier.class);
		action.setScale(x, y);
		action.setDuration(duration);
		action.setInterpolation(interpolation);
		action.setModifierListener(listener);
		return action;
	}

	static public ScaleByModifier scaleBy(float amountX, float amountY,
			float duration) {
		return scaleBy(amountX, amountY, duration, null, null);
	}

	static public ScaleByModifier scaleBy(float amountX, float amountY,
			float duration, Interpolation interpolation,
			IEntityModifierListener listener) {
		ScaleByModifier action = pool(ScaleByModifier.class);
		action.setAmount(amountX, amountY);
		action.setDuration(duration);
		action.setInterpolation(interpolation);
		action.setModifierListener(listener);
		return action;
	}

	static public ParallelModifier parallel(Action... actions) {
		ParallelModifier action = pool(ParallelModifier.class);
		for (int i = 0, n = actions.length; i < n; i++)
			action.addAction(actions[i]);
		return action;
	}

	static public LoopModifier loop(Action actionToRepeat) {
		return repeat(LoopModifier.FOREVER, actionToRepeat, null);
	}

	static public LoopModifier repeat(int count, Action actionToRepeat) {
		return repeat(count, actionToRepeat, null);

	}

	static public LoopModifier repeat(int count, Action actionToRepeat,
			ILoopModifierListener listener) {
		LoopModifier action = pool(LoopModifier.class);
		action.setCount(count);
		action.setAction(actionToRepeat);
		action.setModifierListener(listener);
		return action;
	}
	
	
	
	
	
	
	
	
	
	/** Transitions from the color at the time this action starts to the specified color. */
	static public ColorModifier color(Color color, float duration) {
		return color(color, duration, null,null);
	}
	/** Transitions from the color at the time this action starts to the specified color. */
	static public ColorModifier color(Color color, float duration, Interpolation interpolation)
	{
		return color(color, duration, interpolation, null);
	}
	
	/** Transitions from the color at the time this action starts to the specified color. */
	static public ColorModifier color(Color color, float duration, IEntityModifierListener listener)
	{
		return color(color, duration, null, listener);
	}

	/** Transitions from the color at the time this action starts to the specified color. */
	static public ColorModifier color(Color color, float duration, Interpolation interpolation, IEntityModifierListener listener) {
		ColorModifier action = pool(ColorModifier.class);
		action.setEndColor(color);
		action.setDuration(duration);
		action.setInterpolation(interpolation);
		action.setModifierListener(listener);
		return action;
	}
	
	
	/**
	 * Returns a delay action you can put in a sequence if you want to have a pause in there.
	 * @param duration
	 * @return
	 */
	static public DelayModifier delay (float duration) {
		DelayModifier action = pool(DelayModifier.class);
		action.setDuration(duration);
		return action;
	}

	/**
	 * Delays the specified action by the given duration.
	 * @param duration
	 * @param delayedAction
	 * @return
	 */
	static public DelayModifier delay (float duration, Action delayedAction) {
		DelayModifier action = pool(DelayModifier.class);
		action.setDuration(duration);
		action.setAction(delayedAction);
		return action;
	}

}
