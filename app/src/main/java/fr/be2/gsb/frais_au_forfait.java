package fr.be2.gsb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class frais_au_forfait extends MainActivity {
    EditText txtQte1;
    Spinner listefraisForfait1;
    String[] valeurs;
    double montantcalcul;
    TextView mSomme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frais_au_forfait);
        txtQte1 =findViewById(R.id.edittext_FraisAuForfait_quantite);
        listefraisForfait1 = findViewById(R.id.spinner_FraisAuForfait);
        mSomme = findViewById(R.id.somme);

        valeurs = getResources().getStringArray(R.array.ValeurForfait);



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
        );
    }
}