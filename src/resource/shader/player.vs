#version 330 core

in vec3 vPosition;
in vec2 vTexCoord;

out vec2 fTexCoord;

uniform mat4 projectionMatrix;
uniform mat4 translationMatrix;

void main() {
    gl_Position = projectionMatrix * translationMatrix *
                  vec4(vPosition, 1);
    fTexCoord = vTexCoord;
}