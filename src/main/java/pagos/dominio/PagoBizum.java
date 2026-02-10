package pagos.dominio;

import pagos.cuentas.Cuenta;
import pagos.excepciones.CantidadIncorrectaException;

/**
 *
 * @author delcenjo
 */
public class PagoBizum implements MetodoPago {

    private String telefono;
    private String comprobante;

    public PagoBizum(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public void pagar(double cantidad) throws CantidadIncorrectaException {
        if (cantidad <= 0) {
            throw new CantidadIncorrectaException("Cantidad no válida");
        }

        comprobante = "Pago con BIZUM de " + cantidad + " EUR - Teléfono: " + telefono;
    }

    @Override
    public String obtenerComprobante() {
        return comprobante;
    }
}
