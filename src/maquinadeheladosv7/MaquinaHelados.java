package maquinadeheladosv7;

import java.util.*;

public class MaquinaHelados {

    private double monedero = 0;
    //private double ingresos = 0;

    //CONSTRUCTOR
    public MaquinaHelados() {

    }

    public void mostrarHelados() {
        try (HeladoDAOImplementation muestraHelado = new HeladoDAOImplementation();) {
            ArrayList<Helado> helados = (ArrayList) muestraHelado.getHelados();

            for (Helado h : helados) {
                System.out.println(h);
            }
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getClass().getSimpleName() + " - "
                    + ex.getMessage());
        }

    }

    public void pedirHelados(Scanner sc) throws NotValidPositionException, QuantityExceededException, NotEnounghMoneyException {
        //Pido que eliga la posicion del helado que quiere
        String posicion;
        Helado helado = null;

        //PIDO LOS DATOS PARA EL FILTRO
        do {
            System.out.print("Introduce la posicion del helado que deseas: ");
            posicion = sc.nextLine(); //ENVIAR POSICION AL METODO DEL MAIN
            try (HeladoDAOImplementation h = new HeladoDAOImplementation();) {
                helado = h.obtenerHelado(Integer.parseInt(posicion));

                if (helado == null) {
                    throw new NotValidPositionException("Por favor, introduce una posicion valida" + " has puesto " + posicion);
                }
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        } while (helado == null);

        double precio = 0;
       
        if (helado.getCantidad() <= 0) {
            System.out.println("------------------");
            throw new QuantityExceededException("No quedan helados.");
        } else if (this.monedero >= helado.getPrecio()) {
            System.out.println("Aqui esta su helado");

            this.monedero -= helado.getPrecio();
            try (HeladoDAOImplementation h = new HeladoDAOImplementation(); VentasDAOImplementation v = new VentasDAOImplementation()) {
                v.setVenta(Integer.parseInt(posicion));
                h.updateCantidad(helado.getCantidad() - 1,Integer.parseInt(posicion));
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());

            }
            
        }
        else{
            throw new NotEnounghMoneyException("Dinero insuficiente, te faltan: "
                    + (helado.getPrecio() - this.monedero) + " euros "
                    + "por favor, introduce la cantidad adecuada \n");
        }
        System.out.println("");

    }
    
    public void apagarMaquina(){
        
        if(this.monedero > 0){
            System.out.printf("Toma tu cambio: %.2f euros \n", this.monedero);
        }
        
        try(VentasDAOImplementation v  = new VentasDAOImplementation();){
            System.out.printf("Dinero total recaudado por la maquina: %.2f euros \n", v.getVentaTotal());
        }
        catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
    }


    //GETTERS AND SETTERS
    public double getMonedero() {
        return monedero;
    }

    public void setMonedero(double monedero) {
        this.monedero = monedero;
    }


}
