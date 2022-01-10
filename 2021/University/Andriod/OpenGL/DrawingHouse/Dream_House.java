package com.example.dream_house_hw_proj5;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Dream_House
{
    private int MVPMatrixHandle;
    private FloatBuffer vertexBuffer;
    private FloatBuffer vertexBuffer2;
    private FloatBuffer vertexBuffer3;
    private FloatBuffer vertexBuffer4;
    private final int mProgram;
    private int positionHandle;
    private int colorHandle;

    //private final int vertexCount = triangleCoords.length / COORDS_PER_VERTEX;
    private final int vertexStride = COORDS_PER_VERTEX * 4; // 4 bytes per vertex

    static  final int COORDS_PER_VERTEX =3;

    static int vertex_number = 800;

    static float circle_coordinates[] = new float[vertex_number * 3];
    static float circle_coordinates2[] = new float[vertex_number * 3];
    static float circle_coordinates3[] = new float[vertex_number * 3];

    static float Vertex_coor[] = {

            /// Door
            0.25f, 0.1f, 0.0f,
            0.25f, -0.5f, 0.0f,
            -0.25f, 0.1f, 0.0f,
            -0.25f, -0.5f, 0.0f,
    };

    // Set color with red, green, blue and alpha (opacity) values
    float color_gray[] = { 0.8f, 0.8f, 0.8f, 1.0f };
    float color_yellow[] = { 186/255f, 177/255f, 46/255f, 1.0f };
    float color_brown[] = { 153/255f, 76/255f, 0f, 1.0f };
    float color_sky[] = { 153/255f, 204/255f, 1f, 1.0f };
    float color_black[] = {0f,0f,0f,1.0f};
    float color_pink[] = {1f,102/255f,1f,1.0f};
    float color_green[] = {153/255, 1f, 153/255f, 1f};


    public Dream_House()
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


        /// Create left wall , right wall
        float R = 0.16f;
        float R1=0.1f;
        float R2=0.4f;

        /// left wall
        float circle_center_x = -0.6f;
        float circle_center_y = -0.12f;

        float d_angle = (float) Math.PI/20;

        circle_coordinates[0] = circle_center_x; // p0
        circle_coordinates[1] = circle_center_y; // p0
        circle_coordinates[2] = 0.0f; // p0

        int index = 3;

        // left wall (ellipses)
        for (float angle = 0.0f; angle <= 2*Math.PI + d_angle ; angle = angle + d_angle)
        {
            // vertec p
            circle_coordinates[index] = circle_center_x + (R1+0.17f) * (float) Math.cos(angle);
            circle_coordinates[index+1] = circle_center_y + (R2+0.0f) * (float) Math.sin(angle);
            circle_coordinates[index+2] = 0.0f;
            index=index+3;
        }


        // left wall decoration
        for (float angle = 0.0f; angle <= 2*Math.PI + d_angle ; angle = angle + d_angle)
        {
            // vertec p
            circle_coordinates[index] = circle_center_x + (R+0.07f) * (float) Math.cos(angle);
            circle_coordinates[index+1] = circle_center_y + (R+0.0f) * (float) Math.sin(angle);
            circle_coordinates[index+2] = 0.0f;
            index=index+3;
        }


        /// start 3rd circle  (right wall)

        float circle_center_x2=0.6f;
        float circle_center_y2=-0.12f;

        circle_coordinates[index] = circle_center_x2; // p0
        circle_coordinates[index+1] = circle_center_y2; // p0
        circle_coordinates[index+2] = 0.0f; // p0

        index+=3;

        // right wall (ellipses)
        for (float angle = 0.0f; angle <= 2*Math.PI + d_angle ; angle = angle + d_angle)
        {
            // vertec p
            circle_coordinates[index] = circle_center_x2 + (R1+0.17f) * (float) Math.cos(angle);
            circle_coordinates[index+1] = circle_center_y2 + (R2+0.0f) * (float) Math.sin(angle);
            circle_coordinates[index+2] = 0.0f;
            index=index+3;
        }


        // right wall decoration
        for (float angle = 0.0f; angle <= 2*Math.PI + d_angle ; angle = angle + d_angle)
        {
            // vertec p
            circle_coordinates[index] = circle_center_x2 + (R+0.07f) * (float) Math.cos(angle);
            circle_coordinates[index+1] = circle_center_y2 + (R+0.0f) * (float) Math.sin(angle);
            circle_coordinates[index+2] = 0.0f;
            index=index+3;
        }


        /// ----------------------------------------///

        // ceiling circle

        float circle_center_ceiling_x=0f;
        float circle_center_ceiling_y=0.3f;

        circle_coordinates2[0]=circle_center_ceiling_x;
        circle_coordinates2[1]=circle_center_ceiling_y;
        circle_coordinates2[2]=0.0f;

        int index2=3;
        float d_angle2 = (float) Math.PI/20;

       // ceiling circle (ellipses)
        for (float angle = 0.0f; angle <= 2*Math.PI + d_angle2 ; angle = angle + d_angle2)
        {
            circle_coordinates2[index2] = circle_center_ceiling_x + (R2+0.17f) * (float) Math.cos(angle);
            circle_coordinates2[index2+1] = circle_center_ceiling_y + (R1+0.0f) * (float) Math.sin(angle);
            circle_coordinates2[index2+2] = 0.0f;
            index2+=3;
        }

        /// door_handle circle
        float handle_R = 0.1f;
        float circle_center_handle_x=-0.1f;
        float circle_center_handle_y=-0.2f;

        circle_coordinates2[index2]=circle_center_handle_x;
        circle_coordinates2[index2+1]=circle_center_handle_y;
        circle_coordinates2[index2+2]=0.0f;

        index2+=3;

        // door handle
        for (float angle = 0.0f; angle <= 2*Math.PI + d_angle2 ; angle = angle + d_angle2)
        {
            circle_coordinates2[index2] = circle_center_handle_x + (handle_R+0.07f) * (float) Math.cos(angle);
            circle_coordinates2[index2+1] = circle_center_handle_y + (handle_R+0.0f) * (float) Math.sin(angle);
            circle_coordinates2[index2+2] = 0.0f;
            index2+=3;
        }



        // left Chimney
        float circle_center_left_Chimney_x=-0.7f;
        float circle_center_left_Chimney_y=0.35f;

        circle_coordinates3[0]=circle_center_left_Chimney_x;
        circle_coordinates3[1]=circle_center_left_Chimney_y;
        circle_coordinates3[2]=0.0f;

        int index3=3;
        float d_angle3 = (float) Math.PI/20;

        for (float angle = 0.0f; angle <= 1*Math.PI + d_angle3 ; angle = angle + d_angle3)
        {
            circle_coordinates3[index3] = circle_center_left_Chimney_x + (R1+0.07f) * (float) Math.cos(angle);
            circle_coordinates3[index3+1] = circle_center_left_Chimney_y + (R2+0.0f) * (float) Math.sin(angle);
            circle_coordinates3[index3+2] = 0.0f;
            index3+=3;
        }

        // left Chimney decoration

        float left_Chimney_R=0.07f;
        for (float angle = 0.0f; angle <= 2*Math.PI + d_angle3 ; angle = angle + d_angle3)
        {
            circle_coordinates3[index3] = circle_center_left_Chimney_x + (left_Chimney_R+0.05f) * (float) Math.cos(angle);
            circle_coordinates3[index3+1] = 0.19f + circle_center_left_Chimney_y + (left_Chimney_R+0.0f) * (float) Math.sin(angle);
            circle_coordinates3[index3+2] = 0.0f;
            index3+=3;
        }


        // right Chimney
        float circle_center_right_Chimney_x=0.7f;
        float circle_center_right_Chimney_y=0.35f;

        circle_coordinates3[index3]=circle_center_left_Chimney_x;
        circle_coordinates3[index3+1]=circle_center_left_Chimney_y;
        circle_coordinates3[index3+2]=0.0f;

        for (float angle = 0.0f; angle <= 1*Math.PI + d_angle3 ; angle = angle + d_angle3)
        {
            circle_coordinates3[index3] = circle_center_right_Chimney_x + (R1+0.07f) * (float) Math.cos(angle);
            circle_coordinates3[index3+1] = circle_center_left_Chimney_y + (R2+0.0f) * (float) Math.sin(angle);
            circle_coordinates3[index3+2] = 0.0f;
            index3+=3;
        }

        // right Chimney decoration

        float right_Chimney_R=0.07f;
        for (float angle = 0.0f; angle <= 2*Math.PI + d_angle3 ; angle = angle + d_angle3)
        {
            circle_coordinates3[index3] = circle_center_right_Chimney_x + (right_Chimney_R+0.05f) * (float) Math.cos(angle);
            circle_coordinates3[index3+1] = 0.19f + circle_center_right_Chimney_y + (right_Chimney_R+0.0f) * (float) Math.sin(angle);
            circle_coordinates3[index3+2] = 0.0f;
            index3+=3;
        }




        ///-----------     Process  Buffer   ----------------------------///

        // left wall, right wall

        ByteBuffer bb = ByteBuffer.allocateDirect(
                circle_coordinates.length * 4);
        bb.order(ByteOrder.nativeOrder());

        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(circle_coordinates);
        vertexBuffer.position(0);


        // Door (Square)
        ByteBuffer bb2 = ByteBuffer.allocateDirect(
                Vertex_coor.length * 4);
        bb2.order(ByteOrder.nativeOrder());

        vertexBuffer2 = bb2.asFloatBuffer();
        vertexBuffer2.put(Vertex_coor);
        vertexBuffer2.position(0);


        // Circle  (ceiling, door_handle)
        ByteBuffer bb3 = ByteBuffer.allocateDirect(
                circle_coordinates2.length * 4);
        bb3.order(ByteOrder.nativeOrder());

        vertexBuffer3 = bb3.asFloatBuffer();
        vertexBuffer3.put(circle_coordinates2);
        vertexBuffer3.position(0);


        // Circle (left Chimney, right Chimney)
        ByteBuffer bb4 = ByteBuffer.allocateDirect(
                circle_coordinates3.length * 4);
        bb4.order(ByteOrder.nativeOrder());

        vertexBuffer4 = bb4.asFloatBuffer();
        vertexBuffer4.put(circle_coordinates3);
        vertexBuffer4.position(0);


    }


    public void draw(float [] mvpMatrix)
    {
        // Add program to OpenGL ES environment
        GLES20.glUseProgram(mProgram);

        // get handle to vertex shader's vPosition member
        positionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");

        // Enable a handle to the triangle vertices
        GLES20.glEnableVertexAttribArray(positionHandle);

        // Prepare the circle coordinate data
        GLES20.glVertexAttribPointer(positionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                vertexStride, vertexBuffer);

        // get handle to fragment shader's vColor member
        colorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");
        MVPMatrixHandle = GLES20.glGetAttribLocation(mProgram,"uMVPMatrix");
        GLES20.glUniformMatrix4fv(MVPMatrixHandle,1,false,mvpMatrix,0);


        /// Draw First Circle
        GLES20.glUniform4fv(colorHandle,1,color_brown,0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 0, 42);

        /// Draw Second Circle
        GLES20.glUniform4fv(colorHandle,1,color_sky,0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 42, 42);

        /// Draw 3rd Circle
        GLES20.glUniform4fv(colorHandle,1,color_brown,0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 84, 42);

        /// Draw 4th Circle
        GLES20.glUniform4fv(colorHandle,1,color_sky,0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 126, 40);



        // Draw Door (square)
        GLES20.glVertexAttribPointer(positionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                vertexStride, vertexBuffer2);
        GLES20.glUniform4fv(colorHandle,1,color_black,0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 4);




        // Draw ceiling (ellipse)
        GLES20.glVertexAttribPointer(positionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                vertexStride, vertexBuffer3);
        GLES20.glUniform4fv(colorHandle,1,color_yellow,0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 0,  42);

        // Draw handle
        GLES20.glUniform4fv(colorHandle,1,color_gray,0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 42,  42);


        //  Setting left Chimney Buffer
        GLES20.glVertexAttribPointer(positionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                vertexStride, vertexBuffer4);

        // Draw left Chimney
        GLES20.glUniform4fv(colorHandle,1,color_green,0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 0,  22);

        // Draw left Chimney decoration
        GLES20.glUniform4fv(colorHandle,1,color_pink,0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 22,  42);

        // Draw right Chimney
        GLES20.glUniform4fv(colorHandle,1,color_green,0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 63,  22);

        // Draw right Chimney decoration
        GLES20.glUniform4fv(colorHandle,1,color_pink,0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 85,  40);


        // Disable vertex array
        GLES20.glDisableVertexAttribArray(positionHandle);
    }


    private final String vertexShaderCode =
            "attribute vec4 vPosition;" +
                    "void main() {" +
                    "  gl_Position = vPosition;" +
                    "}";

    private final String fragmentShaderCode =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "  gl_FragColor = vColor;" +
                    "}";

}