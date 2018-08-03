package com.example.gatico.controldegastos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.gatico.controldegastos.Utilidades.Utilidades;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {
    // public Gasto(Integer id, Date fecha, String concepto, Integer cantidad)


    public ConexionSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Utilidades.CREAR_TABLA_GASTO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) {
        db.execSQL("DROP TABLE IF EXISTS gasto");
        onCreate(db);


    }
}
