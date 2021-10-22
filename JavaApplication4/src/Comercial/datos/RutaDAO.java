/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comercial.datos;

import Comercial.dominio.Bodega;
import Comercial.dominio.Ruta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Diana
 */
public class RutaDAO {
    
    
    private static final String SQL_SELECT = "SELECT PK_codigo_transporteruta, nombre_conductoruta, "
            + "nombre_transporteruta, tipo_transporteruta, ubicacion_transporteruta, direccion_transporteruta,"
            + "hora_salida_transporteruta, hora_entrada_transporteruta, estatus_transporteruta FROM tbl_transporteruta";
    
    private static final String SQL_INSERT = "INSERT INTO tbl_transporteruta (K_codigo_transporteruta, nombre_conductoruta, "
            + "nombre_transporteruta, tipo_transporteruta, ubicacion_transporteruta, direccion_transporteruta,"
            + "hora_salida_transporteruta, hora_entrada_transporteruta, estatus_transporteruta) VALUES(?,?,?,?,?,?,?,?,?)";
    
    private static final String SQL_UPDATE = "UPDATE tbl_transporteruta SET  PK_codigo_transporteruta, nombre_conductoruta= ?, "
            + "nombre_transporteruta= ?, tipo_transporteruta=?, ubicacion_transporteruta=?, direccion_transporteruta=?,"
            + "hora_salida_transporteruta=?, hora_entrada_transporteruta=? WHERE PK_codigo_bodega";
    
    private static final String SQL_QUERY = "SELECT PK_codigo_transporteruta, nombre_conductoruta, "
            + "nombre_transporteruta, tipo_transporteruta, ubicacion_transporteruta, direccion_transporteruta,"
            + "hora_salida_transporteruta, hora_entrada_transporteruta, estatus_transporteruta"
            + " FROM tbl_transporteruta WHERE PK_codigo_transporteruta=?";
    
    private static final String SQL_DELETE = "DELETE FROM tbl_transporteruta WHERE PK_codigo_transporteruta=?";

    public List<Ruta> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Ruta ruta = null;
        List<Ruta> rutas = new ArrayList<Ruta>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String PK_codigo_transporteruta = rs.getString("PK_codigo_transporteruta");
                String nombre_conductoruta = rs.getString("nombre_conductoruta");
                String nombre_transporteruta = rs.getString("nombre_transporteruta");
                String tipo_transporteruta = rs.getString("tipo_transporteruta");
                String ubicacion_transporteruta = rs.getString("ubicacion_transporteruta");
                
                bodega = new Bodega();
                bodega.setPKcodigoBodega(PK_codigo_bodega);
                bodega.setNombreBodega(nombreBodega);
                bodega.setEstatusBodega(estatusBodega);

                bodegas.add(bodega);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return bodegas;
    }

    public int insert(Bodega bodega) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, bodega.getPKcodigoBodega());
            stmt.setString(2, bodega.getNombreBodega());
            stmt.setString(3, bodega.getEstatusBodega());

            //System.out.println("ejecutando query:" + SQL_INSERT);
            rows = stmt.executeUpdate();
            //System.out.println("Registros afectados:" + rows);
            JOptionPane.showMessageDialog(null, "Registro Exitoso");
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public int update(Bodega bodega) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);

            stmt.setString(1, bodega.getPKcodigoBodega());
            stmt.setString(2, bodega.getNombreBodega());
            stmt.setString(3, bodega.getEstatusBodega());

            rows = stmt.executeUpdate();
            //System.out.println("Registros actualizado:" + rows);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public Bodega query(Bodega bodega) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Bodega> bodegas = new ArrayList<Bodega>();
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setString(1, bodega.getPKcodigoBodega());
            rs = stmt.executeQuery();

            while (rs.next()) {
                String PKcodigoBodega = rs.getString("PK_codigo_bodega");
                String nombreBodega = rs.getString("nombre_bodega");
                String estatusBodega = rs.getString("estatus_bodega");

                bodega = new Bodega();
                bodega.setPKcodigoBodega(PKcodigoBodega);
                bodega.setNombreBodega(nombreBodega);
                bodega.setEstatusBodega(estatusBodega);

                //empleados.add(empleado); // Si se utiliza un ArrayList
            }
            //System.out.println("Registros buscado:" + empleado);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        //return empleados;  // Si se utiliza un ArrayList
        return bodega;
    }

    public int delete(Bodega bodega) {

        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            //System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setString(1, bodega.getPKcodigoBodega());
            rows = stmt.executeUpdate();
            //System.out.println("Registros eliminados:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }
    
    
    
    
    
    
}
