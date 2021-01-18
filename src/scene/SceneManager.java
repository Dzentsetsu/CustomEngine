package scene;

public class SceneManager {

    private static SceneManager sceneManager = null;
    private static EditorScene editorScene;
    private static GameScene gameScene;

    SceneManager(EditorScene edS, GameScene gameS){
        editorScene = edS;
        gameScene = gameS;
    }

    public static SceneManager getInstance(){
        if(sceneManager == null){
            sceneManager = new SceneManager(new EditorScene(),new GameScene());
        }
        return sceneManager;
    }

    public static EditorScene getEditorScene() {
        return editorScene;
    }

    public static GameScene getGameScene(){
        return  gameScene;
    }

    public static void switchScene(){
        if(editorScene.isOn){
            editorScene.isOn = false;
            gameScene.isOn = true;
            System.out.println("Switched to game view");
            //
        } else {
            gameScene.isOn = false;
            editorScene.isOn = true;
            System.out.println("Switched to editor view");
        }
    }
}
