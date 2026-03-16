/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agenda.validacion;

/**
 *
 * @author delcenjo
 */
public class TelefonoValidar {
    
    public static boolean esTelefonoValido(String telefono) {

        if (telefono == null) {
            return false;
        }

        return telefono.matches("\\d{9}");
    }
    
}
