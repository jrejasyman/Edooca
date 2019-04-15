package dao;

import java.sql.PreparedStatement;
import java.util.List;
import modelo.Apoderado;


public class ApoderadoImpl extends Conexion implements IApoderado{

    @Override
    public void registrar(Apoderado apoderado) throws Exception {
        String sql = "insert into Maestra.Apoderado values (IDAPO,NOMAPO,APEAPO,OCUAPO,DIRAPO,SEXAPO,DNIAPO,UBIGEO_IDUBI)"
                +"values(?,?,?,?,?,?,?,?)";
        try {
           PreparedStatement ps = this.conectar().prepareStatement (sql);
          ps.setInt(1,apoderado.getIdApo());
          ps.setString(2,apoderado.getNomApo());
          ps.setString(3,apoderado.getApeApo());
          ps.setString(4,apoderado.getOcuApo());
          ps.setString(5,apoderado.getDirapo());
          ps.setString(6,apoderado.getSexApo());
          ps.setString(7,apoderado.getDniApo());
          ps.setString(8,apoderado.getUbiApo());
          ps.executeUpdate();
          ps.close();
        } catch (Exception e) {
            System.out.println("Error en la inserción de datos" + e.getMessage());
        }
        finally {
            this.cerrar();
            }
    }

    @Override
    public void modificar(Apoderado apoderado) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Apoderado apoderado) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Apoderado> listarApo() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
