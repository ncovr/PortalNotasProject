package Modelo;

import java.io.Serializable;
import java.util.Date;

public class Evaluacion implements Serializable {
    private TipoEvaluacion tipo;
    private String nombre;
    private double nota;
    private Date fecha;
    private Ramo ramo;

    public Evaluacion(String nombre, TipoEvaluacion tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoEvaluacion getTipo() {
        return tipo;
    }

    public void setTipo(TipoEvaluacion tipo) {
        this.tipo = tipo;
    }

    public Ramo getRamo() {
        return ramo;
    }

    public void setRamo(Ramo ramo) {
        this.ramo = ramo;
    }
}
