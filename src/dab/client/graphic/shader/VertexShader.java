package dab.client.graphic.shader;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public class VertexShader implements Shader {
	
	private int shaderID;
	
	/**
	 * Constructor for VertexShader
	 */
	public VertexShader() {
		shaderID = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);
	}

	/**
	 * sets the source of the vertex shader
	 * to a string containing the GLSL
	 * source code
	 * 
	 * @param sourceCode string containing GLSL code for the vertex shader
	 */
	@Override
	public void loadSource(String sourceCode) {
		GL20.glShaderSource(shaderID, sourceCode);
	}

	/**
	 * compiles the vertex shader
	 * does nothing if compilation fails
	 */
	@Override
	public void compile() {
		GL20.glCompileShader(shaderID);
	}
	
	/**
	 * returns a boolean value indicating if the vertex shader
	 * compiled successfully or not
	 * 
	 * @return boolean representing if compilation
	 *         was successful
	 */
	@Override
	public boolean compileResult() {
		return GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_TRUE;
	}

	/**
	 * returns a string containing the error message
	 * if compilation was unsuccessful or an
	 * empty string if compilation was successful
	 * 
	 * @return string containing error log
	 */
	@Override
	public String getLog() {
		return GL20.glGetShaderInfoLog(shaderID);
	}

	
	@Override
	public int getShaderID() {
		return shaderID;
	}
	
	/**
	 * deletes the vertex shader
	 * shader is only deleted after being detached
	 * from program.
	 */
	@Override
	public void delete() {
		GL20.glDeleteShader(shaderID);
	}
}