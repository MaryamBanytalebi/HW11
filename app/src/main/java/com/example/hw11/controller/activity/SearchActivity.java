package com.example.hw11.controller.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.hw11.R;
import com.example.hw11.controller.fragment.SearchFragment;

public class SearchActivity extends SingleFragmentActivity {

    public static final String EXTRA_SEARCH_VALUE = "EXTRA_SEARCH_VALUE";
    public static final String EXTRA_USER_ID = "Extra_User_Id";
    private static long mUserId;

    public static Intent newIntent(Context context, String search, long userId) {
        mUserId = userId;
        Intent intent = new Intent(context, SearchActivity.class);
        intent.putExtra(EXTRA_SEARCH_VALUE,search);
        intent.putExtra(EXTRA_USER_ID,userId);
        return intent;
    }

    @Override
    public Fragment createFragment() {
        return SearchFragment.newInstance(mUserId);
    }
}