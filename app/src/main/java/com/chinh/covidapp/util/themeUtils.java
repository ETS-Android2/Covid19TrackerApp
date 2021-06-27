package com.chinh.covidapp.util;

import android.app.Activity;

import android.content.Intent;

import androidx.fragment.app.FragmentActivity;

import com.chinh.covidapp.R;


public class themeUtils {
    private static int currentTheme;
    public final static int LIGHT = 1;
    public final static int DARK = 0;
    public static void changeToTheme(Activity activity, int theme)
    {
        currentTheme = theme;
        activity.finish();
        activity.startActivity(new Intent(activity, activity.getClass()));
    }
    public static void onActivityCreateSetTheme(Activity activity) {
        switch (currentTheme){
            default:
            case DARK:
                activity.setTheme(R.style.AppTheme);
                break;
            case LIGHT:
                activity.setTheme(R.style.LightTheme);
                break;
        }
    }
    public static void onFragmentCreateSetTheme(FragmentActivity activity) {
        switch (currentTheme){
            default:
            case DARK:
                activity.setTheme(R.style.AppTheme);
                break;
            case LIGHT:
                activity.setTheme(R.style.LightTheme);
                break;
        }
    }
}