package Scene;

public class EditorScene extends Scene{
    private static final String NAME = "LevelEditorScene";
    public boolean isOn;
    @Override
    public String getName() {
        return NAME;
    }

    public EditorScene(){
        isOn = true;
    }
    @Override
    public void update(float deltaTime) {

    }
}
