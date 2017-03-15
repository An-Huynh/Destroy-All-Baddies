package dab.server.entity;

/**
 * Abstract class for entitites
 * 
 * @author Cristopher Huerta
 */

public abstract class Entity {
	protected String name; //name of entity
	protected boolean alive; //entity alive status
	protected int health;	//health of entity
	protected int attack; //attack power of entity
	protected double xPos, yPos; //x and y positions of entity
	protected double speed; //movement speed of entity
	
	public Entity(){
	}

	/**
	 * performs corresponding attack
	 * and returns bool based on hit or miss
	 *
	 * @return boolean attack success
	 */
	public boolean attack(){
		//to do: performAttack();
		//will return attack success
		return true;
	}

	/**
	 * updates the location of entity
	 * on screen
	 * 
	 */
	public void move(){
		//update location
	}
	
	/**
	 * updates name of entity
	 * (for unique characters, bosses, etc.)
	 * 
	 * @param String name to update with
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * get name of entity
	 * 
	 * @String returns name as string
	 */
	public String getName(){
		return this.name;
	}
}