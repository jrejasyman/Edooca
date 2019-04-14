package dao;

import java.sql.PreparedStatement;
import java.util.List;
import modelo.Estudiante;

public class EstudianteImpl extends Conexion implements IEstudiante<Estudiante> {

    @Override
    public void Registrar(Estudiante modelos) throws Exception {
        String INSERT = "INSERT INTO MAESTRA.ESTUDIANTE"
                + "(IDEST, NOMEST, APEEST, SEXEST, CELEST, DNIEST, PAREST, COREST, UBIGEO_IDUBI)"
                + "VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(INSERT);
            ps.setInt(1, modelos.getIdEstu());
            ps.setString(2, modelos.getNomEstu());
            ps.setString(3, modelos.getApeEstu());
            ps.setString(4, modelos.getSexEstu());
            ps.setString(5, modelos.getCelEstu());
            ps.setString(6, modelos.getDniEstu());
            ps.setString(7, modelos.getParEstu());
            ps.setString(8, modelos.getCorEstu());
            ps.setString(9, modelos.getUbiEstu());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error alingresar datos Impl" + e.getMessage());
            throw e;
        }finally{
            this.cerrar();
        }
    }

    @Override
    public void Modificar(Estudiante modelos) throws Exception {
            String UPDATE = "UPDATE MAESTRA.ESTUDIANTE SET NOMEST=?, APEEST=?, SEXEST=?, CELEST=?, DNIEST=?, PAREST=?, COREST=?, UBIGEO_IDUBI=? WHERE IDEST=?";
            try {
            
        } catch (Exception e) {
        }
    }

    @Override
    public void Eliminar(Estudiante modelos) throws Exception {

    }

    @Override
    public List<Estudiante> listar(Estudiante modelos) throws Exception {
        List<Estudiante> listado = null;
        return listado;
        

    }

}
