package MainMenu;

import Settings.*;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.*;
import org.lwjgl.LWJGLException;
import static org.lwjgl.opengl.GL11.*;

public class GUI {
    private static Button[] buttons;
    void init() {
        try {
            //Создаём окно
            Display.setDisplayMode(new DisplayMode(Values.WIDTH, Values.HEIGHT));
            Display.setInitialBackground(1.0f,1.0f,0.0f);
            Display.create();
            Display.setTitle("Program Window");
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(0);
        }
        //Создаём кнопки
        buttons = new Button[4];
        buttons[0] = new Button( (Display.getWidth()/2)-200, (Display.getHeight()/2)-200, (Display.getWidth()/2)+200, (Display.getHeight()/2)-125 ); //Start
        buttons[1] = new Button( (Display.getWidth()/2)-200, (Display.getHeight()/2)-100, (Display.getWidth()/2)+200, (Display.getHeight()/2)-25 ); //Load
        buttons[2] = new Button( (Display.getWidth()/2)-200, (Display.getHeight()/2), (Display.getWidth()/2)+200, (Display.getHeight()/2)+75 ); //Settings
        buttons[3] = new Button( (Display.getWidth()/2)-200, (Display.getHeight()/2)+100, (Display.getWidth()/2)+200, (Display.getHeight()/2)+175 ); //Exit
    }
    void terminate(){
        Display.destroy();
    }
    boolean draw(){
        glClear(GL_COLOR_BUFFER_BIT);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0,Values.WIDTH,Values.HEIGHT,0,1,-1);
        glMatrixMode(GL_MODELVIEW);
        glBegin(GL_QUADS);
        for(int i=0; i<4; i++) {

            if(buttons[i].isActive(Mouse.getX(),Values.HEIGHT-Mouse.getY())) {
                GL11.glColor3f(0.0f, 1.0f, 0.0f);
                if (Mouse.isButtonDown(0) && (i == 3)) {
                    return true;
                }else if(Mouse.isButtonDown(0) && (i == 2)) {
                    Settings.Control control = new Control();
                    control.main();
                }
            }else {
                GL11.glColor3f(1.0f,0.0f,0.0f);
            }
            glVertex2f(buttons[i].x0, buttons[i].y0);
            glVertex2f(buttons[i].x1, buttons[i].y0);
            glVertex2f(buttons[i].x1, buttons[i].y1);
            glVertex2f(buttons[i].x0, buttons[i].y1);
        }
        glEnd();
        return false;
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
