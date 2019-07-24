package com.moussa889.gestionauto_ecole;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Candidat extends AppCompatActivity {

    public static ArrayList<AdapterItems> listnewsData;
    public static MyCustomAdapter myadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getSupportFragmentManager();
                popajoutercandidat popajouter=new popajoutercandidat();
                popajouter.show(manager,null);
            }
        });


        LoadData();
    }
        private class MyCustomAdapter extends BaseAdapter {
            public ArrayList<AdapterItems> listnewsDataAdpater ;

            public MyCustomAdapter(ArrayList<AdapterItems>  listnewsDataAdpater) {
                this.listnewsDataAdpater=listnewsDataAdpater;
            }


            @Override
            public int getCount() {
                return listnewsDataAdpater.size();
            }

            @Override
            public String getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent)
            {
                LayoutInflater mInflater = getLayoutInflater();
                View myView = mInflater.inflate(R.layout.single_row_condidat, null);

                final   AdapterItems s = listnewsDataAdpater.get(position);

                TextView txtJobTitle=( TextView)myView.findViewById(R.id.tv_user_name);
                TextView txttv_phone=(TextView)myView.findViewById(R.id.tv_phone);
                txtJobTitle.setText(s.CIN);
                txttv_phone.setText(s.FirstName+" "+s.LastName);

                myView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(),ProfileCandidat.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("CIN",s.CIN);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });


                return myView;
            }

        }

        void LoadData(){

            listnewsData= new ArrayList<AdapterItems>();


            listnewsData.clear();
            Cursor cursor=MainActivity.dbManagerCondidat.query(null,null,null,DBManagerCondidat.ColCin);
            if(cursor.moveToFirst()){

                do{

                    listnewsData.add(new AdapterItems(Integer.valueOf(cursor.getString(cursor.getColumnIndex(DBManagerCondidat.ColID))),
                            cursor.getString(cursor.getColumnIndex(DBManagerCondidat.ColFirstName)),
                            cursor.getString(cursor.getColumnIndex(DBManagerCondidat.ColPLastName)),
                            cursor.getString(cursor.getColumnIndex(DBManagerCondidat.ColCin)),
                            cursor.getString(cursor.getColumnIndex(DBManagerCondidat.ColNumTel)),
                            cursor.getString(cursor.getColumnIndex(DBManagerCondidat.ColAdresse)),
                            cursor.getString(cursor.getColumnIndex(DBManagerCondidat.ColTypeCond))));

                }while (cursor.moveToNext());

            }

            myadapter=new MyCustomAdapter(listnewsData);
            ListView lsNews=(ListView)findViewById(R.id.LvCondidat);
            lsNews.setAdapter(myadapter);//intisal with data
        }

}
