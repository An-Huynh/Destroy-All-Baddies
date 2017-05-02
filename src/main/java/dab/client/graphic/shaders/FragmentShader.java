package dab.client.graphic.shaders;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public class FragmentShader implements Shader
{
	private int shader;
	
	@Override
	public void create()
	{
		shader = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);
	}
	
	@Override
	public void loadSource(String sourceCode)
	{
		GL20.glShaderSource(shader, sourceCode);
	}
	
	@Override
	public void compile()
	{
		GL20.glCompileShader(shader);
	}
	
	@Override
	public boolean compileResult()
	{
		return GL20.glGetShaderi(shader, GL20.GL_COMPILE_STATUS) == GL11.GL_TRUE;
	}
	
	@Override
	public String getLog()
	{
		return GL20.glGetShaderInfoLog(shader);
	}
	
	@Override
	public int getShaderID()
	{
		return shader;
	}
	
	@Override
	public void delete()
	{
		GL20.glDeleteShader(shader);
	}
}