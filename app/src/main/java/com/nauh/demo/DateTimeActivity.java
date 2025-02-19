package com.nauh.demo;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;


public class DateTimeActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et, ed;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.date_time);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        et = findViewById(R.id.eTime);
        ed = findViewById(R.id.eDate);
        et.setOnClickListener(this);
        ed.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == et) {
            // TimePickerDialog
            Calendar calendar = Calendar.getInstance();
            int ch = calendar.get(Calendar.HOUR_OF_DAY);
            int cm = calendar.get(Calendar.MINUTE);
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onTimeSet(TimePicker timePicker, int h, int m) {
                    et.setText(h + ":" + m);
                }
            }, ch, cm, true);
            timePickerDialog.show();
        } else if (view == ed) {
            // DatePickerDialog
            Calendar calendar = Calendar.getInstance();
            int y = calendar.get(Calendar.YEAR);
            int m = calendar.get(Calendar.MONTH);
            int d = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int yy, int mm, int dd) {
                    ed.setText(dd + "/" + (mm + 1) + "/" + yy);
                }
            }, y, m, d);
            datePickerDialog.show();
        }
    }
}