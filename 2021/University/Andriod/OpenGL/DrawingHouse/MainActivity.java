package com.example.dream_house_hw_proj5;

import androidx.appcompat.app.AppCompatActivity;

import android.opengl.GLSurfaceView;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity
{
    private GLSurfaceView gLView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gLView = new MyGLSurfaceView(this);
        setContentView(gLView);
    }
}