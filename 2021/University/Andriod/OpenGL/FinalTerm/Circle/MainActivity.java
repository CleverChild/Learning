package com.example.minseoblim_201621228_final_2;

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