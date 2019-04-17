package controlador;

import dao.EstudianteImpl;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Estudiante;

@Named(value = "estudianteCon")
@SessionScoped
public class EstudianteCon implements Serializable {
    
    private Estudiante estudiante;
    private EstudianteImpl dao;
    private List<Estudiante> listadoEst;
    
    
    public EstudianteCon() {
        dao = new EstudianteImpl();
        estudiante = new Estudiante();
        listadoEst = new ArrayList();
    }
    public void registrar ()throws Exception{
        try {
            dao.registrar(estudiante);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado","Completado"));                    
        } catch (Exception e) {
            throw e;
        }
    }
    public void modificar() throws Exception{
        try {
            dao.modificar(estudiante);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado","Modificado"));                    
        } catch (Exception e) {
            throw e;            
        }
    }
    public void eliminar(Estudiante est) throws Exception{
        try {
            dao.eliminar(est);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado","Eliminado..."));                                
        } catch (Exception e) {
            throw e;            
        }
    }
    public void listar()throws Exception{
        try {
            listadoEst = dao.listarEst();
        } catch (Exception e) {
            throw e;
        }
    }
//codigo generado
    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public List<Estudiante> getListadoEst() {
        return listadoEst;
    }

    public void setListadoEst(List<Estudiante> listadoEst) {
        this.listadoEst = listadoEst;
    }
    
}
