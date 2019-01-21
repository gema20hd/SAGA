/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uce.edu.ec.manager;

import ejb.FichaProfesorFacadeLocal;
import entidades.FichaProfesor;
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
public class FichaProfesorBean extends AbstractManagedBean {

    private FichaProfesor fichaProfesor;
    private FichaProfesor selectFProfesor;
    private List<FichaProfesor> listaFProfesor;
    @EJB
    private FichaProfesorFacadeLocal adminFProfesor;

    /**
     * Creates a new instance of ProfesorBean
     */
    public FichaProfesorBean() {
        this.fichaProfesor = new FichaProfesor();
        this.listaFProfesor = new ArrayList<>();
    }

    public FichaProfesor getFichaProfesor() {
        return fichaProfesor;
    }

    public void setFichaProfesor(FichaProfesor fichaProfesor) {
        this.fichaProfesor = fichaProfesor;
    }

    public FichaProfesor getSelectFProfesor() {
        return selectFProfesor;
    }

    public void setSelectFProfesor(FichaProfesor selectFProfesor) {
        this.selectFProfesor = selectFProfesor;
    }

    public List<FichaProfesor> getListaFProfesor() {
        return listaFProfesor;
    }

    public void setListaFProfesor(List<FichaProfesor> listaFProfesor) {
        this.listaFProfesor = listaFProfesor;
    }

    public FichaProfesorFacadeLocal getAdminFProfesor() {
        return adminFProfesor;
    }

    public void setAdminFProfesor(FichaProfesorFacadeLocal adminFProfesor) {
        this.adminFProfesor = adminFProfesor;
    }

    public void seleccionarFichaProfesor(SelectEvent ev) {
        this.selectFProfesor = (FichaProfesor) ev.getObject();
    }

    //----------------------------------------------------
    public void guardarFichaProfesor() {
        try {
            if (fichaProfesor.getFichaProId() == null || fichaProfesor.getFichaProId() == 0) {
                adminFProfesor.create(fichaProfesor);
                anadirMensajeInfo("Cliente registrado exitosamente");
            } else {
                adminFProfesor.edit(fichaProfesor);
                anadirMensajeInfo("Cliente actualizado exitosamente");
            }
            cargarFichaProfesor();
            resetearFormularioFichaProfesor();
        } catch (Exception e) {
            anadirMensajeError("No se pudo guardar el Cliente:" + e.getMessage());
        }
    }

    public void editarFichaProfesor() {
        if (selectFProfesor != null) {
            this.fichaProfesor = selectFProfesor;
        } else {
            anadirMensajeError("Se debe seleccionar un registro");
        }
    }

    public void eliminarFichaProfesor() {
        try {
            if (selectFProfesor != null) {
                adminFProfesor.remove(selectFProfesor);
                anadirMensajeInfo("Cliente eliminado correctamente");
                cargarFichaProfesor();
                resetearFormularioFichaProfesor();
            } else {
                anadirMensajeError("Se debe seleccionar un registro");
            }
        } catch (Exception e) {
            anadirMensajeError("No se pudo eliminar el registro:" + e.getMessage());
        }
    }

    public void resetearFormularioFichaProfesor() {
        this.fichaProfesor = new FichaProfesor();
        this.selectFProfesor = null;
    }

    public void buscarFichaProfesor() {

        try {
            this.listaFProfesor.clear();
            fichaProfesor = adminFProfesor.buscarPorId(fichaProfesor.getFichaProId());

            if (fichaProfesor == null) {
                anadirMensajeError("profesor no encontrado");
                fichaProfesor = new FichaProfesor();
            } else {
                this.listaFProfesor.add(fichaProfesor);
                anadirMensajeInfo("Profesor encontrado");
            }

        } catch (Exception e) {
            anadirMensajeError("Error al buscar :" + e.getMessage());
        }
    }

    private void cargarFichaProfesor() {
        try {
            this.listaFProfesor = adminFProfesor.findAll();
        } catch (Exception e) {
            anadirMensajeError("No se pudo cargar los clientes:" + e.getMessage());
        }
    }

    @PostConstruct
    public void inicializar() {
        cargarFichaProfesor();

    }
}
