package com.example.first.studytest.BlueTooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.first.studytest.R;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import xyz.bboylin.universialtoast.UniversalToast;

public class BlueToothMainActivity extends AppCompatActivity implements View.OnClickListener {
    BluetoothDevice device;
    OutputStream outputStream;
    BluetoothSocket bluetoothSocket;

    boolean openBT = false; // 开启蓝牙
    BluetoothAdapter blueadapter;
    AlertDialog.Builder builder;

    Button btn_scan; // 搜索按钮
    Button btn_print; // 打印按钮
    TextView bt_device_name; // 已连接的设备名

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blue_tooth_main);

        Button btn = findViewById(R.id.open_bt);
        btn.setOnClickListener(this);

        btn_scan = findViewById(R.id.scan_bt);
        btn_scan.setOnClickListener(this);

        btn_print = findViewById(R.id.print_bt);
        btn_print.setOnClickListener(this);

        bt_device_name = findViewById(R.id.bt_device_name);

        blueadapter = BluetoothAdapter.getDefaultAdapter();

        // 注册蓝牙广播
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothDevice.ACTION_FOUND);
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(broadcastReceiver, intentFilter);

        // 显示alertDialog
        builder = new AlertDialog.Builder(BlueToothMainActivity.this);
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
                // 搜索结束
                btn_scan.setEnabled(true);
                btn_scan.setText("搜索设备");
            }
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.open_bt:
                Intent enabler = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enabler, 1);
                break;
            case R.id.scan_bt:
                btn_scan.setEnabled(false);
                btn_scan.setText("搜索中...");
                deviceList.clear();
                blueadapter.startDiscovery();
                break;
            case R.id.print_bt: // 打印数据
                print("打印内容\n");
                break;
        }
    }

    // 打印
    void print(String str){
        if(bluetoothSocket != null && bluetoothSocket.isConnected()){
            try {
                System.out.println("设备连接成功，开始打印");
                outputStream = bluetoothSocket.getOutputStream();
                byte[] data = str.getBytes("gbk");
                outputStream.write(data, 0, data.length);
                outputStream.flush();
                System.out.println("发送");
            } catch (IOException e) {
                e.printStackTrace();
            }
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

    AlertDialog mDeviceListAlertDialog; // 设备选择框弹窗
    // 显示搜索到的设备
    void showDeviceList(BluetoothDevice device){
        if(device != null && device.getName() != null){
            deviceList.add(device);
        }
        List<String> mDeviceName = new ArrayList<>();
        for (BluetoothDevice btDevice: deviceList){
            mDeviceName.add(btDevice.getName());
        }
        String[] deviceName = mDeviceName.toArray(new String[mDeviceName.size()]);
        if(mDeviceListAlertDialog == null){
            builder.setTitle("选择蓝牙设备");
            builder.setItems(deviceName, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // 取消搜索
                    if (blueadapter != null && blueadapter.isDiscovering()) {
                        System.out.println("取消搜索");
                        blueadapter.cancelDiscovery();
                    }
                    connectDevice(deviceList.get(which));
                }
            });
            mDeviceListAlertDialog = builder.show();
        }else{
            mDeviceListAlertDialog.dismiss();
            mDeviceListAlertDialog = null;
            showDeviceList(null);
        }
    }


    void connectDevice(BluetoothDevice device) {
        BluetoothDevice bluetoothDevice = blueadapter.getRemoteDevice(device.getAddress());
        new printString(device,"打印文字\n").start();
    }

    class printString extends Thread{
        String str;
        BluetoothDevice device;
        public printString(BluetoothDevice device,String str){
            this.device = device;
            this.str = str;
        }

        @Override
        public void run() {
            try {
                UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
                bluetoothSocket = device.createRfcommSocketToServiceRecord(uuid);
                bluetoothSocket.connect();
                if(bluetoothSocket.isConnected()){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            bt_device_name.setText("已连接");
                        }
                    });
                    print("连接成功\n");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
