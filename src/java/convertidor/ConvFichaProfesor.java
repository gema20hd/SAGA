package convertidor;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import ejb.EmpleadoFacadeLocal;
import ejb.FichaEmpleadoFacadeLocal;
import ejb.FichaProfesorFacadeLocal;
import entidades.FichaEmpleado;
import entidades.FichaProfesor;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author gemac
 */
@ManagedBean(name = "convFichaPro")
public class ConvFichaProfesor implements Converter {

    @EJB
    private FichaProfesorFacadeLocal adminFichaProfesor;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (!value.equals("")) {
            FichaProfesor fichProfesor = adminFichaProfesor.find(Integer.valueOf(value));
            return fichProfesor;
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            FichaProfesor fichProfesor = (FichaProfesor) value;
            return String.valueOf(fichProfesor.getFichaProId());
        } else {
            return null;
        }
    }
}
