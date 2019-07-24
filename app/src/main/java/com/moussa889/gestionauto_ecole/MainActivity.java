package com.moussa889.gestionauto_ecole;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView btnGo;
    public static DBManagerCondidat dbManagerCondidat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnGo=(ImageView)findViewById(R.id.btnGo);
        dbManagerCondidat=new DBManagerCondidat(this);


        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveSettings saveSettings= new SaveSettings(getApplicationContext());
                saveSettings.SaveData("1");
                Intent Dashboard = new Intent(MainActivity.this, Dashboard.class);
                startActivity(Dashboard);

            }
        });
    }
}
