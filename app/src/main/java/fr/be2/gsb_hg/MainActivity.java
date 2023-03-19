package fr.be2.gsb_hg;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Intent intent;
    EditText codvisiteur;
    Context context;
    private static final String MON_FICHIER = "GSB_PREF_USER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        secure();
        setContentView(R.layout.activity_main);
    }

    public void click_menu(View v){
        //String MSG = "";

        switch(v.getId()){
            case R.id.button_menu_FraisAuForfait:
                //MSG ="bouton frais au forfait";
                intent = new Intent(MainActivity.this, frais_au_forfait.class);
                break;
            case R.id.Button_menu_FraisHorsForfait:
                //MSG = "Bouton frais hors forfait";
                intent = new Intent(MainActivity.this, fraishorsforfait.class);
                break;
            case R.id.Button_menu_Parametre:
                //MSG = "Bouton consulter les frais";
                intent = new Intent(MainActivity.this, parametre.class);
                break;
            case R.id.Button_menu_ConsulterLesFrais:
                //MSG = "Paramétre";
                intent = new Intent(MainActivity.this, consulterlesfrais.class);
                break;
            case R.id.Button_deconnexion:

                getSharedPreferences(MON_FICHIER, MODE_PRIVATE)
                        .edit()
                        .clear()
                        .commit();

        }
        startActivity(intent);
        //Toast.makeText(this, MSG, Toast.LENGTH_SHORT).show();
    }

    public void closeActivity(View v){
        this.finish();
    }

    public void secure(){
        String cvisiteur= getSharedPreferences("GSB_PREF_USER", MODE_PRIVATE).getString("codeVisiteur","pas authentif40ie");
        if (cvisiteur.equals("pas authentifie")) {
            Intent intent = new Intent(MainActivity.this,codeVisiteur.class);
            startActivity(intent);
        }
    }




    public void envoicode(View view) {
    }

    public void boncode(View view) {
    }

    public void click_ajouter(View view) {
    }
    public void afficherMessage(String titre, String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this); //classe qui constuit une boite de dialogue
        builder.setCancelable(true); //pr que la boite de dialogue soit refermable
        builder.setTitle(titre);
        builder.setMessage(message);
        builder.show();

    }

    public void ShowCal(View view) {
    }
}