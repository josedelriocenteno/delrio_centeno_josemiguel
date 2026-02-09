package pagos.cuentas;

/**
 *
 * @author delcenjo
 */
public class CuentaBizum extends Cuenta {

    private String telefono;

    public CuentaBizum(String titular, String telefono) {
        super(titular);
        this.telefono = telefono;
    }

    @Override
    public String getDescripcion() {
        return "Cuenta Bizum asociada al teléfono " + telefono + " de " + titular;
    }
}
