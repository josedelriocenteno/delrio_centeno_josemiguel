/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pagos.metodos;

import pagos.excepciones.CantidadIncorrectaException;
import pagos.excepciones.SaldoInsuficienteException;
import pagos.cuentas.Cuenta;

/**
 *
 * @author delcenjo
 */
public interface MetodoPago {

    void pagar(double cantidad, Cuenta cuentaOrigen) throws CantidadIncorrectaException, SaldoInsuficienteException;
    String obtenerComprobante();
    String getDescripcionMetodo();
    boolean soportaCuenta(Cuenta cuenta);
}