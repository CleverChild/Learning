package com.example.midterm_minseoblim_201621228_3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{

    private TextView my_Answer;
    private EditText my_Text1,my_Text2,my_Text3;
    String str_a,str_b,str_c;
    int a,b,c,answer;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        my_Answer = (TextView) findViewById(R.id.my_answer);
        my_Text1 = (EditText) findViewById(R.id.my_text1);
        my_Text2 = (EditText) findViewById(R.id.my_text2);
        my_Text3 = (EditText) findViewById(R.id.my_text3);

    }

    public void click_n1_n2 (View view)
    {
        str_a= my_Text1.getText().toString();
        str_b= my_Text2.getText().toString();
        str_c= my_Text3.getText().toString();

        int temp;
        a=Integer.parseInt(str_a);
        b=Integer.parseInt(str_b);
        c=Integer.parseInt(str_c);
        temp=a;
        a=b;
        b=temp;
        answer=a*(b+c);
        my_Text1.setText(String.valueOf(a));
        my_Text2.setText(String.valueOf(b));
        my_Text3.setText(String.valueOf(c));
        my_Answer.setText(String.valueOf(answer));

        ///save
    }

    public void click_n1_n3 (View view)
    {
        str_a= my_Text1.getText().toString();
        str_b= my_Text2.getText().toString();
        str_c= my_Text3.getText().toString();

        int temp;
        a=Integer.parseInt(str_a);
        b=Integer.parseInt(str_b);
        c=Integer.parseInt(str_c);
        temp=a;
        a=c;
        c=temp;
        answer=a*(b+c);
        my_Text1.setText(String.valueOf(a));   // c
        my_Text2.setText(String.valueOf(b));  // b
        my_Text3.setText(String.valueOf(c));
        my_Answer.setText(String.valueOf(answer));
    }
}