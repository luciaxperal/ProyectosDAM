package com.example.calculadoragrafica_luciax;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Lucia X. Peral
 */

public class Game extends MainActivity {

    //VARIABLES DECLARADAS
    TextView username, operador, points, level, respuesta;
    Button comprobar, borrar;
    boolean nivelSi;
    int numVidas, numPuntos, num1, num2, nivel;
    ImageView vidas, numero1, numero2;
    ArrayList<String> a;
    ObjectAnimator animatorY, rotation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);


        //VARIABLES INICIALIZADAS
        username = findViewById(R.id.textViewPlayer);
        level = findViewById(R.id.textViewNivel);
        points = findViewById(R.id.textViewScore);
        operador = findViewById(R.id.textViewOperador);
        numero1 = findViewById(R.id.imageViewNumeroUno);
        numero2 = findViewById(R.id.imageViewNumeroDos);
        respuesta = findViewById(R.id.textViewRespuesta);
        comprobar = findViewById(R.id.buttonComprobar);
        vidas =  findViewById(R.id.imageViewVidas);
        borrar = findViewById(R.id.buttonDelete);

        a = new ArrayList<>();
        //DATOS INICIALES
        estadoInicial();
        //Username
        String username1 = getIntent().getExtras().getString("username");
        username.setText(username.getText()+" "+username1);
        compruebaPuntosParaSacarNiveles();

        nivelSi = getIntent().getExtras().getBoolean("nivelSi");

        if(nivelSi==true){
            nivel = getIntent().getExtras().getInt("elNivel");
            estadoInicial();
            compruebaNombreLevels();
        }

        animarNumeros();

        //EVENTO COMPROBAR RESULTADO
        comprobar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                if(nivelSi==false)
                    jugarSinFin();
                else
                    jugarUnNivel();
                a.clear();
                animarNumeros();
            }
        });

        borrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                String laRespuesta = respuesta.getText().toString();
                if(laRespuesta.isEmpty())
                    respuesta.setText("");
                else
                a.remove(a.size()-1);

                visualizarCadena();
            }
        });
    }

    //------------------------ TEXT VIEW DEL RESULTADO -----------------------------

    public void visualizarCadena(){
        String elResul = "";
        for(String s: a){
            elResul = elResul+s;
        }
        respuesta.setText(elResul);
    }

    //Método para que los botones escriban el número
    public void escribirNum(View v){
        Button b = (Button)v;
        String s = ""+b.getText();
        a.add(s);
        visualizarCadena();
    }

    // -------------------------- NIVELES ----------------------------------------

    public void compruebaPuntosParaSacarNiveles(){
        if(numPuntos>10 && numPuntos<20){
            sacarPorNiveles(Niveles.LEVEL2);
            level.setText(R.string.level2);
            escalar(level);
        } else if(numPuntos>=20){
            sacarPorNiveles(Niveles.LEVEL3);
            level.setText(R.string.level3);
            escalar(level);
        } else{
            sacarPorNiveles(Niveles.LEVEL1);
            level.setText(R.string.level1);
        }
    }

    public void compruebaNombreLevels(){
        if(R.id.buttonLevel2==nivel){
            sacarPorNiveles(Niveles.LEVEL2);
            level.setText(R.string.level2);
        } else if(R.id.buttonLevel3==nivel){
            sacarPorNiveles(Niveles.LEVEL3);
            level.setText(R.string.level3);
        } else{
            sacarPorNiveles(Niveles.LEVEL1);
            level.setText(R.string.level1);
        }
        escalar(level);
    }

    public void sacarPorNiveles(Niveles n){
        sacaOperadorAleatorio(n.getOperadores());
        sacaNumerosAleatorios(n.getNumMin(), n.getNumMax());

        while(num1-num2<0)
            sacaNumerosAleatorios(n.getNumMin(), n.getNumMax());

        while(num1%num2!=0 && operador.getText().toString().equals("/"))
            sacaNumerosAleatorios(n.getNumMin(), n.getNumMax());
    }


    // --------------------- MÉTODOS OPERACIONES ------------------------

    //Saca números aleatorios dependiendo del nivel
    public void sacaNumerosAleatorios(int nMin, int nMax){
        num1 = (int) (Math.random()* (nMax - nMin) + nMin);
        num2 = (int) (Math.random()* (nMax - nMin) + nMin);

        numero1.setImageResource(asignaNumToImg(num1));
        numero2.setImageResource(asignaNumToImg(num2));

    }


    public int asignaNumToImg(int num){
        switch(num) {
            case 9:
                return R.drawable.nueve;
            case 1:
                return R.drawable.uno;
            case 2:
                return R.drawable.dos;
            case 3:
                return R.drawable.tres;
            case 4:
                return R.drawable.cuatro;
            case 5:
                return R.drawable.cinco;
            case 6:
                return R.drawable.seis;
            case 7:
                return R.drawable.siete;
            case 8:
                return R.drawable.ocho;
            default:
                return R.drawable.cero;
        }
    }

    //Saca un operador aleatorio
    public void sacaOperadorAleatorio(String operadores){
            int ope = (int) (Math.random()*operadores.length());
            operador.setText(""+operadores.charAt(ope));
    }

    public int asignaVidaToImg(int numVida){
        switch(numVida) {
            case 1:
                return R.drawable.unavida;
            case 2:
                return R.drawable.dosvidas;
            default:
                return R.drawable.tresvidas;
        }
    }

    // -------------------------- NÚCLEO DEL PROGRAMA ----------------------------------------

    //Comprueba el resultado dado. Devuelve true si es correcto y false en caso contrario
    public boolean comprobarRespuesta(){
        int resul=0;
        if(operador.getText().toString().equals("+"))
            resul = num1+num2;
        if(operador.getText().toString().equals("-"))
            resul = num1-num2;
        if(operador.getText().toString().equals("*"))
            resul = num1*num2;
        if(operador.getText().toString().equals("/"))
            resul = num1 / num2;

        String laRespuesta = respuesta.getText().toString();
        if(laRespuesta.equals(String.valueOf(resul)))
            return true;
        else
            return false;

    }

    /*Si es correcta la respuesta, suma puntos. En caso contrario, resta vidas. Cuando las vidas lleguen
    a cero, se termina la partida y se pregunta si se quiere reiniciar
     */
    public void jugarSinFin(){
        if (comprobarRespuesta()) {
            numPuntos++;
            escalar(points);
            points.setText(""+numPuntos);
        } else {
            numVidas--;
            escalar(vidas);
            vidas.setImageResource(asignaVidaToImg(numVidas));
        }

        if(numVidas>0){
            compruebaPuntosParaSacarNiveles();
            respuesta.setText("");
        } else{
            agnadirRegistro();
            AlertDialog.Builder dialogo = new AlertDialog.Builder(Game.this, R.style.miAlertDialogEnd);
            dialogo.setTitle(username.getText().toString()+"- "+R.string.puntos+" " +numPuntos);
            dialogo.setMessage(R.string.mensaje_gameover)
                    .setPositiveButton(R.string.reiniciar, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            estadoInicial();
                            compruebaPuntosParaSacarNiveles();
                        }
                    }).setNegativeButton(R.string.exit, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            }).show();
        }
    }

    public void jugarUnNivel(){
        if (comprobarRespuesta()) {
            numPuntos++;
            escalar(points);
            points.setText(""+numPuntos);
        } else {
            numVidas--;
            escalar(vidas);
            vidas.setImageResource(asignaVidaToImg(numVidas));
        }

        if(numVidas>0) {
            compruebaNombreLevels();
            respuesta.setText("");
        } else{
            AlertDialog.Builder dialogo = new AlertDialog.Builder(Game.this, R.style.miAlertDialogEnd);
            dialogo.setTitle("GAME OVER");
            dialogo.setMessage(R.string.mensaje_gameover)
                    .setPositiveButton(R.string.reiniciar, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            estadoInicial();
                            compruebaNombreLevels();
                        }
                    }).setNegativeButton(R.string.levels, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            }).show();
        }

        if(numPuntos==10){
            AlertDialog.Builder dialogo = new AlertDialog.Builder(Game.this, R.style.miAlertDialogEnd);
            dialogo.setMessage(R.string.mensaje_ganador)
                    .setPositiveButton(R.string.reiniciar, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    estadoInicial();
                    compruebaNombreLevels();
                }
            }).setNegativeButton(R.string.levels, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            }).show();
        }

    }

    public void agnadirRegistro(){
        //Creas la conexion y la abres
        AdminSQLite admin = new AdminSQLite(this, "historial", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        Date date = new Date();
        DateFormat fechayhora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String user = username.getText().toString();
        String puntos = points.getText().toString();
        String fecha = ""+fechayhora.format(date);

            ContentValues registro = new ContentValues();
            registro.put("nombre", user);
            registro.put("puntos", puntos);
            registro.put("fecha", fecha);

            db.insert("players", null, registro);
            db.close();

            Toast.makeText(this, "Registro añadido!", Toast.LENGTH_SHORT).show();
    }

    // -------------------------- ESTADOS ----------------------------------------
    public void estadoInicial(){
        //Vidas
        numVidas = 3;
        vidas.setImageResource(asignaVidaToImg(numVidas));

        //Puntos
        numPuntos = 0;
        points.setText(""+numPuntos);
    }


    //GUARDAR EL ESTADO DE LAS PANTALLAS
    @Override
    protected void onSaveInstanceState(Bundle guardaEstado) {
            super.onSaveInstanceState(guardaEstado);
            guardaEstado.putInt("pto", numPuntos);
            guardaEstado.putInt("vida", numVidas);
            guardaEstado.putInt("num1", num1);
            guardaEstado.putInt("num2", num2);
            guardaEstado.putInt("elNivel", nivel);
            guardaEstado.putString("operator", operador.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle recuperaEstado) {
            super.onRestoreInstanceState(recuperaEstado);
            numPuntos = recuperaEstado.getInt("pto");
            points.setText(""+recuperaEstado.getInt("pto"));
            numVidas = recuperaEstado.getInt("vida");
            vidas.setImageResource(asignaVidaToImg(numVidas));
            num1 = recuperaEstado.getInt("num1");
            numero1.setImageResource(asignaNumToImg(num1));
            num2 = recuperaEstado.getInt("num2");
            nivel = getIntent().getExtras().getInt("elNivel");
            numero2.setImageResource(asignaNumToImg(num2));
            operador.setText(recuperaEstado.getString("operator"));

    }

    //ANIMACIONES

    public void moverY(View v){
        animatorY = ObjectAnimator.ofFloat(v, "y", 500f);
        animatorY.setDuration(500);
        AnimatorSet animatorSetBucle = new AnimatorSet();
        animatorSetBucle.play(animatorY);
        animatorSetBucle.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                animation.start();
            }
        });
        animatorSetBucle.start();
    }

    public void girar(View v){
        rotation = ObjectAnimator.ofFloat(v, "rotation", 0f, 360f);
        rotation.setDuration(1000);
        AnimatorSet animatorSetRotation = new AnimatorSet();
        animatorSetRotation.play(rotation);
        animatorSetRotation.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                animation.start();
            }
        });
        animatorSetRotation.start();
    }

    public void escalar(View v){
        Animation animatorSetScale = AnimationUtils.loadAnimation(this, R.anim.scale);
        v.startAnimation(animatorSetScale);
    }

    public void animarNumeros(){
        moverY(numero1);
        moverY(numero2);
        girar(operador);
    }
}
