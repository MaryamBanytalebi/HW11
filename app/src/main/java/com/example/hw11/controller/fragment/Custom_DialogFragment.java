package com.example.hw11.controller.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.hw11.R;
import com.example.hw11.model.Task;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class Custom_DialogFragment extends androidx.fragment.app.DialogFragment {
    public static final int REQUEST_CODE_DATE_PICKER = 0;
    public static final int REQUEST_CODE_TIME_PICKER = 1;
    public static final String FRAGMENT_TAG_DATE_PICKER = "date picker";
    public static final String FRAGMENT_TAG_TIME_PICKER = "time picker";
    private TextView mTxtTitle;
    private EditText mEdtTitle;
    private EditText mEdtDescript;
    private Button mBtnTime;
    private Button mBtnDate;
    private Button mBtnSave;
    private Button mBtnCancel;
    private CheckBox mCheckbox;
    private Task mTask;

    public Custom_DialogFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Custom_DialogFragment newInstance() {
        Custom_DialogFragment fragment = new Custom_DialogFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder((getActivity()));
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_custom_dialog,null);
        builder.setView(view);
        findViews(view);
        setListeners();
        AlertDialog dialog = builder.create();
        return dialog;
    }

    private void findViews(View view) {
        mTxtTitle = view.findViewById(R.id.txt_title);
        mEdtTitle = view.findViewById(R.id.edt_title);
        mEdtDescript = view.findViewById(R.id.edt_descript);
        mBtnDate = view.findViewById(R.id.btn_date);
        mBtnTime = view.findViewById(R.id.btn_time);
        mBtnSave = view.findViewById(R.id.btn_save);
        mBtnCancel = view.findViewById(R.id.btn_cancel);
        mCheckbox = view.findViewById(R.id.check_box);
    }

    private void setListeners() {
        mBtnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerFragment datePickerFragment = DatePickerFragment.newInstance(mTask.getDate());
                datePickerFragment.setTargetFragment(Custom_DialogFragment.this,
                        REQUEST_CODE_DATE_PICKER);

                datePickerFragment.show(
                        getActivity().getSupportFragmentManager(),
                        FRAGMENT_TAG_DATE_PICKER);

            }
        });

        mBtnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerFragment timePickerFragment = TimePickerFragment.newInstance(mTask.getDate());
                timePickerFragment.setTargetFragment(Custom_DialogFragment.this,
                        REQUEST_CODE_TIME_PICKER);

                timePickerFragment.show(
                        getActivity().getSupportFragmentManager(),
                        FRAGMENT_TAG_TIME_PICKER);

            }
        });
        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    void updateCrimeDate(Date userSelectedDate) {
        mTask.setDate(userSelectedDate);
        //updateCrime();
        mBtnDate.setText(mTask.getDate().toString());
    }

}