package com.example.memory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button easy;
    Button medium;
    Button hard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         easy = findViewById(R.id.button2);
         medium = findViewById(R.id.button3);
         hard = findViewById(R.id.button4);

         easy.setOnClickListener(v -> {
             Intent startEasyActivity = new Intent(MainActivity.this, EasyActivity.class);
             Toast t = Toast.makeText(this, "Loading", Toast.LENGTH_LONG);
             t.show();
             startActivity(startEasyActivity);
         });

        medium.setOnClickListener(v -> {
            Intent startMediumActivity = new Intent(MainActivity.this, MediumActivity.class);
            Toast t = Toast.makeText(this, "Loading", Toast.LENGTH_LONG);
            t.show();
            startActivity(startMediumActivity);
        });

        hard.setOnClickListener(v -> {
            Intent startHardActivity = new Intent(MainActivity.this, HardActivity.class);
            Toast t = Toast.makeText(this, "Loading", Toast.LENGTH_SHORT);
            t.show();
            startActivity(startHardActivity);
        });
    }
}