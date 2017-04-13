package dab.common.logic;

import dab.common.physics.AABB;

public class Collision {
	
	public static boolean testCollision(AABB aabb, AABB aabb2) {
		if (Math.abs(aabb.getCenter().x - aabb2.getCenter().x) >= (aabb.getWidth()/2 + aabb2.getWidth()/2) ||
				Math.abs(aabb.getCenter().y - aabb2.getCenter().y) >= (aabb.getHeight()/2 + aabb2.getHeight()/2)) {
			return false;
		}
		return true;
	}
	
}
