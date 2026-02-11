/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pagos.cuentas;

/**
 *
 * @author delcenjo
 */
import pagos.validaciones.ValidadorPago;
import pagos.excepciones.SaldoInsuficienteException;
import pagos.excepciones.CantidadIncorrectaException;

public class CuentaPayPal extends Cuenta {

    private static final double COMISION_PORCENTAJE = 0.029;
    private static final double COMISION_FIJA = 0.30;
    private final String email;

    public CuentaPayPal(String idCuenta, String titular, double saldoInicial, String email) throws CantidadIncorrectaException {
        super(idCuenta, titular, saldoInicial, 7500.0);
        if (email == null || email.trim().isEmpty() || !ValidadorPago.esEmailValido(email)) throw new CantidadIncorrectaException("Email PayPal inválido: " + email);
        this.email = email.toLowerCase().trim();
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getDescripcionCompleta() {
        return titular + " | PayPal " + email + " (" + idCuenta + ") | " + saldo + " EUR";
    }

    @Override
    public void retirar(double cantidad) throws SaldoInsuficienteException, CantidadIncorrectaException {
        if (cantidad <= 0) throw new CantidadIncorrectaException("Cantidad inválida para PayPal");
        double comision = (cantidad * COMISION_PORCENTAJE) + COMISION_FIJA;
        double total = cantidad + comision;
        total = Math.round(total * 100.0) / 100.0;
        super.retirar(total);
    }

    public void enviarDineroPayPal(CuentaPayPal destino, double cantidad, String nota) throws SaldoInsuficienteException, CantidadIncorrectaException {
        if (destino == null || destino == this) throw new CantidadIncorrectaException("Destino paypal inválido");
        if (nota == null || nota.trim().isEmpty() || nota.length() > 165) throw new CantidadIncorrectaException("Nota obligatoria (máx 165 carscteres)");
        retirar(cantidad);
        destino.recargar(cantidad);
    }

    public String getEmailParcial() {
        int posicion = email.indexOf('@');
        if (posicion < 0) return email;
        return email.substring(0, 3) + "***" + email.substring(posicion);
    }

    @Override
    public String toString() {
        return "PAYPAL | " + idCuenta + " | " + getEmailParcial() + " | " + saldo + " EUR";
    }
}