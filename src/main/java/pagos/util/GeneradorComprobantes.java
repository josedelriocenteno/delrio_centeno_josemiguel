/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pagos.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 *
 * @author delcenjo
 */
public class GeneradorComprobantes {

    private static final DateTimeFormatter FORMATO_FECHA = 
        DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss", Locale.forLanguageTag("es"));

    public static String generar(String metodo, double cantidad, String origen, double saldoRestante) {
        StringBuilder sb = new StringBuilder();
        sb.append("═══════════════════════════════════════\n");
        sb.append("📄     COMPROBANTE DE PAGO            \n");
        sb.append("═══════════════════════════════════════\n");
        sb.append("Fecha/Hora: ").append(LocalDateTime.now().format(FORMATO_FECHA)).append("\n");
        sb.append("Método:     ").append(metodo).append("\n");
        sb.append("Cantidad:   ").append(String.format("%.2f", cantidad)).append(" €\n");
        sb.append("Origen:     ").append(origen).append("\n");
        sb.append("ID Transac: ").append(generarIdTransaccion()).append("\n");
        
        if (saldoRestante >= 0) {
            sb.append("Saldo:      ").append(String.format("%.2f", saldoRestante)).append(" €\n");
        }
        
        sb.append("═══════════════════════════════════════\n");
        sb.append("✅ PAGO REALIZADO CORRECTAMENTE       \n");
        sb.append("═══════════════════════════════════════\n");
        
        return sb.toString();
    }
    
    private static String generarIdTransaccion() {
        long timestamp = System.currentTimeMillis();
        int random = (int) (Math.random() * 10000);
        return String.format("TXN-%d-%04d", timestamp % 1000000, random);
    }
    
    public static String generarError(String metodo, double cantidad, String error) {
        StringBuilder sb = new StringBuilder();
        sb.append("═══════════════════════════════════════\n");
        sb.append("❌     ERROR EN PAGO                  \n");
        sb.append("═══════════════════════════════════════\n");
        sb.append("Método:     ").append(metodo).append("\n");
        sb.append("Cantidad:   ").append(String.format("%.2f", cantidad)).append(" €\n");
        sb.append("Error:      ").append(error).append("\n");
        sb.append("═══════════════════════════════════════\n");
        return sb.toString();
    }
}
