/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uce.edu.ec.manager;

import ejb.SemestreFacade;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author gemac
 */
@ManagedBean
public class primerS {

    @EJB
    private SemestreFacade EJBsemestre;
    private List<Object[]> listado;

    public List<Object[]> getListado() {
        return listado;
    }

    public void setListado(List<Object[]> listado) {
        this.listado = listado;
    }

    @PostConstruct
    public void llenartabla() {
        listado = EJBsemestre.primersemestre();
    }

    /**
     * Creates a new instance of primerS
     */
    public primerS() {
    }
}
