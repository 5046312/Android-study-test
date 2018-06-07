package com.example.first.studytest.ActionBar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.first.studytest.R;

public class ActionBarActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_bar);

        Button btn = findViewById(R.id.Normal_ActionBar);
        btn.setOnClickListener(this);

        btn = findViewById(R.id.Normal_Menu);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.Normal_ActionBar:
                intent.setClass(ActionBarActivity.this, NormalActionBarActivity.class);
                break;

            case R.id.Normal_Menu:
                intent.setClass(ActionBarActivity.this, ClickMenuActivity.class);
                break;
        }
        startActivity(intent);
    }
}
