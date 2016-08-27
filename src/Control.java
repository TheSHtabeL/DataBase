import org.lwjgl.opengl.Display;

abstract public class Control {
    public int navigate(){
        GUI gui = initGUI();
        gui.init();
        LanguageCheck languageCheck = new LanguageCheck();

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
                    return i;
            }
            gui.update();
            languageCheck.updateLanguage();
        }
    }
    abstract GUI initGUI();
}