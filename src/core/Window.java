package core;
import scene.Scene;
import scene.SceneManager;
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {

    public static int FPS = 0;
    public static double deltaTime = 0d;
    private static Window window = null;
    private static long glfwWindow;

    Scene scene = null;

    private int height;
    private int width;
    private String title;

    float r = 0.4f;
    float g = 1f;
    float b = 1f;
    float a = 1f;

    public static Window getWindow(){
        if(window==null){
            window = new Window(720,1280, "Gnome");
            return window;
        }
        return  window;
    }

    private Window(int initialHeight, int initialWidth, String initialTitle){
        this.height = initialHeight;
        this.width = initialWidth;
        this.title = initialTitle;
    }

    public void init() {
        System.out.println("LWjGL ver. you are working with: " + Version.getVersion());
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if (!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");

        // Configure GLFW
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

        glfwWindow = glfwCreateWindow(width, height, title, NULL, NULL);
        if (glfwWindow == NULL)
            throw new RuntimeException("Failed to create the GLFW window");

        glfwSetKeyCallback(glfwWindow, KeyListener::key_event);

        try (MemoryStack stack = stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1); // int*
            IntBuffer pHeight = stack.mallocInt(1); // int*

            // Get the window size passed to glfwCreateWindow
            glfwGetWindowSize(glfwWindow, pWidth, pHeight);
            // Get the resolution of the primary monitor
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
            // Center the window
            glfwSetWindowPos(
                    glfwWindow,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );
        } // the stack frame is popped automaticly

        // Make the OpenGL context current
        glfwMakeContextCurrent(glfwWindow);
        // Enable v-sync
        glfwSwapInterval(GL_TRUE);
        GL.createCapabilities();
        // Make the window visible
        glfwShowWindow(glfwWindow);
        //glfwSetKeyCallback(GLFWwindow, KeyBoardListener::key_event);
        scene = new Scene();

    }
        public void loop() {

            while (!glfwWindowShouldClose(glfwWindow)) {
                long startTime = System.nanoTime();
                glClearColor(r, g, b, a);
                glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

                if(KeyListener.EnterWasPressed) {
                  r = Math.max(r - 0.01f, 0);
                  g = Math.max(g - 0.01f, 0);
                  b = Math.max(b - 0.01f, 0);
                  a = Math.max(a - 0.01f, 0);
                }
                scene.loader.draw();
                glfwSwapBuffers(glfwWindow);
                glfwPollEvents();

                long endTime = System.nanoTime();
                deltaTime = (endTime - startTime) * 1E-9;
                FPS = (int)(1.0f / deltaTime);

                 //System.out.println("FPS "+Math.round(FPS));
            }
            stop();
        }
    public void stop(){
        cleanUp();
    }
    private void cleanUp(){
        // Free the window callbacks and destroy the window
        glfwFreeCallbacks(glfwWindow);
        glfwDestroyWindow(glfwWindow);

        // Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

}
