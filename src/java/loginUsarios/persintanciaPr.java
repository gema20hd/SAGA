/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package loginUsarios;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.Persistence;

/**
 *
 * @author gemac
 */
@ManagedBean
public class persintanciaPr {

    /**
     * Creates a new instance of persintanciaPr
     */
    public static void main(String[] args) {
        Persistence.createEntityManagerFactory("EJB_SISTEMAAPU");

    }
}
