package com.moussa889.gestionauto_ecole;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class popajouterexamencandidat extends DialogFragment implements View.OnClickListener {
    View form;
    Button btnAddExamen,btnAnnuler;
    EditText edittext,edDateExamen;
    final Calendar myCalendar= Calendar.getInstance();
     DatePickerDialog.OnDateSetListener date;
    String TypeCand;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        form= inflater.inflate(R.layout.ajouterexamencandidat,container,false);
        btnAddExamen=(Button)form.findViewById(R.id.btnAddExamen);
        btnAnnuler=(Button)form.findViewById(R.id.btnAnnuler);
        edDateExamen=(EditText) form.findViewById(R.id.edDateExamen);
        RadioGroup mRadioGroup=(RadioGroup)form.findViewById(R.id.mRadioGroup);



        edittext= (EditText) form.findViewById(R.id.edDateExamen);
        date= new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

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
        edittext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(form.getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        btnAddExamen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values= new ContentValues();
                values.put(MainActivity.dbManagerCondidat.ColIdCandidat,ProfileCandidat.idCandidat);
                values.put(MainActivity.dbManagerCondidat.ColDateExamen,edDateExamen.getText().toString());
                values.put(MainActivity.dbManagerCondidat.ColTypeExamen,TypeCand);
                values.put(MainActivity.dbManagerCondidat.ColResultarExamen,"Pas eencore");
                long id= MainActivity.dbManagerCondidat.InsertExamen(values);
                if (id>0){
                    Toast.makeText(form.getContext(),"Data is added and id:"+id,Toast.LENGTH_LONG).show();

                    edDateExamen.setText("");
                    TypeCand="";

                    dismiss();


                } else
                    Toast.makeText(form.getContext(),"cannot insert",Toast.LENGTH_LONG).show();

                dismiss();
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

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        edittext.setText(sdf.format(myCalendar.getTime()));
    }
}
