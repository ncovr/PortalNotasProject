package Modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class Ramo implements Serializable {
    private ArrayList<Evaluacion> evaluaciones;
    private Modulo modulo;
    private boolean esModular;
    private String nombre;

    public Ramo(String nombre, boolean esModular) {
        this.nombre = nombre;
        this.esModular = esModular;
        this.evaluaciones = new ArrayList<>();
        if(!esModular){
            this.modulo = new Modulo("Modulo0", this);
        }
    }

    public void addEvaluacion(Evaluacion evaluacion){
        evaluaciones.add(evaluacion);
    }

    public void removeEvaluacion(Evaluacion evaluacion){
        evaluaciones.remove(evaluacion);
    }

    public Evaluacion browseEvaluacion(String nombre){
        for(Evaluacion e:evaluaciones){
            if(e.getNombre().equals(nombre)){
                return e;
            }
        }
        return null;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    public boolean isEsModular() {
        return esModular;
    }

    public void setEsModular(boolean esModular) {
        this.esModular = esModular;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
