package dab.server.block;

public abstract class Block {
    private String name;
    
    protected void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}
