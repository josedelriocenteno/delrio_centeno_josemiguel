package pagos.dominio;

import pagos.excepciones.CantidadIncorrectaException;
import pagos.util.GeneradorComprobantes;
import pagos.validaciones.ValidadorPago;

public class PagoTarjeta implements MetodoPago {

    private String numeroTarjeta;
    private String titular;
    private String comprobante;

    public PagoTarjeta(String numeroTarjeta, String titular) {
        this.numeroTarjeta = numeroTarjeta;
        this.titular = titular;
    }

    @Override
    public void pagar(double cantidad) throws CantidadIncorrectaException {
        ValidadorPago.validarCantidad(cantidad);
        // No hay cuenta: pago directo al banco emisor (sin SaldoInsuficiente)

        String origen = "Tarjeta " + titular + " - ****" + numeroTarjeta.substring(numeroTarjeta.length() - 4);
        comprobante = GeneradorComprobantes.generar("TARJETA", cantidad, origen, 0.0);
    }

    @Override
    public String obtenerComprobante() {
        return comprobante;
    }

    @Override
    public String getDescripcionMetodo() {
        return "Pago Tarjeta " + titular;
    }
}
