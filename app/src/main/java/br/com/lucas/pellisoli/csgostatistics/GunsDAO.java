package br.com.lucas.pellisoli.csgostatistics;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class GunsDAO {

    public static void insert(Guns guns, Context context){
        ContentValues valores = new ContentValues();
        valores.put("name", guns.getName() );
        valores.put("price", guns.getPrice() );
        valores.put("kill_award", guns.getKill_award() );
        valores.put("magazine_size", guns.getMagazine_size() );

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();

        db.insert("Guns", null, valores);
    }

    public static void edit(Guns guns, Context context){
        ContentValues valores = new ContentValues();
        valores.put("name", guns.getName() );
        valores.put("price", guns.getPrice() );
        valores.put("kill_award", guns.getKill_award() );
        valores.put("magazine_size", guns.getMagazine_size() );

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();

        db.update("Guns", valores, " id = " + guns.getId() , null );
    }

    public static void delete(int id, Context context){
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();
        db.delete("Guns", " id = " + id , null);
    }

    public static List<Guns> getGunsList(Context context){
        List<Guns> lista = new ArrayList<>();
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, name, price,  kill_award, magazine_size FROM Guns ORDER BY name", null );
        if( cursor.getCount() > 0 ){
            cursor.moveToFirst();
            do{
                Guns guns = new Guns();
                guns.setId(cursor.getInt( 0));
                guns.setName(cursor.getString( 1));
                guns.setPrice(cursor.getString( 2));
                guns.setKill_award(cursor.getString( 3));
                guns.setMagazine_size(cursor.getString( 4));
                lista.add( guns );
            }while( cursor.moveToNext() );
        }
        return lista;
    }


    public static Guns getGunsById(Context context, int id){
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, name, price,  kill_award, magazine_size FROM Guns ORDER BY name", null );
        if( cursor.getCount() > 0 ){
            cursor.moveToFirst();
            Guns guns = new Guns();
            guns.setId(cursor.getInt( 0));
            guns.setName(cursor.getString( 1));
            guns.setPrice(cursor.getString( 2));
            guns.setKill_award(cursor.getString( 3));
            guns.setMagazine_size(cursor.getString( 4));
            return guns;
        }else{
            return null;
        }
    }



}
