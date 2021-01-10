package Scene;

public class GameScene extends Scene{
    private static final String NAME = "GameScene";
    public boolean isOn;
    @Override
    public String getName() {
        return NAME;
    }

    public GameScene(){
        isOn = false;
    }

    @Override
    public void update(float deltaTime) {

    }


}
