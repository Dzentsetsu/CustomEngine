package scene;

public class EditorScene extends Scene{
    private static final String NAME = "LevelEditorScene";
    public boolean isOn;
    @Override
    public String getName() {
        return NAME;
    }

    public EditorScene(){
        super();
        isOn = true;
    }

    public void init(){
        super.init();
    }
    @Override
    public void update(float deltaTime) {

    }
}
