/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uce.edu.ec.manager;

import ejb.FichaEstudianteFacadeLocal;
import entidades.FichaEstudiante;
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
public class FichaEstudianteBean extends AbstractManagedBean {

    private FichaEstudiante fichaEstudiante;
    private FichaEstudiante selectFEstudiante;
    private List<FichaEstudiante> listaFEstudiante;
    @EJB
    private FichaEstudianteFacadeLocal adminFEstudiante;

    /**
     * Creates a new instance of ProfesorBean
     */
    public FichaEstudianteBean() {
        this.fichaEstudiante = new FichaEstudiante();
        this.listaFEstudiante = new ArrayList<>();
    }

    public FichaEstudiante getFichaEstudiante() {
        return fichaEstudiante;
    }

    public void setFichaEstudiante(FichaEstudiante fichaEstudiante) {
        this.fichaEstudiante = fichaEstudiante;
    }

    public FichaEstudiante getSelectFEstudiante() {
        return selectFEstudiante;
    }

    public void setSelectFEstudiante(FichaEstudiante selectFEstudiante) {
        this.selectFEstudiante = selectFEstudiante;
    }

    public List<FichaEstudiante> getListaFEstudiante() {
        return listaFEstudiante;
    }

    public void setListaFEstudiante(List<FichaEstudiante> listaFEstudiante) {
        this.listaFEstudiante = listaFEstudiante;
    }

    public FichaEstudianteFacadeLocal getAdminFEstudiante() {
        return adminFEstudiante;
    }

    public void setAdminFEstudiante(FichaEstudianteFacadeLocal adminFEstudiante) {
        this.adminFEstudiante = adminFEstudiante;
    }

    public void seleccionarFichaEstudiante(SelectEvent ev) {
        this.selectFEstudiante = (FichaEstudiante) ev.getObject();
    }

    //----------------------------------------------------
    public void guardarFichaEstudiante() {
        try {
            if (fichaEstudiante.getFichaEstId() == null || fichaEstudiante.getFichaEstId() == 0) {
                adminFEstudiante.create(fichaEstudiante);
                anadirMensajeInfo("Cliente registrado exitosamente");
            } else {
                adminFEstudiante.edit(fichaEstudiante);
                anadirMensajeInfo("Cliente actualizado exitosamente");
            }
            cargarFichaEstudiante();
            resetearFormularioFichaEstudiante();
        } catch (Exception e) {
            anadirMensajeError("No se pudo guardar el Cliente:" + e.getMessage());
        }
    }

    public void editarFichaEstudiante() {
        if (selectFEstudiante != null) {
            this.fichaEstudiante = selectFEstudiante;
        } else {
            anadirMensajeError("Se debe seleccionar un registro");
        }
    }

    public void eliminarFichaEstudiante() {
        try {
            if (selectFEstudiante != null) {
                adminFEstudiante.remove(selectFEstudiante);
                anadirMensajeInfo("Cliente eliminado correctamente");
                cargarFichaEstudiante();
                resetearFormularioFichaEstudiante();
            } else {
                anadirMensajeError("Se debe seleccionar un registro");
            }
        } catch (Exception e) {
            anadirMensajeError("No se pudo eliminar el registro:" + e.getMessage());
        }
    }

    public void resetearFormularioFichaEstudiante() {
        this.fichaEstudiante = new FichaEstudiante();
        this.selectFEstudiante = null;
    }

    public void buscarFichaEstudiante() {

        try {
            this.listaFEstudiante.clear();
            fichaEstudiante = adminFEstudiante.buscarPorId(fichaEstudiante.getFichaEstId());

            if (fichaEstudiante == null) {
                anadirMensajeError("profesor no encontrado");
                fichaEstudiante = new FichaEstudiante();
            } else {
                this.listaFEstudiante.add(fichaEstudiante);
                anadirMensajeInfo("Profesor encontrado");
            }

        } catch (Exception e) {
            anadirMensajeError("Error al buscar :" + e.getMessage());
        }
    }

    private void cargarFichaEstudiante() {
        try {
            this.listaFEstudiante = adminFEstudiante.findAll();
        } catch (Exception e) {
            anadirMensajeError("No se pudo cargar los clientes:" + e.getMessage());
        }
    }

    @PostConstruct
    public void inicializar() {
        cargarFichaEstudiante();

    }
}
