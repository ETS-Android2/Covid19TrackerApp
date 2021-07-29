package com.chinh.covidapp.ui.main;

import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chinh.covidapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProtectionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class ProtectionFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FloatingActionButton wearmask_btn;
    private FloatingActionButton keepdistance_btn;
    private FloatingActionButton avoidcrowded_btn;
    private FloatingActionButton covercough_btn;
    private FloatingActionButton ventilation_btn;
    private FloatingActionButton clean_btn;
    private ImageView expand_bg;
    private ImageView expand_keepdistance_bg;
    private ImageView image_clean_bg;
    private ImageView image_ventilation_bg;
    private ImageView image_avoid_bg;
    private ImageView image_cover_bg;
    private TextView expand_distance_txt;
    private TextView expand_txt;
    private TextView clean_txt;
    private TextView ventilation_txt;
    private TextView covercough_txt;
    private TextView avoidcrowded_txt;
    boolean clicked = false;
    boolean clicked2 = false;
    boolean clicked3 = false;
    boolean clicked4 = false;
    boolean clicked5 = false;
    boolean clicked6 = false;
    public ProtectionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment protection.
     */
    // TODO: Rename and change types and number of parameters
    public static ProtectionFragment newInstance(String param1, String param2) {
        ProtectionFragment fragment = new ProtectionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_protection, container, false);

        expand_txt = root.findViewById(R.id.expand_txt);
        expand_bg = root.findViewById(R.id.expand_bg);
        expand_keepdistance_bg = root.findViewById(R.id.expand_keepdistance_bg);
        wearmask_btn = root.findViewById(R.id.wearmask_btn);
        keepdistance_btn = root.findViewById(R.id.keepdistance_btn);
        avoidcrowded_btn = root.findViewById(R.id.avoidcrowded_btn);
        covercough_btn = root.findViewById(R.id.covercough_btn);
        ventilation_btn = root.findViewById(R.id.ventilation_btn);
        clean_btn = root.findViewById(R.id.clean_btn);
        image_avoid_bg = root.findViewById(R.id.image_avoid_bg);
        clean_txt = root.findViewById(R.id.clean_txt);
        image_ventilation_bg = root.findViewById(R.id.image_ventilation_bg);
        image_clean_bg = root.findViewById(R.id.image_clean_bg);
        image_cover_bg = root.findViewById(R.id.image_cover_bg);
        expand_distance_txt = root.findViewById(R.id.expand_distance_txt);
        ventilation_txt = root.findViewById(R.id.ventilation_txt);
        avoidcrowded_txt = root.findViewById(R.id.avoidcrowded_txt);
        covercough_txt = root.findViewById(R.id.covercough_txt);

        expand_keepdistance_bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandButton2Clicked();
            }
        });

        expand_bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandButtonClicked();
            }
        });

        avoidcrowded_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandButton6Clicked();
            }
        });

        ventilation_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandButton4Clicked();
            }
        });

        covercough_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandButton5Clicked();
            }
        });


        clean_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandButton3Clicked();
            }
        });

        keepdistance_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandButton2Clicked();
            }
        });
        wearmask_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandButtonClicked();
            }
        });
        return root;

    }

    private void expandButton6Clicked() {
        setHeight6(clicked6);
        clicked6 = !clicked6;
    }

    private void expandButton5Clicked() {
        setHeight5(clicked5);
        clicked5 = !clicked5;

    }

    private void expandButton4Clicked() {
        setHeight4(clicked4);
        clicked4 = !clicked4;

    }


    private void expandButton3Clicked() {
        setHeight3(clicked3);
        clicked3 = !clicked3;
    }

    private void expandButtonClicked() {
        setHeight(clicked);
        clicked = !clicked;
    }

    private void expandButton2Clicked() {
        setHeight2(clicked2);
        clicked2 = !clicked2;
    }

    private void setHeight(boolean clicked) {
        if(!clicked) {
            TextView tv = (TextView) expand_txt.findViewById(R.id.expand_txt);
            ImageView img = (ImageView) expand_bg.findViewById(R.id.expand_bg);
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ProtectionFragment.this.getContext());
            //set size x2 actual in java
            tv.getLayoutParams().height = 350;
            img.getLayoutParams().height = 550;
            tv.requestLayout();
            img.requestLayout();
        }
        if(clicked){
            TextView tv = (TextView) expand_txt.findViewById(R.id.expand_txt);
            ImageView img = (ImageView) expand_bg.findViewById(R.id.expand_bg);
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ProtectionFragment.this.getContext());
            //set size x2 actual in java
            tv.getLayoutParams().height = 50;
            img.getLayoutParams().height = 250;
            tv.requestLayout();
            img.requestLayout();
        }
    }



    private void setHeight2(boolean clicked) {
        if(!clicked){
            TextView tv = (TextView) expand_distance_txt.findViewById(R.id.expand_distance_txt);
            ImageView img = (ImageView) expand_keepdistance_bg.findViewById(R.id.expand_keepdistance_bg);
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ProtectionFragment.this.getContext());
            //set size x2 actual in java
            tv.getLayoutParams().height = 350;
            img.getLayoutParams().height = 530;
            tv.requestLayout();
            img.requestLayout();
        }
        if(clicked){
            TextView tv = (TextView) expand_distance_txt.findViewById(R.id.expand_distance_txt);
            ImageView img = (ImageView) expand_keepdistance_bg.findViewById(R.id.expand_keepdistance_bg);
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ProtectionFragment.this.getContext());
            //set size x2 actual in java
            tv.getLayoutParams().height = 50;
            img.getLayoutParams().height = 250;
            tv.requestLayout();
            img.requestLayout();
        }
    }

    private void setHeight3(boolean clicked) {
        if(!clicked){
            TextView tv = (TextView) clean_txt.findViewById(R.id.clean_txt);
            ImageView img = (ImageView) image_clean_bg.findViewById(R.id.image_clean_bg);
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ProtectionFragment.this.getContext());
            //set size x2 actual in java
            tv.getLayoutParams().height = 350;
            img.getLayoutParams().height = 500;
            tv.requestLayout();
            img.requestLayout();
        }
        if(clicked){
            TextView tv = (TextView) clean_txt.findViewById(R.id.clean_txt);
            ImageView img = (ImageView) image_clean_bg.findViewById(R.id.image_clean_bg);
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ProtectionFragment.this.getContext());
            //set size x2 actual in java
            tv.getLayoutParams().height = 50;
            img.getLayoutParams().height = 250;
            tv.requestLayout();
            img.requestLayout();
        }
    }

    private void setHeight4(boolean clicked) {
        if (!clicked) {
            TextView tv = (TextView) ventilation_txt.findViewById(R.id.ventilation_txt);
            ImageView img = (ImageView) image_ventilation_bg.findViewById(R.id.image_ventilation_bg);
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ProtectionFragment.this.getContext());
            //set size x2 actual in java
            tv.getLayoutParams().height = 350;
            img.getLayoutParams().height = 450;
            tv.requestLayout();
            img.requestLayout();
        }
        if (clicked) {
            TextView tv = (TextView) ventilation_txt.findViewById(R.id.ventilation_txt);
            ImageView img = (ImageView) image_ventilation_bg.findViewById(R.id.image_ventilation_bg);
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ProtectionFragment.this.getContext());
            //set size x2 actual in java
            tv.getLayoutParams().height = 50;
            img.getLayoutParams().height = 250;
            tv.requestLayout();
            img.requestLayout();
        }
    }

    private void setHeight5(boolean clicked) {
        if (!clicked) {
            TextView tv = (TextView) covercough_txt.findViewById(R.id.covercough_txt);
            ImageView img = (ImageView) image_cover_bg.findViewById(R.id.image_cover_bg);
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ProtectionFragment.this.getContext());
            //set size x2 actual in java
            tv.getLayoutParams().height = 350;
            img.getLayoutParams().height = 550;
            tv.requestLayout();
            img.requestLayout();
        }
        if (clicked) {
            TextView tv = (TextView) covercough_txt.findViewById(R.id.covercough_txt);
            ImageView img = (ImageView) image_cover_bg.findViewById(R.id.image_cover_bg);
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ProtectionFragment.this.getContext());
            //set size x2 actual in java
            tv.getLayoutParams().height = 50;
            img.getLayoutParams().height = 250;
            tv.requestLayout();
            img.requestLayout();
        }
    }
    private void setHeight6(boolean clicked) {
        if (!clicked) {
            TextView tv = (TextView) avoidcrowded_txt.findViewById(R.id.avoidcrowded_txt);
            ImageView img = (ImageView) image_avoid_bg.findViewById(R.id.image_avoid_bg);
            FloatingActionButton btn = (FloatingActionButton) avoidcrowded_btn.findViewById(R.id.avoidcrowded_btn);
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ProtectionFragment.this.getContext());
            //set size x2 actual in java
            tv.getLayoutParams().height = 450;
            img.getLayoutParams().height = 680;
            tv.requestLayout();
            img.requestLayout();
        }
        if (clicked) {
            TextView tv = (TextView) avoidcrowded_txt.findViewById(R.id.avoidcrowded_txt);
            ImageView img = (ImageView) image_avoid_bg.findViewById(R.id.image_avoid_bg);
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ProtectionFragment.this.getContext());
            //set size x2 actual in java
            tv.getLayoutParams().height = 50;
            img.getLayoutParams().height = 250;
            tv.requestLayout();
            img.requestLayout();
        }
    }

}