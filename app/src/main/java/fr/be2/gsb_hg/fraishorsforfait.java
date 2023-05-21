package fr.be2.gsb_hg;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class fraishorsforfait extends MainActivity {
    //declaration des variables
    Button btnAjouter;
    EditText  libelle;
    EditText montant;
    TextView date;
    SQLHelper database; //variable qui permet d'accéder à la classe SQLHelper pr pouvoir accéder à ses méthodes.
    DatePickerDialog picker;
    Calendar calendrier = Calendar.getInstance(); //on declare une classe d'un calendrier qui existe deja
    int jj=calendrier.get(Calendar.DAY_OF_MONTH);
    int mm=calendrier.get(Calendar.MONTH);
    int aaaa=calendrier.get(Calendar.YEAR);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fraishorsforfait);
        database=new SQLHelper(this); //j'instancie la classe SQLHelper avec la variable database
        init();
    }

    /**
     * Permet de relier les boutons avec leur objet graphique
     */
    public void init(){
        btnAjouter=findViewById(R.id.button_FraisHorsForfait_ajouter);
        libelle=findViewById(R.id.edittext_FraisHorsForfait_libelle);
        montant=findViewById(R.id.edittext_FraisHorsForfait_montant);
        date=findViewById(R.id.textview_FraisHorsForfait_date);
        DatePickerDialog picker;
    }


    public void ShowCal1(View vv)
    {
        picker = new DatePickerDialog(fraishorsforfait.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        //date qu'on recupere une fois qu'on a selectionne
                    }
                },aaaa, mm, jj);//date qui s'affiche sur le calendrier
        picker.show();
    }

    /**
     * Ajoute le libelle, la date et le montant d'un frais hors forfait
     * @param view
     */
    public void monClick(View view){
        switch (view.getId()){
            case R.id.button_FraisHorsForfait_ajouter:
                String libelle1= libelle.getText().toString();
                Float montant1 = Float.parseFloat(montant.getText().toString());//conversion d'un text
                // en string et d'un string en float
                String date1=date.getText().toString();
                if(database.insertData("Hors forfait", null, date1, montant1, libelle1)){
                    afficherMessage("Succès!", "Informations ajoutées.");
                    return;

        }
    }}

    /**
     * Permet le retour à la page précédente
     * @param view
     */
    public void clique_retour(View view) {
        finish();
    }

}
