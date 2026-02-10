ackage pagos.dominio;

import pagos.cuentas.Cuenta;
import pagos.excepciones.CantidadIncorrectaException;
import pagos.excepciones.SaldoInsuficienteException;
import pagos.util.GeneradorComprobantes;
import pagos.validaciones.ValidadorPago;

public class PagoBizum implements MetodoPago {

    private String telefono;
    private String comprobante;

    public PagoBizum(String telefono) {
        ValidadorPago.validarTelefonoBizum(telefono);
        this.telefono = telefono;
    }

    @Override
    public void pagar(Cuenta cuentaOrigen, double cantidad)
            throws CantidadIncorrectaException, SaldoInsuficienteException {

        ValidadorPago.validarCantidad(cantidad);
        cuentaOrigen.retirar(cantidad); // Puede lanzar SaldoInsuficienteException

        comprobante = GeneradorComprobantes.generar(
                "BIZUM",
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