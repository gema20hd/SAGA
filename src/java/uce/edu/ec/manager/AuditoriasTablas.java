/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uce.edu.ec.manager;

import ejb.LoggedActionsAdiministrativoFacadeLocal;
import ejb.LoggedActionsEstudianteFacade;
import ejb.LoggedActionsEstudianteFacadeLocal;
import ejb.LoggedActionsFacadeLocal;
import ejb.LoggedActionsProfesorFacadeLocal;
import entidades.LoggedActions;
import entidades.LoggedActionsAdiministrativo;
import entidades.LoggedActionsEstudiante;
import entidades.LoggedActionsProfesor;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author gemac
 */
@ManagedBean
@ViewScoped
public class AuditoriasTablas extends AbstractManagedBean {

    private LoggedActions audEmpleado;
    private List<LoggedActions> listaAuditoriaEmpleado;
    @EJB
    private LoggedActionsFacadeLocal adminAudiEmpleado;
    private LoggedActionsEstudiante audEstudiante;
    private List<LoggedActionsEstudiante> listaAuditoriaEstudiante;
    @EJB
    private LoggedActionsEstudianteFacadeLocal adminAudiestudiante;
    // ----------------
    private LoggedActionsAdiministrativo audAdministrador;
    private List<LoggedActionsAdiministrativo> listaAuditoriaAdmin;
    @EJB
    private LoggedActionsAdiministrativoFacadeLocal adminAudiAdmin;
    //--------------------
    private LoggedActionsProfesor audProfesor;
    private List<LoggedActionsProfesor> listaAuditoriaProfesor;
    @EJB
    private LoggedActionsProfesorFacadeLocal adminAudiProfesor;

    public AuditoriasTablas() {
        this.listaAuditoriaEmpleado = new ArrayList<>();
        this.listaAuditoriaProfesor = new ArrayList<>();
        this.listaAuditoriaEstudiante = new ArrayList<>();
        this.listaAuditoriaAdmin = new ArrayList<>();

    }

    public LoggedActionsEstudiante getAudEstudiante() {
        return audEstudiante;
    }

    public void setAudEstudiante(LoggedActionsEstudiante audEstudiante) {
        this.audEstudiante = audEstudiante;
    }

    public List<LoggedActionsEstudiante> getListaAuditoriaEstudiante() {
        return listaAuditoriaEstudiante;
    }

    public void setListaAuditoriaEstudiante(List<LoggedActionsEstudiante> listaAuditoriaEstudiante) {
        this.listaAuditoriaEstudiante = listaAuditoriaEstudiante;
    }

    public LoggedActionsEstudianteFacadeLocal getAdminAudiestudiante() {
        return adminAudiestudiante;
    }

    public void setAdminAudiestudiante(LoggedActionsEstudianteFacadeLocal adminAudiestudiante) {
        this.adminAudiestudiante = adminAudiestudiante;
    }

    public LoggedActionsAdiministrativo getAudAdministrador() {
        return audAdministrador;
    }

    public void setAudAdministrador(LoggedActionsAdiministrativo audAdministrador) {
        this.audAdministrador = audAdministrador;
    }

    public List<LoggedActionsAdiministrativo> getListaAuditoriaAdmin() {
        return listaAuditoriaAdmin;
    }

    public void setListaAuditoriaAdmin(List<LoggedActionsAdiministrativo> listaAuditoriaAdmin) {
        this.listaAuditoriaAdmin = listaAuditoriaAdmin;
    }

    public LoggedActionsAdiministrativoFacadeLocal getAdminAudiAdmin() {
        return adminAudiAdmin;
    }

    public void setAdminAudiAdmin(LoggedActionsAdiministrativoFacadeLocal adminAudiAdmin) {
        this.adminAudiAdmin = adminAudiAdmin;
    }

    public LoggedActionsProfesor getAudProfesor() {
        return audProfesor;
    }

    public void setAudProfesor(LoggedActionsProfesor audProfesor) {
        this.audProfesor = audProfesor;
    }

    public List<LoggedActionsProfesor> getListaAuditoriaProfesor() {
        return listaAuditoriaProfesor;
    }

    public void setListaAuditoriaProfesor(List<LoggedActionsProfesor> listaAuditoriaProfesor) {
        this.listaAuditoriaProfesor = listaAuditoriaProfesor;
    }

    public LoggedActions getAudEmpleado() {
        return audEmpleado;
    }

    public void setAudEmpleado(LoggedActions audEmpleado) {
        this.audEmpleado = audEmpleado;
    }

    public List<LoggedActions> getListaAuditoriaEmpleado() {
        return listaAuditoriaEmpleado;
    }

    public void setListaAuditoriaEmpleado(List<LoggedActions> listaAuditoriaEmpleado) {
        this.listaAuditoriaEmpleado = listaAuditoriaEmpleado;
    }

    public LoggedActionsFacadeLocal getAdminAudiEmpleado() {
        return adminAudiEmpleado;
    }

    public void setAdminAudiEmpleado(LoggedActionsFacadeLocal adminAudiEmpleado) {
        this.adminAudiEmpleado = adminAudiEmpleado;
    }

    private void cargarAudiEmpleado() {
        try {
            this.listaAuditoriaEmpleado = adminAudiEmpleado.findAll();
        } catch (Exception e) {
            anadirMensajeError("No se pudo cargar la auditoria:" + e.getMessage());
        }
    }

    private void cargarAudiadministrador() {
        try {
            this.listaAuditoriaAdmin = adminAudiAdmin.findAll();
        } catch (Exception e) {
            anadirMensajeError("No se pudo cargar la auditoria:" + e.getMessage());
        }
    }

    private void cargarAudiEstudiante() {
        try {
            this.listaAuditoriaEstudiante = adminAudiestudiante.findAll();
        } catch (Exception e) {
            anadirMensajeError("No se pudo cargar la auditoria:" + e.getMessage());
        }
    }

    private void cargarAudiProfesor() {
        try {
            this.listaAuditoriaProfesor = adminAudiProfesor.findAll();
        } catch (Exception e) {
            anadirMensajeError("No se pudo cargar la auditoria:" + e.getMessage());
        }
    }

    @PostConstruct
    public void inicializar() {
        cargarAudiEmpleado();
        cargarAudiEstudiante();
        cargarAudiProfesor();
        cargarAudiadministrador();

    }
}
