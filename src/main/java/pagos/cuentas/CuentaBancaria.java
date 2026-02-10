package pagos.cuentas;

public class CuentaBancaria extends Cuenta {

    private String iban;

    public CuentaBancaria(String titular, double saldo, String iban) {
        super(titular, saldo);
        this.iban = iban;
    }

    @Override
    public String getDescripcion() {
        return "Cuenta bancaria IBAN " + iban + " - Titular: " + titular;
    }
}