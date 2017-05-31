package com.algsyn.aleksandr.agfitness.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.support.v7.widget.CardView;

import com.algsyn.aleksandr.agfitness.R;
import com.algsyn.aleksandr.agfitness.model.Workout;
import com.algsyn.aleksandr.agfitness.model.WorkoutList;

/**
 * @author Created by Aleksandr on 15.MAY.2017.
 */

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";


    private CardView pullUpsCV;
    private CardView pushUpsCV;
    private TextView pushUpsCountTextView;
    private TextView pullUpsCountTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        setPushUpsClickBehavior();
        setPullUpsClickBehavior();
        setLastRepeatCount();
    }


    @Override
    protected void onResume() {
        setLastRepeatCount();
        super.onResume();
    }

    private void setLastRepeatCount() {
        pushUpsCountTextView.setText(String.valueOf(WorkoutList.getInstance().getWorkoutsList().get(Workout.PUSHUPS).getRepCount()));
        pullUpsCountTextView.setText(String.valueOf(WorkoutList.getInstance().getWorkoutsList().get(Workout.PULLUPS).getRepCount()));
    }

    private void initUI() {
        pullUpsCV = (CardView) findViewById(R.id.pullups_card_view);
        pushUpsCV = (CardView) findViewById(R.id.pushups_card_view);
        pushUpsCountTextView = (TextView) findViewById(R.id.pushups_last_count);
        pullUpsCountTextView = (TextView) findViewById(R.id.pullups_last_count);
    }

    //============================  отжимания
    private void setPushUpsClickBehavior() {
        pushUpsCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WorkoutActivity.class);
                intent.putExtra(Workout.WORKOUT_TYPE_KEY, Workout.PUSHUPS);
                startActivity(intent);
            }
        });
    }


    //============================  подтягивания
    private void setPullUpsClickBehavior() {
        pullUpsCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WorkoutActivity.class);
                intent.putExtra(Workout.WORKOUT_TYPE_KEY, Workout.PULLUPS);
                startActivity(intent);
            }
        });
    }
}