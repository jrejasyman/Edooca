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
    
    private Estudiante estudiante = new Estudiante();
//    private EstudianteImpl dao;
    private List<Estudiante> listadoEst;
    private String accionEst;
    
    
     @PostConstruct
     public void init(){
         try {
             listar();
         } catch (Exception e) {
         }
         
    }    
     
     public void operarEstudiante() throws Exception {
        switch (accionEst) {
            case "Registrar":
                registrar();
                break;
            case "Modificar":
                modificar();
                break;
        }
    }
    
     public void limpiarEstudiante(){
         estudiante = new Estudiante();
     }
    public EstudianteCon() {
//        dao = new EstudianteImpl();
        estudiante = new Estudiante();
        listadoEst = new ArrayList();
        
    }
    
    public void registrar ()throws Exception{
        EstudianteImpl Conexion;
        try {
            Conexion= new EstudianteImpl();
            estudiante.setUbiEstu(Conexion.obtenerCodigoUbigeo(estudiante.getNomubigeo()));
            Conexion.registrar(estudiante);
            listar();
            limpiarEstudiante();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado","Completado"));                    
        } catch (Exception e) {
            throw e;
        }
    }
    public void modificar() throws Exception{
         EstudianteImpl Conexion;
        try {
            Conexion = new EstudianteImpl();
            estudiante.setUbiEstu(Conexion.obtenerCodigoUbigeo(estudiante.getNomubigeo()));
            Conexion.modificar(estudiante);
            listar();
            limpiarEstudiante();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Estudiante","Modificado"));                    
        } catch (Exception e) {
            throw e;            
        }
    }
    public void eliminar(Estudiante est) throws Exception{
        EstudianteImpl Conexion;
        try {
            Conexion = new EstudianteImpl();
            Conexion.eliminar(est);
            listar();
            limpiarEstudiante();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado","Eliminado..."));                                
        } catch (Exception e) {
            throw e;            
        }
    }
    public void leerEstudiante(String codigoAlumno) throws Exception {
        EstudianteImpl Conexion;
        try {
            Conexion = new EstudianteImpl();
            estudiante = Conexion.leerEstudiante(codigoAlumno);
            accionEst = "Modificar";
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

//    public EstudianteImpl getDao() {
//        return dao;
//    }
//
//    public void setDao(EstudianteImpl dao) {
//        this.dao = dao;
//    }
//    

    public String getAccionEst() {
        return accionEst;
    }

    public void setAccionEst(String accionEst) {
        this.accionEst = accionEst;
    }
}
