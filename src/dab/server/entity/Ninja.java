package dab.server.entity;

/**
 * Class for Ninja entity
 * 
 * @author Cristopher Huerta
 */
import dab.server.initialization.EntityList;

public class Ninja extends Entity {
	/*
	 * contructor - default spawn
	 */
	
	public Ninja() {
        
		this.name = "genericNinja";
		this.alive = true;
		this.maxHealth = 50;
		this.attackDamage = 10;
		this.movementSpeed = 0.6;
        
        	EntityList.register(this);
	}
}
