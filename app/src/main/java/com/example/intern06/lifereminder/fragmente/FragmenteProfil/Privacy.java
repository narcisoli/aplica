package com.example.intern06.lifereminder.fragmente.FragmenteProfil;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.intern06.lifereminder.R;


public class Privacy extends Fragment {


    private View view;


    public static Privacy newInstance() {
        Privacy fragment = new Privacy();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_privacy, container, false);
        return view;
    }

}
