package com.example.hw11.controller.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hw11.R;
import com.example.hw11.model.Task;

public class TaskDetailFragment extends Fragment {
    private TextView mTaskName;
    private ImageView mTaskImage;
    private Task mTask;


    // TODO: Rename and change types of parameters

    public TaskDetailFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    /* public static TaskDetailFragment newInstance(String param1, String param2) {
        TaskDetailFragment fragment = new TaskDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
*/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task_detail, container, false);
        findViews(view);
        mTaskName.setText(mTask.getName());
        //mTaskImage.setImageDrawable(mTask.setPhoto(res.drawable.));
        return  view;
    }

    private void findViews(View view) {
        mTaskName = view.findViewById(R.id.task_name);
        mTaskImage = view.findViewById(R.id.task_image);
    }
}