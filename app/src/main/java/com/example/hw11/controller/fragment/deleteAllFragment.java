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

import com.example.hw11.R;
import com.example.hw11.model.Task;
import com.example.hw11.repository.IRepositry;
import com.example.hw11.repository.TaskDBRepository;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link deleteAllFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class deleteAllFragment extends DialogFragment {

    private IRepositry mRepository;
    private List<Task> mTasks;

    public deleteAllFragment() {
        // Required empty public constructor
    }

    public static deleteAllFragment newInstance() {
        deleteAllFragment fragment = new deleteAllFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRepository = TaskDBRepository.getInstance(getActivity());
        mTasks = mRepository.getTasks();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.fragment_delete_all, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if (mTasks.size() != 0) {
            builder.setTitle(R.string.delete_all_title);
            //builder.setIcon(R.drawable.ic_high_importance);
            builder.setView(view);
            builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mRepository.deleteAllTask();

                }
            })
                    .setNegativeButton(R.string.no, null);
        } else {
            builder.setTitle(R.string.no_tasks);
            builder.setNegativeButton(R.string.exit,null);
        }


        AlertDialog dialog = builder.create();
        return dialog;
    }
}