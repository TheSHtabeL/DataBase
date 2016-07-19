package MainMenu;

import org.lwjgl.*;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.*;
import org.lwjgl.LWJGLException;
import static org.lwjgl.opengl.GL11.*;

public class GUI {
    void init() {
        try {
            Display.setDisplayMode(new DisplayMode(Values.WIDTH, Values.HEIGHT));
            Display.setInitialBackground(1.0f,1.0f,0.0f);
            Display.create();
            Display.setTitle("Program Window");
            Display.setResizable(true);
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
    void terminate(){
        Display.destroy();
    }
    void draw(){
        glClear(GL_COLOR_BUFFER_BIT);
    }
    void update(){
        updateOpenGL();
        //TODO: Изменение значений ширины и длины кнопок
    }
    private void updateOpenGL(){
        Display.update();
        Display.sync(Values.FPS);
    }
}
