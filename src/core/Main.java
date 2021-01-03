package core;

public class Main {
    public static void main(String[] args){
        Window window = Window.getWindow();

        window.init();
        window.loop();
        window.stop();

    }
}
