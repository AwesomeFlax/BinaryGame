package com.example.pavilion.androidgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MenuActivity extends Activity {

    TextView Start;
    TextView SecondGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //кнопка запуска класической версии игры
        Start = (TextView) findViewById(R.id.start_but);
        Start.setOnClickListener(startGame);

        SecondGame = (TextView) findViewById(R.id.second_game_but);
        SecondGame.setOnClickListener(second_game_launch);
    }

    View.OnClickListener startGame = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent myIntent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(myIntent);
        }
    };

    View.OnClickListener second_game_launch = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent myIntent = new Intent(getBaseContext(), PlateActivity.class);
            startActivity(myIntent);
        }
    };
}
