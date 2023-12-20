package Modelo;

import java.util.ArrayList;

public class Modulo {
    private String nombre;
    private Ramo ramo;
    private ArrayList<Evaluacion> evaluaciones;
    private int anio;

    public Modulo(String nombre, Ramo ramo) {
        evaluaciones = new ArrayList<>();
        this.nombre = nombre;
        this.ramo = ramo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Evaluacion getEvaluacion(int index) {
        return evaluaciones.get(index);
    }

    public void removeEvaluacion(Evaluacion evaluacion) {
        evaluaciones.remove(evaluacion);
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }
}
