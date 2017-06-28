package com.example.intern06.lifereminder.fragmente.FragmenteProfil;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import com.example.intern06.lifereminder.R;


public class Notificari extends Fragment {

    private View view;
    private Switch switch1;


    public static Notificari newInstance() {
        Notificari fragment = new Notificari();

        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_notificari, container, false);
        switch1 = (Switch)view.findViewById(R.id.switch1);
        switch1.setTextOn("On");
        switch1.setTextOff("Off");

        return view;
    }


}
