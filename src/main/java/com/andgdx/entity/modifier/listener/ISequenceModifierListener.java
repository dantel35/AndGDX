package com.andgdx.entity.modifier.listener;

public interface ISequenceModifierListener {
	
	/**
	 * Called when one modifier/action out of the sequence is finished
	 */
	public void onSubFinished(int index);
	
	
 
	
	/**
	 * Called when one modifier/action out of the sequence is started
	 */
	public  void onSubStarted(int index);
	
	/**
	 * Called when the whole sequence is finished
	 */
	public void onFinished();
	
	/**
	 * Called when the whole sequence is started
	 */
	public void onStarted();

}
