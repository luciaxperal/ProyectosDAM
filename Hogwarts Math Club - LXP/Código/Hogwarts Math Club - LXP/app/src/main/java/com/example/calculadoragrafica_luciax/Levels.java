package com.example.calculadoragrafica_luciax;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 *
 * @author Lucia X. Peral
 */

public class Levels extends MainActivity implements View.OnClickListener {

    Button b1, b2, b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levels);

        b1 = findViewById(R.id.buttonLevel1);
        b2 = findViewById(R.id.buttonLevel1);
        b3 = findViewById(R.id.buttonLevel1);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
    }

    public void pasarNivel(View v){
        Intent i = new Intent(this, Game.class);


        String username1 = getIntent().getExtras().getString("username");
        i.putExtra("username", username1);
        Button nivel = (Button) v;
        i.putExtra("elNivel", nivel.getId());
        i.putExtra("nivelSi", true);
        startActivity(i);
    }

    @Override
    public void onClick(View v) {
        pasarNivel(v);
    }
}
