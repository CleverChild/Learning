package com.example.arithmetic_home_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity
{
    final Random myRandom = new Random();
    private TextView my_Text;
    private TextView my_Answer;

    private RadioButton rb_Plus_1;
    private RadioButton rb_Minus_1;
    private RadioButton rb_Multiple_1;
    private RadioGroup rb1_Group;

    private RadioButton rb_Plus_2;
    private RadioButton rb_Minus_2;
    private RadioButton rb_Multiple_2;
    private RadioGroup rb2_Group;

    int bracket_status=2;
    int first_oper_status;
    int second_oper_status;
    int a,b,c;
    int user_answer;
    int generated_answer;
    int generated_answer_status;

    String str1="";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        my_Text = (TextView) findViewById(R.id.my_text);
        my_Answer = (TextView) findViewById(R.id.my_answer);

        rb_Plus_1 = (RadioButton) findViewById(R.id.rb_plus1);
        rb_Minus_1 = (RadioButton) findViewById(R.id.rb_minus1);
        rb_Multiple_1 = (RadioButton) findViewById(R.id.rb_multiple1);


        rb_Plus_2 = (RadioButton) findViewById(R.id.rb_plus2);
        rb_Minus_2 = (RadioButton) findViewById(R.id.rb_minus2);
        rb_Multiple_2 = (RadioButton) findViewById(R.id.rb_multiple2);

        rb1_Group = (RadioGroup) findViewById(R.id.rb1_group);
        rb2_Group = (RadioGroup) findViewById(R.id.rb2_group);
    }

    public void click_clear(View view)
    {
        bracket_status=2;
        str1=str1.replace("(","");
        str1=str1.replace(")","");
        str1=str1.replaceAll("[+||\\-||*]"," ");
        my_Answer.setText("---");
        rb1_Group.clearCheck();
        rb2_Group.clearCheck();
        my_Text.setText(str1+" = "+generated_answer);
    }

    public void click_generate(View view)
    {
        a=myRandom.nextInt(10);
        b=myRandom.nextInt(10);
        c=myRandom.nextInt(10);

        /// if  a or b or c ==0   choose again random  1~9
        while (a==0 || b==0 || c==0)
        {
            a=myRandom.nextInt(10);
            b=myRandom.nextInt(10);
            c=myRandom.nextInt(10);
        }

        generated_answer_status = myRandom.nextInt(14);
        if(generated_answer_status==0)
        {
            generated_answer = a+b+c;
        }
        else if(generated_answer_status==1)
        {
            generated_answer = a+b-c;
        }
        else if(generated_answer_status==3)
        {
            generated_answer = a+b*c;
        }
        else if(generated_answer_status==4)
        {
            generated_answer = a-b+c;
        }
        else if(generated_answer_status==5)
        {
            generated_answer = a-b-c;
        }
        else if(generated_answer_status==6)
        {
            generated_answer = a-b*c;
        }
        else if(generated_answer_status==7)
        {
            generated_answer = a*b+c;
        }
        else if(generated_answer_status==8)
        {
            generated_answer = a*b-c;
        }
        else if(generated_answer_status==9)
        {
            generated_answer = a*b*c;
        }
        else if(generated_answer_status==10)
        {
            generated_answer = a*(b+c);
        }
        else if(generated_answer_status==11)
        {
            generated_answer = a*(b-c);
        }
        else if(generated_answer_status==12)
        {
            generated_answer = (a+b)*c;
        }
        else if(generated_answer_status==13)
        {
            generated_answer = (a-b)*c;
        }


        str1 = String.valueOf(a)+" "+String.valueOf(b)+" "+String.valueOf(c);
        my_Answer.setText("---");
        rb1_Group.clearCheck();
        rb2_Group.clearCheck();
        my_Text.setText(str1+" = "+generated_answer);
    }

    public void click_check(View view)
    {
        if (generated_answer==user_answer)
        {
            my_Answer.setText("Correct!");
        }
        else
        {
            my_Answer.setText("Wrong, "+user_answer);
        }
    }
    public void click_plus1 (View view)
    {
        str1=str1.replaceFirst("[\\s||+||\\-||*]","+");
        first_oper_status=0;

        if(bracket_status==0)
        {
            if(second_oper_status==0)
            {
                user_answer=a+b+c;
            }
            else if (second_oper_status==1)
            {
                user_answer = a+b-c;
            }
            else if (second_oper_status==2)
            {
                user_answer = (a+b)*c;
            }
        }
        else if (bracket_status==1)
        {
            if(second_oper_status==0)
            {
                user_answer=a+b+c;
            }
            else if (second_oper_status==1)
            {
                user_answer = a+b-c;
            }
            else if (second_oper_status==2)
            {
                user_answer = a+(b*c);
            }
        }
        else if (bracket_status==2)
        {
            if(second_oper_status==0)
            {
                user_answer=a+b+c;
            }
            else if (second_oper_status==1)
            {
                user_answer = a+b-c;
            }
            else if (second_oper_status==2)
            {
                user_answer = a+b*c;
            }
        }

        my_Text.setText(str1+" = "+generated_answer);
    }

    public void click_minus1 (View view)
    {
        str1=str1.replaceFirst("[\\s||+||\\-||*]","-");
        first_oper_status=1;

        if(bracket_status==0)
        {
            if(second_oper_status==0)
            {
                user_answer=a-b+c;
            }
            else if (second_oper_status==1)
            {
                user_answer = a-b-c;
            }
            else if (second_oper_status==2)
            {
                user_answer = (a-b)*c;
            }
        }
        else if (bracket_status==1)
        {
            if(second_oper_status==0)
            {
                user_answer=a-b+c;
            }
            else if (second_oper_status==1)
            {
                user_answer = a-b-c;
            }
            else if (second_oper_status==2)
            {
                user_answer = a-(b*c);
            }
        }
        else if (bracket_status==2)
        {
            if(second_oper_status==0)
            {
                user_answer=a-b+c;
            }
            else if (second_oper_status==1)
            {
                user_answer = a-b-c;
            }
            else if (second_oper_status==2)
            {
                user_answer = a-b*c;
            }
        }

        my_Text.setText(str1+" = "+generated_answer);
    }

    public void click_multiple1 (View view)
    {
        str1=str1.replaceFirst("[\\s||+||\\-||*]","*");
        first_oper_status=2;

        if(bracket_status==0)
        {
            if(second_oper_status==0)
            {
                user_answer=(a*b)+c;
            }
            else if (second_oper_status==1)
            {
                user_answer = (a*b)-c;
            }
            else if (second_oper_status==2)
            {
                user_answer = (a*b)*c;
            }
        }
        else if (bracket_status==1)
        {
            if(second_oper_status==0)
            {
                user_answer=a*(b+c);
            }
            else if (second_oper_status==1)
            {
                user_answer = a*(b-c);
            }
            else if (second_oper_status==2)
            {
                user_answer = a*(b*c);
            }
        }
        else if (bracket_status==2)
        {
            if(second_oper_status==0)
            {
                user_answer=a*b+c;
            }
            else if (second_oper_status==1)
            {
                user_answer = a*b-c;
            }
            else if (second_oper_status==2)
            {
                user_answer = a*b*c;
            }
        }

        my_Text.setText(str1+" = "+generated_answer);
    }

    public String reverse_str (String str2)
    {
        String res="";
        for (int i = str2.length() -1; i>=0; i--)
        {
            res=res+str2.charAt(i);
        }
        return res;
    }

    public void click_plus2 (View view)
    {
        str1=reverse_str(str1);
        str1=str1.replaceFirst("[\\s||+||\\-||*]","+");
        str1=reverse_str(str1);
        second_oper_status=0;

        if(bracket_status==0)
        {
            if(first_oper_status==0)
            {
                user_answer=(a+b)+c;
            }
            else if (first_oper_status==1)
            {
                user_answer = (a-b)+c;
            }
            else if (first_oper_status==2)
            {
                user_answer = (a*b)+c;
            }
        }
        else if (bracket_status==1)
        {
            if(first_oper_status==0)
            {
                user_answer=a+(b+c);
            }
            else if (first_oper_status==1)
            {
                user_answer = a-(b+c);
            }
            else if (first_oper_status==2)
            {
                user_answer = a*(b+c);
            }
        }
        else if (bracket_status==2)
        {
            if(first_oper_status==0)
            {
                user_answer=a+b+c;
            }
            else if (first_oper_status==1)
            {
                user_answer = a-b+c;
            }
            else if (first_oper_status==2)
            {
                user_answer = a*b+c;
            }
        }
        my_Text.setText(str1+" = "+generated_answer);
    }

    public void click_minus2 (View view)
    {
        str1=reverse_str(str1);
        str1=str1.replaceFirst("[\\s||+||\\-||*]","-");
        str1=reverse_str(str1);
        second_oper_status=1;

        if(bracket_status==0)
        {
            if(first_oper_status==0)
            {
                user_answer=(a+b)-c;
            }
            else if (first_oper_status==1)
            {
                user_answer = (a-b)-c;
            }
            else if (first_oper_status==2)
            {
                user_answer = (a*b)-c;
            }
        }
        else if (bracket_status==1)
        {
            if(first_oper_status==0)
            {
                user_answer=a+(b-c);
            }
            else if (first_oper_status==1)
            {
                user_answer = a-(b-c);
            }
            else if (first_oper_status==2)
            {
                user_answer = a*(b-c);
            }
        }
        else if (bracket_status==2)
        {
            if(first_oper_status==0)
            {
                user_answer=a+b-c;
            }
            else if (first_oper_status==1)
            {
                user_answer = a-b-c;
            }
            else if (first_oper_status==2)
            {
                user_answer = a*b-c;
            }
        }

        my_Text.setText(str1+" = "+generated_answer);
    }

    public void click_multiple2 (View view)
    {
        str1=reverse_str(str1);
        str1=str1.replaceFirst("[\\s||+||\\-||*]","*");
        str1=reverse_str(str1);
        second_oper_status=2;

        if(bracket_status==0)
        {
            if(first_oper_status==0)
            {
                user_answer=(a+b)*c;
            }
            else if (first_oper_status==1)
            {
                user_answer = (a-b)*c;
            }
            else if (first_oper_status==2)
            {
                user_answer = (a*b)*c;
            }
        }
        else if (bracket_status==1)
        {
            if(first_oper_status==0)
            {
                user_answer=a+(b*c);
            }
            else if (first_oper_status==1)
            {
                user_answer = a-(b*c);
            }
            else if (first_oper_status==2)
            {
                user_answer = a*(b*c);
            }
        }
        else if (bracket_status==2)
        {
            if(first_oper_status==0)
            {
                user_answer=a+b*c;
            }
            else if (first_oper_status==1)
            {
                user_answer = a-b*c;
            }
            else if (first_oper_status==2)
            {
                user_answer = a*b*c;
            }
        }


        my_Text.setText(str1+" = "+generated_answer);
    }

    public void click_bracket0 (View view)
    {

        if(bracket_status==1) /// previous -->  a (b c)
        {
            if(first_oper_status==0)
            {
                /// user_answer  obtain  process.
                if(second_oper_status==0)
                {
                    user_answer=a+b+c;
                }
                else if (second_oper_status==1)
                {
                    user_answer=a+b-c;
                }
                else if (second_oper_status==2)
                {
                    user_answer=(a+b)*c;
                }

                str1=str1.replace("(","");
                str1=str1.replace(")","");
                str1=str1.replace(String.valueOf(a)+"+"+String.valueOf(b),"("+String.valueOf(a)+"+"+String.valueOf(b)+")");
                my_Text.setText(str1+" = "+generated_answer);
            }
            else if(first_oper_status==1)
            {
                /// user_answer  obtain  process.
                if(second_oper_status==0)
                {
                    user_answer=a-b+c;
                }
                else if (second_oper_status==1)
                {
                    user_answer=a-b-c;
                }
                else if (second_oper_status==2)
                {
                    user_answer=(a-b)*c;
                }

                str1=str1.replace("(","");
                str1=str1.replace(")","");
                str1=str1.replace(String.valueOf(a)+"-"+String.valueOf(b),"("+String.valueOf(a)+"-"+String.valueOf(b)+")");
                my_Text.setText(str1+" = "+generated_answer);
            }
            else if(first_oper_status==2)
            {
                /// user_answer  obtain  process.
                if(second_oper_status==0)
                {
                    user_answer=a*b+c;
                }
                else if (second_oper_status==1)
                {
                    user_answer=a*b-c;
                }
                else if (second_oper_status==2)
                {
                    user_answer=a*b*c;
                }

                str1=str1.replace("(","");
                str1=str1.replace(")","");
                str1=str1.replace(String.valueOf(a)+"*"+String.valueOf(b),"("+String.valueOf(a)+"*"+String.valueOf(b)+")");
                my_Text.setText(str1+" = "+generated_answer);
            }
        }

        else if (bracket_status==2) /// previous --> a b c
        {
            if(first_oper_status==0)
            {
                /// user_answer  obtain  process.
                if(second_oper_status==0)
                {
                    user_answer=a+b+c;
                }
                else if (second_oper_status==1)
                {
                    user_answer=a+b-c;
                }
                else if (second_oper_status==2)
                {
                    user_answer=(a+b)*c;
                }

                str1=str1.replace(String.valueOf(a)+"+"+String.valueOf(b),"("+String.valueOf(a)+"+"+String.valueOf(b)+")");
                my_Text.setText(str1+" = "+generated_answer);
            }
            else if(first_oper_status==1)
            {
                /// user_answer  obtain  process.
                if(second_oper_status==0)
                {
                    user_answer=a-b+c;
                }
                else if (second_oper_status==1)
                {
                    user_answer=a-b-c;
                }
                else if (second_oper_status==2)
                {
                    user_answer=(a-b)*c;
                }

                str1=str1.replace(String.valueOf(a)+"-"+String.valueOf(b),"("+String.valueOf(a)+"-"+String.valueOf(b)+")");
                my_Text.setText(str1+" = "+generated_answer);
            }
            else if(first_oper_status==2)
            {
                /// user_answer  obtain  process.
                if(second_oper_status==0)
                {
                    user_answer=a*b+c;
                }
                else if (second_oper_status==1)
                {
                    user_answer=a*b-c;
                }
                else if (second_oper_status==2)
                {
                    user_answer=a*b*c;
                }

                str1=str1.replace(String.valueOf(a)+"*"+String.valueOf(b),"("+String.valueOf(a)+"*"+String.valueOf(b)+")");
            }
        }
        my_Text.setText(str1+" = "+generated_answer);
        bracket_status=0;
    }

    public void click_bracket1 (View view)
    {
        if(bracket_status==0)
        {
            str1=str1.replace("(","");
            str1=str1.replace(")","");

            if(first_oper_status==0)
            {
                if(second_oper_status==0)
                {
                    user_answer=a+b+c;
                    str1=str1.replace(String.valueOf(b)+"+"+String.valueOf(c),"("+String.valueOf(b)+"+"+String.valueOf(c)+")");
                }
                else if(second_oper_status==1)
                {
                    user_answer=a+b-c;
                    str1=str1.replace(String.valueOf(b)+"-"+String.valueOf(c),"("+String.valueOf(b)+"-"+String.valueOf(c)+")");
                }
                else if(second_oper_status==2)
                {
                    user_answer=a+(b*c);
                    str1=str1.replace(String.valueOf(b)+"*"+String.valueOf(c),"("+String.valueOf(b)+"*"+String.valueOf(c)+")");
                }
            }
            else if(first_oper_status==1)
            {
                if(second_oper_status==0)
                {
                    user_answer=a-b+c;
                    str1=str1.replace(String.valueOf(b)+"+"+String.valueOf(c),"("+String.valueOf(b)+"+"+String.valueOf(c)+")");
                }
                else if(second_oper_status==1)
                {
                    user_answer=a-b-c;
                    str1=str1.replace(String.valueOf(b)+"-"+String.valueOf(c),"("+String.valueOf(b)+"-"+String.valueOf(c)+")");
                }
                else if(second_oper_status==2)
                {
                    user_answer=a-(b*c);
                    str1=str1.replace(String.valueOf(b)+"*"+String.valueOf(c),"("+String.valueOf(b)+"*"+String.valueOf(c)+")");
                }
            }
            else if(first_oper_status==2)
            {
                if(second_oper_status==0)
                {
                    user_answer=a*(b+c);
                    str1=str1.replace(String.valueOf(b)+"+"+String.valueOf(c),"("+String.valueOf(b)+"+"+String.valueOf(c)+")");
                }
                else if(second_oper_status==1)
                {
                    user_answer=a*(b-c);
                    str1=str1.replace(String.valueOf(b)+"-"+String.valueOf(c),"("+String.valueOf(b)+"-"+String.valueOf(c)+")");
                }
                else if(second_oper_status==2)
                {
                    user_answer=a*(b*c);
                    str1=str1.replace(String.valueOf(b)+"*"+String.valueOf(c),"("+String.valueOf(b)+"*"+String.valueOf(c)+")");
                }
            }
        }
        else if (bracket_status==2)
        {
            if(first_oper_status==0)
            {
                if(second_oper_status==0)
                {
                    user_answer=a+b+c;
                    str1=str1.replace(String.valueOf(b)+"+"+String.valueOf(c),"("+String.valueOf(b)+"+"+String.valueOf(c)+")");
                }
                else if(second_oper_status==1)
                {
                    user_answer=a+b-c;
                    str1=str1.replace(String.valueOf(b)+"-"+String.valueOf(c),"("+String.valueOf(b)+"-"+String.valueOf(c)+")");
                }
                else if(second_oper_status==2)
                {
                    user_answer=a+(b*c);
                    str1=str1.replace(String.valueOf(b)+"*"+String.valueOf(c),"("+String.valueOf(b)+"*"+String.valueOf(c)+")");
                }
            }
            else if(first_oper_status==1)
            {
                if(second_oper_status==0)
                {
                    user_answer=a-b+c;
                    str1=str1.replace(String.valueOf(b)+"+"+String.valueOf(c),"("+String.valueOf(b)+"+"+String.valueOf(c)+")");
                }
                else if(second_oper_status==1)
                {
                    user_answer=a-b-c;
                    str1=str1.replace(String.valueOf(b)+"-"+String.valueOf(c),"("+String.valueOf(b)+"-"+String.valueOf(c)+")");
                }
                else if(second_oper_status==2)
                {
                    user_answer=a-(b*c);
                    str1=str1.replace(String.valueOf(b)+"*"+String.valueOf(c),"("+String.valueOf(b)+"*"+String.valueOf(c)+")");
                }
            }
            else if(first_oper_status==2)
            {
                if(second_oper_status==0)
                {
                    user_answer=a*(b+c);
                    str1=str1.replace(String.valueOf(b)+"+"+String.valueOf(c),"("+String.valueOf(b)+"+"+String.valueOf(c)+")");
                }
                else if(second_oper_status==1)
                {
                    user_answer=a*(b-c);
                    str1=str1.replace(String.valueOf(b)+"-"+String.valueOf(c),"("+String.valueOf(b)+"-"+String.valueOf(c)+")");
                }
                else if(second_oper_status==2)
                {
                    user_answer=a*b*c;
                    str1=str1.replace(String.valueOf(b)+"*"+String.valueOf(c),"("+String.valueOf(b)+"*"+String.valueOf(c)+")");
                }
            }
        }
        bracket_status=1;
        my_Text.setText(str1+" = "+generated_answer);
    }

    public void click_bracket2 (View view)
    {
        str1=str1.replace("(","");
        str1=str1.replace(")","");

        if(first_oper_status==0)
        {
            if(second_oper_status==0)
            {
                user_answer=a+b+c;
            }
            else if(second_oper_status==1)
            {
                user_answer=a+b-c;
            }
            else if(second_oper_status==2)
            {
                user_answer=a+b*c;
            }
        }
        else if(first_oper_status==1)
        {
            if(second_oper_status==0)
            {
                user_answer=a-b+c;
            }
            else if(second_oper_status==1)
            {
                user_answer=a-b-c;
            }
            else if(second_oper_status==2)
            {
                user_answer=a-b*c;
            }
        }
        else if(first_oper_status==2)
        {
            if(second_oper_status==0)
            {
                user_answer=a*b+c;
            }
            else if(second_oper_status==1)
            {
                user_answer=a*b-c;
            }
            else if(second_oper_status==2)
            {
                user_answer=a*b*c;
            }
        }
        my_Text.setText(str1+" = "+generated_answer);
        bracket_status=2;
    }
}