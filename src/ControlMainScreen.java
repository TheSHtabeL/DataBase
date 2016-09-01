import org.lwjgl.opengl.Display;

public class ControlMainScreen extends Control{
    public int navigate(){
        GUI gui = initGUI();
        gui.init();
        LanguageCheck languageCheck = new LanguageCheck();
        DataBase dataBase = new DataBase();
        if(!Values.Connection) {
            dataBase.createConnection();
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
            }
            gui.update();
        }
    }
    GUI initGUI(){
        GUI gui = new GUImainScreen();
        return gui;
    }
}
