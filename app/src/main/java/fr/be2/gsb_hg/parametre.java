package fr.be2.gsb_hg;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.database.Cursor;


public class parametre extends MainActivity {
    SQLHelper database;
    EditText Codev;
    EditText Nom;
    EditText Prenom;
    EditText Mail;
    EditText Urlserveur;
    EditText Password;
    Button Valider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametre);
        database = new SQLHelper(this);
        init();
    }

    @SuppressLint("Range")
    public void init() {
        Cursor Param = database.fetch_parametre();
        Param.moveToFirst();
        Codev = findViewById(R.id.Codev);
        Codev.setText(Param.getString(Param.getColumnIndexOrThrow("codev")));
        Nom = findViewById(R.id.Nom);
        Nom.setText(Param.getString(Param.getColumnIndexOrThrow("nom")));
        Prenom = findViewById(R.id.Prenom);
        Prenom.setText(Param.getString(Param.getColumnIndexOrThrow("prenom")));
        Mail = findViewById(R.id.Mail);
        Mail.setText(Param.getString(Param.getColumnIndexOrThrow("email")));
        Urlserveur = findViewById(R.id.Urlserver);
        Urlserveur.setText(Param.getString(Param.getColumnIndexOrThrow("urlserveur")));
        Password = findViewById(R.id.Password);
        Password.setText(Param.getString(Param.getColumnIndexOrThrow("motdepasse")));
        Valider=findViewById(R.id.enregistrer);
    }




    public void ClickValider(View v) {
        switch (v.getId()) {
            case R.id.enregistrer:
                if (Codev.getText().toString().trim().length() == 0 || Nom.getText().toString().length() == 0
                        || Prenom.getText().toString().trim().length() == 0 || Mail.getText().toString().trim().length() == 0 ||
                        Urlserveur.getText().toString().trim().length() == 0|| Password.getText().toString().trim().length() == 0) {
                    //getText : recupere , toString met en chiffre trim enleve les espaces lenght compte a longueur
                    afficherMessage("Erreur!", "Champ vide");
                    return;
                } else {

                    Integer codev = Integer.parseInt(String.valueOf(Codev.getText()));
                    String nom = Nom.getText().toString();
                    String prenom = Prenom.getText().toString();
                    String mail = Mail.getText().toString();
                    String urlserveur = Urlserveur.getText().toString();
                    String password = Password.getText().toString();
                    if(database.update_parametre(codev,nom,prenom,mail,urlserveur,password)){
                        afficherMessage("Succès", "Valeur ajoutée. " );
                        return;
                    }

                }
                break;
        }
    }
    public void clique_retour(View view) {
        finish();
    }


}
