package MainMenu;

public class Button {
    int x0;
    int x1;
    int y0;
    int y1;

    Button(int x0,int y0,int x1,int y1) {
        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.y1 = y1;
    }
    boolean isActive(int mouseX,int mouseY){
        return ( ( (mouseX>= x0) && (mouseX<=x1) ) && ( (mouseY>=y0) && (mouseY<=y1) ));
    }
}
