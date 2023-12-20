package Modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class Semestre implements Serializable {
    private int codigo;
    private ArrayList<Ramo> ramos;

    public Semestre(int codigo) {
        this.codigo = codigo;
        ramos = new ArrayList<>();
    }

    public int getCodigo(){
        return codigo;
    }

    public void addRamo(Ramo ramo){
        ramos.add(ramo);
    }

    public Ramo getRamo(int index){
        return ramos.get(index);
    }

    public Ramo browseRamo(String nombre){
        for(Ramo r:ramos){
            if(r.getNombre().equals(nombre)){
                return r;
            }
        }
        return null;
    }
}
