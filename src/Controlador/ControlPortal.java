package Controlador;

import Modelo.*;

import java.io.*;
import java.util.ArrayList;

public class ControlPortal implements Serializable{
    private static ControlPortal instance;
    private ArrayList<Carrera> carreras;

    public ControlPortal() {
        carreras = new ArrayList<>();
    }

    public static ControlPortal getInstance() {
        if (instance == null) {
            instance = new ControlPortal();
        }
        return instance;
    }

    public void addCarrera(Carrera carrera){
        carreras.add(carrera);
    }

    public void removeCarrera(Carrera carrera){
        carreras.remove(carrera);
    }

    public Carrera getCarrera(String nombre){
        for(Carrera c:carreras){
            if(c.getNombre().equals(nombre)){
                return c;
            }
        }
        return null;
    }

    public String[] getCarreras(){
        String[] carrerasS = new String[carreras.size()];
        for(int i=0;i<carreras.size();i++){
            carrerasS[i] = carreras.get(i).toString();
        }
        return carrerasS;
    }

    public void cargarDatosSistema() throws Exception {
        try (ObjectInputStream datos = new ObjectInputStream(new FileInputStream("DatosPortal.obj"))) {
            instance = (ControlPortal) datos.readObject();
            instance = ControlPortal.getInstance();
            datos.close();
            System.out.println("Datos del sistema recuperados correctamente.");
        } catch (IOException e) {
            throw new Exception("Error al recuperar los datos del sistema.");
        } catch (ClassNotFoundException e) {
            throw new Exception("Error! No se pudo encontrar la clase al recuperar datos.");
        }
    }

    public void guardarDatosSistema() throws Exception {
        try (ObjectOutputStream datos = new ObjectOutputStream(new FileOutputStream("DatosPortal.obj"))) {
            datos.writeObject(instance);
            datos.close();
            System.out.println("Datos almacenados");
        } catch (IOException e) {
            System.out.println("Error al almacenar");
            throw e;
        }
    }

    public void calificar(String nombreCarrera, int codSemestre, String nombreRamo) {
        System.out.print("\nIngrese nombre carrera: ");
        Carrera carrera = instance.getCarrera(nombreCarrera);
        if (!(carrera == null)) {
            System.out.print("Ingrese codigo del semestre: ");
            Semestre semestre = carrera.getSemestre(codSemestre);
            if (!(semestre == null)) {
                System.out.print("Ingrese nombre del ramo: ");
                String nombre = nombreRamo;
                Ramo ramo = semestre.browseRamo(nombre);
                if (!(ramo == null)) {
                    System.out.print("Ingrese el nombre de la evaluacion: ");
                    nombre = in.nextLine();
                    Evaluacion evaluacion = ramo.browseEvaluacion(nombre);
                    if (!(evaluacion == null)) {
                        System.out.print("Ingrese la nota (Escala 1.0 a 7.0): ");
                        evaluacion.setNota(in.nextDouble());
                        in.nextLine();
                    } else {
                        System.out.println("\nLa evaluacion no existe");
                    }
                } else {
                    System.out.println("\nEl ramo no existe");
                }
            } else {
                System.out.println("\nEl modulo no existe");
            }
        } else {
            System.out.println("\nLa carrera no existe");
        }
    }

}
