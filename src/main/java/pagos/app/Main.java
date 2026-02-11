package pagos.app;

import pagos.cuentas.*;
import pagos.dominio.*;
import pagos.excepciones.CantidadIncorrectaException;
import pagos.excepciones.SaldoInsuficienteException;

public class Main {
    public static void main(String[] args) {
        try {
            // --- Crear cuentas ---
            CuentaBancaria banco = new CuentaBancaria("C001", "Juan Pérez", 5000, "ES7921000813610123456789");
            CuentaBizum bizum1 = new CuentaBizum("B001", "Ana Gómez", 800, "612345678");
            CuentaBizum bizum2 = new CuentaBizum("B002", "Luis Ruiz", 300, "698765432");
            CuentaPayPal paypal = new CuentaPayPal("P001", "Marta Díaz", 1200, "marta@email.com", "EUR");

            System.out.println("Cuentas creadas correctamente:");
            System.out.println(banco);
            System.out.println(bizum1);
            System.out.println(bizum2);
            System.out.println(paypal);
            System.out.println();

            // --- Probar PagoBizum ---
            PagoBizum pagoBizum = new PagoBizum(bizum1.getTelefono());
            pagoBizum.pagar(200, bizum1);
            System.out.println("Pago Bizum realizado:");
            System.out.println(pagoBizum.obtenerComprobante());
            System.out.println(bizum1);

            // Enviar Bizum a otra cuenta
            bizum1.enviarBizum(bizum2, 100, "Cena de ayer");
            System.out.println("Bizum enviado de Ana a Luis:");
            System.out.println(bizum1);
            System.out.println(bizum2);
            System.out.println();

            // --- Probar PagoPayPal ---
            PagoPayPal pagoPayPal = new PagoPayPal(paypal.getEmail());
            pagoPayPal.pagar(150, paypal);
            System.out.println("Pago PayPal realizado:");
            System.out.println(pagoPayPal.obtenerComprobante());
            System.out.println(paypal);
            System.out.println();

            // --- Probar PagoTarjeta ---
            PagoTarjeta tarjeta = new PagoTarjeta("1234567812345678", "Juan Pérez");
            tarjeta.pagar(50, banco); // opcional pasar cuenta para registro
            System.out.println("Pago con tarjeta realizado:");
            System.out.println(tarjeta.obtenerComprobante());
            System.out.println(banco);
            System.out.println();

            // --- Probar transferencia bancaria ---
            CuentaBancaria banco2 = new CuentaBancaria("C002", "Laura Sánchez", 1000, "ES9121000813610123456789");
            banco.transferirACuenta(banco2, 500);
            System.out.println("Transferencia bancaria de Juan a Laura:");
            System.out.println(banco);
            System.out.println(banco2);

        } catch (CantidadIncorrectaException | SaldoInsuficienteException e) {
            System.err.println("Error en la operación: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Error de datos: " + e.getMessage());
        }
    }
}