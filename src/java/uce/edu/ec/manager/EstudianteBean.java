
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uce.edu.ec.manager;

import convertidor.Usuario;
import ejb.DocumentoFacadeLocal;
import ejb.EstudianteFacadeLocal;
import ejb.FichaEstudianteFacadeLocal;
import ejb.MateriaFacadeLocal;
import ejb.MatriculaFacadeLocal;
import ejb.NotaFacadeLocal;
import ejb.PeriodoFacadeLocal;
import ejb.SemestreFacadeLocal;
import ejb.TipoMatriculaFacadeLocal;
import entidades.Documento;
import entidades.Estudiante;
import entidades.FichaEstudiante;
import entidades.Materia;
import entidades.Matricula;
import entidades.Nota;
import entidades.Periodo;
import entidades.Semestre;
import entidades.TipoMatricula;
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
public class EstudianteBean extends AbstractManagedBean {

    Usuario usuarioEstudiante = new Usuario();
    private Matricula matricula;
    private TipoMatricula tipoMatricula;
    private Nota nota;
    private Periodo periodo;
    private Materia materia;
    private Estudiante estudiante;
    private Semestre semestre;
    private Estudiante selectEstudiante;
    private List<Semestre> listaSemestre;
    private List<Estudiante> listaEstudiante;
    private List<FichaEstudiante> listaFichaEstudiantes;
    private List<Documento> listaDocumentos;
    private List<Nota> listaNotas;
    private List<Materia> listaMateria;
    private List<Periodo> listaPeriodo;
    private List<Matricula> listaMatricula;
    private List<TipoMatricula> listaTipoMatricula;
    @EJB
    private FichaEstudianteFacadeLocal adminFichaEstudiante;
    @EJB
    private DocumentoFacadeLocal adminDocumentoo;
    @EJB
    private SemestreFacadeLocal adminSemetres;
    @EJB
    private EstudianteFacadeLocal adminEstudiante;
    @EJB
    private MateriaFacadeLocal admiMateria;
    @EJB
    private NotaFacadeLocal adminNota;
    @EJB
    private PeriodoFacadeLocal adminPeriodo;
    @EJB
    private MatriculaFacadeLocal adminMatricula;
    @EJB
    private TipoMatriculaFacadeLocal adminTipoMatricula;

    /**
     * Creates a new instance of EstudianteBean
     */
    public EstudianteBean() {
        this.estudiante = new Estudiante();
        this.listaEstudiante = new ArrayList<>();
        this.listaDocumentos = new ArrayList<>();
        this.listaFichaEstudiantes = new ArrayList<>();
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    public TipoMatricula getTipoMatricula() {
        return tipoMatricula;
    }

    public void setTipoMatricula(TipoMatricula tipoMatricula) {
        this.tipoMatricula = tipoMatricula;
    }

    public List<Matricula> getListaMatricula() {
        return listaMatricula;
    }

    public void setListaMatricula(List<Matricula> listaMatricula) {
        this.listaMatricula = listaMatricula;
    }

    public List<TipoMatricula> getListaTipoMatricula() {
        return listaTipoMatricula;
    }

    public void setListaTipoMatricula(List<TipoMatricula> listaTipoMatricula) {
        this.listaTipoMatricula = listaTipoMatricula;
    }

    public MatriculaFacadeLocal getAdminMatricula() {
        return adminMatricula;
    }

    public void setAdminMatricula(MatriculaFacadeLocal adminMatricula) {
        this.adminMatricula = adminMatricula;
    }

    public TipoMatriculaFacadeLocal getAdminTipoMatricula() {
        return adminTipoMatricula;
    }

    public void setAdminTipoMatricula(TipoMatriculaFacadeLocal adminTipoMatricula) {
        this.adminTipoMatricula = adminTipoMatricula;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public List<Periodo> getListaPeriodo() {
        return listaPeriodo;
    }

    public void setListaPeriodo(List<Periodo> listaPeriodo) {
        this.listaPeriodo = listaPeriodo;
    }

    public PeriodoFacadeLocal getAdminPeriodo() {
        return adminPeriodo;
    }

    public void setAdminPeriodo(PeriodoFacadeLocal adminPeriodo) {
        this.adminPeriodo = adminPeriodo;
    }

    public Semestre getSemestre() {
        return semestre;
    }

    public void setSemestre(Semestre semestre) {
        this.semestre = semestre;
    }

    public List<Semestre> getListaSemestre() {
        return listaSemestre;
    }

    public void setListaSemestre(List<Semestre> listaSemestre) {
        this.listaSemestre = listaSemestre;
    }

    public SemestreFacadeLocal getAdminSemetres() {
        return adminSemetres;
    }

    public void setAdminSemetres(SemestreFacadeLocal adminSemetres) {
        this.adminSemetres = adminSemetres;
    }

    public Usuario getUsuarioEstudiante() {
        return usuarioEstudiante;
    }

    public void setUsuarioEstudiante(Usuario usuarioEstudiante) {
        this.usuarioEstudiante = usuarioEstudiante;
    }

    public List<FichaEstudiante> getListaFichaEstudiantes() {
        return listaFichaEstudiantes;
    }

    public void setListaFichaEstudiantes(List<FichaEstudiante> listaFichaEstudiantes) {
        this.listaFichaEstudiantes = listaFichaEstudiantes;
    }

    public List<Documento> getListaDocumentos() {
        return listaDocumentos;
    }

    public void setListaDocumentos(List<Documento> listaDocumentos) {
        this.listaDocumentos = listaDocumentos;
    }

    public FichaEstudianteFacadeLocal getAdminFichaEstudiante() {
        return adminFichaEstudiante;
    }

    public void setAdminFichaEstudiante(FichaEstudianteFacadeLocal adminFichaEstudiante) {
        this.adminFichaEstudiante = adminFichaEstudiante;
    }

    public DocumentoFacadeLocal getAdminDocumentoo() {
        return adminDocumentoo;
    }

    public void setAdminDocumentoo(DocumentoFacadeLocal adminDocumentoo) {
        this.adminDocumentoo = adminDocumentoo;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Estudiante getSelectEstudiante() {
        return selectEstudiante;
    }

    public void setSelectEstudiante(Estudiante selectEstudiante) {
        this.selectEstudiante = selectEstudiante;
    }

    public List<Estudiante> getListaEstudiante() {
        return listaEstudiante;
    }

    public void setListaEstudiante(List<Estudiante> listaEstudiante) {
        this.listaEstudiante = listaEstudiante;
    }

    public EstudianteFacadeLocal getAdminEstudiante() {
        return adminEstudiante;
    }

    public void setAdminEstudiante(EstudianteFacadeLocal adminEstudiante) {
        this.adminEstudiante = adminEstudiante;
    }

    public Nota getNota() {
        return nota;
    }

    public void setNota(Nota nota) {
        this.nota = nota;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public List<Nota> getListaNotas() {
        return listaNotas;
    }

    public void setListaNotas(List<Nota> listaNotas) {
        this.listaNotas = listaNotas;
    }

    public List<Materia> getListaMateria() {
        return listaMateria;
    }

    public void setListaMateria(List<Materia> listaMateria) {
        this.listaMateria = listaMateria;
    }

    public MateriaFacadeLocal getAdmiMateria() {
        return admiMateria;
    }

    public void setAdmiMateria(MateriaFacadeLocal admiMateria) {
        this.admiMateria = admiMateria;
    }

    public NotaFacadeLocal getAdminNota() {
        return adminNota;
    }

    public void setAdminNota(NotaFacadeLocal adminNota) {
        this.adminNota = adminNota;
    }

    // --------------------
    public void seleccionarEstudiante(SelectEvent ev) {
        this.selectEstudiante = (Estudiante) ev.getObject();
    }

    //----------------------------------------------------
    public void guardarEstudiante() {
        try {
            if (estudiante.getEstudianteId() == null || estudiante.getEstudianteId() == 0) {
                generarUserEstudiante();
                generarPassEstudiante();
                adminEstudiante.create(estudiante);
                anadirMensajeInfo("Estudiante registrado exitosamente");
            } else {
                adminEstudiante.edit(estudiante);
                anadirMensajeInfo("Estudiante actualizado exitosamente");
            }
            cargarEstudiante();
            resetearFormularioEstudiante();
        } catch (Exception e) {
            anadirMensajeError("No se pudo guardar el estudiante:" + e.getMessage());
        }
    }

    public void editarEstudiante() {
        if (selectEstudiante != null) {
            this.estudiante = selectEstudiante;
        } else {
            anadirMensajeError("Se debe seleccionar un registro");
        }
    }

    public void eliminarEstudiante() {
        try {
            if (selectEstudiante != null) {
                adminEstudiante.remove(selectEstudiante);
                anadirMensajeInfo("Cliente eliminado correctamente");
                cargarEstudiante();
                resetearFormularioEstudiante();
            } else {
                anadirMensajeError("Se debe seleccionar un registro");
            }
        } catch (Exception e) {
            anadirMensajeError("No se pudo eliminar el registro:" + e.getMessage());
        }
    }

    public void resetearFormularioEstudiante() {
        this.estudiante = new Estudiante();
        this.selectEstudiante = null;
    }

    //----------------------------------------------------
    public void buscarEstudiante() {
        try {
            this.listaEstudiante.clear();
            estudiante = adminEstudiante.buscarPorIdentificacion(estudiante.getDniEst());

            if (estudiante == null) {
                anadirMensajeError("Estudiante no encontrado");
                estudiante = new Estudiante();
            } else {
                this.listaEstudiante.add(estudiante);
                anadirMensajeInfo("Estudiante encontrado");
            }

        } catch (Exception e) {
            anadirMensajeError("Error al buscar :" + e.getMessage());
        }

    }

    public void buscarSemestre() {

        try {
            this.listaSemestre.clear();
            semestre = adminSemetres.buscarPorCurso(semestre.getDescripcionSmt());

            if (semestre == null) {
                anadirMensajeError("Estudiante no encontrado");
                semestre = new Semestre();
            } else {
                this.listaSemestre.add(semestre);
                anadirMensajeInfo("Estudiante encontrado");
            }

        } catch (Exception e) {
            anadirMensajeError("Error al buscar :" + e.getMessage());
        }


    }

    private void cargarEstudiante() {
        try {
            this.listaEstudiante = adminEstudiante.findAll();
        } catch (Exception e) {
            anadirMensajeError("No se pudo cargar la lista de Estudiantes:" + e.getMessage());
        }
    }

    private void cargarDocumentos() {
        try {
            this.listaDocumentos = adminDocumentoo.findAll();
        } catch (Exception e) {
            anadirMensajeError("No se pudo cargar los clientes:" + e.getMessage());
        }
    }

    private void cargarFichaEstudiante() {
        try {
            this.listaFichaEstudiantes = adminFichaEstudiante.findAll();
        } catch (Exception e) {
            anadirMensajeError("No se pudo cargar los clientes:" + e.getMessage());
        }
    }

    private void cargarSemestre() { //buscarSemestre
        try {
            this.listaSemestre = adminSemetres.findAll();
        } catch (Exception e) {
            anadirMensajeError("No se pudo cargar los clientes:" + e.getMessage());
        }
    }

    private void cargarMateria() { //buscarSemestre
        try {
            this.listaMateria = admiMateria.findAll();
        } catch (Exception e) {
            anadirMensajeError("No se pudo cargar las Materia:" + e.getMessage());
        }
    }

    private void cargarNota() { //buscarSemestre
        try {
            this.listaNotas = adminNota.findAll();
        } catch (Exception e) {
            anadirMensajeError("No se pudo cargar los Notas:" + e.getMessage());
        }
    }

    private void cargarPeriodo() { //buscarSemestre
        try {
            this.listaPeriodo = adminPeriodo.findAll();
        } catch (Exception e) {
            anadirMensajeError("No se pudo cargar los Periodo:" + e.getMessage());
        }
    }

    private void cargarMatricula() { //buscarSemestre
        try {
            this.listaMatricula = adminMatricula.findAll();
        } catch (Exception e) {
            anadirMensajeError("No se pudo cargar los Periodo:" + e.getMessage());
        }
    }

    private void cargarTipoMatricula() { //buscarSemestre
        try {
            this.listaTipoMatricula = adminTipoMatricula.findAll();
        } catch (Exception e) {
            anadirMensajeError("No se pudo cargar los Periodo:" + e.getMessage());
        }
    }
    //--------------------------

    public void generarUserEstudiante() {
        String user = usuarioEstudiante.generadorUsurio(estudiante.getNombreEst(), estudiante.getApellidoEst());
        estudiante.setNombreUsr(user);
        estudiante.getNombreUsr();
    }

    public void generarPassEstudiante() {
        String clav = usuarioEstudiante.generadorClave(estudiante.getApellidoEst(), estudiante.getDniEst());
        estudiante.setClaveUsr(clav);
        estudiante.getClaveUsr();


    }

    @PostConstruct
    public void inicializar() {
        cargarEstudiante();
        cargarDocumentos();
        cargarNota();
        cargarMateria();
        cargarSemestre();
        cargarFichaEstudiante();
        cargarPeriodo();
        cargarTipoMatricula();
        cargarMatricula();

    }
}
