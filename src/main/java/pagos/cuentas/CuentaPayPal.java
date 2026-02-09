package pagos.cuentas;

/**
 *
 * @author delcenjo
 */
public class CuentaPayPal extends Cuenta {

    private String email;

    public CuentaPayPal(String titular, String email) {
        super(titular);
        this.email = email;
    }

    @Override
    public String getDescripcion() {
        return "Cuenta PayPal asociada a " + email + " de " + titular;
    }
}
