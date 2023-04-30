package fr.be2.gsb_hg;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log ;

public class SQLHelper extends SQLiteOpenHelper {
    //declaration des variables
    public static final String DB_NAME = "GSB.db";
    public static final String DB_TABLE = "FRAIS";
    public static final String ID_FRAIS = "ID_FRAIS"; //ce sera les colonnes de la table frais
    public static final String TYPE_FRAIS = "TYPE_FRAIS";
    public static final String QUANTITE = "QUANTITE";
    public static final String DATE_FRAIS = "DATE_FRAIS";
    public static final String MONTANT = "MONTANT";
    public static final String DATE_SAISIE = "DATE_SAISIE";
    public static final String LIBELLE = "LIBELLE";

    private static final String TAG = "FraisDbAdapter";

    private SQLHelper mDbHelper;
    private SQLiteDatabase mDb;
    //private final Context mCtx;


    /**
     * Crée une table par une requete SQL
     */
    private static final String CREATE_TABLE = "CREATE TABLE " + DB_TABLE + " (" + ID_FRAIS +
            " INTEGER PRIMARY KEY AUTOINCREMENT," + TYPE_FRAIS + " TEXT," + QUANTITE + " INTEGER," + DATE_FRAIS
            + " TEXT," + MONTANT + " REAL," + LIBELLE + " TEXT," + DATE_SAISIE + " DATETIME DEFAULT CURRENT_TIMESTAMP)";

    private static final String CREATE_PARAMETRES="CREATE TABLE PARAMETRES(id int primary key,codev text ,nom text ," +
            "prenom text, email text , urlserveur text ,password text)";

    private static final String INIT_PARAMETRES="INSERT INTO PARAMETRES( ID, CODEV,NOM, PRENOM,EMAIL, URLSERVEUR) Values(1,0,'','','@','https://')";

    /**
     *
     * @param context
     */
    public SQLHelper(Context context) {

        super(context, DB_NAME, null, 1);//permet d'acceder aux membres de la classe mère

    }




        /**
         * constructeur de la classe
         * methode venant de SQLLite et permettant d'executer une requete SQL
         *
         * @param sqLiteDatabase
         */
        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

            sqLiteDatabase.execSQL(CREATE_TABLE);

        }


        /**
         * destructeur de la classe
         *
         * @param sqLiteDatabase
         * @param i
         * @param i1
         */
        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
            onCreate(sqLiteDatabase);
        }




    /**
     * Insère dans le BDD les données type, quantité, date, montant et libellé renseignées par le visiteur
     * @param typeForfait
     * @param quantite
     * @param dateForfait
     * @param montant
     * @param libelle
     * @return booleen
     */
    public boolean insertData(String typeForfait, Integer quantite, String dateForfait, double montant, String libelle) {
        //on cree une variable de type sqLitedatabase pr pouvoir y acceder
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TYPE_FRAIS, typeForfait);
        contentValues.put(QUANTITE, quantite);
        contentValues.put(DATE_FRAIS, dateForfait);
        contentValues.put(MONTANT, montant);
        contentValues.put(LIBELLE, libelle);
        //insert sert a inserer des donnees, elle insere ds notre table contentValue les contenus
        // des variables que l'utilisateur renseigne
        long result = db.insert(DB_TABLE, null, contentValues);
        return result != -1;

    }


    private fr.be2.gsb_hg.SQLHelper SQLHelper;
    private SQLiteDatabase Sql;


    private static final String SQLITE_TABLE = " Frais " ;









    public void close() {
        if (SQLHelper != null) {
            SQLHelper.close();
        }
    }

    public long createFrais(String code, String Type_Forfait,
                            String Quantite, String date) {

        ContentValues initialValues = new ContentValues();
        initialValues.put(LIBELLE, code);
        initialValues.put(TYPE_FRAIS, Type_Forfait);
        initialValues.put(QUANTITE, Quantite);
        initialValues.put(DATE_SAISIE, date);

        return Sql.insert(SQLITE_TABLE, null, initialValues);
    }
    public SQLHelper open() throws SQLException {

        SQLiteDatabase db =this.getWritableDatabase();
        return this;

    }

    public boolean deleteAllFrais() {

        int doneDelete = 0;
        doneDelete = Sql.delete(SQLITE_TABLE, null , null);
        Log.w(TAG, Integer.toString(doneDelete));
        return doneDelete > 0;

    }

    public Cursor fetchFraisByName(String inputText) throws SQLException {
        Log.w(TAG, inputText);
        Cursor mCursor = null;
        if (inputText == null  ||  inputText.length () == 0)  {
            mCursor = Sql.query(SQLITE_TABLE, new String[] {"rowid _id",
                            LIBELLE, TYPE_FRAIS, QUANTITE, DATE_FRAIS},
                    null, null, null, null, null);

        }
        else {
            mCursor = Sql.query(true, SQLITE_TABLE, new String[] {"rowid _id",
                            LIBELLE, TYPE_FRAIS, QUANTITE, DATE_SAISIE},
                    TYPE_FRAIS + " like '%" + inputText + "%'", null,
                    null, null, null, null);
        }
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }


    public Cursor fetchAllFrais() {

        Cursor mCursor = Sql.query(SQLITE_TABLE, new String[] {"rowid _id",LIBELLE,
                        TYPE_FRAIS, QUANTITE, DATE_SAISIE},

                null, null, null, null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public void insertSomeFrais() {

        createFrais("01585686","Petit dejeuné", "5", "01/01/2017");
        createFrais("01456895","Nuité Hotel", "20", "01/03/2017");
        createFrais("01514578","Nuité Hotel", "15", "01/04/2017");
        createFrais("01531586","Forfait etape", "6", "26/01/2017");
        createFrais("01467983","Repas restaurant", "2", "29/01/2017");
        createFrais("01245876","Petit dejeuné", "4", "07/09/2017");
        createFrais("01542571","Forfait etape", "17", "16/05/2017");

    }


    public Cursor fetchFrais(String filtre) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor Cursor = db.query(DB_TABLE, new String[]{"rowid _id",LIBELLE,
                        DATE_SAISIE, MONTANT, QUANTITE},
                filtre, null, null, null, null);

        if (Cursor != null) {
            Cursor.moveToFirst();
        }
        return Cursor;
    }

}




