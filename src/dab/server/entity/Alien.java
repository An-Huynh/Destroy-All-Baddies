package dab.server.entity;

/**
 * Class for Alien entity
 * 
 * @author Cristopher Huerta
 */
import dab.server.initialization.EntityList;

public class Alien extends Entity {
	/*contructor - default spawn
	 *
	 *@param double x position
	 *@param double y position
	 *
	 */
	public Alien(){
		Alien(0.0,0.0);
	}
	
	public Alien(double xPos, double yPos) {
		this.xPos = xPos;
        this.yPos = yPos;
        
        this.name = "genericAlien";
        this.alive = true;
        this.health = 80;
        this.attack = 20;
        this.speed = 0.4;
        
        EntityList.register(this);
	}
}