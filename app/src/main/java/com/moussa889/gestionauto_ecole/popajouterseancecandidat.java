package com.moussa889.gestionauto_ecole;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class popajouterseancecandidat  extends DialogFragment implements View.OnClickListener {
    View form;
    EditText edDateSeance,edHeureSeance;
    Button btnAnnuler;
    final Calendar myCalendar= Calendar.getInstance();
    final Calendar myCalendarHuere= Calendar.getInstance();

    DatePickerDialog.OnDateSetListener date;
    TimePickerDialog.OnTimeSetListener heure;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        form= inflater.inflate(R.layout.ajouterseancecandidat,container,false);
        edDateSeance=(EditText)form.findViewById(R.id.edDateSeance);
        edHeureSeance=(EditText)form.findViewById(R.id.edHeureSeance);
        btnAnnuler=(Button)form.findViewById(R.id.btnAnnuler);

        btnAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
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

        heure=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                myCalendarHuere.set(Calendar.HOUR_OF_DAY,hourOfDay);
                myCalendarHuere.set(Calendar.MINUTE,minute);
                updateLabelHeure();

            }
        };

        edHeureSeance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(form.getContext(),
                        heure,
                        myCalendarHuere.get(Calendar.HOUR_OF_DAY),
                        myCalendarHuere.get(Calendar.MINUTE), true).show(); }
        });

        edDateSeance.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(form.getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
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

        edDateSeance.setText(sdf.format(myCalendar.getTime()));
    }
    private  void updateLabelHeure(){
        String myFormat = "HH:mm"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        edHeureSeance.setText(sdf.format(myCalendarHuere.getTime()));

    }
}
