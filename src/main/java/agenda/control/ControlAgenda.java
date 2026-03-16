/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agenda.control;

import agenda.gui.VentanaPrincipal;
import agenda.modelo.Contacto;
import agenda.servicios.Agenda;
import agenda.validacion.TelefonoValidar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionListener;
import java.util.List;
import javax.swing.event.ListSelectionEvent;

/**
 *
 * @author delcenjo
 */
public class ControlAgenda implements ActionListener, ListSelectionListener {
    private VentanaPrincipal vista;
    private Agenda modelo;
    private Contacto seleccionado = null;
    
    public ControlAgenda(VentanaPrincipal vista, Agenda modelo) {
        this.vista = vista;
        this.modelo = modelo;
        
        // ★ Conecta TODOS los eventos
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
        
        // ★ Inicialización
        actualizarLista();
        modoNuevo();
        vista.getLabelEstado().setText("Bienvenido. Pulsa Añadir o selecciona un contacto.");
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        
        // ★ IDENTIFICACIÓN POR OBJETO (funciona SIEMPRE)
        if (src == vista.getBotonAceptar() || src == vista.getItemAñadir()) {
            aceptar();
        } else if (src == vista.getBotonCancelar()) {
            cancelar();
        } else if (src == vista.getBotonBorrar() || src == vista.getItemBorrar()) {
            borrar();
        } else if (src == vista.getItemBuscar()) {
            buscar();
        } else if (src == vista.getItemModificar()) {
            modoEditar();
        } else if (src == vista.getItemListar()) {
            actualizarLista();
        } else if (src == vista.getItemVaciar()) {
            vaciar();
        } else if (src == vista.getItemSalir()) {
            System.exit(0);
        }
        
        actualizarContador();
    }
    
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            int idx = vista.getListaContactos().getSelectedIndex();
            if (idx >= 0) {
                List<Contacto> lista = modelo.listarContactos();
                seleccionado = lista.get(idx);
                vista.getTextNombre().setText(seleccionado.getNombre());
                vista.getTextTelefono().setText(seleccionado.getTelefono());
                vista.getLabelEstado().setText("Seleccionado: " + seleccionado.getNombre());
            }
        }
    }
    
    private void aceptar() {
        String nombre = vista.getTextNombre().getText().trim();
        String tel = vista.getTextTelefono().getText().trim();
        
        if (nombre.isEmpty()) {
            vista.getLabelEstado().setText("¡Error! El nombre no puede estar vacío.");
            return;
        }
        if (!TelefonoValidar.esTelefonoValido(tel)) {
            vista.getLabelEstado().setText("¡Error! Teléfono inválido (9 dígitos numéricos).");
            return;
        }
        
        if (seleccionado == null) {  // AÑADIR NUEVO
            if (modelo.añadirContacto(nombre, tel)) {
                vista.getLabelEstado().setText("✓ Añadido: " + nombre);
            } else {
                vista.getLabelEstado().setText("✗ Ya existe: " + nombre);
            }
        } else {  // MODIFICAR
            if (modelo.modificarContacto(seleccionado.getNombre(), tel)) {
                seleccionado.setTelefono(tel);
                vista.getLabelEstado().setText("✓ Modificado: " + nombre);
            }
        }
        actualizarLista();
        modoNuevo();
    }
    
    private void cancelar() {
        modoNuevo();
    }
    
    private void modoNuevo() {
        seleccionado = null;
        vista.getTextNombre().setText("");
        vista.getTextTelefono().setText("");
        vista.getLabelEstado().setText("Modo nuevo - introduce nombre y teléfono");
    }
    
    private void modoEditar() {
        if (seleccionado != null) {
            vista.getLabelEstado().setText("Editando: " + seleccionado.getNombre());
        } else {
            vista.getLabelEstado().setText("Selecciona un contacto de la lista para editar");
        }
    }
    
    private void buscar() {
        String nombre = vista.getTextNombre().getText().trim();
        Contacto c = modelo.buscarContacto(nombre);
        if (c != null) {
            seleccionado = c;
            vista.getTextTelefono().setText(c.getTelefono());
            vista.getLabelEstado().setText("✓ Encontrado: " + nombre);
            actualizarLista();
        } else {
            vista.getLabelEstado().setText("✗ No encontrado: " + nombre);
        }
    }
    
    private void borrar() {
        if (seleccionado != null && modelo.eliminarContacto(seleccionado.getNombre())) {
            vista.getLabelEstado().setText("✓ Borrado: " + seleccionado.getNombre());
            seleccionado = null;
            actualizarLista();
            modoNuevo();
        } else {
            vista.getLabelEstado().setText("✗ Selecciona un contacto para borrar");
        }
    }
    
    private void vaciar() {
        modelo.vaciarAgenda();
        vista.getLabelEstado().setText("✓ Agenda vaciada completamente");
        actualizarLista();
        modoNuevo();
    }
    
    private void actualizarLista() {
        List<Contacto> lista = modelo.listarContactos();
        String[] datosLista = lista.stream()
            .map(Contacto::toString)
            .toArray(String[]::new);
        vista.getListaContactos().setListData(datosLista);
        vista.getLabelContadorContactos().setText("Total contactos: " + lista.size());
    }
    
    private void actualizarContador() {
        List<Contacto> lista = modelo.listarContactos();
        vista.getLabelContadorContactos().setText("Total contactos: " + lista.size());
    }
}
