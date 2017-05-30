package com.example.aleksandr.a1_workout_ag;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


public class PressActivity  extends AppCompatActivity{

    private static final String TAG = "PressActivity";


    // Ctrl + Enter  --  Override Methods.. -- onCreate

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_press);

        Intent intent = getIntent();




    }
}
