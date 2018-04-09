package com.andgdx.entity.modifier;

import com.andgdx.entity.modifier.listener.ILoopModifierListener;
import com.badlogic.gdx.scenes.scene2d.actions.DelegateAction;

/**
 * Repeats an action a number of times or forever.
 * 
 * @author Nathan Sweet
 */
public class LoopModifier extends DelegateAction {
	static public final int FOREVER = -1;

	private int repeatCount, executedCount;
	protected ILoopModifierListener modifierListener;
	private boolean finished;
	int lastCount = -1;

	protected boolean delegate(float delta) {
		if (executedCount == repeatCount) {
			finished(repeatCount);
			return true;
		}
		else
		{ 
			started(executedCount);
		}
		if (action.act(delta)) {
			finished(executedCount);
			if (finished) {
				finished(repeatCount);
				return true;
			}
			if (repeatCount > 0) {
				executedCount++;

			}
			if (executedCount == repeatCount) {
				finished(repeatCount);
				return true;
			}
			if (action != null)
				action.restart();
		}
		return false;
	}

	private void started(int index) {
		if (modifierListener != null)
		{
		if(index == 0)
		{
			modifierListener.onStarted();
		}
		if(index > lastCount)
		{
			lastCount = executedCount;
			modifierListener.onSubStarted(index);
		}
		}
		
	}

	private void finished(int index) {
		if (modifierListener != null) {
			if(index == repeatCount)
			{
				modifierListener.onFinished();
				
			}
			if(index > lastCount)
			{
				modifierListener.onSubFinished(index);
			}
		}

	}

	 

	public void setModifierListener(ILoopModifierListener listener) {
		this.modifierListener = listener;
	}

	/** Causes the action to not repeat again. */
	public void finish() {
		finished = true;
	}

	public void reset() {
		super.reset();
		modifierListener = null;
		lastCount = -1;
	}

	public void restart() {
		super.restart();
		executedCount = 0;
		lastCount = -1;
		finished = false;

	}

	/** Sets the number of times to repeat. Can be set to {@link #FOREVER}. */
	public void setCount(int count) {
		this.repeatCount = count;
	}

	public int getCount() {
		return repeatCount;
	}
}
