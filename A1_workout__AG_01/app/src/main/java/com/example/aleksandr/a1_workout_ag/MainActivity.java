package com.example.aleksandr.a1_workout_ag;

import android.content.Intent;
import android.content.IntentSender;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Created by Aleksandr on 15.MAY.2017.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    public static final String PUSH_UPS_KEY = "PUSHUPS_COUNT_KEY"; // 3.

    public static final int PUSHUPS_REQUEST_CODE = 100; // 3.

    private CardView pullUpsCV;
    private CardView pushUpsCV;
    private CardView pressCV;
    private CardView stretchingCV;
    private CardView descriptionCV;
    private TextView descriptionTextView;
    private TextView pushUpsCountTextView;

    private int pushupsCount = 0; // счетчик отжиманий


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Alt + Cmd + L - выравнивание
        // fib
        // Alt + Enter --> Cast to ...
        // Cmd + Alt + M --> преобразовать выделенное в метод

        initUI();
        setPushUpsClickBehavior();
        setPressClickBehavior();
        setStretchingClickBehavior();
        setLastRepeatCount();
    }

    private void setLastRepeatCount() {
        String repeatCount = getResources().getString(R.string.repeats_count);
        repeatCount += " " + pushupsCount;
        pushUpsCountTextView.setText(repeatCount);
    }


    private void initUI() {
        pullUpsCV = (CardView) findViewById(R.id.pullups_card_view);
        pushUpsCV = (CardView) findViewById(R.id.pushups_card_view);
        pressCV = (CardView) findViewById(R.id.press_card_view);
        stretchingCV = (CardView) findViewById(R.id.stetching_card_view);
        descriptionTextView = (TextView) findViewById(R.id.workout_description_text_view);
        descriptionCV = (CardView) findViewById(R.id.workout_description_card_view);
        pushUpsCountTextView = (TextView) findViewById(R.id.pushups_last_count);
        pushUpsCountTextView.setText(R.string.repeats_count + pushupsCount); //

        descriptionCV.setVisibility(View.GONE); // не видно
        descriptionCV.setOnClickListener(this);
    }

    //=================================================================  подтягивания
    // 1 вариант обработки событий
    // если метод будет public --> то, так нарушается инкапсалюция, один из принципов ООП.
//    public void onPullUpsClick(View view) {
    public void onPullUpsClick(View view) {

        Uri adress = Uri.parse("http://fitness-body.ru/fitness/training/podtyagivaniya-na-turnike.html");
        Intent viewIntent = new Intent(Intent.ACTION_VIEW, adress); // неявный интент
        startActivity(viewIntent);

//        Intent sendIntent = new Intent();
//        sendIntent.setAction(Intent.ACTION_SEND);
//        sendIntent.putExtra(Intent.EXTRA_TEXT, "Text Message");
//        sendIntent.setType("text/plain");

//        if (sendIntent.resolveActivity(getPackageManager()) !=null){
//            startActivity(sendIntent);
//        }


//        if (descriptionCV.getVisibility() != View.VISIBLE) {
//            Log.i(TAG, "PullUps pressed, description CV is GONE");
//            descriptionCV.setVisibility(View.VISIBLE);
//            descriptionTextView.setText(R.string.pullups_discription);
//        } else {
////            Log.e(TAG,"PullUps pressed, description CV is VISIBLE");
//            Log.i(TAG, "PullUps pressed, description CV is VISIBLE");
////            Log.w(TAG,"PullUps pressed, description CV is VISIBLE");
////            Log.d(TAG,"PullUps pressed, description CV is VISIBLE");
////            Log.wtf(TAG,"PullUps pressed, description CV is VISIBLE");
////            Log.v(TAG,"PullUps pressed, description CV is VISIBLE");
//            descriptionTextView.setText(R.string.pullups_discription);
//        }
    }

    //=================================================================  отжимания
    // 2 вариант обработки событий -- наиболее предпочтительный
    private void setPushUpsClickBehavior() {
        pushUpsCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Intent - намерерние, активность намеревается что-то сделать.
                Intent intent = new Intent(getApplicationContext(), PushupsActivity.class); // 3. создать intent(намерение)
                // создать intent(намерение) перейти в PushupsActivity
                // getApplicationContext() - контекст = интерфейс для доступа к глобальным ресурсам приложения;
                // доступ к области памяти в которой запущено приложение

                intent.putExtra(PUSH_UPS_KEY, pushupsCount); // 3. положить в intent информацию:
                // ключ(PUSH_UPS_KEY) и значение(pushupsCount), ключ выносим в константы

                startActivityForResult(intent, PUSHUPS_REQUEST_CODE);

//                startActivity(intent); // 3. с помощью метода startActivity, которому мы передаем етот intent
                // запустить другую активность: PushupsActivity.java


//                if (descriptionCV.getVisibility() != View.VISIBLE) {
//                    descriptionCV.setVisibility(View.VISIBLE);
//                    descriptionTextView.setText(R.string.pullups_discription);
//                    Toast.makeText(MainActivity.this, "Push Ups Pressed", Toast.LENGTH_SHORT).show();
//                } else {
//                    descriptionTextView.setText(R.string.pushups_description);
//                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        descriptionTextView.setText("");
    }

    //=================================================================  упражнение пресс
    private void setPressClickBehavior() {
        pressCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), PressActivity.class); // явный интент,
                // переход на PressActivity
                startActivity(intent);

//                getApplicationContext();


//                if (descriptionCV.getVisibility() != View.VISIBLE) {
//                    descriptionCV.setVisibility(View.VISIBLE);
//                    descriptionTextView.setText(R.string.press_description);
//                    Toast.makeText(MainActivity.this, "Push Ups Pressed", Toast.LENGTH_SHORT).show();
//                } else {
//                    descriptionTextView.setText(R.string.press_description);
//                }
            }
        });
    }

    //=================================================================  расстяжка
    private void setStretchingClickBehavior() {
        stretchingCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), StretchingActivity.class); // явный интент,
                // переход на StretchingActivity
                startActivity(intent);
            }
        });
    }
    //=================================================================

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PUSHUPS_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            pushupsCount = data.getIntExtra(PUSH_UPS_KEY, 0);
            setLastRepeatCount();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}