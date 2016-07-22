package Settings;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

public class Control {
    public void main(){
        GUI gui = new GUI();
        gui.clear();
        gui.update();
        gui.clear();
        while( !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) && !Display.isCloseRequested()){
            gui.update();
            gui.draw();
        }
    }
}
