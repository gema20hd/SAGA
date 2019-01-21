package convertidor;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import ejb.ProfesorFacadeLocal;
import entidades.Profesor;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author gemac
 */
@ManagedBean(name = "convPro")
public class ConvProfesor implements Converter {

    @EJB
    private ProfesorFacadeLocal adminProfe;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (!value.equals("")) {
            Profesor tipPro = adminProfe.find(Integer.valueOf(value));
            return tipPro;
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Profesor tipPro = (Profesor) value;
            return String.valueOf(tipPro.getProfesorId());
        } else {
            return null;
        }
    }
}
