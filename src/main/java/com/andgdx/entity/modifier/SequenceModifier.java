package com.andgdx.entity.modifier;

import com.andgdx.entity.modifier.listener.ISequenceModifierListener;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.utils.Pool;

public class SequenceModifier extends ParallelModifier {
	private int index;
	private int lastIndex = -1;
	ISequenceModifierListener listener;

	public SequenceModifier() {
	}

	public SequenceModifier(Action action1) {
		addAction(action1);
	}

	public SequenceModifier(Action action1, Action action2) {
		addAction(action1);
		addAction(action2);
	}

	public SequenceModifier(Action action1, Action action2, Action action3) {
		addAction(action1);
		addAction(action2);
		addAction(action3);
	}

	public SequenceModifier(Action action1, Action action2, Action action3,
			Action action4) {
		addAction(action1);
		addAction(action2);
		addAction(action3);
		addAction(action4);
	}

	public SequenceModifier(Action action1, Action action2, Action action3,
			Action action4, Action action5) {
		addAction(action1);
		addAction(action2);
		addAction(action3);
		addAction(action4);
		addAction(action5);
	}

	public void setListener(ISequenceModifierListener listener) {
		this.listener = listener;
	}

	public boolean act(float delta) {
		if (index >= actions.size) {
			sequenceFinished();
			return true;
		}
		checkActionStarted();
		Pool pool = getPool();
		setPool(null); // Ensure this action can't be returned to the pool while
						// executings.
		try {
			if (actions.get(index).act(delta)) {
				if (actor == null)
					return true; // This action was removed.
				checkActionFinished();
				index++;
				if (index >= actions.size)
				{
					sequenceFinished();
					return true;
				}
			}
			return false;
		} finally {
			setPool(pool);
		}
	}

	private void checkActionStarted() {
		if (listener != null)
			if (index > lastIndex) {
				if (index == 0) {
					listener.onStarted();
				}
				lastIndex = index;
				listener.onSubStarted(index);
			}
	}

	private void sequenceFinished() {
		if (listener != null)

		{

			listener.onFinished();
		}
	}

	private void checkActionFinished() {
		if (listener != null)

		{

			listener.onSubFinished(index);
		}
	}

	public void reset()
	{
		super.reset();
		listener=null;
	}
	
	public void restart() {
		super.restart();
		index = 0;
		lastIndex = -1;
		listener=null;
	}
}
