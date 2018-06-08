package com.example.first.studytest.ActionBar;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.first.studytest.R;

public class ClickMenuActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_menu);
        Button btn = findViewById(R.id.click_menu_tooltip);
        btn.setOnClickListener(this);

        // 上下文菜单
        btn = findViewById(R.id.click_menu_context);
        registerForContextMenu(btn);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.click_menu_tooltip:
                PopupMenu popupMenu = new PopupMenu(ClickMenuActivity.this, v);
                getMenuInflater().inflate(R.menu.normal_menu, popupMenu.getMenu());

                // 设置监听
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.menu_item_1:
                                Toast.makeText(ClickMenuActivity.this, "点击了", Toast.LENGTH_LONG).show();
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
                break;
        }
    }

    @SuppressLint("ResourceType")
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = new MenuInflater(ClickMenuActivity.this);
        menuInflater.inflate(R.menu.normal_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_item_1:
                Toast.makeText(ClickMenuActivity.this, "点击", Toast.LENGTH_LONG).show();
                break;
        }
        return true;
    }
}

