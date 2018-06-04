package com.example.first.studytest.PullRefresh;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dinuscxj.refresh.RecyclerRefreshLayout;
import com.example.first.studytest.R;

public class NormalPullRefreshActivity extends AppCompatActivity {

    RecyclerRefreshLayout recyclerRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_pull_refresh);

        recyclerRefreshLayout = new RecyclerRefreshLayout(NormalPullRefreshActivity.this);

        recyclerRefreshLayout.setOnRefreshListener(new RecyclerRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });
    }
}
