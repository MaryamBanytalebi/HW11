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
import com.example.hw11.controller.fragment.doingFragment;
import com.example.hw11.controller.fragment.doneFragment;
import com.example.hw11.controller.fragment.todoFragment;
import com.example.hw11.model.Task;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class TaskPagerActivity extends AppCompatActivity {
    private ViewPager2 mViewPager;
    private TabLayout mTabLayout;
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
        /*tasks = new ArrayList<>();
        TaskPagerAdapter adapter = new TaskPagerAdapter(this,tasks);*/
        mViewPager.setAdapter(new TaskPagerAdapter(this));
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(
                mTabLayout, mViewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:{
                        tab.setText("TODO");
                        break;
                    }
                    case 1:{
                        tab.setText("DOING");
                        break;
                    }
                    case 2:{
                        tab.setText("DONE");
                        break;
                    }
                }
            }
        });
        tabLayoutMediator.attach();
        /*mViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                BadgeDrawable badgeDrawable = mTabLayout.getTabAt(position).getOrCreateBadge();
                badgeDrawable.setVisible(false);
            }
        });*/
    }
    private class TaskPagerAdapter extends FragmentStateAdapter {
        private final int NUM_PAGE = 3;

        public TaskPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            /*TaskDetailFragment taskDetailFragment = new TaskDetailFragment();
            return taskDetailFragment;*/
            switch(position){
                case 0:
                    return new todoFragment();
                case 1:
                    return new doingFragment();
                default:
                    return new doneFragment();
            }
        }

        @Override
        public int getItemCount() {
            return NUM_PAGE;
        }
    }

}