package com.chinh.covidapp;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

import com.chinh.covidapp.adapter.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button callfragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        callfragment = findViewById(R.id.btnTrack);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnTrack){
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new NewsFragment()).commit();
            callfragment.setVisibility(View.GONE);
        }
    }
}