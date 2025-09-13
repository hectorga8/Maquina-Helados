package maquinadeheladosv7;

import java.sql.*;
import java.util.*;

public class HeladoDAOImplementation implements HeladoDAO, AutoCloseable {

    static {
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

    public HeladoDAOImplementation() throws Exception {
        con = DriverManager.getConnection("jdbc:sqlite:./base/baseDatos.db");
    }

    @Override
    public List<Helado> getHelados() throws Exception {
        List<Helado> listaHelados = new ArrayList<>();
        String sql = "select * from helados";

        Helado helado;

        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql);) {
            while (rs.next()) {
                System.out.print(rs.getInt("posicion"));
                System.out.print(" :: " + rs.getString("nombre"));
                System.out.print(" :: " + rs.getFloat("precio"));
                System.out.print(" :: " + rs.getString("tipo"));
                System.out.print(" :: " + rs.getInt("cantidad") + "\n");
                
                helado = new Helado(rs.getInt("posicion"), rs.getString("nombre"),
                        rs.getFloat("precio"), rs.getString("tipo"),
                        rs.getInt("cantidad"));
                listaHelados.add(helado);
            }
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getClass().getSimpleName()
                    + " - " + ex.getMessage());
        }

        return listaHelados;
    }

    public Helado obtenerHelado(int posicion) throws Exception {
        Helado helado = null;
        //Si tengo el ResultSet iniciado arriba a Null, siempre hay que cerrarlo abajo
        ResultSet rs = null;

        String sql = "select * from helados where posicion = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, posicion);
            rs = stmt.executeQuery();
            while (rs.next()) {
                helado = new Helado(
                        rs.getInt("posicion"),
                        rs.getString("nombre"),
                        rs.getFloat("precio"),
                        rs.getString("tipo"),
                        rs.getInt("cantidad")
                );
            }

        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getClass().getSimpleName()
                    + " - " + ex.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
        }

        return helado;
    }

    public void updateCantidad(int cantidad, int posicion) throws Exception {

        Helado helado = null;
        //Si tengo el ResultSet iniciado arriba a Null, siempre hay que cerrarlo abajo
        ResultSet rs = null;
        
        String sql = "update helados set cantidad = ? where posicion = ?";
        
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, cantidad);
            stmt.setInt(2, posicion);
            int resultado = stmt.executeUpdate();
            

        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public void close() throws Exception {
        con.close();
    }
}
