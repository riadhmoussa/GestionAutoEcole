package com.moussa889.gestionauto_ecole;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class popajoutercandidat extends DialogFragment implements View.OnClickListener {

    View form;
    String TypeCand;
    EditText edNom;
    EditText edPrenom;
    EditText edAdresse;
    EditText edCin;
    EditText edTel;
    Button btnAnnuler;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        form= inflater.inflate(R.layout.ajoutercandidat,container,false);
        Button btnSuppr=(Button)form.findViewById(R.id.btnAnnuler);
        Button btnAC=(Button)form.findViewById(R.id.btnAC);
        RadioGroup mRadioGroup=(RadioGroup)form.findViewById(R.id.mRadioGroup);
        btnAnnuler=(Button)form.findViewById(R.id.btnAnnuler);

        btnAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.radio_pirates) {
                    TypeCand="Code";

                } else if(checkedId == R.id.radio_ninjas) {
                    TypeCand="Conduite";

                }
            }
        });


         edNom=(EditText)form.findViewById(R.id.edNom);
         edPrenom=(EditText)form.findViewById(R.id.edPrenom);
         edAdresse=(EditText)form.findViewById(R.id.edAdresse);
         edCin=(EditText)form.findViewById(R.id.edCin);
         edTel=(EditText)form.findViewById(R.id.edTel);






        btnAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ContentValues values= new ContentValues();
                values.put(MainActivity.dbManagerCondidat.ColFirstName,edNom.getText().toString());
                values.put(MainActivity.dbManagerCondidat.ColPLastName,edPrenom.getText().toString());
                values.put(MainActivity.dbManagerCondidat.ColCin,edCin.getText().toString());
                values.put(MainActivity.dbManagerCondidat.ColNumTel,edTel.getText().toString());
                values.put(MainActivity.dbManagerCondidat.ColAdresse,edAdresse.getText().toString());
                values.put(MainActivity.dbManagerCondidat.ColTypeCond,TypeCand);
                long id= MainActivity.dbManagerCondidat.Insert(values);
                if (id>0){
                    Toast.makeText(form.getContext(),"Data is added and id:"+id,Toast.LENGTH_LONG).show();

                    edNom.setText("");
                    edPrenom.setText("");
                    edAdresse.setText("");
                    edCin.setText("");
                    edTel.setText("");
                    TypeCand="";

                    dismiss();


                } else
                    Toast.makeText(form.getContext(),"cannot insert",Toast.LENGTH_LONG).show();

            }
        });
        btnAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return form;
    }

    @Override
    public void onClick(View v) {

    }


}
