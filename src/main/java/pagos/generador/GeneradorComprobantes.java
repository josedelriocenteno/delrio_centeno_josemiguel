/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pagos.generador;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author delcenjo
 */
public class GeneradorComprobantes {

    public static String generar(String metodo, double cantidad, String origen, double saldoRestante) {
        String comprobante = "";
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        comprobante += "COMPROBANTE DE PAGO\n";
        comprobante += "Fecha: " + LocalDateTime.now().format(formato) + "\n";
        comprobante += "Metodo: " + metodo + "\n";
        comprobante += "Cantidad: " + cantidad + " €\n";
        comprobante += "Origen: " + origen + "\n";
        comprobante += "ID Transac: " + generarIdTransaccion() + "\n";
        comprobante += "Saldo: " + saldoRestante + " €\n";
        comprobante += "PAGO REALIZADO CORRECTAMENTE\n";
        return comprobante;
    }

    private static String generarIdTransaccion() {
        long milis = System.currentTimeMillis();
        int random = (int)(Math.random()*10000);
        return "TXN-" + (milis % 1000000) + "-" + random;
    }

    public static String generarError(String metodo, double cantidad, String error) {
        String comprobante = "";
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        comprobante = comprobante + "ERROR EN PAGO\n";
        comprobante = comprobante + "Fecha: " + LocalDateTime.now().format(formato) + "\n";
        comprobante = comprobante + "Metodo: " + metodo + "\n";
        comprobante = comprobante + "Cantidad: " + cantidad + " €\n";
        comprobante = comprobante + "Error: " + error + "\n";
        return comprobante;
    }
}