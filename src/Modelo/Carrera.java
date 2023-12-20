package Modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class Carrera implements Serializable {
    private String nombre;
    private ArrayList<Semestre> semestres;

    public Carrera(String nombre) {
        this.nombre = nombre;
        this.semestres = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void addSemestre(Semestre semestre){
        semestres.add(semestre);
    }

    public void removeSemestre(Semestre semestre){
        semestres.remove(semestre);
    }

    public Semestre getSemestre(int codigo){
        for(Semestre s:semestres){
            if(s.getCodigo()==codigo){
                return s;
            }
        }
        return null;
    }

    public String toString(){
        return nombre;
    }

}
