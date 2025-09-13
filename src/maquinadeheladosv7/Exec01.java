package maquinadeheladosv7;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Exec01 {

    public static void main(String[] args) {

        String nombreDriver = "org.sqlite.JDBC";
        String direccionBD = "jdbc:sqlite:./base/baseDatos.db";
        
        //Cargamos el driver
        cargarDriver(nombreDriver,direccionBD);
        
        //INSTANCIAR MaQUINA
        MaquinaHelados maquina = new MaquinaHelados();

        Scanner sc = new Scanner(System.in);
        String opcionSeleccionada = "S";
        //Mientras no digan que quieren salir (Opcion S)
        do {
            //Muestro el menu
            mostrarMenu();
            //Leo la opcion
            opcionSeleccionada = leerOpcion();
            //Hago la acción que se corresponda  con la opcion elegida
            if (opcionSeleccionada.equals("1")) {
                //MOSTRAR HELADOS
                maquina.mostrarHelados();
            } else if (opcionSeleccionada.equals("2")) {
                //MONEDAS
                pillarDinero(maquina);
            } else if (opcionSeleccionada.equals("3")) {
                try {
                    // pedirHelados(maquina.getHelados(), maquina);
                    maquina.pedirHelados(sc);
                } catch (NotValidPositionException ex) {
                    System.out.println(ex.getMessage());
                } catch (QuantityExceededException ex) {
                    System.out.println(ex.getMessage());
                } catch (NotEnounghMoneyException ex) {
                    System.out.println(ex.getMessage());
                }
            } else if (opcionSeleccionada.equalsIgnoreCase("S")) {
                maquina.apagarMaquina();
            }

        } while (!opcionSeleccionada.equalsIgnoreCase("S"));
    }
    
    public static void cargarDriver(String nombreDriver, String direccionBD) {
        try {
            // Cargamos el driver
            Class.forName(nombreDriver);
            System.out.println("Driver cargado con éxito");
        } catch (Exception ex) {
            System.out.println("Error cargando el driver :"
                    + ex.getClass().getSimpleName() + ex.getMessage());
        }
    }

    public static void mostrarMenu() {
        System.out.println("------------Menú---------------");
        System.out.println("1.-Mostrar Helados");
        System.out.println("2.-Introducir monedas");
        System.out.println("3.-Pedir helado");
        System.out.println("S.-Salir y apagar la máquina de helados.");
        System.out.println("-------------------------------");
    }

    public static String leerOpcion() {
        String entrada;
        do {
            Scanner sc = new Scanner(System.in);
            System.out.print("Selecciona una opcion : ");
            entrada = sc.nextLine();
            System.out.println("-------------------------------");

        } while (!entrada.matches("[123sS]"));
        return entrada;
    }

    public static void mostrarMonedas() {
        System.out.println("A.- 0,05€");
        System.out.println("B.- 0,10€");
        System.out.println("C.- 0,20€");
        System.out.println("D.- 0,50€");
        System.out.println("E.- 1,00€");
        System.out.println("F.- 2,00€");
        System.out.println("S.- SALIR");
    }

    public static void pillarDinero(MaquinaHelados maquina) {
        // Iniciamos monedero
        mostrarMonedas();
        boolean programa = true;
        while (programa) {
            //Scan
            Scanner sc = new Scanner(System.in);
            System.out.print("\nIntroduce tu monedas: ");
            String respuesta = sc.nextLine();
            if (respuesta.equalsIgnoreCase("A")) {
                maquina.setMonedero(maquina.getMonedero() + 0.05);
            } else if (respuesta.equalsIgnoreCase("B")) {
                maquina.setMonedero(maquina.getMonedero() + 0.1);
            } else if (respuesta.equalsIgnoreCase("C")) {
                maquina.setMonedero(maquina.getMonedero() + 0.2);
            } else if (respuesta.equalsIgnoreCase("D")) {
                maquina.setMonedero(maquina.getMonedero() + 0.5);
            } else if (respuesta.equalsIgnoreCase("E")) {
                maquina.setMonedero(maquina.getMonedero() + 1);
            } else if (respuesta.equalsIgnoreCase("F")) {
                maquina.setMonedero(maquina.getMonedero() + 2);
            } else if (respuesta.equalsIgnoreCase("S")) {
                System.out.println("\n\tSaliendo....");
                programa = false;
                break;
            }
            System.out.printf("\nTienes en tu monedero virtual: %.2f €", maquina.getMonedero());
        }
    }


}
