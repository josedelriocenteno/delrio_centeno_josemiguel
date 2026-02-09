package pagos.dominio;

import pagos.cuentas.Cuenta;
import pagos.excepciones.CantidadIncorrectaException;

/**
 *
 * @author delcenjo
 */
public class PagoBizum implements MetodoPago {

    private Cuenta cuenta;
    private String comprobante;

    public PagoBizum(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    @Override
    public void pagar(double cantidad) throws CantidadIncorrectaException {
        if (cantidad <= 0) {
            throw new CantidadIncorrectaException(
                    "La cantidad a pagar por Bizum debe ser mayor que 0"
            );
        }

        // Simulación del pago
        comprobante = "Pago de " + cantidad + "€ realizado mediante BIZUM\n"
                    + "Origen: " + cuenta.getDescripcion();
    }

    @Override
    public String obtenerComprobante() {
        return comprobante;
    }
}
