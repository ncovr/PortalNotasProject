package Vista;

import java.util.Scanner;
import Controlador.ControlPortal;

public class UIPortal {
    private static ControlPortal instanceCP;
    private static Scanner in;
    private static UIPortal instance;

    private UIPortal() {
    }

    public static UIPortal getInstance() {
        instanceCP = ControlPortal.getInstance();
        in = new Scanner(System.in);
        if (instance == null) {
            instance = new UIPortal();
        }
        return instance;
    }

    public void menu() throws Exception {
        System.out.println("Biemvenido a tu portal de notas :)");
        String opcion = "";
        do {
            System.out.println("\n____Menú Principal____");
            System.out.println("1. Crear una carrera");
            System.out.println("2. Crear un semestre");
            System.out.println("3. Crear un ramo");
            System.out.println("4. Crear una evaluacion");
            System.out.println("5. Añadir una nota");
            System.out.println("6. Borrar una nota");
            System.out.println("7. Guardar datos del sistema");
            System.out.println("8. Cargar datos en el sistema");
            System.out.println("9. Limpiar datos del sistema");
            System.out.println("10. Generar listados");
            System.out.println("11. Exportar datos a Excel (No disponible)");
            System.out.println("0. Salir");
            System.out.print("-> ");
            opcion = in.nextLine();
            switch (opcion) {
                case "1" -> crearCarrera();
                case "2" -> crearSemestre();
                case "3" -> crearRamo();
                case "4" -> crearEvaluacion();
                case "5" -> instanceCP.calificar();
                //case "6" -> eliminarCalificacion();
                case "7" -> instanceCP.guardarDatosSistema();
                case "8" -> recuperarDatos();
                //case "9" -> limpiarDatos();
                case "10" -> listados();
                default -> {
                    if (!opcion.equals("0")) opcion = casoDefault(opcion);
                }
            }
        } while (!opcion.equals("0"));
    }

    private void crearCarrera() {
        System.out.print("Ingrese el nombre de su carrera: ");
        instanceCP.addCarrera(new Carrera(in.nextLine()));
        System.out.println("Creación exitosa...\n");
    }

    private void crearSemestre() {
        System.out.print("Ingrese el nombre de la carrera: ");
        Carrera carrera = instanceCP.getCarrera(in.nextLine());
        if (!(carrera == null)) {
            System.out.print("Ingrese el código del semestre (aaSS): ");
            carrera.addSemestre(new Semestre(in. nextInt()));
            in.nextLine();
            System.out.println("Creación exitosa...\n");
        } else {
            System.out.println("\nLa carrera no existe");
        }
    }

    private void crearRamo() {
        System.out.print("\nIngrese nombre carrera: ");
        Carrera carrera = instanceCP.getCarrera(in.nextLine());
        if (!(carrera == null)) {
            System.out.print("Ingrese codigo del semestre: ");
            Semestre semestre = carrera.getSemestre(in.nextInt());
            in.nextLine();
            if (!(semestre == null)) {
                System.out.print("Ingrese nombre del ramo: ");
                String nombre = in.nextLine();
                System.out.print("¿Es modular? [S/N]: ");
                String modular = in.nextLine();
                do {
                    if (modular.equalsIgnoreCase("S")) {
                        semestre.addRamo(new Ramo(nombre, true));
                        System.out.println("Creación exitosa...\n");
                    } else {
                        if (modular.equalsIgnoreCase("N")) {
                            semestre.addRamo(new Ramo(nombre, false));
                            System.out.println("Creación exitosa...\n");
                        } else {
                            System.out.print("Seleccione una opcion válida: ");
                            modular = in.nextLine();
                        }
                    }
                } while (!(modular.equalsIgnoreCase("S") || modular.equalsIgnoreCase("N")));
            } else {
                System.out.println("\nEl modulo no existe");
            }
        } else {
            System.out.println("\nLa carrera no existe");
        }
    }

    private void crearEvaluacion() {
        System.out.print("\nIngrese nombre carrera: ");
        Carrera carrera = instanceCP.getCarrera(in.nextLine());
        if (!(carrera == null)) {
            System.out.print("Ingrese codigo del semestre: ");
            Semestre semestre = carrera.getSemestre(in.nextInt());
            in.nextLine();
            if (!(semestre == null)) {
                System.out.print("Ingrese nombre del ramo: ");
                String nombre = in.nextLine();
                Ramo ramo = semestre.browseRamo(nombre);
                if (!(ramo == null)) {
                    System.out.print("Ingrese el nombre de la evaluacion: ");
                    nombre = in.nextLine();
                    System.out.print("Ingrese el tipo de evaluacion [test, certamen, proyecto, presentacion]: ");
                    String tipo = in.nextLine();
                    TipoEvaluacion tipoEv = null;
                    switch (tipo) {
                        case "test" -> tipoEv = TipoEvaluacion.TEST;
                        case "certamen" -> tipoEv = TipoEvaluacion.CERTAMEN;
                        case "proyecto" -> tipoEv = TipoEvaluacion.PROYECTO;
                        case "presentacion" -> tipoEv = TipoEvaluacion.PRESENTACION;
                    }
                    ramo.addEvaluacion(new Evaluacion(nombre, tipoEv));
                    System.out.println("Creación exitosa...\n");
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

    private static void recuperarDatos() throws Exception {
        System.out.println("Recuperando datos guardados...");
        try {
            instanceCP.cargarDatosSistema();
            instanceCP = ControlPortal.getInstance();
            System.out.println("Datos recuperados con exito, volviendo al menú...");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


    private void listados() {
        String op = "";
        do {
            System.out.println("\n____MenuListados____");
            System.out.println("1. Listar carreras");
            System.out.println("2. Regresar");
            System.out.println("0. Salir del programa");
            System.out.print("-> ");
            op = in.nextLine();
            switch (op) {
                case "1" -> {
                    String[] carreras = instanceCP.getCarreras();
                    if (carreras.length > 0) {
                        for (int i = 0; i < carreras.length; i++) {
                            System.out.println(carreras[i].toString());
                        }
                    } else {
                        System.out.println("\nNo existen carreras almacenadas");
                    }
                }
                case "2" -> op = "0";
                default -> {
                    if(casoDefault(op).equals("0")){
                        System.exit(0);
                    }
                }
            }
        } while (!op.equals("0"));
    }

    private String casoDefault(String opcion) {
        System.out.println("\nOpción inválida");
        System.out.print("¿Reintentar? [S/N]");
        String op = in.nextLine();
        if (!op.equalsIgnoreCase("S")) {
            return opcion = "0";
        }
        return "";
    }

}