import org.lwjgl.opengl.Display;

public class main {
//    public static void main(String[] args) {
//        MainMenu.Control mainMenu = new MainMenu.Control();
//        Settings.Control settings = new Settings.Control();
//        MainScreen.Control mainScreen = new MainScreen.Control();
//        Init init = new Init();
//        init.init();
//        int flag = 0;
//        while(true) {
//            flag = mainMenu.main();
//            switch (flag){
//                case 0:
//                    mainScreen.control();
//                    break;
//                case 1:
//                    settings.main();
//                    break;
//                case 2:
//                default:
//                    return;
//            }
//        }
//    }
    public static void main(String[] args) {
        Control control = new ControlMainMenu();
        Init init = new Init();
        init.init();
        int flag = 0;
        while(true){
            flag = control.navigate();
            switch (flag){
                case 0:
                    control = new ControlMainMenu();
                    break;
                case 1:
                    control = new ControlMainScreen();
                    break;
                case 2:
                    control = new ControlSettings();
                    break;
                case 3:
                default:
                    Display.destroy();
                    return;

            }
        }
    }
}
