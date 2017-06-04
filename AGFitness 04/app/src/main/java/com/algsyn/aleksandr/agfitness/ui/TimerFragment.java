package com.algsyn.aleksandr.agfitness.ui;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.algsyn.aleksandr.agfitness.R;

public class TimerFragment extends Fragment {

    //============================  Константы для передачи( ключ/значение)
    private final static String SECONDS_KEY = "seconds_key"; // константа для таймера
    private final static String RUNNING_KEY = "running_key"; // константа для секундомера

    private static final String WAS_RUNNING_KEY = "was_running_key";

    //============================  Поля таймера
    private Button resetButton;
    private Button startButton;
    private Button stopButton;
    private TextView clockText;

    private int seconds = 0;
    private boolean isRunning;
    private boolean wasRunning;
    //============================

    // fib : findViewById()
    // Alt + Enter --> Cast to ...
    // Cmd + Shift + Enter :  ставит ;
    // Cmd + Alt + M --> преобразовать выделенное в метод


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        restoreSavedState(savedInstanceState);

        setRetainInstance(true); // для сохранения таймера при повороте(с использованием фрагмента)

        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt(SECONDS_KEY);
            isRunning = savedInstanceState.getBoolean(RUNNING_KEY);
            wasRunning = savedInstanceState.getBoolean(WAS_RUNNING_KEY);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_timer_layout, container, false);

        if (!wasRunning) {
            runTimer(); // Запуск таймера
        }

        InitView(root); // Таймер кнопки

        return root;
    }

    //============================ Таймер кнопки

    private void InitView(View root) {
        clockText = (TextView) root.findViewById(R.id.clock_text_view);
        startButton = (Button) root.findViewById(R.id.start_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRunning = true;
            }
        });
        resetButton = (Button) root.findViewById(R.id.reset_button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRunning = false;
                seconds = 0;
            }
        });
        stopButton = (Button) root.findViewById(R.id.stop_button);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRunning = false;
            }
        });
    }
    //============================

    @Override

//    protected void onStart() {  у Фрагмента метод должен быть public, вызывается активностью
    public void onStart() {
        super.onStart();
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
//    protected void onStop() {  у Фрагмента метод должен быть public, вызывается активностью
    public void onStop() {
        wasRunning = isRunning;
        isRunning = false;
        super.onStop();
    }

    //============================ для сохранения таймера при повороте экрана
    @Override
//    protected void onSaveInstanceState(Bundle outState) { у Фрагмента метод должен быть public, вызывается активностью
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(SECONDS_KEY, seconds);  // кладем значение для таймера
        outState.putBoolean(RUNNING_KEY, isRunning); // кладем значение для таймера
        outState.putBoolean(WAS_RUNNING_KEY, wasRunning);
        super.onSaveInstanceState(outState);
    }
    //============================


    private void restoreSavedState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt(SECONDS_KEY);
            isRunning = savedInstanceState.getBoolean(RUNNING_KEY);
            wasRunning = savedInstanceState.getBoolean(WAS_RUNNING_KEY);
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
