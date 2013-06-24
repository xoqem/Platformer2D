import org.lwjgl.util.vector.Vector2f;

public class PhysicsEntity extends Entity {

  public Map map;
  public Vector2f velocity = new Vector2f(0f, 0f);

  public void update(int delta) {
    position.translate(velocity.x * delta, velocity.y * delta);

    while (position.x < 0) position.x += map.width;
    while (position.x >= map.width) position.x -= map.width;

    if (position.y < 0) position.y = 0;
    if (position.y >= map.height) position.y = map.height;
  }

  public PhysicsEntity(Map map, float x, float y) {
    super(x, y);
    this.map = map;
  }
}
