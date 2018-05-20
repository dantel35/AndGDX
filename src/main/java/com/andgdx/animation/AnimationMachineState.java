package com.andgdx.animation;

import java.util.Iterator;

import com.badlogic.gdx.utils.Array;

public class AnimationMachineState {
	
	Array<String> states;
	
	public AnimationMachineState(Array<String> states)
	{
		this.states = states;
		states.sort();

	}
	
	public AnimationMachineState(String... states)
	{
		this.states = new Array<String>();
		 for(String state : states){
			 this.states.add(state);
		    }
		this.states.sort();

	}
	
	
	public AnimationMachineState()
	{
		this.states = new Array<String>();
	}
	
	public void addState(String state)
	{
		states.add(state);
		states.sort();
	}
	
	public void removeState(String state)
	{
		states.removeValue(state, false);
		states.sort();

	}
	
	public void setStates(Array<String> states)
	{
		this.states = states;
		states.sort();

	}
	
	public int overlap(AnimationMachineState machineState)
	{
		float match = 0;
		float noMatch = 0;
		
		Array<String> states = (machineState.states.size < this.states.size) ? this.states : machineState.states;
		AnimationMachineState obj = (machineState.states.size > this.states.size) ? this : machineState;
		Iterator<String> iterator = states.iterator();
		String temp;
		while ( iterator.hasNext())
		{
			temp =  iterator.next();
			if (obj.contains(temp))
			{
				match++;
			}
			else
			{
				noMatch++;
			}
			
		}
		
//		int diff = Math.abs(machineState.states.size - this.states.size);
//		noMatch = noMatch + diff;
		
		float result = (100.0f / (match+noMatch)) * match;
		return (int)Math.round(result);
	}
	
	public boolean equals(Object otherMachineState)
	{
		boolean result = false;
		if (otherMachineState instanceof AnimationMachineState)
		{
			AnimationMachineState state = (AnimationMachineState) otherMachineState;
			result = states.equals(state.states);			
		}
		return result;
	}
	
	   @Override
	    public int hashCode() {
	      
	        return states.hashCode();
	    }
	
	
	public boolean contains(String state)
	{
		return states.contains(state, false);
	}
	
	public String toString()
	{
		return states.toString(",");
	}
	
	public boolean isSubset (AnimationMachineState obj)
	{
		boolean result = true;
		String temp;
		Iterator<String> iterator = states.iterator();
		while ( iterator.hasNext())
		{
			temp =  iterator.next();
			if (obj.contains(temp) == false)
			{
				result = false;
				break;
			}
			
		}
		return result;
	}

}
