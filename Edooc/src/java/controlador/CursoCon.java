package controlador;

import dao.Impl.CursoImpl;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Curso;

@Named(value = "cursoCon")
@SessionScoped
public class CursoCon implements Serializable {
    
    private Curso curso;
    private CursoImpl dao;
    private List<Curso> listadoCur;

        public CursoCon() {
        dao = new CursoImpl();
        curso = new Curso();
        listadoCur = new ArrayList();
    }
    public void registrar ()throws Exception{
        try {
            dao.registrar(curso);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado","Completado"));                    
        } catch (Exception e) {
            throw e;
        }
    }
    public void modificar() throws Exception{
        try {
            dao.modificar(curso);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado","Modificado"));                    
        } catch (Exception e) {
            throw e;            
        }
    }
    public void eliminar() throws Exception{
        try {
            dao.eliminar(curso);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado","Eliminado..."));                                
        } catch (Exception e) {
            throw e;            
        }
    }
    public void listar()throws Exception{
        try {
            listadoCur = dao.listarCur();
        } catch (Exception e) {
            throw e;
        }
    }
//codigo generado
    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<Curso> getListadoCur() {
        return listadoCur;
    }

    public void setListadoCur(List<Curso> listadoCur) {
        this.listadoCur = listadoCur;
    }
    
}