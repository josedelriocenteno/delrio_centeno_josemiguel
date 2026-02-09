package pagos.cuentas;

/**
 *
 * @author delcenjo
 */
public class CuentaBancaria extends Cuenta {

    private String iban;

    public CuentaBancaria(String titular, String iban) {
        super(titular);
        this.iban = iban;
    }

    @Override
    public String getDescripcion() {
        return "Cuenta bancaria IBAN " + iban + " de " + titular;
    }
}
