package com.example.intern06.lifereminder.fragmente.FragmenteProfil;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.intern06.lifereminder.R;

public class Setari extends Fragment {

    private View view;


    public static Setari newInstance() {
        Setari fragment = new Setari();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_setari, container, false);
        return view;
    }
}
