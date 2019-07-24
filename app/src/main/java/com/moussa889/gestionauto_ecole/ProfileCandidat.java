package com.moussa889.gestionauto_ecole;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileCandidat extends AppCompatActivity {

    TextView tv_numtel;
    Button btnaddexamen,btnaddseance;
    public static Integer idCandidat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_candidat);
        SaveSettings saveSettings= new SaveSettings(getApplicationContext());
        saveSettings.LoadData();

        TextView tv_name=(TextView)findViewById(R.id.tv_name);
        TextView tv_address=(TextView)findViewById(R.id.tv_address);
        TextView tv_CIN=(TextView)findViewById(R.id.tv_CIN);
        tv_numtel=(TextView)findViewById(R.id.tv_numtel);
        btnaddexamen=(Button)findViewById(R.id.btnaddexamen);
        btnaddseance=(Button)findViewById(R.id.btnaddseance);
        TextView tv_typecandidat=(TextView)findViewById(R.id.tv_typecandidat);

        ImageView iv_phoneCall=(ImageView)findViewById(R.id.iv_phoneCall);

        Bundle b = getIntent().getExtras();
        String CIN = b.getString("CIN");
        Toast.makeText(this,CIN,Toast.LENGTH_LONG).show();
        String[] SelectionArgs={CIN};
        Cursor cursor=MainActivity.dbManagerCondidat.query(null,"CIN=?",SelectionArgs,null);
        if(cursor.moveToFirst()){

            tv_name.setText(cursor.getString(cursor.getColumnIndex(DBManagerCondidat.ColFirstName))+" "+cursor.getString(cursor.getColumnIndex(DBManagerCondidat.ColPLastName)));
            tv_address.setText(cursor.getString(cursor.getColumnIndex(DBManagerCondidat.ColAdresse)));
            tv_CIN.setText(cursor.getString(cursor.getColumnIndex(DBManagerCondidat.ColCin)));
            tv_numtel.setText(cursor.getString(cursor.getColumnIndex(DBManagerCondidat.ColNumTel)));
            tv_typecandidat.setText(cursor.getString(cursor.getColumnIndex(DBManagerCondidat.ColTypeCond)));
            idCandidat=cursor.getInt(cursor.getColumnIndex(DBManagerCondidat.ColID));
        }

        iv_phoneCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+tv_numtel.getText().toString()));
                startActivity(intent);

            }
        });

        btnaddexamen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getSupportFragmentManager();
                popajouterexamencandidat popajouterexamen=new popajouterexamencandidat();
                popajouterexamen.show(manager,null);
            }
        });
        btnaddseance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getSupportFragmentManager();
                popajouterseancecandidat popajouterseance=new popajouterseancecandidat();
                popajouterseance.show(manager,null);
            }
        });


    }
}
