package com.chinh.covidapp;


import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.chinh.covidapp.ui.main.SelfDiagnosisFragment;
import com.chinh.covidapp.ui.main.ProtectionFragment;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class HomeActivity extends AppCompatActivity{
    private final int ID_TRACK = 1;
    private final int ID_NEWS = 2;
    private final int ID_MAPS = 3;
    private final int ID_ABOUT = 4;
    private final int ID_HOME = 5;
    private Switch switch_btn;
    private MeowBottomNavigation bottomNavigation;
    private HomeActivity home = this; //create home parameter
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        TextView selected_page = findViewById(R.id.selected_page);
        bottomNavigation = findViewById(R.id.bottomNavigation);



//        findViewById(R.id.homeBtn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//                startActivity(new Intent(home, ChartActivity.class));
//            }
//        });
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frame, new ProtectionFragment())
                .commit();
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_TRACK, R.drawable.power));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_NEWS, R.drawable.newspaper));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_HOME, R.drawable.home));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_MAPS, R.drawable.maps));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_ABOUT, R.drawable.information));
        bottomNavigation.show(ID_HOME,true);
        bottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                switch (model.getId()){
                    case 1:
                        startActivity(new Intent(home, ChartActivity.class));
                        break;
                    case 2:
                        replace(new NewsFragment());
                        break;
                    case 3:
                        replace(new MapFragment());
                        break;
                    case 4:
                        replace(new SelfDiagnosisFragment());
                        break;
                    case 5 :
                        replace(new ProtectionFragment());
                        break;
                }
                return null;
            }
        });




    }
    private void replace(Fragment fragment ){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, fragment);
        transaction.commit();
    }
}