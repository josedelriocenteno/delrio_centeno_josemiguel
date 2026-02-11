package pagos.dominio;

import pagos.excepciones.CantidadIncorrectaException;
import pagos.excepciones.SaldoInsuficienteException;
import pagos.cuentas.Cuenta;

public interface MetodoPago {

    void pagar(double cantidad, Cuenta cuentaOrigen)
        throws CantidadIncorrectaException, SaldoInsuficienteException;

    String obtenerComprobante();

    String getDescripcionMetodo();

    boolean soportaCuenta(Cuenta cuenta);
}