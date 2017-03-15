//this class is all the actors/objects in the game. 
//NOT FINISHED
******************************************************

public abstract class Block
{
	int xPosition;
	int yPosition;
	Boolean isBad;
	Boolean visible;
	Image image;
	
	//not yet to finish
	

	public Block()
	{
	}
	
	public void action()
	{
	}
	
	public int getX()
	{
		return xPosition;
	}
	
	public int getY()
	{
		return yPosition;
	}
	
	public boolean isBadGuy()
	{
		return isBad;
	}
	
	public void setToBadGuy(Boolean badOrNot)
	{
	}

	public int getDirection()
	{
	}
	
	public void setDirection()
	{
	}
	
	public void turnTowards(int x, int y)
	{
	
	}
	
	public boolean isAtEdge()
	{
	}
	
	public void setLocation()
	{
	}
	
	public void move(int distance)
	{
	}
	
	public void turn(int amount)
	{
	}
	
	public Map getMap()
	{
	}
	
	protected void addedToMap(Map map)
	{
	}
	
	public void setImage(String imageFile)
	{
	}
	
	protected boolean intersects(Block otherBlock)
	{
	}
	
	protected <A> java.util.List<A> getNeighbours(int distance,
                                              boolean diagonal,
                                              java.lang.Class<A> cls)
    {
    	
    }

	protected <A> java.util.List<A> getBlocksAtOffset(int dx,
                                                   int dy,
                                                   java.lang.Class<A> cls)
	{
	}
	
	protected <A> java.util.List<A> getObjectsInRange(int radius,
                                                  java.lang.Class<A> cls)
    {
    }
	
	protected Actor getOneBlockAtOffset(int dx,
                                     int dy,
                                     java.lang.Class<?> cls)
    {
    }
    
    protected <A> java.util.List<A> getIntersectingBlocks(java.lang.Class<A> cls)
    {
    }
         
	protected Actor getOneIntersectingObject(java.lang.Class<?> cls)
	{
	}
	
	protected boolean isTouching(java.lang.Class<?> cls)
	{
	}
	
	protected void removeTouching(java.lang.Class<?> cls)
	{
	}                            
	
}