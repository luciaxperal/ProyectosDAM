package com.example.micalcu_conevalex;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

/**
 *
 * @author Lucia X. Peral
 */

public class Historial extends MainActivity {
    TextView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historial);


        // Obtengo de un ArrayList las operaciones que haya ido realizando
        ArrayList<String> miLista = getIntent().getStringArrayListExtra("miLista");
        lista = findViewById(R.id.textView2);


        String cadena="";
        String linea = "....................................";
        // Recorro con un foreach y quito los número que terminan en .0
        for(String a: miLista){
            if(a.endsWith(".0")){
                a = a.substring(0, a.length()-2);
            }
            cadena = cadena + a + System.lineSeparator()+ linea + System.lineSeparator();
        }
        lista.setText(cadena);
    }
}

