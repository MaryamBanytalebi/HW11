package com.example.hw11.controller.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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
    private Date mTaskDate;
    private DatePicker mDatePicker;

    //private static final String ARG_PARAM1 = "param1";

    //private String mParam1;

    public DatePickerFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
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

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.fragment_date_picker, null);
        findViews(view);
        initViews();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setView(view)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int year = mDatePicker.getYear();
                        int month = mDatePicker.getMonth();
                        int dayOfMonth = mDatePicker.getDayOfMonth();

                        GregorianCalendar gregorianCalendar = new GregorianCalendar(year,month,dayOfMonth);
                        Date userSelectedDate = gregorianCalendar.getTime();
                        Fragment fragment = getTargetFragment();
                        if (fragment != null && fragment instanceof Custom_DialogFragment){
                            Custom_DialogFragment custom_dialogFragment =
                                    (Custom_DialogFragment) fragment;

                            custom_dialogFragment.updateCrimeDate(userSelectedDate);
                        }

                    }
                });
        AlertDialog dialog = builder.create();
        return dialog ;

    }

   /* @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_date_picker, container, false);
        findViews(view);
        initViews();
        return view;
    }*/

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
}