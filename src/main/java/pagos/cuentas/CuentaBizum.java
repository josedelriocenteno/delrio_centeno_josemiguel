/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pagos.cuentas;

/**
 *
 * @author delcenjo
 */
import pagos.validaciones.ValidadorPago;
import pagos.excepciones.SaldoInsuficienteException;
import pagos.excepciones.CantidadIncorrectaException;

public class CuentaBizum extends Cuenta {
    private final String telefono;

    public CuentaBizum(String idCuenta, String titular, double saldoInicial, String telefono) throws CantidadIncorrectaException {
        super(idCuenta, titular, saldoInicial, 1000.0);
        if (telefono == null) throw new CantidadIncorrectaException("Teléfono Bizum no puede ser nulo");
        String telefonoLimpio = telefono.trim();
        if (!ValidadorPago.esTelefonoBizumValido(telefonoLimpio))throw new CantidadIncorrectaException("Teléfono Bizum imvalido (debe tener 9 dígitos): " + telefonoLimpio);
        this.telefono = telefonoLimpio;
    }

    @Override
    public String getDescripcionCompleta() {
        return "📱 " + titular + " | Bizum " + telefono + " | " + saldo + "€";
    }

    @Override
    public void retirar(double cantidad) throws SaldoInsuficienteException, CantidadIncorrectaException {
        if (cantidad > getLimiteDiario()) throw new CantidadIncorrectaException("Bizum máximo " + getLimiteDiario() + "€ por operación");
        super.retirar(cantidad);
    }

    public void enviarBizum(CuentaBizum destino, double cantidad, String concepto) throws SaldoInsuficienteException, CantidadIncorrectaException {
        if (destino == null || destino == this) throw new CantidadIncorrectaException("Destino Bizum inválido");
        if (concepto == null || concepto.trim().isEmpty() || concepto.length() > 140) throw new CantidadIncorrectaException("Concepto obligatorio (máx 140 caracteres)");
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
        return "BIZUM | " + idCuenta + " | " + getTelefonoParcial() + " | " + saldo + "€";
    }
}