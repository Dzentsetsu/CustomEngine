package shaders;

import static org.lwjgl.opengl.GL20.*;

public class DefaultShader {
    public int vertID, fragID, shaderProgramID;

    private final String verShaderSourse = "#version 330 core\n" +
            "\n" +
            "layout (location = 0) in vec3 aPos;\n" +
            "layout (location = 1) in vec4 aColour;\n" +
            "\n" +
            "out vec4 fColour;\n" +
            "\n" +
            "void main(){\n" +
            "    fColour = aColour;\n" +
            "    gl_Position = vec4(aPos, 1.0);\n" +
            "}";

    private final String fragShaderSourse = "#version 330 core\n" +
            "\n" +
            "in vec4 fColour;\n" +
            "out vec4 final_Colour;\n" +
            "void main(){\n" +
            "    final_Colour = fColour;\n" +
            "}";

    public DefaultShader(){
        //Creating vertex shader
        vertID = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vertID,verShaderSourse);
        glCompileShader(vertID);
        checkingStatus(vertID);

        //Creating fragment shader
        fragID = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fragID,fragShaderSourse);
        glCompileShader(fragID);
        checkingStatus(fragID);

        //Creating shaderProgram where we link vertex and fragment shaders
        shaderProgramID = glCreateProgram();
        glAttachShader(shaderProgramID,vertID);
        glAttachShader(shaderProgramID,fragID);
        glLinkProgram(shaderProgramID);

        if (glGetProgrami(shaderProgramID, GL_LINK_STATUS) == GL_TRUE) {
            System.out.println("Successfully Linked");
        } else {
            System.out.println(glGetProgramInfoLog(shaderProgramID));
        }
        glValidateProgram(shaderProgramID);
        glUseProgram(shaderProgramID);
    }

    private void checkingStatus(int shader){
        if(glGetShaderi(shader,GL_COMPILE_STATUS) == GL_FALSE){
            System.out.println(glGetShaderi(shader, GL_SHADER_TYPE));
            System.out.println(glGetShaderInfoLog(shader, 500));
            System.exit(1);
        }
    }
}
