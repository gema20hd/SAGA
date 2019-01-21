package convertidor;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import ejb.DocumentoFacadeLocal;
import ejb.SemestreFacadeLocal;
import ejb.TipoDocumentoFacadeLocal;
import entidades.Documento;
import entidades.Semestre;
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
@ManagedBean(name = "convSem")
public class ConvSemestre implements Converter {

    @EJB
    private SemestreFacadeLocal adminSemetre;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (!value.equals("")) {
            Semestre docum = adminSemetre.find(Integer.valueOf(value));
            return docum;
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Semestre docu = (Semestre) value;
            return String.valueOf(docu.getSemestreId());
        } else {
            return null;
        }
    }
}
