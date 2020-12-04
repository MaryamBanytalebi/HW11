package com.example.hw11.controller.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.hw11.R;
import com.example.hw11.controller.fragment.LoginFragment;
import com.example.hw11.controller.fragment.SearchFragment;

public class LoginActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }


    @Override
    public Fragment createFragment() {
        return LoginFragment.newInstance();
    }
}