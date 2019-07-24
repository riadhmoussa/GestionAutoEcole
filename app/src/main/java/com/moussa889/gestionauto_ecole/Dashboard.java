package com.moussa889.gestionauto_ecole;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Dashboard extends AppCompatActivity {

    LinearLayout btnCandidat,btnExamen;
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
    }
}
