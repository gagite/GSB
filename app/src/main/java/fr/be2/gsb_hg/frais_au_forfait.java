package fr.be2.gsb_hg;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class frais_au_forfait extends MainActivity {
    EditText txtQte1;
    Spinner listefraisForfait1;
    String[] valeurs;
    DatePickerDialog picker;
    Calendar calendrier = Calendar.getInstance(); //on declare une classe d'un calendrier qui existe deja
    int jj=calendrier.get(Calendar.DAY_OF_MONTH);
    int mm=calendrier.get(Calendar.MONTH);
    int aaaa=calendrier.get(Calendar.YEAR);
    TextView mSomme;
    TextView maDate;
    Button btnAjouter;
    SQLHelper database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database= new SQLHelper(this);
        setContentView(R.layout.activity_frais_au_forfait);
        txtQte1 =findViewById(R.id.edittext_FraisAuForfait_quantite);
        listefraisForfait1 = findViewById(R.id.spinner_FraisAuForfait);
        mSomme = findViewById(R.id.somme);
        maDate = findViewById(R.id.textview_FraisAuForfait_date);
        valeurs = getResources().getStringArray(R.array.ValeurForfait);

        btnAjouter = findViewById(R.id.Button_FraisAuForfait_ajouter);





        txtQte1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This is where we'll check the user input
                Integer q1 = Integer.parseInt(String.valueOf("0" + txtQte1.getText()));
                //String f1 = listefraisForfait1.getSelectedItem().toString();
                int posF1 = listefraisForfait1.getSelectedItemPosition();
                Float s1 = q1 * Float.parseFloat(valeurs[posF1]);

                mSomme.setText(s1.toString());

            }

        }
        ); }
    public void ShowCal(View vv)
    {
        picker = new DatePickerDialog(frais_au_forfait.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        maDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        //date qu'on recupere une fois qu'on a selectionne
                    }
                },aaaa, mm, jj);//date qui s'affiche sur le calendrier
        picker.show();
    }

        public void click_ajouter(View v){
            switch(v.getId()){
                case R.id.Button_FraisAuForfait_ajouter:
                    Integer quantite =Integer.parseInt(String.valueOf(txtQte1.getText()));
                    String forfait = listefraisForfait1 .getSelectedItem().toString();
                    Float montantcalcul = Float.parseFloat(mSomme.getText().toString());
                    String date = maDate.getText().toString();;

                    if (database.insertData(forfait, quantite, date, montantcalcul, forfait)) {
                        afficherMessage("Succès", "Valeur ajoutée. " + "Montant= " + montantcalcul);
                        return;
                    }
             break;
            }


        }

}