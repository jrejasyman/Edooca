package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Apoderado;

public class ApoderadoImpl extends Conexion implements IApoderado {

    @Override
    public void registrar(Apoderado apoderado) throws Exception {
        String sql = "insert into MAESTRA.APODERADO values (IDAPO,NOMAPO,APEAPO,OCUAPO,DIRAPO,SEXAPO,DNIAPO,UBIGEO_IDUBI)"
                + "values(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, apoderado.getIdApo());
            ps.setString(2, apoderado.getNomApo());
            ps.setString(3, apoderado.getApeApo());
            ps.setString(4, apoderado.getOcuApo());
            ps.setString(5, apoderado.getDirApo());
            ps.setString(6, apoderado.getSexApo());
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
        String sql = "UPDATE MAESTRA.APODERADO SET NOMAPO=?, APEAPO=?, OCUAPO=?, DIRAPO=?, SEXAPO=?, DNIAPO=?, UBIGEO_IDUBI=? WHERE IDAPO=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, apoderado.getNomApo());
            ps.setString(2, apoderado.getApeApo());
            ps.setString(3, apoderado.getOcuApo());
            ps.setString(4, apoderado.getDirApo());
            ps.setString(5, apoderado.getSexApo());
            ps.setString(6, apoderado.getDniApo());
            ps.setString(7, apoderado.getUbiApo());
            ps.setInt(8, apoderado.getIdApo());
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
            PreparedStatement ps = this.conectar().prepareStatement(sql);
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
        Apoderado apo;
        String sql = "SELECT * FROM MAESTRA.APODERADO";
        try {
            listado = new ArrayList();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                apo = new Apoderado();
                apo.setIdApo(rs.getInt("IDAPO"));
                apo.setNomApo(rs.getString("NOMAPO"));
                apo.setApeApo(rs.getString("APEAPO"));
                apo.setOcuApo(rs.getString("OCUAPO"));
                apo.setDirApo(rs.getString("DIRAPO"));
                apo.setSexApo(rs.getString("SEXAPO"));
                apo.setDniApo(rs.getString("DNIAPO"));
                apo.setUbiApo(rs.getString("UBIGEO_IDUBI"));
                listado.add(apo);
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
