import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Color;
import org.lwjgl.util.vector.Vector2f;

public class Entity {

  public Vector2f position;
  public boolean passable;
  public Color color = new Color(125, 125, 255, 255);

  public Entity(float x, float y) {
    position = new Vector2f(x, y);
  }

  public void render() {
    render(position);
  }

  public void render(Vector2f renderPosition) {
    render(renderPosition.getX(), renderPosition.getY());
  }

  public void render(float x, float y) {
    GL11.glColor4f(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, color.getAlpha() / 255f);

    GL11.glBegin(GL11.GL_QUADS);
    GL11.glVertex2f((x - 0.5f) * Globals.scale, (y - 0.5f) * Globals.scale);
    GL11.glVertex2f((x + 0.5f) * Globals.scale, (y - 0.5f) * Globals.scale);
    GL11.glVertex2f((x + 0.5f) * Globals.scale, (y + 0.5f) * Globals.scale);
    GL11.glVertex2f((x - 0.5f) * Globals.scale, (y + 0.5f) * Globals.scale);
    GL11.glEnd();
  }
}
