package com.moussa889.gestionauto_ecole;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ChoisirTypeExamenAfficher extends DialogFragment implements View.OnClickListener {
    View form;
    Button btnListECODE,btnListEConduite;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        form= inflater.inflate(R.layout.choisirtypeexamenafficher,container,false);
        btnListECODE=(Button)form.findViewById(R.id.btnListECODE);
        btnListEConduite=(Button)form.findViewById(R.id.btnListEConduite);
        btnListECODE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(form.getContext(),ListeExamenCode.class);
                startActivity(intent);


            }
        });

        btnListEConduite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(form.getContext(),ListExamenCoduite.class);
                startActivity(intent);
            }
        });
        return form;
    }

    @Override
    public void onClick(View v) {

    }
}
