package com.example.first.studytest.AlertDialog;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.first.studytest.R;

import java.util.ArrayList;
import java.util.List;

import xyz.bboylin.universialtoast.UniversalToast;

public class AlertDialogMainActivity extends AppCompatActivity implements View.OnClickListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog_main);

        Button button = findViewById(R.id.alert_dialog_confirm);
        button.setOnClickListener(this);

        button = findViewById(R.id.alert_dialog_list);
        button.setOnClickListener(this);

        button = findViewById(R.id.alert_dialog_radio);
        button.setOnClickListener(this);

        button = findViewById(R.id.alert_dialog_select);
        button.setOnClickListener(this);

        button = findViewById(R.id.alert_dialog_prompt);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.alert_dialog_confirm:
                AlertDialog.Builder build = new AlertDialog.Builder(AlertDialogMainActivity.this);
                build.setTitle("标题");
                build.setMessage("内容区域");
                build.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                build.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alertDialog = build.create();
                alertDialog.show();
                break;

            case R.id.alert_dialog_list:
                List<String> chooseList = new ArrayList<>();
                chooseList.add("选项A");
                chooseList.add("选项B");
                chooseList.add("选项C");

                AlertDialog.Builder builder = new AlertDialog.Builder(AlertDialogMainActivity.this);
                builder.setTitle("标题");
                builder.setItems(chooseList.toArray(new String[chooseList.size()]), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        UniversalToast.makeText(AlertDialogMainActivity.this, String.valueOf(which), UniversalToast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
                break;
            case R.id.alert_dialog_radio:
                final String[] sex = {"男", "女", "保密"};

                AlertDialog.Builder radioBuild = new AlertDialog.Builder(AlertDialogMainActivity.this);
                radioBuild.setTitle("请选择性别");
                radioBuild.setSingleChoiceItems(sex, 1, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        UniversalToast.makeText(AlertDialogMainActivity.this, String.valueOf(which), UniversalToast.LENGTH_SHORT).show();
                    }
                });
                radioBuild.setPositiveButton("确定", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                    }
                });
                radioBuild.setNegativeButton("取消", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                    }
                });
                radioBuild.show();
                break;

            case R.id.alert_dialog_select: // 多选
                AlertDialog.Builder selectBuilder = new AlertDialog.Builder(AlertDialogMainActivity.this);
                selectBuilder.setTitle("多选选项");
                String[] selectArray = {"多选A", "多选B", "多选C", "多选D"};

                boolean[] defaultSelected = {true, false, true, false};
                selectBuilder.setMultiChoiceItems(selectArray, defaultSelected, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                    }
                });

                selectBuilder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                selectBuilder.show();
                break;
            case R.id.alert_dialog_prompt:
                View view = LayoutInflater.from(AlertDialogMainActivity.this).inflate(R.layout.dialog_prompt, null);
                AlertDialog.Builder promptBuilder = new AlertDialog.Builder(AlertDialogMainActivity.this);
                promptBuilder.setTitle("标题");
                promptBuilder.setView(view);
                promptBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                promptBuilder.show();
                break;

        }
    }
}
