package com.example.calculadoragrafica_luciax;

/**
 *
 * @author Lucia X. Peral
 */

public enum Niveles{
    LEVEL1(1,6, "+-"),
    LEVEL2(1,10, "*"),
    LEVEL3(1,10, "/");

    int numMin;
    int numMax;
    String operadores;

    Niveles(int numMin, int numMax, String operadores) {
        this.numMin = numMin;
        this.numMax = numMax;
        this.operadores = operadores;
    }

    public int getNumMin() {
        return numMin;
    }

    public int getNumMax() {
        return numMax;
    }

    public String getOperadores() {
        return operadores;
    }

}
