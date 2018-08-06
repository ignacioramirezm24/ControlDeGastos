package com.example.gatico.controldegastos;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gatico.controldegastos.Utilidades.Utilidades;

public class RegistroGastos extends AppCompatActivity {

    private EditText etCantidad;
    private EditText etConcepto;
    //private EditText etFecha;
    private Button btRegistrarGasto;
    private TextView tvSentencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_gastos);

        etCantidad = (EditText) findViewById(R.id.etCantidad);
        etConcepto = (EditText) findViewById(R.id.etConcepto);
        //etFecha = (EditText) findViewById(R.id.etFecha);
        btRegistrarGasto = (Button) findViewById(R.id.btRegistrarGasto);
        tvSentencia = (TextView) findViewById(R.id.tvSentencia);

        btRegistrarGasto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                registrarGasto();

            }
        });

    }

    private void registrarGasto() {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_gasto", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();
        ContentValues values = new ContentValues();
        // public Gasto(Integer id, Date fecha, String concepto, Integer cantidad)

        values.put(Utilidades.CAMPO_CANTIDAD, etCantidad.getText().toString());
        values.put(Utilidades.CAMPO_CONCEPTO, etConcepto.getText().toString());

        Long idResultante = db.insert(Utilidades.TABLA_GASTO, Utilidades.CAMPO_ID, values);
        Toast.makeText(getApplicationContext(), idResultante + "Nuevo regitro agregado: "
                + etCantidad.getText() + " por: "
                + etConcepto, Toast.LENGTH_SHORT).show();
        db.close();
        //tvSentencia.setText(Utilidades.CREAR_TABLA_GASTO);
        limpiar();
    }

    private void limpiar() {
        etConcepto.setText("");
        etCantidad.setText("");
    }
}
