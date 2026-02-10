package pagos.cuentas;

/**
 *
 * @author delcenjo
 */
public class CuentaPayPal extends Cuenta {

    private String email;

    public CuentaPayPal(String titular, double saldo, String email) {
        super(titular, saldo);
        this.email = email;
    }

    @Override
    public String getDescripcion() {
        return "Cuenta PayPal: " + email + " - Titular: " + titular;
    }
}
