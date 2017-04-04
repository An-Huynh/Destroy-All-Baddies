package dab.client.graphic.shader;

public interface Shader {
	public void loadSource(String sourceCode);
	public void compile();
	public boolean compileResult();
	public String getLog();
	public int getShaderID();
	public void delete();
}
