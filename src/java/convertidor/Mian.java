/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package convertidor;

/**
 *
 * @author gemac
 */
public class Mian {

    public static void main(String[] args) {
        String usuario;
        String clave;
        String dominio = "@uce.edu.ec";
        String correo;
        String nomb = "Gema";
        String app = "Zumba";
        String dni = "1234567898";
        Usuario user = new Usuario();
        usuario = user.generadorUsurio(nomb, app);
        clave = user.generadorClave(app, dni);
        correo = user.generadorCorreo(nomb, app, dominio);
        System.out.println("El  usuario es :" + usuario);
        System.out.println("La clave es :" + clave);
        System.out.println("La correo es :" + correo);

    }
}
