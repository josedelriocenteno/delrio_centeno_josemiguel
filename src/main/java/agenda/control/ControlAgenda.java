/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package agenda.control;

import agenda.gui.VentanaPrincipal;
import agenda.modelo.Contacto;
import agenda.servicios.Agenda;
import agenda.validacion.TelefonoValidar;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

/**
 *
 * @author delcenjo
 */
public class ControlAgenda implements ActionListener, ListSelectionListener {

    private VentanaPrincipal vista;
    private Agenda modelo;
    private Contacto seleccionado = null;

    private enum Modo {
        NINGUNO, AÑADIR, BUSCAR, MODIFICAR, BORRAR, LISTAR, VACIAR
    }
    private Modo modo = Modo.NINGUNO;

    private List<String> listaTemporal = new ArrayList<>();

    public ControlAgenda(VentanaPrincipal vista, Agenda modelo) {
        this.vista = vista;
        this.modelo = modelo;

        vista.getBotonAceptar().addActionListener(this);
        vista.getBotonCancelar().addActionListener(this);
        vista.getBotonBorrar().addActionListener(this);
        vista.getItemAñadir().addActionListener(this);
        vista.getItemBuscar().addActionListener(this);
        vista.getItemModificar().addActionListener(this);
        vista.getItemBorrar().addActionListener(this);
        vista.getItemListar().addActionListener(this);
        vista.getItemVaciar().addActionListener(this);
        vista.getItemSalir().addActionListener(this);
        vista.getListaContactos().addListSelectionListener(this);

        modoPorDefecto();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if (src == vista.getBotonAceptar()) {
            ejecutarModo();
        } else if (src == vista.getBotonCancelar()) {
            modoPorDefecto();
        } else if (src == vista.getBotonBorrar()) {
            limpiarCampos();
        } else if (src == vista.getItemAñadir()) {
            cambiarModo(Modo.AÑADIR);
        } else if (src == vista.getItemBuscar()) {
            cambiarModo(Modo.BUSCAR);
        } else if (src == vista.getItemModificar()) {
            cambiarModo(Modo.MODIFICAR);
        } else if (src == vista.getItemBorrar()) {
            cambiarModo(Modo.BORRAR);
        } else if (src == vista.getItemListar()) {
            cambiarModo(Modo.LISTAR);
        } else if (src == vista.getItemVaciar()) {
            cambiarModo(Modo.VACIAR);
        } else if (src == vista.getItemSalir()) {
            System.exit(0);
        }
    }

    private void cambiarModo(Modo nuevoModo) {
        modo = nuevoModo;
        actualizarUI();
    }

    private void ejecutarModo() {
        switch (modo) {
            case AÑADIR ->
                añadir();
            case BUSCAR ->
                buscar();
            case MODIFICAR ->
                modificar();
            case BORRAR ->
                eliminarContacto();
            case LISTAR ->
                actualizarLista();
            case VACIAR ->
                vaciar();
            default ->
                JOptionPane.showMessageDialog(vista, "Selecciona un modo primero", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void actualizarUI() {
        limpiarCampos();
        listaTemporal.clear();
        vista.getListaContactos().setListData(new String[]{});
        vista.mostrarTelefono(false);
        vista.mostrarCampoNombre(false);
        vista.getTextNombre().setEnabled(false);
        vista.getTextTelefono().setEnabled(false);
        vista.getBotonAceptar().setEnabled(false);
        vista.getBotonBorrar().setEnabled(false);

        switch (modo) {
            case NINGUNO -> {
                vista.cambiarColorModo(Color.GRAY);
                vista.getLabelEstado().setText("Selecciona un modo");
                vista.getBotonAceptar().setEnabled(false);
                vista.getBotonBorrar().setEnabled(false);
                vista.getBotonCancelar().setEnabled(false);
            }
            case AÑADIR -> {
                vista.cambiarColorModo(Color.GREEN);
                vista.getLabelEstado().setText("Modo AÑADIR: ingresa nombre y teléfono");
                vista.mostrarCampoNombre(true);
                vista.mostrarTelefono(true);
                vista.getTextNombre().setEnabled(true);
                vista.getTextTelefono().setEnabled(true);
                vista.getBotonAceptar().setEnabled(true);
                vista.getBotonBorrar().setEnabled(true);
            }
            case BUSCAR -> {
                vista.cambiarColorModo(Color.BLUE);
                vista.getLabelEstado().setText("Modo BUSCAR: ingresa nombre");
                vista.mostrarCampoNombre(true);
                vista.getTextNombre().setEnabled(true);
                vista.getBotonAceptar().setEnabled(true);
            }
            case MODIFICAR -> {
                vista.cambiarColorModo(Color.ORANGE);
                vista.getLabelEstado().setText("Modo MODIFICAR: selecciona contacto para editar");
                actualizarLista();
                vista.mostrarCampoNombre(true);
                vista.mostrarTelefono(true);
                vista.getTextNombre().setEnabled(false);
                vista.getTextTelefono().setEnabled(false);
            }
            case BORRAR -> {
                vista.cambiarColorModo(Color.RED);
                vista.getLabelEstado().setText("Modo BORRAR: selecciona contacto y pulsa Aceptar");
                actualizarLista();
            }
            case LISTAR -> {
                vista.cambiarColorModo(Color.MAGENTA);
                vista.getLabelEstado().setText("Modo LISTAR");
                actualizarLista();
            }
            case VACIAR -> {
                vista.cambiarColorModo(Color.RED);
                vista.getLabelEstado().setText("⚠ Modo VACIAR: pulsa Aceptar para borrar todos");
                vista.getBotonAceptar().setEnabled(true);
            }
        }
    }

    private void añadir() {
        String nombre = vista.getTextNombre().getText().trim();
        String tel = vista.getTextTelefono().getText().trim();
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Nombre vacío", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!TelefonoValidar.esTelefonoValido(tel)) {
            JOptionPane.showMessageDialog(vista, "Teléfono inválido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (modelo.añadirContacto(nombre, tel)) {
            JOptionPane.showMessageDialog(vista, "Contacto añadido", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            actualizarLista();
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(vista, "Ya existe un contacto con ese nombre", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscar() {
        String nombre = vista.getTextNombre().getText().trim();
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Introduce nombre para buscar", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Contacto c = modelo.buscarContacto(nombre);
        listaTemporal.clear();
        if (c != null) {
            seleccionado = c;
            listaTemporal.add(c.toString());
            vista.getListaContactos().setListData(listaTemporal.toArray(new String[0]));
            vista.getLabelEstado().setText("✓ Encontrado");
        } else {
            vista.getListaContactos().setListData(new String[]{});
            vista.getLabelEstado().setText("No encontrado");
        }
    }

    private void modificar() {
        if (seleccionado == null) {
            JOptionPane.showMessageDialog(vista, "Selecciona un contacto de la lista", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String nuevoTelefono = vista.getTextTelefono().getText().trim();
        if (!TelefonoValidar.esTelefonoValido(nuevoTelefono)) {
            JOptionPane.showMessageDialog(vista, "Teléfono inválido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        modelo.modificarContacto(seleccionado.getNombre(), nuevoTelefono);
        seleccionado.setTelefono(nuevoTelefono);
        JOptionPane.showMessageDialog(vista, "Contacto modificado", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        actualizarLista();
        limpiarCampos();
        vista.getTextNombre().setEnabled(false);
        vista.getTextTelefono().setEnabled(false);
        vista.getBotonAceptar().setEnabled(false);
    }

    private void eliminarContacto() {
        if (seleccionado == null) {
            JOptionPane.showMessageDialog(vista, "Selecciona un contacto de la lista", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                vista,
                "¿Seguro que deseas eliminar " + seleccionado.getNombre() + "?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION
        );
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        modelo.eliminarContacto(seleccionado.getNombre());
        JOptionPane.showMessageDialog(vista, "Contacto eliminado", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        actualizarLista();
        limpiarCampos();
    }

    private void vaciar() {
        int confirm = JOptionPane.showConfirmDialog(
                vista,
                "¿Deseas vaciar toda la agenda y borrar todos los contactos?",
                "Confirmar vaciado",
                JOptionPane.YES_NO_OPTION
        );
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        modelo.vaciarAgenda();
        actualizarLista();
        JOptionPane.showMessageDialog(vista, "Agenda vaciada", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        modoPorDefecto();
    }

    private void modoPorDefecto() {
        modo = Modo.NINGUNO;
        actualizarUI();
    }

    private void limpiarCampos() {
        vista.getTextNombre().setText("");
        vista.getTextTelefono().setText("");
        seleccionado = null;
    }

    private void actualizarLista() {
        listaTemporal.clear();
        List<Contacto> lista = modelo.listarContactos();

        for (Contacto c : lista) {
            listaTemporal.add(c.toString());
        }

        vista.getListaContactos().setListData(listaTemporal.toArray(new String[0]));
        vista.getLabelContadorContactos().setText("Total: " + lista.size());
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            int idx = vista.getListaContactos().getSelectedIndex();
            if (idx >= 0 && idx < listaTemporal.size()) {
                String texto = listaTemporal.get(idx);
                String nombre = texto.split(" - ")[0];
                Contacto c = modelo.buscarContacto(nombre);
                if (c != null) {
                    seleccionado = c;

                    if (modo == Modo.BORRAR) {
                        int confirm = JOptionPane.showConfirmDialog(vista,
                                "¿Seguro que deseas eliminar " + c.getNombre() + "?",
                                "Confirmar",
                                JOptionPane.YES_NO_OPTION);
                        if (confirm == JOptionPane.YES_OPTION) {
                            modelo.eliminarContacto(c.getNombre());
                            actualizarLista();
                        }
                    } else if (modo == Modo.MODIFICAR) {
                        vista.getTextNombre().setText(c.getNombre());
                        vista.getTextTelefono().setText(c.getTelefono());
                        vista.getTextNombre().setEnabled(true);
                        vista.getTextTelefono().setEnabled(true);
                        vista.getBotonAceptar().setEnabled(true);
                    }
                }
            }
        }
    }
}
