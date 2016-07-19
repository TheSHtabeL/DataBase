package MainMenu;

import org.lwjgl.*;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.*;
import org.lwjgl.LWJGLException;

public class GUI {
    void init() {
        try {
            Display.setDisplayMode(new DisplayMode(Values.WIDTH, Values.HEIGHT));
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

    }
    void update(){
        updateOpenGL();
    }
    private void updateOpenGL(){
        Display.update();
        Display.sync(Values.FPS);
    }
}
