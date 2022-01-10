package com.example.midterm_201621228_minseoblim_4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity
{
    private RadioButton rb_arr [] = new RadioButton[8];
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rb_arr[0] = (RadioButton) findViewById(R.id.rb_0);
        rb_arr[1] = (RadioButton) findViewById(R.id.rb_1);
        rb_arr[2] = (RadioButton) findViewById(R.id.rb_2);
        rb_arr[3] = (RadioButton) findViewById(R.id.rb_3);
        rb_arr[4] = (RadioButton) findViewById(R.id.rb_4);
        rb_arr[5] = (RadioButton) findViewById(R.id.rb_5);
        rb_arr[6] = (RadioButton) findViewById(R.id.rb_6);
        rb_arr[7] = (RadioButton) findViewById(R.id.rb_7);
    }

    /// overall
    public void click_1 (View view)
    {
        for (int i=0;i<8;i++)
        {
            if(rb_arr[i].isChecked())
            {
                rb_arr[i].setChecked(false);
            }
            else
            {
                rb_arr[i].setChecked(true);
            }
        }
    }

    public void click_2 (View view)
    {
        for (int i=0;i<3;i++)
        {
            if(rb_arr[i].isChecked())
            {
                rb_arr[i].setChecked(false);
            }
            else
            {
                rb_arr[i].setChecked(true);
            }
        }
    }

    public void click_3 (View view)
    {
        if(rb_arr[0].isChecked())
        {
            rb_arr[0].setChecked(false);
        }
        else
        {
            rb_arr[0].setChecked(true);
        }


        if(rb_arr[3].isChecked())
        {
            rb_arr[3].setChecked(false);
        }
        else
        {
            rb_arr[3].setChecked(true);
        }

        if(rb_arr[5].isChecked())
        {
            rb_arr[5].setChecked(false);
        }
        else
        {
            rb_arr[5].setChecked(true);
        }
    }
}