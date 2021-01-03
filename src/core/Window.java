package core;
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

import java.io.InputStream;
import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {

    private static Window window = null;
    private long GLFWwindow;

    private int height;
    private int width;
    private String title;

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

    public void init(){

        System.out.println("LWjGL ver. you are working with: "+ Version.getVersion());
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if ( !glfwInit() )
            throw new IllegalStateException("Unable to initialize GLFW");

        // Configure GLFW
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

        GLFWwindow = glfwCreateWindow(width, height, title, NULL, NULL);
        if ( GLFWwindow == NULL )
            throw new RuntimeException("Failed to create the GLFW window");

        try ( MemoryStack stack = stackPush() ) {
            IntBuffer pWidth = stack.mallocInt(1); // int*
            IntBuffer pHeight = stack.mallocInt(1); // int*

            // Get the window size passed to glfwCreateWindow
            glfwGetWindowSize(GLFWwindow, pWidth, pHeight);

            // Get the resolution of the primary monitor
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            // Center the window
            glfwSetWindowPos(
                    GLFWwindow,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );
        } // the stack frame is popped automati


        // Make the OpenGL context current
        glfwMakeContextCurrent(GLFWwindow);
        // Enable v-sync
        glfwSwapInterval(GL_TRUE);
        // Make the window visible
        glfwShowWindow(GLFWwindow);
        GL.createCapabilities();
    }

    public void loop(){
        glClearColor(1,0.5f,1,1);

        while ( !glfwWindowShouldClose(GLFWwindow) ) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

            glfwSwapBuffers(GLFWwindow);
            glfwPollEvents();
        }
    }
    public void stop(){
        cleanUp();
    }
    private void cleanUp(){}

}
