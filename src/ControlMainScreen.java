import org.lwjgl.opengl.Display;

public class ControlMainScreen extends Control{
    public int navigate(){
        GUI gui = initGUI();
        gui.init();
        LanguageCheck languageCheck = new LanguageCheck();
        DataBase dataBase = new DataBase();
        if(!Values.Connection) {
            dataBase.createConnection();
            dataBase.joinBase();
            Values.Connection = true;
        }

        while(true) {
            if (Display.isCloseRequested()) {
                gui.release();
                System.exit(0);
            }
            int i = gui.draw();
            switch(i){
                case 0:
                case 1:
                case 2:
                case 3:
                    gui.release();
                    dataBase.closeConnection();
                    Values.Connection = false;
                    return i;
                case 4:
                    dataBase.joinBase();
                    break;
                case 5:
                    dataBase.createTable("HelloFromProgram(name VARCHAR(10))");
                    break;
                default:
            }
            gui.update();
        }
    }
    GUI initGUI(){
        GUI gui = new GUImainScreen();
        return gui;
    }
}
