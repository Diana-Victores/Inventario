/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comercial.datos;


import Comercial.vista.Proceso_Inventario;
import Comercial.dominio.Inventario;
import com.sun.jdi.connect.spi.Connection;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author Diana
 */
public class InventarioDAO {

    private static final String SQL_SELECT = "SELECT PK_codigo_inventario, PK_codigo_producto, PK_codigo_bodega,"
            + "PK_codigo_existencia, PK_codigo_linea, PK_codigo_marca, PK_codigo_unidad, estatus_inventario"
            + " FROM tbl_inventario";
    private static final String SQL_INSERT = "INSERT INTO tbl_inventario (PK_codigo_inventario, estatus_inventario) "
            + "VALUES(?,?,?,?,?,?,?,?)";
    
    private static final String SQL_UPDATE = "UPDATE tbl_inventario SET  PK_codigo_inventario=?, estatus_inventario=? "
            + "WHERE PK_codigo_inventario";
    
    private static final String SQL_QUERY = "SELECT PK_codigo_inventario, estatus_inventario FROM tbl_inventario "
            + "WHERE PK_codigo_inventario=?";
    
    private static final String SQL_DELETE = "DELETE FROM tbl_inventario WHERE PK_codigo_inventario=?";

  
   public List<Inventario> select() {
        java.sql.Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Inventario invent = null;
        List<Inventario> inventarios = new ArrayList<Inventario>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                
                String PK_codigo_producto = rs.getString("PK_codigo_producto");
                String PK_codigo_bodega = rs.getString("PK_codigo_bodega");
                String PK_codigo_existencia = rs.getString("PK_codigo_existencia");
                String PK_codigo_linea = rs.getString("PK_codigo_linea");
                String PK_codigo_marca = rs.getString("PK_codigo_marca");
                String PK_codigo_unidad = rs.getString("PK_codigo_unidad");
                String estatus_inventario = rs.getString("estatus_inventario");
                
                invent = new Inventario();
                
                invent.setPK_codigo_producto(PK_codigo_producto);
                invent.setPK_codigo_bodega(PK_codigo_bodega);
                invent.setPK_codigo_existencia(PK_codigo_existencia);
                invent.setPK_codigo_linea(PK_codigo_linea);
                invent.setPK_codigo_marca(PK_codigo_marca);
                invent.setPK_codigo_unidad(PK_codigo_unidad);
                invent.setEstatus_inventario(estatus_inventario);
                        

                inventarios.add(invent); 
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return inventarios;
    }
    
    
}
