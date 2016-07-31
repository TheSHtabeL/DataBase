import MainMenu.*;
import Settings.Control;

public class main {
    public static void main(String[] args) {
        MainMenu.Control mainMenu = new MainMenu.Control();
        Settings.Control settings = new Settings.Control();
        Init init = new Init();
        init.init();
        int flag = 0;
        while(true) {
            flag = mainMenu.main();
            switch (flag){
                case 0:
                    break;
                case 1:
                    settings.main();
                    break;
                case 2:
                default:
                    return;
            }
        }
    }
}
