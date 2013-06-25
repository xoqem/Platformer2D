import org.lwjgl.util.Color;
import org.lwjgl.util.vector.Vector2f;

import java.util.Iterator;
import java.util.List;

public class Map {

  public float width;
  public float height;

  Cell cells[][];

  public Map(float width, float height) {
    this.width = width;
    this.height = height;

    generate();
  }

  public Cell getCell(Vector2f coords) {
    return getCell(coords.x, coords.y);
  }

  public Cell getCell(float x, float y) {
    if (!areCoordsValid(x, y)) return null;

    while (x >= width) x -= width;
    while (x < 0) x+= width;

    return cells[(int)x][(int)y];
  }

  public boolean areCoordsValid(float x, float y) {
    return (y >= 0 && y < height);
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
    List<Vector2f> cellCoords = MapUtil.getContainedCellCoords(this, position, viewportSize);
    Iterator<Vector2f> itr = cellCoords.iterator();
    while(itr.hasNext()){
      Vector2f coords = itr.next();
      getCell(coords).render(coords);
    }
  }
}
