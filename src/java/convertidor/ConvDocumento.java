package convertidor;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import ejb.DocumentoFacadeLocal;
import ejb.TipoDocumentoFacadeLocal;
import entidades.Documento;
import entidades.TipoDocumento;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author gemac
 */
@ManagedBean(name = "convDoc")
public class ConvDocumento implements Converter {

    @EJB
    private DocumentoFacadeLocal adminDocumento;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (!value.equals("")) {
            Documento docum = adminDocumento.find(Integer.valueOf(value));
            return docum;
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
           Documento docu = (Documento) value;
            return String.valueOf(docu.getDocumentoId());
        } else {
            return null;
        }
    }
}
