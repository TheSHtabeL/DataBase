package MainMenu;

import org.lwjgl.*;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.*;
import org.lwjgl.LWJGLException;

public class GUI {
    void init() {
        try{
            Display.setDisplayMode(new DisplayMode(WIDTH,HEIGHT));
            Display.create();
        }catch (LWJGLException e){
            e.printStackTrace();
            System.exit(0);
        }
    }
}
