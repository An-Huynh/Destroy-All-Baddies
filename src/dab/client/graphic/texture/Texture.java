package dab.client.graphic.texture;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

public class Texture {
    
    private int textureID;
    
    public Texture(String fileName) {
        BufferedImage bi;
        try {
        	bi = ImageIO.read(this.getClass().getResourceAsStream(fileName));
            int width = bi.getWidth();
            int height = bi.getHeight();
            
            int[] rawPixels = new int[width * height];
            rawPixels = bi.getRGB(0, 0, width, height, null, 0, width);
            
            ByteBuffer pixels = BufferUtils.createByteBuffer(width * height * 4);
            for (int i = 0; i < height; ++i) {
                for (int j = 0; j < width; ++j) {
                    int pixel = rawPixels[i * width + j];
                    pixels.put((byte)((pixel >> 16) & 0xFF));
                    pixels.put((byte)((pixel >>  8) & 0xFF));
                    pixels.put((byte)((pixel >>  0) & 0xFF));
                    pixels.put((byte)((pixel >> 24) & 0xFF));
                }
            }
            pixels.flip();
            
            textureID = GL11.glGenTextures();
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
            GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
            GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);  
            GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, width, height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, pixels);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void bind(int sampler) {
        if (sampler >= 0 && sampler < 32) {
            GL13.glActiveTexture(GL13.GL_TEXTURE0 + sampler);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
        }
    }
    
    public void delete() {
    	GL11.glDeleteTextures(textureID);
    }
}