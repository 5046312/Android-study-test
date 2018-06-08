package com.example.first.studytest.AlarmManager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.first.studytest.R;

import xyz.bboylin.universialtoast.UniversalToast;

public class AlarmManagerActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_manager);

        Button button = findViewById(R.id.after5sToast);
        button.setOnClickListener(this);

        button = findViewById(R.id.after1mNotify);
        button.setOnClickListener(this);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("time");
        intentFilter.addAction("other");
        registerReceiver(new mBroad(), intentFilter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.after5sToast:
                UniversalToast.makeText(AlarmManagerActivity.this, "5S后广播", Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setAction("time");
                PendingIntent pi = PendingIntent.getBroadcast(AlarmManagerActivity.this, 0, intent, 0);
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                int five = 5 * 1000;
                alarmManager.set(AlarmManager.RTC_WAKEUP, five, pi);
                break;
            case R.id.after1mNotify:
                UniversalToast.makeText(AlarmManagerActivity.this, "other 5s", Toast.LENGTH_LONG).show();
                Intent dd = new Intent();
                dd.setAction("other");
                PendingIntent pp = PendingIntent.getBroadcast(AlarmManagerActivity.this, 0, dd, 0);
                AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP, 5 * 1000, pp);
                break;
        }
    }

    class mBroad extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "接收广播", Toast.LENGTH_SHORT).show();
        }
    }
}
