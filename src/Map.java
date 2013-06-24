import org.lwjgl.util.Color;
import org.lwjgl.util.vector.Vector2f;

public class Map {

  public float width;
  public float height;

  Cell cells[][];

  public Map(float width, float height) {
    this.width = width;
    this.height = height;

    generate();
  }

  public Cell getCell(float x, float y) {

    while (x >= width) x -= width;
    while (x < 0) x+= width;

    if (y < 0) return null;
    if (y >= height) return null;

    return cells[(int)x][(int)y];
  }

  public void generate() {
    cells = new Cell[(int)width][(int)height];
    float halfHeight = (float)Math.floor(height / 2);

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y ++) {
        Cell cell = new Cell(x, y);

        if (y > halfHeight) {
          cell.visible = false;
          cell.passable = true;
        } else {
          cell.visible = true;
          cell.passable = false;

          if (y == halfHeight) {
            cell.color = new Color(0, 158, 11, 255);
          } else if (Math.random() < 0.5) {
            cell.color = new Color(214, 175, 107, 255);
          } else {
            cell.color = new Color(160, 160, 160, 255);
          }
        }

        cells[x][y] = cell;
      }
    }
  }

  public void render(Vector2f position, Vector2f viewportSize) {

    float halfWidth = viewportSize.getX() / 2;
    float halfHeight = viewportSize.getY() / 2;
    float startX = (float)Math.floor(position.getX() - halfWidth);
    float startY = (float)Math.floor(position.getY() - halfHeight);
    float endX = (float)(Math.ceil(position.getX() + Math.ceil(halfWidth)));
    float endY = (float)(Math.ceil(position.getY() + Math.ceil(halfHeight)));

    for (float x = startX; x < endX; x++) {
      for (float y = startY; y < endY; y ++) {
        Cell cell = getCell(x, y);
        if (cell == null) continue;

        cell.render(x, y);
      }
    }
  }
}
