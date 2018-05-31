package com.example.first.studytest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.first.studytest.ProgressBar.ProgressBarActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.btn_ProgressBar);
        btn.setOnClickListener(new BtnClick());
    }

    class BtnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            switch (v.getId()) {
                case R.id.btn_ProgressBar:
                    intent.setClass(MainActivity.this, ProgressBarActivity.class);
                    break;
            }
            startActivity(intent);
        }
    }
}
