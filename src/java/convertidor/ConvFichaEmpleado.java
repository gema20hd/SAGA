package convertidor;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import ejb.EmpleadoFacadeLocal;
import ejb.FichaEmpleadoFacadeLocal;
import entidades.Empleado;
import entidades.FichaEmpleado;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author gemac
 */
@ManagedBean(name = "convFichaEmp")
public class ConvFichaEmpleado implements Converter {

    @EJB
    private FichaEmpleadoFacadeLocal adminFichaEmpleado;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (!value.equals("")) {
            FichaEmpleado emple = adminFichaEmpleado.find(Integer.valueOf(value));
            return emple;
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            FichaEmpleado fichEmple = (FichaEmpleado) value;
            return String.valueOf(fichEmple.getFichaEmpId());
        } else {
            return null;
        }
    }
}
