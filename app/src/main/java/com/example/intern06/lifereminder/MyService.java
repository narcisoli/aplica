package com.example.intern06.lifereminder;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.intern06.lifereminder.obiecte.reminder;
import com.example.intern06.lifereminder.sql.ReminderDatabase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyService extends Service {

    private static AlertDialog.Builder alert;
    private boolean bool = false;
    private List<reminder> lista = new ArrayList<>();
    private static NotificationManager mNotificationManager;

    public MyService() {
        Log.i("Notif", "contructor");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i("Notif", "ibind error");
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        lista.clear();
        lista = new ReminderDatabase(this).getReminders();
        Collections.reverse(lista);

        if (lista.size() != 0) {
            notif(lista.get(0).getText(), "");
        }


    }



    private void notif(String text, String text2) {


        RemoteViews remoteViews = new RemoteViews(getPackageName(),
                R.layout.widget);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                this).setSmallIcon(R.drawable.fab_add).setContent(
                remoteViews);
        mBuilder.setOngoing(true);

        remoteViews.setTextViewText(R.id.textView1, text);
        remoteViews.setTextViewText(R.id.textView2, text2);
        Intent intentX = new Intent(this, ButtonReceiver.class);
        intentX.putExtra("id", 1);
        Intent intentMAIN = new Intent(this, MainActivity.class);

        PendingIntent btPendingIntent = PendingIntent.getBroadcast(this, 0, intentX, 0);


        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(intentMAIN);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        remoteViews.setOnClickPendingIntent(R.id.buttoncancel, btPendingIntent);
        remoteViews.setOnClickPendingIntent(R.id.linear1, resultPendingIntent);


        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(100, mBuilder.build());

    }

    public static class ButtonReceiver extends BroadcastReceiver {


        @Override
        public void onReceive(Context context, Intent intent) {
            mNotificationManager.cancel(100);


        }
    }
}
