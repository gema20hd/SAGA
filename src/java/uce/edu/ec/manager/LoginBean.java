/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uce.edu.ec.manager;

import ejb.AdministradorFacadeLocal;
import ejb.EmpleadoFacadeLocal;
import ejb.EstudianteFacadeLocal;
import ejb.ProfesorFacadeLocal;
import entidades.Administrador;
import entidades.Empleado;
import entidades.Estudiante;
import entidades.Profesor;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author gemac
 */
@ManagedBean
@ViewScoped
public class LoginBean implements Serializable {

    private Administrador administrador;
    private Estudiante estudiante;
    private Profesor profesor;
    private Empleado empleado;
    //7 validar Uusuario al ingreso
    private Empleado emp;
    private Profesor pro;
    private Estudiante estud;
    private Administrador admin;
    @EJB
    private EmpleadoFacadeLocal adminEmpleado;
    @EJB
    private ProfesorFacadeLocal adminProfesor;
    @EJB
    private EstudianteFacadeLocal adminEstudiante;
    @EJB
    private AdministradorFacadeLocal adminAdministrador;
    private List<Estudiante> listaEstudiante;
    private List<Profesor> listaprofesor;
    private List<Empleado> listaEmpleado;
    private List<Administrador> listaAdministrador;
    private String nombreUsr;
    private String claveUsr;

    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
        estudiante = new Estudiante();

    }

    public String getNombreUsr() {
        return nombreUsr;
    }

    public void setNombreUsr(String nombreUsr) {
        this.nombreUsr = nombreUsr;
    }

    public String getClaveUsr() {
        return claveUsr;
    }

    public void setClaveUsr(String claveUsr) {
        this.claveUsr = claveUsr;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public EmpleadoFacadeLocal getAdminEmpleado() {
        return adminEmpleado;
    }

    public void setAdminEmpleado(EmpleadoFacadeLocal adminEmpleado) {
        this.adminEmpleado = adminEmpleado;
    }

    public ProfesorFacadeLocal getAdminProfesor() {
        return adminProfesor;
    }

    public void setAdminProfesor(ProfesorFacadeLocal adminProfesor) {
        this.adminProfesor = adminProfesor;
    }

    public EstudianteFacadeLocal getAdminEstudiante() {
        return adminEstudiante;
    }

    public void setAdminEstudiante(EstudianteFacadeLocal adminEstudiante) {
        this.adminEstudiante = adminEstudiante;
    }

    public AdministradorFacadeLocal getAdminAdministrador() {
        return adminAdministrador;
    }

    public void setAdminAdministrador(AdministradorFacadeLocal adminAdministrador) {
        this.adminAdministrador = adminAdministrador;
    }

    public List<Estudiante> getListaEstudiante() {
        return listaEstudiante;
    }

    public void setListaEstudiante(List<Estudiante> listaEstudiante) {
        this.listaEstudiante = listaEstudiante;
    }

    public List<Profesor> getListaprofesor() {
        return listaprofesor;
    }

    public void setListaprofesor(List<Profesor> listaprofesor) {
        this.listaprofesor = listaprofesor;
    }

    public List<Empleado> getListaEmpleado() {
        return listaEmpleado;
    }

    public void setListaEmpleado(List<Empleado> listaEmpleado) {
        this.listaEmpleado = listaEmpleado;
    }

    public List<Administrador> getListaAdministrador() {
        return listaAdministrador;
    }

    public void setListaAdministrador(List<Administrador> listaAdministrador) {
        this.listaAdministrador = listaAdministrador;
    }

    public String validarUsuario() {
        String redireccion = null;
        Estudiante estudianteIngreso;
        try {
            estudianteIngreso = adminEstudiante.iniciorEstudiante(getNombreUsr(), getClaveUsr());
            if (estudianteIngreso != null) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("estudiante", estudianteIngreso);
                redireccion = "vistaEstudiante?faces-redirect=true";
            } else {
                Profesor profesorIngreso;
                profesorIngreso = adminProfesor.inicioProfesor(getNombreUsr(), getClaveUsr());
                if (profesorIngreso != null) {
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("profesor", profesorIngreso);
                    redireccion = "vistaProfesor?faces-redirect=true";
                } else {
                    Empleado empleadoIngreso;
                    empleadoIngreso = adminEmpleado.inicioEmpleado(getNombreUsr(), getClaveUsr());
                    if (empleadoIngreso != null) {
                        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("empleado", empleadoIngreso);
                        redireccion = "vistaAdministrativo?faces-redirect=true";
                    } else {
                        Administrador administradorIngreso;
                        administradorIngreso = adminAdministrador.inicioSesionA(getNombreUsr(), getClaveUsr());
                        if (administradorIngreso != null) {
                            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("administrador", administradorIngreso);
                            redireccion = "vistaAdminSistema?faces-redirect=true";
                        } else {
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "AVISO", "ERROR. Credenciales erroneas"));
                        }
                    }
                }
            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "AVISO", "INGRESE CON UN USUARIO CORRECTO"));
        }
        return redireccion;

    }

    public void cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }

    @PostConstruct
    public void inicio() {
        estudiante = new Estudiante();
    }
}
