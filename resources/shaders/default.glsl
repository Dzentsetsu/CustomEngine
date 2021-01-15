#type vertex
#version 330 core

layout (location = 0) in vec3 aPos;
layout (location = 1) in vec4 aColour;

out vec4 fColour;

void main(){
    fColour = aColour;
    gl_Position = vec4(aPos, 1.0);
}

#type fragment
#version 330 core

in vec4 fColour;
out vec4 final_Colour;
void main(){
    final_Colour = fColour;
}