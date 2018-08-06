package com.example.gatico.controldegastos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gatico.controldegastos.Utilidades.Utilidades;

public class MainActivity extends AppCompatActivity {
    //Listo
    private Button btInsertar;
    private Button btLista;
    private TextView tvSQL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btInsertar = (Button) findViewById(R.id.btInsertar);
        btLista = (Button) findViewById(R.id.btLista);
        tvSQL = (TextView) findViewById(R.id.tvSQL);
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_gasto", null, 1);
        tvSQL.setText(Utilidades.CREAR_TABLA_GASTO);

        btInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Acceder al segundo activity y mandarle un string
                Intent intent = new Intent(MainActivity.this, RegistroGastos.class);
                //intent.putExtra("greeter", GREETER);
                startActivity(intent);
            }
        });

        btLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Acceder al segundo activity y mandarle un string
                Intent intent = new Intent(MainActivity.this, ConsultarListaGasto.class);
                //intent.putExtra("greeter", GREETER);
                startActivity(intent);
            }
        });
    }
}