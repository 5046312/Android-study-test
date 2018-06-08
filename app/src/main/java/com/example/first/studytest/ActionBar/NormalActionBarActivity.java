package com.example.first.studytest.ActionBar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.first.studytest.R;

public class NormalActionBarActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_action_bar);

        Button button = findViewById(R.id.refreshActionBar);
        button.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.normal_action_bar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_bar_next_item:
                Toast.makeText(NormalActionBarActivity.this, "点击下一个", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // 切换 下一个 按钮
        menu.findItem(R.id.action_bar_next_item).setTitle(Math.random() + "随机");
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.refreshActionBar:
                invalidateOptionsMenu();//通知系统刷新Menu
                break;
        }
    }
}
