/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agenda.main;

import agenda.control.ControlAgenda;
import agenda.gui.VentanaPrincipal;
import agenda.servicios.Agenda;
import javax.swing.SwingUtilities;

/**
 *
 * @author delcenjo
 */
public class Main {
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Agenda modelo = new Agenda();
            VentanaPrincipal vista = new VentanaPrincipal();       
            new ControlAgenda(vista, modelo);           
            vista.setVisible(true);
        });
    }
}