/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pagos.metodos;

import pagos.cuentas.Cuenta;
import pagos.cuentas.CuentaBizum;
import pagos.excepciones.CantidadIncorrectaException;
import pagos.excepciones.SaldoInsuficienteException;
import pagos.generador.GeneradorComprobantes;
import pagos.validaciones.ValidadorPago;

/**
 *
 * @author delcenjo
 */
public class PagoBizum implements MetodoPago {

    private final String telefonoBizum;
    private String comprobante;

    public PagoBizum(String telefonoBizum) throws CantidadIncorrectaException {
        ValidadorPago.validarTelefonoBizum(telefonoBizum);
        this.telefonoBizum = telefonoBizum;
    }

    @Override
    public void pagar(double cantidad, Cuenta cuentaOrigen) throws CantidadIncorrectaException, SaldoInsuficienteException {
        if (!soportaCuenta(cuentaOrigen)) throw new CantidadIncorrectaException("Cuenta no compatible con PagoBizum");
        CuentaBizum cuenta = (CuentaBizum) cuentaOrigen;
        cuenta.retirar(cantidad);
        this.comprobante = GeneradorComprobantes.generar("BIZUM", cantidad, cuenta.getDescripcionCompleta(), cuenta.getSaldo());
    }


    @Override
    public String obtenerComprobante() {
        if (comprobante == null) return "No se ha realizado ningún pago Bizum aún.";
        return comprobante;
    }


    @Override
    public String getDescripcionMetodo() {
        return "Bizum: " + telefonoBizum;
    }

    @Override
    public boolean soportaCuenta(Cuenta cuenta) {
        return cuenta instanceof CuentaBizum && ((CuentaBizum) cuenta).getTelefono().equals(this.telefonoBizum);
    }

    public String getTelefonoBizum() {
        return telefonoBizum;
    }
}
