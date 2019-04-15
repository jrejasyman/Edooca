package controlador;

import dao.EstudianteImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Estudiante;

@Named(value = "estudianteC")
@SessionScoped
public class EstudianteC implements Serializable {

   
    private Estudiante estudiante;
    private EstudianteImpl dao;
    private List<Estudiante> listarEst;    
    
    public EstudianteC() {
        dao = new EstudianteImpl();
        estudiante = new Estudiante();
        listarEst = new ArrayList();
    }
    public void Registrar() throws Exception{
        try {
            dao.Registrar(estudiante);
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro","Completo..."));                                
        } catch (Exception e) {
            System.out.println("Error en controlador registrar "+e.getMessage());
            throw e;
        }
    }
    public void modificar() throws Exception{
        try {
            dao.Modificar(estudiante);
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualización","Completa..."));
        } catch (Exception e) {
            System.out.println("Error en el controlador "+e.getMessage());
            throw e;
        }
    }
    public void eliminar() throws Exception{
        try {
            dao.Eliminar(estudiante);
             FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Eliminación","Completa..."));
        } catch (Exception e) {
            System.out.println("Error en el controlador eliminar "+e.getMessage());
            throw e;
        }
    }

    public void listar()throws Exception{
        try {
            listarEst = dao.listarEst();
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

    public EstudianteImpl getDao() {
        return dao;
    }

    public void setDao(EstudianteImpl dao) {
        this.dao = dao;
    }

    public List<Estudiante> getListar() {
        return listarEst;
    }

    public void setListar(List<Estudiante> listar) {
        this.listarEst = listar;
    }
    
    
    
}
