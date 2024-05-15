package telasClientes;

import java.sql.*;
import conexao.ModuloConexao;
import javax.swing.JOptionPane;
import telas.telaPrincipal;

public class ClienteCadastro extends javax.swing.JInternalFrame {
    Connection conexao = null;
    PreparedStatement pst = null;
    
    public ClienteCadastro() {
        initComponents();
        conexao = ModuloConexao.conector();
    }
    
    public void ClientesCadastrar(){
        try {
            String sql = "INSERT INTO tabela_clientes (clientes_nome, clientes_cpf, clientes_endereco, clientes_ddd, clientes_telefone, clientes_cidade, clientes_estado, clientes_email) VALUES (?,?,?,?,?,?,?,?)";
            pst = conexao.prepareStatement(sql);
            
            pst.setString(1, clienteNome.getText());
            pst.setString(2, clienteCPF.getText());
            pst.setString(3, clienteEndereco.getText());
            pst.setString(4, clienteDDD.getText());
            pst.setString(5, clienteTelefone.getText());
            pst.setString(6, clienteCidade.getText());
            pst.setString(7, clienteEstado.getSelectedItem().toString());
            pst.setString(8, clienteEmail.getText());
            
            int add = pst.executeUpdate();
            if (add > 0){
                JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
                
                clienteNome.setText(null);
                clienteCPF.setText(null);
                clienteEndereco.setText(null);
                clienteDDD.setText(null);
                clienteTelefone.setText(null);
                clienteCidade.setText(null);
                clienteEstado.setSelectedItem(null);
                clienteEmail.setText(null);
            }
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Cliente não cadastrado");
        }
    }
    
    public void ClienteCancelar() {
        this.dispose();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        clienteNome = new javax.swing.JTextField();
        clienteCPF = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        clienteTelefone = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        clienteDDD = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        clienteEmail = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        clienteEndereco = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        clienteEstado = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        clienteCidade = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        clientesCadastrar = new javax.swing.JButton();
        clientesCancelar = new javax.swing.JButton();

        setClosable(true);

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel1.setText("Insira os dados");

        jLabel2.setText("Nome");

        jLabel3.setText("CPF");

        jLabel4.setText("Endereço");

        jLabel5.setText("DDD");

        jLabel6.setText("Telefone");

        jLabel7.setText("Cidade");

        clienteEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Escolha o estado --", "AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MG", "MT", "MS", "PA", "PB", "PE", "PI", "PR", "RJ", "RN", "RO", "RR", "RS", "SC", "SE", "SP", "TO" }));

        jLabel8.setText("Estado");

        jLabel9.setText("E-mail");

        clientesCadastrar.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        clientesCadastrar.setText("CADASTRAR");
        clientesCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientesCadastrarActionPerformed(evt);
            }
        });

        clientesCancelar.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        clientesCancelar.setText("CANCELAR");
        clientesCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientesCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(clientesCadastrar)
                        .addGap(18, 18, 18)
                        .addComponent(clientesCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(clienteCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(clienteEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(clienteDDD, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(clienteTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(clienteNome, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                        .addComponent(clienteEndereco)
                                        .addComponent(clienteCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(clienteEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(clienteNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clienteCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(clienteEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(clienteTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(clienteDDD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(jLabel5)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clienteCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clienteEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clienteEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clientesCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clientesCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clientesCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientesCadastrarActionPerformed
        ClientesCadastrar();
    }//GEN-LAST:event_clientesCadastrarActionPerformed

    private void clientesCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientesCancelarActionPerformed
        ClienteCancelar();
    }//GEN-LAST:event_clientesCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField clienteCPF;
    private javax.swing.JTextField clienteCidade;
    private javax.swing.JTextField clienteDDD;
    private javax.swing.JTextField clienteEmail;
    private javax.swing.JTextField clienteEndereco;
    private javax.swing.JComboBox<String> clienteEstado;
    private javax.swing.JTextField clienteNome;
    private javax.swing.JTextField clienteTelefone;
    private javax.swing.JButton clientesCadastrar;
    private javax.swing.JButton clientesCancelar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    // End of variables declaration//GEN-END:variables
}
