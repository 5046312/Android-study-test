package com.example.first.studytest.ActionBar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

import com.example.first.studytest.R;

public class ClickMenuActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_menu);
        Button btn = findViewById(R.id.click_menu_tooltip);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.click_menu_tooltip:
                PopupMenu popupMenu = new PopupMenu(ClickMenuActivity.this, v);
                getMenuInflater().inflate(R.menu.normal_menu, popupMenu.getMenu());
                popupMenu.show();
                break;
        }
    }
}
