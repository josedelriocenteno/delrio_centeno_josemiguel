package agenda.validacion;

public class TelefonoValidar {

    public static boolean esTelefonoValido(String telefono) {

        if (telefono == null) {
            return false;
        }

        telefono = telefono.trim();

        // Debe tener exactamente 9 dígitos
        if (!telefono.matches("\\d{9}")) {
            return false;
        }

        // Opcional (más realista en España)
        // Los teléfonos suelen empezar por 6, 7, 8 o 9
        char primero = telefono.charAt(0);
        if (primero < '6' || primero > '9') {
            return false;
        }

        return true;
    }
}