package pagos.dominio;

import pagos.excepciones.CantidadIncorrectaException;
import pagos.excepciones.SaldoInsuficienteException;

public interface MetodoPago {
    void pagar(double cantidad) throws CantidadIncorrectaException, SaldoInsuficienteException;
    String obtenerComprobante();
    String getDescripcionMetodo();  // Para mostrar en UI
}
