
package telasPagamentos;

import conexao.ModuloConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class PagamentosVisualizar extends javax.swing.JInternalFrame {
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public PagamentosVisualizar() {
        initComponents();
        conexao = ModuloConexao.conector();
        Pagamentos();
    }

    private void Pagamentos(){
        try {
            String sql = "SELECT tabela_pagamentos.*, tabela_clientes.clientes_nome" +
                         "FROM tabela_pagamentos " +
                         "JOIN tabela_clientes ON tabela_pagamentos.clientes_id = tabela_clientes.clientes_id " +
                         "JOIN tabela_reservas ON tabela_pagamentos.reservas_id = tabela_reservas.reservas_id " ;
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while (rs.next()){
                int ID = rs.getInt("pagamentos_id");
                String dataReserva = rs.getString("pagamentos_dataReserva");
                int reservaID = rs.getInt("pagamentos_reservasID");
                int clienteID = rs.getInt("pagamentos_clientesID");
                String clienteNome = rs.getString("clientes_nome");
                double valorTotal = rs.getDouble("pagamentos_valorTotal");
                String situacao = rs.getString("pagamentos_situacao");
                
                DefaultTableModel modelo = (DefaultTableModel) tabelaPagamentos.getModel();
                modelo.addRow(new Object[]{ID, dataReserva, reservaID, clienteID, clienteNome, valorTotal, situacao});
            }
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Não foi possivel efetuar a procura!");
        }
    }
    
    private void BuscarPagamento() {
        String sql = "SELECT tabela_pagamentos.*, tabela_clientes.clientes_nome" +
                    "FROM tabela_pagamentos " +
                    "JOIN tabela_clientes ON tabela_pagamentos.clientes_id = tabela_clientes.clientes_id " +
                    "JOIN tabela_reservas ON tabela_pagamentos.reservas_id = tabela_reservas.reservas_id " ;
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, PagamentoID.getText());
            rs = pst.executeQuery();
            
            if (rs.next()){
                pagamentoDataReserva.setText(rs.getString(2));
                pagamentoIDReserva.setText(rs.getString(3));
                NomeCliente.setText(rs.getString(4));
                pagamentoValorTotal.setText(rs.getString(5));
            }
            else {
                JOptionPane.showMessageDialog(null, "Não foi possível achar informação!");
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não foi possível achar informação!");
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaPagamentos = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        PagamentoID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        buscarID = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        pagamentoDataReserva = new javax.swing.JTextField();
        pagamentoIDReserva = new javax.swing.JTextField();
        NomeCliente = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        pagamentoValorTotal = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnEfetuar = new javax.swing.JButton();

        setClosable(true);
        setTitle("Pagamentos");

        tabelaPagamentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Data da Reserva", "ID da Reserva", "ID do Cliente", "Nome Cliente", "Valor Total", "Situação"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaPagamentos.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tabelaPagamentos);
        if (tabelaPagamentos.getColumnModel().getColumnCount() > 0) {
            tabelaPagamentos.getColumnModel().getColumn(0).setPreferredWidth(50);
            tabelaPagamentos.getColumnModel().getColumn(1).setPreferredWidth(75);
            tabelaPagamentos.getColumnModel().getColumn(2).setPreferredWidth(50);
            tabelaPagamentos.getColumnModel().getColumn(3).setPreferredWidth(50);
            tabelaPagamentos.getColumnModel().getColumn(4).setPreferredWidth(200);
            tabelaPagamentos.getColumnModel().getColumn(5).setPreferredWidth(75);
            tabelaPagamentos.getColumnModel().getColumn(6).setPreferredWidth(90);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 876, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(221, 221, 221))
        );

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel1.setText("Pagamentos de Reservas");

        jLabel2.setText("ID");

        buscarID.setText("BUSCAR");
        buscarID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarIDActionPerformed(evt);
            }
        });

        jLabel3.setText("Data da Reserva");

        jLabel6.setText("ID da Reserva");

        jLabel4.setText("Nome do Cliente");

        jLabel5.setText("Valor Total");

        btnEfetuar.setText("EFETUAR");
        btnEfetuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEfetuarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(btnEfetuar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(PagamentoID, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(buscarID))
                            .addComponent(NomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pagamentoValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pagamentoDataReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pagamentoIDReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(81, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PagamentoID, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscarID)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(pagamentoDataReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pagamentoIDReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pagamentoValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(75, 75, 75)
                .addComponent(btnEfetuar)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buscarIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarIDActionPerformed
        BuscarPagamento();
    }//GEN-LAST:event_buscarIDActionPerformed

    private void btnEfetuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEfetuarActionPerformed
        PagamentosReserva pagamento = new PagamentosReserva();
        pagamento.setVisible(true);
    }//GEN-LAST:event_btnEfetuarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField NomeCliente;
    private javax.swing.JTextField PagamentoID;
    private javax.swing.JButton btnEfetuar;
    private javax.swing.JButton buscarID;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField pagamentoDataReserva;
    private javax.swing.JTextField pagamentoIDReserva;
    private javax.swing.JTextField pagamentoValorTotal;
    private javax.swing.JTable tabelaPagamentos;
    // End of variables declaration//GEN-END:variables
}
