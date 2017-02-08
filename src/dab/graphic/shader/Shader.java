package dab.graphic.shader;

public interface Shader {
	
	public void loadSource(String sourceCode);
	public void compile();
	
	public boolean compileResult();
	public String errorLog();
	
	public void attach(int program);
	public void detach(int program);
	
	public void delete();
}
