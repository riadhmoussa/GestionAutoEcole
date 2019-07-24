package com.moussa889.gestionauto_ecole;

public class AdapterItems {

    public   int ID;
    public String FirstName;
    public String LastName;
    public String CIN;
    public String NumTel;
    public String Adresse;
    public String TypeCondidat;
    //for news details
    AdapterItems( int ID, String FirstName,String LastName,String CIN,String NumTel,String Adresse,String TypeCondidat)
    {
        this. ID=ID;
        this. FirstName=FirstName;
        this. LastName=LastName;
        this.CIN=CIN;
        this.NumTel=NumTel;
        this.Adresse=Adresse;
        this.TypeCondidat=TypeCondidat;
    }
}
