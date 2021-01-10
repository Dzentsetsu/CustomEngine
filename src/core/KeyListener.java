package core;

import Scene.SceneManager;

import static org.lwjgl.glfw.GLFW.*;

public class KeyListener {

    public static boolean EnterWasPressed = false;

    static void key_event(long glfwWindow, int key, int scanmode, int action, int mod){
        if(key == GLFW_KEY_ENTER && action == GLFW_PRESS){
            EnterWasPressed = true;
            System.out.println("Enter was pressed");
        }
        if(key == GLFW_KEY_ESCAPE && action == GLFW_PRESS){
            glfwSetWindowShouldClose(glfwWindow,true);
        }

        if(key == GLFW_KEY_SPACE && action == GLFW_PRESS){
            SceneManager.switchScene();
        }

    }

}
