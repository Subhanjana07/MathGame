package com.company.mathgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button addition;
    Button subtraction;
    Button multiplication;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Math Game");
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#079FAB"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        addition = findViewById(R.id.add);
        subtraction = findViewById(R.id.subs);
        multiplication = findViewById(R.id.multi);

        addition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Game.class);
                startActivity(intent);
                //finish();

            }
        });

        subtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,GamesSub.class);
                startActivity(intent);

            }
        });

        multiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,GamesMulti.class);
                startActivity(intent);
            }
        });
    }
}