package agenda.main;

import agenda.control.ControlAgenda;
import agenda.gui.VentanaPrincipal;
import agenda.servicios.Agenda;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {

    public static void main(String[] args) {

        // 🔥 Mejora visual (look & feel del sistema)
        try {
            UIManager.setLookAndFeel(
                UIManager.getSystemLookAndFeelClassName()
            );
        } catch (Exception e) {
            // Si falla, sigue con el default
        }

        // Ejecutar en hilo de Swing (correcto)
        SwingUtilities.invokeLater(() -> {

            Agenda modelo = new Agenda();
            VentanaPrincipal vista = new VentanaPrincipal();

            new ControlAgenda(vista, modelo);

            vista.setVisible(true);
        });
    }
}