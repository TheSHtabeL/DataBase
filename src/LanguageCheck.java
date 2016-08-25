import org.lwjgl.input.Keyboard;

public class LanguageCheck {
    private boolean permission = true;

    public void updateLanguage(){
        //Класс отслеживает состояние управляющих клавиш для печати
        while(Keyboard.next()){
            if(Keyboard.getEventKeyState()){
                if(Keyboard.getEventKey() == Keyboard.KEY_CAPITAL){
                    Values.CapsLockState = !Values.CapsLockState; //Отслеживание состояние CapsLock
                }else if( Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) & Keyboard.isKeyDown(Keyboard.KEY_LMENU) & permission){
                    permission = false;
                    Values.enLocale = !Values.enLocale; //Отслеживает переключение раскладки клавиатуры
                }else{
                    permission = true;
                }
            }
        }
    }
    public boolean isShiftDown(){
        return Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) ? true : false;
    }
}
