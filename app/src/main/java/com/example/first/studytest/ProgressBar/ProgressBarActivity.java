package com.example.first.studytest.ProgressBar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.first.studytest.R;

public class ProgressBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("ProgressBar");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);
        Button btn = findViewById(R.id.btn_ProgressBar_normal);
        btn.setOnClickListener(new btnOnClick());
        btn = findViewById(R.id.btn_ProgressBar_round);
        btn.setOnClickListener(new btnOnClick());
        btn = findViewById(R.id.btn_ProgressBar_diy);
        btn.setOnClickListener(new btnOnClick());
    }

    class btnOnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            switch (v.getId()) {
                case R.id.btn_ProgressBar_round:
                    // 圆形
                    intent.setClass(ProgressBarActivity.this, ProgressBarRoundActivity.class);
                    break;
                case R.id.btn_ProgressBar_normal:
                    intent.setClass(ProgressBarActivity.this, ProgressBarNormalActivity.class);
                    break;
                case R.id.btn_ProgressBar_diy:
                    intent.setClass(ProgressBarActivity.this, ProgressBarDIYActivity.class);
                    break;
            }
            startActivity(intent);
        }
    }
}
