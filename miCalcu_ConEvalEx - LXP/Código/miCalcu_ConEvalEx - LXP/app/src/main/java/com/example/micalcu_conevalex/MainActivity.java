package com.example.micalcu_conevalex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.udojava.evalex.Expression;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 *
 * @author Lucia X. Peral
 */

public class MainActivity extends AppCompatActivity {

    Button boton0, boton1, boton2, boton3, boton4, boton5, boton6, boton7, boton8, boton9, botonBorrar, botonAbreParentesis, botonRaiz,
            botonReset, botonMultiplicar, botonSumar, botonRestar, botonDividir, botonResultado, botonComa, botonCierraParentesis, botonAns;

    TextView pantallaResultado, pantallaGuardado;
    String operador, numero, cadenaPantalla;

    ArrayList<String> operaciones;
    ArrayList<String> laOperacion;
    ArrayList<String> resulGuardado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        operaciones = new ArrayList<>();
        laOperacion = new ArrayList<>();
        resulGuardado = new ArrayList<>();

        pantallaResultado = findViewById(R.id.textViewResultado);
        pantallaGuardado = findViewById(R.id.textViewGuardado);

        //BOTONES
        boton0 = findViewById(R.id.button0);
        boton1 = findViewById(R.id.button1);
        boton2 = findViewById(R.id.button2);
        boton3 = findViewById(R.id.button3);
        boton4 = findViewById(R.id.button4);
        boton5 = findViewById(R.id.button5);
        boton6 = findViewById(R.id.button6);
        boton7 = findViewById(R.id.button7);
        boton8 = findViewById(R.id.button8);
        boton9 = findViewById(R.id.button9);
        botonReset = findViewById(R.id.buttonReset);
        botonMultiplicar = findViewById(R.id.buttonMultiplicar);
        botonSumar = findViewById(R.id.buttonSumar);
        botonRestar = findViewById(R.id.buttonRestar);
        botonDividir = findViewById(R.id.buttonDividir);
        botonResultado = findViewById(R.id.buttonResultado);
        botonComa = findViewById(R.id.buttonComa);
        botonRaiz = findViewById(R.id.buttonRaiz);
        botonAns = findViewById(R.id.buttonAns);
        botonBorrar = findViewById(R.id.buttonBorrar);
        botonAbreParentesis = findViewById(R.id.buttonAbreParentesis);
        botonCierraParentesis = findViewById(R.id.buttonCierraParentesis);

        //EVENTOS DE ACCION

        boton0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numero = "0";
                laOperacion.add(numero);
                visualizarCadena();
                irCalculando();
            }
        });

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numero = "1";
                laOperacion.add(numero);
                visualizarCadena();
                irCalculando();
            }
        });

        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numero = "2";
                laOperacion.add(numero);
                visualizarCadena();
                irCalculando();
            }
        });

        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numero = "3";
                laOperacion.add(numero);
                visualizarCadena();
                irCalculando();
            }
        });

        boton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numero = "4";
                laOperacion.add(numero);
                visualizarCadena();
                irCalculando();
            }
        });

        boton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numero = "5";
                laOperacion.add(numero);
                visualizarCadena();
                irCalculando();
            }
        });

        boton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numero = "6";
                laOperacion.add(numero);
                visualizarCadena();
                irCalculando();
            }
        });

        boton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numero = "7";
                laOperacion.add(numero);
                visualizarCadena();
                irCalculando();
            }
        });

        boton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numero = "8";
                laOperacion.add(numero);
                visualizarCadena();
                irCalculando();
            }
        });

        boton9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numero = "9";
                laOperacion.add(numero);
                visualizarCadena();
                irCalculando();
            }
        });

        botonComa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadenaPantalla = pantallaResultado.getText().toString();
                if(!cadenaPantalla.endsWith(".")){
                    laOperacion.add(".");
                    visualizarCadena();
                    irCalculando();
                }
            }
        });

        botonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                laOperacion.clear();
                pantallaResultado.setText("");
                pantallaGuardado.setText("");
            }
        });

        botonBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadenaPantalla = pantallaResultado.getText().toString();
                if (cadenaPantalla.isEmpty()) {
                    pantallaResultado.setText("");
                    pantallaGuardado.setText("");
                } else {
                    laOperacion.remove(laOperacion.size()-1);
                    visualizarCadena();
                    irCalculando();
                }
            }
        });

        botonSumar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadenaPantalla = pantallaResultado.getText().toString();
                operador="+";
                if(!cadenaPantalla.endsWith(operador)) {
                    laOperacion.add(operador);
                    visualizarCadena();
                }
            }
        });

        botonRestar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadenaPantalla = pantallaResultado.getText().toString();
                operador = "-";
                if(!cadenaPantalla.endsWith(operador)) {
                    laOperacion.add(operador);
                    visualizarCadena();
                };

            }
        });

        botonMultiplicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadenaPantalla = pantallaResultado.getText().toString();
                operador = "*";
                if(!cadenaPantalla.endsWith(operador)) {
                    laOperacion.add(operador);
                    visualizarCadena();
                }
            }
        });

        botonDividir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadenaPantalla = pantallaResultado.getText().toString();
                operador = "/";
                if(!cadenaPantalla.endsWith(operador)) {
                    laOperacion.add(operador);
                    visualizarCadena();
                }

            }
        });

        botonRaiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadenaPantalla = pantallaResultado.getText().toString();
                if(!cadenaPantalla.endsWith("√")) {
                    laOperacion.add("√");
                    visualizarCadena();
                }

            }
        });

        botonAbreParentesis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadenaPantalla = pantallaResultado.getText().toString();
                laOperacion.add("(");
                visualizarCadena();
            }
        });

        botonCierraParentesis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadenaPantalla = pantallaResultado.getText().toString();
                laOperacion.add(")");
                visualizarCadena();
                irCalculando();
            }
        });

        botonAns.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                laOperacion.clear();

                    String ans = "";
                    for(String e: resulGuardado){
                        ans = e;
                    }
                    laOperacion.add(ans);
                    visualizarCadena();
                    irCalculando();

            }
        });

        botonResultado.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                visualizarCadena();
                String ope = pantallaResultado.getText().toString();
                String elResultado = pantallaGuardado.getText().toString();
                if(!elResultado.isEmpty() && !elResultado.equals("No se puede dividir entre 0")){
                    operaciones.add(ope+"="+elResultado);
                    pantallaResultado.setText(elResultado);
                    pantallaGuardado.setText("");
                    laOperacion.clear();

                    if(!elResultado.equals("ERROR")){
                        laOperacion.add(elResultado);
                        resulGuardado.clear();
                        resulGuardado.add(elResultado);
                    }

                }
            }
        });


    }

    // Este método recorre el arraylist que guarda lo que el usuario ha introducido por pantalla
    public void visualizarCadena(){
        String elResul = "";
        for(String s: laOperacion){
            elResul = elResul+s;
        }
        pantallaResultado.setText(elResul);
    }

    // Este método calcula con ayuda de EvalEx la operación introducida por el usuario
    public void irCalculando(){
        try{
            String operacion = pantallaResultado.getText().toString();

            if(Pattern.matches("[0-9]/0", operacion)){
                pantallaGuardado.setText("No se puede dividir entre 0");
            } else {
                BigDecimal result = null;
                Expression expression = null;

                // Para realizar raíces cuadradas hay que pasar el signo a SQRT. Además, dependiendo de donde
                // esté colocado el símbolo, debe realizar una operación u otra. Por ejemplo, si está simplemente la raiz, solo hará esta.
                // Sin embargo, si hay más operaciones además de la raíz cuadrada, tendrá que tener en cuenta todo.
                String raiz = operacion.substring(operacion.indexOf("√")+1);

                if(operacion.startsWith("√")){

                    if(raiz.startsWith("-")){
                        pantallaResultado.setText("ERROR");
                    } else {
                        expression = new Expression("SQRT("+raiz+")");
                    }

                } else if (operacion.contains("√")) {
                    String restoOp = operacion.substring(0, operacion.indexOf("√") - 1);
                    String elOperador = operacion.substring(operacion.indexOf("√") - 1, operacion.indexOf("√"));
                    if(raiz.startsWith("-")){
                        pantallaResultado.setText("ERROR");
                    } else{
                        expression = new Expression(restoOp + elOperador + "SQRT(" + raiz + ")");
                    }
                } else {
                    expression = new Expression(operacion);
                }

                result = expression.eval();

                double elResul = Double.parseDouble(result.toString());

                pantallaGuardado.setText(""+elResul);
                String miResul = pantallaGuardado.getText().toString();
                if(miResul.endsWith(".0")){
                    miResul = miResul.substring(0, miResul.length()-2);
                    pantallaGuardado.setText(miResul);
                }
            }
        } catch (Exception e){
        }
    }

    //MENU HISTORIAL
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.historial) {
            sacarHistorial(null);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void sacarHistorial(View v) {
        Intent i = new Intent(this, Historial.class);
        i.putExtra("miLista", operaciones);
        startActivity(i);
    }

}