package pagos.dominio;

import pagos.cuentas.Cuenta;
import pagos.excepciones.CantidadIncorrectaException;
import pagos.util.GeneradorComprobantes;
import pagos.validaciones.ValidadorPago;

public class PagoTarjeta implements MetodoPago {

    private final String numeroTarjeta;
    private final String titular;
    private String comprobante;

    public PagoTarjeta(String numeroTarjeta, String titular) {
        if (numeroTarjeta == null || !numeroTarjeta.matches("\\d{16}")) {
            throw new IllegalArgumentException("Tarjeta debe tener 16 dígitos");
        }
        if (titular == null || titular.trim().isEmpty()) {
            throw new IllegalArgumentException("Titular requerido");
        }
        this.numeroTarjeta = numeroTarjeta;
        this.titular = titular.trim();
    }

    @Override
    public void pagar(double cantidad, Cuenta cuentaOrigen) throws CantidadIncorrectaException {
        ValidadorPago.validarCantidad(cantidad);

        String ultimo4 = numeroTarjeta.substring(numeroTarjeta.length() - 4);
        String origen = String.format("💳 %s ****%s", titular, ultimo4);

        // Simulación del procesamiento de la tarjeta
        simularProcesamientoTarjeta(cantidad);

        // Registrar en cuenta como gasto opcional
        if (cuentaOrigen != null) {
            try {
                cuentaOrigen.recargar(-cantidad);  // Gasto negativo
            } catch (CantidadIncorrectaException e) {
                throw new CantidadIncorrectaException("Error al registrar gasto en cuenta: " + e.getMessage());
            }
        }

        this.comprobante = GeneradorComprobantes.generar(
                "💳 TARJETA",
                cantidad,
                origen,
                0.0  // Sin saldo (crédito)
        );
    }

    private void simularProcesamientoTarjeta(double cantidad) throws CantidadIncorrectaException {
        try {
            Thread.sleep(1200 + (int) (Math.random() * 800)); // 1.2-2s latencia
            double azar = Math.random();
            if (cantidad > 500 && azar < 0.05) { // 5% rechazo pagos grandes
                throw new CantidadIncorrectaException("Tarjeta rechazada - límite excedido");
            }
            if (azar < 0.015) { // 1.5% fallo genérico
                throw new CantidadIncorrectaException("Tarjeta rechazada por banco");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new CantidadIncorrectaException("Procesamiento interrumpido");
        }
    }

    @Override
    public String obtenerComprobante() {
        return comprobante != null ? comprobante : "No se ha realizado ningún pago con tarjeta aún.";
    }

    @Override
    public String getDescripcionMetodo() {
        String ultimo4 = numeroTarjeta.substring(numeroTarjeta.length() - 4);
        return String.format("💳 %s (****%s)", titular, ultimo4);
    }

    @Override
    public boolean soportaCuenta(Cuenta cuenta) {
        return true; // Tarjetas universales
    }

    public String getNumeroTarjetaParcial() {
        return "**** **** **** " + numeroTarjeta.substring(12);
    }

    public String getTitular() {
        return titular;
    }
}