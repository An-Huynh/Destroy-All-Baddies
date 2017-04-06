package dab.client.graphic.drawable.zone;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import dab.client.graphic.drawable.Drawable;
import dab.client.graphic.program.Program;
import dab.client.graphic.shader.FragmentShader;
import dab.client.graphic.shader.VertexShader;
import dab.client.graphic.texture.Texture;


public class ZoneDrawable implements Drawable {
	protected Program program;
	protected Texture texture;
	protected int vao;
	protected int vbo;
	protected int indiceBuffer;
	
	public ZoneDrawable(String fileLoc) throws IOException {
		createProgram();
		
		createVAO();
		bindVAO();
		
		createTexture(fileLoc);
		createBuffers();
		bindBuffers();
		populateBuffers();
		setShaderAttrib();
		
		unbindVAO();
		unbindBuffers();
	}
	
	protected void createProgram() throws IOException {

		String vSource = readSource("/resource/shader/zone.vs");
		String fSource = readSource("/resource/shader/zone.fs");

		VertexShader vs = new VertexShader();

		vs.loadSource(vSource);

		vs.compile();

		
		if (vs.compileResult() == false) {
			System.out.println(vs.getLog());
		}
		

		FragmentShader fs = new FragmentShader();
		fs.loadSource(fSource);
		fs.compile();

		program = new Program();
		program.attachShader(vs);
		program.attachShader(fs);
		program.link();

		program.detachShader(vs);
		program.detachShader(fs);
		
		vs.delete();
		fs.delete();
	}
	
	protected void freeProgram() {
		program.delete();
	}
	
	protected void createTexture(String sourceFile) {
		this.texture = new Texture(sourceFile);
	}
	
	protected void freeTexture() {
		this.texture.delete();
	}
	
	protected void createBuffers() {
		vbo = GL15.glGenBuffers();
		indiceBuffer = GL15.glGenBuffers();
	}
	
	protected void freeBuffers() {
		GL15.glDeleteBuffers(vbo);
		GL15.glDeleteBuffers(indiceBuffer);
	}
	
	protected void createVAO() {
		vao = GL30.glGenVertexArrays();
	}
	
	protected void freeVAO() {
		GL30.glDeleteVertexArrays(vao);
	}
	
	protected void bindVAO() {
		GL30.glBindVertexArray(vao);
	}
	
	protected void unbindVAO() {
		GL30.glBindVertexArray(0);
	}
	
	protected void bindTexture(String name, int samplerNum) {
		program.setSamplerNum(name, samplerNum);
		texture.bind(samplerNum);
	}
	
	protected void bindBuffers() {
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, indiceBuffer);
	}
	
	protected void unbindBuffers() {
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
	}
	
	protected void populateBuffers() {
		float[] vertices = {-1.0f, -1.0f, 0.0f,
				             1.0f, -1.0f, 0.0f,
				             1.0f,  1.0f, 0.0f,
				            -1.0f,  1.0f, 0.0f};
		FloatBuffer vBuffer = BufferUtils.createFloatBuffer(vertices.length);
		vBuffer.put(vertices);
		vBuffer.flip();
		
		byte[] texCoords = {0, 1, 1, 1, 1, 0, 0, 0};
		ByteBuffer tBuffer = BufferUtils.createByteBuffer(texCoords.length);
		tBuffer.put(texCoords);
		tBuffer.flip();
		
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, Float.BYTES * 12 + Byte.BYTES * 8, GL15.GL_STATIC_DRAW);
		GL15.glBufferSubData(GL15.GL_ARRAY_BUFFER, 0, vBuffer);
		GL15.glBufferSubData(GL15.GL_ARRAY_BUFFER, Float.BYTES * 12, tBuffer);
		
		byte[] indices = {0, 1, 2, 2, 3, 0};
		ByteBuffer iBuffer = BufferUtils.createByteBuffer(indices.length);
		iBuffer.put(indices);
		iBuffer.flip();
		
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, Byte.BYTES * 6,  GL15.GL_STATIC_DRAW);
		GL15.glBufferSubData(GL15.GL_ELEMENT_ARRAY_BUFFER, 0, iBuffer);
	}
	
	protected void setShaderAttrib() {
		int vPos = program.getAttribLocation("vPosition");
		GL20.glEnableVertexAttribArray(vPos);
		GL20.glVertexAttribPointer(vPos, 3, GL11.GL_FLOAT, false, 0, 0);
		
		int vTex = program.getAttribLocation("vTexCoord");
		GL20.glEnableVertexAttribArray(vTex);
		GL20.glVertexAttribPointer(vTex, 2, GL11.GL_UNSIGNED_BYTE, false, 0, Float.BYTES * 12);
	}
	
	public void render() {
		bindVAO();
		
		program.use();
		bindTexture("texture", 0);
		
		GL11.glDrawElements(GL11.GL_TRIANGLES, 6, GL11.GL_UNSIGNED_BYTE, 0);
		
		unbindVAO();
	}
	
	
	protected String readSource(String location) throws IOException {
		InputStream in = getClass().getResourceAsStream(location);
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		try {
			StringBuilder sb = new StringBuilder();
			String line = reader.readLine();
			
			while (line != null) {
				sb.append(line);
				sb.append("\n");
				line = reader.readLine();
			}
			return sb.toString();
		} finally {
			in.close();
		}
	}
}
