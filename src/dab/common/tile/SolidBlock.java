package dab.common.tile;

public class SolidBlock extends Tile {
    
    public SolidBlock() {
        super();
        
        setName("dab:tile:solidblock");
        setCenter(0.5f, 0.5f);
        setWidth(1.0f);
        setHeight(1.0f);
        setSolid(true);
    }
    
}
