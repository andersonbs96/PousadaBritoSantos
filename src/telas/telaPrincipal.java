
package telas;


import javax.swing.JOptionPane;
import telasClientes.ClienteCadastro;
import telasClientes.ClientesVisualizar;
import telasQuartos.QuartosVisualizar;
import telasReservas.ReservasCadastrar;

public class telaPrincipal extends javax.swing.JFrame {

   
    public telaPrincipal() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Desktop = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        itemClienteCadastrar = new javax.swing.JMenuItem();
        itemClienteVisualizar = new javax.swing.JMenuItem();
        menuReservas = new javax.swing.JMenu();
        itemReservasFazer = new javax.swing.JMenuItem();
        itemReservaVisualizar = new javax.swing.JMenuItem();
        itemReservasCancelar = new javax.swing.JMenuItem();
        menuQuartos = new javax.swing.JMenu();
        itemQuartosDisponivel = new javax.swing.JMenuItem();
        menuSobre = new javax.swing.JMenu();
        menuSair = new javax.swing.JMenu();
        itemOpcoesSair = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pousada Brito Santos");
        setPreferredSize(new java.awt.Dimension(1366, 768));

        javax.swing.GroupLayout DesktopLayout = new javax.swing.GroupLayout(Desktop);
        Desktop.setLayout(DesktopLayout);
        DesktopLayout.setHorizontalGroup(
            DesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1342, Short.MAX_VALUE)
        );
        DesktopLayout.setVerticalGroup(
            DesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 723, Short.MAX_VALUE)
        );

        jMenu1.setText("Clientes");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        itemClienteCadastrar.setText("Cadastrar");
        itemClienteCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemClienteCadastrarActionPerformed(evt);
            }
        });
        jMenu1.add(itemClienteCadastrar);

        itemClienteVisualizar.setText("Visualizar");
        itemClienteVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemClienteVisualizarActionPerformed(evt);
            }
        });
        jMenu1.add(itemClienteVisualizar);

        jMenuBar1.add(jMenu1);

        menuReservas.setText("Reservas");

        itemReservasFazer.setText("Reservar");
        itemReservasFazer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemReservasFazerActionPerformed(evt);
            }
        });
        menuReservas.add(itemReservasFazer);

        itemReservaVisualizar.setText("Editar");
        itemReservaVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemReservaVisualizarActionPerformed(evt);
            }
        });
        menuReservas.add(itemReservaVisualizar);

        itemReservasCancelar.setText("Cancelar");
        menuReservas.add(itemReservasCancelar);

        jMenuBar1.add(menuReservas);

        menuQuartos.setText("Quartos");

        itemQuartosDisponivel.setText("Disponiveis");
        itemQuartosDisponivel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemQuartosDisponivelActionPerformed(evt);
            }
        });
        menuQuartos.add(itemQuartosDisponivel);

        jMenuBar1.add(menuQuartos);

        menuSobre.setText("Sobre");
        jMenuBar1.add(menuSobre);

        menuSair.setText("Opções");
        menuSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSairActionPerformed(evt);
            }
        });

        itemOpcoesSair.setText("Sair");
        itemOpcoesSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemOpcoesSairActionPerformed(evt);
            }
        });
        menuSair.add(itemOpcoesSair);

        jMenuBar1.add(menuSair);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Desktop)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Desktop)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void menuSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSairActionPerformed
        
    }//GEN-LAST:event_menuSairActionPerformed

    private void itemReservaVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemReservaVisualizarActionPerformed
        telaVisualizar visualizar = new telaVisualizar();
        visualizar.setVisible(true);
        Desktop.add(visualizar);
    }//GEN-LAST:event_itemReservaVisualizarActionPerformed

    private void itemOpcoesSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemOpcoesSairActionPerformed
        int sair = JOptionPane.showConfirmDialog(null, "Você deseja mesmo cancelar?", "Atenção", JOptionPane.YES_NO_OPTION);
        
        if (sair == JOptionPane.YES_OPTION){
            telaInicial inicial = new telaInicial();
            inicial.setVisible(true);
            this.dispose();
        }  
    }//GEN-LAST:event_itemOpcoesSairActionPerformed

    private void itemQuartosDisponivelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemQuartosDisponivelActionPerformed
        QuartosVisualizar quartos = new QuartosVisualizar();
        quartos.setVisible(true);
        Desktop.add(quartos);
    }//GEN-LAST:event_itemQuartosDisponivelActionPerformed

    private void itemClienteCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemClienteCadastrarActionPerformed
        ClienteCadastro clienteCadastrar = new ClienteCadastro();
        clienteCadastrar.setVisible(true);
        Desktop.add(clienteCadastrar);
    }//GEN-LAST:event_itemClienteCadastrarActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void itemClienteVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemClienteVisualizarActionPerformed
        ClientesVisualizar clientesVisualizar = new ClientesVisualizar();
        clientesVisualizar.setVisible(true);
        Desktop.add(clientesVisualizar);
    }//GEN-LAST:event_itemClienteVisualizarActionPerformed

    private void itemReservasFazerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemReservasFazerActionPerformed
        ReservasCadastrar reservaCadastrar = new ReservasCadastrar();
        reservaCadastrar.setVisible(true);
        Desktop.add(reservaCadastrar);
    }//GEN-LAST:event_itemReservasFazerActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(telaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(telaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(telaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(telaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new telaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane Desktop;
    private javax.swing.JMenuItem itemClienteCadastrar;
    private javax.swing.JMenuItem itemClienteVisualizar;
    private javax.swing.JMenuItem itemOpcoesSair;
    private javax.swing.JMenuItem itemQuartosDisponivel;
    private javax.swing.JMenuItem itemReservaVisualizar;
    private javax.swing.JMenuItem itemReservasCancelar;
    private javax.swing.JMenuItem itemReservasFazer;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu menuQuartos;
    private javax.swing.JMenu menuReservas;
    private javax.swing.JMenu menuSair;
    private javax.swing.JMenu menuSobre;
    // End of variables declaration//GEN-END:variables
}