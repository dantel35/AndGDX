package com.andgdx.entity.modifier;

import com.andgdx.entity.modifier.listener.IPathModifierListener;
import com.andgdx.entity.modifier.listener.SequenceModifierAdapter;
import com.badlogic.gdx.math.Interpolation;

 

public class PathModifier extends SequenceModifier {
	
	private Path mPath;
	private IPathModifierListener mPathModifierListener;
	private SequenceModifier sequenceAction;

	public SequenceModifier getPathSequence(final float pDuration, final Path pPath, final IPathModifierListener pPathModifierListener, final Interpolation pEaseFunction) throws IllegalArgumentException {
		final int pathSize = pPath.getSize();

		if (pathSize < 2) {
			throw new IllegalArgumentException("Path needs at least 2 waypoints!");
		}

		this.mPath = pPath;
		this.mPathModifierListener = pPathModifierListener;

		final MoveToModifier[] moveModifiers = new MoveToModifier[pathSize - 1];

		final float[] coordinatesX = pPath.getCoordinatesX();
		final float[] coordinatesY = pPath.getCoordinatesY();

		final float velocity = pPath.getLength() / pDuration;

		final int modifierCount = moveModifiers.length;
		for (int i = 0; i < modifierCount; i++) {
			final float duration = pPath.getSegmentLength(i) / velocity;
//			moveModifiers[i] = AndGDXActions.moveFromTo(coordinatesX[i], coordinatesY[i], coordinatesX[i + 1], coordinatesY[i + 1],duration, pEaseFunction,null);
			moveModifiers[i] = AndGDXActions.moveTo( coordinatesX[i + 1], coordinatesY[i + 1],duration, pEaseFunction,null);

		}

		/* Create a new SequenceModifier and register the listeners that
		 * call through to mEntityModifierListener and mPathModifierListener. */
		 sequenceAction = AndGDXActions.sequence(new SequenceModifierAdapter(){
			
			public void onSubFinished(int index)
			{
				if(mPathModifierListener != null)
				{
					mPathModifierListener.onPathWaypointFinished(PathModifier.this, index);
				}
			}
	
			public  void onSubStarted(int index)
			{
				if(mPathModifierListener != null)
				{
					mPathModifierListener.onPathWaypointStarted(PathModifier.this, index);
				}
			}
			
			public void onFinished()
			{
				if(mPathModifierListener != null)
				{
					mPathModifierListener.onPathFinished(PathModifier.this);
				}
			}
			public void onStarted()
			{
				if(mPathModifierListener != null)
				{
					mPathModifierListener.onPathStarted(PathModifier.this);
				}
			}
		}, moveModifiers);
		
 
		 
		 return sequenceAction;
	}
	
	public void reset()
	{
		mPathModifierListener=null;
		sequenceAction = null;
		mPath = null;
		
	}
	
	 

}
