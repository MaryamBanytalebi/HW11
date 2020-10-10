package com.example.hw11.controller.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.example.hw11.R;
import com.example.hw11.model.Task;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DatePickerFragment extends DialogFragment {
    public static final String ARG_TASK_DATE = "task date";
    public static final String EXTRA_USER_SELECTED_DATE = "user select date";
    private Date mTaskDate;
    private DatePicker mDatePicker;
    private Calendar mCalendar;

    public DatePickerFragment() {
        // Required empty public constructor
    }

    public static DatePickerFragment newInstance(Date taskDate) {
        DatePickerFragment fragment = new DatePickerFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_TASK_DATE,taskDate);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTaskDate = (Date) getArguments().getSerializable(ARG_TASK_DATE);
        mCalendar = Calendar.getInstance();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.fragment_date_picker, null);
        findViews(view);
        initViews();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle("date of task:")
                .setView(view)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        extractDateFromDatePicker();
                        sendResult(mCalendar);
                    }
                })
                .setNegativeButton("cancle",null);
                AlertDialog dialog = builder.create();
                return dialog;
    }

    private void initViews() {
        Calendar calender = Calendar.getInstance();
        calender.setTime(mTaskDate);
        int year = calender.get(Calendar.YEAR);
        int monthOfYear = calender.get(Calendar.MONTH);
        int dayofMonth = calender.get(Calendar.DAY_OF_MONTH);
        mDatePicker.init(year,monthOfYear,dayofMonth,null);
    }

    private void findViews(View view) {
        mDatePicker = view.findViewById(R.id.date_picker);
    }

    private void extractDateFromDatePicker(){
        int year = mDatePicker.getYear();
        int month = mDatePicker.getMonth();
        int dayOfMonth = mDatePicker.getDayOfMonth();

        mCalendar.set(Calendar.YEAR,year);
        mCalendar.set(Calendar.MONTH,month);
        mCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
    }

    private void sendResult(Calendar userSelectedDate){
        Fragment fragment = getTargetFragment();
        int requestCode = getTargetRequestCode();
        int resultCode = Activity.RESULT_OK;
        Intent intent = new Intent();
        intent.putExtra(EXTRA_USER_SELECTED_DATE, userSelectedDate);

        fragment.onActivityResult(requestCode,resultCode,intent);


    }
}