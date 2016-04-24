package com.helloarron.datetimepicker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by arron on 16/4/23.
 */
public class NormalTypeActivity extends AppCompatActivity {

    private Calendar calendar = Calendar.getInstance();
    private DatePicker datePicker;
    private TimePicker timePicker;

    private int year, month, day, hour, minute;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_type);

        datePicker = (DatePicker) findViewById(R.id.date_picker);
        timePicker = (TimePicker) findViewById(R.id.time_picker);

        // 获取年月日时分秒等信息
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH)+1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);

        // 设置title
        setTitle(year+"-"+month+"-"+day+" "+hour+":"+minute);

        // datePicker初始化并设置监听事件
        datePicker.init(year, calendar.get(Calendar.MONTH), day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                setTitle(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
            }
        });

        // timePicker监听事件
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                setTitle(hourOfDay + ":" + minute);
            }
        });

    }
}
