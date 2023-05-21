package fr.be2.gsb_hg;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.database.Cursor;


public class parametre extends MainActivity {
    //SQLHelper database;
    EditText Codev;
    EditText Nom;
    EditText Prenom;
    EditText Mail;
    EditText Urlserveur;
    Button Valider;
    private static final String MON_FICHIER = "GSB_PREF_USER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametre);
        //database = new SQLHelper(this);
        init();
    }

    @SuppressLint("Range")
    public void init() {
        //Cursor Param = database.fetch_parametre();
        //Param.moveToFirst();
        Codev = findViewById(R.id.Codev);
        Codev.setText(getSharedPreferences(MON_FICHIER, MODE_PRIVATE).getString("codeVisiteur",""));
        Nom = findViewById(R.id.Nom);
        Nom.setText(getSharedPreferences(MON_FICHIER, MODE_PRIVATE).getString("Nom",""));
        Prenom = findViewById(R.id.Prenom);
        Prenom.setText(getSharedPreferences(MON_FICHIER, MODE_PRIVATE).getString("Prenom",""));
        Mail = findViewById(R.id.Mail);
        Mail.setText(getSharedPreferences(MON_FICHIER, MODE_PRIVATE).getString("email",""));
        Urlserveur = findViewById(R.id.Urlserver);
        Urlserveur.setText(getSharedPreferences(MON_FICHIER, MODE_PRIVATE).getString("Urlserver",""));
        Valider=findViewById(R.id.enregistrer);
    }




    public void ClickValider(View v) {


                getSharedPreferences(MON_FICHIER, MODE_PRIVATE)
                        .edit()
                        .putString("codeVisiteur", Codev.getText().toString())
                        .putString("email",Mail.getText().toString())
                        .putString("Nom",Nom.getText().toString())
                        .putString("Prenom",Prenom.getText().toString())
                        .putString("Urlserver",Urlserveur.getText().toString())

                        .apply();



        }

    public void clique_retour(View view) {
        finish();
    }


}
