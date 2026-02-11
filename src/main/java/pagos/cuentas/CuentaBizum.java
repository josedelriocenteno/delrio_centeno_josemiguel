package pagos.cuentas;

import pagos.validaciones.ValidadorPago;
import pagos.excepciones.SaldoInsuficienteException;
import pagos.excepciones.CantidadIncorrectaException;

public class CuentaBizum extends Cuenta {
    private final String telefono;

    public CuentaBizum(String idCuenta, String titular, double saldoInicial, String telefono)
            throws CantidadIncorrectaException {
        super(idCuenta, titular, saldoInicial, 1000.0); // límite diario Bizum

        if (telefono == null) {
            throw new CantidadIncorrectaException("Teléfono Bizum no puede ser nulo");
        }

        String cleanTelefono = telefono.trim();

        if (!ValidadorPago.esTelefonoBizumValido(cleanTelefono)) {
            throw new CantidadIncorrectaException(
                    "Teléfono Bizum inválido (debe tener 9 dígitos): " + cleanTelefono
            );
        }

        this.telefono = cleanTelefono;
    }

    @Override
    public String getDescripcionCompleta() {
        return String.format("📱 %s | Bizum %s | %.2f€",
                titular, telefono, saldo);
    }

    @Override
    public void retirar(double cantidad)
            throws SaldoInsuficienteException, CantidadIncorrectaException {
        if (cantidad > getLimiteDiario()) {
            throw new CantidadIncorrectaException(
                    "Bizum máximo " + getLimiteDiario() + "€ por operación"
            );
        }
        super.retirar(cantidad);
    }

    public void enviarBizum(CuentaBizum destino, double cantidad, String concepto)
            throws SaldoInsuficienteException, CantidadIncorrectaException {

        if (destino == null || destino == this) {
            throw new CantidadIncorrectaException("Destino Bizum inválido");
        }

        if (concepto == null || concepto.trim().isEmpty() || concepto.length() > 140) {
            throw new CantidadIncorrectaException(
                    "Concepto obligatorio (máx 140 caracteres)"
            );
        }

        retirar(cantidad);
        destino.recargar(cantidad);
    }

    public String getTelefono() {
        return telefono;
    }

    public String getTelefonoParcial() {
        if (telefono.length() != 9) return telefono;
        return telefono.substring(0, 3) + "***" + telefono.substring(6);
    }

    @Override
    public String toString() {
        return String.format("BIZUM | %s | %s | %.2f€",
                idCuenta, getTelefonoParcial(), saldo);
    }
}