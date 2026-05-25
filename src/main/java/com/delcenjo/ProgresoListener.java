package com.delcenjo;

/**
 * Interfaz para comunicar el progreso de una tarea a la interfaz gráfica
 * sin acoplar fuertemente las clases.
 */
public interface ProgresoListener {
    void actualizarProgreso(int porcentaje);
}
