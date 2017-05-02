package dab.client.graphic.drawable;

import org.joml.Vector2f;
import org.joml.Vector2i;

public interface VariableDrawable
{
	public void render(float x, float y, Vector2i scale);
	public void render(Vector2f offset, Vector2i scale);
}
