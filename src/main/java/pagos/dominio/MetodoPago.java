package pagos.dominio;

import pagos.cuentas.Cuenta;
import pagos.excepciones.CantidadIncorrectaException;
import pagos.excepciones.SaldoInsuficienteException;

public interface MetodoPago {

    void pagar(Cuenta cuentaOrigen, double cantidad)
            throws CantidadIncorrectaException, SaldoInsuficienteException;

    String obtenerComprobante();
}