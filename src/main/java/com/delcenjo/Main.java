package com.delcenjo;

/**
 * Clase principal que arranca la aplicación.
 * Mantiene separada la lógica de arranque del código de la interfaz (JFrame).
 */
public class Main {
    
    public static void main(String[] args) {
        /* Configura el aspecto visual (Look and Feel) de Nimbus */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Crea y muestra la ventana en el hilo de eventos de Swing */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CopiadorArchivos().setVisible(true);
            }
        });
    }
}
