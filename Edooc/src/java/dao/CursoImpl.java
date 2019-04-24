package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Curso;

public class CursoImpl extends Conexion implements ICurso {

    @Override
    public void registrar(Curso curso) throws Exception {
        String sql = "insert into Maestra.Curso values (IDCUR,NOMCUR)"
                + "values(?,?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, curso.getIdCur());
            ps.setString(2, curso.getNomCur());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en la inserción de datos" + e.getMessage());
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void modificar(Curso curso) throws Exception {
        String sql = "UPDATE Maestra.Curso values (NOMCUR=?, WHERE IDCUR=?)";

        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, curso.getNomCur());
            ps.setInt(2, curso.getIdCur());
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
    public void eliminar(Curso curso) throws Exception {
<<<<<<< HEAD
        String sql = "delete from MAESTRA.CURSO where IDCUR=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, curso.getIdCur());
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
    public List<Curso> listarCur() throws Exception {
        List<Curso> listado;
        Curso cur;
        String sql = "SELECT * FROM MAESTRA.CURSO";
=======
        String sql = "delete from MAESTRA.Curso where IDCUR=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, curso.getIdCur());
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
    public List<Curso> listarCur() throws Exception {
        List<Curso> listado;
        Curso cur;
        String sql = "SELECT * FROM MAESTRA.Curso";
>>>>>>> origin/master
        try {
            listado = new ArrayList();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                cur = new Curso();
                cur.setIdCur(rs.getInt("IDCUR"));
                cur.setNomCur(rs.getString("NOMCUR"));
                listado.add(cur);
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
