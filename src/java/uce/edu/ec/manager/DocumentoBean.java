/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uce.edu.ec.manager;

import ejb.DocumentoFacadeLocal;
import ejb.EmpleadoFacadeLocal;
import ejb.EstudianteFacadeLocal;
import ejb.ProfesorFacadeLocal;
import ejb.TipoDocumentoFacadeLocal;
import entidades.Documento;
import entidades.Empleado;
import entidades.Estudiante;
import entidades.Profesor;
import entidades.TipoDocumento;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author gemac
 */
@ManagedBean
@ViewScoped
public class DocumentoBean extends AbstractManagedBean {

    private Empleado empleado;
    private Estudiante estudiante;
    private Profesor profesor;
    private Documento documentos;
    private TipoDocumento tipoDocumentos;
    private Documento selectDocumentos;
    private List<Empleado> listaEmpleado;
    private List<Estudiante> listaEstudiante;
    private List<Profesor> listaProfesor;
    private List<TipoDocumento> listaTipoDocumentos;
    private List<Documento> listaDocumentos;
    @EJB
    private DocumentoFacadeLocal adminDocumento;
    @EJB
    private TipoDocumentoFacadeLocal adminTipoDocumento;
    @EJB
    private ProfesorFacadeLocal adminProfesor;
    @EJB
    private EmpleadoFacadeLocal adminEmpleado;
    @EJB
    private EstudianteFacadeLocal adminEstudiante;

    /**
     * Creates a new instance of DocumentoBean
     */
    public DocumentoBean() {
        this.documentos = new Documento();
        this.listaDocumentos = new ArrayList<>();
        this.listaTipoDocumentos = new ArrayList<>();
//        this.listaEmpleado = new ArrayList<>();
//        this.listaEstudiante = new ArrayList<>();
//        this.listaProfesor = new ArrayList<>();
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
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

    public TipoDocumento getTipoDocumentos() {
        return tipoDocumentos;
    }

    public void setTipoDocumentos(TipoDocumento tipoDocumentos) {
        this.tipoDocumentos = tipoDocumentos;
    }

    public List<Empleado> getListaEmpleado() {
        return listaEmpleado;
    }

    public void setListaEmpleado(List<Empleado> listaEmpleado) {
        this.listaEmpleado = listaEmpleado;
    }

    public List<Estudiante> getListaEstudiante() {
        return listaEstudiante;
    }

    public void setListaEstudiante(List<Estudiante> listaEstudiante) {
        this.listaEstudiante = listaEstudiante;
    }

    public List<Profesor> getListaProfesor() {
        return listaProfesor;
    }

    public void setListaProfesor(List<Profesor> listaProfesor) {
        this.listaProfesor = listaProfesor;
    }

    public ProfesorFacadeLocal getAdminProfesor() {
        return adminProfesor;
    }

    public void setAdminProfesor(ProfesorFacadeLocal adminProfesor) {
        this.adminProfesor = adminProfesor;
    }

    public EmpleadoFacadeLocal getAdminEmpleado() {
        return adminEmpleado;
    }

    public void setAdminEmpleado(EmpleadoFacadeLocal adminEmpleado) {
        this.adminEmpleado = adminEmpleado;
    }

    public EstudianteFacadeLocal getAdminEstudiante() {
        return adminEstudiante;
    }

    public void setAdminEstudiante(EstudianteFacadeLocal adminEstudiante) {
        this.adminEstudiante = adminEstudiante;
    }

    public Documento getDocumentos() {
        return documentos;
    }

    public void setDocumentos(Documento documentos) {
        this.documentos = documentos;
    }

//    public TipoDocumento getTipoDocumentos() {
//        return tipoDocumentos;
//    }
//
//    public void setTipoDocumentos(TipoDocumento tipoDocumentos) {
//        this.tipoDocumentos = tipoDocumentos;
//    }
    public Documento getSelectDocumentos() {
        return selectDocumentos;
    }

    public void setSelectDocumentos(Documento selectDocumentos) {
        this.selectDocumentos = selectDocumentos;
    }

    public List<Documento> getListaDocumentos() {
        return listaDocumentos;
    }

    public void setListaDocumentos(List<Documento> listaDocumentos) {
        this.listaDocumentos = listaDocumentos;
    }

    public List<TipoDocumento> getListaTipoDocumentos() {
        return listaTipoDocumentos;
    }

    public void setListaTipoDocumentos(List<TipoDocumento> listaTipoDocumentos) {
        this.listaTipoDocumentos = listaTipoDocumentos;
    }

    public DocumentoFacadeLocal getAdminDocumento() {
        return adminDocumento;
    }

    public void setAdminDocumento(DocumentoFacadeLocal adminDocumento) {
        this.adminDocumento = adminDocumento;
    }

    public TipoDocumentoFacadeLocal getAdminTipoDocumento() {
        return adminTipoDocumento;
    }

    public void setAdminTipoDocumento(TipoDocumentoFacadeLocal adminTipoDocumento) {
        this.adminTipoDocumento = adminTipoDocumento;
    }

    public void seleccionarDocumento(SelectEvent ev) {
        this.selectDocumentos = (Documento) ev.getObject();
    }

    //----------------------------------------------------
    public void guardarDocumento() {
        try {
            if (documentos.getDocumentoId() == null || documentos.getDocumentoId() == 0) {
                adminDocumento.create(documentos);
                anadirMensajeInfo("Documento registrado exitosamente");
            } else {
                adminDocumento.edit(documentos);
                anadirMensajeInfo("documento actualizado exitosamente");
            }
            cargarDocumento();
            resetearFormularioDocumento();
        } catch (Exception e) {
            anadirMensajeError("No se pudo guardar el Documento:" + e.getMessage());
        }
    }

    public void editarDocumento() {
        if (selectDocumentos != null) {
            this.documentos = selectDocumentos;
        } else {
            anadirMensajeError("Se debe seleccionar un registro");
        }
    }

    public void eliminarDocumento() {
        try {
            if (selectDocumentos != null) {
                adminDocumento.remove(selectDocumentos);
                anadirMensajeInfo("documento eliminado correctamente");
                cargarDocumento();
                resetearFormularioDocumento();
            } else {
                anadirMensajeError("Se debe seleccionar un registro");
            }
        } catch (Exception e) {
            anadirMensajeError("No se pudo eliminar el registro:" + e.getMessage());
        }
    }

    public void resetearFormularioDocumento() {
        this.documentos = new Documento();
        this.selectDocumentos = null;
    }

    public void buscarDocumento() {
    }

    private void cargarDocumento() {
        try {
            this.listaDocumentos = adminDocumento.findAll();
        } catch (Exception e) {
            anadirMensajeError("No se pudo cargar los documentos:" + e.getMessage());
        }
    }

    private void cargarTipoDocumento() {
        try {
            this.listaTipoDocumentos = adminTipoDocumento.findAll();
        } catch (Exception e) {
            anadirMensajeError("No se pudo cargar los tipos de documentos:" + e.getMessage());
        }
    }

    private void cargarProfesor() {
        try {
            this.listaProfesor = adminProfesor.findAll();
        } catch (Exception e) {
            anadirMensajeError("No se pudo cargar los tipos de documentos:" + e.getMessage());
        }
    }

    private void cargarEmpleado() {
        try {
            this.listaEmpleado = adminEmpleado.findAll();
        } catch (Exception e) {
            anadirMensajeError("No se pudo cargar los tipos de documentos:" + e.getMessage());
        }
    }

    private void cargarEstudiante() {
        try {
            this.listaEstudiante = adminEstudiante.findAll();
        } catch (Exception e) {
            anadirMensajeError("No se pudo cargar los tipos de documentos:" + e.getMessage());
        }
    }

    @PostConstruct
    public void inicializar() {
        cargarDocumento();
        cargarTipoDocumento();
        cargarEmpleado();
        cargarEstudiante();
        cargarProfesor();

    }
}
