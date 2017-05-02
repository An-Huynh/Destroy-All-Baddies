package dab.common.entity;

public class Enemy extends Entity
{
	private static final long serialVersionUID = 5028909663469024138L;
	private String className; // zombie, spider, etc
	
	public Enemy()
	{
		super();
		setClassName("");
	}
	
	public String getClassName()
	{
		return className;
	}
	
	public void setClassName(String className)
	{
		this.className = className;
	}
}
