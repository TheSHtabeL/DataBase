package MainMenu;

import org.lwjgl.*;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.*;
import org.lwjgl.LWJGLException;

public class control {
    public void main(){
        GUI gui = new GUI();
        gui.init();
        while(!Display.isCloseRequested()){
            if(gui.draw()){
                return;
            };
            gui.update();
        }
        gui.terminate();
    }
}
