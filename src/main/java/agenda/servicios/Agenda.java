package agenda.servicios;

import agenda.modelo.Contacto;
import java.util.*;

public class Agenda {

    private Map<String, Contacto> agenda;

    public Agenda() {
        agenda = new HashMap<>();
    }

    public boolean añadirContacto(String nombre, String telefono) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return false;
        }

        nombre = nombre.trim();

        if (agenda.containsKey(nombre)) {
            return false;
        }

        Contacto contacto = new Contacto(nombre, telefono);
        agenda.put(nombre, contacto);
        return true;
    }

    public Contacto buscarContacto(String nombre) {
        if (nombre == null) return null;
        return agenda.get(nombre.trim());
    }

    public boolean eliminarContacto(String nombre) {
        if (nombre == null || !agenda.containsKey(nombre.trim())) {
            return false;
        }

        agenda.remove(nombre.trim());
        return true;
    }

    public boolean modificarContacto(String nombre, String nuevoTelefono) {
        if (nombre == null) return false;

        Contacto contacto = agenda.get(nombre.trim());

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
        if (nombre == null) return false;
        return agenda.containsKey(nombre.trim());
    }

    // 🔥 EXTRA PRO (opcional)
    public int tamaño() {
        return agenda.size();
    }
}