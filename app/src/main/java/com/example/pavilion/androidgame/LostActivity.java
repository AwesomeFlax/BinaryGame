package com.example.pavilion.androidgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LostActivity extends Activity {

    int playersScore;
    int previousGame;

    TextView score;
    TextView restart;
    TextView menu;

    View.OnClickListener restart_game = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;

            //определяем, в какую игру мы играли, чтобы вернуться в неё же
            switch (previousGame) {
                case 1:     //первая игра
                    intent = new Intent(getBaseContext(), MainActivity.class);
                    break;

                case 2:     //вторая игра
                    intent = new Intent(getBaseContext(), PlateActivity.class);
                    break;

                default:    //если всё делать правильно, сюда мы не попадем
                    intent = new Intent(getBaseContext(), MenuActivity.class);
                    break;
            }

            overridePendingTransition(0, 0);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            finish();
            overridePendingTransition(0, 0);
            startActivity(intent);
        }
    };

    View.OnClickListener go_to_menu = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getBaseContext(), MenuActivity.class);
            overridePendingTransition(0, 0);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            finish();
            overridePendingTransition(0, 0);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost);

        score = (TextView) findViewById(R.id.score);
        restart = (TextView) findViewById(R.id.restart);
        menu = (TextView)findViewById(R.id.menu);

        restart.setOnClickListener(restart_game);
        menu.setOnClickListener(go_to_menu);

        Intent myIntent = getIntent();

        //получение информации от прошлого окна
        // в скобках  значение по умолчанию, если передастся пустота
        playersScore = myIntent.getIntExtra("score", 1);
        previousGame = myIntent.getIntExtra("mode", 0);

        //наполнение окна поражения смыслом
        String qwe = (String) score.getText();
        score.setText(qwe + " " + String.valueOf(playersScore));
    }
}
