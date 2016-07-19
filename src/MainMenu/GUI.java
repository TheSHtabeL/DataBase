package MainMenu;

import org.lwjgl.*;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.*;
import org.lwjgl.LWJGLException;
import static org.lwjgl.opengl.GL11.*;

public class GUI {
    private Button[] buttons;
    void init() {
        try {
            //Создаём окно
            Display.setDisplayMode(new DisplayMode(Values.WIDTH, Values.HEIGHT));
            Display.setInitialBackground(1.0f,1.0f,0.0f);
            Display.create();
            Display.setTitle("Program Window");
            Display.setResizable(true);

        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(0);
        }
        //Создаём кнопки
       // buttons[0] = new Button( (Display.getWidth()/2)-100, (Display.getHeight()/2)-50, (Display.getWidth()/2)+100, (Display.getHeight()/2)+50 );
    }
    void terminate(){
        Display.destroy();
    }
    void draw(){
        glClear(GL_COLOR_BUFFER_BIT);
//        glMatrixMode(GL_PROJECTION);
//        glLoadIdentity();
//        glOrtho(0,Values.WIDTH,Values.HEIGHT,0,1,-1);
//        glMatrixMode(GL_MODELVIEW);
//        GL11.glColor3f(1.0f,0.0f,0.0f);
//        glBegin(GL_QUADS);
//        glVertex2f(buttons[0].x0,buttons[1].y0);
//        glVertex2f(buttons[0].x1,buttons[1].y0);
//        glVertex2f(buttons[0].x1,buttons[1].y1);
//        glVertex2f(buttons[0].x0,buttons[1].y1);
//        glEnd();
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
