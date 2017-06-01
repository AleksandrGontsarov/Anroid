package com.algsyn.aleksandr.agfitness.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.algsyn.aleksandr.agfitness.R;
import com.algsyn.aleksandr.agfitness.model.WorkoutList;

public class WorkoutActivity extends AppCompatActivity {
//    private static final String TAG = "WorkoutActivity";

    //============================  Константы для передачи( ключ/значение)

    private final static String SECONDS_KEY = "seconds_key"; // константа для таймера
    private final static String RUNNING_KEY = "running_key"; // константа для секундомера

    private final static String WORKOUT_INDEX ="WORKOUT_INDEX";

    //============================  Поля таймера
    private Button resetButton;
    private Button startButton;
    private Button stopButton;
    private TextView clockText;

    private int seconds = 0;
    private boolean isRunning;
    private boolean wasRunning;
    //============================

    //============================ Поля активности
    private ImageButton plusButton;
    private ImageButton minusButton;
    private Button completeButton;
    private TextView workoutTitleTextView;
    private TextView repeatCountTextView;
    private TextView descriptionTextView;
    //============================

    //============================ Счетчик повторений
    private int repeatCount = 0;
    private int workoutIndex;


    // fib : findViewById()
    // Alt + Enter --> Cast to ...
    // Cmd + Shift + Enter :  ставит ;
    // Cmd + Alt + M --> преобразовать выделенное в метод

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Log.i(TAG, "Вызван метод onCreate");
        setContentView(R.layout.activity_workout);

        workoutIndex = getIntent().getIntExtra(WORKOUT_INDEX, 0);

//        restoreSavedState(savedInstanceState); // Восстановление состояния после уничтожения активности
        initUI(); // Инициализация пользовательского интерфейса, связывание xml с кодом.

        restoreSavedState(savedInstanceState); // Восстановление состояния после уничтожения активности

        runTimer(); // Запуск таймера
    }

    @Override
    protected void onStart() {
        super.onStart();
//        Log.i(TAG, "Вызван метод onStart");
        if (wasRunning) {
            isRunning = true;
        }
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
////        Log.i(TAG, "Вызван метод onResume");
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
////        Log.i(TAG, "Вызван метод onPause");
//    }

    @Override
    protected void onStop() {
//        Log.i(TAG, "Вызван метод onStop");
        wasRunning = isRunning;
        isRunning = false;
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        WorkoutList.getInstance().getWorkouts().get(workoutIndex).setRepCount(repeatCount);
        setResult(RESULT_CANCELED);
        super.onBackPressed();
    }

    //============================ для сохранения таймера при повороте экрана
    @Override
    protected void onSaveInstanceState(Bundle outState) {
//        Log.i(TAG, "Вызван метод onSaveInstanceState");
        outState.putInt(SECONDS_KEY, seconds);  // кладем значение для таймера
        outState.putBoolean(RUNNING_KEY, isRunning); // кладем значение для таймера
        super.onSaveInstanceState(outState);
    }
    //============================

public static void startActivity(Context context, int workoutIndex){

    Intent intent = new Intent(context, WorkoutActivity.class);
    intent.putExtra(WORKOUT_INDEX, workoutIndex);
    context.startActivity(intent);

}

    private void initUI() {
        repeatCount = WorkoutList.getInstance().getWorkouts().get(workoutIndex).getRepCount();

        plusButton = (ImageButton) findViewById(R.id.plus_button);
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repeatCount++;
                repeatCountTextView.setText(String.valueOf(repeatCount));
            }
        });
        minusButton = (ImageButton) findViewById(R.id.minus_button);
        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (repeatCount >= 1) {
                    repeatCount--;
                    repeatCountTextView.setText(String.valueOf(repeatCount));
                } else {
                    Toast.makeText(WorkoutActivity.this, "Повторения отрицательными быть не могут!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        workoutTitleTextView = (TextView) findViewById(R.id.workout_title_text_view);
        workoutTitleTextView.setText(WorkoutList.getInstance().getWorkouts().get(workoutIndex).getTitle());

        repeatCountTextView = (TextView) findViewById(R.id.repeat_count_text_view);
        repeatCountTextView.setText(String.valueOf(repeatCount));

        descriptionTextView = (TextView) findViewById(R.id.workout_description_text_view);
        descriptionTextView.setText(WorkoutList.getInstance().getWorkouts().get(workoutIndex).getDescription());

        completeButton = (Button) findViewById(R.id.completeButton);
        completeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WorkoutList.getInstance().getWorkouts().get(workoutIndex).setRepCount(repeatCount);
                setResult(RESULT_OK);
                finish();
            }
        });
        //============================ Таймер
        clockText = (TextView) findViewById(R.id.clock_text_view);
        startButton = (Button) findViewById(R.id.start_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRunning = true;
            }
        });
        resetButton = (Button) findViewById(R.id.reset_button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRunning = false;
                seconds = 0;
            }
        });
        stopButton = (Button) findViewById(R.id.stop_button);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRunning = false;
            }
        });
        //============================
    }

    private void restoreSavedState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt(SECONDS_KEY);
            isRunning = savedInstanceState.getBoolean(RUNNING_KEY);
        }
    }

    //============================ Таймер
    private void runTimer() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;

                String hourStr = hours > 9 ? String.valueOf(hours) : String.valueOf("0" + hours);
                String minutesStr = minutes > 9 ? String.valueOf(minutes) : String.valueOf("0" + minutes);
                String secsStr = secs > 9 ? String.valueOf(secs) : String.valueOf("0" + secs);

                String time = hourStr + ":" + minutesStr + ":" + secsStr;
                clockText.setText(time);
                if (isRunning) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }
    //============================
}

