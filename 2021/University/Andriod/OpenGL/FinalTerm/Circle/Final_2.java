package com.example.minseoblim_201621228_final_2;

import android.opengl.GLES20;
import android.opengl.Matrix;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Final_2
{
    private final float[] mTranslationMatrix = new float[16];
    private final float[] result_mvpMatrix_Translation = new float[16];

    private final float[] mRotationMatrix = new float[16];
    private final float[] result_mvpMatrix_Rotation = new float[16];
    private final float[] result_mvpMatrix_Translation_Rotation = new float[16];

    private int vPMatrixHandle;

    private FloatBuffer circle_Buffer1,circle_Buffer2, square_Buffer1;

    private final int mProgram;

    private int positionHandle;
    private int colorHandle;

    private final int vertexStride = COORDS_PER_VERTEX * 4; // 4 bytes per vertex

    static  final int COORDS_PER_VERTEX =3;
    static int vertex_number = 100;

    static float Circle_Coordinate_1 [] = new float [vertex_number * 3];

    // Set color with red, green, blue and alpha (opacity) values
    float color_gray[] = { 0.8f, 0.8f, 0.8f, 1.0f };
    float color_yellow[] = { 255/255f, 212/255f, 0f, 1.0f };
    float color_brown[] = { 153/255f, 76/255f, 0f, 1.0f };
    float color_sky[] = { 153/255f, 204/255f, 1f, 1.0f };
    float color_black[] = {0f,0f,0f,1.0f};
    float color_pink[] = {1f,102/255f,1f,1.0f};
    float color_green[] = {153/255f, 1f, 153/255f, 1f};
    float color_red[] = {1f, 0, 0, 1f};
    float color_white [] = {1,1,1,0};

    float color_dark_green [] = {0,1,0,1};

    static float square_Coordinate1[] = {

            0.28f, -0.15f, 0.0f,
            0.28f, -1.2f, 0.0f,
            0.13f, -0.15f, 0.0f,
            0.13f, -1.2f, 0.0f,
    };



    public Final_2()
    {

        int vertexShader = MyGLRenderer.loadShader(GLES20.GL_VERTEX_SHADER,
                vertexShaderCode);
        int fragmentShader = MyGLRenderer.loadShader(GLES20.GL_FRAGMENT_SHADER,
                fragmentShaderCode);

        // create empty OpenGL ES Program
        mProgram = GLES20.glCreateProgram();

        // add the vertex shader to program
        GLES20.glAttachShader(mProgram, vertexShader);

        // add the fragment shader to program
        GLES20.glAttachShader(mProgram, fragmentShader);

        // creates OpenGL ES program executables
        GLES20.glLinkProgram(mProgram);

        float d_angle = (float) Math.PI / 14;

        /// create circle1
        float circle1_R1 = 0.43f;
        float circle1_R2 = 0.37f;
        float circle1_x = 0.0f;
        float circle1_y = 0.45f;

        Circle_Coordinate_1[0]=circle1_x;
        Circle_Coordinate_1[1]=circle1_y;
        Circle_Coordinate_1[2]=0;

        int index = 3;

        for (float angle = 0.0f; angle <= 2 * Math.PI + d_angle ; angle = angle + d_angle)
        {

            Circle_Coordinate_1[index] = circle1_x + (circle1_R1+0.0f) * (float) Math.cos(angle);
            Circle_Coordinate_1[index+1] = circle1_y + (circle1_R2+0.0f) * (float) Math.sin(angle);
            Circle_Coordinate_1[index+2]=0;

            index=index+3;
        }

        // initialize vertex byte buffer for shape coordinates
        ByteBuffer bb0 = ByteBuffer.allocateDirect(
                // (number of coordinate values * 4 bytes per float)
                Circle_Coordinate_1.length * 4);
        // use the device hardware's native byte order
        bb0.order(ByteOrder.nativeOrder());

        // create a floating point buffer from the ByteBuffer
        circle_Buffer1 = bb0.asFloatBuffer();
        // add the coordinates to the FloatBuffer
        circle_Buffer1.put(Circle_Coordinate_1);
        // set the buffer to read the first coordinate
        circle_Buffer1.position(0);


        index = 3;

        /// create  small_circles (white)
        float circle1_R = 0.045f;
        circle1_x = 0.0f;
        circle1_y = 0.0f;

        Circle_Coordinate_1[0]=circle1_x;
        Circle_Coordinate_1[1]=circle1_y;
        Circle_Coordinate_1[2]=0;


        for (float angle = 0.0f; angle <= 2 * Math.PI + d_angle ; angle = angle + d_angle)
        {

            Circle_Coordinate_1[index] = circle1_x + (circle1_R+0.0f) * (float) Math.cos(angle);
            Circle_Coordinate_1[index+1] = circle1_y + (circle1_R+0.0f) * (float) Math.sin(angle);
            Circle_Coordinate_1[index+2]=0;

            index=index+3;
        }

        // initialize vertex byte buffer for shape coordinates
        ByteBuffer bb1 = ByteBuffer.allocateDirect(
                // (number of coordinate values * 4 bytes per float)
                Circle_Coordinate_1.length * 4);
        // use the device hardware's native byte order
        bb1.order(ByteOrder.nativeOrder());

        // create a floating point buffer from the ByteBuffer
        circle_Buffer2 = bb1.asFloatBuffer();
        // add the coordinates to the FloatBuffer
        circle_Buffer2.put(Circle_Coordinate_1);
        // set the buffer to read the first coordinate
        circle_Buffer2.position(0);


        // initialize vertex byte buffer for shape coordinates
        ByteBuffer bb2 = ByteBuffer.allocateDirect(
                // (number of coordinate values * 4 bytes per float)
                square_Coordinate1.length * 4);
        // use the device hardware's native byte order
        bb2.order(ByteOrder.nativeOrder());

        // create a floating point buffer from the ByteBuffer
        square_Buffer1 = bb2.asFloatBuffer();
        // add the coordinates to the FloatBuffer
        square_Buffer1.put(square_Coordinate1);
        // set the buffer to read the first coordinate
        square_Buffer1.position(0);


    }


    public void draw(float [] mvpMatrix)
    {
        // Add program to OpenGL ES environment
        GLES20.glUseProgram(mProgram);

        // get handle to vertex shader's vPosition member
        positionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");

        // Enable a handle to the triangle vertices
        GLES20.glEnableVertexAttribArray(positionHandle);

        colorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");

        vPMatrixHandle = GLES20.glGetUniformLocation(mProgram,"uMVPMatrix");


        // ------------------------------------------------------- //

        GLES20.glVertexAttribPointer(positionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                vertexStride, square_Buffer1);

        GLES20.glUniform4fv(colorHandle, 1, color_dark_green, 0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 4);


        GLES20.glVertexAttribPointer(positionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                vertexStride, circle_Buffer1);


        Matrix.setIdentityM(mTranslationMatrix,0);
        Matrix.translateM(mTranslationMatrix,0,0f,-0f,0.0f);
        Matrix.multiplyMM(result_mvpMatrix_Translation,0,mvpMatrix,0,mTranslationMatrix,0);
        GLES20.glUniformMatrix4fv(vPMatrixHandle,1,false,result_mvpMatrix_Translation,0);

        GLES20.glUniform4fv(colorHandle, 1, color_red, 0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 0, 16);


        GLES20.glVertexAttribPointer(positionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                vertexStride, circle_Buffer2);


        Matrix.setIdentityM(mTranslationMatrix,0);
        Matrix.translateM(mTranslationMatrix,0,0.29f,0.54f,0.0f);
        Matrix.multiplyMM(result_mvpMatrix_Translation,0,mvpMatrix,0,mTranslationMatrix,0);
        GLES20.glUniformMatrix4fv(vPMatrixHandle,1,false,result_mvpMatrix_Translation,0);

        GLES20.glUniform4fv(colorHandle, 1, color_white, 0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 0, 30);


        // cut part2
        Matrix.setIdentityM(mTranslationMatrix,0);
        Matrix.translateM(mTranslationMatrix,0,0.01f,0.68f,0.0f);
        Matrix.multiplyMM(result_mvpMatrix_Translation,0,mvpMatrix,0,mTranslationMatrix,0);
        GLES20.glUniformMatrix4fv(vPMatrixHandle,1,false,result_mvpMatrix_Translation,0);

        GLES20.glUniform4fv(colorHandle, 1, color_white, 0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 0, 30);


        // cut part3
        Matrix.setIdentityM(mTranslationMatrix,0);
        Matrix.translateM(mTranslationMatrix,0,-0.2f,0.6f,0.0f);
        Matrix.multiplyMM(result_mvpMatrix_Translation,0,mvpMatrix,0,mTranslationMatrix,0);
        GLES20.glUniformMatrix4fv(vPMatrixHandle,1,false,result_mvpMatrix_Translation,0);

        GLES20.glUniform4fv(colorHandle, 1, color_white, 0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 0, 30);

        // Disable vertex array
        GLES20.glDisableVertexAttribArray(positionHandle);
    }

    private final String fragmentShaderCode =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "  gl_FragColor = vColor;" +
                    "}";

    private final String vertexShaderCode =
            "uniform mat4 uMVPMatrix;" +
                    "attribute vec4 vPosition;" +
                    "void main() {" +
                    "  gl_Position = uMVPMatrix * vPosition;" +
                    "}";
}

