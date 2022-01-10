package com.example.calculator_;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity
{
    private EditText my_Text;

    boolean operPlus=false;
    boolean operMinus=false;
    boolean operMultiple=false;
    boolean operDivide=false;

    String Line_1="";
    String Line_2="";

    String string_num_1;

    RadioButton rb_Radian;
    RadioButton rb_Degree;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        my_Text = (EditText) findViewById(R.id.my_text);
        rb_Radian = (RadioButton) findViewById(R.id.rb_radian);
        rb_Degree = (RadioButton) findViewById(R.id.rb_degree);
    }

    public void click_clear(View view)
    {
        my_Text.setText("");
        Line_1="";
        Line_2="";
    }

    public void click_dot(View view)
    {
        if(!Line_2.contains("."))
        {
            Line_2=Line_2+".";
            my_Text.setText(Line_1+"\n"+Line_2);
        }
    }

    public void click_7 (View view)
    {
        Line_2=Line_2+"7";
        my_Text.setText(Line_1+"\n"+Line_2);
    }

    public void click_8 (View view)
    {
        Line_2=Line_2+"8";
        my_Text.setText(Line_1+"\n"+Line_2);
    }

    public void click_9 (View view)
    {
        Line_2=Line_2+"9";
        my_Text.setText(Line_1+"\n"+Line_2);
    }

    public void click_4 (View view)
    {
        Line_2=Line_2+"4";
        my_Text.setText(Line_1+"\n"+Line_2);
    }

    public void click_5 (View view)
    {
        Line_2=Line_2+"5";
        my_Text.setText(Line_1+"\n"+Line_2);
    }

    public void click_6 (View view)
    {
        Line_2=Line_2+"6";
        my_Text.setText(Line_1+"\n"+Line_2);
    }

    public void click_1 (View view)
    {
        Line_2=Line_2+"1";
        my_Text.setText(Line_1+"\n"+Line_2);
    }

    public void click_2 (View view)
    {
        Line_2=Line_2+"2";
        my_Text.setText(Line_1+"\n"+Line_2);
    }

    public void click_3 (View view)
    {
        Line_2=Line_2+"3";
        my_Text.setText(Line_1+"\n"+Line_2);
    }

    public void click_0 (View view)
    {
        Line_2=Line_2+"0";
        my_Text.setText(Line_1+"\n"+Line_2);
    }

    public void click_plus(View view)
    {
        operPlus=true;
        operMinus=false;
        operMultiple=false;
        operDivide=false;

        /// save number1 before add operator in  Line_1
        string_num_1 = Line_2;

        ///display  operator and number2 in the line1
        Line_1=string_num_1+"+";
        Line_2="";

        my_Text.setText(Line_1+"\n"+Line_2);
    }

    public void click_minus(View view)
    {
        operPlus=false;
        operMinus=true;
        operMultiple=false;
        operDivide=false;

        /// save number1 before add operator in  Line_1
        string_num_1 = Line_2;

        ///display  operator and number2 in the line1
        Line_1=string_num_1+"-";
        Line_2="";

        my_Text.setText(Line_1+"\n"+Line_2);
    }

    public void click_multiple(View view)
    {
        operPlus=false;
        operMinus=false;
        operMultiple=true;
        operDivide=false;

        string_num_1 = Line_2;

        ///display  operator and number2 in the line1
        Line_1=string_num_1+"*";
        Line_2="";

        my_Text.setText(Line_1+"\n"+Line_2);
    }

    public void click_divide(View view)
    {
        operPlus=false;
        operMinus=false;
        operMultiple=false;
        operDivide=true;

        string_num_1 = Line_2;

        ///display  operator and number2 in the line1
        Line_1=string_num_1+"/";
        Line_2="";

        my_Text.setText(Line_1+"\n"+Line_2);
    }

    public void click_equal(View view)
    {
        float float_num1;
        float float_num2;
        float float_res=0.0f;

        float_num1 = Float.parseFloat(string_num_1);
        float_num2 = Float.parseFloat(Line_2);

        if(operPlus)
        {
            float_res = float_num1+float_num2;
        }
        else if(operMinus)
        {
            float_res = float_num1-float_num2;
        }
        else if(operMultiple)
        {
            float_res = float_num1*float_num2;
        }
        else if(operDivide)
        {
            float_res = float_num1/float_num2;
        }

        /// display result
        Line_1 = Line_1 + Line_2 + "=";
        Line_2=String.valueOf(float_res);
        my_Text.setText(Line_1+"\n"+Line_2);
    }

    public void click_plus_minus(View view)
    {
        if(!Line_2.contains("-"))
        {
            Line_2 = "-" + Line_2;
        }
        else
        {
            Line_2=Line_2.substring(1);
        }

        my_Text.setText(Line_1 + "\n" + Line_2);
    }

    public void click_del(View view)
    {
        if(Line_2.length()!=0)
        {
            Line_2=Line_2.substring(0,Line_2.length()-1);
        }

        my_Text.setText(Line_1+"\n"+Line_2);
    }

    public void click_square (View view)
    {
        float float_num1;
        string_num_1 = Line_2.toString();

        float_num1 = Float.parseFloat(string_num_1);

        Line_1 = Line_2 +"^2="+String.valueOf(Math.pow(float_num1,2));
        Line_2="";
        my_Text.setText(Line_1+"\n"+Line_2);
    }

    public void click_sin(View view)
    {
        double double_num1=0.0d,double_res=0.0d;

        string_num_1=Line_2;
        Line_2="";

        if(rb_Radian.isChecked())
        {
            double_num1 = Double.parseDouble(string_num_1);
            double_num1 = Math.toDegrees(double_num1);

            /// prevent  ex) sin(1.57)=0.89 , Math function is used radian parameter.
            double_num1 = Math.toRadians(double_num1);
            double_res = Math.sin(double_num1);
        }
        else if(rb_Degree.isChecked())
        {
            double_num1 = Double.parseDouble(string_num_1);

            /// prevent  ex) sin(1.57)=0.89 , Math function is used radian parameter.
            double_num1 = Math.toRadians(double_num1);
            double_res = Math.sin(double_num1);
        }
        double_res = (double) double_res * 100000.0d;
        double_res = Math.round(double_res);
        double_res = (double) double_res / 100000.0d;

        Line_1="Sin("+string_num_1+")="+String.valueOf(double_res);
        my_Text.setText(Line_1+"\n"+Line_2);
    }

    public void click_cos(View view)
    {
        double double_num1=0.0d,double_res=0.0d;

        string_num_1=Line_2;
        Line_2="";

        if(rb_Radian.isChecked())
        {
            double_num1 = Double.parseDouble(string_num_1);
            double_num1 = Math.toDegrees(double_num1);

            ///  Math function is used radian parameter.
            double_num1 = Math.toRadians(double_num1);
            double_res = Math.cos(double_num1);
        }
        else if(rb_Degree.isChecked())
        {
            double_num1 = Double.parseDouble(string_num_1);

            ///  Math function is used radian parameter.
            double_num1 = Math.toRadians(double_num1);
            double_res = Math.cos(double_num1);
        }
        double_res = (double) double_res * 100000.0d;
        double_res = Math.round(double_res);
        double_res = (double) double_res / 100000.0d;

        Line_1="Cos("+string_num_1+")="+String.valueOf(double_res);
        my_Text.setText(Line_1+"\n"+Line_2);
    }

    public void click_log (View view)
    {
        double double_num1=0.0d,double_res=0.0d;

        string_num_1=Line_2;
        Line_2="";

        double_num1 = Double.parseDouble(string_num_1);
        double_res = Math.log10(double_num1);

        Line_1 = "log(" + string_num_1 + ")=";
        Line_2 = String.valueOf(double_res);
        my_Text.setText(Line_1 + "\n" + Line_2);
    }
}