package pagos.cuentas;

/**
 *
 * @author delcenjo
 */
public abstract class Cuenta {

    protected String titular;

    public Cuenta(String titular) {
        this.titular = titular;
    }

    /**
     * Devuelve la descripción del origen del dinero.
     * Cada tipo de cuenta implementará este método.
     */
    public abstract String getDescripcion();
}
