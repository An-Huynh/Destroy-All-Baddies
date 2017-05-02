package dab.client.graphic.shaders;

public interface Shader
{
	public void loadSource(String sourceCode);
	public void create();
	public void compile();
	public boolean compileResult();
	public String getLog();
	public int getShaderID();
	public void delete();
}
