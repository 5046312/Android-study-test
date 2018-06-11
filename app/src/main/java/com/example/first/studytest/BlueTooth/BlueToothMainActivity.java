package com.example.first.studytest.BlueTooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.first.studytest.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xyz.bboylin.universialtoast.UniversalToast;

public class BlueToothMainActivity extends AppCompatActivity implements View.OnClickListener {
    boolean openBT = false; // 开启蓝牙
    BluetoothAdapter blueadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blue_tooth_main);

        Button btn = findViewById(R.id.open_bt);
        btn.setOnClickListener(this);

        btn = findViewById(R.id.scan_bt);
        btn.setOnClickListener(this);

        blueadapter = BluetoothAdapter.getDefaultAdapter();

        // 注册蓝牙广播
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothDevice.ACTION_FOUND);
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 判断蓝牙是否开启
        openBT = isBlueToothOpen();
        if(openBT){
            UniversalToast.makeText(BlueToothMainActivity.this, "蓝牙已开启", UniversalToast.LENGTH_SHORT).showSuccess();
        }else{
            UniversalToast.makeText(BlueToothMainActivity.this, "蓝牙已关闭", UniversalToast.LENGTH_SHORT).showError();
        }
    }

    boolean isBlueToothOpen(){
        return blueadapter.isEnabled();
    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(BluetoothDevice.ACTION_FOUND.equals(action)){
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                showDeviceList(device);
            }else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)){
                UniversalToast.makeText(BlueToothMainActivity.this, "搜索结束", UniversalToast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.open_bt:
                Intent enabler = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enabler, 1);
                break;
            case R.id.scan_bt:
                deviceList.clear();
                blueadapter.startDiscovery();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == -1){
            // 已开启
            UniversalToast.makeText(BlueToothMainActivity.this, "蓝牙已开启", UniversalToast.LENGTH_SHORT).showSuccess();
            openBT = true;
        }else{
            // 未开启
            UniversalToast.makeText(BlueToothMainActivity.this, "蓝牙未开启", UniversalToast.LENGTH_SHORT).showError();
            openBT = false;
        }
    }

    AlertDialog device_choose_dialog;
    List<BluetoothDevice> deviceList = new ArrayList<>(); // 发现的设备列表

    // 显示搜索到的设备
    void showDeviceList(BluetoothDevice device){
        deviceList.add(device);
        // 显示alertDialog
        
    }
}
