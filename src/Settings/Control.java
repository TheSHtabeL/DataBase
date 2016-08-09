package Settings;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

public class Control {
    public void main(){
        GUI gui = new GUI();
        gui.init();
        gui.clear();
        gui.update();
        gui.clear();
        while( !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
            if(Display.isCloseRequested()){
                gui.reveal();
                System.exit(0);
            }
            gui.update();
            gui.draw();
        }
        gui.reveal();
    }
}
