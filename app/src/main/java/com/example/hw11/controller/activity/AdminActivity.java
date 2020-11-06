package com.example.hw11.controller.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.hw11.controller.fragment.AdminFragment;

public class AdminActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, AdminActivity.class);
        return intent;
    }

    @Override
    public Fragment createFragment() {
        return AdminFragment.newInstance();
    }
}