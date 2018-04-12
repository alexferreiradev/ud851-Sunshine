package com.example.android.sunshine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private static final String FORECAST_SHARE_HASHTAG = " #SunshineApp";
    private String weather;
    private TextView weatherTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // (2) Display the weather forecast that was passed from MainActivity
        if (getIntent().hasExtra(MainActivity.EXTRA_WEATHER_PARAM_KEY)) {
            weather = getIntent().getStringExtra(MainActivity.EXTRA_WEATHER_PARAM_KEY);
        } else {
            throw new IllegalArgumentException("Activity espera parametro weather");
        }

        weatherTV = (TextView) findViewById(R.id.tv_weather_data);
        weatherTV.setText(weather);
    }
}