package dao.Impl;

import dao.Conexion;
import dao.IPersonal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Personal;

public class PersonalImpl extends Conexion implements IPersonal {

    @Override
    public void registrar(Personal personal) throws Exception {
        String INSERT = "INSERT INTO MAESTRA.PERSONAL"
                + "(IDPER, NOMPER, APEPER, CARPER, DNIPER, FDNPER, SEXPER, CORPER, CELPER)"
                + " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = this.getConectar().prepareStatement(INSERT);
            ps.setInt(1, personal.getIdPer());
            ps.setString(2, personal.getNomPer());
            ps.setString(3, personal.getApePer());
            ps.setString(4, personal.getCarPer());
            ps.setString(5, personal.getDniPer());
            ps.setDate(6, personal.getFdnPer());
            ps.setString(7, personal.getSexPer());
            ps.setString(8, personal.getCorPer());
            ps.setString(9, personal.getCelPer());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al ingresar datos Impl" + e.getMessage());
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void modificar(Personal personal) throws Exception {
        String sql = "UPDATE MAESTRA.PERSONAL SET NOMPER=?, APEPER=?, CARPER=?, DNIPER=?, FDNPER=?, SEXPER=?, CORPER=?, CELPER=?, WHERE IDPER=?";
        try {
            this.Conexion();
            PreparedStatement ps = this.getConectar().prepareStatement(sql);
            ps.setString(1, personal.getNomPer());
            ps.setString(2, personal.getApePer());
            ps.setString(3, personal.getCarPer());
            ps.setString(4, personal.getDniPer());
            ps.setDate(5, personal.getFdnPer());
            ps.setString(6, personal.getSexPer());
            ps.setString(7, personal.getCorPer());
            ps.setString(8, personal.getCelPer());
            ps.setInt(9, personal.getIdPer());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("error en update" + e.getMessage());
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void eliminar(Personal personal) throws Exception {
        String sql = "delete from MAESTRA.PERSONAL where IDPER=?";
        try {
            this.Conexion();
            PreparedStatement ps = this.getConectar().prepareStatement(sql);
            ps.setInt(1, personal.getIdPer());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en l aparte de eliminar de impl" + e.getMessage());
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public List<Personal> listarPer() throws Exception {
        List<Personal> listado;
        ResultSet rs;
        try {
            Conexion();
            String sql = "SELECT * FROM MAESTRA.PERSONAL";
            listado = new ArrayList();
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Personal per = new Personal();
                per.setIdPer(rs.getInt("IDPER"));
                per.setNomPer(rs.getString("NOMPER"));
                per.setApePer(rs.getString("APEPER"));
                per.setCarPer(rs.getString("CARPER"));
                per.setDniPer(rs.getString("DNIPER"));
                per.setFdnPer(rs.getDate("FDNPER"));
                per.setSexPer(rs.getString("SEXPER"));
                per.setCorPer(rs.getString("CORPER"));
                per.setCelPer(rs.getString("CELPER"));
                listado.add(per);
            }
        } catch (SQLException e) {
            throw e;

        } finally {
            this.cerrar();
        }
        return listado;

    }

}
