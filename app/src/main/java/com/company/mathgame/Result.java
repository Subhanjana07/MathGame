package com.company.mathgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    TextView result;
    Button again;
    Button exitGame;
    int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Math Game");
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#079FAB"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        result = findViewById(R.id.textViewRes);
        again = findViewById(R.id.buttonAgain);
        exitGame = findViewById(R.id.buttonExit);

        Intent intent = getIntent();
        score = intent.getIntExtra("score",0);
        String scoreString = String.valueOf(score);
        result.setText("Your Score : "+scoreString);

        again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Result.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        exitGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finishAffinity();
            }
        });
    }
}