package com.example.currence_converter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    private EditText my_text;

    private RadioButton rb_from_wons;
    private RadioButton rb_from_dollars;
    private RadioButton rb_from_euro;
    private RadioButton rb_from_yens;

    private RadioButton rb_to_wons;
    private RadioButton rb_to_dollars;
    private RadioButton rb_to_euro;
    private RadioButton rb_to_yens;

    private TextView notice;

    private boolean convert_flag=true;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        my_text = (EditText) findViewById(R.id.my_text);

        rb_from_wons = (RadioButton) findViewById(R.id.rb_from_wons);
        rb_from_dollars = (RadioButton) findViewById(R.id.rb_from_dollars);
        rb_from_euro = (RadioButton) findViewById(R.id.rb_from_euro);
        rb_from_yens = (RadioButton) findViewById(R.id.rb_from_yens);

        rb_to_wons = (RadioButton) findViewById(R.id.rb_to_wons);
        rb_to_dollars = (RadioButton) findViewById(R.id.rb_to_dollars);
        rb_to_euro = (RadioButton) findViewById(R.id.rb_to_euro);
        rb_to_yens = (RadioButton) findViewById(R.id.rb_to_yens);

        notice = (TextView) findViewById(R.id.notice);
    }

    public void click_from_wons (View view)
    {
        rb_to_wons.setEnabled(false);

        rb_to_dollars.setEnabled(true);
        rb_to_euro.setEnabled(true);
        rb_to_yens.setEnabled(true);
    }

    public void click_from_dollars (View view)
    {
        rb_to_dollars.setEnabled(false);

        rb_to_wons.setEnabled(true);
        rb_to_euro.setEnabled(true);
        rb_to_yens.setEnabled(true);
    }

    public void click_from_euro (View view)
    {
        rb_to_euro.setEnabled(false);

        rb_to_dollars.setEnabled(true);
        rb_to_wons.setEnabled(true);
        rb_to_yens.setEnabled(true);
    }

    public void click_from_yens (View view)
    {
        rb_to_yens.setEnabled(false);

        rb_to_dollars.setEnabled(true);
        rb_to_euro.setEnabled(true);
        rb_to_wons.setEnabled(true);
    }

    public void click_7 (View view)
    {
        my_text.setText(my_text.getText().toString()+"7");
    }

    public void click_8 (View view)
    {
        my_text.setText(my_text.getText().toString()+"8");
    }

    public void click_9 (View view)
    {
        my_text.setText(my_text.getText().toString()+"9");
    }

    public void click_4 (View view)
    {
        my_text.setText(my_text.getText().toString()+"4");
    }

    public void click_5 (View view)
    {
        my_text.setText(my_text.getText().toString()+"5");
    }

    public void click_6 (View view)
    {
        my_text.setText(my_text.getText().toString()+"6");
    }

    public void click_1 (View view)
    {
        my_text.setText(my_text.getText().toString()+"1");
    }

    public void click_2 (View view)
    {
        my_text.setText(my_text.getText().toString()+"2");
    }

    public void click_3 (View view)
    {
        my_text.setText(my_text.getText().toString()+"3");
    }

    public void click_dot (View view)
    {
        my_text.setText(my_text.getText().toString()+".");
    }

    public void click_0 (View view)
    {
        my_text.setText(my_text.getText().toString()+"0");
    }

    public void click_clear(View view)
    {
        my_text.setText("");

        rb_from_wons.setChecked(false);
        rb_from_dollars.setChecked(false);
        rb_from_euro.setChecked(false);
        rb_from_yens.setChecked(false);

        rb_to_wons.setChecked(false);
        rb_to_dollars.setChecked(false);
        rb_to_euro.setChecked(false);
        rb_to_yens.setChecked(false);

        rb_to_wons.setEnabled(true);
        rb_to_dollars.setEnabled(true);
        rb_to_euro.setEnabled(true);
        rb_to_yens.setEnabled(true);

        convert_flag=true;
        notice.setText("---");
    }

    public void click_convert(View view)
    {
        String str_my_text;
        float float_my_text;
        float float_res;

        if(convert_flag==true)
        {
            if(my_text.length()!=0)
            {
                str_my_text = my_text.getText().toString();
                float_my_text = Float.parseFloat(str_my_text);

                /// wons checked by user.
                if(rb_from_wons.isChecked())
                {
                    /// wons-->dollars
                    if(rb_to_dollars.isChecked())
                    {
                        float_res = float_my_text * (float) 0.00088;
                        my_text.setText(str_my_text +" wons="+String.format("%.2f",float_res) + " dollars.");
                        convert_flag=false;
                    }
                    else if(rb_to_euro.isChecked())
                    {
                        float_res = float_my_text * (float) 0.00074;
                        my_text.setText(str_my_text +" wons="+String.format("%.2f",float_res) + " euro.");
                        convert_flag=false;
                    }
                    else if(rb_to_yens.isChecked())
                    {
                        float_res = float_my_text * (float) 0.09632;
                        my_text.setText(str_my_text +" wons="+String.format("%.2f",float_res) + " yens.");
                        convert_flag=false;
                    }
                    else
                    {
                        notice.setText("Please select \" to : \" choice");
                    }

                }
                else if(rb_from_dollars.isChecked())
                {
                    if(rb_to_wons.isChecked())
                    {
                        float_res = float_my_text * (float) 1133.30;
                        my_text.setText(str_my_text +" dollars="+String.format("%.2f",float_res) + " wons.");
                        convert_flag=false;
                    }
                    else if(rb_to_euro.isChecked())
                    {
                        float_res = float_my_text * (float) 0.83833;
                        my_text.setText(str_my_text +" dollars="+String.format("%.2f",float_res) + " euro.");
                        convert_flag=false;
                    }
                    else if(rb_to_yens.isChecked())
                    {
                        float_res = float_my_text * (float) 109.17;
                        my_text.setText(str_my_text +" dollars="+String.format("%.2f",float_res) + " yens.");
                        convert_flag=false;
                    }
                    else
                    {
                        notice.setText("Please select \" to : \" choice");
                    }

                }
                else if(rb_from_euro.isChecked())
                {
                    if(rb_to_wons.isChecked())
                    {
                        float_res = float_my_text * (float) 1352.83;
                        my_text.setText(str_my_text +" euro="+String.format("%.2f",float_res) + " wons.");
                        convert_flag=false;
                    }
                    else if(rb_to_dollars.isChecked())
                    {
                        float_res = float_my_text * (float) 1.19;
                        my_text.setText(str_my_text +" euro="+String.format("%.2f",float_res) + " dollars.");
                        convert_flag=false;
                    }
                    else if(rb_to_yens.isChecked())
                    {
                        float_res = float_my_text * (float) 130.19;
                        my_text.setText(str_my_text +" euro="+String.format("%.2f",float_res) + " yens.");
                        convert_flag=false;
                    }
                    else
                    {
                        notice.setText("Please select \" to : \" choice");
                    }
                }
                else if(rb_from_yens.isChecked())
                {
                    if(rb_to_wons.isChecked())
                    {
                        float_res = float_my_text * (float) 10.39;
                        my_text.setText(str_my_text +" yens="+String.format("%.2f",float_res) + " wons.");
                        convert_flag=false;
                    }
                    else if(rb_to_dollars.isChecked())
                    {
                        float_res = float_my_text * (float) 0.00916;
                        my_text.setText(str_my_text +" yens="+String.format("%.2f",float_res) + " dollars.");
                        convert_flag=false;
                    }
                    else if(rb_to_euro.isChecked())
                    {
                        float_res = float_my_text * (float) 0.00768;
                        my_text.setText(str_my_text +" yens="+String.format("%.2f",float_res) + " euro.");
                        convert_flag=false;
                    }
                    else
                    {
                        notice.setText("Please select \" to : \" choice");
                    }
                }
                /// if   from : radio button  not checked.
                else
                {
                    notice.setText("Please select \" from : \" choice");
                }
            }
            else
            {
                notice.setText("Please insert amount");
            }
        }
    }
}