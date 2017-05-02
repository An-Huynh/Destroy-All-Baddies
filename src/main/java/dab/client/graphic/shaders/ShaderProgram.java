package dab.client.graphic.shaders;

import java.nio.FloatBuffer;

import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public class ShaderProgram
{
	private int program;
	private FloatBuffer buffer;
	
	public void create()
	{
		program = GL20.glCreateProgram();
		buffer = BufferUtils.createFloatBuffer(4*4);
	}
	
	public void attachShader(Shader shader)
	{
		GL20.glAttachShader(program, shader.getShaderID());
	}
	
	public void detachShader(Shader shader)
	{
		GL20.glDetachShader(program, shader.getShaderID());
	}
	
	public void link()
	{
		GL20.glLinkProgram(program);
	}
	
	public boolean linkResult()
	{
		return GL20.glGetProgrami(program, GL20.GL_LINK_STATUS) == GL11.GL_TRUE;
	}
	
	public String getInfoLog()
	{
		return GL20.glGetProgramInfoLog(program);
	}
	
	public void delete()
	{
		GL20.glDeleteProgram(program);
	}
	
	public void use()
	{
		GL20.glUseProgram(program);
	}
	
	public int getAttribLocation(String attributeName)
	{
		return GL20.glGetAttribLocation(program, attributeName);
	}
	
	public int getUniformLocation(String uniformName)
	{
		return GL20.glGetUniformLocation(program, uniformName);
	}
	
	public void setSamplerNumber(String name, int sample)
	{
		GL20.glUniform1i(getUniformLocation(name), sample);
	}
	
	public void setUniformMatrix4fv(String name, Matrix4f translationMatrix)
	{
		int location = getUniformLocation(name);
		buffer.clear();
		translationMatrix.get(buffer);
		if (location != -1)
		{
			GL20.glUniformMatrix4fv(location, false, buffer);
		}
	}
}