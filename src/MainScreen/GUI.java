package MainScreen;


import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.io.IOException;

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
    int draw(){
        //Рисуем фон
        
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
