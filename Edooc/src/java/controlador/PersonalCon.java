package controlador;

import dao.PersonalImpl;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Personal;

@Named(value = "personalCon")
@SessionScoped
public class PersonalCon implements Serializable {

    private Personal personal;
    private PersonalImpl dao;
    private List<Personal> listadoPer;

    public PersonalCon() {
        dao = new PersonalImpl();
        personal = new Personal();
        listadoPer = new ArrayList();
    }

    public void registrar() throws Exception {
        try {
            dao.registrar(personal);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado", "Completado"));
        } catch (Exception e) {
            throw e;
        }
    }

    public void modificar() throws Exception {
        try {
            dao.modificar(personal);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado", "Modificado"));
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminar(Personal per) throws Exception {
        try {
            dao.eliminar(per);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado", "Eliminado..."));
        } catch (Exception e) {
            throw e;
        }
    }

    public void listar() throws Exception {
        try {
            listadoPer = dao.listarPer();
        } catch (Exception e) {
            throw e;
        }
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

}
