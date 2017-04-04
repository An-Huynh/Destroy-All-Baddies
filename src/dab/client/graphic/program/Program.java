package dab.client.graphic.program;

import java.nio.FloatBuffer;

import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import dab.client.graphic.shader.Shader;

public class Program {

    private int programID;
    private FloatBuffer buffer;
	
    /**
     * Constructor for Program
     */
    public Program() {
        programID = GL20.glCreateProgram();
        buffer = BufferUtils.createFloatBuffer(4*4);
    }
	
    /**
     * Attaches shader to program object
     * 
     * @param shader
     */
    public void attachShader(Shader shader) {
    	GL20.glAttachShader(programID, shader.getShaderID());
    }
	
    /**
     * Detaches shader from program object
     * 
     * @param shader
     */
    public void detachShader(Shader shader) {
    	GL20.glDetachShader(programID, shader.getShaderID());
    }
	
    /**
     * Links the program object
     */
    public void link() {
        GL20.glLinkProgram(programID);
    }
	
    /**
     * Checks if the program successfully linked or not
     * 
     * @return boolean value representing link status
     */
    public boolean linkResult() {
        return GL20.glGetProgrami(programID, GL20.GL_LINK_STATUS) == GL11.GL_TRUE;
    }
	
    /**
     * Gets the info log for the program
     * 
     * @return String containing info log
     */
    public String infoLog() {
        return GL20.glGetProgramInfoLog(programID);
    }
	
    /**
     * Deletes the program object
     */
    public void delete() {
        GL20.glDeleteProgram(programID);
    }
	
    /**
     * Installs a program as start of current rendering state
     */
    public void use() {
        GL20.glUseProgram(programID);
    }
	
    /**
     * Get location of attribute in program
     * 
     * @param attribName name of attribute
     * @return location indicating attribute, -1 if not active
     */
    public int getAttribLocation(String attribName) {
        return GL20.glGetAttribLocation(programID, attribName);
    }
    
	/**
	 * Get location of Uniform in program
	 * 
	 * @param uniformName name of uniform
	 * @return location indicating uniform, -1 if not active
	 */
    public int getUniformLocation(String uniformName) {
        return GL20.glGetUniformLocation(programID, uniformName);
    }
	
    /**
     * Sets uniform to a specified sampler number
     * 
     * @param name uniform name
     * @param sample sampler number
     */
    public void setSamplerNum(String name, int sample) {
        GL20.glUniform1i(getUniformLocation(name), sample);
    }
	
    /**
     * Sets the value of a 4x4 matrix in the program
     * 
     * @param name uniform name
     * @param translationMatrix containing values from 4x4 matrix
     */
    public void setUniformMatrix4fv(String name, Matrix4f translationMatrix) {
        int location = getUniformLocation(name);
        buffer.clear();
        translationMatrix.get(buffer);
        if (location != -1) {
            GL20.glUniformMatrix4fv(location,  false,  buffer);
        }
    }
}