import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.*;

public class GUIsettings extends GUI {
    private static Texture fullscreen;
    private static Texture sound;
    private static Texture filled;
    private static Texture unfilled;
    private static Button buttons[] = new Button[2];
    private static boolean permission = true;

    void init(){
        //Загружаем текстуры
        background = textureBind("Settings", "background");
        fullscreen = textureBind("Settings", "textFullscreen");
        sound = textureBind("Settings", "textSound");
        filled = textureBind("Settings", "aSquare");
        unfilled = textureBind("Settings", "naSquare");
        //Подгружаем кнопки
        buttons[0] = new Button((Values.WIDTH/2) + 150, (Values.HEIGHT/2) - 200, (Values.WIDTH/2) + 250, (Values.HEIGHT/2) - 100);
        buttons[1] = new Button((Values.WIDTH/2) + 150, (Values.HEIGHT/2), (Values.WIDTH/2) + 250, (Values.HEIGHT/2) + 100);
    }
    void release(){
        background.release();
        fullscreen.release();
        sound.release();
        filled.release();
        unfilled.release();
    }
    int draw(){
        setBackground();
        setText();
        for(int i=0; i<2; i++){
            if(buttons[i].isActive(Mouse.getX(),Values.HEIGHT-Mouse.getY()) && Mouse.isButtonDown(0)) {
                if(permission) {
                    if( i == 0){
                        Values.Fullscreen = !Values.Fullscreen;
                    }else{
                        Values.Sound = !Values.Sound;
                    }
                    permission = !permission;
                }
            }
            if(!Mouse.isButtonDown(0)){
                permission = true;
            }
            //
            if( i == 0) {
                if( Values.Fullscreen == true ){
                    filled.bind();
                }else{
                    unfilled.bind();
                }
            }
            if( i == 1 ) {
                if (Values.Sound == true) {
                    filled.bind();
                } else {
                    unfilled.bind();
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
        }
        return 4;
    }
    void setText(){
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
    }
}
