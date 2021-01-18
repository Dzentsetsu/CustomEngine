package scene;

public class GameScene extends Scene{
    private static final String NAME = "GameScene";
    public boolean isOn;

    public String getName() {
        return NAME;
    }

    public GameScene(){
        super();
        isOn = false;
    }
}
