/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uce.edu.ec.manager;

import convertidor.Usuario;
import ejb.DocumentoFacadeLocal;
import ejb.EmpleadoFacadeLocal;
import ejb.FichaEmpleadoFacadeLocal;
import ejb.TipoDocumentoFacadeLocal;
import entidades.Documento;
import entidades.Empleado;
import entidades.FichaEmpleado;
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
public class EmpleadoBean extends AbstractManagedBean {

    Usuario usuario = new Usuario();
    private TipoDocumento tipoDocumentos;
    private String buscquedaPor;
    private String valorBusPor;
    private Empleado empleado;
    private Empleado selectEmpleado;
    private List<FichaEmpleado> listaFichaEmpleados;
    private List<Documento> listaDocumentos;
    private List<Empleado> listaEmpleados;
    private List<TipoDocumento> listaTipoDocumentos;
    @EJB
    private TipoDocumentoFacadeLocal adminTipoDocumento;
    @EJB
    private EmpleadoFacadeLocal adminEmpleado;
    @EJB
    private FichaEmpleadoFacadeLocal adminFichaEmpleado;
    @EJB
    private DocumentoFacadeLocal adminDocumentoo;
    String dominio = "@uce.edu.ec";

    /**
     * Creates a new instance of EmpleadoBean
     */
    public EmpleadoBean() {
        this.empleado = new Empleado();
        this.listaEmpleados = new ArrayList<>();
        this.listaDocumentos = new ArrayList<>();
        this.listaTipoDocumentos = new ArrayList<>();
        this.listaFichaEmpleados = new ArrayList<>();
    }

    public TipoDocumento getTipoDocumentos() {
        return tipoDocumentos;
    }

    public void setTipoDocumentos(TipoDocumento tipoDocumentos) {
        this.tipoDocumentos = tipoDocumentos;
    }

    public List<TipoDocumento> getListaTipoDocumentos() {
        return listaTipoDocumentos;
    }

    public void setListaTipoDocumentos(List<TipoDocumento> listaTipoDocumentos) {
        this.listaTipoDocumentos = listaTipoDocumentos;
    }

    public TipoDocumentoFacadeLocal getAdminTipoDocumento() {
        return adminTipoDocumento;
    }

    public void setAdminTipoDocumento(TipoDocumentoFacadeLocal adminTipoDocumento) {
        this.adminTipoDocumento = adminTipoDocumento;
    }

    public String getDominio() {
        return dominio;
    }

    public void setDominio(String dominio) {
        this.dominio = dominio;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Empleado getSelectEmpleado() {
        return selectEmpleado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<FichaEmpleado> getListaFichaEmpleados() {
        return listaFichaEmpleados;
    }

    public void setListaFichaEmpleados(List<FichaEmpleado> listaFichaEmpleados) {
        this.listaFichaEmpleados = listaFichaEmpleados;
    }

    public List<Documento> getListaDocumentos() {
        return listaDocumentos;
    }

    public void setListaDocumentos(List<Documento> listaDocumentos) {
        this.listaDocumentos = listaDocumentos;
    }

    public FichaEmpleadoFacadeLocal getAdminFichaEmpleado() {
        return adminFichaEmpleado;
    }

    public void setAdminFichaEmpleado(FichaEmpleadoFacadeLocal adminFichaEmpleado) {
        this.adminFichaEmpleado = adminFichaEmpleado;
    }

    public DocumentoFacadeLocal getAdminDocumentoo() {
        return adminDocumentoo;
    }

    public void setAdminDocumentoo(DocumentoFacadeLocal adminDocumentoo) {
        this.adminDocumentoo = adminDocumentoo;
    }

    public void setSelectEmpleado(Empleado selectEmpleado) {
        this.selectEmpleado = selectEmpleado;
    }

    public List<Empleado> getListaEmpleados() {
        return listaEmpleados;
    }

    public void setListaEmpleados(List<Empleado> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }

    public EmpleadoFacadeLocal getAdminEmpleado() {
        return adminEmpleado;
    }

    public void setAdminEmpleado(EmpleadoFacadeLocal adminEmpleado) {
        this.adminEmpleado = adminEmpleado;
    }

    public String getValorBusPor() {
        return valorBusPor;
    }

    public void setValorBusPor(String valorBusPor) {
        this.valorBusPor = valorBusPor;
    }

    public String getBuscquedaPor() {
        return buscquedaPor;
    }

    public void setBuscquedaPor(String buscquedaPor) {
        this.buscquedaPor = buscquedaPor;
    }

    public void seleccionarEmpleado(SelectEvent ev) {
        this.selectEmpleado = (Empleado) ev.getObject();
    }
    //----------------------------------------------------

    public void guardarEmpleado() {
        try {
            if (empleado.getEmpleadoId() == null || empleado.getEmpleadoId() == 0) {
                generarCorreo();
                generarUser();
                generarPass();
                // adminFichaEmpleado.buscarPorId(empleado.getFichaEmpId());
                adminEmpleado.create(empleado);
                anadirMensajeInfo("Empleado registrado exitosamente");
            } else {
                adminEmpleado.edit(empleado);
                anadirMensajeInfo("Empleado actualizado exitosamente");
            }
            cargarEmpleados();
            resetearFormularioEmpleado();
        } catch (Exception e) {
            anadirMensajeError("No se pudo guardar el Empleado:" + e.getMessage());
        }
    }

    public void editarEmpleado() {
        if (selectEmpleado != null) {
            this.empleado = selectEmpleado;
        } else {
            anadirMensajeError("Se debe seleccionar un registro");
        }
    }

    public void eliminarEmpleado() {
        try {
            if (selectEmpleado != null) {
                adminEmpleado.remove(selectEmpleado);
                anadirMensajeInfo("Empleado eliminado correctamente");
                cargarEmpleados();
                resetearFormularioEmpleado();
            } else {
                anadirMensajeError("Se debe seleccionar un registro");
            }
        } catch (Exception e) {
            anadirMensajeError("No se pudo eliminar el registro:" + e.getMessage());
        }
    }

    public void resetearFormularioEmpleado() {
        this.empleado = new Empleado();
        this.selectEmpleado = null;
    }

    public void buscarEmpleados() {
        try {
            this.listaEmpleados.clear();
            empleado = adminEmpleado.buscarPorIdentificacion(empleado.getDniAdm());

            if (empleado == null) {
                anadirMensajeError("Empleado no encontrado");
                empleado = new Empleado();
            } else {
                this.listaEmpleados.add(empleado);
                anadirMensajeInfo("Empleado encontrado");
            }

        } catch (Exception e) {
            anadirMensajeError("Error al buscar :" + e.getMessage());
        }

    }

    private void cargarEmpleados() {
        try {
            this.listaEmpleados = adminEmpleado.findAll();
        } catch (Exception e) {
            anadirMensajeError("No se pudo cargar los clientes:" + e.getMessage());
        }
    }

    private void cargarDocumentos() {
        try {
            this.listaDocumentos = adminDocumentoo.findAll();
        } catch (Exception e) {
            anadirMensajeError("No se pudo cargar los clientes:" + e.getMessage());
        }
    }

    private void cargarFichaEmpleado() {
        try {
            this.listaFichaEmpleados = adminFichaEmpleado.findAll();
        } catch (Exception e) {
            anadirMensajeError("No se pudo cargar los clientes:" + e.getMessage());
        }
    }

    public void generarUser() {
        String user = usuario.generadorUsurio(empleado.getNombreAdm(), empleado.getApellidoAdm());
        empleado.setNombreUsr(user);
        empleado.getNombreUsr();
    }

    public void generarPass() {
        String clav = usuario.generadorClave(empleado.getApellidoAdm(), empleado.getDniAdm());
        empleado.setClaveUsr(clav);
        empleado.getClaveUsr();

    }

    public void generarCorreo() {
        String mail = usuario.generadorCorreo(empleado.getNombreAdm(), empleado.getApellidoAdm(), dominio);
        empleado.setClaveUsr(mail);
        empleado.getClaveUsr();

    }

    private void cargarDocumento() {
        try {
            this.listaDocumentos = adminDocumentoo.findAll();
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

    @PostConstruct
    public void inicializar() {
        cargarEmpleados();
        cargarFichaEmpleado();
        cargarDocumentos();
        cargarTipoDocumento();

    }
}
