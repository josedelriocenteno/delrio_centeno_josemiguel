package pagos.dominio;

import pagos.cuentas.CuentaPayPal;
import pagos.excepciones.CantidadIncorrectaException;
import pagos.excepciones.SaldoInsuficienteException;
import pagos.util.GeneradorComprobantes;
import pagos.validaciones.ValidadorPago;

public class PagoPayPal implements MetodoPago {

    private CuentaPayPal cuentaPayPal;
    private String comprobante;

    public PagoPayPal(CuentaPayPal cuentaPayPal) {
        if (cuentaPayPal == null) {
            throw new IllegalArgumentException("Debe proporcionar una CuentaPayPal");
        }
        this.cuentaPayPal = cuentaPayPal;
    }

    @Override
    public void pagar(double cantidad) throws CantidadIncorrectaException, SaldoInsuficienteException {
        ValidadorPago.validarCantidad(cantidad);
        cuentaPayPal.retirar(cantidad);

        comprobante = GeneradorComprobantes.generar(
            "PAYPAL",
            cantidad,
            cuentaPayPal.getDescripcionCompleta(),
            cuentaPayPal.getSaldo()
        );
    }

    @Override
    public String obtenerComprobante() {
        return comprobante;
    }

    @Override
    public String getDescripcionMetodo() {
        return "Pago PayPal desde " + cuentaPayPal.getEmail();
    }
}
