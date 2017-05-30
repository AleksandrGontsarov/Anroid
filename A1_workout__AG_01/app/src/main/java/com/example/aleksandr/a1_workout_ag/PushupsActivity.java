package com.example.aleksandr.a1_workout_ag;


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

public class PushupsActivity extends AppCompatActivity {
    private static final String TAG = "PushupsActivity";

    // ========== Поля активности
    private ImageButton plusButton;
    private ImageButton minusButton;
    private ImageButton completeButton;
    private TextView repeatCountTextView;
    private TextView pushUpsCountTextView;

    //========== Поля таймера
    private Button startButton;
    private Button stopButton;
    private Button resetButton;

    private TextView clockTextView; // отображение таймера

    int seconds = 0;

    boolean isRunning;
    boolean wasRunning;


    private int pushupsCount = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pushups); //setContentView(int layoutResID)

        // fib : findViewById()
        // Alt + Enter --> Cast to ...
        // Cmd + Shift + Enter :  ставит ;
        // Cmd + Alt + M --> преобразовать выделенное в метод

        initUI();
        runTimer();


        pushupsCount = getIntent().getIntExtra(MainActivity.PUSH_UPS_KEY, 0); // метод getIntent позваляет
        // позваляет получить intent и изнего извлечь передаваемую информацию(PUSH_UPS_KEY)

        pushUpsCountTextView.setText(Integer.toString(pushupsCount)); // Информация передается
        // по связке : ключ, значение, как в любой коллекции

    }

    private void initUI() {
        plusButton = (ImageButton) findViewById(R.id.pluss_button);
        plusButton.setOnClickListener(new View.OnClickListener() { //обработчик кнопки +
            @Override
            public void onClick(View view) {
                pushupsCount++;
                pushUpsCountTextView.setText(Integer.toString(pushupsCount));
            }
        });
        minusButton = (ImageButton) findViewById(R.id.minus_button);
        minusButton.setOnClickListener(new View.OnClickListener() { //обработчик кнопки -
            @Override
            public void onClick(View view) {
                if (pushupsCount >= 1) {
                    pushupsCount--;
                    pushUpsCountTextView.setText(Integer.toString(pushupsCount));
                } else {
                    Toast.makeText(PushupsActivity.this, "Повторения отрицательными быть не могут!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        startButton = (Button) findViewById(R.id.start_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isRunning = true;
            }
        });

        stopButton = (Button) findViewById(R.id.stop_button);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isRunning = false;
            }
        });

        resetButton = (Button) findViewById(R.id.reset_button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isRunning) {
                    isRunning = false;
                    seconds = 0;
                } else seconds = 0;
            }
        });

        clockTextView = (TextView) findViewById(R.id.clock_text_view);

        pushUpsCountTextView = (TextView) findViewById(R.id.pushups_count_text_view);
    }


    @Override
    public void onBackPressed() {
        Intent pushUpsIntent = new Intent();
        pushUpsIntent.putExtra(MainActivity.PUSH_UPS_KEY, pushupsCount);
        setResult(RESULT_OK, pushUpsIntent);
        super.onBackPressed();
    }

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
                clockTextView.setText(time);
                if (isRunning) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }
}