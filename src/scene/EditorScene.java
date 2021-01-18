package scene;

public class EditorScene extends Scene{
    private static final String NAME = "LevelEditorScene";
    public boolean isOn;

    public String getName() {
        return NAME;
    }

    public EditorScene(){
        super();
        isOn = true;
    }
}
