package convertidor;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import ejb.TipoDocumentoFacadeLocal;
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
@ManagedBean(name = "convTipDoc")
public class ConvTipoDocumento implements Converter {

    @EJB
    private TipoDocumentoFacadeLocal adminTipoUser;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (!value.equals("")) {
            TipoDocumento tipPro = adminTipoUser.find(Integer.valueOf(value));
            return tipPro;
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            TipoDocumento tipPro = (TipoDocumento) value;
            return String.valueOf(tipPro.getTipoDocumentoId());
        } else {
            return null;
        }
    }
}
