package dab.server.initialization;

/**
 * ArrayList of Entity classes
 * 
 * @author Cristopher Huerta
 */
import java.util.ArrayList;

import dab.server.entity.Entity;

public class EntityList {
    public static ArrayList<Entity> entityList;
    
    public EntityList() {
        entityList = new ArrayList<Entity>();
    }
    
    public static void register(Entity entity) {
        entityList.add(entity);
    }
}