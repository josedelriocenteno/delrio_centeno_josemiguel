package pagos.app;

import java.util.Scanner;

import pagos.cuentas.*;
import pagos.dominio.*;
import pagos.excepciones.*;
import pagos.util.GeneradorComprobantes;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== PASARELA DE PAGOS ===");

        System.out.print("Elige método de pago (Tarjeta / PayPal / Bizum): ");
        String metodo = sc.nextLine().trim();

        System.out.print("Introduce la cantidad a pagar: ");
        double cantidad;
        try {
            cantidad = Double.parseDouble(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Cantidad no válida");
            sc.close();
            return;
        }

        MetodoPago pago = null;

        try {
            switch (metodo.toLowerCase()) {
                case "tarjeta":
                    System.out.print("Titular de la tarjeta: ");
                    String titular = sc.nextLine().trim();
                    System.out.print("Número de tarjeta: ");
                    String numero = sc.nextLine().trim();
                    CuentaBancaria cuentaB = new CuentaBancaria(titular, 1000, numero);
                    pago = new PagoTarjeta(numero, titular);
                    break;

                case "paypal":
                    System.out.print("Titular de la cuenta PayPal: ");
                    String titularPay = sc.nextLine().trim();
                    System.out.print("Email PayPal: ");
                    String email = sc.nextLine().trim();
                    CuentaPayPal cuentaP = new CuentaPayPal(titularPay, 500, email);
                    pago = new PagoPayPal(email);
                    break;

                case "bizum":
                    System.out.print("Titular de Bizum: ");
                    String titularBiz = sc.nextLine().trim();
                    System.out.print("Teléfono Bizum: ");
                    String telefono = sc.nextLine().trim();
                    CuentaBizum cuentaBz = new CuentaBizum(titularBiz, 300, telefono);
                    pago = new PagoBizum(telefono);
                    break;

                default:
                    throw new MetodoPagoNoValidoException("Método de pago no válido");
            }

            pago.pagar(cantidad);
            System.out.println(GeneradorComprobantes.generar(
                metodo.toUpperCase(),
                cantidad,
                (metodo.equalsIgnoreCase("tarjeta") ? ((CuentaBancaria)new CuentaBancaria("dummy", 0, "dummy")).getDescripcion()
                                                    : metodo.equalsIgnoreCase("paypal") ? ((CuentaPayPal)new CuentaPayPal("dummy",0,"dummy")).getDescripcion()
                                                    : ((CuentaBizum)new CuentaBizum("dummy",0,"dummy")).getDescripcion()),
                -1 // saldo opcional en esta versión
            ));
        } catch (CantidadIncorrectaException | MetodoPagoNoValidoException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        sc.close();
    }
}
