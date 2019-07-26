package com.spencerstudios.unixtimelive;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView tvMilliseconds, tvSeconds, tvFormattedDateTime;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setVisibility(View.GONE);

        tvSeconds = findViewById(R.id.tv_secs);
        tvMilliseconds = findViewById(R.id.tv_millis);
        tvFormattedDateTime = findViewById(R.id.tv1);

        handler = new Handler();
        handler.post(runnable);
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {

            long now_time = System.currentTimeMillis();

            tvSeconds.setText(String.format(Locale.getDefault(), "%,ds", now_time / 1000L));
            tvMilliseconds.setText(String.format(Locale.getDefault(), "%,dms", now_time));
            tvFormattedDateTime.setText(DateFormat.getDateInstance(DateFormat.FULL).format(now_time).concat(", ").concat(DateFormat.getTimeInstance().format(now_time)));

            handler.postDelayed(this, 0);
        }
    };

    @Override
    public void onBackPressed() {
        if (handler != null)
            handler.removeCallbacks(runnable);
        super.onBackPressed();
    }
}
