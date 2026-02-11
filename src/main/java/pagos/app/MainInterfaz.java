package pagos.app;

import pagos.cuentas.*;
import pagos.dominio.*;
import pagos.excepciones.CantidadIncorrectaException;
import pagos.excepciones.SaldoInsuficienteException;

import javax.swing.*;

public class MainInterfaz {
    public static void main(String[] args) {
        try {
            // --- Crear cuentas de prueba ---
            CuentaBancaria banco = new CuentaBancaria("C001", "Juan Pérez", 5000, "ES7921000813610123456789");
            CuentaBizum bizum1 = new CuentaBizum("B001", "Ana Gómez", 800, "612345678");
            CuentaPayPal paypal = new CuentaPayPal("P001", "Marta Díaz", 1200, "marta@email.com", "EUR");

            JOptionPane.showMessageDialog(null,
                    String.format("Cuentas creadas correctamente:\nBanco: %.2f€\nBizum: %.2f€\nPayPal: %.2f€",
                            banco.getSaldo(), bizum1.getSaldo(), paypal.getSaldo()));

            // --- Interfaz de prueba ---
            String[] opciones = {"Bizum", "PayPal", "Tarjeta", "Salir"};

            while (true) {
                int seleccion = JOptionPane.showOptionDialog(
                        null,
                        "Selecciona un método de pago",
                        "Prueba de Pagos",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        opciones,
                        opciones[0]
                );

                if (seleccion == 3 || seleccion == JOptionPane.CLOSED_OPTION) {
                    break; // Salir
                }

                try {
                    String cantidadStr = JOptionPane.showInputDialog("Cantidad a pagar:");
                    double cantidad = Math.round(Double.parseDouble(cantidadStr) * 100.0) / 100.0;

                    switch (seleccion) {
                        case 0 -> { // Bizum
                            PagoBizum pago = new PagoBizum(bizum1.getTelefono());
                            pago.pagar(cantidad, bizum1);
                            JOptionPane.showMessageDialog(null, pago.obtenerComprobante());
                        }
                        case 1 -> { // PayPal
                            PagoPayPal pago = new PagoPayPal(paypal.getEmail());
                            pago.pagar(cantidad, paypal);
                            JOptionPane.showMessageDialog(null, pago.obtenerComprobante());
                        }
                        case 2 -> { // Tarjeta
                            PagoTarjeta tarjeta = new PagoTarjeta("1234567812345678", "Juan Pérez");
                            tarjeta.pagar(cantidad, null);
                            JOptionPane.showMessageDialog(null, tarjeta.obtenerComprobante());
                        }
                    }

                    // Mostrar saldos actualizados
                    JOptionPane.showMessageDialog(null,
                            String.format("Saldos actualizados:\nBanco: %.2f€\nBizum: %.2f€\nPayPal: %.2f€",
                                    banco.getSaldo(), bizum1.getSaldo(), paypal.getSaldo()));

                } catch (CantidadIncorrectaException | SaldoInsuficienteException e) {
                    JOptionPane.showMessageDialog(null, "Error al pagar: " + e.getMessage());
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Cantidad inválida");
                }
            }

        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Error creando cuentas: " + e.getMessage());
        } catch (CantidadIncorrectaException e){
            JOptionPane.showMessageDialog(null, "Cantidad incorrecta: " + e.getMessage());
        }
    }
}