package scene;

import core.Loader;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import shaders.DefaultShader;

public class Scene {

    public static DefaultShader shader = null;
    public static Loader loader = null;

    public Scene(){
        shader = new DefaultShader();
        loader = new Loader(shader);
    }

    public void update(float deltaTime) {
    }

}
