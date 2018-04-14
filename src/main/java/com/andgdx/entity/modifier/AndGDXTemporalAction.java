package com.andgdx.entity.modifier;

import com.andgdx.entity.modifier.listener.IEntityModifierListener;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;

public abstract class AndGDXTemporalAction extends TemporalAction {
	private boolean firstTimeExec = true;
	private boolean notFinishedYet = true;
	protected IEntityModifierListener modifierListener;
	
	
	protected void update(float percent) {
		
		checkListener(percent);
	}

	private void checkListener(float percent) {
	
		if (percent >= 1 && notFinishedYet ) {
			if(modifierListener != null)
			modifierListener.onFinished();
			
			
			notFinishedYet = false;
		} else if (firstTimeExec  ) {
			if(modifierListener != null)
			modifierListener.onStarted();
			
			
			firstTimeExec = false;
		}

	}
	
	public void setModifierListener(IEntityModifierListener listener) {
		this.modifierListener = listener;
	}
	
	
	public void reset() {
		super.reset();
		modifierListener = null;
		firstTimeExec = true;
		notFinishedYet = true;
	}
	
	public void restart () {
		super.restart();
	}
}
