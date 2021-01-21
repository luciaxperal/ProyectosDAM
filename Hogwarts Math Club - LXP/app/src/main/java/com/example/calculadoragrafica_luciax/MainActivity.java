package com.example.calculadoragrafica_luciax;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ToggleButton;

/**
 *
 * @author Lucia X. Peral
 */

public class MainActivity extends AppCompatActivity {

   EditText textoNombre;
   Button botonPlay;
   ImageButton botonStats;
   ToggleButton botonMusica;
   MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textoNombre = findViewById(R.id.editTextTextPersonName);
        botonPlay = findViewById(R.id.buttonPlay);
        botonStats = findViewById(R.id.buttonStats);
        botonMusica = findViewById(R.id.buttonMusic);
        mp = MediaPlayer.create(this, R.raw.hedwig);


        botonPlay.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v)
            {
                escalar(v);
                AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this, R.style.miAlertDialogInicio);
                dialogo.setTitle(R.string.modo)
                        .setPositiveButton(R.string.levels, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                pasarANiveles(v);
                            }
                        }).setNegativeButton(R.string.endless, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        pasarAJuegoSinFin(v);
                    }
                }).show();
            }
        });

        botonStats.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
               escalar(v);
               pasarAStats(v);
            }
        });

        botonMusica.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    mp.start();
                else
                    mp.pause();
            }
        });
    }

    public void pasarAJuegoSinFin(View v){
        Intent i = new Intent(this, Game.class);
        i.putExtra("username", textoNombre.getText().toString());
        i.putExtra("nivelSi", false);
        startActivity(i);
    }

    public void pasarAStats(View v){
        Intent i = new Intent(this, Scoreboard.class);
        startActivity(i);
    }

    public void pasarANiveles(View v){
        Intent i = new Intent(this, Levels.class);
        i.putExtra("username", textoNombre.getText().toString());
        startActivity(i);
    }

    public void escalar(View v){
        Animation animatorSetScale = AnimationUtils.loadAnimation(this, R.anim.scale);
        animatorSetScale.setDuration(500);
        v.startAnimation(animatorSetScale);
    }
}