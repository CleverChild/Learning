package com.example.minterm_minseoblim_201621228_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    private TextView my_Answer;
    private TextView my_Text;
    int a,b,c;

    private EditText my_Blank1,my_Blank2,my_Blank3;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        my_Text = (TextView) findViewById(R.id.my_text);
        my_Answer = (TextView) findViewById(R.id.my_answer);

        my_Blank1 = (EditText) findViewById(R.id.my_blank1);
        my_Blank2 = (EditText) findViewById(R.id.my_blank2);
        my_Blank3 = (EditText) findViewById(R.id.my_blank3);


    }

    public void click_get(View view)
    {
        String my_str_1,my_str_2,my_str_3;
        String res="";

        my_str_1 = my_Blank1.getText().toString();
        a = Integer.parseInt(my_str_1);

        my_str_2 = my_Blank2.getText().toString();
        b = Integer.parseInt(my_str_2);

        my_str_3 = my_Blank3.getText().toString();
        c = Integer.parseInt(my_str_3);

        res = my_Text.getText().toString().substring(a,a+1);
        res = res + my_Text.getText().toString().substring(b,b+1);
        res = res + my_Text.getText().toString().substring(c,c+1);
        my_Answer.setText(my_Answer.getText().toString()+res+"\n");
    }

}