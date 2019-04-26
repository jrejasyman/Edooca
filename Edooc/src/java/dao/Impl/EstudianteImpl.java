package dao.Impl;

import dao.Conexion;
import dao.IEstudiante;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Estudiante;

public class EstudianteImpl extends Conexion implements IEstudiante {

    @Override
    public void registrar(Estudiante estudiante) throws Exception {
        String INSERT = "INSERT INTO MAESTRA.ESTUDIANTE"
                + "(IDEST, NOMEST, APEEST, SEXEST, CELEST, DNIEST, PAREST, COREST, UBIGEO_IDUBI)"
                + " values (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = this.getConectar().prepareStatement(INSERT);
            ps.setInt(1, estudiante.getIdEstu());
            ps.setString(2, estudiante.getNomEstu());
            ps.setString(3, estudiante.getApeEstu());
            ps.setString(4, estudiante.getSexEstu());
            ps.setString(5, estudiante.getCelEstu());
            ps.setString(6, estudiante.getDniEstu());
            ps.setString(7, estudiante.getParEstu());
            ps.setString(8, estudiante.getCorEstu());
            ps.setString(9, estudiante.getUbiEstu());
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
    public void modificar(Estudiante estudiante) throws Exception {
        String sql = "UPDATE MAESTRA.ESTUDIANTE SET NOMEST=?, APEEST=?, SEXEST=?, CELEST=?, DNIEST=?, PAREST=?, COREST=?, UBIGEO_IDUBI=? WHERE IDEST=?";
        try {
            this.Conexion();
            PreparedStatement ps = this.getConectar().prepareStatement(sql);
            ps.setString(1, estudiante.getNomEstu());
            ps.setString(2, estudiante.getApeEstu());
            ps.setString(3, estudiante.getSexEstu());
            ps.setString(4, estudiante.getCelEstu());
            ps.setString(5, estudiante.getDniEstu());
            ps.setString(6, estudiante.getParEstu());
            ps.setString(7, estudiante.getCorEstu());
            ps.setString(8, estudiante.getUbiEstu());
            ps.setInt(9, estudiante.getIdEstu());
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
    public void eliminar(Estudiante estudiante) throws Exception {
        String sql = "delete from MAESTRA.ESTUDIANTE where IDEST=?";
        try {
            this.Conexion();
            PreparedStatement ps = this.getConectar().prepareStatement(sql);
            ps.setInt(1, estudiante.getIdEstu());
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
    public List<Estudiante> listarEst() throws Exception {
        List<Estudiante> listado;
        ResultSet rs;
        try {
            Conexion();
            String sql = "select idest, nomest, APEEST,SEXEST,CELEST,DNIEST, PAREST,COREST,UBIGEO_IDUBI, concat(MAESTRA.UBIGEO.DISUBI,' ',MAESTRA.UBIGEO.PROUBI,' ',MAESTRA.UBIGEO.DEPUBI) as nombreUbigeo from maestra.ESTUDIANTE inner join MAESTRA.UBIGEO on maestra.ESTUDIANTE.UBIGEO_IDUBI = MAESTRA.UBIGEO.IDUBI";
            listado = new ArrayList();
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Estudiante estu = new Estudiante();
                estu.setIdEstu(rs.getInt("IDEST"));
                estu.setNomEstu(rs.getString("NOMEST"));
                estu.setApeEstu(rs.getString("APEEST"));
                estu.setSexEstu(rs.getString("SEXEST"));
                estu.setCelEstu(rs.getString("CELEST"));
                estu.setDniEstu(rs.getString("DNIEST"));
                estu.setParEstu(rs.getString("PAREST"));
                estu.setCorEstu(rs.getString("COREST"));
                estu.setNomubigeo(rs.getString("nombreUbigeo"));
                listado.add(estu);
            }
        } catch (SQLException e) {
            throw e;

        } finally {
            this.cerrar();
        }
        return listado;

    }

    //codigo de autocomplete implemen
    public String obtenerCodigoUbigeo(String Ubigeo) throws SQLException, Exception {
        this.Conexion();
        ResultSet rs;
        try {
            String sql = "SELECT IDUBI FROM MAESTRA.UBIGEO WHERE CONCAT(DEPUBI,' ',PROUBI,' ', DISUBI) LIKE ?";
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

    public List<String> autocompleteUbigeo(String Consulta) throws SQLException, Exception {
        this.Conexion();
        ResultSet rs;
        List<String> Lista;
        try {
            String sql = "SELECT CONCAT(DEPUBI,' ',PROUBI,' ',DISUBI) AS DISUBI FROM MAESTRA.UBIGEO WHERE DISUBI LIKE ?";
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            ps.setString(1, "%" + Consulta + "%");
            Lista = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {

                Lista.add(rs.getString("DISUBI"));
            }
            return Lista;
        } catch (SQLException e) {
            throw e;
        }
    }

}
