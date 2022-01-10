package com.example.midterm_minseoblim_201621228_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{

    private EditText my_Text1,my_Text2,my_Text3;
    private TextView Monthly,Total;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        my_Text1 = (EditText) findViewById(R.id.my_text1);
        my_Text2 = (EditText) findViewById(R.id.my_text2);
        my_Text3 = (EditText) findViewById(R.id.my_text3);

        Monthly = (TextView) findViewById(R.id.monthly);
        Total = (TextView) findViewById(R.id.total);
    }

    public void click_compute (View view)
    {
        double initial_amount = Double.parseDouble(my_Text1.getText().toString());
        double interest_rate = Double.parseDouble(my_Text2.getText().toString());
        double period_months = Double.parseDouble(my_Text3.getText().toString());

        interest_rate = (interest_rate * 0.01)  / 12;


        double answer_monthly,answer_total;
        double temp = 1+interest_rate;

        answer_monthly = (interest_rate * initial_amount) / (1-(Math.pow(temp,-period_months)));
        answer_total = answer_monthly * period_months;

        answer_monthly=(answer_monthly*100d);
        answer_monthly=Math.round(answer_monthly) / 100.00d;

        answer_total=(answer_total*100d);
        answer_total=Math.round(answer_total) / 100.00d;

        Monthly.setText(String.valueOf(answer_monthly));
        Total.setText(String.valueOf(answer_total));
    }

    public void click_clear (View view)
    {
        my_Text1.setText("");
        my_Text2.setText("");
        my_Text3.setText("");
        Monthly.setText("---");
        Total.setText("---");
    }
}