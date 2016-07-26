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
    private static Texture filled;
    private static Texture unfilled;
    private static Texture flag[] = new Texture[2];
    private static Button buttons[] = new Button[2];
    private static boolean permission = true;
    void init(){
        //Считываем текстуры
        background = textureBind("background.png");
        fullscreen = textureBind("textFullscreen.png");
        sound = textureBind("textSound.png");
        filled = textureBind("aSquare.png");
        unfilled = textureBind("naSquare.png");
        buttons[0] = new Button((Values.WIDTH/2) + 150, (Values.HEIGHT/2) - 200, (Values.WIDTH/2) + 250, (Values.HEIGHT/2) - 100);
        buttons[1] = new Button((Values.WIDTH/2) + 150, (Values.HEIGHT/2), (Values.WIDTH/2) + 250, (Values.HEIGHT/2) + 100);
        flag[0] = unfilled;
        flag[1] = unfilled;
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
        glVertex2f((Values.WIDTH/2) - 300, (Values.HEIGHT/2) - 200);
        glTexCoord2f(0,1);
        glVertex2f((Values.WIDTH/2) - 300, (Values.HEIGHT/2) - 100);
        glTexCoord2f(1,1);
        glVertex2f((Values.WIDTH/2) + 100, (Values.HEIGHT/2) - 100);
        glTexCoord2f(1,0);
        glVertex2f((Values.WIDTH/2) + 100, (Values.HEIGHT/2) - 200);
        glEnd();
        sound.bind();
        glBegin(GL_QUADS);
        glTexCoord2f(0,0);
        glVertex2f((Values.WIDTH/2) - 300, (Values.HEIGHT/2));
        glTexCoord2f(0,1);
        glVertex2f((Values.WIDTH/2) - 300, (Values.HEIGHT/2) + 100);
        glTexCoord2f(1,1);
        glVertex2f((Values.WIDTH/2) + 100, (Values.HEIGHT/2) + 100);
        glTexCoord2f(1,0);
        glVertex2f((Values.WIDTH/2) + 100, (Values.HEIGHT/2));
        glEnd();
        for(int i=0; i<2; i++){
            if(buttons[i].isActive(Mouse.getX(),Values.HEIGHT-Mouse.getY()) && Mouse.isButtonDown(0)) {
                if(permission) {
                    permission = !permission;
                    if (flag[i] == filled) {
                        flag[i] = unfilled;
                    } else {
                        flag[i] = filled;
                    }
                }
            }
            if(!Mouse.isButtonDown(0)){
                permission = true;
            }
            flag[i].bind();
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
    }
    void update(){
        updateOpenGL();
        //TODO: работа над кнопками и изменением другого GUI
    }
    void reveal(){
        background.release();
        fullscreen.release();
        sound.release();
        filled.release();
        unfilled.release();
    }
    void updateOpenGL(){
        Display.update();
        Display.sync(Values.FPS);
    }

}
