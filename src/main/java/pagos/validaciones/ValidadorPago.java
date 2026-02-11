/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pagos.validaciones;

import pagos.excepciones.CantidadIncorrectaException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author delcenjo
 */
public class ValidadorPago {

    private static final String EMAIL_PATRON = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final String TELEFONO_PATRON = "\\d{9}";
    private static final String IBAN_PATORN = "[A-Z]{2}\\d{22}";
    private static final double MAX_CANTIDAD_PAGO = 9999.99;

    public static void validarCantidad(double cantidad) throws CantidadIncorrectaException {
        if (cantidad <= 0) {
            throw new CantidadIncorrectaException("Cantidad debe ser mayor que 0");
        }
        if (cantidad > MAX_CANTIDAD_PAGO) {
            throw new CantidadIncorrectaException("Cantidad excede límite diario: " + MAX_CANTIDAD_PAGO);
        }
        if (cantidad != Math.floor(cantidad * 100) / 100) {
            throw new CantidadIncorrectaException("Solo 2 decimales permitidos");
        }
    }

    public static boolean esEmailValido(String email) {
        if (email == null || email.trim().isEmpty()) return false;
        Pattern pattern = Pattern.compile(EMAIL_PATRON);
        Matcher matcher = pattern.matcher(email.trim());
        return matcher.matches();
    }

    public static void validarEmail(String email) throws CantidadIncorrectaException {
        if (!esEmailValido(email)) {
            throw new CantidadIncorrectaException("Formato de email inválido: " + email);
        }
    }

    public static boolean esTelefonoBizumValido(String telefono) {
        if (telefono == null) return false;
        return telefono.matches(TELEFONO_PATRON);
    }

    public static void validarTelefonoBizum(String telefono) throws CantidadIncorrectaException {
        if (!esTelefonoBizumValido(telefono)) {
            throw new CantidadIncorrectaException("Teléfono Bizum debe tener 9 dígitos: " + telefono);
        }
    }

    public static boolean esIbanValido(String iban) {
        if (iban == null) return false;
        String ibanLimpio = iban.replaceAll("\\s+", "").toUpperCase();
        return ibanLimpio.matches(IBAN_PATORN);
    }

    public static void validarIBAN(String iban) throws CantidadIncorrectaException {
        if (!esIbanValido(iban)) {
            throw new CantidadIncorrectaException("IBAN inválido (formato: ESXX XXXX XXXX XXXX XXXX XX): " + iban);
        }
    }

    public static void validarLimiteBizum(double cantidad) throws CantidadIncorrectaException {
        if (cantidad > 1000) {
            throw new CantidadIncorrectaException("Bizum máximo 1000€ por operación");
        }
    }
    
    public static void validarTarjeta(String numeroTarjeta, String titular) throws CantidadIncorrectaException {
        if (numeroTarjeta == null || !numeroTarjeta.matches("\\d{16}")) {
            throw new CantidadIncorrectaException("Tarjeta debe tener 16 dígitos");
        }
        if (titular == null || titular.trim().isEmpty()) {
            throw new CantidadIncorrectaException("Titular requerido");
        }
    }
}