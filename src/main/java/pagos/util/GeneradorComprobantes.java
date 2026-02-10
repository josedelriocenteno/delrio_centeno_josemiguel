/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pagos.util;

/**
 *
 * @author delcenjo
 */
public class GeneradorComprobantes {

    private GeneradorComprobantes() {
        // Clase utilitaria, no se puede instanciar
    }

    public static String generar(String metodo, double cantidad, String origen, double saldoRestante) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== COMPROBANTE DE PAGO ===\n");
        sb.append("Método: ").append(metodo).append("\n");
        sb.append("Cantidad: ").append(cantidad).append(" EUR\n");
        sb.append("Origen: ").append(origen).append("\n");
        if (saldoRestante >= 0) {
            sb.append("Saldo restante: ").append(saldoRestante).append(" EUR\n");
        }
        sb.append("===========================\n");
        return sb.toString();
    }
}
