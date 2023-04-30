package fr.be2.gsb_hg;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class mdpoublie extends AppCompatActivity {
    EditText codeVisiteur;
    EditText email;
    EditText codeVerif;
    LinearLayout verification;
    Integer codeAleatoire;


    private static final String MONFICHIER= "GSB_PREF_USER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mdpoublier);
        codeVisiteur = findViewById(R.id.Codevisiteur);
        email = findViewById(R.id.email);
        verification = findViewById(R.id.linear);
        codeVerif= findViewById(R.id.codeVerif);
    }

    public void click_code(View v){
        Random r = new Random();
        int min = 1000;
        int max = 9999;
        codeAleatoire = r.nextInt((max - min) + 1) + min;
        Toast.makeText(this,"code="+codeAleatoire.toString(),Toast.LENGTH_SHORT).show();
        verification.setVisibility(View.VISIBLE);

    }
    public void Valide_code (View v){
        String codeAleatoireStr = codeAleatoire.toString();
        String codeverifStr = codeVerif.getText().toString();
        if (codeAleatoireStr.equals(codeverifStr)){
            Toast.makeText(this, "Réussite", Toast.LENGTH_SHORT).show();
            getSharedPreferences( MONFICHIER, MODE_PRIVATE)
                    .edit()
                    .putString("CodeVisiteur",codeVisiteur.getText().toString())
                    .putString("email", email.getText().toString())
                    .apply();
        }else{
            Toast.makeText(this, "Erreur", Toast.LENGTH_SHORT).show();
        }
    }

}