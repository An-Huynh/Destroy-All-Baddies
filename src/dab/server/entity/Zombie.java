package dab.server.entity;

/**
 * Class for Zombie entity
 * 
 * @author Cristopher Huerta
 */
import dab.server.resource.EntityList;

public class Zombie extends Entity {
	/*
	 *contructor - default spawn
	 */
	public Zombie() {
		this.name = "genericZombie";
		this.alive = true;
		this.maxHealth = 30;
		this.attackDamage = 5;
		this.movementSpeed = 0.2;
        
        	EntityList.register(this);
	}
}
