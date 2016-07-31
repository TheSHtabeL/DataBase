package MainScreen;

import java.io.IOException;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.util.ResourceLoader;

import org.lwjgl.opengl.Display;

import static org.lwjgl.opengl.GL11.*;

public class GUI {
    private static Texture background;

    Texture textureBind(String name){
        Texture texture = null;
        try{
            texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("img/Main Screen/" + name + ".png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        return texture;
    }

    void init(){
        background = textureBind("background");

    }
    void setBackground(){
        //Натянуть текстуру background
        background.bind();
        background.getImageHeight();
        background.getImageWidth();
        background.getTextureHeight();
        background.getTextureWidth();
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
    int draw(){
        setBackground();
        return 0;
    }
    void update(){
        Display.update();
        Display.sync(Values.FPS);
    }
    void reveal(){
        background.release();
    }
}
