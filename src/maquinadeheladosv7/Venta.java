/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package maquinadeheladosv7;

import java.security.Timestamp;
import java.time.LocalDate;

/**
 *
 * @author hgome
 */
public class Venta {
    private int id;
    private String fecha_hora;
    private String posicion;
    
    
    //constructor

    public Venta(int id, String fecha_hora, String posicion) {
        this.id = id;
        this.fecha_hora = fecha_hora;
        this.posicion = posicion;
    }
    
    //toString

    @Override
    public String toString() {
        return "Venta{" + "id=" + id + ", fecha_hora=" + fecha_hora + ", posicion=" + posicion + '}';
    }
    
    //Gett sett

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(String fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }
}
