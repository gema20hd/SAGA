/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package convertidor;

/**
 *
 * @author gemac
 */
public class Usuario {

    private String usuario;
    private String clave;
    private String correo;

    public Usuario() {
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String generadorUsurio(String nomb, String app) {
        String usuario = (nomb.substring(0, 1) + "" + app.substring(0, 5)).toLowerCase();
        setUsuario(usuario);
        return getUsuario();
    }

    public String generadorClave(String app, String dni) {
        String clave = (app.substring(0, 2) + "," + dni.substring(0, 2));
        setClave(clave);
        return getClave();
    }

    public String generadorCorreo(String nomb, String app,String dom) {
        String dominio = "@uce.edu.ec";
        String domin = (nomb.charAt(0) + app + dom).toLowerCase();
        setCorreo(domin);
        return getCorreo();
    }
}
