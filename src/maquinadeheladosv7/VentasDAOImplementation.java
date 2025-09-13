
package maquinadeheladosv7;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VentasDAOImplementation implements VentasDAO, AutoCloseable{
    
    static{
        try {
                String nombreDriver = "org.sqlite.JDBC";
                // Cargamos el driver
                Class.forName(nombreDriver);
                System.out.println("Driver cargado");
            } catch (Exception ex) {
                System.out.println("Error cargando el driver :"
                        + ex.getClass().getSimpleName() + ex.getMessage());
                System.exit(1);
            }
    }
    
    Connection con = null;
    
    public VentasDAOImplementation() throws Exception{
        con = DriverManager.getConnection("jdbc:sqlite:./base/baseDatos.db");
    }
    
   
    public double getVentaTotal() throws Exception {
       double total = 0;
        String sql = "select sum(precio) as precio from helados natural join venta";
        
        try(PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()){
            
            total = rs.getDouble("precio");
            
        } catch(Exception ex){
            throw ex;
        }
        
        return total;
    }
    
    public void setVenta(int posicion) throws Exception {
        String sql = "insert into venta(posicion) values (?)";
      
        try(PreparedStatement stmt = con.prepareStatement(sql)){           
           stmt.setInt(1, posicion);
           stmt.executeUpdate();
           
        } catch(Exception ex){
            throw ex;
        } 
    }
    
    

    @Override
    public void close() throws Exception {
        con.close();
    }

    @Override
    public ArrayList<Venta> getVentas() throws Exception {
        ArrayList<Venta> ventas = new ArrayList<>();
        int id;
        String posicion;
        String fecha_hora;
        
        String sql = "select id, fecha_hora, posicion from venta order by fecha_hora desc";
        
        try (PreparedStatement stmt = con.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();) {
            while (rs.next()) {
                id = rs.getInt("id");
                posicion = rs.getString("posicion");
                fecha_hora = rs.getString("fecha_hora");
                
                
                
                ventas.add(new Venta(id, fecha_hora, posicion));
            }
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getClass().getSimpleName() + " - "
                    + ex.getMessage());
        }
        
        return ventas;
        
    }
    
}
