package dab.server.entity;

/**
 * Class for Zombie entity
 * 
 * @author Cristopher Huerta
 */
import dab.server.initialization.EntityList;

public class Zombie extends Entity {
	/*contructor - default spawn
	 *
	 *@param double x position
	 *@param double y position
	 *
	 */
	public Zombie(){
		Zombie(0.0,0.0);
	}
	
	public Zombie(double xPos, double yPos) {
		this.xPos = xPos;
        this.yPos = yPos;
        
        this.name = "genericZombie";
        this.alive = true;
        this.health = 30;
        this.attack = 5;
        this.speed = 0.2;
        
        EntityList.register(this);
	}
}
