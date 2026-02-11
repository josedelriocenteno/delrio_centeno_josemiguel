/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pagos.cuentas;

import pagos.excepciones.CantidadIncorrectaException;
import pagos.excepciones.SaldoInsuficienteException;

/**
 *
 * @author delcenjo
 */
public abstract class Cuenta {

    protected final String idCuenta;
    protected String titular;
    protected double saldo;
    private double limiteDiario;

    public Cuenta(String idCuenta, String titular, double saldoInicial, double limiteDiario) {
        if (idCuenta == null || idCuenta.trim().isEmpty()) throw new IllegalArgumentException("ID cuenta requerido");
        if (saldoInicial < 0) throw new IllegalArgumentException("Límite diario inválido");
        this.idCuenta = idCuenta.trim();
        this.titular = titular != null ? titular.trim() : "";
        this.saldo = saldoInicial;
        this.limiteDiario = limiteDiario;
    }

    public void retirar(double cantidad) throws SaldoInsuficienteException, CantidadIncorrectaException {
        validarCantidad(cantidad);
        if (cantidad > saldo) throw new SaldoInsuficienteException(String.format("Saldo %.2f€ insuficiente para %.2f€", saldo, cantidad));
        if (cantidad > limiteDiario) throw new CantidadIncorrectaException("Excede límite diario: " + limiteDiario + "€");
        saldo -= cantidad;
    }

    protected void validarCantidad(double cantidad) throws CantidadIncorrectaException {
        if (cantidad <= 0) throw new CantidadIncorrectaException("Cantidad debe ser mayor que 0");
        if (Math.round(cantidad * 100) != cantidad * 100) throw new CantidadIncorrectaException("Solo se permiten 2 decimales");
    }

    public void recargar(double cantidad) throws CantidadIncorrectaException {
        validarCantidad(cantidad);
        saldo += cantidad;
    }

    public abstract String getDescripcionCompleta();

    public String getIdCuenta() { return idCuenta; }

    public String getTitular() { return titular; }

    public void setTitular(String titular) {
        if (titular == null) {
            this.titular = "";
        } else {
            this.titular = titular.trim();
        }
    }

    public double getSaldo() { return saldo; }

    public double getLimiteDiario() { return limiteDiario; }

    @Override
    public String toString() {
        return idCuenta + " | " + titular + " | " + saldo + "€ | Límite: " + limiteDiario + "€";
    }
}