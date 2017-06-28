package com.example.intern06.lifereminder.adaptori;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.intern06.lifereminder.R;
import com.example.intern06.lifereminder.obiecte.reminder;

import java.util.ArrayList;
import java.util.List;

public class adaptorreminder extends ArrayAdapter<reminder>  {


    private final AdapterCallback callback;



    public interface AdapterCallback {
        void sterge(reminder obj);
        void distribuie(reminder obj);
        void editeaza(reminder obj);
        void fixeaza(reminder obj);
    }
    private int layoutResource;
    private reminder ev;
    private View view;


    public adaptorreminder(Context context, int layoutResource, List<reminder> pizzalist, AdapterCallback callback) {
        super(context, layoutResource, pizzalist);
        this.layoutResource = layoutResource;
        this.callback=callback;
    }




    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

         view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(layoutResource, null);

        }

        RelativeLayout relativeLayout=(RelativeLayout)view.findViewById(R.id.relfundal);
        TextView textnume=(TextView)view.findViewById(R.id.reminderdata);
        TextView descrioere=(TextView)view.findViewById(R.id.remindertext);
        final ImageView menu1=(ImageView)view.findViewById(R.id.more);
        ImageView fixed=(ImageView)view.findViewById(R.id.fixed) ;




        ev=getItem(position);
        String data=ev.getData();
        String aux[]=data.split(" ");
        data=aux[0]+"."+aux[1]+"."+aux[2]+" "+aux[3]+":"+aux[4];

        if (ev != null) {
            if(ev.getFixeaza()==1)
                fixed.setVisibility(View.VISIBLE);
            else
                fixed.setVisibility(View.GONE);
            textnume.setText(data);
            descrioere.setText(ev.getText());
            relativeLayout.setBackgroundColor(ev.getCuloarefundal());
            textnume.setTextColor(ev.getCuloaretext());
            descrioere.setTextColor(ev.getCuloaretext());
            menu1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu menu = new PopupMenu (view.getContext(), menu1);
                    menu.setOnMenuItemClickListener (new PopupMenu.OnMenuItemClickListener ()
                    {
                        @Override
                        public boolean onMenuItemClick (MenuItem item)
                        {
                            int id = item.getItemId();
                            switch (id)
                            {
                                case R.id.item_delete:callback.sterge(ev);break;
                                case R.id.item_share: callback.distribuie(ev);break;
                                case R.id.item_edit: callback.editeaza(ev);break;
                                case R.id.item_fixeaza: callback.fixeaza(ev);break;
                            }
                            return true;
                        }
                    });
                    menu.inflate (R.menu.menu);
                    menu.show();
                }
            });
        }

        return view;
    }



}

