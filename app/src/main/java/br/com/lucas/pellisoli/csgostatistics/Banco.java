package br.com.lucas.pellisoli.csgostatistics;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Banco extends SQLiteOpenHelper {

    private static final int VERSAO = 3;
    private static final String NOME = "AppFilmes";

    public Banco(Context context){
        super(context, NOME, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS Guns ( " +
                "     id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT ," +
                "     name TEXT NOT NULL ," +
                "     price TEXT NOT NULL ," +
                "     kill_award TEXT NOT NULL ," +
                "     magazine_size TEXT NOT NULL) "  );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
    }
}
