package MainScreen;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

public class Control {
    public void control(){
        GUI gui = new GUI();
        int flag = 0;

        gui.init();
        while(!Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
            if(Display.isCloseRequested()){
                System.exit(0);
            }
            flag = gui.draw();
            switch (flag){
                case 0:
                    gui.release();
                    return;
            }
            gui.update();
        }
    }
}
