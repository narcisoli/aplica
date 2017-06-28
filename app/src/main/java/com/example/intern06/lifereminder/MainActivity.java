package com.example.intern06.lifereminder;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.etsy.android.grid.StaggeredGridView;
import com.example.intern06.lifereminder.adaptori.adaptorreminder;
import com.example.intern06.lifereminder.fragmente.addreminder;
import com.example.intern06.lifereminder.fragmente.profil;
import com.example.intern06.lifereminder.obiecte.reminder;
import com.example.intern06.lifereminder.sql.ReminderDatabase;
import com.github.clans.fab.FloatingActionButton;
import com.yalantis.guillotine.animation.GuillotineAnimation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MainActivity extends AppCompatActivity implements adaptorreminder.AdapterCallback , addreminder.addreminderCallBack {


    private RelativeLayout root;
    private View hamburger;
    private List<reminder> reminderList;
    private FloatingActionButton floatingActionButton;
    private adaptorreminder adaptor;
    private StaggeredGridView listView;
    private ReminderDatabase db;
    private SearchView searchView;
    private List<reminder> aux;
    private ImageView sort;
    private boolean bool=true;
    private StaggeredGridView fixedListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        startService(new Intent(this, MyService.class));
        View profil1 = LayoutInflater.from(this).inflate(R.layout.profilmenu, null);

        root.addView(profil1);
        getSupportFragmentManager().beginTransaction().replace(R.id.containerprofil,profil.newInstance()).commit();
        new GuillotineAnimation.GuillotineBuilder(profil1, profil1.findViewById(R.id.menu2), hamburger)
                .setStartDelay(250)
                .setClosedOnStart(true)
                .build();
        listener();
    }

    private void init() {
        sort=(ImageView)findViewById(R.id.sort);
        db=new ReminderDatabase(this);
        root=(RelativeLayout)findViewById(R.id.root);
        hamburger=findViewById(R.id.hamburger);
        searchView=(SearchView)findViewById(R.id.search_view);
        db = new ReminderDatabase(this);
        reminderList = db.getReminders();
        Collections.reverse(reminderList);
        aux=new ArrayList<reminder>(reminderList);
        setFixed();
        floatingActionButton = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fabreminder);
        adaptor = new adaptorreminder(this, R.layout.adaptorreminder, reminderList, this);
        listView = (StaggeredGridView) findViewById(R.id.listareminder);
        listView.setAdapter(adaptor);

    }

    private void listener() {
        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bool) {
                    bool = false;
                    sort.setImageDrawable(getResources().getDrawable(R.drawable.gridon));
                    listView.setColumnCount(1);
                } else{
                    listView.setColumnCount(2);
                    sort.setImageDrawable(getResources().getDrawable(R.drawable.gridof));
                    bool=true;}
            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.root, addreminder.getInstace()).addToBackStack("").commit();
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {



                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                reminderList.clear();
                for (int i=0;i<aux.size();i++){
                    if(aux.get(i).getText().toLowerCase().contains(newText.toLowerCase()))
                        reminderList.add(aux.get(i));
                }
                setFixed();
                adaptor.notifyDataSetChanged();
                return false;
            }
        });
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

            }
        });

    }


    @Override
    public void sterge(reminder obj) {
        db.deleteReminder(obj);
        reminderList.remove(obj);

    }

    @Override
    public void distribuie(reminder obj) {

    }

    @Override
    public void editeaza(reminder obj) {

    }

    @Override
    public void fixeaza(reminder obj) {
            if(obj.getFixeaza()==0)
                obj.setFixeaza(1);
            else obj.setFixeaza(0);
            db.updateContact(obj);
        Toast.makeText(this, obj.getText(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void adaugat(reminder obj) {
        reminderList.add(0,obj);
        aux=new ArrayList<reminder>(reminderList);
        setFixed();
        adaptor.notifyDataSetChanged();
    }
    private void setFixed(){
        List<reminder> list=new ArrayList<reminder>(reminderList);
        for (int i=0;i<reminderList.size();i++){
            if(reminderList.get(i).getFixeaza()==1);
            {
                list.remove(reminderList.get(i));
                list.add(0,reminderList.get(i));
            }
        }
        reminderList=list;
    }

}
