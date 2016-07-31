import MainMenu.Values;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;

public class Init {
    public void init() {
        try {
            //Создаём окно
            Display.setDisplayMode(new DisplayMode(Values.WIDTH, Values.HEIGHT));
            Display.create();
            Display.setTitle("Program Window");
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(1);
        }
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, Values.WIDTH, Values.HEIGHT, 0, 1, -1);
        glMatrixMode(GL_MODELVIEW);
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    }
}
