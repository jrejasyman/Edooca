package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Estudiante;

public class EstudianteImpl extends Conexion implements IEstudiante {

    @Override
    public void Registrar(Estudiante estudiante) throws Exception {
        String INSERT = "INSERT INTO MAESTRA.ESTUDIANTE"
                + "(IDEST, NOMEST, APEEST, SEXEST, CELEST, DNIEST, PAREST, COREST, UBIGEO_IDUBI)"
                + "VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(INSERT);
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
        } catch (Exception e) {
            System.out.println("Error alingresar datos Impl" + e.getMessage());
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void Modificar(Estudiante estudiante) throws Exception {
        String UPDATE = "UPDATE MAESTRA.ESTUDIANTE SET NOMEST=?, APEEST=?, SEXEST=?, CELEST=?, DNIEST=?, PAREST=?, COREST=?, UBIGEO_IDUBI=? WHERE IDEST=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(UPDATE);
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
        } catch (Exception e) {
            System.out.println("error en update" + e.getMessage());
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void Eliminar(Estudiante estudiante) throws Exception {
        String DELETE = "delete from maestra.estudiante where idest=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(DELETE);
            ps.setInt(1, estudiante.getIdEstu());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en l aparte de elimanr de impl" + e.getMessage());
            throw e;
        }finally{
            this.cerrar();
        }
    }

    @Override
    public List<Estudiante> listarEst() throws Exception {
        List<Estudiante> listado;
        Estudiante estu;
        String sql ="SELECT * FROM MAESTRA.ESTUDIANTE" ;
        try {
            listado = new ArrayList() ;
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                estu = new Estudiante();
                estu.setIdEstu(rs.getInt("IDEST"));
                estu.setNomEstu(rs.getString("NOMEST"));
                estu.setApeEstu(rs.getString("APEEST"));
                estu.setSexEstu(rs.getString("SEXEST"));
                estu.setCelEstu(rs.getString("CELEST"));
                estu.setDniEstu(rs.getString("DNIEST"));
                estu.setParEstu(rs.getString("PAREST"));
                estu.setCorEstu(rs.getString("COREST"));
                estu.setUbiEstu(rs.getString("UBIGEO_IDUBI"));
                listado.add(estu);                
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            throw e;
            
        }finally{
            this.cerrar();
        }
        return listado;

    }

    

}
