package com.example.aleksandr.a1_workout_ag;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class SwimmingActivity extends AppCompatActivity {

    private static final String TAG = "SwimmingActivity";

    // Ctrl + Enter  --  Override Methods.. -- onCreate
    // Ctrl + O   -- onCreate

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swimming);

        Intent intent = getIntent();

    }
}
