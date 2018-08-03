package com.example.gatico.controldegastos.Utilidades;

import static java.sql.Types.INTEGER;
import static java.util.Calendar.DATE;
import static javax.xml.xpath.XPathConstants.STRING;

public class Utilidades {

    //Constantes campos tabla
    // public Gasto(Integer id, Date fecha, String concepto, Integer cantidad)

    public static final String TABLA_GASTO = "gasto";
    public static final String CAMPO_ID = "id";
    public static final String CAMPO_FECHA = "fecha";
    public static final String CAMPO_CONCEPTO = "concepto";
    public static final String CAMPO_CANTIDAD = "cantidad";


    public static final String CREAR_TABLA_GASTO = "CREATE TABLE " +
            ""+TABLA_GASTO+" ("+
            CAMPO_ID+" " + "INTEGER PRIMARY KEY AUTOINCREMENT, "+
            CAMPO_FECHA+ " DATETIME DEFAULT CURRENT_TIMESTAMP, " +
            CAMPO_CONCEPTO+ " TEXT, "+
            CAMPO_CANTIDAD+ " INTEGER)";
}

