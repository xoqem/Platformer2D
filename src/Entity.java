import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Color;
import org.lwjgl.util.vector.Vector2f;

public class Entity {

  public Vector2f position;
  public Vector2f size = new Vector2f(1, 1);
  public boolean visible = true;
  public Color color = new Color(0, 0, 0, 0);

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
    if (!visible) return;

    GL11.glColor4f(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, color.getAlpha() / 255f);

    float halfWidth = size.x / 2f;
    float halfHeight = size.y / 2f;

    GL11.glBegin(GL11.GL_QUADS);
    GL11.glVertex2f((x - halfWidth) * Globals.scale, (y - halfHeight) * Globals.scale);
    GL11.glVertex2f((x + halfWidth) * Globals.scale - 2, (y - halfHeight) * Globals.scale);
    GL11.glVertex2f((x + halfWidth) * Globals.scale - 2, (y + halfHeight) * Globals.scale - 2);
    GL11.glVertex2f((x - halfWidth) * Globals.scale, (y + halfHeight) * Globals.scale - 2);
    GL11.glEnd();
  }
}
