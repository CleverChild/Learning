package com.example.toeictest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity
{
    final Random myRandom = new Random();

    private TextView my_Text;
    private TextView my_Cnt;
    private Button my_Btn;

    private RadioButton rb_Answer1,rb_Answer2,rb_Answer3,rb_Answer4;
    private RadioGroup my_rb_Group;

    int test_status=0;
    int problem_cnt=-1;
    int random_sequence [] = new int[25];

    String problem_list [] = {"To _______ the repair job on time, the engineer asked the carpenters\n and electricians to do overtime yesterday.",
                              "The union leader plans to _______ his remarks on work safety problems \n to the management during the meeting this afternoon.",
                              "_______ to secure your export products, we recommend that you have them \n insured before shipment.",
                              "The consultant was impressed that the draftsmen worked efficiently even \n without the _______ of a design director.",
                              "Jerry Cunningham will _______ the names of researchers and production \n assistants who will join Ms. Marina Fletcher at the Paris Film Festival.",
                              "With all the negative reviews about the novel, it seems that the author’s \n message was _______ by literary critics.",
                              "The painting's monetary worth in the arts market _______ when it was discovered \n that it was one of the earliest works done by the renowned artist Van Gogh.",
                              "Ellen's probationary status was extended by _______ month after it was discovered \n that she had no actual experience in accounting procedures.",
                              "If you would like to cancel or make any changes to your order, please include your \n order number on the subject line in all _______ to customer service.",
                              "_______ last minute finishing touches in decorating the venue, preparations for the \n community ball to be held in the afternoon are complete.",
                              //0~9 problem


                             // 10~19 problem
                             "Clients interested in holding social or business gatherings at the Mayfair Hotel\n may inquire about the availability of function rooms at _______ reception area or on the hotel’s website.",
                             "The most recent economic crisis resulted in the near collapse of Smart Savings Bank, \n a highly _______ financial institution in Asia and Europe.",
                             "The national economy has been ------- for nearly two years because of the impact of \n global recession.",
                             "Garbage, ------- biodegradable or non-biodegradable,should be thrown into \n designated trash cans within the park.",
                             "At the general assembly, the manager thanked the employees for giving their \n full ------- to the company.",
                             "Mr. Forester is confident that _______ can convince the Japanese investors to do \n further business with our company.",
                             "Customers cannot claim their cash remittance _______ presenting valid identification, \n such as a driver’s license and passport.",
                             "Due to the economic crisis, many companies all over the world are _______ to reduce \n operating costs next year.",
                             "Low sales recorded by Blued Clothing last year was primarily due to its ------- \n popularity in the market.",
                             "Climate in different parts of the world has become ------- unpredictable \n because of global warming.",


                            // 20~24 problem
                            "Overtime requests must be ------- by employees’ immediate supervisors \n and the personnel department.",
                            "If you would like to ------- another venue for the party, please contact \n the events coordinator on her mobile phone.",
                            "ExD Enterprise Incorporated is the leading local ------- of imported contact \n lenses in the country.",
                            "The food packaging process is ________ checked by a team of well-trained \n quality control staff.",
                            "Pedestrians are advised to be _______ and look both ways before \n crossing the street."};



    String answer_list [][] = {{"finished","finishing","finishes","finish"},
                               {"address","exalt","broadcast","send",},
                               {"In order","So that","Though","Because"},
                               {"standard","correction","supervision","accuracy"},
                               {"announce","diffuse","trade","command"},
                               {"misinterpret","misinterprets","misinterpreting","misinterpreted"},
                               {"doubled","rolled","plunged","overlapped"},
                               {"more","other","another","one another"},
                               {"incidence","correspondence","adherence","assistance"},
                               {"But for","On account of","In place of","Instead of"},
                               // answer 0~9



                               // answer 10~19
                               {"us","ours","ourselves","our"},
                               {"recognizes","recognized","has recognized","recognizing"},
                               {"stagnant","stagnancy","stagnating","stagnantly"},
                               {"whenever","until","also","whether"},
                               {"collection","commitment","relation","duration"},
                               {"you","yours","yourself","your"},
                               {"without","around","onto","along"},
                               {"expectedly","expected","expectation","expectant"},
                               {"retractable","transferable","minimizing","declining"},
                               {"increase","increasable","increasingly","increased"},




                              // answer 20~24
                              {"approved","delivered","initiated","founded"},
                              {"suggest","comment","release","advocate"},
                              {"distributed","distributing","distributor","distribution"},
                              {"sensitively","carefully","prominently","importantly"},
                              {"cautionary","cautioning","cautious","cautions"}};

    int correct_answer []= {3,0,0,2,0,3,
                            0,2,1,0,3,1,
                            0,3,1,0,0,1,
                            3,2,0,0,1,1,
                            2};

    int user_answer [] = new int[10];

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        my_Text=(TextView) findViewById(R.id.my_text);
        my_Cnt=(TextView) findViewById(R.id.my_cnt);
        my_Btn = (Button) findViewById(R.id.my_btn);

        rb_Answer1 = (RadioButton) findViewById(R.id.rb_answer1);
        rb_Answer2 = (RadioButton) findViewById(R.id.rb_answer2);
        rb_Answer3 = (RadioButton) findViewById(R.id.rb_answer3);
        rb_Answer4 = (RadioButton) findViewById(R.id.rb_answer4);

        my_rb_Group = (RadioGroup) findViewById(R.id.my_rb_group);
    }

    public String make_string_from_arr (int[] init, int length)
    {
        String res="";

        for (int i=0;i<length;i++)
        {
            res=res+init[i]+", ";
        }

        return res;
    }

    public void click_start_next(View view)
    {
        if(problem_cnt==-1)
        {
            random_sequence=generate_sequence(25);
            /// random_sequence was permutated by function.
        }

        if(test_status==0)
        {
            rb_Answer1.setVisibility(View.VISIBLE);
            rb_Answer2.setVisibility(View.VISIBLE);
            rb_Answer3.setVisibility(View.VISIBLE);
            rb_Answer4.setVisibility(View.VISIBLE);
            my_Btn.setText("NEXT");

            my_rb_Group.clearCheck();

            problem_cnt++;   /// -1 --> 0

            my_Text.setText(problem_list[random_sequence[problem_cnt]]);
            rb_Answer1.setText(answer_list[random_sequence[problem_cnt]][0]);
            rb_Answer2.setText(answer_list[random_sequence[problem_cnt]][1]);
            rb_Answer3.setText(answer_list[random_sequence[problem_cnt]][2]);
            rb_Answer4.setText(answer_list[random_sequence[problem_cnt]][3]);
            //my_Cnt.setText(make_string_from_arr(random_sequence,10));
            my_Cnt.setText(String.valueOf(problem_cnt+1)+"/10");

            if(problem_cnt==9)
            {
                test_status=1;
            }
        }
        else if (test_status==1)
        {
            int total=0;
            my_Text.setText("");
            for(int i=0;i<=problem_cnt;i++)
            {
                if(correct_answer[random_sequence[i]]==user_answer[i])
                {
                    my_Text.setText(my_Text.getText().toString()+"Q#"+String.valueOf(i+1)+"/"+String.valueOf(random_sequence[i])+": correct!"+"\n");
                    total+=10;
                }
                else
                {
                    my_Text.setText(my_Text.getText().toString()+"Q#"+String.valueOf(i+1)+"/"+String.valueOf(random_sequence[i])+": wrong ("+
                               String.valueOf(user_answer[i])+" / "+ String.valueOf(correct_answer[random_sequence[i]])+")"+"\n");
                }
            }
            my_Text.setText(my_Text.getText().toString()+ "Total : "+String.valueOf(total));
            rb_Answer1.setVisibility(View.INVISIBLE);
            rb_Answer2.setVisibility(View.INVISIBLE);
            rb_Answer3.setVisibility(View.INVISIBLE);
            rb_Answer4.setVisibility(View.INVISIBLE);
            my_Btn.setVisibility(View.INVISIBLE);
        }
    }

    public int[] generate_sequence (int question_length)
    {
        int tmp;
        int A [] = new int[question_length];

        /// setting 0 1 2 3 4 5 ... 24
        for(int i=0;i<question_length;i++)
        {
            A[i]=i;
        }

        /// permutation arr idx
        for(int i=0;i<question_length;i++)
        {
            int swap_idx = myRandom.nextInt(question_length);
            tmp=A[i];
            A[i]=A[swap_idx];
            A[swap_idx]=tmp;
        }
        return A;
    }

    public void click_answer1 (View view)
    {
        user_answer[problem_cnt]=0;
    }

    public void click_answer2 (View view)
    {
        user_answer[problem_cnt]=1;
    }

    public void click_answer3 (View view)
    {
        user_answer[problem_cnt]=2;
    }

    public void click_answer4 (View view)
    {
        user_answer[problem_cnt]=3;
    }
}