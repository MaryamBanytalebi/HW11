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
import com.example.hw11.controller.fragment.SignUpFragment;

public class SignUpActivity extends SingleFragmentActivity {

    public static final String EXTRA_USERNAME = "org.maktab.homework11_maktab37.controller.activity.extra_username";
    public static final String EXTRA_PASSWORD = "org.maktab.homework11_maktab37.controller.activity.extra_password";
    private static String mGetExtraUsername,mGetExtraPassword;

    public static Intent newIntent(Context context, String username, String password) {
        mGetExtraUsername = username;
        mGetExtraPassword = password;
        Intent intent = new Intent(context, SignUpActivity.class);
        intent.putExtra(EXTRA_USERNAME,username);
        intent.putExtra(EXTRA_PASSWORD,password);
        return intent;
    }

    @Override
    public Fragment createFragment() {
        SignUpFragment signUpFragment = SignUpFragment.newInstance(mGetExtraUsername,mGetExtraPassword);
        return signUpFragment;
    }
}