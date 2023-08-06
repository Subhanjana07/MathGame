package com.company.mathgame;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Locale;
import java.util.Random;

public class GamesSub extends AppCompatActivity {
    TextView score;
    TextView life;
    TextView time;
    TextView question;
    EditText answer;
    Button okay;
    Button nextQues;
    Random random = new Random();
    int num1,num2;
    int userAns,realAns,userScore = 0,userLife = 3;
    CountDownTimer timer;
    private static final long TIMER_IN_MILLIS = 60000;
    Boolean timerRunning;
    long timeLeft = TIMER_IN_MILLIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Math Game");
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#079FAB"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        score = findViewById(R.id.textViewNum);
        life = findViewById(R.id.textViewLifeNum);
        time = findViewById(R.id.textViewTimeVal);
        question = findViewById(R.id.ques);
        answer = findViewById(R.id.ans);
        okay = findViewById(R.id.ok);
        nextQues = findViewById(R.id.nextQuestion);

        gameContinue();

        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if((answer.getText().toString()).equals(""))
                {
                    Toast.makeText(GamesSub.this, "Please enter an answer", Toast.LENGTH_SHORT).show();
                } else if (!TextUtils.isDigitsOnly(answer.getText().toString())) {

                    Toast.makeText(GamesSub.this, "you can only enter numbers", Toast.LENGTH_SHORT).show();

                } else {
                    pauseTimer();
                    userAns = Integer.valueOf(answer.getText().toString());
                    if (userAns == realAns) {

                        userScore = userScore + 10;
                        score.setText(""+userScore);
                        question.setText("Congratulations,your answer is correct");
                    } else {
                        userLife = userLife - 1;
                        life.setText(""+userLife);
                        question.setText("Sorry your answer is wrong");
                    }
                    okay.setEnabled(false);
                }
            }
        });

        nextQues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(userLife <= 0 )
                {
                    Toast.makeText(GamesSub.this, "Game Over", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(GamesSub.this, Result.class);
                    intent.putExtra("score",userScore);
                    startActivity(intent);
                    finish();
                }
                else {
                    resetTimer();
                    gameContinue();
                    okay.setEnabled(true);
                    answer.setText("");
                }

            }
        });
    }
    public void gameContinue(){
        num1 = random.nextInt(100);
        num2 = random.nextInt(100);
        if(num1>=num2) {
            realAns = num1 - num2;
            question.setText(num1+" - "+num2);
        }
        else {
            realAns = num2 - num1;
            question.setText(num2+" - "+num1);
        }
        startTimer();
    }

    public void startTimer(){
        timer = new CountDownTimer(timeLeft,1000) {
            @Override
            public void onTick(long l) {

                timeLeft = l;
                updateTimer();
            }

            @Override
            public void onFinish() {

                timerRunning = false;
                pauseTimer();
                resetTimer();
                updateTimer();
                userLife = userLife - 1;
                life.setText(""+userLife);
                question.setText("Time is up");
                okay.setEnabled(false);

            }
        }.start();
        timerRunning = true;
    }
    public void  updateTimer(){

        int second = (int)(timeLeft/1000)%60;
        String time_left = String.format(Locale.getDefault(),"%02d",second);
        time.setText(time_left);
    }
    public void pauseTimer(){

        timer.cancel();
        timerRunning = false;
    }
    public void resetTimer(){

        timeLeft = TIMER_IN_MILLIS;
        updateTimer();


    }
}
