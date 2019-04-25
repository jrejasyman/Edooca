package controlador;

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
import modelo.Estudiante;

@Named(value = "estudianteCon")
@SessionScoped
public class EstudianteCon implements Serializable {
    
    private Estudiante estudiante;
    private EstudianteImpl dao;
    private List<Estudiante> listadoEst;
    
    
     @PostConstruct
     public void init(){
         try {
             listar();
         } catch (Exception e) {
             System.out.println("error init "+e.getMessage());
         }
         
    }    
    
    public EstudianteCon() {
        dao = new EstudianteImpl();
        estudiante = new Estudiante();
        listadoEst = new ArrayList();
    }
    
    public void registrar ()throws Exception{
        try {
            dao= new EstudianteImpl();
            estudiante.setUbiEstu(dao.obtenerCodigoUbigeo(estudiante.getUbiEstu()));
            dao.registrar(estudiante);
            listar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado","Completado"));                    
        } catch (Exception e) {
            throw e;
        }
    }
    public void modificar() throws Exception{
        try {
            dao = new EstudianteImpl();
            dao.modificar(estudiante);
            listar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado","Modificado"));                    
        } catch (Exception e) {
            throw e;            
        }
    }
    public void eliminar(Estudiante est) throws Exception{
        try {
            dao = new EstudianteImpl();
            dao.eliminar(est);
            listar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado","Eliminado..."));                                
        } catch (Exception e) {
            throw e;            
        }
    }
    public void listar()throws Exception{
        EstudianteImpl Conexion;
        try {
            Conexion = new EstudianteImpl();
            listadoEst = Conexion.listarEst();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public List<String> completeText(String query) throws SQLException, Exception {
        EstudianteImpl Conexion = new EstudianteImpl();
        return Conexion.autocompleteUbigeo(query);
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

    public EstudianteImpl getDao() {
        return dao;
    }

    public void setDao(EstudianteImpl dao) {
        this.dao = dao;
    }
    
}
