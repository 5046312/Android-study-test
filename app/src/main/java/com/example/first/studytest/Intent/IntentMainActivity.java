package com.example.first.studytest.Intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.first.studytest.R;

import xyz.bboylin.universialtoast.UniversalToast;

public class IntentMainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_main);

        Button btn = findViewById(R.id.openNewActivity);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.openNewActivity:
                Intent intent = new Intent(IntentMainActivity.this, IntentNewActivity.class);
                startActivityForResult(intent, 100);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UniversalToast.makeText(IntentMainActivity.this, String.valueOf(resultCode), UniversalToast.LENGTH_SHORT).show();
        if(data != null){
            System.out.println(data.getExtras().getString("name"));
        }
    }
}
