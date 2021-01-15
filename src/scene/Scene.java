package scene;

import core.Loader;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import shaders.DefaultShader;

public class Scene {
    public DefaultShader shader = null;
    public Loader loader = null;

    public Scene(){
        shader = new DefaultShader();
        loader = new Loader(shader);
    }

    public String getName(){return null;}
    public void update(float deltaTime) {
    }

    public void init(){

    }
    public void update(){

    }
}
