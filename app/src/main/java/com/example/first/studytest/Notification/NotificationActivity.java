package com.example.first.studytest.Notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.first.studytest.MainActivity;
import com.example.first.studytest.R;

public class NotificationActivity extends AppCompatActivity implements View.OnClickListener{

    private int id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        Button btn = findViewById(R.id.normal_notification);
        btn.setOnClickListener(this);

        btn = findViewById(R.id.cancel_notification);
        btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.normal_notification:
                normal();
                break;
            case R.id.cancel_notification:
                cancelNotification();
                break;
        }
    }

    private void normal(){
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        // 设置通知Build

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, "ABC");
        mBuilder.setSmallIcon(R.drawable.icon);
        mBuilder.setContentTitle("这是标题");
        mBuilder.setContentText("这是正文，这是正文，这是正文，这是正文，这是正文");
        mBuilder.setSubText("这是摘要");
        mBuilder.setAutoCancel(true);
        mBuilder.setContentInfo("Info");
        mBuilder.setTicker("在状态栏上显示的文本");

        // 设置优先级
//        mBuilder.setPriority(NotificationCompat.PRIORITY_MAX);

        //自定义消息时间，以毫秒为单位，当前设置为比系统时间少一小时
        mBuilder.setWhen(System.currentTimeMillis() - 3600000);


//        mBuilder.setOngoing(true); // 设置为一个正在进行的通知，此时用户无法清除通知

        //设置消息的提醒方式，震动提醒：DEFAULT_VIBRATE     声音提醒：NotificationCompat.DEFAULT_SOUND
        //三色灯提醒NotificationCompat.DEFAULT_LIGHTS     以上三种方式一起：DEFAULT_ALL
        mBuilder.setDefaults(NotificationCompat.DEFAULT_SOUND);
        // 设置震动方式，延迟零秒，震动一秒，延迟一秒、震动一秒
//        mBuilder.setVibrate(new long[]{0, 1000, 1000, 1000});

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (mNotificationManager != null) {
            // 设置通知id， 相同id会有显示条数限制， 由mBuilder.setNumber(int)控制
            mNotificationManager.notify(1, mBuilder.build());
        }
    }

    private void cancelNotification(){
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.cancelAll();
    }
}
