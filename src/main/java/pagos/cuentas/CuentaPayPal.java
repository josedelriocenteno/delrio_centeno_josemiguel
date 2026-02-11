package pagos.cuentas;

import pagos.validaciones.ValidadorPago;
import pagos.excepciones.SaldoInsuficienteException;
import pagos.excepciones.CantidadIncorrectaException;

public class CuentaPayPal extends Cuenta {

    private static final double COMISION_PORCENTAJE = 0.029;
    private static final double COMISION_FIJA = 0.30;

    private final String email;
    private final String moneda;

    public CuentaPayPal(String idCuenta, String titular, double saldoInicial,
                        String email, String moneda) throws CantidadIncorrectaException {
        super(idCuenta, titular, saldoInicial, 7500.0); // límite diario PayPal

        if (email == null || email.trim().isEmpty() || !ValidadorPago.esEmailValido(email)) {
            throw new CantidadIncorrectaException("Email PayPal inválido: " + email);
        }

        this.email = email.toLowerCase().trim();
        this.moneda = (moneda == null || moneda.isBlank())
                ? "EUR"
                : moneda.toUpperCase();
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getDescripcionCompleta() {
        return String.format("💰 %s | PayPal %s (%s) | %.2f %s",
                titular, email, idCuenta, saldo, moneda);
    }

    @Override
    public void retirar(double cantidad)
            throws SaldoInsuficienteException, CantidadIncorrectaException {

        if (cantidad <= 0) {
            throw new CantidadIncorrectaException("Cantidad inválida para PayPal");
        }

        double comision = (cantidad * COMISION_PORCENTAJE) + COMISION_FIJA;
        double total = cantidad + comision;

        // 🔹 Redondeo a 2 decimales para evitar error de validación
        total = Math.round(total * 100.0) / 100.0;

        super.retirar(total);
    }

    public void enviarDineroPayPal(CuentaPayPal destino,
                                   double cantidad,
                                   String nota)
            throws SaldoInsuficienteException, CantidadIncorrectaException {

        if (destino == null || destino == this) {
            throw new CantidadIncorrectaException("Destino PayPal inválido");
        }

        if (nota == null || nota.trim().isEmpty() || nota.length() > 165) {
            throw new CantidadIncorrectaException("Nota obligatoria (máx 165 caracteres)");
        }

        retirar(cantidad); // incluye comisión
        destino.recargar(cantidad); // recibe monto completo
    }

    public String getEmailParcial() {
        int atIndex = email.indexOf('@');
        if (atIndex < 0) return email;
        return email.substring(0, 3) + "***" + email.substring(atIndex);
    }

    public String getMoneda() {
        return moneda;
    }

    @Override
    public String toString() {
        return String.format("PAYPAL | %s | %s | %.2f %s",
                idCuenta, getEmailParcial(), saldo, moneda);
    }
}