package com.moussa889.gestionauto_ecole;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListeExamenCode extends AppCompatActivity {
    public static ArrayList<AdapterItemsListeExamen> listnewsData;
    public static ListeExamenCode.MyCustomAdapter myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_examen_code);

        LoadData();

    }

    void LoadData(){

        listnewsData= new ArrayList<AdapterItemsListeExamen>();

        String[] SelectionArgs={"Code"};

        listnewsData.clear();
        Cursor cursor=MainActivity.dbManagerCondidat.queryExamen(null,"TypeExamen=?",SelectionArgs,null);

        if(cursor.moveToFirst()){
            do{
                listnewsData.add(new AdapterItemsListeExamen(String.valueOf(cursor.getInt(cursor.getColumnIndex(DBManagerCondidat.ColIDExamen))),
                        String.valueOf(cursor.getInt(cursor.getColumnIndex(DBManagerCondidat.ColIdCandidat))),
                        cursor.getString(cursor.getColumnIndex(DBManagerCondidat.ColDateExamen)),
                        cursor.getString(cursor.getColumnIndex(DBManagerCondidat.ColResultarExamen))));
            }while (cursor.moveToNext());
        }

        myadapter=new MyCustomAdapter(listnewsData);
        ListView lsNews=(ListView)findViewById(R.id.list_examen_code);
        lsNews.setAdapter(myadapter);//intisal with data
    }


    private class MyCustomAdapter extends BaseAdapter {
        public ArrayList<AdapterItemsListeExamen> listnewsDataAdpater ;

        public MyCustomAdapter(ArrayList<AdapterItemsListeExamen>  listnewsDataAdpater) {
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
            View myView = mInflater.inflate(R.layout.single_row_condidat_examen_code, null);

            final   AdapterItemsListeExamen s = listnewsDataAdpater.get(position);

            TextView txtJobTitle=( TextView)myView.findViewById(R.id.tv_user_name);
            TextView txttv_phone=(TextView)myView.findViewById(R.id.tv_phone);
            txtJobTitle.setText(s.DateExamen);
            txttv_phone.setText(s.ID+" "+s.ResultatExamen);

            myView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(),ProfileCandidat.class);
                    startActivity(intent);
                }
            });


            return myView;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_candidat_activity, menu);

        SearchView sv=(SearchView) menu.findItem(R.id.searchbar).getActionView();
        SearchManager sm=(SearchManager) getSystemService(Context.SEARCH_SERVICE);
        sv.setSearchableInfo(sm.getSearchableInfo(getComponentName()));
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(getApplicationContext(),query,Toast.LENGTH_LONG).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.searchbar:
                Toast.makeText(this,"Home",Toast.LENGTH_LONG).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
