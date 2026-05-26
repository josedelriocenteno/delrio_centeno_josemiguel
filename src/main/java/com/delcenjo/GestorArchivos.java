/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.delcenjo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author delcenjo
 */
public class GestorArchivos {

    public File generarArchivoDestino(File archivoOrigen, File carpetaDestino) {
        String nombreOriginal = archivoOrigen.getName();
        String nombreCopia;
        int puntoIndex = nombreOriginal.lastIndexOf('.');
        
        if (puntoIndex > 0) {
            String nombre = nombreOriginal.substring(0, puntoIndex);
            String extension = nombreOriginal.substring(puntoIndex);
            nombreCopia = nombre + "_copia" + extension;
        } else {
            nombreCopia = nombreOriginal + "_copia";
        }
        
        return new File(carpetaDestino, nombreCopia);
    }

    public void copiarArchivo(File origen, File destino, ProgresoListener listener) throws IOException {
        long totalBytes = origen.length();
        long copiedBytes = 0;
        byte[] buffer = new byte[4096];
        int bytesLeidos;

        try (FileInputStream fis = new FileInputStream(origen);
             FileOutputStream fos = new FileOutputStream(destino)) {
             
            while ((bytesLeidos = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesLeidos);
                copiedBytes += bytesLeidos;
                
                if (listener != null) {
                    int progreso = (int) ((copiedBytes * 100) / totalBytes);
                    listener.actualizarProgreso(progreso);
                }
            }
        }
    }
}
