import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Color;
import org.lwjgl.util.vector.Vector2f;

public class Main {

  /** time at last frame */
  long lastFrame;

  /** frames per second */
  int fps;
  /** last fps time */
  long lastFPS;

  Map map = new Map(100, 50);
  PhysicsEntity player = new PhysicsEntity(map, 0, map.height / 2 + 1);

  int screenWidth = 800;
  int screenHeight = 600;

  public void start() {
    try {
      Display.setDisplayMode(new DisplayMode(screenWidth, screenHeight));
      Display.create();
    } catch (LWJGLException e) {
      e.printStackTrace();
      System.exit(0);
    }

    player.color = new Color(255, 255, 127, 255);

    initGL(); // init OpenGL
    getDelta(); // call once before loop to initialise lastFrame
    lastFPS = getTime(); // call before loop to initialise fps timer

    while (!Display.isCloseRequested()) {
      int delta = getDelta();

      update(delta);
      renderGL();

      Display.update();
      Display.sync(60); // cap fps to 60fps
    }

    Display.destroy();
  }

  public void update(int delta) {

    player.velocity.set(0f, 0f);

    if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) player.velocity.setX(-0.02f);
    if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) player.velocity.setX(0.02f);

    if (Keyboard.isKeyDown(Keyboard.KEY_UP)) player.velocity.setY(0.02f);
    if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) player.velocity.setY(-0.02f);

    player.update(delta);

    updateFPS(); // update FPS Counter
  }

  /**
   * Calculate how many milliseconds have passed
   * since last frame.
   *
   * @return milliseconds passed since last frame
   */
  public int getDelta() {
    long time = getTime();
    int delta = (int) (time - lastFrame);
    lastFrame = time;

    return delta;
  }

  /**
   * Get the accurate system time
   *
   * @return The system time in milliseconds
   */
  public long getTime() {
    return (Sys.getTime() * 1000) / Sys.getTimerResolution();
  }

  /**
   * Calculate the FPS and set it in the title bar
   */
  public void updateFPS() {
    if (getTime() - lastFPS > 1000) {
      Display.setTitle("FPS: " + fps);
      fps = 0;
      lastFPS += 1000;
    }
    fps++;
  }

  public void initGL() {
    GL11.glMatrixMode(GL11.GL_PROJECTION);
    GL11.glLoadIdentity();
    GL11.glOrtho(0, 800, 0, 600, 1, -1);
    GL11.glMatrixMode(GL11.GL_MODELVIEW);
  }

  public void renderGL() {
    // Clear The Screen And The Depth Buffer
    GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
    GL11.glClearColor(0.55f, 0.9f, 1.0f, 1.0f);

    GL11.glPushMatrix();
    GL11.glTranslatef(
        -player.position.getX() * Globals.scale + screenWidth / 2,
        -player.position.getY() * Globals.scale + screenHeight / 2,
        0);

    Vector2f viewportSize = new Vector2f(screenWidth / Globals.scale, screenHeight / Globals.scale);
    map.render(player.position, viewportSize);
    player.render();

    GL11.glPopMatrix();
  }

  public static void main(String[] argv) {
    Main main = new Main();
    main.start();
  }
}
