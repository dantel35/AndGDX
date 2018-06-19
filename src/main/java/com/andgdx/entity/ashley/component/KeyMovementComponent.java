package com.andgdx.entity.ashley.component;

import com.andgdx.command.UserKeyInput;

public class KeyMovementComponent extends AndGDXEntityComponent{
	
	
	/**
	 * KeyMovementSystem will listen for this code and interpret it as moving north.
	 * Default value is UserKeyInput.UP, change this if you want to map another key to this action.
	 */
	public static int MOVE_NORTH = UserKeyInput.UP;
	
	
	
	/**
	 * KeyMovementSystem will listen for this code and interpret it as moving east.
	 * Default value is UserKeyInput.RIGHT, change this if you want to map another key to this action.
	 */
	public static int MOVE_EAST = UserKeyInput.RIGHT;
	
	
	
	/**
	 * KeyMovementSystem will listen for this code and interpret it as moving south.
	 * Default value is UserKeyInput.DOWN, change this if you want to map another key to this action.
	 */
	public static int MOVE_SOUTH = UserKeyInput.DOWN;
	
	
	/**
	 * KeyMovementSystem will listen for this code and interpret it as moving west.
	 * Default value is UserKeyInput.LEFT, change this if you want to map another key to this action.
	 */
	public static int MOVE_WEST = UserKeyInput.LEFT;

}
