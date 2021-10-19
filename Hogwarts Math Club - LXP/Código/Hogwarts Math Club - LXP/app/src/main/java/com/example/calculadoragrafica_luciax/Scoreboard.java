package com.example.calculadoragrafica_luciax;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 *
 * @author Lucia X. Peral
 */

public class Scoreboard extends MainActivity {

    ListView lv;
    ArrayList<String> laLista;
    ArrayAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scoreboard);

        lv = findViewById(R.id.listViewLista);
        laLista = listarTodo();
        adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, laLista);
        lv.setAdapter(adaptador);
    }

    public ArrayList listarTodo() {
        ArrayList<String> misPlayers = new ArrayList<>();

        AdminSQLite admin = new AdminSQLite(this, "historial", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        Cursor fila = db.rawQuery("SELECT nombre, fecha, puntos FROM players ORDER BY puntos DESC", null);

        int posicion = 0;
        if (fila != null) {
            fila.moveToFirst();
            do {
                posicion++;
                misPlayers.add(posicion+"º - "+fila.getString(0) + " - Puntos: " +
                        fila.getString(2) +"\n" + " - Fecha: " +
                        fila.getString(1));
            } while (fila.moveToNext());
            fila.close();
            db.close();
        } else{
            Toast.makeText(this, "No hay nada", Toast.LENGTH_SHORT).show();
            db.close();
        }

    return misPlayers;
}

    public void eliminarTodo() {
        AdminSQLite admin = new AdminSQLite(this, "historial", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

            db.delete("players", null, null);
            db.close();

    }
}
