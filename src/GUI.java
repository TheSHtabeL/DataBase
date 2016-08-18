import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.io.IOException;

import static org.lwjgl.opengl.GL11.*;

public abstract class GUI {
    protected Texture background;

    abstract void init();
    abstract int draw();
    abstract void release();
    Texture textureBind(String screenName, String name){
        //Метод для подгрузки текстур
        //name - имя файла текстуры
        //screenName - имя экрана, которому иерархически принадлежит текстура
        Texture texture = null;
        try{
            texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("img/" + screenName + "/"+ name + ".png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        return texture;
    }
    public void setBackground(){
        //Метод отрисовки фона окна
        background.bind();
        GL11.glColor4f(1.0f,1.0f,1.0f,1.0f);
        glBegin(GL_QUADS);
        glTexCoord2f(0,0);
        glVertex2f(0,0);
        glTexCoord2f(0,1);
        glVertex2f(0, Values.HEIGHT);
        glTexCoord2f(1,1);
        glVertex2f(Values.WIDTH, Values.HEIGHT);
        glTexCoord2f(1,0);
        glVertex2f(Values.WIDTH,0);
        glEnd();
    }
    public void update(){
        //Метод отвечает за обновление изображения в окне программы
        Display.update();
        Display.sync(Values.FPS);
    }
}
