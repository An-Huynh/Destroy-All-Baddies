package dab.graphic.shader;

/**
 * Interface for shader classes will define public methods that
 * vertex and fragment shaders need to use.
 * 
 * @author An Huynh
 */

public interface Shader {
	
	/**
	 * sets the source of the shader
	 * to a string containing the GLSL
	 * source code
	 * 
	 * @param sourceCode string containing GLSL code for shader
	 */
	public void loadSource(String sourceCode);
	
	/**
	 * compiles the shader
	 * does not do anything if compile fails
	 */
	public void compile();
	
	/**
	 * returns a boolean value indicating if the shader
	 * compiled successfully or not
	 * 
	 * @return boolean representing if compilation
	 *         was successful
	 */
	public boolean compileResult();
	
	/**
	 * returns a string containing the error message
	 * if compilation was unsuccessful or an
	 * empty string if compilation was successful
	 * 
	 * @return string containing error log
	 */
	public String errorLog();
	
	/**
	 * attaches shader to a specified program
	 * 
	 * @param program
	 */
	public void attach(int program);
	
	/**
	 * detaches the shader from a specified program
	 * 
	 * @param program
	 */
	public void detach(int program);
	
	/**
	 * deletes the shader
	 * shader is only deleted after being detached
	 * from program.
	 */
	public void delete();
}
