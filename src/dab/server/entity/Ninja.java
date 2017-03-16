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
		this.health = 50;
		this.attack = 10;
		this.speed = 0.6;
        
        	EntityList.register(this);
	}
}
