package com.example.first.studytest.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.mItemViewHold> {


    @Override
    public mItemViewHold onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(mItemViewHold holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class mItemViewHold extends RecyclerView.ViewHolder {
        public mItemViewHold(View itemView) {
            super(itemView);
        }
    }
}


