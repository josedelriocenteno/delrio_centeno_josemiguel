/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pagos.app;

/**
 *
 * @author delcenjo
 */
public class Main {
    public static void main(String[] args) {
        Cuenta cuenta = new CuentaBancaria("Juan Pérez", 500.0, "ES0012345678901234567890");

MetodoPago metodo = new PagoPayPal("juan@example.com");

try {
    metodo.pagar(cuenta, 100.0);
    System.out.println(metodo.obtenerComprobante());
} catch (CantidadIncorrectaException | SaldoInsuficienteException e) {
    System.err.println("Error en el pago: " + e.getMessage());
}
    }
}
