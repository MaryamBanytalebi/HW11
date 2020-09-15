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


public class Custom_DialogFragment extends androidx.fragment.app.DialogFragment {
    private TextView mTxtTitle;
    private EditText mEdtTitle;
    private EditText mEdtDescript;
    private Button mBtnTime;
    private Button mBtnDate;
    private Button mBtnSave;
    private Button mBtnCancel;
    private CheckBox mCheckbox;

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
        mBtnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mBtnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

}