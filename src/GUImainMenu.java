import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.*;

public class GUImainMenu extends GUI{
    private static Texture startActive;
    private static Texture startNotActive;
    private static Texture settingsActive;
    private static Texture settingsNotActive;
    private static Texture exitActive;
    private static Texture exitNotActive;
    private static Button[] buttons = new Button[3];
    void init(){
        //Загрузка текстур
        background = textureBind("Main Menu", "background");
        startActive = textureBind("Main Menu", "aStartButton");
        startNotActive = textureBind("Main Menu", "naStartButton");
        settingsActive = textureBind("Main Menu", "aSettings");
        settingsNotActive = textureBind("Main Menu", "naSettings");
        exitActive = textureBind("Main Menu", "aExit");
        exitNotActive = textureBind("Main Menu", "naExit");
        //Создание логических областей под кнопки
        buttons[0] = new Button( (Display.getWidth()/2)-200, (Display.getHeight()/2)-100, (Display.getWidth()/2)+200, (Display.getHeight()/2)-25 ); //Start
        buttons[1] = new Button( (Display.getWidth()/2)-200, (Display.getHeight()/2), (Display.getWidth()/2)+200, (Display.getHeight()/2)+75); //Settings
        buttons[2] = new Button( (Display.getWidth()/2)-200, (Display.getHeight()/2)+100, (Display.getWidth()/2)+200, (Display.getHeight()/2)+175 ); //Exit
    }
    int draw(){
        Texture button = null;
        setBackground();

        for(int i=0; i<3; i++) {
            if(buttons[i].isActive(Mouse.getX(),Values.HEIGHT-Mouse.getY())) {
                switch (i){
                    case 0:
                        button = startActive;
                        break;
                    case 1:
                        button = settingsActive;
                        break;
                    case 2:
                        button = exitActive;
                        break;
                }
                if (Mouse.isButtonDown(0) && (i == 2)) {
                    return i+1;
                }else if(Mouse.isButtonDown(0) && (i == 1)) {
                    return i+1;
                }else if(Mouse.isButtonDown(0) && (i == 0)){
                    return i+1;
                }
            }else {
                switch (i){
                    case 0:
                        button = startNotActive;
                        break;
                    case 1:
                        button = settingsNotActive;
                        break;
                    case 2:
                        button = exitNotActive;
                        break;
                }
            }
            button.bind();
            glBegin(GL_QUADS);
            glTexCoord2f(0,0);
            glVertex2f(buttons[i].x0, buttons[i].y0);
            glTexCoord2f(1,0);
            glVertex2f(buttons[i].x1, buttons[i].y0);
            glTexCoord2f(1,1);
            glVertex2f(buttons[i].x1, buttons[i].y1);
            glTexCoord2f(0,1);
            glVertex2f(buttons[i].x0, buttons[i].y1);
            glEnd();
        }
        return -1;
    }
    void release(){
        //Удаление текстур из памяти
        background.release();
        startActive.release();
        startNotActive.release();
        settingsActive.release();
        settingsNotActive.release();
        exitActive.release();
        exitNotActive.release();
    }
}
