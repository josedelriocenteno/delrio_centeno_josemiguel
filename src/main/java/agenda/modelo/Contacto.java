/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package agenda.modelo;

import java.time.LocalDate;

/**
 *
 * @author delcenjo
 */
public class Contacto {

    private String nombre;
    private String telefono;
    private LocalDate fechaRegistro;

    public Contacto(String nombre, String telefono) {
        this.nombre = nombre != null ? nombre.trim() : "";
        this.telefono = telefono != null ? telefono.trim() : "";
        this.fechaRegistro = LocalDate.now();
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setNombre(String nombre) {
        if (nombre != null) {
            this.nombre = nombre.trim();
        }
    }

    public void setTelefono(String telefono) {
        if (telefono != null) {
            this.telefono = telefono.trim();
        }
    }

    @Override
    public String toString() {
        return nombre + " - " + telefono;
    }
}