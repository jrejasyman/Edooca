package dao.Impl;

import dao.Conexion;
import dao.IApoderado;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Apoderado;

public class ApoderadoImpl extends Conexion implements IApoderado {

    @Override
    public void registrar(Apoderado apoderado) throws Exception {
        String sql = "insert into MAESTRA.APODERADO ( NOMAPO, APEAPO, OCUAPO, DIRAPO, SEXAPO, CELAPO, DNIAPO, IDUBI)"
                + "values(? , ? , ? , ? , ? , ? , ? , ?)";
        try {
            PreparedStatement ps = this.getConectar().prepareStatement(sql);
            ps.setString(1, apoderado.getNomApo());
            ps.setString(2, apoderado.getApeApo());
            ps.setString(3, apoderado.getOcuApo());
            ps.setString(4, apoderado.getDirApo());
            ps.setString(5, apoderado.getSexApo());
            ps.setString(6, apoderado.getCelApo());
            ps.setString(7, apoderado.getDniApo());
            ps.setString(8, apoderado.getUbiApo());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al registrar" + e.getMessage());
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void modificar(Apoderado apoderado) throws Exception {
        String sql = "UPDATE MAESTRA.APODERADO SET NOMAPO=?, APEAPO=?, OCUAPO=?, DIRAPO=?, SEXAPO=?,CELAPO=?, DNIAPO=?, IDUBI=? WHERE IDAPO=?";
        try {
            this.Conexion();
            PreparedStatement ps = this.getConectar().prepareStatement(sql);
            ps.setString(1, apoderado.getNomApo());
            ps.setString(2, apoderado.getApeApo());
            ps.setString(3, apoderado.getOcuApo());
            ps.setString(4, apoderado.getDirApo());
            ps.setString(5, apoderado.getSexApo());
            ps.setString(6, apoderado.getCelApo());
            ps.setString(7, apoderado.getDniApo());
            ps.setString(8, apoderado.getUbiApo());
            ps.setInt(9, apoderado.getIdApo());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("error en update" + e.getMessage());
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void eliminar(Apoderado apoderado) throws Exception {
        String sql = "delete from MAESTRA.APODERADO where IDAPO=?";
        try {
            this.Conexion();
            PreparedStatement ps = this.getConectar().prepareStatement(sql);
            ps.setInt(1, apoderado.getIdApo());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al eliminar" + e.getMessage());
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public List<Apoderado> listarApo() throws Exception {
        List<Apoderado> listado;
        ResultSet rs;

        
        try {
            Conexion();
            String sql = "SELECT * FROM MAESTRA.APODERADO";
            listado = new ArrayList();
            Statement st = this.getConectar().createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Apoderado apo = new Apoderado();
                apo.setIdApo(rs.getInt("IDAPO"));
                apo.setNomApo(rs.getString("NOMAPO"));
                apo.setApeApo(rs.getString("APEAPO"));
                apo.setOcuApo(rs.getString("OCUAPO"));
                apo.setDirApo(rs.getString("DIRAPO"));
                apo.setSexApo(rs.getString("SEXAPO"));
                apo.setCelApo(rs.getString("CELAPO"));
                apo.setDniApo(rs.getString("DNIAPO"));
                apo.setUbiApo(rs.getString("IDUBI"));
                listado.add(apo);
            }
        } catch (Exception e) {
            throw e;

        } finally {
            this.cerrar();
        }
        return listado;

    }

    public String obtenerCodigoUbigeo(String Ubigeo) throws SQLException, Exception {
        this.Conexion();
        ResultSet rs;
        try {
            String sql = "Select idubi from maestra.ubigeo where concat(depubi,' ', proubi,' ',disubi)like ?";
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            ps.setString(1, Ubigeo);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("IDUBI");
            }
            return null;
        } catch (SQLException e) {
            throw e;
        }
    }

    public List<String> autocompleteUbigeo(String Cosulta) throws SQLException, Exception {
        this.Conexion();
        ResultSet rs;
        List<String> Lista;
        try {
            String sql = "Select CONCAT (DEPUBI,' ', PROUBI,' ',DISUBI) AS DISUBI FROM MAESTRA.UBIGEO WHERE DISUBI LIKE ?";
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            ps.setString(1, "%" + Cosulta + "%");
            Lista = new ArrayList<>();
            rs = ps.executeQuery();
           
            while (rs.next()) {
                Lista.add(rs.getString("disubi"));
            }
            return Lista;
        } catch (SQLException e) {
            throw e;
        }
        
    }

}
