/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agenda.servicios;

import agenda.modelo.Contacto;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author delcenjo
 */
public class Agenda {
    
    private Map<String, Contacto> agenda;

    public Agenda() {
        agenda = new HashMap<>();
    }

    public boolean añadirContacto(String nombre, String telefono) {

        if (agenda.containsKey(nombre)) {
            return false;
        }

        Contacto contacto = new Contacto(nombre, telefono);
        agenda.put(nombre, contacto);

        return true;
    }

    public Contacto buscarContacto(String nombre) {
        return agenda.get(nombre);
    }

    public boolean eliminarContacto(String nombre) {

        if (!agenda.containsKey(nombre)) {
            return false;
        }

        agenda.remove(nombre);
        return true;
    }

    public boolean modificarContacto(String nombre, String nuevoTelefono) {

        Contacto contacto = agenda.get(nombre);

        if (contacto == null) {
            return false;
        }

        contacto.setTelefono(nuevoTelefono);
        return true;
    }

    public List<Contacto> listarContactos() {

        List<Contacto> lista = new ArrayList<>(agenda.values());

        lista.sort(Comparator.comparing(Contacto::getNombre));

        return lista;
    }

    public void vaciarAgenda() {
        agenda.clear();
    }

    public boolean existeContacto(String nombre) {
        return agenda.containsKey(nombre);
    }
    
}
