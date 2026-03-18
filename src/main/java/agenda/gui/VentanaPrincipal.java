/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package agenda.gui;

/**
 *
 * @author delcenjo
 */
public class VentanaPrincipal extends javax.swing.JFrame {
        
    public VentanaPrincipal(){
        initComponents();
        setFrame();
    }

    private void setFrame(){
        setTitle("Agenda Telefónica");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        labelEstado.setText("Selecciona un modo para empezar");
    }

    public javax.swing.JButton getBotonAceptar(){
        return botonAceptar;
    }
    
    public javax.swing.JButton getBotonCancelar(){
        return botonCancelar;
    }
    
    public javax.swing.JButton getBotonBorrar(){
        return botonBorrar;
    }
    
    public javax.swing.JMenuItem getItemAñadir(){
        return itemAñadir;
    }
    
    public javax.swing.JMenuItem getItemBuscar(){
        return itemBuscar;
    }
    
    public javax.swing.JMenuItem getItemModificar(){
        return itemModificar;
    }
    
    public javax.swing.JMenuItem getItemBorrar(){
        return itemBorrar;
    }
    
    public javax.swing.JMenuItem getItemListar(){
        return itemListar;
    }
    
    public javax.swing.JMenuItem getItemVaciar(){
        return itemVaciar;
    }
    
    public javax.swing.JMenuItem getItemSalir(){
        return itemSalir;
    }
    
    public javax.swing.JList<String> getListaContactos(){
        return listaContactos;
    }
    
    public javax.swing.JTextField getTextNombre(){
        return textNombre;
    }
    
    public javax.swing.JTextField getTextTelefono(){
        return textTelefono;
    }
    
    public javax.swing.JLabel getLabelEstado(){
        return labelEstado;
    }
    
    public javax.swing.JLabel getLabelContadorContactos(){
        return labelContadorContactos;
    }
    
    public javax.swing.JPanel getPanelFormulario(){
        return panelFormularioContacto;
    }

    public void cambiarColorModo(java.awt.Color color) {
        panelFormularioContacto.setBorder(
            javax.swing.BorderFactory.createTitledBorder(
                javax.swing.BorderFactory.createLineBorder(color, 3),
                "Modo actual"
            )
        );
    }

    public void mostrarTelefono(boolean visible) {
        labelTelefono.setVisible(visible);
        textTelefono.setVisible(visible);
    }

    public void mostrarCampoNombre(boolean visible) {
        labelNombre.setVisible(visible);
        textNombre.setVisible(visible);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelContenido = new javax.swing.JPanel();
        panelFormularioContacto = new javax.swing.JPanel();
        labelNombre = new javax.swing.JLabel();
        labelTelefono = new javax.swing.JLabel();
        textNombre = new javax.swing.JTextField();
        textTelefono = new javax.swing.JTextField();
        panelBotonesFormulario = new javax.swing.JPanel();
        botonAceptar = new javax.swing.JButton();
        botonCancelar = new javax.swing.JButton();
        botonBorrar = new javax.swing.JButton();
        panelListaContactos = new javax.swing.JPanel();
        scrollContactos = new javax.swing.JScrollPane();
        listaContactos = new javax.swing.JList<>();
        labelContadorContactos = new javax.swing.JLabel();
        panelEstado = new javax.swing.JPanel();
        labelEstado = new javax.swing.JLabel();
        menuPrincipal = new javax.swing.JMenuBar();
        menuContacto = new javax.swing.JMenu();
        itemAñadir = new javax.swing.JMenuItem();
        itemBuscar = new javax.swing.JMenuItem();
        itemModificar = new javax.swing.JMenuItem();
        itemBorrar = new javax.swing.JMenuItem();
        menuAgenda = new javax.swing.JMenu();
        itemListar = new javax.swing.JMenuItem();
        itemVaciar = new javax.swing.JMenuItem();
        itemSalir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelContenido.setLayout(new java.awt.BorderLayout());

        labelNombre.setText("Nombre:");

        labelTelefono.setText("Teléfono:");

        botonAceptar.setText("Aceptar");
        panelBotonesFormulario.add(botonAceptar);

        botonCancelar.setText("Cancelar");
        panelBotonesFormulario.add(botonCancelar);

        botonBorrar.setText("Borrar");
        panelBotonesFormulario.add(botonBorrar);

        javax.swing.GroupLayout panelFormularioContactoLayout = new javax.swing.GroupLayout(panelFormularioContacto);
        panelFormularioContacto.setLayout(panelFormularioContactoLayout);
        panelFormularioContactoLayout.setHorizontalGroup(
            panelFormularioContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFormularioContactoLayout.createSequentialGroup()
                .addGroup(panelFormularioContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelFormularioContactoLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(panelFormularioContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelNombre)
                            .addComponent(labelTelefono))
                        .addGap(72, 72, 72)
                        .addGroup(panelFormularioContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textNombre)
                            .addComponent(textTelefono)))
                    .addGroup(panelFormularioContactoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelBotonesFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)))
                .addGap(18, 18, 18))
        );
        panelFormularioContactoLayout.setVerticalGroup(
            panelFormularioContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormularioContactoLayout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addGroup(panelFormularioContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNombre)
                    .addComponent(textNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(panelFormularioContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTelefono)
                    .addComponent(textTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 338, Short.MAX_VALUE)
                .addComponent(panelBotonesFormulario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        panelContenido.add(panelFormularioContacto, java.awt.BorderLayout.LINE_START);

        panelListaContactos.setLayout(new java.awt.BorderLayout());

        scrollContactos.setViewportView(listaContactos);

        panelListaContactos.add(scrollContactos, java.awt.BorderLayout.CENTER);
        panelListaContactos.add(labelContadorContactos, java.awt.BorderLayout.PAGE_END);

        panelContenido.add(panelListaContactos, java.awt.BorderLayout.CENTER);

        getContentPane().add(panelContenido, java.awt.BorderLayout.CENTER);

        panelEstado.add(labelEstado);

        getContentPane().add(panelEstado, java.awt.BorderLayout.PAGE_END);

        menuContacto.setText("Contacto");

        itemAñadir.setText("Añadir");
        menuContacto.add(itemAñadir);

        itemBuscar.setText("Buscar");
        menuContacto.add(itemBuscar);

        itemModificar.setText("Modificar");
        menuContacto.add(itemModificar);

        itemBorrar.setText("Borrar");
        menuContacto.add(itemBorrar);

        menuPrincipal.add(menuContacto);

        menuAgenda.setText("Agenda");

        itemListar.setText("Listar");
        menuAgenda.add(itemListar);

        itemVaciar.setText("Vaciar");
        menuAgenda.add(itemVaciar);

        itemSalir.setText("Salir");
        menuAgenda.add(itemSalir);

        menuPrincipal.add(menuAgenda);

        setJMenuBar(menuPrincipal);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAceptar;
    private javax.swing.JButton botonBorrar;
    private javax.swing.JButton botonCancelar;
    private javax.swing.JMenuItem itemAñadir;
    private javax.swing.JMenuItem itemBorrar;
    private javax.swing.JMenuItem itemBuscar;
    private javax.swing.JMenuItem itemListar;
    private javax.swing.JMenuItem itemModificar;
    private javax.swing.JMenuItem itemSalir;
    private javax.swing.JMenuItem itemVaciar;
    private javax.swing.JLabel labelContadorContactos;
    private javax.swing.JLabel labelEstado;
    private javax.swing.JLabel labelNombre;
    private javax.swing.JLabel labelTelefono;
    private javax.swing.JList<String> listaContactos;
    private javax.swing.JMenu menuAgenda;
    private javax.swing.JMenu menuContacto;
    private javax.swing.JMenuBar menuPrincipal;
    private javax.swing.JPanel panelBotonesFormulario;
    private javax.swing.JPanel panelContenido;
    private javax.swing.JPanel panelEstado;
    private javax.swing.JPanel panelFormularioContacto;
    private javax.swing.JPanel panelListaContactos;
    private javax.swing.JScrollPane scrollContactos;
    private javax.swing.JTextField textNombre;
    private javax.swing.JTextField textTelefono;
    // End of variables declaration//GEN-END:variables
}
