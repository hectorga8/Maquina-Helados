
package maquinadeheladosv7;

import java.util.*;

public interface HeladoDAO {
    public List<Helado> getHelados() throws Exception;

    
    public void updateCantidad(int cantidad, int posicion) throws Exception;
    
    
}
