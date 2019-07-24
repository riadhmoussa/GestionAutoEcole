package com.moussa889.gestionauto_ecole;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.widget.Toast;

public class DBManagerCondidat {


    private SQLiteDatabase sqlDB;
    static final String DBName="AutoEcole";

    static final String TableName="Condidat";
    static final String ColFirstName="FirstName";
    static final String ColPLastName="LastName";
    static final String ColCin="CIN";
    static final String ColNumTel="NumTel";
    static final String ColAdresse="Adresse";
    static final String ColTypeCond="TypeCondidat";

    static final String ColID="ID";
    static final  int DBVersion=3;


    static final String ColIDExamen="IDExamen";
    static final String TableNameExamen="Examen";
    static final String ColIdCandidat="IDCandidat";
    static final String ColDateExamen="DateExamen";
    static final String ColTypeExamen="TypeExamen";
    static final String ColResultarExamen="ResultatExamen";


    static final String CreateTableExamen="CREATE TABLE IF NOT EXISTS "+TableNameExamen+"(IDExamen INTEGER PRIMARY KEY AUTOINCREMENT,"
            +ColIdCandidat +" INTEGER ,"+ColDateExamen+" TEXT ,"+ColTypeExamen+" TEXT,"+ColResultarExamen+" TEXT);";



    static final  String CreateTable=" CREATE TABLE IF NOT EXISTS " +TableName+
            "(ID INTEGER PRIMARY KEY AUTOINCREMENT,"+ ColFirstName+
            " TEXT,"+ ColPLastName + " TEXT,"+ ColCin + " TEXT,"+ ColNumTel + " TEXT,"+ ColAdresse + " TEXT,"+ ColTypeCond + " TEXT);";


    private static class  DatabaseHelperUser extends SQLiteOpenHelper {
        Context context;
        DatabaseHelperUser(Context context){
            super(context,DBName,null,DBVersion);
            this.context=context;

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CreateTable);
            db.execSQL(CreateTableExamen);
            Toast.makeText(context,"Table is created",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("Drop table IF  EXISTS "+ TableName);
            db.execSQL("Drop table IF EXISTS "+TableNameExamen);
            onCreate(db);
        }
    }


    public DBManagerCondidat(Context context){

        DatabaseHelperUser db=new DatabaseHelperUser(context) ;
        sqlDB=db.getWritableDatabase();

    }

    public long InsertExamen(ContentValues values){
        long ID=   sqlDB.insert(TableNameExamen,"",values);
        //could insert id is user id, or fail id is or equal 0
        return ID;
    }

    public  long Insert(ContentValues values){
        long ID=   sqlDB.insert(TableName,"",values);
        //could insert id is user id, or fail id is or equal 0
        return ID;
    }
    //select username,Password from Logins where ID=1
    public Cursor query(String[] Projection, String Selection, String[] SelectionArgs, String SortOrder){

        SQLiteQueryBuilder qb= new SQLiteQueryBuilder();
        qb.setTables(TableName);

        Cursor cursor=qb.query(sqlDB,Projection,Selection,SelectionArgs,null,null,SortOrder);
        return cursor;
    }

    public Cursor queryExamen(String[] Projection, String Selection, String[] SelectionArgs, String SortOrder){

        SQLiteQueryBuilder qb= new SQLiteQueryBuilder();
        qb.setTables(TableNameExamen);

        Cursor cursor=qb.query(sqlDB,Projection,Selection,SelectionArgs,null,null,SortOrder);
        return cursor;
    }

    public int Delete(String Selection,String[] SelectionArgs){
        int count=sqlDB.delete(TableName,Selection,SelectionArgs);
        return count;
    }

    public int DeleteExamen(String Selection,String[] SelectionArgs){
        int count=sqlDB.delete(TableNameExamen,Selection,SelectionArgs);
        return count;
    }

    public  int Update(ContentValues values,String Selection,String[] SelectionArgs)
    {
        int count=sqlDB.update(TableName,values,Selection,SelectionArgs);
        return count;
    }

    public  int UpdateExamen(ContentValues values,String Selection,String[] SelectionArgs)
    {
        int count=sqlDB.update(TableNameExamen,values,Selection,SelectionArgs);
        return count;
    }
}
