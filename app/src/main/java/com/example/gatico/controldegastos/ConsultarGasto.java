package com.example.gatico.controldegastos;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gatico.controldegastos.Utilidades.Utilidades;

public class ConsultarGasto extends AppCompatActivity {
    EditText etConsecutivo, etCantidad, etConcepto;
    Button btnConsultar, btnActualizar, btnEliminar;

    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_gasto);

        conn = new ConexionSQLiteHelper(getApplicationContext(), "bd_gasto", null, 1);

        etConcepto = (EditText) findViewById(R.id.etConcepto);
        etCantidad = (EditText) findViewById(R.id.etCantidad);
        etConsecutivo = (EditText) findViewById(R.id.etConsecutivo);

        btnActualizar = (Button) findViewById(R.id.btnActualizar);
        btnConsultar = (Button) findViewById(R.id.btnConsultar);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consultarSql();
            }
        });

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarGasto();
            }
        });

       btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarGasto();
            }
        });

    }


    private void eliminarGasto() {
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametros = {etConsecutivo.getText().toString()};

        db.delete(Utilidades.TABLA_GASTO, Utilidades.CAMPO_CONCEPTO + "=?", parametros);
        Toast.makeText(getApplicationContext(), "Ya se Eliminó el usuario", Toast.LENGTH_LONG).show();
        etConsecutivo.setText("");
        limpiar();
        db.close();
    }


    private void consultarSql() {
        SQLiteDatabase db = conn.getReadableDatabase();
        String[] parametros = {etConsecutivo.getText().toString()};

        try {
            //select concepto,cantidad from gasto where id=?
            Cursor cursor = db.rawQuery("SELECT " + Utilidades.CAMPO_CONCEPTO + "," + Utilidades.CAMPO_CANTIDAD +
                    " FROM " + Utilidades.TABLA_GASTO + " WHERE " + Utilidades.CAMPO_ID + "=? ", parametros);

            cursor.moveToFirst();
            etCantidad.setText(cursor.getString(0));
            etConcepto.setText(cursor.getString(1));

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "El gasto no existe", Toast.LENGTH_LONG).show();
            limpiar();
        }

    }

    private void consultar() {
        SQLiteDatabase db = conn.getReadableDatabase();
        String[] parametros = {etConsecutivo.getText().toString()};
        String[] campos = {Utilidades.CAMPO_CONCEPTO, Utilidades.CAMPO_CANTIDAD};

        try {
            Cursor cursor = db.query(Utilidades.TABLA_GASTO, campos, Utilidades.CAMPO_ID + "=?", parametros, null, null, null);
            cursor.moveToFirst();
            etCantidad.setText(cursor.getString(0));
            etConcepto.setText(cursor.getString(1));
            cursor.close();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "El documento no existe", Toast.LENGTH_LONG).show();
            limpiar();
        }


    }

    private void actualizarGasto() {
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametros = {etConsecutivo.getText().toString()};
        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_CONCEPTO, etConcepto.getText().toString());
        values.put(Utilidades.CAMPO_CANTIDAD, etCantidad.getText().toString());

        db.update(Utilidades.TABLA_GASTO, values, Utilidades.CAMPO_CONCEPTO + "=?", parametros);
        Toast.makeText(getApplicationContext(), "Ya se actualizó el Gasto", Toast.LENGTH_LONG).show();
        db.close();

    }

    private void limpiar() {
        etCantidad.setText("");
        etConcepto.setText("");
    }

}

