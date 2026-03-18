/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
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
        telefono = telefono.trim();
        if (!telefono.matches("\\d{9}")) {
            return false;
        }
        char primero = telefono.charAt(0);
        if (primero < '6' || primero > '9') {
            return false;
        }
        return true;
    }
}