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
    private static Texture fullscreen;
    private static Texture sound;
    private static Button buttons[];
    void init(){
        //Считываем текстуры
        background = textureBind("background.png");
        fullscreen = textureBind("textFullscreen.png");
        sound = textureBind("textSound.png");
        buttons[0] = new Button((Values.WIDTH/2) - 50, (Values.HEIGHT/2) - 50, (Values.WIDTH/2) + 50, (Values.HEIGHT/2) + 50);
        buttons[1] = new Button((Values.WIDTH/2) - 50, (Values.HEIGHT/2) + 250, (Values.WIDTH/2) + 50, (Values.HEIGHT/2) + 350);
    }
    Texture textureBind(String name){
        Texture texture = null;
        try {
            texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("img/Settings/" + name));
        } catch(IOException e){
            e.printStackTrace();
            System.out.print(1);
        }
        return texture;
    }
    void clear(){
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, MainMenu.Values.WIDTH, MainMenu.Values.HEIGHT,0,1,-1);
        glMatrixMode(GL_MODELVIEW);
        glBegin(GL_QUADS);
        glVertex2f(0,0);
        glVertex2f(0,Values.HEIGHT);
        glVertex2f(Values.WIDTH,MainMenu.Values.HEIGHT);
        glVertex2f(Values.WIDTH,0);
        glEnd();
    }
    void draw(){
       //TODO: Добавить каркас интерфейса
        //Рисуем background
        background.bind();
        glBegin(GL_QUADS);
        glTexCoord2f(0,0);
        glVertex2f(0,0);
        glTexCoord2f(0,1);
        glVertex2f(0, Values.HEIGHT);
        glTexCoord2f(1,1);
        glVertex2f(Values.WIDTH, Values.HEIGHT);
        glTexCoord2f(1,0);
        glVertex2f(Values.WIDTH, 0);
        glEnd();
        //Выводим текст
        fullscreen.bind();
        glBegin(GL_QUADS);
        glTexCoord2f(0,0);
        glVertex2f((Values.WIDTH/2) - 200, (Values.HEIGHT/2) - 200);
        glTexCoord2f(0,1);
        glVertex2f((Values.WIDTH/2) - 200, (Values.HEIGHT/2) - 100);
        glTexCoord2f(1,1);
        glVertex2f((Values.WIDTH/2) + 200, (Values.HEIGHT/2) - 100);
        glTexCoord2f(1,0);
        glVertex2f((Values.WIDTH/2) + 200, (Values.HEIGHT/2) - 200);
        glEnd();
        sound.bind();
        glBegin(GL_QUADS);
        glTexCoord2f(0,0);
        glVertex2f((Values.WIDTH/2) - 200, (Values.HEIGHT/2) + 100);
        glTexCoord2f(0,1);
        glVertex2f((Values.WIDTH/2) - 200, (Values.HEIGHT/2) + 200);
        glTexCoord2f(1,1);
        glVertex2f((Values.WIDTH/2) + 200, (Values.HEIGHT/2) + 200);
        glTexCoord2f(1,0);
        glVertex2f((Values.WIDTH/2) + 200, (Values.HEIGHT/2) + 100);
        glEnd();
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
