/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uce.edu.ec.manager;

import javax.annotation.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author gemac
 */
@ManagedBean
@ViewScoped
public class AbstractManagedBean {

    protected FacesContext obtenerContexto() {
        return FacesContext.getCurrentInstance();
    }

    protected void anadirMensajeInfo(String mensaje) {
        anadirMensaje(mensaje, FacesMessage.SEVERITY_INFO);
    }

    protected void anadirMensajeError(String mensaje) {
        anadirMensaje(mensaje, FacesMessage.SEVERITY_ERROR);
    }

    private void anadirMensaje(String mensaje, Severity severity) {
        FacesMessage menJSF = new FacesMessage();
        menJSF.setSeverity(severity);
        menJSF.setSummary(mensaje);
        obtenerContexto().addMessage(null, menJSF);

    }
}
