package com.delcenjo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Clase encargada de la lógica de negocio (copiado de archivos).
 * Desacopla la lógica principal de la interfaz gráfica.
 */
public class GestorArchivos {

    /**
     * Genera el archivo de destino con el sufijo "_copia".
     *
     * @param archivoOrigen Archivo original.
     * @param carpetaDestino Carpeta donde se va a copiar.
     * @return Archivo File que representa el destino con el sufijo "_copia".
     */
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

    /**
     * Realiza la copia del archivo byte a byte e informa del progreso.
     *
     * @param origen Archivo original.
     * @param destino Archivo destino.
     * @param listener Interfaz para notificar el progreso (puede ser null).
     * @throws IOException En caso de error de lectura/escritura.
     */
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
                
                // Actualizamos el progreso si se proporcionó un listener
                if (listener != null) {
                    int progreso = (int) ((copiedBytes * 100) / totalBytes);
                    listener.actualizarProgreso(progreso);
                }
            }
        }
    }
}
