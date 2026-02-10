package pagos.cuentas;

import pagos.excepciones.SaldoInsuficienteException;

public abstract class Cuenta {

    protected String titular;
    protected double saldo;

    public Cuenta(String titular, double saldo) {
        this.titular = titular;
        this.saldo = saldo;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getTitular() {
        return titular;
    }

    public void retirar(double cantidad) throws SaldoInsuficienteException {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("Cantidad no válida");
        }
        if (cantidad > saldo) {
            throw new SaldoInsuficienteException("Saldo insuficiente");
        }
        saldo -= cantidad;
    }

    public abstract String getDescripcion();

    public String getDescripcionCompleta() {
        return getDescripcion() + " - Saldo: " + saldo + " EUR";
    }
}