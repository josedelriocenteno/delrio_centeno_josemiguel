package pagos.validaciones;

import pagos.excepciones.CantidadIncorrectaException;

public class ValidadorPago {

    private ValidadorPago() {
        // Clase utilitaria, no se puede instanciar
    }

    public static void validarCantidad(double cantidad) throws CantidadIncorrectaException {
        if (cantidad <= 0) {
            throw new CantidadIncorrectaException("La cantidad debe ser mayor que cero");
        }
    }

    public static void validarEmail(String email) throws IllegalArgumentException {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Email no válido");
        }
    }

    public static void validarTelefonoBizum(String telefono) throws IllegalArgumentException {
        if (telefono == null || !telefono.matches("\\d{9}")) {
            throw new IllegalArgumentException("Teléfono Bizum no válido");
        }
    }

    public static void validarIBAN(String iban) throws IllegalArgumentException {
        if (iban == null || !iban.matches("[A-Z]{2}\\d{22}")) {
            throw new IllegalArgumentException("IBAN no válido");
        }
    }
}
