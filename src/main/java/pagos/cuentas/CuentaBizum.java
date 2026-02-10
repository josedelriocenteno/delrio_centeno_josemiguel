package pagos.cuentas;

/**
 *
 * @author delcenjo
 */
public class CuentaBizum extends Cuenta {

    private String telefono;

    public CuentaBizum(String titular, double saldo, String telefono) {
        super(titular, saldo);
        this.telefono = telefono;
    }

    @Override
    public String getDescripcion() {
        return "Cuenta Bizum: " + telefono + " - Titular: " + titular;
    }
}
