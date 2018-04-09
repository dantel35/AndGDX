package com.andgdx.pathfinder;

import java.util.LinkedList;





import com.andgdx.entity.IEntity;
import com.andgdx.entity.modifier.Path;
import com.andgdx.grid.IGrid;
import com.andgdx.util.FloatPoint;

public class PathFinderUtil {
	
	public static Path pathFinderPathToModifierPath(PathFinderPath path, IGrid grid, IEntity movingEntity)
	{
		LinkedList<FloatPoint> list = cleanPath(path);
		float currentX = movingEntity.getX();
		float currentY = movingEntity.getY();
		Path returnValue = new Path(list.size()+1);
		
		float smoothReroutingX = currentX - grid.getX(path.getX(0)); // here the lastX is the X of
		float smoothReroutingY = currentY - grid.getY(path.getY(0));
		
		returnValue.to(grid.getX(0)+smoothReroutingX, grid.getY(0) + smoothReroutingY);
		for (FloatPoint p: list)
		{
			returnValue.to(grid.getX((int) p.getX()), grid.getY((int) p.getY()));
			System.out.println("go to x: " + p.getXint() + " go to y: " + p.getYint());
		}
		return returnValue;
		
	}
	
	private static LinkedList<FloatPoint> cleanPath(PathFinderPath path) {

		LinkedList<FloatPoint> bereinigterPath = new LinkedList<FloatPoint>();

		for (int i = 0; i < path.getLength() - 1; i++) {
			System.out.println("CLEAN PATH,  LAUFVARIABLE: " + i);
			if (!(path.getDirectionToNextStep(i).equals(path
					.getDirectionToNextStep(i + 1)))) {
			 
				bereinigterPath.add(new FloatPoint(path.getX(i + 1), path
						.getY(i + 1)));
				System.out.println("Clean path x: " + path.getX(i+1) + " y: " + 
						path.getY(i+1));
			}
		}

		 

		return bereinigterPath;
	}
}
