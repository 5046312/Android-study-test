package com.example.first.studytest.ProgressBar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.first.studytest.R;

public class ProgressBarNormalActivity extends AppCompatActivity {

    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar_normal);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(100);
        progressBar.setProgress(0);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        int current = progressBar.getProgress();
                        if (current < 100) {
                            Thread.sleep(10);
                            progressBar.setProgress(current + 1);
                        } else {
                            progressBar.setVisibility(View.INVISIBLE);
                            break;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }
}
