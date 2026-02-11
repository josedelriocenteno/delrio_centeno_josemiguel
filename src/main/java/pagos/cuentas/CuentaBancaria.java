package pagos.cuentas;

import pagos.validaciones.ValidadorPago;
import pagos.excepciones.SaldoInsuficienteException;
import pagos.excepciones.CantidadIncorrectaException;

public class CuentaBancaria extends Cuenta {
    private static final double COMISION_TRANSFERENCIA = 0.50;  // comisión propia al retirar
    private static final double COMISION_DESTINO = 0.005;       // 0.5% que llega al destinatario

    private final String iban;

    public CuentaBancaria(String idCuenta, String titular, double saldoInicial, String iban) 
            throws CantidadIncorrectaException {

        super(idCuenta, titular, saldoInicial, 10000.0);  // límite diario alto

        if (iban == null) {
            throw new CantidadIncorrectaException("IBAN no puede ser nulo");
        }

        // Normalizar: eliminar espacios y pasar a mayúsculas
        String cleanIban = iban.replaceAll("\\s+", "").toUpperCase();

        // Validar IBAN y lanzar CantidadIncorrectaException si es inválido
        if (!ValidadorPago.esIbanValido(cleanIban)) {
            throw new CantidadIncorrectaException(
                "IBAN inválido (formato: ESXX XXXX XXXX XXXX XXXX XX): " + cleanIban
            );
        }

        this.iban = cleanIban;
    }

    public String getIbanParcial() {
        if (iban.length() < 8) return iban;
        return iban.substring(0, 4) + "********" + iban.substring(iban.length() - 4);
    }

    @Override
    public String getDescripcionCompleta() {
        return String.format("🏦 %s | %s | IBAN %s | %.2f€",
                titular, idCuenta, getIbanParcial(), saldo);
    }

    @Override
    public void retirar(double cantidad) 
            throws SaldoInsuficienteException, CantidadIncorrectaException {
        // Añadimos comisión fija de la entidad
        double total = cantidad + COMISION_TRANSFERENCIA;
        super.retirar(total);
    }

    public void transferirACuenta(CuentaBancaria destino, double cantidad)
            throws SaldoInsuficienteException, CantidadIncorrectaException {

        if (destino == null || destino == this) {
            throw new CantidadIncorrectaException("Destino inválido");
        }

        ValidadorPago.validarCantidad(cantidad); // validación de cantidad
        retirar(cantidad);                        // retira con comisión propia

        double recibido = cantidad * (1 - COMISION_DESTINO); // 0.5% de comisión banco destino
        destino.recargar(recibido);
    }

    @Override
    public String toString() {
        return String.format("BANCO | %s | %s | %.2f€",
                idCuenta, getIbanParcial(), saldo);
    }

    public String getIban() {
        return iban;
    }
}