package scene;

public class GameScene extends Scene{
    private static final String NAME = "GameScene";
    public boolean isOn;
    @Override
    public String getName() {
        return NAME;
    }

    public GameScene(){
        super();
        isOn = false;
    }

    public void init(){
        super.init();
    }

    @Override
    public void update(float deltaTime) {

    }


}
