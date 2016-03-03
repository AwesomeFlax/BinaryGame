package com.example.pavilion.androidgame;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;


public class MainActivity extends Activity {
    //хронометр для поражения
    Chronometer chronometer;

    //кнопки для набора статистики...
    ImageButton but_1;
    ImageButton but_2;
    ImageButton but_4;
    ImageButton but_8;
    ImageButton but_16;
    ImageButton but_32;
    ImageButton but_64;
    ImageButton but_128;
    ImageButton but_256;
    ImageButton but_512;

    //.... и цифры для тих кнопок
    TextView let_1;
    TextView let_2;
    TextView let_4;
    TextView let_8;
    TextView let_16;
    TextView let_32;
    TextView let_64;
    TextView let_128;
    TextView let_256;
    TextView let_512;

    //логика нажатой/отжатой кнопки
    boolean but_1_pressed;
    boolean but_2_pressed;
    boolean but_4_pressed;
    boolean but_8_pressed;
    boolean but_16_pressed;
    boolean but_32_pressed;
    boolean but_64_pressed;
    boolean but_128_pressed;
    boolean but_256_pressed;
    boolean but_512_pressed;

    //сообщение о поражении
    TextView finish;
    TextView repeat;

    //уровень сложности игры
    int difficult;

    //время до поражения
    int time_num;
    TextView time;
    TextView time_header;

    //сколько пользователь уже набрал циферками
    int current;
    TextView current_num;
    TextView current_header;

    //сколько надо набрать для зачисления
    int randomed;
    TextView randomed_num;

    //сколько набрал в текущей сессии
    int score;
    TextView score_num;

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

    View.OnClickListener plusToCurrent;

    {
        plusToCurrent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //проверка нажатой кнопочки
                switch (v.getId()) {
                    case R.id.b_1:
                        changeBackgr(1);
                        if (but_1_pressed) {
                            current -= 1;
                            but_1_pressed = false;
                        } else {
                            current += 1;
                            but_1_pressed = true;
                        }
                        break;

                    case R.id.b_2:
                        changeBackgr(2);
                        if (but_2_pressed) {
                            current -= 2;
                            but_2_pressed = false;
                        } else {
                            current += 2;
                            but_2_pressed = true;
                        }
                        break;

                    case R.id.b_4:
                        changeBackgr(3);
                        if (but_4_pressed) {
                            current -= 4;
                            but_4_pressed = false;
                        } else {
                            current += 4;
                            but_4_pressed = true;
                        }
                        break;

                    case R.id.b_8:
                        changeBackgr(4);
                        if (but_8_pressed) {
                            current -= 8;
                            but_8_pressed = false;
                        } else {
                            current += 8;
                            but_8_pressed = true;
                        }
                        break;

                    case R.id.b_16:
                        changeBackgr(5);
                        if (but_16_pressed) {
                            current -= 16;
                            but_16_pressed = false;
                        } else {
                            current += 16;
                            but_16_pressed = true;
                        }
                        break;

                    case R.id.b_32:
                        changeBackgr(6);
                        if (but_32_pressed) {
                            current -= 32;
                            but_32_pressed = false;
                        } else {
                            current += 32;
                            but_32_pressed = true;
                        }
                        break;

                    case R.id.b_64:
                        changeBackgr(7);
                        if (but_64_pressed) {
                            current -= 64;
                            but_64_pressed = false;
                        } else {
                            current += 64;
                            but_64_pressed = true;
                        }
                        break;

                    case R.id.b_128:
                        changeBackgr(8);
                        if (but_128_pressed) {
                            current -= 128;
                            but_128_pressed = false;
                        } else {
                            current += 128;
                            but_128_pressed = true;
                        }
                        break;

                    case R.id.b_256:
                        changeBackgr(9);
                        if (but_256_pressed) {
                            current -= 256;
                            but_256_pressed = false;
                        } else {
                            current += 256;
                            but_256_pressed = true;
                        }
                        break;

                    case R.id.b_512:
                        changeBackgr(10);
                        if (but_512_pressed) {
                            current -= 512;
                            but_512_pressed = false;
                        } else {
                            current += 512;
                            but_512_pressed = true;
                        }
                        break;
                }
                current_num.setText(String.valueOf(current));

                //проверка, если пользователь набрал больше
                if (randomed < current)
                    ifYouDidWrongChoice();

                //проверка, если пользователь набрал правильно
                if (randomed == current) {
                    ifYouDidRightChoice();
                }
            }
        };
    }

    //получить новое число для угадывания

    void itsRandomTimeBaby() {
        randomed = (int) getRandomNum();
        randomed_num.setText(String.valueOf(randomed));
    }

    //если правильно посчитал
    void ifYouDidRightChoice() {
        randomed = 0;
        itsRandomTimeBaby();

        but_1_pressed = false;
        but_2_pressed = false;
        but_4_pressed = false;
        but_8_pressed = false;
        but_16_pressed = false;
        but_32_pressed = false;
        but_64_pressed = false;
        but_128_pressed = false;
        but_256_pressed = false;
        but_512_pressed = false;

        //обрабатываем переменные
        current = 0;
        score++;

        if (score % 5 == 0)
            time_num += 30000;

        if ((score % 10 == 0) && (difficult <= 6))
            difficult++;

        switch (difficult) {
            case 2:
                but_32.setVisibility(View.VISIBLE);
                let_32.setVisibility(View.VISIBLE);
                break;
            case 3:
                but_64.setVisibility(View.VISIBLE);
                let_64.setVisibility(View.VISIBLE);
                break;
            case 4:
                but_128.setVisibility(View.VISIBLE);
                let_128.setVisibility(View.VISIBLE);
                break;
            case 5:
                but_256.setVisibility(View.VISIBLE);
                let_256.setVisibility(View.VISIBLE);
                break;
            case 6:
                but_512.setVisibility(View.VISIBLE);
                let_512.setVisibility(View.VISIBLE);
                break;
        }

        //вывод на экран
        resetPicks();
        current_num.setText("0");
        score_num.setText(getResources().getString(R.string.score) + " " + String.valueOf(score));
    }

    void ifYouDidWrongChoice() {
        lostGame();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ловим всякую мелочь и инициализируем, если нужно
        current = 0;
        difficult = 1;
        time_num = 60000;
        current_num = (TextView) findViewById(R.id.current);
        randomed_num = (TextView) findViewById(R.id.randomNum);
        score_num = (TextView) findViewById(R.id.score);
        time = (TextView) findViewById(R.id.timer);
        chronometer = (Chronometer) findViewById(R.id.chronometer1);
        time_header = (TextView) findViewById(R.id.word_time);

        //по умолчанию все кнопочки отжаты
        but_1_pressed = false;
        but_2_pressed = false;
        but_4_pressed = false;
        but_8_pressed = false;
        but_16_pressed = false;
        but_32_pressed = false;
        but_64_pressed = false;
        but_128_pressed = false;
        but_256_pressed = false;
        but_512_pressed = false;

        //привязка кнопка-объект
        but_1 = (ImageButton) findViewById(R.id.b_1);
        but_2 = (ImageButton) findViewById(R.id.b_2);
        but_4 = (ImageButton) findViewById(R.id.b_4);
        but_8 = (ImageButton) findViewById(R.id.b_8);
        but_16 = (ImageButton) findViewById(R.id.b_16);
        but_32 = (ImageButton) findViewById(R.id.b_32);
        but_64 = (ImageButton) findViewById(R.id.b_64);
        but_128 = (ImageButton) findViewById(R.id.b_128);
        but_256 = (ImageButton) findViewById(R.id.b_256);
        but_512 = (ImageButton) findViewById(R.id.b_512);

        //нужно получить доступ к цифрам (для изменнеия их цвета и состояния видимости)
        let_1 = (TextView) findViewById(R.id.letter_1);
        let_2 = (TextView) findViewById(R.id.letter_2);
        let_4 = (TextView) findViewById(R.id.letter_4);
        let_8 = (TextView) findViewById(R.id.letter_8);
        let_16 = (TextView) findViewById(R.id.letter_16);
        let_32 = (TextView) findViewById(R.id.letter_32);
        let_64 = (TextView) findViewById(R.id.letter_64);
        let_128 = (TextView) findViewById(R.id.letter_128);
        let_256 = (TextView) findViewById(R.id.letter_256);
        let_512 = (TextView) findViewById(R.id.letter_512);

        //нажатие на кнопку - прибавка к числу
        but_1.setOnClickListener(plusToCurrent);
        but_2.setOnClickListener(plusToCurrent);
        but_4.setOnClickListener(plusToCurrent);
        but_8.setOnClickListener(plusToCurrent);
        but_16.setOnClickListener(plusToCurrent);
        but_32.setOnClickListener(plusToCurrent);
        but_64.setOnClickListener(plusToCurrent);
        but_128.setOnClickListener(plusToCurrent);
        but_256.setOnClickListener(plusToCurrent);
        but_512.setOnClickListener(plusToCurrent);

        itsRandomTimeBaby();

        chronometer.setOnChronometerTickListener(gameProcess);
        chronometer.start();
    }

    //если проиграл
    void lostGame() {
        chronometer.stop();

        Intent myIntent = new Intent(getBaseContext(), LostActivity.class);
        myIntent.putExtra("mode", 1);
        myIntent.putExtra("score", score);
        startActivity(myIntent);
    }

    //рандомим число (исходя из сложности)
    double getRandomNum() {
        double randomedNum = 0;

        //выбор диапазона рандома
        switch (difficult) {
            case 1:
                randomedNum = Math.random() * 30;
                break; //если макс число - 16
            case 2:
                randomedNum = Math.random() * 62;
                break; //если макс число - 32
            case 3:
                randomedNum = Math.random() * 126;
                break; //если макс число - 64
            case 4:
                randomedNum = Math.random() * 254;
                break; //если макс число - 128
            case 5:
                randomedNum = Math.random() * 510;
                break; //если макс число - 256
            case 6:
                randomedNum = Math.random() * 1022;
                break; //если макс число - 512
            default:
                randomedNum = Math.random() * 1022;
                break; //если макс число
        }

        return randomedNum + 1;
    }

    //меняем картинку по нажатию на кнопку
    void changeBackgr(int k) {
        switch (k) {
            case 1:
                if (!but_1_pressed) {
                    but_1.setBackgroundResource(R.drawable.used);
                    let_1.setTextColor(Color.parseColor("#f9c53f"));
                }
                else {
                    but_1.setBackgroundResource(R.drawable.unused);
                    let_1.setTextColor(Color.parseColor("#768daa"));
                }
                break;
            case 2:
                if (!but_2_pressed) {
                    but_2.setBackgroundResource(R.drawable.used);
                    let_2.setTextColor(Color.parseColor("#f9c53f"));
                }
                else {
                    but_2.setBackgroundResource(R.drawable.unused);
                    let_2.setTextColor(Color.parseColor("#768daa"));
                }
                break;
            case 3:
                if (!but_4_pressed) {
                    but_4.setBackgroundResource(R.drawable.used);
                    let_4.setTextColor(Color.parseColor("#f9c53f"));
                }
                else {
                    but_4.setBackgroundResource(R.drawable.unused);
                    let_4.setTextColor(Color.parseColor("#768daa"));
                }
                break;
            case 4:
                if (!but_8_pressed){
                    but_8.setBackgroundResource(R.drawable.used);
                    let_8.setTextColor(Color.parseColor("#f9c53f"));
                }
                else {
                    but_8.setBackgroundResource(R.drawable.unused);
                    let_8.setTextColor(Color.parseColor("#768daa"));
                }
                break;
            case 5:
                if (!but_16_pressed) {
                    but_16.setBackgroundResource(R.drawable.used);
                    let_16.setTextColor(Color.parseColor("#f9c53f"));
                }
                else {
                    but_16.setBackgroundResource(R.drawable.unused);
                    let_16.setTextColor(Color.parseColor("#768daa"));
                }
                break;
            case 6:
                if (!but_32_pressed){
                    but_32.setBackgroundResource(R.drawable.used);
                    let_32.setTextColor(Color.parseColor("#f9c53f"));
                }
                else {
                    but_32.setBackgroundResource(R.drawable.unused);
                    let_32.setTextColor(Color.parseColor("#768daa"));
                }
                break;
            case 7:
                if (!but_64_pressed){
                    but_64.setBackgroundResource(R.drawable.used);
                    let_64.setTextColor(Color.parseColor("#f9c53f"));
                }
                else {
                    but_64.setBackgroundResource(R.drawable.unused);
                    let_64.setTextColor(Color.parseColor("#768daa"));
                }
                break;
            case 8:
                if (!but_128_pressed){
                    but_128.setBackgroundResource(R.drawable.used);
                    let_128.setTextColor(Color.parseColor("#f9c53f"));
                }
                else {
                    but_128.setBackgroundResource(R.drawable.unused);
                    let_128.setTextColor(Color.parseColor("#768daa"));
                }
                break;
            case 9:
                if (!but_256_pressed){
                    but_256.setBackgroundResource(R.drawable.used);
                    let_256.setTextColor(Color.parseColor("#f9c53f"));
                }
                else {
                    but_256.setBackgroundResource(R.drawable.unused);
                    let_256.setTextColor(Color.parseColor("#768daa"));
                }
                break;
            case 10:
                if (!but_512_pressed){
                    but_512.setBackgroundResource(R.drawable.used);
                    let_512.setTextColor(Color.parseColor("#f9c53f"));
                }
                else {
                    but_512.setBackgroundResource(R.drawable.unused);
                    let_512.setTextColor(Color.parseColor("#768daa"));
                }
                break;
        }
    }

    //все картинки и цифры делаем "ненажатыми"
    void resetPicks() {
        but_1.setBackgroundResource(R.drawable.unused);
        let_1.setTextColor(Color.parseColor("#768daa"));
        but_2.setBackgroundResource(R.drawable.unused);
        let_2.setTextColor(Color.parseColor("#768daa"));
        but_4.setBackgroundResource(R.drawable.unused);
        let_4.setTextColor(Color.parseColor("#768daa"));
        but_8.setBackgroundResource(R.drawable.unused);
        let_8.setTextColor(Color.parseColor("#768daa"));
        but_16.setBackgroundResource(R.drawable.unused);
        let_16.setTextColor(Color.parseColor("#768daa"));
        but_32.setBackgroundResource(R.drawable.unused);
        let_32.setTextColor(Color.parseColor("#768daa"));
        but_64.setBackgroundResource(R.drawable.unused);
        let_64.setTextColor(Color.parseColor("#768daa"));
        but_128.setBackgroundResource(R.drawable.unused);
        let_128.setTextColor(Color.parseColor("#768daa"));
        but_256.setBackgroundResource(R.drawable.unused);
        let_256.setTextColor(Color.parseColor("#768daa"));
        but_512.setBackgroundResource(R.drawable.unused);
        let_512.setTextColor(Color.parseColor("#768daa"));
    }
}