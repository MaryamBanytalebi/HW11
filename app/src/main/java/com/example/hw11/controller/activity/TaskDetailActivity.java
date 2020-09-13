package com.example.hw11.controller.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.hw11.R;
import com.example.hw11.controller.fragment.TaskDetailFragment;

public class TaskDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);

        if (fragment == null){
            TaskDetailFragment taskDetailFragment = new TaskDetailFragment();
            fragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container,taskDetailFragment)
                    .commit();

        }

    }
}