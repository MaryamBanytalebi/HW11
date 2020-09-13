package com.example.hw11.controller.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.hw11.R;
import com.example.hw11.controller.fragment.TaskDetailFragment;
import com.example.hw11.model.Task;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class TaskPagerActivity extends AppCompatActivity {
    private ViewPager2 mViewPager;
    private TabLayout mTabLayout;
    private List<Task> tasks;
    public static Intent newIntent(Context context){
        return new Intent(context,TaskPagerActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskpager);

        findViews();
        initViews();
    }

    private void findViews() {
        mViewPager = findViewById(R.id.view_pager_tasks);
        mTabLayout =  findViewById(R.id.tabLayout);
    }

    private void initViews() {
        tasks = new ArrayList<>();
        TaskPagerAdapter adapter = new TaskPagerAdapter(this,tasks);
        mViewPager.setAdapter(adapter);

    }
    private class TaskPagerAdapter extends FragmentStateAdapter {
        private List<Task> mTasks;
        private final int NUM_PAGE = 3;

        public List<Task> getTasks() {
            return mTasks;
        }

        public void setTasks(List<Task> tasks) {
            mTasks = tasks;
        }

        public TaskPagerAdapter(@NonNull FragmentActivity fragmentActivity,List<Task> tasks) {
            super(fragmentActivity);
            mTasks = tasks;
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            TaskDetailFragment taskDetailFragment = new TaskDetailFragment();
            return taskDetailFragment;
        }

        @Override
        public int getItemCount() {
            return NUM_PAGE;
        }
    }

}