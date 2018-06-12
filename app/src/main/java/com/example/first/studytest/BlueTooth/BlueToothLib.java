package com.example.first.studytest.BlueTooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

// 全局蓝牙Lib
public class BlueToothLib {

    // 蓝牙监听
    public static BroadcastReceiver blueToothState = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

        }
    };
}
