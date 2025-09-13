
package maquinadeheladosv7;

import java.util.ArrayList;
import java.util.List;

public interface VentasDAO {
    public double getVentaTotal() throws Exception;
    public void setVenta(int posicion) throws Exception;
    public ArrayList<Venta> getVentas() throws Exception;
}
