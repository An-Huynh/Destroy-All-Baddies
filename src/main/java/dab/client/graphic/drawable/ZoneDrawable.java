package dab.client.graphic.drawable;

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

import dab.client.graphic.shaders.FragmentShader;
import dab.client.graphic.shaders.ShaderProgram;
import dab.client.graphic.shaders.VertexShader;
import dab.client.graphic.texture.Texture;

public class ZoneDrawable implements StaticDrawable
{
	private ShaderProgram program;
	private Texture texture;
	private int vao;
	private int vbo;
	private int indiceBuffer;
	
	public ZoneDrawable(String imageLocation)
	{
		createProgram();
		
		createVAO();
		bindVAO();
		
		createTexture(imageLocation);
		createBuffers();
		bindBuffers();
		populateBuffers();
		setupShaderAttributes();
		
		unbindVAO();
		unbindBuffers();
	}
	
	@Override
	public void render()
	{
		bindVAO();
		
		program.use();
		bindTexture("texture", 0);
		
		GL11.glDrawElements(GL11.GL_TRIANGLES, 6, GL11.GL_UNSIGNED_BYTE, 0);
		
		unbindVAO();
	}
	
	private void createProgram()
	{
		VertexShader vs = new VertexShader();
		vs.create();
		vs.loadSource(readSourceFile("/shaders/simple.vs"));
		vs.compile();
		
		FragmentShader fs = new FragmentShader();
		fs.create();
		fs.loadSource(readSourceFile("/shaders/simple.fs"));
		fs.compile();
		
		program = new ShaderProgram();
		program.create();
		program.attachShader(vs);
		program.attachShader(fs);
		program.link();
		
		program.detachShader(vs);
		program.detachShader(fs);
		
		vs.delete();
		fs.delete();
	}
	
	private String readSourceFile(String fileLocation)
	{
		InputStream in = getClass().getResourceAsStream(fileLocation);
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String source = "";
		try
		{
			source = buildString(reader);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return source;
	}
	
	private String buildString(BufferedReader in) throws IOException
	{
		StringBuilder sb = new StringBuilder();
		String line = in.readLine();
		while (line != null)
		{
			sb.append(line).append("\n");
			line = in.readLine();
		}
		return sb.toString();
	}
	
	private void createTexture(String textureLocation)
	{
		try {
			texture = new Texture(textureLocation);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void createVAO()
	{
		vao = GL30.glGenVertexArrays();
	}
	
	private void bindVAO()
	{
		GL30.glBindVertexArray(vao);
	}
	
	private void unbindVAO()
	{
		GL30.glBindVertexArray(0);
	}
	
	private void createBuffers()
	{
		vbo = GL15.glGenBuffers();
		indiceBuffer = GL15.glGenBuffers();
	}
	
	private void bindTexture(String uniformName, int samplerNumber)
	{
		program.setSamplerNumber(uniformName, samplerNumber);
		texture.bind(samplerNumber);
	}
	
	private void bindBuffers()
	{
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, indiceBuffer);
	}
	
	private void unbindBuffers()
	{
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
	}
	
	private void populateBuffers()
	{
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
	
	private void setupShaderAttributes()
	{
		int vPos = program.getAttribLocation("vPosition");
		GL20.glEnableVertexAttribArray(vPos);
		GL20.glVertexAttribPointer(vPos, 3, GL11.GL_FLOAT, false, 0, 0);
		
		int vTex = program.getAttribLocation("vTexCoord");
		GL20.glEnableVertexAttribArray(vTex);
		GL20.glVertexAttribPointer(vTex, 2, GL11.GL_UNSIGNED_BYTE, false, 0, Float.BYTES * 12);
	}
}
