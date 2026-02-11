package pagos.cuentas;

import pagos.excepciones.CantidadIncorrectaException;
import pagos.excepciones.SaldoInsuficienteException;

public abstract class Cuenta {

    protected final String idCuenta;
    protected String titular;
    protected double saldo;
    private double limiteDiario;

    public Cuenta(String idCuenta, String titular, double saldoInicial, double limiteDiario) {

        if (idCuenta == null || idCuenta.trim().isEmpty()) {
            throw new IllegalArgumentException("ID cuenta requerido");
        }

        if (saldoInicial < 0) {
            throw new IllegalArgumentException("Saldo inicial no puede ser negativo");
        }

        if (limiteDiario <= 0) {
            throw new IllegalArgumentException("Límite diario inválido");
        }

        this.idCuenta = idCuenta.trim();
        this.titular = titular != null ? titular.trim() : "";
        this.saldo = saldoInicial;
        this.limiteDiario = limiteDiario;
    }

    public void retirar(double cantidad)
            throws SaldoInsuficienteException, CantidadIncorrectaException {

        validarCantidad(cantidad);

        if (cantidad > saldo) {
            throw new SaldoInsuficienteException(
                    String.format("Saldo %.2f€ insuficiente para %.2f€", saldo, cantidad)
            );
        }

        if (cantidad > limiteDiario) {
            throw new CantidadIncorrectaException(
                    "Excede límite diario: " + limiteDiario + "€"
            );
        }

        saldo -= cantidad;
    }

    protected void validarCantidad(double cantidad)
            throws CantidadIncorrectaException {

        if (cantidad <= 0) {
            throw new CantidadIncorrectaException("Cantidad debe ser mayor que 0");
        }

        if (Math.round(cantidad * 100) != cantidad * 100) {
            throw new CantidadIncorrectaException("Solo se permiten 2 decimales");
        }
    }

    public void recargar(double cantidad) throws CantidadIncorrectaException {
        validarCantidad(cantidad);
        saldo += cantidad;
    }

    public abstract String getDescripcionCompleta();

    public String getIdCuenta() { return idCuenta; }

    public String getTitular() { return titular; }

    public void setTitular(String titular) {
        this.titular = titular != null ? titular.trim() : "";
    }

    public double getSaldo() { return saldo; }

    public double getLimiteDiario() { return limiteDiario; }

    @Override
    public String toString() {
        return String.format("%s | %s | %.2f€ | Límite: %.2f€",
                idCuenta, titular, saldo, limiteDiario);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Cuenta)) return false;
        return idCuenta.equals(((Cuenta) obj).idCuenta);
    }

    @Override
    public int hashCode() {
        return idCuenta.hashCode();
    }
}