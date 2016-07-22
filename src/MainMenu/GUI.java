package MainMenu;

import Settings.*;
import java.io.IOException;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.util.ResourceLoader;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.*;
import org.lwjgl.LWJGLException;
import org.w3c.dom.Text;

import static org.lwjgl.opengl.GL11.*;

public class GUI {
    private static Button[] buttons;
    private static Texture background;
    private static Texture buttonNotActive;
    private static Texture buttonActive;
    void init() {
        try {
            //Создаём окно
            Display.setDisplayMode(new DisplayMode(Values.WIDTH, Values.HEIGHT));
            Display.create();
            Display.setTitle("Program Window");
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(1);
        }
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0,Values.WIDTH,Values.HEIGHT,0,1,-1);
        glMatrixMode(GL_MODELVIEW);
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        //Создаём кнопки
        buttons = new Button[4];
        buttons[0] = new Button( (Display.getWidth()/2)-200, (Display.getHeight()/2)-100, (Display.getWidth()/2)+200, (Display.getHeight()/2)-25 ); //Start
        buttons[1] = new Button( (Display.getWidth()/2)-200, (Display.getHeight()/2), (Display.getWidth()/2)+200, (Display.getHeight()/2)+75); //Load
        buttons[2] = new Button( (Display.getWidth()/2)-200, (Display.getHeight()/2)+100, (Display.getWidth()/2)+200, (Display.getHeight()/2)+175 ); //Settings
        buttons[3] = new Button( (Display.getWidth()/2)-200, (Display.getHeight()/2)+200, (Display.getWidth()/2)+200, (Display.getHeight()/2)+275 ); //Exit
        //Грузим текстуры
        background = textureBind("Background.png");
        buttonActive = textureBind("newGameActive.png");
        buttonNotActive = textureBind("newGameNotActive.png");
    }
    Texture textureBind(String texName){
        Texture texture = null;
        try {
            texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("img/" + texName));
        }catch (IOException e){
            e.printStackTrace();
            System.exit(1);
        }
        return texture;
    }
    void terminate(){
        Display.destroy();
    }
    void setBackground(){
        //Натянуть текстуру background
        background.bind();
        background.getImageHeight();
        background.getImageWidth();
        background.getTextureHeight();
        background.getTextureWidth();
        GL11.glColor4f(1.0f,1.0f,1.0f,1.0f);
        glBegin(GL_QUADS);
        glTexCoord2f(0,0);
        glVertex2f(0,0);
        glTexCoord2f(0,1);
        glVertex2f(0,Values.HEIGHT);
        glTexCoord2f(1,1);
        glVertex2f(Values.WIDTH,Values.HEIGHT);
        glTexCoord2f(1,0);
        glVertex2f(Values.WIDTH,0);
        glEnd();
    }
    boolean draw(){
        Texture button;
        glClear(GL_COLOR_BUFFER_BIT);
        setBackground();
        for(int i=0; i<4; i++) {
            if(buttons[i].isActive(Mouse.getX(),Values.HEIGHT-Mouse.getY())) {
                //GL11.glColor3f(0.0f, 1.0f, 0.0f);
                button = buttonActive;
                if (Mouse.isButtonDown(0) && (i == 3)) {
                    return true;
                }else if(Mouse.isButtonDown(0) && (i == 2)) {
                    Settings.Control control = new Control();
                    control.main();
                }
            }else {
                button = buttonNotActive;
                //GL11.glColor3f(1.0f,0.0f,0.0f);
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
