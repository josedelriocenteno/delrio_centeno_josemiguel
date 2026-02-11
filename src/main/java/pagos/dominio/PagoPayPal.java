package pagos.dominio;

import pagos.cuentas.Cuenta;
import pagos.cuentas.CuentaPayPal;
import pagos.excepciones.CantidadIncorrectaException;
import pagos.excepciones.SaldoInsuficienteException;
import pagos.util.GeneradorComprobantes;
import pagos.validaciones.ValidadorPago;

public class PagoPayPal implements MetodoPago {

    private final String emailPayPal;
    private String comprobante;

    public PagoPayPal(String emailPayPal) throws CantidadIncorrectaException {
        ValidadorPago.validarEmail(emailPayPal);
        this.emailPayPal = emailPayPal.toLowerCase().trim();
    }

    @Override
    public void pagar(double cantidad, Cuenta cuentaOrigen)
            throws CantidadIncorrectaException, SaldoInsuficienteException {

        if (!soportaCuenta(cuentaOrigen)) {
            throw new CantidadIncorrectaException("Cuenta no compatible con PagoPayPal");
        }

        ValidadorPago.validarCantidad(cantidad);

        CuentaPayPal cuenta = (CuentaPayPal) cuentaOrigen;

        // Delegamos validaciones de saldo y límites a la propia cuenta
        cuenta.retirar(cantidad);

        this.comprobante = GeneradorComprobantes.generar(
                "💳 PAYPAL",
                cantidad,
                cuenta.getDescripcionCompleta(),
                cuenta.getSaldo()
        );
    }

    @Override
    public String obtenerComprobante() {
        return comprobante != null
                ? comprobante
                : "No se ha realizado ningún pago PayPal aún.";
    }

    @Override
    public String getDescripcionMetodo() {
        return "PayPal: " + emailPayPal;
    }

    @Override
    public boolean soportaCuenta(Cuenta cuenta) {
        return cuenta instanceof CuentaPayPal &&
               ((CuentaPayPal) cuenta).getEmail().equalsIgnoreCase(this.emailPayPal);
    }

    public String getEmailPayPal() {
        return emailPayPal;
    }
}