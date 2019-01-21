package convertidor;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import ejb.EstudianteFacadeLocal;
import ejb.TipoDocumentoFacadeLocal;
import entidades.Estudiante;
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
@ManagedBean(name = "convEst")
public class ConvEstudiante implements Converter {

    @EJB
    private EstudianteFacadeLocal adminEstud;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (!value.equals("")) {
            Estudiante estud = adminEstud.find(Integer.valueOf(value));
            return estud;
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
           Estudiante estud = (Estudiante) value;
            return String.valueOf(estud.getEstudianteId());
        } else {
            return null;
        }
    }
}
