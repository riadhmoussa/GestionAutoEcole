package com.moussa889.gestionauto_ecole;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class SaveSettings {

    public  static String UserID="";

    Context context;
    SharedPreferences ShredRef;
    public  SaveSettings(Context context){
        this.context=context;
        ShredRef=context.getSharedPreferences("myRef",Context.MODE_PRIVATE);
    }

    void SaveData(String UserID){

        SharedPreferences.Editor editor=ShredRef.edit();
        editor.putString("UserID",UserID);

        editor.commit();
        LoadData();
    }

    void LoadData(){
        UserID= ShredRef.getString("UserID","0");
        if (UserID.equals("0")){

            Intent intent=new Intent(context, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }

    }
    String ReturnId(){
        UserID= ShredRef.getString("UserID","0");
        return UserID;
    }

    void Deconnection(){
        SharedPreferences.Editor editor=ShredRef.edit();
        editor.putString("UserID","0");

        editor.commit();
        LoadData();
    }

}
