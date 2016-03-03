package com.example.pavilion.androidgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Chronometer;
import android.widget.TextView;

public class PlateActivity extends Activity {
    //хронометр для поражения
    Chronometer chronometer;

    //переменная, считающая набранные очки
    int score;

    //время до поражения
    int time_num;
    TextView time;

    //что происходит каждую секунду
    Chronometer.OnChronometerTickListener gameProcess = new Chronometer.OnChronometerTickListener() {
        @Override
        public void onChronometerTick(Chronometer chronometer) {
            //перевод миллисекунд в привычный нам формат
            time_num = time_num - 1000;
            int min;
            int sec;

            min = time_num / 60000;
            sec = (time_num - min * 60000) / 1000;

            //проверка на поражение
            if (time_num == 0) lostGame();

            //красивое формирование счетчика
            if (sec < 10)
                time.setText(String.valueOf(min) + ":0" + String.valueOf(sec));
            else
                time.setText(String.valueOf(min) + ":" + String.valueOf(sec));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plate);

        //инициализация данных
        chronometer = (Chronometer) findViewById(R.id.gameProcess);
        time = (TextView) findViewById(R.id.text);
        time_num = 60000;

        chronometer.setOnChronometerTickListener(gameProcess);
        chronometer.start();
    }

    void lostGame() {
        chronometer.stop();

        Intent myIntent = new Intent(getBaseContext(), LostActivity.class);
        myIntent.putExtra("mode", 2);
        myIntent.putExtra("score", score);
        startActivity(myIntent);
    }
}
