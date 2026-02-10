package pagos.dominio;

import pagos.excepciones.CantidadIncorrectaException;

/**
 *
 * @author delcenjo
 */
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
        if (cantidad <= 0) {
            throw new CantidadIncorrectaException("Cantidad no válida");
        }

        comprobante = "Pago con TARJETA de " + cantidad + " EUR - Titular: "
                + titular + " - ****"
                + numeroTarjeta.substring(numeroTarjeta.length() - 4);
    }

    @Override
    public String obtenerComprobante() {
        return comprobante;
    }
}
