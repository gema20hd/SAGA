package convertidor;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import ejb.EmpleadoFacadeLocal;
import ejb.ProfesorFacadeLocal;
import entidades.Empleado;
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
@ManagedBean(name = "convEmp")
public class ConvEmpleado implements Converter {

    @EJB
    private EmpleadoFacadeLocal adminEmp;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (!value.equals("")) {
            Empleado emp = adminEmp.find(Integer.valueOf(value));
            return emp;
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Empleado emp = (Empleado) value;
            return String.valueOf(emp.getEmpleadoId());
        } else {
            return null;
        }
    }
}
