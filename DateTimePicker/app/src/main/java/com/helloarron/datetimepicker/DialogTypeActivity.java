package com.helloarron.datetimepicker;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by arron on 16/4/23.
 */
public class DialogTypeActivity extends AppCompatActivity implements View.OnClickListener {

    private Calendar calendar = Calendar.getInstance();
    private Button btnDate, btnTime;

    private int year, month, day, hour, minute;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_type);

        btnDate = (Button) findViewById(R.id.date_btn);
        btnTime = (Button) findViewById(R.id.time_btn);

        btnTime.setOnClickListener(this);
        btnDate.setOnClickListener(this);

        // 获取年月日时分秒等信息
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);

        // 设置title
        setTitle(year + "-" + month + "-" + day + " " + hour + ":" + minute);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.date_btn:
                new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        setTitle(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    }
                }, year, calendar.get(Calendar.MONTH), day).show();
                break;
            case R.id.time_btn:
                new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        setTitle(hourOfDay + ":" + minute);
                    }
                }, hour, minute, true).show();
                break;
        }
    }
}
