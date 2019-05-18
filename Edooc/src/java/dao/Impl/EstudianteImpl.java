package dao.Impl;

import dao.Conexion;
import dao.IEstudiante;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import modelo.Estudiante;

public class EstudianteImpl extends Conexion implements IEstudiante {

    Format fechaFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public void registrar(Estudiante estudiante) throws Exception {
        try {
            String sql = "INSERT INTO MAESTRA.ESTUDIANTE"
                + "( NOMEST, APEEST, FECHNAC , DNIEST, PAREST, COREST, IDUBI)"
                + " values (?,?,CONVERT(date,?,103),?,?,?,?)";
            PreparedStatement ps = this.getConectar().prepareStatement(sql);
            ps.setString(1, estudiante.getNomEstu());
            ps.setString(2, estudiante.getApeEstu());
            ps.setString(3, estudiante.getDateEstu());
            ps.setString(4, estudiante.getDniEstu());
            ps.setString(5, estudiante.getParEstu());
            ps.setString(6, estudiante.getCorEstu());
            ps.setString(7, estudiante.getUbiEstu());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al ingresar datos Impl" + e.getMessage());
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public Estudiante leerEstudiante(String codigoEstudiante) throws Exception{
        Estudiante est = null;
        ResultSet rs;
        try {
            Conexion();
            String sql="select idest, nomest, APEEST,FORMAT(Convert(DATE,FECHNAC,"
                    + "105),'dd/MM/yyyy','en-gb') as FECHNAC, DNIEST, PAREST,COREST,MAESTRA.UBIGEO.IDUBI, "
                    + "concat(MAESTRA.UBIGEO.DISUBI,' ',MAESTRA.UBIGEO.PROUBI,' ',MAESTRA.UBIGEO.DEPUBI) as nombreUbigeo "
                    + "from maestra.ESTUDIANTE inner join MAESTRA.UBIGEO on maestra.ESTUDIANTE.IDUBI = MAESTRA.UBIGEO.IDUBI WHERE IDEST = ?";
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            ps.setString(1, codigoEstudiante);
            rs = ps.executeQuery();
            if(rs.next()){
                est = new Estudiante();
                est.setIdEstu(rs.getString("idest"));
                est.setNomEstu(rs.getString("nomest"));
                est.setApeEstu(rs.getString("APEEST"));
                est.setDateEstu(rs.getString("FECHNAC"));
                est.setDniEstu(rs.getString("DNIEST"));
                est.setParEstu(rs.getString("PAREST"));
                est.setCorEstu(rs.getString("COREST"));
                est.setNomubigeo(rs.getString("nombreUbigeo"));
            }
        } catch (SQLException e) {
            throw e;
        }finally{
            cerrar();
        }
        return est;
    }
    
    @Override
    public void modificar(Estudiante estudiante) throws Exception {
        try {
            this.Conexion();
            String sql = "UPDATE MAESTRA.ESTUDIANTE SET NOMEST=?, APEEST=?, FECHNAC=CONVERT(date,?,103), DNIEST=?, PAREST=?, COREST=?, IDUBI=? WHERE IDEST=?";
            PreparedStatement ps = this.getConectar().prepareStatement(sql);
            ps.setString(1, estudiante.getNomEstu());
            ps.setString(2, estudiante.getApeEstu());
            ps.setString(3, estudiante.getDateEstu());
            ps.setString(4, estudiante.getDniEstu());
            ps.setString(5, estudiante.getParEstu());
            ps.setString(6, estudiante.getCorEstu());
            ps.setString(7, estudiante.getUbiEstu());
            ps.setString(8, estudiante.getIdEstu());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("error en update" + e.getMessage());
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void eliminar(Estudiante estudiante) throws Exception {
        try {
            this.Conexion();
            String sql = "delete from MAESTRA.ESTUDIANTE where IDEST=?";
            PreparedStatement ps = this.getConectar().prepareStatement(sql);
            ps.setString(1, estudiante.getIdEstu());
            ps.executeUpdate();
        } catch (SQLException e) {
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
            String sql = "select idest, nomest, APEEST,Convert(NVARCHAR(10),FECHNAC,105) as FECHNAC, DNIEST, PAREST,COREST,MAESTRA.UBIGEO.IDUBI, "
                    + "concat(MAESTRA.UBIGEO.DISUBI,' ',MAESTRA.UBIGEO.PROUBI,' ',MAESTRA.UBIGEO.DEPUBI) as nombreUbigeo from maestra.ESTUDIANTE "
                    + "inner join MAESTRA.UBIGEO on maestra.ESTUDIANTE.IDUBI = MAESTRA.UBIGEO.IDUBI";
            listado = new ArrayList();
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Estudiante estu = new Estudiante();
                estu.setIdEstu(rs.getString("idest"));
                estu.setNomEstu(rs.getString("NOMEST"));
                estu.setApeEstu(rs.getString("APEEST"));
                estu.setDateEstu(rs.getString("FECHNAC"));
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
