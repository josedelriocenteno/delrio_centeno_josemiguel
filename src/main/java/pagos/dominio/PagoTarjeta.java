package pagos.dominio;

import pagos.cuentas.Cuenta;
import pagos.excepciones.CantidadIncorrectaException;
import pagos.excepciones.SaldoInsuficienteException;
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
    public void pagar(Cuenta cuentaOrigen, double cantidad)
            throws CantidadIncorrectaException, SaldoInsuficienteException {

        ValidadorPago.validarCantidad(cantidad);
        cuentaOrigen.retirar(cantidad);

        String origen = "Tarjeta de " + titular + " - ****"
                + numeroTarjeta.substring(numeroTarjeta.length() - 4);

        comprobante = GeneradorComprobantes.generar(
                "TARJETA",
                cantidad,
                origen,
                cuentaOrigen.getSaldo()
        );
    }

    @Override
    public String obtenerComprobante() {
        return comprobante;
    }
}