/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uce.edu.ec.manager;

import convertidor.Usuario;
import ejb.CarreraMateriaProfesorFacadeLocal;
import ejb.DocumentoFacadeLocal;
import ejb.FichaProfesorFacadeLocal;
import ejb.MateriaFacadeLocal;
import ejb.ProfesorFacadeLocal;
import ejb.TipoDocumentoFacadeLocal;
import entidades.CarreraMateriaProfesor;
import entidades.Documento;
import entidades.FichaProfesor;
import entidades.Materia;
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
public class ProfesorBean extends AbstractManagedBean {

    Usuario usuario = new Usuario();
    private Materia materia;
    private TipoDocumento tipoDocumentos;
    private CarreraMateriaProfesor carreraProfe;
    private Profesor profesor;
    private Profesor selectProfesor;
    private List<Materia> listaMat;
    private List<TipoDocumento> listaTipoDocument;
    private List<CarreraMateriaProfesor> listaMatProfe;
    private List<Profesor> listaProfesor;
    private List<FichaProfesor> listaFichaProfesor;
    private List<Documento> listaDocumentos;
    @EJB
    private MateriaFacadeLocal adminMateria;
    @EJB
    private TipoDocumentoFacadeLocal adminTipoDoc;
    @EJB
    private FichaProfesorFacadeLocal adminFichaEstudiante;
    @EJB
    private DocumentoFacadeLocal adminDocumentoo;
    @EJB
    private ProfesorFacadeLocal adminProfesor;
    @EJB
    private CarreraMateriaProfesorFacadeLocal adminMatProfe;

    /**
     * Creates a new instance of ProfesorBean
     */
    public ProfesorBean() {
        this.profesor = new Profesor();
        this.listaProfesor = new ArrayList<>();
        this.listaDocumentos = new ArrayList<>();
        this.listaFichaProfesor = new ArrayList<>();
        this.listaMatProfe = new ArrayList<>();

    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public CarreraMateriaProfesor getCarreraProfe() {
        return carreraProfe;
    }

    public void setCarreraProfe(CarreraMateriaProfesor carreraProfe) {
        this.carreraProfe = carreraProfe;
    }

    public List<CarreraMateriaProfesor> getListaMatProfe() {
        return listaMatProfe;
    }

    public void setListaMatProfe(List<CarreraMateriaProfesor> listaMatProfe) {
        this.listaMatProfe = listaMatProfe;
    }

    public MateriaFacadeLocal getAdminMateria() {
        return adminMateria;
    }

    public void setAdminMateria(MateriaFacadeLocal adminMateria) {
        this.adminMateria = adminMateria;
    }

    public CarreraMateriaProfesorFacadeLocal getAdminMatProfe() {
        return adminMatProfe;
    }

    public void setAdminMatProfe(CarreraMateriaProfesorFacadeLocal adminMatProfe) {
        this.adminMatProfe = adminMatProfe;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<FichaProfesor> getListaFichaProfesor() {
        return listaFichaProfesor;
    }

    public void setListaFichaProfesor(List<FichaProfesor> listaFichaProfesor) {
        this.listaFichaProfesor = listaFichaProfesor;
    }

    public List<Documento> getListaDocumentos() {
        return listaDocumentos;
    }

    public void setListaDocumentos(List<Documento> listaDocumentos) {
        this.listaDocumentos = listaDocumentos;
    }

    public FichaProfesorFacadeLocal getAdminFichaEstudiante() {
        return adminFichaEstudiante;
    }

    public void setAdminFichaEstudiante(FichaProfesorFacadeLocal adminFichaEstudiante) {
        this.adminFichaEstudiante = adminFichaEstudiante;
    }

    public DocumentoFacadeLocal getAdminDocumentoo() {
        return adminDocumentoo;
    }

    public void setAdminDocumentoo(DocumentoFacadeLocal adminDocumentoo) {
        this.adminDocumentoo = adminDocumentoo;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Profesor getSelectProfesor() {
        return selectProfesor;
    }

    public void setSelectProfesor(Profesor selectProfesor) {
        this.selectProfesor = selectProfesor;
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

    public void seleccionarProfesor(SelectEvent ev) {
        this.selectProfesor = (Profesor) ev.getObject();
    }

    //----------------------------------------------------
    public void guardarProfesor() {
        try {
            if (profesor.getProfesorId() == null || profesor.getProfesorId() == 0) {
                generarUser();
                generarPass();
                adminProfesor.create(profesor);
                anadirMensajeInfo("Profesor registrado exitosamente");
            } else {
                adminProfesor.edit(profesor);
                anadirMensajeInfo("Profesor actualizado exitosamente");
            }
            cargarProfesor();
            resetearFormularioProfesor();
        } catch (Exception e) {
            anadirMensajeError("No se pudo guardar el profesor:" + e.getMessage());
        }
    }

    public void editarProfesor() {
        if (selectProfesor != null) {
            this.profesor = selectProfesor;
        } else {
            anadirMensajeError("Se debe seleccionar un registro");
        }
    }

    public void eliminarProfesor() {
        try {
            if (selectProfesor != null) {
                adminProfesor.remove(selectProfesor);
                anadirMensajeInfo("Cliente eliminado correctamente");
                cargarProfesor();
                resetearFormularioProfesor();
            } else {
                anadirMensajeError("Se debe seleccionar un registro");
            }
        } catch (Exception e) {
            anadirMensajeError("No se pudo eliminar el registro:" + e.getMessage());
        }
    }

    public void resetearFormularioProfesor() {
        this.profesor = new Profesor();
        this.selectProfesor = null;
    }

    public void buscarProfesor() {

        try {
            this.listaProfesor.clear();
            profesor = adminProfesor.buscarPorIdentificacion(profesor.getDniPrf());

            if (profesor == null) {
                anadirMensajeError("profesor no encontrado");
                profesor = new Profesor();
            } else {
                this.listaProfesor.add(profesor);
                anadirMensajeInfo("Profesor encontrado");
            }

        } catch (Exception e) {
            anadirMensajeError("Error al buscar :" + e.getMessage());
        }
    }

    private void cargarProfesor() {
        try {
            this.listaProfesor = adminProfesor.findAll();
        } catch (Exception e) {
            anadirMensajeError("No se pudo cargar los profesores:" + e.getMessage());
        }
    }

    private void cargarMateriaProfe() {
        try {
            this.listaMatProfe = adminMatProfe.findAll();
        } catch (Exception e) {
            anadirMensajeError("No se pudo cargar los profesores:" + e.getMessage());
        }
    }

    private void cargarMateria() {
        try {
            this.listaMat = adminMateria.findAll();
        } catch (Exception e) {
            anadirMensajeError("No se pudo cargar los Materia:" + e.getMessage());
        }
    }

    public void generarUser() {
        String user = usuario.generadorUsurio(profesor.getNombrePrf(), profesor.getApellidoPrf());
        profesor.setNombreUsr(user);
        profesor.getNombreUsr();
    }

    public void generarPass() {
        String clav = usuario.generadorClave(profesor.getApellidoPrf(), profesor.getDniPrf());
        profesor.setClaveUsr(clav);
        profesor.getClaveUsr();


    }

    private void cargarTipoDocumento() {
        try {
            this.listaTipoDocument = adminTipoDoc.findAll();
        } catch (Exception e) {
            anadirMensajeError("No se pudo cargar los tipos de documentos:" + e.getMessage());
        }
    }

    private void cargarDocumentos() {
        try {
            this.listaDocumentos = adminDocumentoo.findAll();
        } catch (Exception e) {
            anadirMensajeError("No se pudo cargar los Documentos:" + e.getMessage());
        }
    }

    private void cargarFichaProfesor() {
        try {
            this.listaFichaProfesor = adminFichaEstudiante.findAll();
        } catch (Exception e) {
            anadirMensajeError("No se pudo cargar las Fichas:" + e.getMessage());
        }
    }

    @PostConstruct
    public void inicializar() {
        cargarProfesor();
        cargarDocumentos();
        cargarFichaProfesor();
        cargarMateria();
        cargarMateriaProfe();
        cargarTipoDocumento();

    }
}
