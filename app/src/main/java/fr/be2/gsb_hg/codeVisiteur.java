package fr.be2.gsb_hg;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;



import java.util.Random;

public class codeVisiteur extends AppCompatActivity {
    EditText codeVisiteur;
    EditText email;
    EditText codeVerif;
    LinearLayout envoyer;
    Integer codeAleatoir;
    Intent intent;

    private static final String MON_FICHIER = "GSB_PREF_USER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codevisiteur);
        codeVisiteur = findViewById(R.id.Codevisiteur);
        email = findViewById(R.id.email);
        envoyer = findViewById(R.id.layout_mdpoublier);
        codeVerif= findViewById(R.id.newmdp);

    }

    public void envoicode(View v){


        Random r = new Random();
        int min = 1000;
        int max = 9999;
        codeAleatoir = r.nextInt((max - min) + 1) + min;
        Toast.makeText(this,"code="+codeAleatoir.toString(),Toast.LENGTH_SHORT).show();
        envoyer.setVisibility(View.VISIBLE);

       }
    public void boncode (View v){
        String codeAleatoirStr = codeAleatoir.toString();
        String codeverifStr = codeVerif.getText().toString();
        if (codeAleatoirStr.equals(codeverifStr)){
            Toast.makeText(this, "votre code est bon", Toast.LENGTH_SHORT).show();
            getSharedPreferences(MON_FICHIER, MODE_PRIVATE)
                    .edit()
                    .putString("codeVisiteur", codeVisiteur.getText().toString())
                    .putString("email",email.getText().toString())
                    .apply();

            Intent intent = new Intent(codeVisiteur.this,MainActivity.class);
            startActivity(intent);


        }else{
            Toast.makeText(this, "erreur erreur", Toast.LENGTH_SHORT).show();
        }
    }


}
