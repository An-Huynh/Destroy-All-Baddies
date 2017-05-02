package dab.client.graphic.texture;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

public class Texture
{
	private int textureID;
	
	public Texture(String textureLocation) throws IOException
	{
		BufferedImage bi = ImageIO.read(getResourceStream(textureLocation));
		ByteBuffer pixels = getPixels(bi);
		createTexture(pixels, bi.getWidth(), bi.getHeight());
	}
	
	public void bind(int samplerNumber)
	{
		if (samplerNumber >= 0 && samplerNumber < 32)
		{
			GL13.glActiveTexture(GL13.GL_TEXTURE0 + samplerNumber);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
		}
	}
	
	private ByteBuffer getPixels(BufferedImage bi)
	{
		ByteBuffer pixels = BufferUtils.createByteBuffer(bi.getWidth() * bi.getHeight() * 4);
		for (int pixel : getRawPixels(bi))
		{
			pixels.put((byte) ((pixel >> 16) & 0xFF));
			pixels.put((byte) ((pixel >>  8) & 0xFF));
			pixels.put((byte) ((pixel >>  0) & 0xFF));
			pixels.put((byte) ((pixel >> 24) & 0xFF));
		}
		pixels.flip();
		return pixels;
	}
	
	private int[] getRawPixels(BufferedImage bi)
	{
		return bi.getRGB(0, 0, bi.getWidth(), bi.getHeight(), null, 0, bi.getWidth());
	}
	
	private void createTexture(ByteBuffer pixels, int width, int height)
	{
		textureID = GL11.glGenTextures();
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
		GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
		GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, width, height, 0, GL11.GL_RGBA,
				          GL11.GL_UNSIGNED_BYTE, pixels);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
	}
	
	private InputStream getResourceStream(String resourceLocation)
	{
		return getClass().getResourceAsStream(resourceLocation);
	}
}
