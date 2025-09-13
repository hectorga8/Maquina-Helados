
package maquinadeheladosv7;



public class Helado {
    private int posicion;
    private String nombre;
    private double precio;
    private String tipo;
    private int cantidad;
    
    //METODOS
    
    //CONSTRUCTOR
    public Helado(int posicion, String nombre, double precio, String tipo,int cantidad) {
        this.posicion = posicion;
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
        this.cantidad = cantidad;
    }
    
    
    //TO STRING
    @Override
    public String toString() {
        return posicion + " :: " + nombre + " :: " + precio + " :: " + tipo + " :: " + cantidad;
    }
    
    
    //GETTERS AND SETTERS
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    } 

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
}
