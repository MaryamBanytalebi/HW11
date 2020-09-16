package com.example.hw11.controller.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;

import com.example.hw11.R;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimePickerFragment extends DialogFragment {
    public static final String ARG_TASK_TIME = "task time";
    private TimePicker mTimePicker;
    private Date mTaskTime;

    public TimePickerFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static TimePickerFragment newInstance(Date taskTime) {
        TimePickerFragment fragment = new TimePickerFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTaskTime = (Date) getArguments().getSerializable(ARG_TASK_TIME);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.fragment_time_picker,null);
        findViews(view);
        initViews();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setView(view)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int hour = mTimePicker.getCurrentHour();
                int minute = mTimePicker.getCurrentMinute();

                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR,hour);
                calendar.set(Calendar.MINUTE,minute);

                Date userSelectedDate = calendar.getTime();

                Fragment fragment = getTargetFragment();
                if (fragment != null && fragment instanceof Custom_DialogFragment){
                    Custom_DialogFragment custom_dialogFragment =
                            (Custom_DialogFragment) fragment;

                    custom_dialogFragment.updateCrimeDate(userSelectedDate);
                }
            }
        });
        AlertDialog dialog = builder.create();
        return dialog;
    }

     /* @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_time_picker, container, false);
        findViews(view);
        return view;
    }*/

    private void initViews() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mTaskTime);
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
    }

    private void findViews(View view) {
        mTimePicker = view.findViewById(R.id.time_picker);
    }
}