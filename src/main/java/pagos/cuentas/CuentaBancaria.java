/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pagos.cuentas;

/**
 *
 * @author delcenjo
 */
import pagos.validaciones.ValidadorPago;
import pagos.excepciones.SaldoInsuficienteException;
import pagos.excepciones.CantidadIncorrectaException;

public class CuentaBancaria extends Cuenta {
    private static final double COMISION_TRANSFERENCIA = 0.50;
    private static final double COMISION_DESTINO = 0.005;

    private final String iban;

    public CuentaBancaria(String idCuenta, String titular, double saldoInicial, String iban) throws CantidadIncorrectaException {
        super(idCuenta, titular, saldoInicial, 10000.0);
        if (iban == null) throw new CantidadIncorrectaException("IBAN no puede ser nulo");
        String ibanLimpio = iban.replaceAll("\\s+", "").toUpperCase();
        if (!ValidadorPago.esIbanValido(ibanLimpio)) throw new CantidadIncorrectaException("IBAN inválido (formato: ESXX XXXX XXXX XXXX XXXX XX): " + ibanLimpio);
        this.iban = ibanLimpio;
    }

    public String getIbanParcial() {
        if (iban.length() < 8) return iban;
        return iban.substring(0, 4) + "********" + iban.substring(iban.length() - 4);
    }

    @Override
    public String getDescripcionCompleta() {
        return "🏦 " + titular + " | " + idCuenta + " | IBAN " + getIbanParcial() + " | " + saldo + "€";
    }

    @Override
    public void retirar(double cantidad) throws SaldoInsuficienteException, CantidadIncorrectaException {
        double total = cantidad + COMISION_TRANSFERENCIA;
        super.retirar(total);
    }

    public void transferirACuenta(CuentaBancaria destino, double cantidad) throws SaldoInsuficienteException, CantidadIncorrectaException {
        if (destino == null || destino == this) throw new CantidadIncorrectaException("Destino inválido");
        ValidadorPago.validarCantidad(cantidad);
        retirar(cantidad);
        double recibido = cantidad * (1 - COMISION_DESTINO);
        destino.recargar(recibido);
    }

    @Override
    public String toString() {
        return "BANCO | " + idCuenta + " | " + getIbanParcial() + " | " + saldo + "€";
    }

    public String getIban() {
        return iban;
    }
}