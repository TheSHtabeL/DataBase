package MainScreen;

import java.io.IOException;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.util.ResourceLoader;

import org.lwjgl.opengl.Display;

import static org.lwjgl.opengl.GL11.*;

public class GUI {
    private static Texture background;
    private static Texture aNewCustomer;
    private static Texture naNewCustomer;
    private static Texture aChangeCustomer;
    private static Texture naChangeCustomer;
    private static Texture aDeleteCustomer;
    private static Texture naDeleteCustomer;
    private static Texture view;
    private static Texture aArrow;
    private static Texture naArrow;
    private static Texture aArrowReversed;
    private static Texture naArrowReversed;
    private static Texture aFind;
    private static Texture naFind;
    private static Button buttons[] = new Button[6];

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
        //Грузим текстуры кнопок
        background = textureBind("background");
        view = textureBind("view");
        aArrow = textureBind("aArrow");
        naArrow = textureBind("naArrow");
        aArrowReversed = textureBind("aArrowReversed");
        naArrowReversed = textureBind("naArrowReversed");
        aFind = textureBind("aFind");
        naFind = textureBind("naFind");
        aNewCustomer = textureBind("aNewCustomer");
        naNewCustomer = textureBind("naNewCustomer");
        aChangeCustomer = textureBind("aChangeCustomer");
        naChangeCustomer = textureBind("naChangeCustomer");
        aDeleteCustomer = textureBind("aDeleteCustomer");
        naDeleteCustomer = textureBind("naDeleteCustomer");
        //Создаём кнопки
        buttons[0] = new Button((Values.WIDTH/2)+350,(Values.HEIGHT/2)-150,(Values.WIDTH/2)+600,(Values.HEIGHT/2)-75);
        buttons[1] = new Button((Values.WIDTH/2)+350,(Values.HEIGHT/2)-50,(Values.WIDTH/2)+600,(Values.HEIGHT/2)+25);
        buttons[2] = new Button((Values.WIDTH/2)+350,(Values.HEIGHT/2)+50,(Values.WIDTH/2)+600,(Values.HEIGHT/2)+125);
        buttons[3] = new Button((Values.WIDTH/2)-150,(Values.HEIGHT/2)+230,(Values.WIDTH/2)-50,(Values.HEIGHT/2)+330);
        buttons[4] = new Button((Values.WIDTH/2)+50,(Values.HEIGHT/2)+230,(Values.WIDTH/2)+150,(Values.HEIGHT/2)+330);
        buttons[5] = new Button((Values.WIDTH/2)-300,(Values.HEIGHT/2)-275,(Values.WIDTH/2)+300,(Values.HEIGHT/2)-205);
    }
    void setBackground(){
        //Натянуть текстуру background
        background.bind();
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
        //Отрисовка экрана вывода записей
        view.bind();
        glBegin(GL_QUADS);
        glTexCoord2f(0,0);
        glVertex2f((Values.WIDTH/2)-300, (Values.HEIGHT/2)-200);
        glTexCoord2f(1,0);
        glVertex2f((Values.WIDTH/2)+300, (Values.HEIGHT/2)-200);
        glTexCoord2f(1,1);
        glVertex2f((Values.WIDTH/2)+300, (Values.HEIGHT/2)+200);
        glTexCoord2f(0,1);
        glVertex2f((Values.WIDTH/2)-300, (Values.HEIGHT/2)+200);
        glEnd();
        //Выводим на экран кнопки
        for(int i=0; i<buttons.length; i++){
            if(buttons[i].isActive(Mouse.getX(),Values.HEIGHT-Mouse.getY())){
                switch(i){
                    case 0:
                        aNewCustomer.bind();
                        break;
                    case 1:
                        aChangeCustomer.bind();
                        break;
                    case 2:
                        aDeleteCustomer.bind();
                        break;
                    case 3:
                        aArrowReversed.bind();
                        break;
                    case 4:
                        aArrow.bind();
                        break;
                    case 5:
                        aFind.bind();
                        break;
                }
            }else{
                switch (i) {
                    case 0:
                        naNewCustomer.bind();
                        break;
                    case 1:
                        naChangeCustomer.bind();
                        break;
                    case 2:
                        naDeleteCustomer.bind();
                        break;
                    case 3:
                        naArrowReversed.bind();
                        break;
                    case 4:
                        naArrow.bind();
                        break;
                    case 5:
                        naFind.bind();
                        break;
                }
            }
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
        if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
            return 0;
        }else {
            return 1;
        }
    }
    void update(){
        Display.update();
        Display.sync(Values.FPS);
    }
    void release(){
        background.release();
        view.release();
        aArrowReversed.release();
        naArrowReversed.release();
        aFind.release();
        naFind.release();
        aDeleteCustomer.release();
        naDeleteCustomer.release();
        aNewCustomer.release();
        naNewCustomer.release();
        aChangeCustomer.release();
        naChangeCustomer.release();
    }
}
