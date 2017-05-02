package dab.common.physics;

import org.joml.Vector2f;

public class AABBMath
{
	public static boolean aabbCollides(AABB aabb1, AABB aabb2)
	{
		if (Math.abs(aabb1.getMidPoint().x - aabb2.getMidPoint().x) >= (aabb1.getWidth()/2 + aabb2.getWidth()/2) ||
				Math.abs(aabb1.getMidPoint().y - aabb2.getMidPoint().y) >= (aabb1.getHeight()/2 + aabb2.getHeight()/2))
		{
			return false;
		}
		return true;
	}
	
	public static void add(AABB aabb, Vector2f offset)
	{
		aabb.getMidPoint().add(offset);
	}
	
	public static void add(AABB aabb, float offsetX, float offsetY)
	{
		aabb.getMidPoint().add(offsetX, offsetY);
	}

}
