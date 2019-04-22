package controlador;

import dao.ApoderadoImpl;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Apoderado;

@Named(value = "apoderadoCon")
@SessionScoped
public class ApoderadoCon implements Serializable {
    
    private Apoderado apoderado;
    private ApoderadoImpl dao;
    private List<Apoderado> listadoApo;
    
    
    public ApoderadoCon() {
        dao = new ApoderadoImpl();
        apoderado = new Apoderado();
        listadoApo = new ArrayList();
    }
    public void registrar ()throws Exception{
        try {
            dao.registrar(apoderado);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro","Completado"));                    
        } catch (Exception e) {
            throw e;
        }
    }
    public void modificar() throws Exception{
        try {
            dao.modificar(apoderado);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro","Modificado"));                    
        } catch (Exception e) {
            throw e;            
        }
    }
    public void eliminar(Apoderado apo) throws Exception{
        try {
            dao.eliminar(apo);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro","Eliminado"));                                
        } catch (Exception e) {
            throw e;            
        }
    }
    public void listar()throws Exception{
        try {
            listadoApo = dao.listarApo();
        } catch (Exception e) {
            throw e;
        }
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
    
}
