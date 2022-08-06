package com.example.main_;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;

import java.util.Calendar;

public class post extends AppCompatActivity {

    private Spinner spinner;
    private DatePickerDialog datePickerDialog;
    private DatePickerDialog datePickerDialog2;
    private Button dateButton;
    private Button dateButton2;
    private Button btn_move;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        initDatePicker();
        initDatePicker2();
        dateButton = findViewById(R.id.dp_post_startDay);
        dateButton.setText(getTodaysDate());
        dateButton2 = findViewById(R.id.dp_post_endDay);
        dateButton2.setText(getTodaysDate2());


        btn_move = findViewById(R.id.bt_post_back);
        btn_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btn_move = findViewById(R.id.bt_post_cancel);
        btn_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }





    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year =cal.get(Calendar.YEAR);
        int month =cal.get(Calendar.MONTH);
        month = month + 1;
        int day =cal.get(Calendar.DAY_OF_MONTH);

        return makeDateString(day,month, year);

    }

    private String getTodaysDate2() {
        Calendar cal = Calendar.getInstance();
        int year =cal.get(Calendar.YEAR);
        int month =cal.get(Calendar.MONTH);
        month = month + 1;
        int day =cal.get(Calendar.DAY_OF_MONTH);

        return makeDateString2(day,month, year);

    }

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month+1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);
            }
        };



        Calendar cal = Calendar.getInstance();
        int year =cal.get(Calendar.YEAR);
        int month =cal.get(Calendar.MONTH);
        int day =cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog( this, style, dateSetListener, year, month, day);

    }

    private void initDatePicker2()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month+1;
                String date = makeDateString2(day, month, year);
                dateButton2.setText(date);
            }
        };



        Calendar cal = Calendar.getInstance();
        int year =cal.get(Calendar.YEAR);
        int month =cal.get(Calendar.MONTH);
        int day =cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog2 = new DatePickerDialog( this, style, dateSetListener, year, month, day);

    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + getDayFormat(day) + " " + year;
    }

    private String makeDateString2(int day, int month, int year) {
        return getMonthFormat(month) + " " + getDayFormat(day) + " " + year;
    }

    private String getDayFormat(int day) {
        if(day == 1)
            return "1일";
        if(day == 2)
            return "2일";
        if(day == 3)
            return "3일";
        if(day == 4)
            return "4일";
        if(day == 5)
            return "5일";
        if(day == 6)
            return "6일";
        if(day == 7)
            return "7일";
        if(day == 8)
            return "8일";
        if(day == 9)
            return "9일";
        if(day == 10)
            return "10일";
        if(day == 11)
            return "11일";
        if(day == 12)
            return "12일";
        if(day == 13)
            return "13일";
        if(day == 14)
            return "14일";
        if(day == 15)
            return "15일";
        if(day == 16)
            return "16일";
        if(day == 17)
            return "17일";
        if(day == 18)
            return "18일";
        if(day == 19)
            return "19일";
        if(day == 20)
            return "20일";
        if(day == 21)
            return "21일";
        if(day == 22)
            return "22일";
        if(day == 23)
            return "23일";
        if(day == 24)
            return "24일";
        if(day == 25)
            return "25일";
        if(day == 26)
            return "26일";
        if(day == 27)
            return "27일";
        if(day == 28)
            return "28일";
        if(day == 29)
            return "29일";
        if(day == 30)
            return "30일";
        if(day == 31)
            return "31일";

        return "1일";

    }

    private String getMonthFormat(int month) {
        if(month == 1)
            return "1월";
        if(month == 2)
            return "2월";
        if(month == 3)
            return "3월";
        if(month == 4)
            return "4월";
        if(month == 5)
            return "5월";
        if(month == 6)
            return "6월";
        if(month == 7)
            return "7월";
        if(month == 8)
            return "8월";
        if(month == 9)
            return "9월";
        if(month == 10)
            return "10월";
        if(month == 11)
            return "11월";
        if(month == 12)
            return "12월";

        return "1월";

    }


    public void openDatePicker(View view) {
        datePickerDialog2.show();
        datePickerDialog.show();
    }


}