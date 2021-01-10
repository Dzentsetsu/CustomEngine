package core;

public class Main {
    private static Window game = Window.getWindow();
    public static void main(String[] args){
        game.init();
        game.loop();
    }
}
