package pagos.dominio;

import pagos.cuentas.Cuenta;
import pagos.excepciones.CantidadIncorrectaException;
import pagos.excepciones.SaldoInsuficienteException;
import pagos.util.GeneradorComprobantes;
import pagos.validaciones.ValidadorPago;

public class PagoPayPal implements MetodoPago {

    private String email;
    private String comprobante;

    public PagoPayPal(String email) {
        ValidadorPago.validarEmail(email);
        this.email = email;
    }

    @Override
    public void pagar(Cuenta cuentaOrigen, double cantidad)
            throws CantidadIncorrectaException, SaldoInsuficienteException {

        ValidadorPago.validarCantidad(cantidad);
        cuentaOrigen.retirar(cantidad);

        comprobante = GeneradorComprobantes.generar(
                "PAYPAL",
                cantidad,
                cuentaOrigen.getDescripcion(),
                cuentaOrigen.getSaldo()
        );
    }

    @Override
    public String obtenerComprobante() {
        return comprobante;
    }
}