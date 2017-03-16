package dab.server.entity;

/**
 * Class for Alien entity
 * 
 * @author Cristopher Huerta
 */
import dab.server.initialization.EntityList;

public class Alien extends Entity {
	/*
	 * contructor - default spawn
	 */

	public Alien() {
       
        this.name = "genericAlien";
        this.alive = true;
        this.maxHealth = 80;
        this.attackDamage = 20;
        this.movementSpeed = 0.4;
        
        EntityList.register(this);
	}
}
