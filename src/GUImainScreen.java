import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.*;

public class GUImainScreen extends GUI{
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
    private static Texture cursor;
    private static int cursorCounter = 0;
    private static float cursorDelta = -0.5F;
    private static Button buttons[] = new Button[6];
    private static boolean findActive = false;
    void init(){
        //Загрузка текстур
        background = textureBind("Main Screen", "background");
        view = textureBind("Main Screen", "view");
        aArrow = textureBind("Main Screen", "aArrow");
        naArrow = textureBind("Main Screen", "naArrow");
        aArrowReversed = textureBind("Main Screen", "aArrowReversed");
        naArrowReversed = textureBind("Main Screen", "naArrowReversed");
        aFind = textureBind("Main Screen", "aFind");
        naFind = textureBind("Main Screen", "naFind");
        aNewCustomer = textureBind("Main Screen", "aNewCustomer");
        naNewCustomer = textureBind("Main Screen", "naNewCustomer");
        aChangeCustomer = textureBind("Main Screen", "aChangeCustomer");
        naChangeCustomer = textureBind("Main Screen", "naChangeCustomer");
        aDeleteCustomer = textureBind("Main Screen", "aDeleteCustomer");
        naDeleteCustomer = textureBind("Main Screen", "naDeleteCustomer");
        cursor = textureBind("Main Screen", "cursor");
        //Создаём кнопки
        buttons[0] = new Button((Values.WIDTH/2)+350,(Values.HEIGHT/2)-150,(Values.WIDTH/2)+600,(Values.HEIGHT/2)-75); //Создать
        buttons[1] = new Button((Values.WIDTH/2)+350,(Values.HEIGHT/2)-50,(Values.WIDTH/2)+600,(Values.HEIGHT/2)+25); //Изменить
        buttons[2] = new Button((Values.WIDTH/2)+350,(Values.HEIGHT/2)+50,(Values.WIDTH/2)+600,(Values.HEIGHT/2)+125); //Удалить
        buttons[3] = new Button((Values.WIDTH/2)-150,(Values.HEIGHT/2)+230,(Values.WIDTH/2)-50,(Values.HEIGHT/2)+330); //Стрелка влево
        buttons[4] = new Button((Values.WIDTH/2)+50,(Values.HEIGHT/2)+230,(Values.WIDTH/2)+150,(Values.HEIGHT/2)+330); //Стрелка вправо
        buttons[5] = new Button((Values.WIDTH/2)-300,(Values.HEIGHT/2)-275,(Values.WIDTH/2)+300,(Values.HEIGHT/2)-205); //Строка поиска
    }
    void showViewPanel(){
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
    }
    void showCursor(){
        cursorCounter++;
        if(cursorCounter > 30){
            cursorCounter = 0;
            cursorDelta = -cursorDelta;
        }
        cursor.bind();
        glBegin(GL_QUADS);
        glTexCoord2f( 0.5F + cursorDelta, 0 );
        //glTexCoord2f(0,0);
        glVertex2f( (Values.WIDTH/2)-260, (Values.HEIGHT/2)-265 );
        glTexCoord2f( 0.5F, 0 );
        //glTexCoord2f(1,0);
        glVertex2f( (Values.WIDTH/2)-265, (Values.HEIGHT/2)-265 );
        glTexCoord2f( 0.5F, 1 );
        //glTexCoord2f(1,1);
        glVertex2f( (Values.WIDTH/2)-265, (Values.HEIGHT/2)-215 );
        glTexCoord2f( 0.5F + cursorDelta, 1 );
        //glTexCoord2f(0,1);
        glVertex2f( (Values.WIDTH/2)-260, (Values.HEIGHT/2)-215 );
        glEnd();
    }
    int draw(){
        setBackground();
        showViewPanel();
        //Выводим на экран кнопки
        for(int i=0; i<buttons.length; i++){
            if( Mouse.isButtonDown(0) && i!=5 ){
                findActive = false;
            }
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
                        if( Mouse.isButtonDown(0) ){
                            findActive = true;
                        }
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
                        if(findActive){
                            showCursor();
                            aFind.bind();
                        }else {
                            naFind.bind();
                        }
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
        //Вывод курсора, если строка поиска активна
        if(findActive){
            showCursor();
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
            return 0;
        }else {
            return 4;
        }
    }
    void release(){
        aNewCustomer.release();
        naNewCustomer.release();
        aChangeCustomer.release();
        naChangeCustomer.release();
        aDeleteCustomer.release();
        naDeleteCustomer.release();
        view.release();
        aArrow.release();
        naArrow.release();
        aArrowReversed.release();
        naArrowReversed.release();
        aFind.release();
        naFind.release();
        cursor.release();
    }
}