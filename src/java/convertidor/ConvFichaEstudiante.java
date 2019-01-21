package convertidor;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import ejb.EmpleadoFacadeLocal;
import ejb.FichaEmpleadoFacadeLocal;
import ejb.FichaEstudianteFacadeLocal;
import ejb.FichaProfesorFacadeLocal;
import entidades.FichaEmpleado;
import entidades.FichaEstudiante;
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
@ManagedBean(name = "convFichaEst")
public class ConvFichaEstudiante implements Converter {

    @EJB
    private FichaEstudianteFacadeLocal adminFichaEstudiante;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (!value.equals("")) {
            FichaEstudiante fichaEstudiante = adminFichaEstudiante.find(Integer.valueOf(value));
            return fichaEstudiante;
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            FichaEstudiante fichaEstudiante = (FichaEstudiante) value;
            return String.valueOf(fichaEstudiante.getFichaEstId());
        } else {
            return null;
        }
    }
}
