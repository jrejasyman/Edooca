package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Personal;

public class PersonalImpl extends Conexion implements IPersonal {

    @Override
    public void registrar(Personal personal) throws Exception {
        String sql = "insert into Maestra.Personal values (IDPER,NOMPER,APEPER,CORPER,DNIPER,DOCPER,CELPER,UBIGEO_IDUBI)"
                + "values(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, personal.getIdPer());
            ps.setString(2, personal.getNomPer());
            ps.setString(3, personal.getApePer());
            ps.setString(4, personal.getCorPer());
            ps.setString(5, personal.getDniPer());
            ps.setString(6, personal.getDocPer());
            ps.setString(7, personal.getCelPer());
            ps.setString(8, personal.getUbiPer());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en la inserción de datos" + e.getMessage());
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void modificar(Personal personal) throws Exception {
        String sql = "UPDATE Maestra.Personal values (NOMPER=?,APEPER=?,CORPER=?,DNIPER=?,DOCPER=?,CELPER=?,UBIGEO_IDUBI=?, WHERE IDEST=?)";

        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, personal.getNomPer());
            ps.setString(2, personal.getApePer());
            ps.setString(3, personal.getCorPer());
            ps.setString(4, personal.getDniPer());
            ps.setString(5, personal.getDocPer());
            ps.setString(6, personal.getCelPer());
            ps.setString(7, personal.getUbiPer());
            ps.setInt(8, personal.getIdPer());
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
    public void eliminar(Personal personal) throws Exception {
        String sql = "delete from MAESTRA.PERSONAL where IDEST=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
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
        Personal per;
        String sql = "SELECT * FROM MAESTRA.PERSONAL";
        try {
            listado = new ArrayList();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                per = new Personal();
                per.setIdPer(rs.getInt("IDPER"));
                per.setNomPer(rs.getString("NOMPER"));
                per.setApePer(rs.getString("APEPER"));
                per.setCorPer(rs.getString("APEPER"));
                per.setDniPer(rs.getString("APEPER"));
                per.setDocPer(rs.getString("APEPER"));
                per.setCelPer(rs.getString("APEPER"));
                per.setUbiPer(rs.getString("UBIGEO_IDUBI"));
                listado.add(per);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            throw e;

        } finally {
            this.cerrar();
        }
        return listado;

    }

}
