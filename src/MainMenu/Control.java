package MainMenu;

import org.lwjgl.opengl.Display;

public class Control {
    public int main(){
        GUI gui = new GUI();
        gui.init();
        while(true){
            if(Display.isCloseRequested()){
                gui.terminate();
                System.exit(0);
            }
            int i = gui.draw();
            switch (i){
                case 0:
                case 1:
                case 2:
                    return i;
            }
            gui.update();
        }
    }
}
