package controlador;

import dao.Impl.ApoderadoImpl;
import dao.Impl.EstudianteImpl;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Apoderado;

@Named(value = "apoderadoCon")
@SessionScoped
public class ApoderadoCon implements Serializable {
    
    private Apoderado apoderado;
    private ApoderadoImpl dao;
    private List<Apoderado> listadoApo;
    
    
    @PostConstruct
    public void init(){
        try {
            listar();
        } catch (Exception e) {
            System.out.println("error init Apoderado "+e.getMessage());
        }
    }
    
    public ApoderadoCon() {
        dao = new ApoderadoImpl();
        apoderado = new Apoderado();
        listadoApo = new ArrayList();
    }
    public void registrar ()throws Exception{
        try {
            dao = new ApoderadoImpl();
            apoderado.setUbiApo(dao.obtenerCodigoUbigeo(apoderado.getUbiApo()));
            dao.registrar(apoderado);
            listar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro","Completado"));                    
        } catch (Exception e) {
            throw e;
        }
    }
    public void modificar() throws Exception{
        try {
            dao = new ApoderadoImpl();            
            dao.modificar(apoderado);
            listar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro","Modificado"));                    
        } catch (Exception e) {
            throw e;            
        }
    }
    public void eliminar(Apoderado apo) throws Exception{
        try {
            dao = new ApoderadoImpl();
            dao.eliminar(apo);
            listar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro","Eliminado"));                                
        } catch (Exception e) {
            throw e;            
        }
    }
    public void listar()throws Exception{
        ApoderadoImpl Conexion;
        try {
            Conexion = new ApoderadoImpl();
            listadoApo = Conexion.listarApo();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public List<String> completeText (String query) throws SQLException, Exception{
        ApoderadoImpl Conexion = new ApoderadoImpl();
        return Conexion.autocompleteUbigeo(query);
    }
    
//codigo generado
    public Apoderado getApoderado() {
        return apoderado;
    }

    public void setApoderado(Apoderado apoderado) {
        this.apoderado = apoderado;
    }

    public List<Apoderado> getListadoApo() {
        return listadoApo;
    }

    public void setListadoApo(List<Apoderado> listadoApo) {
        this.listadoApo = listadoApo;
    }

    public ApoderadoImpl getDao() {
        return dao;
    }

    public void setDao(ApoderadoImpl dao) {
        this.dao = dao;
    }
    
}
