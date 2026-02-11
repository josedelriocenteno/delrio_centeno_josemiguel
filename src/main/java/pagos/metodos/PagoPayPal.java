/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pagos.metodos;

import pagos.cuentas.Cuenta;
import pagos.cuentas.CuentaPayPal;
import pagos.excepciones.CantidadIncorrectaException;
import pagos.excepciones.SaldoInsuficienteException;
import pagos.generador.GeneradorComprobantes;
import pagos.validaciones.ValidadorPago;

/**
 *
 * @author delcenjo
 */
public class PagoPayPal implements MetodoPago {

    private final String emailPayPal;
    private String comprobante;

    public PagoPayPal(String emailPayPal) throws CantidadIncorrectaException {
        ValidadorPago.validarEmail(emailPayPal);
        this.emailPayPal = emailPayPal.toLowerCase().trim();
    }

    @Override
    public void pagar(double cantidad, Cuenta cuentaOrigen) throws CantidadIncorrectaException, SaldoInsuficienteException {
        if (!soportaCuenta(cuentaOrigen)) throw new CantidadIncorrectaException("Cuenta no compatible con PagoPayPal");
        CuentaPayPal cuenta = (CuentaPayPal) cuentaOrigen;
        cuenta.retirar(cantidad);
        comprobante = GeneradorComprobantes.generar("PAYPAL", cantidad, cuenta.getDescripcionCompleta(), cuenta.getSaldo());
    }

    @Override
    public String obtenerComprobante() {
        if (comprobante == null) return "No se ha realizado ningún pago PayPal aún.";
        return comprobante;
    }

    @Override
    public String getDescripcionMetodo() {
        return "PayPal: " + emailPayPal;
    }

    @Override
    public boolean soportaCuenta(Cuenta cuenta) {
        return cuenta instanceof CuentaPayPal && ((CuentaPayPal) cuenta).getEmail().equalsIgnoreCase(emailPayPal);
    }

    public String getEmailPayPal() {
        return emailPayPal;
    }
}
