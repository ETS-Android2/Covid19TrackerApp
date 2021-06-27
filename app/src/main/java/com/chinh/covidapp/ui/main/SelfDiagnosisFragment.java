package com.chinh.covidapp.ui.main;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinh.covidapp.R;


public class SelfDiagnosisFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match


    public SelfDiagnosisFragment() {
    }

    // TODO: Rename and change types and number of parameters
    public static SelfDiagnosisFragment newInstance(String param1, String param2) {
        SelfDiagnosisFragment fragment = new SelfDiagnosisFragment();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_self__diagnosis_, container, false);

        return v;

    }
}