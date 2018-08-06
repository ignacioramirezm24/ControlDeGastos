package com.example.gatico.controldegastos;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.gatico.controldegastos.Utilidades.Utilidades;
import com.example.gatico.controldegastos.entidades.Gasto;

import java.util.ArrayList;

public class ConsultarListaGasto extends AppCompatActivity {

    private ListView lvGastos;
    private ArrayList<String> listaInformacion;
    private ArrayList<Gasto> listaGasto;
    private ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_lista_gasto);
        lvGastos = (ListView) findViewById(R.id.lvGastos);
        conn = new ConexionSQLiteHelper(getApplicationContext(), "bd_gasto", null, 1);

        consultarListaGastos();

        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaInformacion);
        lvGastos.setAdapter(adaptador);
 }

    private void consultarListaGastos() {
        SQLiteDatabase db = conn.getReadableDatabase();
        Gasto gasto = null;
        listaGasto = new ArrayList<Gasto>();
        // Select
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_GASTO, null);

        while (cursor.moveToNext()) {
            gasto = new Gasto();
            gasto.setId(cursor.getInt(0));
            gasto.setFecha(cursor.getString(1));
            gasto.setConcepto(cursor.getString(2));
            gasto.setCantidad(cursor.getInt(3));

            listaGasto.add(gasto);

        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaInformacion = new ArrayList<String>();

        for (int i = 0; i < listaGasto.size(); i++) {
            listaInformacion.add(listaGasto.get(i).getId() + " - "
                    + listaGasto.get(i).getFecha() + " \n"
                    + listaGasto.get(i).getConcepto() + " - "
                    + listaGasto.get(i).getCantidad());

        }
    }
}