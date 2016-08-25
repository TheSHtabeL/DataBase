import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;

public class GUImainScreen extends GUI {
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
    private static Texture space;
    private static Texture[] enULetters = new Texture[26];
    private static Texture[] enLLetters = new Texture[26];
    private static Texture[] ruLetters;
    private static Texture[] numbers = new Texture[10];
    private static int cursorPointer = 0;
    private static int cursorCounter = 10;
    private static float cursorDelta = -0.5F;
    private static Button buttons[] = new Button[6];
    private static boolean findActive = false;
    private static ArrayList<String> findString = new ArrayList<String>();
    private long lastFrame;
    private long time;

    void init() {
        //Загрузка текстур
        lastFrame = getTime();
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
        //Загружаем текстуры алфавитов
        space = textureBind("Main Screen", "Letters/space");
        for (int i = 0; i < 10; i++) {
            numbers[i] = textureBind("Main Screen", "Letters/" + String.valueOf(i)); //Загрузка цифр
        }
        for (int i = 1; i < 27; i++) {
            enULetters[i - 1] = textureBind("Main Screen", "Letters/enU_" + String.valueOf(i));
            enLLetters[i - 1] = textureBind("Main Screen", "Letters/enL_" + String.valueOf(i));
        }
        //Создаём кнопки
        buttons[0] = new Button((Values.WIDTH / 2) + 350, (Values.HEIGHT / 2) - 150, (Values.WIDTH / 2) + 600, (Values.HEIGHT / 2) - 75); //Создать
        buttons[1] = new Button((Values.WIDTH / 2) + 350, (Values.HEIGHT / 2) - 50, (Values.WIDTH / 2) + 600, (Values.HEIGHT / 2) + 25); //Изменить
        buttons[2] = new Button((Values.WIDTH / 2) + 350, (Values.HEIGHT / 2) + 50, (Values.WIDTH / 2) + 600, (Values.HEIGHT / 2) + 125); //Удалить
        buttons[3] = new Button((Values.WIDTH / 2) - 150, (Values.HEIGHT / 2) + 230, (Values.WIDTH / 2) - 50, (Values.HEIGHT / 2) + 330); //Стрелка влево
        buttons[4] = new Button((Values.WIDTH / 2) + 50, (Values.HEIGHT / 2) + 230, (Values.WIDTH / 2) + 150, (Values.HEIGHT / 2) + 330); //Стрелка вправо
        buttons[5] = new Button((Values.WIDTH / 2) - 300, (Values.HEIGHT / 2) - 275, (Values.WIDTH / 2) + 300, (Values.HEIGHT / 2) - 205); //Строка поиска
    }

    void showViewPanel() {
        //Отрисовка экрана вывода записей
        view.bind();
        glBegin(GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex2f((Values.WIDTH / 2) - 300, (Values.HEIGHT / 2) - 200);
        glTexCoord2f(1, 0);
        glVertex2f((Values.WIDTH / 2) + 300, (Values.HEIGHT / 2) - 200);
        glTexCoord2f(1, 1);
        glVertex2f((Values.WIDTH / 2) + 300, (Values.HEIGHT / 2) + 200);
        glTexCoord2f(0, 1);
        glVertex2f((Values.WIDTH / 2) - 300, (Values.HEIGHT / 2) + 200);
        glEnd();
    }

    void showCursor() {
        int delta = getDeltaTime();
        if (delta > cursorCounter) {
            cursorDelta = -cursorDelta;
            cursorCounter = 400;

        } else {
            cursorCounter -= delta;
        }
        cursor.bind();
        glBegin(GL_QUADS);
        glTexCoord2f(0.5F + cursorDelta, 0);
        glVertex2f((Values.WIDTH / 2) - 285 + 30 * cursorPointer, (Values.HEIGHT / 2) - 265);
        glTexCoord2f(0.5F, 0);
        glVertex2f((Values.WIDTH / 2) - 290 + 30 * cursorPointer, (Values.HEIGHT / 2) - 265);
        glTexCoord2f(0.5F, 1);
        glVertex2f((Values.WIDTH / 2) - 290 + 30 * cursorPointer, (Values.HEIGHT / 2) - 215);
        glTexCoord2f(0.5F + cursorDelta, 1);
        glVertex2f((Values.WIDTH / 2) - 285 + 30 * cursorPointer, (Values.HEIGHT / 2) - 215);
        glEnd();
    }

    int draw() {
        setBackground();
        showViewPanel();
        //Выводим на экран кнопки
        for (int i = 0; i < buttons.length; i++) {
            if (Mouse.isButtonDown(0) && i != 5) {
                findActive = false;
            }
            if (buttons[i].isActive(Mouse.getX(), Values.HEIGHT - Mouse.getY())) {
                switch (i) {
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
                        if (Mouse.isButtonDown(0)) {
                            findActive = true;
                        }
                        aFind.bind();
                        break;
                }
            } else {
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
                        if (findActive || findString.size() != 0) {
                            aFind.bind();
                        } else {
                            naFind.bind();
                        }
                        break;
                }
            }
            glBegin(GL_QUADS);
            glTexCoord2f(0, 0);
            glVertex2f(buttons[i].x0, buttons[i].y0);
            glTexCoord2f(1, 0);
            glVertex2f(buttons[i].x1, buttons[i].y0);
            glTexCoord2f(1, 1);
            glVertex2f(buttons[i].x1, buttons[i].y1);
            glTexCoord2f(0, 1);
            glVertex2f(buttons[i].x0, buttons[i].y1);
            glEnd();
        }
        textInput();
        //Вывод курсора, если строка поиска активна
        if (!findActive) {
            while (Keyboard.next()) ;
        } else {
            showCursor();
        }
        //Блок ввода и вывода текста
        textOutput();
        if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            return 0;
        } else {
            return 4;
        }
    }

    void textOutput() {
        for (int i = 0; i < findString.size(); i++) {
            //Выбираем текстуру для символа
            switch (findString.get(i)) {
                case "0":
                    numbers[0].bind();
                    break;
                case "1":
                    numbers[1].bind();
                    break;
                case "2":
                    numbers[2].bind();
                    break;
                case "3":
                    numbers[3].bind();
                    break;
                case "4":
                    numbers[4].bind();
                    break;
                case "5":
                    numbers[5].bind();
                    break;
                case "6":
                    numbers[6].bind();
                    break;
                case "7":
                    numbers[7].bind();
                    break;
                case "8":
                    numbers[8].bind();
                    break;
                case "9":
                    numbers[9].bind();
                    break;
                case " ":
                    space.bind();
                    break;
                case "A":
                    enULetters[0].bind();
                    break;
                case "B":
                    enULetters[1].bind();
                    break;
                case "C":
                    enULetters[2].bind();
                    break;
                case "D":
                    enULetters[3].bind();
                    break;
                case "E":
                    enULetters[4].bind();
                    break;
                case "F":
                    enULetters[5].bind();
                    break;
                case "G":
                    enULetters[6].bind();
                    break;
                case "H":
                    enULetters[7].bind();
                    break;
                case "I":
                    enULetters[8].bind();
                    break;
                case "J":
                    enULetters[9].bind();
                    break;
                case "K":
                    enULetters[10].bind();
                    break;
                case "L":
                    enULetters[11].bind();
                    break;
                case "M":
                    enULetters[12].bind();
                    break;
                case "N":
                    enULetters[13].bind();
                    break;
                case "O":
                    enULetters[14].bind();
                    break;
                case "P":
                    enULetters[15].bind();
                    break;
                case "Q":
                    enULetters[16].bind();
                    break;
                case "R":
                    enULetters[17].bind();
                    break;
                case "S":
                    enULetters[18].bind();
                    break;
                case "T":
                    enULetters[19].bind();
                    break;
                case "U":
                    enULetters[20].bind();
                    break;
                case "V":
                    enULetters[21].bind();
                    break;
                case "W":
                    enULetters[22].bind();
                    break;
                case "X":
                    enULetters[23].bind();
                    break;
                case "Y":
                    enULetters[24].bind();
                    break;
                case "Z":
                    enULetters[25].bind();
                    break;
                case "a":
                    enLLetters[0].bind();
                    break;
                case "b":
                    enLLetters[1].bind();
                    break;
                case "c":
                    enLLetters[2].bind();
                    break;
                case "d":
                    enLLetters[3].bind();
                    break;
                case "e":
                    enLLetters[4].bind();
                    break;
                case "f":
                    enLLetters[5].bind();
                    break;
                case "g":
                    enLLetters[6].bind();
                    break;
                case "h":
                    enLLetters[7].bind();
                    break;
                case "i":
                    enLLetters[8].bind();
                    break;
                case "j":
                    enLLetters[9].bind();
                    break;
                case "k":
                    enLLetters[10].bind();
                    break;
                case "l":
                    enLLetters[11].bind();
                    break;
                case "m":
                    enLLetters[12].bind();
                    break;
                case "n":
                    enLLetters[13].bind();
                    break;
                case "o":
                    enLLetters[14].bind();
                    break;
                case "p":
                    enLLetters[15].bind();
                    break;
                case "q":
                    enLLetters[16].bind();
                    break;
                case "r":
                    enLLetters[17].bind();
                    break;
                case "s":
                    enLLetters[18].bind();
                    break;
                case "t":
                    enLLetters[19].bind();
                    break;
                case "u":
                    enLLetters[20].bind();
                    break;
                case "v":
                    enLLetters[21].bind();
                    break;
                case "w":
                    enLLetters[22].bind();
                    break;
                case "x":
                    enLLetters[23].bind();
                    break;
                case "y":
                    enLLetters[24].bind();
                    break;
                case "z":
                    enLLetters[25].bind();
                    break;
                default:
                    break;
            }
            //Выводим символ в строку поиска
            glBegin(GL_QUADS);
            glTexCoord2f(0.1F, 0.1F);
            glVertex2f((Values.WIDTH / 2) - 285 + 30 * (i), (Values.HEIGHT / 2) - 265);
            glTexCoord2f(0.9F, 0.1F);
            glVertex2f((Values.WIDTH / 2) - 290 + 30 * (i + 1), (Values.HEIGHT / 2) - 265);
            glTexCoord2f(0.9F, 0.9F);
            glVertex2f((Values.WIDTH / 2) - 290 + 30 * (i + 1), (Values.HEIGHT / 2) - 215);
            glTexCoord2f(0.1F, 0.9F);
            glVertex2f((Values.WIDTH / 2) - 285 + 30 * (i), (Values.HEIGHT / 2) - 215);
            glEnd();
        }
    }

    void textInput() {
        boolean timeLocale = Values.enLocale;
        while (Keyboard.next()) {
            if (Keyboard.getEventKeyState()) {
                if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) && Keyboard.isKeyDown(Keyboard.KEY_LMENU)) {
                    if (Values.enLocale == timeLocale) {
                        Values.enLocale = !Values.enLocale;
                        System.out.println(Values.enLocale);
                    }
                }
                if (((cursorPointer < 19) || (Keyboard.getEventKey() == Keyboard.KEY_BACK) && (findActive))) {
                    switch (Keyboard.getEventKey()) {
                        case Keyboard.KEY_BACK:
                            if (cursorPointer != 0) {
                                findString.remove(cursorPointer - 1);
                                cursorPointer--;
                            }
                            break;
                        case Keyboard.KEY_0:
                            findString.add("0");
                            cursorPointer++;
                            break;
                        case Keyboard.KEY_1:
                            findString.add("1");
                            cursorPointer++;
                            break;
                        case Keyboard.KEY_2:
                            findString.add("2");
                            cursorPointer++;
                            break;
                        case Keyboard.KEY_3:
                            findString.add("3");
                            cursorPointer++;
                            break;
                        case Keyboard.KEY_4:
                            findString.add("4");
                            cursorPointer++;
                            break;
                        case Keyboard.KEY_5:
                            findString.add("5");
                            cursorPointer++;
                            break;
                        case Keyboard.KEY_6:
                            findString.add("6");
                            cursorPointer++;
                            break;
                        case Keyboard.KEY_7:
                            findString.add("7");
                            cursorPointer++;
                            break;
                        case Keyboard.KEY_8:
                            findString.add("8");
                            cursorPointer++;
                            break;
                        case Keyboard.KEY_9:
                            findString.add("9");
                            cursorPointer++;
                            break;
                        case Keyboard.KEY_SPACE:
                            findString.add(" ");
                            cursorPointer++;
                            break;
                        case Keyboard.KEY_Q:
                            if (Values.CapsLockState != Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
                                if (Values.enLocale) {
                                    findString.add("Q");
                                } else {
                                    findString.add("Й");
                                }
                            } else {
                                if(Values.enLocale) {
                                    findString.add("q");
                                }else{
                                    findString.add("й");
                                }
                            }
                            cursorPointer++;
                            break;
                        case Keyboard.KEY_W:
                            if (Values.CapsLockState != Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
                                if(Values.enLocale) {
                                    findString.add("W");
                                }else{
                                    findString.add("Ц");
                                }
                            } else {
                                if(Values.enLocale) {
                                    findString.add("w");
                                }else{
                                    findString.add("ц");
                                }
                            }
                            cursorPointer++;
                            break;
                        case Keyboard.KEY_E:
                            if (Values.CapsLockState != Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
                                if(Values.enLocale) {
                                    findString.add("E");
                                }else{
                                    findString.add("У");
                                }
                            } else {
                                if(Values.enLocale) {
                                    findString.add("e");
                                }else{
                                    findString.add("у");
                                }
                            }
                            cursorPointer++;
                            break;
                        case Keyboard.KEY_R:
                            if (Values.CapsLockState != Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
                                if(Values.enLocale) {
                                    findString.add("R");
                                }else{
                                    findString.add("К");
                                }
                            } else {
                                if(Values.enLocale) {
                                    findString.add("r");
                                }else{
                                    findString.add("к");
                                }
                            }
                            cursorPointer++;
                            break;
                        case Keyboard.KEY_T:
                            if (Values.CapsLockState != Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
                                if(Values.enLocale) {
                                    findString.add("T");
                                }else{
                                    findString.add("Е");
                                }
                            } else {
                                if(Values.enLocale) {
                                    findString.add("t");
                                }else{
                                    findString.add("е");
                                }
                            }
                            cursorPointer++;
                            break;
                        case Keyboard.KEY_Y:
                            if (Values.CapsLockState != Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
                                if(Values.enLocale) {
                                    findString.add("Y");
                                }else{
                                    findString.add("Н");
                                }
                            } else {
                                if(Values.enLocale) {
                                    findString.add("y");
                                }else{
                                    findString.add("н");
                                }
                            }
                            cursorPointer++;
                            break;
                        case Keyboard.KEY_U:
                            if (Values.CapsLockState != Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
                                if(Values.enLocale) {
                                    findString.add("U");
                                }else{
                                    findString.add("Г");
                                }
                            } else {
                                if(Values.enLocale) {
                                    findString.add("u");
                                }else{
                                    findString.add("г");
                                }
                            }
                            cursorPointer++;
                            break;
                        case Keyboard.KEY_I:
                            if (Values.CapsLockState != Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
                                if(Values.enLocale) {
                                    findString.add("I");
                                }else{
                                    findString.add("Ш");
                                }
                            } else {
                                if(Values.enLocale) {
                                    findString.add("i");
                                }else{
                                    findString.add("ш");
                                }
                            }
                            cursorPointer++;
                            break;
                        case Keyboard.KEY_O:
                            if (Values.CapsLockState != Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
                                if(Values.enLocale) {
                                    findString.add("O");
                                }else{
                                    findString.add("щ");
                                }
                            } else {
                                if(Values.enLocale) {
                                    findString.add("o");
                                }else{
                                    findString.add("щ");
                                }
                            }
                            cursorPointer++;
                            break;
                        case Keyboard.KEY_P:
                            if (Values.CapsLockState != Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
                                if(Values.enLocale) {
                                    findString.add("P");
                                }else{
                                    findString.add("З");
                                }
                            } else {
                                if(Values.enLocale) {
                                    findString.add("p");
                                }else{
                                    findString.add("з");
                                }
                            }
                            cursorPointer++;
                            break;
                        case Keyboard.KEY_A:
                            if (Values.CapsLockState != Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
                                if(Values.enLocale) {
                                    findString.add("A");
                                }else{
                                    findString.add("Ф");
                                }
                            } else {
                                if(Values.enLocale) {
                                    findString.add("a");
                                }else {
                                    findString.add("ф");
                                }
                            }
                            cursorPointer++;
                            break;
                        case Keyboard.KEY_S:
                            if (Values.CapsLockState != Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
                                if(Values.enLocale) {
                                    findString.add("S");
                                }else{
                                    findString.add("Ы");
                                }
                            } else {
                                if(Values.enLocale) {
                                    findString.add("s");
                                }else{
                                    findString.add("ы");
                                }
                            }
                            cursorPointer++;
                            break;
                        case Keyboard.KEY_D:
                            if (Values.CapsLockState != Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
                                if(Values.enLocale) {
                                    findString.add("D");
                                }else{
                                    findString.add("В");
                                }
                            } else {
                                if(Values.enLocale) {
                                    findString.add("d");
                                }else{
                                    findString.add("в");
                                }
                            }
                            cursorPointer++;
                            break;
                        case Keyboard.KEY_F:
                            if (Values.CapsLockState != Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
                                if(Values.enLocale) {
                                    findString.add("F");
                                }else{
                                    findString.add("А");
                                }
                            } else {
                                if(Values.enLocale) {
                                    findString.add("f");
                                }else {
                                    findString.add("а");
                                }
                            }
                            cursorPointer++;
                            break;
                        case Keyboard.KEY_G:
                            if (Values.CapsLockState != Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
                                if(Values.enLocale) {
                                    findString.add("G");
                                }else{
                                    findString.add("П");
                                }
                            } else {
                                if(Values.enLocale) {
                                    findString.add("g");
                                }else{
                                    findString.add("п");
                                }
                            }
                            cursorPointer++;
                            break;
                        case Keyboard.KEY_H:
                            if (Values.CapsLockState != Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
                                if(Values.enLocale) {
                                    findString.add("H");
                                }else{
                                    findString.add("Р");
                                }
                            } else {
                                if(Values.enLocale) {
                                    findString.add("h");
                                }else{
                                    findString.add("р");
                                }
                            }
                            cursorPointer++;
                            break;
                        case Keyboard.KEY_J:
                            if (Values.CapsLockState != Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
                                if(Values.enLocale) {
                                    findString.add("J");
                                }else{
                                    findString.add("О");
                                }
                            } else {
                                if(Values.enLocale) {
                                    findString.add("j");
                                }else{
                                    findString.add("о");
                                }
                            }
                            cursorPointer++;
                            break;
                        case Keyboard.KEY_K:
                            if (Values.CapsLockState != Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
                                if(Values.enLocale) {
                                    findString.add("K");
                                }else{
                                    findString.add("Л");
                                }
                            } else {
                                if(Values.enLocale) {
                                    findString.add("k");
                                }else{
                                    findString.add("л");
                                }
                            }
                            cursorPointer++;
                            break;
                        case Keyboard.KEY_L:
                            if (Values.CapsLockState != Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
                                if(Values.enLocale) {
                                    findString.add("L");
                                }else{
                                    findString.add("Д");
                                }
                            } else {
                                if(Values.enLocale) {
                                    findString.add("l");
                                }else{
                                    findString.add("д");
                                }
                            }
                            cursorPointer++;
                            break;
                        case Keyboard.KEY_Z:
                            if (Values.CapsLockState != Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
                                if(Values.enLocale) {
                                    findString.add("Z");
                                }else{
                                    findString.add("Я");
                                }
                            } else {
                                if(Values.enLocale) {
                                    findString.add("z");
                                }else{
                                    findString.add("я");
                                }
                            }
                            cursorPointer++;
                            break;
                        case Keyboard.KEY_X:
                            if (Values.CapsLockState != Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
                                if(Values.enLocale) {
                                    findString.add("X");
                                }else{
                                    findString.add("Ч");
                                }
                            } else {
                                if(Values.enLocale) {
                                    findString.add("x");
                                }else{
                                    findString.add("ч");
                                }
                            }
                            cursorPointer++;
                            break;
                        case Keyboard.KEY_C:
                            if (Values.CapsLockState != Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
                                if(Values.enLocale) {
                                    findString.add("C");
                                }else{
                                    findString.add("С");
                                }
                            } else {
                                if(Values.enLocale) {
                                    findString.add("c");
                                }else{
                                    findString.add("с");
                                }
                            }
                            cursorPointer++;
                            break;
                        case Keyboard.KEY_V:
                            if (Values.CapsLockState != Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
                                if(Values.enLocale) {
                                    findString.add("V");
                                }else{
                                    findString.add("М");
                                }
                            } else {
                                if(Values.enLocale) {
                                    findString.add("v");
                                }else{
                                    findString.add("м");
                                }
                            }
                            cursorPointer++;
                            break;
                        case Keyboard.KEY_B:
                            if (Values.CapsLockState != Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
                                if(Values.enLocale) {
                                    findString.add("B");
                                }else{
                                    findString.add("И");
                                }
                            } else {
                                if(Values.enLocale) {
                                    findString.add("b");
                                }else{
                                    findString.add("и");
                                }
                            }
                            cursorPointer++;
                            break;
                        case Keyboard.KEY_N:
                            if (Values.CapsLockState != Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
                                if(Values.enLocale) {
                                    findString.add("N");
                                }else{
                                    findString.add("Т");
                                }
                            } else {
                                if(Values.enLocale) {
                                    findString.add("n");
                                }else{
                                    findString.add("т");
                                }
                            }
                            cursorPointer++;
                            break;
                        case Keyboard.KEY_M:
                            if (Values.CapsLockState != Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
                                if(Values.enLocale) {
                                    findString.add("M");
                                }else{
                                    findString.add("Ь");
                                }
                            } else {
                                if(Values.enLocale) {
                                    findString.add("m");
                                }else{
                                    findString.add("ь");
                                }
                            }
                            cursorPointer++;
                            break;
                        case Keyboard.KEY_LBRACKET:
                            if (Values.CapsLockState != Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
                                findString.add("Х");
                            }else{
                                findString.add("х");
                            }
                            cursorPointer++;
                            break;
                        case Keyboard.KEY_RBRACKET:
                            if (Values.CapsLockState != Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
                                findString.add("Ъ");
                            }else{
                                findString.add("ъ");
                            }
                            cursorPointer++;
                            break;
                        case Keyboard.KEY_SEMICOLON:
                            if (Values.CapsLockState != Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
                                findString.add("Ж");
                            }else{
                                findString.add("ж");
                            }
                            cursorPointer++;
                            break;
                        case Keyboard.KEY_APOSTROPHE:
                            if (Values.CapsLockState != Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
                                findString.add("Э");
                            }else{
                                findString.add("э");
                            }
                            cursorPointer++;
                            break;
                        case Keyboard.KEY_COMMA:
                            if (Values.CapsLockState != Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
                                findString.add("Б");
                            }else{
                                findString.add("б");
                            }
                            cursorPointer++;
                            break;
                        case Keyboard.KEY_PERIOD:
                            if (Values.CapsLockState != Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
                                findString.add("Ю");
                            }else{
                                findString.add("ю");
                            }
                            cursorPointer++;
                            break;
                        case Keyboard.KEY_LEFT:
                            if (cursorPointer != 0) {
                                cursorPointer--;
                            }
                            break;
                        case Keyboard.KEY_RIGHT:
                            if (cursorPointer != findString.size()) {
                                cursorPointer++;
                            }
                            break;
                        case Keyboard.KEY_CAPITAL:
                            Values.CapsLockState = !Values.CapsLockState;
                            System.out.println(Values.CapsLockState);
                            break;
                    }
                    System.out.println(findString + " " + cursorPointer);
                }
            }
        }
    }

    long getTime() {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }

    int getDeltaTime() {
        long thisFrame = getTime();
        int delta = (int) (thisFrame - lastFrame);
        lastFrame = thisFrame;
        return delta;
    }

    void release() {
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
        for (int i = 0; i > numbers.length; i++) {
            numbers[i].release();
        }
        for (int i = 0; i < enULetters.length; i++) {
            enULetters[i].release();
            enLLetters[i].release();
        }
        space.release();
    }
}