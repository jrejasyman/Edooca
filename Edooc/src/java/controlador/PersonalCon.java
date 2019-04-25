package controlador;

import dao.Impl.PersonalImpl;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Personal;

@Named(value = "personalCon")
@SessionScoped
public class PersonalCon implements Serializable {

    private Personal personal;
    private PersonalImpl dao;
    private List<Personal> listadoPer;

    @PostConstruct
    public void init() {
        try {
            listar();
        } catch (Exception e) {
            System.out.println("error init " + e.getMessage());
        }

    }

    public PersonalCon() {
        dao = new PersonalImpl();
        personal = new Personal();
        listadoPer = new ArrayList();
    }

    public void registrar() throws Exception {
        try {
            dao = new PersonalImpl();
            dao.registrar(personal);
            listar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado", "Completado"));
        } catch (Exception e) {
            throw e;
        }
    }

    public void modificar() throws Exception {
        try {
            dao = new PersonalImpl();
            dao.modificar(personal);
            listar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado", "Modificado"));
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminar(Personal per) throws Exception {
        try {
            dao = new PersonalImpl();
            dao.eliminar(per);
            listar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado", "Eliminado..."));
        } catch (Exception e) {
            throw e;
        }
    }

    public void listar() throws Exception {
        PersonalImpl Conexion;
        try {
            Conexion = new PersonalImpl();
            listadoPer = Conexion.listarPer();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<String> completeText(String query) throws SQLException, Exception {
        PersonalImpl Conexion = new PersonalImpl();
        return null;
    }

//codigo generado
    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public List<Personal> getListadoPer() {
        return listadoPer;
    }

    public void setListadoPer(List<Personal> listadoPer) {
        this.listadoPer = listadoPer;
    }

    public PersonalImpl getDao() {
        return dao;
    }

    public void setDao(PersonalImpl dao) {
        this.dao = dao;
    }

}
