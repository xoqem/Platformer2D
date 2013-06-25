import org.lwjgl.util.vector.Vector2f;

import java.util.ArrayList;
import java.util.List;

public class MapUtil {
  public static List<Vector2f> getContainedCellCoords(Map map, Vector2f position, Vector2f viewportSize) {
    float halfWidth = viewportSize.getX() / 2;
    float halfHeight = viewportSize.getY() / 2;
    float startX = (float)Math.floor(position.getX() - halfWidth);
    float startY = (float)Math.floor(position.getY() - halfHeight);
    float endX = (float)(Math.ceil(position.getX() + Math.ceil(halfWidth)));
    float endY = (float)(Math.ceil(position.getY() + Math.ceil(halfHeight)));

    List<Vector2f> coords = new ArrayList<Vector2f>();
    for (float x = startX; x < endX; x++) {
      for (float y = startY; y < endY; y ++) {
        if (!map.areCoordsValid(x, y)) continue;
        coords.add(new Vector2f(x, y));
      }
    }

    return coords;
  }
}
