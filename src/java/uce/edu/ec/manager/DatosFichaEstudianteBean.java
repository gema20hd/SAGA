/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uce.edu.ec.manager;

import ejb.DocumentoFacadeLocal;
import ejb.EstudianteFacadeLocal;
import ejb.FichaEstudianteFacadeLocal;
import entidades.Documento;
import entidades.Estudiante;
import entidades.FichaEstudiante;
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
public class DatosFichaEstudianteBean extends AbstractManagedBean {

    private Documento documento;
    private List<Documento> listaDocumento;
    private Estudiante estudiante;
    private List<Estudiante> listaEstudiante;
    private FichaEstudiante fichaEstudiante;
    private FichaEstudiante ficha;
    private List<FichaEstudiante> listaFichaEstudiante;
    private Integer indexFichaEst;
    @EJB
    private FichaEstudianteFacadeLocal adminFEstudiante;
    @EJB
    private DocumentoFacadeLocal adminDocumento;
    @EJB
    private EstudianteFacadeLocal adminEstudiante;

    /**
     * Creates a new instance of ProfesorBean
     */
    public DatosFichaEstudianteBean() {
        this.fichaEstudiante = new FichaEstudiante();
        this.documento = new Documento();
        this.estudiante = new Estudiante();
        this.listaEstudiante = new ArrayList<>();
        this.listaDocumento = new ArrayList<>();
        this.listaFichaEstudiante = new ArrayList<>();
    }

    public FichaEstudiante getFicha() {
        return ficha;
    }

    public void setFicha(FichaEstudiante ficha) {
        this.ficha = ficha;
    }

    public Integer getIndexFichaEst() {
        return indexFichaEst;
    }

    public void setIndexFichaEst(Integer indexFichaEst) {
        this.indexFichaEst = indexFichaEst;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public List<Documento> getListaDocumento() {
        return listaDocumento;
    }

    public void setListaDocumento(List<Documento> listaDocumento) {
        this.listaDocumento = listaDocumento;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public List<Estudiante> getListaEstudiante() {
        return listaEstudiante;
    }

    public void setListaEstudiante(List<Estudiante> listaEstudiante) {
        this.listaEstudiante = listaEstudiante;
    }

    public FichaEstudiante getFichaEstudiante() {
        return fichaEstudiante;
    }

    public void setFichaEstudiante(FichaEstudiante fichaEstudiante) {
        this.fichaEstudiante = fichaEstudiante;
    }

    public List<FichaEstudiante> getListaFichaEstudiante() {
        return listaFichaEstudiante;
    }

    public void setListaFichaEstudiante(List<FichaEstudiante> listaFichaEstudiante) {
        this.listaFichaEstudiante = listaFichaEstudiante;
    }

    public FichaEstudianteFacadeLocal getAdminFEstudiante() {
        return adminFEstudiante;
    }

    public void setAdminFEstudiante(FichaEstudianteFacadeLocal adminFEstudiante) {
        this.adminFEstudiante = adminFEstudiante;
    }

    public DocumentoFacadeLocal getAdminDocumento() {
        return adminDocumento;
    }

    public void setAdminDocumento(DocumentoFacadeLocal adminDocumento) {
        this.adminDocumento = adminDocumento;
    }

    public EstudianteFacadeLocal getAdminEstudiante() {
        return adminEstudiante;
    }

    public void setAdminEstudiante(EstudianteFacadeLocal adminEstudiante) {
        this.adminEstudiante = adminEstudiante;
    }

    public void listaEstudianteFicha() {
        this.listaFichaEstudiante.clear();
        ficha = adminFEstudiante.listarEstudiante(indexFichaEst).get(0);


    }

    public void buscarEstudianteFicha() {
        try {
            this.listaFichaEstudiante.clear();
            fichaEstudiante = adminFEstudiante.buscarPorId(fichaEstudiante.getFichaEstId());

            if (fichaEstudiante == null) {
                anadirMensajeError("profesor no encontrado");
                fichaEstudiante = new FichaEstudiante();
            } else {
                this.listaFichaEstudiante.add(fichaEstudiante);
                anadirMensajeInfo("Profesor encontrado");
            }

        } catch (Exception e) {
            anadirMensajeError("Error al buscar :" + e.getMessage());
        }
    }

    private void cargarFichaEstudiante() {
        try {
            this.listaFichaEstudiante = adminFEstudiante.findAll();
            cargarEstudiante();
        } catch (Exception e) {
            anadirMensajeError("No se pudo cargar las fichas :" + e.getMessage());
        }
    }

    private void cargarEstudiante() {
        try {
            ficha = adminFEstudiante.listarEstudiante(indexFichaEst).get(0);
            this.listaFichaEstudiante.add(ficha);
        } catch (Exception e) {
            anadirMensajeError("No se pudo cargar los estudiantes :" + e.getMessage());
        }
    }

    private void cargarDocumento() {
        try {
            this.listaDocumento = adminDocumento.findAll();
        } catch (Exception e) {
            anadirMensajeError("No se pudo cargar los documentos :" + e.getMessage());
        }
    }

    @PostConstruct
    public void inicializar() {
        cargarEstudiante();
        // cargarDocumento();
        //cargarEstudiante();
    }
}
