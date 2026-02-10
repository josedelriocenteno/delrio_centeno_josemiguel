package pagos.dominio;

import pagos.cuentas.CuentaBizum;
import pagos.excepciones.CantidadIncorrectaException;
import pagos.excepciones.SaldoInsuficienteException;
import pagos.util.GeneradorComprobantes;
import pagos.validaciones.ValidadorPago;

public class PagoBizum implements MetodoPago {

    private CuentaBizum cuentaBizum;
    private String comprobante;

    public PagoBizum(CuentaBizum cuentaBizum) {
        if (cuentaBizum == null) {
            throw new IllegalArgumentException("Debe proporcionar una CuentaBizum");
        }
        this.cuentaBizum = cuentaBizum;
    }

    @Override
    public void pagar(double cantidad) throws CantidadIncorrectaException, SaldoInsuficienteException {
        ValidadorPago.validarCantidad(cantidad);
        cuentaBizum.retirar(cantidad);  // Solo CuentaBizum válida

        comprobante = GeneradorComprobantes.generar(
            "BIZUM",
            cantidad,
            cuentaBizum.getDescripcionCompleta(),
            cuentaBizum.getSaldo()
        );
    }

    @Override
    public String obtenerComprobante() {
        return comprobante;
    }

    @Override
    public String getDescripcionMetodo() {
        return "Pago Bizum desde " + cuentaBizum.getTelefono();
    }
}
