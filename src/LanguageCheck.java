import org.lwjgl.input.Keyboard;

public class LanguageCheck {
    private boolean localePermission = true;
    private boolean capsPermission = true;

    public void updateLanguage(){
        //Класс отслеживает состояние управляющих клавиш для печати
        while(Keyboard.next()) {
            if (capsPermission && Keyboard.isKeyDown(Keyboard.KEY_CAPITAL)) {
                Values.CapsLockState = !Values.CapsLockState; //Отслеживание состояние CapsLock
                System.out.println("Caps: " + Values.CapsLockState);
                capsPermission = false;
            } else {
                capsPermission = true;
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) && Keyboard.isKeyDown(Keyboard.KEY_LMENU) && localePermission) {
                localePermission = false;
                Values.enLocale = !Values.enLocale; //Отслеживает переключение раскладки клавиатуры
                System.out.println("Locale: " + Values.enLocale);
            } else {
                localePermission = true;
            }
        }
    }
    public boolean isShiftDown(){
        return Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) ? true : false;
    }
}
