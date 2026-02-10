package pagos.dominio;

import pagos.cuentas.Cuenta;
import pagos.excepciones.CantidadIncorrectaException;

/**
 *
 * @author delcenjo
 */

public class PagoPayPal implements MetodoPago {

    private String email;
    private String comprobante;

    public PagoPayPal(String email) {
        this.email =email;
    }

    @Override
    public void pagar(double cantidad) throws CantidadIncorrectaException {
        if (cantidad <= 0) {
            throw new CantidadIncorrectaException("Cantidad no válida");
        }

        comprobante = "Pago con PAYPAL de " + cantidad + " EUR - Cuenta: " + email;
    }

    @Override
    public String obtenerComprobante() {
        return comprobante;
    }
}
