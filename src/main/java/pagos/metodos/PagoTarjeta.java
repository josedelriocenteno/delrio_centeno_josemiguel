/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pagos.metodos;

import pagos.cuentas.Cuenta;
import pagos.cuentas.CuentaBancaria;
import pagos.excepciones.CantidadIncorrectaException;
import pagos.excepciones.SaldoInsuficienteException;
import pagos.generador.GeneradorComprobantes;
import pagos.validaciones.ValidadorPago;

/**
 *
 * @author delcenjo
 */
public class PagoTarjeta implements MetodoPago {

    private final String numeroTarjeta;
    private final String titular;
    private final CuentaBancaria cuentaBanco; // Cuenta asociada a la tarjeta
    private String comprobante;

    public PagoTarjeta(String numeroTarjeta, String titular, CuentaBancaria cuentaBanco) throws CantidadIncorrectaException {
        ValidadorPago.validarTarjeta(numeroTarjeta, titular);
        if (cuentaBanco == null) {
            throw new IllegalArgumentException("Debe asociar una cuenta bancaria a la tarjeta");
        }
        this.numeroTarjeta = numeroTarjeta;
        this.titular = titular.trim();
        this.cuentaBanco = cuentaBanco;
    }

    @Override
    public void pagar(double cantidad, Cuenta cuentaOrigen) throws CantidadIncorrectaException, SaldoInsuficienteException {
        ValidadorPago.validarCantidad(cantidad);
        cuentaBanco.retirar(cantidad);
        String ultimo4 = numeroTarjeta.substring(numeroTarjeta.length() - 4);
        String origen = String.format("%s ****%s", titular, ultimo4);
        comprobante = GeneradorComprobantes.generar("TARJETA", cantidad, origen, cuentaBanco.getSaldo());
    }

    @Override
    public String obtenerComprobante() {
        if (comprobante == null) {
            return "No se ha realizado ningún pago con tarjeta aún.";
        }
        return comprobante;
    }

    @Override
    public String getDescripcionMetodo() {
        String ultimo4 = numeroTarjeta.substring(numeroTarjeta.length() - 4);
        return String.format("%s (****%s)", titular, ultimo4);
    }

    @Override
    public boolean soportaCuenta(Cuenta cuenta) {
        return cuenta instanceof CuentaBancaria && cuenta == cuentaBanco;
    }

    public String getNumeroTarjetaParcial() {
        return "**** **** **** " + numeroTarjeta.substring(12);
    }

    public String getTitular() {
        return titular;
    }

    public CuentaBancaria getCuentaBanco() {
        return cuentaBanco;
    }
}
