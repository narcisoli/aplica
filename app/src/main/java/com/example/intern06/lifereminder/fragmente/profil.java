package com.example.intern06.lifereminder.fragmente;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.intern06.lifereminder.Login;

import com.example.intern06.lifereminder.R;
import com.example.intern06.lifereminder.fragmente.FragmenteProfil.Despre;
import com.example.intern06.lifereminder.fragmente.FragmenteProfil.Notificari;
import com.example.intern06.lifereminder.fragmente.FragmenteProfil.Privacy;
import com.example.intern06.lifereminder.fragmente.FragmenteProfil.Setari;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;



public class profil extends Fragment  {


    private RelativeLayout logout;
    private View view;
    private ImageView pozaDeProfil;
    private TextView numePrenume;
    private TextView email;
    private RelativeLayout setari;
    private RelativeLayout privacy;
    private RelativeLayout notificari;
    private RelativeLayout distribuie;
    private RelativeLayout despre;


    public static profil newInstance() {
        profil fragmentFirst = new profil();
        return fragmentFirst;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.profil, container, false);
        logout= (RelativeLayout) view.findViewById(R.id.logoutt);
        numePrenume= (TextView) view.findViewById(R.id.numeprenume);
        email= (TextView) view.findViewById(R.id.email);
        setari = (RelativeLayout)view.findViewById(R.id.setari);
        privacy = (RelativeLayout)view.findViewById(R.id.privacy);
        notificari = (RelativeLayout)view.findViewById(R.id.notificari);
        distribuie = (RelativeLayout)view.findViewById(R.id.share);
        despre = (RelativeLayout)view.findViewById(R.id.about);

        pozaDeProfil = (ImageView)view.findViewById(R.id.pozadeprofil);
        Picasso.with(view.getContext())
                .load(FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl())
                .into(pozaDeProfil);

        numePrenume.setText(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
        email.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent a=new Intent(view.getContext(),Login.class);
                startActivity(a);
                getActivity().finish();
            }
        });

        setari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, Setari.newInstance()).addToBackStack("").commit();
            }
        });

        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, Privacy.newInstance()).addToBackStack("").commit();
            }
        });

        notificari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, Notificari.newInstance()).addToBackStack("").commit();
            }
        });

        distribuie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "AndroidSolved");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Aplicatia lui Narcis Clau si Ovi ");
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });

        despre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.root, Despre.newInstance()).addToBackStack("").commit();
            }
        });
        return view;
    }
}


