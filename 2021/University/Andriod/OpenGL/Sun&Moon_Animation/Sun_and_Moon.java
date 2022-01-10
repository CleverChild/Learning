package com.example.sun_and_moon_hw6;

import android.opengl.GLES20;
import android.opengl.Matrix;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Sun_and_Moon
{
    private final float[] mTranslationMatrix = new float[16];
    private final float[] result_mvpMatrix_Translation = new float[16];

    private final float[] mRotationMatrix = new float[16];
    private final float[] result_mvpMatrix_Rotation = new float[16];
    private final float[] result_mvpMatrix_Translation_Rotation = new float[16];

    private int vPMatrixHandle;

    private FloatBuffer vertexBuffer_Door, vertexBuffer_Circle1, vertexBuffer_Ellipse1;
    private FloatBuffer vertexBuffer_Big_Circle1;

    private final int mProgram;
    private int status=0;


    private int positionHandle;
    private int colorHandle;

    private final int vertexStride = COORDS_PER_VERTEX * 4; // 4 bytes per vertex

    static  final int COORDS_PER_VERTEX =3;
    static int vertex_number = 100;

    static float Circle_Coordinate_1 [] = new float [vertex_number * 3];
    static float Circle_Coordinate_2 [] = new float [vertex_number * 3];

    static float Big_Circle_Coordinate_1 [] = new float [vertex_number * 3];


    static float Door_Coordinate_1[] = {

            /// Door
            0.25f, -0.2f, 0.0f,
            0.25f, -0.7f, 0.0f,
            -0.25f, -0.2f, 0.0f,
            -0.25f, -0.7f, 0.0f,
    };


    // Set color with red, green, blue and alpha (opacity) values
    float color_gray[] = { 0.8f, 0.8f, 0.8f, 1.0f };
    float color_yellow[] = { 255/255f, 212/255f, 0f, 1.0f };
    float color_brown[] = { 153/255f, 76/255f, 0f, 1.0f };
    float color_sky[] = { 153/255f, 204/255f, 1f, 1.0f };
    float color_black[] = {0f,0f,0f,1.0f};
    float color_pink[] = {1f,102/255f,1f,1.0f};
    float color_green[] = {153/255f, 1f, 153/255f, 1f};
    float color_red[] = {1f, 0, 0, 1f};

    float sun_x=-0.51f;
    float d_sun_x=0.005f;

    float sun_y=0f;
    float d_sun_y=0f;

    float moon_x;
    float d_moon_x;

    float moon_y;
    float d_moon_y;

    public Sun_and_Moon()
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

        /// create circle1  (full circle)
        float circle1_R = 0.14f;
        float circle1_x = 0.0f;
        float circle1_y = 0.0f;

        Circle_Coordinate_1[0]=circle1_x;
        Circle_Coordinate_1[1]=circle1_y;
        Circle_Coordinate_1[2]=0;

        int index = 3;

        for (float angle = 0.0f; angle <= 2 * Math.PI + d_angle ; angle = angle + d_angle)
        {

            Circle_Coordinate_1[index] = circle1_x + (circle1_R+0.0f) * (float) Math.cos(angle);
            Circle_Coordinate_1[index+1] = circle1_y + (circle1_R+0.0f) * (float) Math.sin(angle);
            Circle_Coordinate_1[index+2]=0;

            index=index+3;
        }

        index = 3;

        /// create ellipse  (half ellipse)
        float ellipse_R1 = 0.2f;
        float ellipse_R2 = 0.4f;
        float ellipse_x = 0f;
        float ellipse_y = 0f;

        Circle_Coordinate_2[0]=ellipse_x;
        Circle_Coordinate_2[1]=ellipse_y;
        Circle_Coordinate_2[2]=0;


        for (float angle = 0.0f; angle <= 1 * Math.PI + d_angle ; angle = angle + d_angle)
        {

            Circle_Coordinate_2[index] = ellipse_x + (ellipse_R1+0.0f) * (float) Math.cos(angle);
            Circle_Coordinate_2[index+1] = ellipse_y + (ellipse_R2+0.0f) * (float) Math.sin(angle);
            Circle_Coordinate_2[index+2]=0;

            index=index+3;
        }

        index =3;


        /// create Big_circle for Sun  (full circle)
        float big_Circle1_R = 0.24f;
        float big_Circle1_x = 0.0f;
        float big_Circle1_y = 0.0f;

        Big_Circle_Coordinate_1[0]=big_Circle1_x;
        Big_Circle_Coordinate_1[1]=big_Circle1_y;
        Big_Circle_Coordinate_1[2]=0;

        for (float angle = 0.0f; angle <= 2 * Math.PI + d_angle ; angle = angle + d_angle)
        {

            Big_Circle_Coordinate_1[index] = big_Circle1_x + (big_Circle1_R+0.0f) * (float) Math.cos(angle);
            Big_Circle_Coordinate_1[index+1] = big_Circle1_y + (big_Circle1_R+0.0f) * (float) Math.sin(angle);
            Big_Circle_Coordinate_1[index+2]=0;

            index=index+3;
        }



        // -------------------------------------------------- //

        // Door buffer

        // initialize vertex byte buffer for shape coordinates
        ByteBuffer bb0 = ByteBuffer.allocateDirect(
                // (number of coordinate values * 4 bytes per float)
                Door_Coordinate_1.length * 4);
        // use the device hardware's native byte order
        bb0.order(ByteOrder.nativeOrder());

        // create a floating point buffer from the ByteBuffer
        vertexBuffer_Door = bb0.asFloatBuffer();
        // add the coordinates to the FloatBuffer
        vertexBuffer_Door.put(Door_Coordinate_1);
        // set the buffer to read the first coordinate
        vertexBuffer_Door.position(0);


        //Circle buffer  (full circle)
        ByteBuffer bb1 = ByteBuffer.allocateDirect(
                // (number of coordinate values * 4 bytes per float)
                Circle_Coordinate_1.length * 4);
        // use the device hardware's native byte order
        bb1.order(ByteOrder.nativeOrder());

        // create a floating point buffer from the ByteBuffer
        vertexBuffer_Circle1 = bb1.asFloatBuffer();
        // add the coordinates to the FloatBuffer
        vertexBuffer_Circle1.put(Circle_Coordinate_1);
        // set the buffer to read the first coordinate
        vertexBuffer_Circle1.position(0);


        //ellipse buffer
        ByteBuffer bb2 = ByteBuffer.allocateDirect(
                // (number of coordinate values * 4 bytes per float)
                Circle_Coordinate_2.length * 4);
        // use the device hardware's native byte order
        bb2.order(ByteOrder.nativeOrder());

        // create a floating point buffer from the ByteBuffer
        vertexBuffer_Ellipse1 = bb2.asFloatBuffer();
        // add the coordinates to the FloatBuffer
        vertexBuffer_Ellipse1.put(Circle_Coordinate_2);
        // set the buffer to read the first coordinate
        vertexBuffer_Ellipse1.position(0);


        // Big_circle buffer
        ByteBuffer bb3 = ByteBuffer.allocateDirect(
                // (number of coordinate values * 4 bytes per float)
                Big_Circle_Coordinate_1.length * 4);
        // use the device hardware's native byte order
        bb3.order(ByteOrder.nativeOrder());

        // create a floating point buffer from the ByteBuffer
        vertexBuffer_Big_Circle1 = bb3.asFloatBuffer();
        // add the coordinates to the FloatBuffer
        vertexBuffer_Big_Circle1.put(Big_Circle_Coordinate_1);
        // set the buffer to read the first coordinate
        vertexBuffer_Big_Circle1.position(0);

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
        // start ellipse part

        // draw left_wall
        GLES20.glVertexAttribPointer(positionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                vertexStride, vertexBuffer_Ellipse1);

        Matrix.setIdentityM(mTranslationMatrix,0);
        Matrix.translateM(mTranslationMatrix,0,-0.51f,-0.45f,0.0f);

        Matrix.multiplyMM(result_mvpMatrix_Translation,0,mvpMatrix,0,mTranslationMatrix,0);

        GLES20.glUniformMatrix4fv(vPMatrixHandle,1,false,result_mvpMatrix_Translation,0);


        GLES20.glUniform4fv(colorHandle, 1, color_brown, 0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 0, 16);


        //  rotate  half_ellipse (left wall)
        Matrix.setIdentityM(mRotationMatrix,0);
        Matrix.rotateM(mRotationMatrix,0,180.0f,0,0,-1.0f);
        Matrix.multiplyMM(result_mvpMatrix_Translation_Rotation,0,result_mvpMatrix_Translation,0,mRotationMatrix,0);

        GLES20.glUniformMatrix4fv(vPMatrixHandle,1,false,result_mvpMatrix_Translation_Rotation,0);

        GLES20.glUniform4fv(colorHandle, 1, color_brown, 0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 0, 16);


        // draw right wall
        GLES20.glVertexAttribPointer(positionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                vertexStride, vertexBuffer_Ellipse1);

        Matrix.setIdentityM(mTranslationMatrix,0);
        Matrix.translateM(mTranslationMatrix,0,0.51f,-0.45f,0.0f);

        Matrix.multiplyMM(result_mvpMatrix_Translation,0,mvpMatrix,0,mTranslationMatrix,0);

        GLES20.glUniformMatrix4fv(vPMatrixHandle,1,false,result_mvpMatrix_Translation,0);


        GLES20.glUniform4fv(colorHandle, 1, color_brown, 0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 0, 16);


        //  rotate  half_ellipse (right_wall)
        Matrix.setIdentityM(mRotationMatrix,0);
        Matrix.rotateM(mRotationMatrix,0,180.0f,0,0,-1.0f);
        Matrix.multiplyMM(result_mvpMatrix_Translation_Rotation,0,result_mvpMatrix_Translation,0,mRotationMatrix,0);

        GLES20.glUniformMatrix4fv(vPMatrixHandle,1,false,result_mvpMatrix_Translation_Rotation,0);

        GLES20.glUniform4fv(colorHandle, 1, color_brown, 0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 0, 16);


        // draw right Chimney
        Matrix.setIdentityM(mTranslationMatrix,0);
        Matrix.translateM(mTranslationMatrix,0,0.51f,0.45f,0.0f);

        Matrix.multiplyMM(result_mvpMatrix_Translation,0,mvpMatrix,0,mTranslationMatrix,0);

        //  rotate  half_ellipse (right_Chimney)
        Matrix.setIdentityM(mRotationMatrix,0);
        Matrix.rotateM(mRotationMatrix,0,180.0f,0,0,-1.0f);
        Matrix.multiplyMM(result_mvpMatrix_Translation_Rotation,0,result_mvpMatrix_Translation,0,mRotationMatrix,0);

        GLES20.glUniformMatrix4fv(vPMatrixHandle,1,false,result_mvpMatrix_Translation_Rotation,0);

        GLES20.glUniform4fv(colorHandle, 1, color_green, 0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 0, 16);

        //draw left Chimney

        Matrix.setIdentityM(mTranslationMatrix,0);
        Matrix.translateM(mTranslationMatrix,0,-0.51f,0.45f,0.0f);

        Matrix.multiplyMM(result_mvpMatrix_Translation,0,mvpMatrix,0,mTranslationMatrix,0);


        //  rotate  half_ellipse (left_Chimney)
        Matrix.setIdentityM(mRotationMatrix,0);
        Matrix.rotateM(mRotationMatrix,0,180.0f,0,0,-1.0f);
        Matrix.multiplyMM(result_mvpMatrix_Translation_Rotation,0,result_mvpMatrix_Translation,0,mRotationMatrix,0);

        GLES20.glUniformMatrix4fv(vPMatrixHandle,1,false,result_mvpMatrix_Translation_Rotation,0);

        GLES20.glUniform4fv(colorHandle, 1, color_green, 0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 0, 16);

        //draw ceiling
        Matrix.setIdentityM(mTranslationMatrix,0);
        Matrix.translateM(mTranslationMatrix,0,0f,-0.1f,0.0f);

        Matrix.multiplyMM(result_mvpMatrix_Translation,0,mvpMatrix,0,mTranslationMatrix,0);

        GLES20.glUniformMatrix4fv(vPMatrixHandle,1,false,result_mvpMatrix_Translation,0);

        Matrix.setIdentityM(mRotationMatrix,0);
        Matrix.rotateM(mRotationMatrix,0,0f,0,0,-1.0f);
        Matrix.multiplyMM(result_mvpMatrix_Translation_Rotation,0,result_mvpMatrix_Translation,0,mRotationMatrix,0);

        GLES20.glUniformMatrix4fv(vPMatrixHandle,1,false,result_mvpMatrix_Translation_Rotation,0);

        GLES20.glUniform4fv(colorHandle, 1, color_yellow, 0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 0, 16);



        //  end ellipse part //

        // start door //

        // draw  door (square)
        GLES20.glUniformMatrix4fv(vPMatrixHandle,1,false,mvpMatrix,0);

        GLES20.glVertexAttribPointer(positionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                vertexStride, vertexBuffer_Door);

        GLES20.glUniform4fv(colorHandle, 1, color_black, 0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 4);


        // start   circle   part
        //draw door_handle  (color_gray)
        Matrix.setIdentityM(mTranslationMatrix,0);
        Matrix.translateM(mTranslationMatrix,0,-0.1f,-0.45f,0.0f);

        Matrix.multiplyMM(result_mvpMatrix_Translation,0,mvpMatrix,0,mTranslationMatrix,0);

        GLES20.glUniformMatrix4fv(vPMatrixHandle,1,false,result_mvpMatrix_Translation,0);

        GLES20.glVertexAttribPointer(positionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                vertexStride, vertexBuffer_Circle1);

        GLES20.glUniform4fv(colorHandle, 1, color_gray, 0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 0, 30);


        //draw left_wall_decoration
        Matrix.setIdentityM(mTranslationMatrix,0);
        Matrix.translateM(mTranslationMatrix,0,-0.51f,-0.45f,0.0f);

        Matrix.multiplyMM(result_mvpMatrix_Translation,0,mvpMatrix,0,mTranslationMatrix,0);

        GLES20.glUniformMatrix4fv(vPMatrixHandle,1,false,result_mvpMatrix_Translation,0);

        GLES20.glUniform4fv(colorHandle, 1, color_sky, 0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 0, 30);


        //draw  right_wall_decoration
        Matrix.setIdentityM(mTranslationMatrix,0);
        Matrix.translateM(mTranslationMatrix,0,0.51f,-0.45f,0.0f);

        Matrix.multiplyMM(result_mvpMatrix_Translation,0,mvpMatrix,0,mTranslationMatrix,0);

        GLES20.glUniformMatrix4fv(vPMatrixHandle,1,false,result_mvpMatrix_Translation,0);

        GLES20.glUniform4fv(colorHandle, 1, color_sky, 0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 0, 30);


        //draw  left_chimney_decoration
        Matrix.setIdentityM(mTranslationMatrix,0);
        Matrix.translateM(mTranslationMatrix,0,-0.51f,0.45f,0.0f);

        Matrix.multiplyMM(result_mvpMatrix_Translation,0,mvpMatrix,0,mTranslationMatrix,0);

        GLES20.glUniformMatrix4fv(vPMatrixHandle,1,false,result_mvpMatrix_Translation,0);

        GLES20.glUniform4fv(colorHandle, 1, color_pink, 0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 0, 30);


        //draw  right_chimney_decoration
        Matrix.setIdentityM(mTranslationMatrix,0);
        Matrix.translateM(mTranslationMatrix,0,0.51f,0.45f,0.0f);

        Matrix.multiplyMM(result_mvpMatrix_Translation,0,mvpMatrix,0,mTranslationMatrix,0);

        GLES20.glUniformMatrix4fv(vPMatrixHandle,1,false,result_mvpMatrix_Translation,0);

        GLES20.glUniform4fv(colorHandle, 1, color_pink, 0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 0, 30);



        // apply  Sun & Moon animation


        if (status==0)
        {
            GLES20.glVertexAttribPointer(positionHandle, COORDS_PER_VERTEX,
                    GLES20.GL_FLOAT, false,
                    vertexStride, vertexBuffer_Big_Circle1);

            sun_x+=d_sun_x;
            sun_y =  (float) (-0.78)  *  (sun_x * sun_x) + 0.7f;

            Matrix.setIdentityM(mTranslationMatrix,0);
            Matrix.translateM(mTranslationMatrix,0,sun_x,sun_y,0.0f);

            Matrix.multiplyMM(result_mvpMatrix_Translation,0,mvpMatrix,0,mTranslationMatrix,0);
            GLES20.glUniformMatrix4fv(vPMatrixHandle,1,false,result_mvpMatrix_Translation,0);


            GLES20.glUniform4fv(colorHandle, 1, color_red, 0);
            GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 0, 30);

            if(sun_x > 1)
            {
                sun_x=-0.51f;
                status=1;
            }

        }
        else if (status==1)
        {

            GLES20.glVertexAttribPointer(positionHandle, COORDS_PER_VERTEX,
                    GLES20.GL_FLOAT, false,
                    vertexStride, vertexBuffer_Circle1);

            sun_x+=d_sun_x;
            sun_y =  (float) (-0.78)  *  (sun_x * sun_x) + 0.7f;

            Matrix.setIdentityM(mTranslationMatrix,0);
            Matrix.translateM(mTranslationMatrix,0,sun_x,sun_y,0.0f);

            Matrix.multiplyMM(result_mvpMatrix_Translation,0,mvpMatrix,0,mTranslationMatrix,0);
            GLES20.glUniformMatrix4fv(vPMatrixHandle,1,false,result_mvpMatrix_Translation,0);

            GLES20.glUniform4fv(colorHandle, 1, color_gray, 0);
            GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 0, 30);

            if(sun_x > 1)
            {
                sun_x=-0.51f;
                status=0;
            }
        }


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