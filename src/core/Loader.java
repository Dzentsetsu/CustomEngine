package core;

import org.lwjgl.BufferUtils;
import shaders.DefaultShader;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL30.*;

public class Loader {

    DefaultShader shader;

    private float[] vertexData = {
    // vertex position (NDC)    // vertex colour (RGBA)
             0.5f, -0.5f, 0,     1.0f, 0.0f, 0.0f, 1.0f,   // 0
            0.5f, 0.5f, 0,     1.0f, 1.0f, 0.0f, 1.0f,   // 1
             -0.5f,  0.5f,  0,     1.0f, 0.0f, 1.0f, 1.0f,   // 2
            -0.5f,-0.5f, 0,     1.0f, 0.0f, 1.0f, 1.0f    // 3
    };
    private int[] indices = {
            0,1,2, // 1-st triangle
            2,3,0 // 2-nd triangle
    };

    public Loader(DefaultShader shader){
        this.shader = shader;
        loadDataToGPU();
    }

    private int vaoID, vboID, eboID;
    private void createVAO(){
        vaoID = glGenVertexArrays();
        glBindVertexArray(vaoID);
    }
    private void createVBO(){
        vboID = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER,vboID);

        FloatBuffer fb = BufferUtils.createFloatBuffer(vertexData.length);
        fb.put(vertexData).flip();
        glBufferData(GL_ARRAY_BUFFER, fb,GL_STATIC_DRAW);
    }
    private void createEBO(){
        eboID = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, eboID);

        IntBuffer ib = BufferUtils.createIntBuffer(indices.length);
        ib.put(indices).flip();
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, ib, GL_STATIC_DRAW);
    }

    private void addPointers(){
        glVertexAttribPointer(0,3,GL_FLOAT,false,(3+4)*Float.BYTES,0);
        glEnableVertexAttribArray(0);
        glVertexAttribPointer(1,4,GL_FLOAT,false,(3+4)*Float.BYTES,3*Float.BYTES);
        glEnableVertexAttribArray(1);
    }
    private void loadDataToGPU(){
        createVAO();
        createEBO();
        createVBO();
        addPointers();
    }

    public void draw(){

        glDrawElements(GL_TRIANGLES, indices.length,GL_UNSIGNED_INT,0);
    }
}
