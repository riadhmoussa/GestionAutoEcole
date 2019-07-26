package com.moussa889.gestionauto_ecole;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Dashboard extends AppCompatActivity {

    LinearLayout btnCandidat,btnExamen,btnNotification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        SaveSettings saveSettings= new SaveSettings(getApplicationContext());
        saveSettings.LoadData();
        btnCandidat=(LinearLayout)findViewById(R.id.btnCandidat);
        btnExamen=(LinearLayout)findViewById(R.id.btnExamen);
        btnCandidat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent Dashboard = new Intent(Dashboard.this, Candidat.class);
                startActivity(Dashboard);
            }
        });

        btnExamen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getSupportFragmentManager();
                ChoisirTypeExamenAfficher choisirTypeExamenAfficher=new ChoisirTypeExamenAfficher();
                choisirTypeExamenAfficher.show(manager,null);
            }
        });



        btnNotification=(LinearLayout)findViewById(R.id.btnNotification);
        btnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                String NOTIFICATION_CHANNEL_ID = "my_channel_id_01";

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_MAX);

                    // Configure the notification channel.
                    notificationChannel.setDescription("Channel description");
                    notificationChannel.enableLights(true);
                    notificationChannel.setLightColor(Color.RED);
                    notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
                    notificationChannel.enableVibration(true);
                    notificationManager.createNotificationChannel(notificationChannel);
                }

                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(Dashboard.this, NOTIFICATION_CHANNEL_ID);

                notificationBuilder.setAutoCancel(true)
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.drawable.icon_notification)
                        .setTicker("Hearty365")
                        //     .setPriority(Notification.PRIORITY_MAX)
                        .setContentTitle("Default notification")
                        .setContentText("Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
                        .setContentInfo("Info");

                notificationManager.notify(/*notification id*/1, notificationBuilder.build());

            }
        });
    }
}
