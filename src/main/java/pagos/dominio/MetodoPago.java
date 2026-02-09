/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pagos.dominio;

import pagos.excepciones.CantidadIncorrectaException;

/**
 *
 * @author delcenjo
 */
public interface MetodoPago {
    void pagar(double cantidad) throws CantidadIncorrectaException;
    String obtenerComprobante();
}