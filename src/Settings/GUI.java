package Settings;
import org.newdawn.slick.util.ResourceLoader;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.opengl.Texture;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import java.io.IOException;

import static org.lwjgl.opengl.GL11.*;

public class GUI {
    private static Texture background;
    void init(){
        //Считываем текстуры
        background = textureBind("background.png");
    }
    Texture textureBind(String name){
        Texture texture = null;
        try {
            texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("img/Settings" + name));
        } catch(IOException e){
            e.printStackTrace();
            System.out.print(1);
        }
        return texture;
    }
    void clear(){
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glColor3f(1.0f,1.0f,0.0f);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, MainMenu.Values.WIDTH, MainMenu.Values.HEIGHT,0,1,-1);
        glMatrixMode(GL_MODELVIEW);
        glBegin(GL_QUADS);
        glVertex2f(0,0);
        glVertex2f(0,MainMenu.Values.HEIGHT);
        glVertex2f(MainMenu.Values.WIDTH,MainMenu.Values.HEIGHT);
        glVertex2f(MainMenu.Values.WIDTH,0);
        glEnd();
    }
    void draw(){
       //TODO: Добавить каркас интерфейса
    }
    void update(){
        updateOpenGL();
        //TODO: работа над кнопками и изменением другого GUI
    }
    void updateOpenGL(){
        Display.update();
        Display.sync(Values.FPS);
    }

}
