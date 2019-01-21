/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uce.edu.ec.manager;

import ejb.FichaEmpleadoFacadeLocal;
import ejb.FichaProfesorFacadeLocal;
import entidades.FichaEmpleado;
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
public class FichaEmpleadoBean extends AbstractManagedBean {

    private FichaEmpleado fichaEmpleado;
    private FichaEmpleado selectFEmpleado;
    private List<FichaEmpleado> listaFEmpleado;
    @EJB
    private FichaEmpleadoFacadeLocal adminFEmpleado;

    /**
     * Creates a new instance of ProfesorBean
     */
    public FichaEmpleadoBean() {
        this.fichaEmpleado = new FichaEmpleado();
        this.listaFEmpleado = new ArrayList<>();
    }

    public FichaEmpleado getFichaEmpleado() {
        return fichaEmpleado;
    }

    public void setFichaEmpleado(FichaEmpleado fichaEmpleado) {
        this.fichaEmpleado = fichaEmpleado;
    }

    public FichaEmpleado getSelectFEmpleado() {
        return selectFEmpleado;
    }

    public void setSelectFEmpleado(FichaEmpleado selectFEmpleado) {
        this.selectFEmpleado = selectFEmpleado;
    }

    public List<FichaEmpleado> getListaFEmpleado() {
        return listaFEmpleado;
    }

    public void setListaFEmpleado(List<FichaEmpleado> listaFEmpleado) {
        this.listaFEmpleado = listaFEmpleado;
    }

    public FichaEmpleadoFacadeLocal getAdminFEmpleado() {
        return adminFEmpleado;
    }

    public void setAdminFEmpleado(FichaEmpleadoFacadeLocal adminFEmpleado) {
        this.adminFEmpleado = adminFEmpleado;
    }

    public void seleccionarFichaEmpleado(SelectEvent ev) {
        this.selectFEmpleado = (FichaEmpleado) ev.getObject();
    }

    //----------------------------------------------------
    public void guardarFichaEmpleado() {
        try {
            if (fichaEmpleado.getFichaEmpId() == null || fichaEmpleado.getFichaEmpId() == 0) {
                adminFEmpleado.create(fichaEmpleado);
                anadirMensajeInfo("Cliente registrado exitosamente");
            } else {
                adminFEmpleado.edit(fichaEmpleado);
                anadirMensajeInfo("Cliente actualizado exitosamente");
            }
            cargarFichaEmpleado();
            resetearFormularioFichaEmpleado();
        } catch (Exception e) {
            anadirMensajeError("No se pudo guardar el Cliente:" + e.getMessage());
        }
    }

    public void editarFichaEmpleado() {
        if (selectFEmpleado != null) {
            this.fichaEmpleado = selectFEmpleado;
        } else {
            anadirMensajeError("Se debe seleccionar un registro");
        }
    }

    public void eliminarFichaEmpleado() {
        try {
            if (selectFEmpleado != null) {
                adminFEmpleado.remove(selectFEmpleado);
                anadirMensajeInfo("Cliente eliminado correctamente");
                cargarFichaEmpleado();
                resetearFormularioFichaEmpleado();
            } else {
                anadirMensajeError("Se debe seleccionar un registro");
            }
        } catch (Exception e) {
            anadirMensajeError("No se pudo eliminar el registro:" + e.getMessage());
        }
    }

    public void resetearFormularioFichaEmpleado() {
        this.fichaEmpleado = new FichaEmpleado();
        this.selectFEmpleado = null;
    }

    public void buscarFichaEmpleado() {

        try {
            this.listaFEmpleado.clear();
            fichaEmpleado = adminFEmpleado.buscarPorId(fichaEmpleado.getFichaEmpId());

            if (fichaEmpleado == null) {
                anadirMensajeError("profesor no encontrado");
                fichaEmpleado = new FichaEmpleado();
            } else {
                this.listaFEmpleado.add(fichaEmpleado);
                anadirMensajeInfo("Profesor encontrado");
            }

        } catch (Exception e) {
            anadirMensajeError("Error al buscar :" + e.getMessage());
        }
    }

    private void cargarFichaEmpleado() {
        try {
            this.listaFEmpleado = adminFEmpleado.findAll();
        } catch (Exception e) {
            anadirMensajeError("No se pudo cargar los clientes:" + e.getMessage());
        }
    }

    @PostConstruct
    public void inicializar() {
        cargarFichaEmpleado();

    }
}
