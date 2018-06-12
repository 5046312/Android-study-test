package com.example.first.studytest.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.first.studytest.R;

public class IntentNewActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_new);

        Button btn = findViewById(R.id.withoutResult);
        btn.setOnClickListener(this);

        btn = findViewById(R.id.withResult);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.withoutResult: // 直接返回，onActivityResult 返回 0
                finish();
                break;
            case R.id.withResult: // setResult
                setResult(999);
                finish();
                break;
        }
    }
}
